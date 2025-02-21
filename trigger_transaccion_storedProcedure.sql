
-- AGENDAR CITA
-- Trigger
DELIMITER //
CREATE TRIGGER auditoria_agendar_cita
AFTER INSERT ON CITAS
FOR EACH ROW
BEGIN
    INSERT INTO AUDITORIAS (tipo_accion, id_usuario_paciente, id_cita)
    VALUES ('Cita programada', NEW.id_usuario_paciente, NEW.id_cita);
END//
DELIMITER ;

-- Los otros TRIGGERS para auditoria

DELIMITER //
CREATE TRIGGER auditoria_consulta_realizada
AFTER INSERT ON CONSULTAS
FOR EACH ROW
BEGIN
    INSERT INTO AUDITORIAS (tipo_accion, id_usuario_paciente, id_cita)
    VALUES ('Consulta realizada', (SELECT id_usuario_paciente FROM CITAS WHERE id_cita = NEW.id_cita), NEW.id_cita);
END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER auditoria_cancelar_cita
AFTER UPDATE ON CITAS
FOR EACH ROW
BEGIN
    IF OLD.estado != 'Cancelada' AND NEW.estado = 'Cancelada' THEN
        INSERT INTO AUDITORIAS (tipo_accion, id_usuario_paciente, id_cita)
        VALUES ('Cita cancelada', (SELECT id_usuario_paciente FROM CITAS WHERE id_cita = NEW.id_cita), NEW.id_cita);
    END IF;
END//
DELIMITER ;


-- Producedure agendar_cita
--
DELIMITER //
CREATE PROCEDURE AGREGAR_CITA(
    IN ac_fecha_hora DATETIME,
    IN ac_id_usuario_paciente INT,
    IN ac_id_usuario_medico INT
)
BEGIN
    DECLARE citas_misma_fecha_mismo_doctor INT;
    DECLARE horario_ocupado INT;
    
    START TRANSACTION;

    SELECT COUNT(*) INTO citas_misma_fecha_mismo_doctor 
    FROM CITAS
    WHERE id_usuario_paciente = ac_id_usuario_paciente
    AND id_usuario_medico = ac_id_usuario_medico
    AND DATE(fecha_hora) = DATE(ac_fecha_hora)
    AND estado != 'Cancelada';

    SELECT COUNT(*) INTO horario_ocupado 
    FROM CITAS
    WHERE id_usuario_medico = ac_id_usuario_medico
    AND estado != 'Cancelada'
    AND ac_fecha_hora >= fecha_hora 
    AND ac_fecha_hora < DATE_ADD(fecha_hora, INTERVAL 30 MINUTE);

    IF citas_misma_fecha_mismo_doctor > 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'El paciente ya tiene una cita con este médico en esa fecha';
    ELSEIF horario_ocupado > 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'El médico ya tiene una cita agendada en un horario que se empalma con la nueva cita';
    ELSE
        INSERT INTO CITAS (fecha_hora, estado, tipo, id_usuario_paciente, id_usuario_medico)
        VALUES (ac_fecha_hora, 'Activa', 'Cita Agendada', ac_id_usuario_paciente, ac_id_usuario_medico);
        COMMIT;
    END IF;
END //
DELIMITER ;

-- Vista
CREATE VIEW vista_horarios_medicos AS
WITH RECURSIVE horasintervalos AS (
    SELECT h.hora_entrada AS hora_disponible, h.id_usuario_medico, h.dia
    FROM horarios h
    UNION ALL
    SELECT hi.hora_disponible + INTERVAL 30 MINUTE, hi.id_usuario_medico, hi.dia
    FROM horasintervalos hi
    JOIN horarios h ON hi.id_usuario_medico = h.id_usuario_medico AND hi.dia = h.dia
    WHERE hi.hora_disponible < h.hora_salida
)
SELECT 
    h.id_usuario_medico,
    m.nombre AS nombre_medico,
    h.dia,
    hi.hora_disponible,
    CASE 
        WHEN DATE_ADD(DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY), INTERVAL h.dia - 1 DAY) < CURDATE() 
        THEN DATE_ADD(DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY), INTERVAL h.dia - 1 DAY) + INTERVAL 1 WEEK
        ELSE DATE_ADD(DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY), INTERVAL h.dia - 1 DAY)
    END AS fecha
FROM 
    horasintervalos hi
JOIN 
    horarios h ON hi.id_usuario_medico = h.id_usuario_medico AND hi.dia = h.dia
JOIN 
    medicos m ON h.id_usuario_medico = m.id_usuario
LEFT JOIN 
    citas ci ON ci.id_usuario_medico = h.id_usuario_medico
            AND TIME(ci.fecha_hora) = TIME(hi.hora_disponible)
            AND h.dia = DAYOFWEEK(ci.fecha_hora)
            AND ci.estado != 'Cancelada'
WHERE 
    ci.id_cita IS NULL  
ORDER BY 
    h.id_usuario_medico, h.dia, hi.hora_disponible;

--
DELIMITER //
CREATE PROCEDURE CANCELAR_CITA(
    IN ac_id_cita INT
)
BEGIN
    DECLARE fecha_cita DATETIME;
    DECLARE fecha_actual DATETIME;
    DECLARE diferencia INT;
    DECLARE estado_cita ENUM('Activa', 'Atendida', 'No atendida', 'Cancelada', 'No asistio el paciente');

    SELECT fecha_hora, estado INTO fecha_cita, estado_cita
    FROM CITAS
    WHERE id_cita = ac_id_cita;

    IF estado_cita IS NULL THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'La cita no existe.';
    END IF;

    IF estado_cita != 'Activa' THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Solo se pueden cancelar citas activas.';
    END IF;

    SET fecha_actual = NOW();

    SET diferencia = TIMESTAMPDIFF(HOUR, fecha_actual, fecha_cita);

    IF diferencia > 24 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'La cita solo puede cancelarse con 24 horas de anticipación.';
    ELSE
        UPDATE CITAS
        SET estado = 'Cancelada'
        WHERE id_cita = ac_id_cita;
    END IF;
END //
DELIMITER ;

--
DELIMITER //
CREATE PROCEDURE REGISTRAR_USUARIO_PACIENTE(
    IN p_nombre_usuario VARCHAR(50),
    IN p_contrasenia VARCHAR(20),
    IN p_nombre_paciente VARCHAR(100),
    IN p_apellido_paterno VARCHAR(100),
    IN p_apellido_materno VARCHAR(100),
    IN p_telefono VARCHAR(10),
    IN p_fecha_nacimiento DATE,
    IN p_correo_electronico VARCHAR(50),
    IN p_id_direccion INT
)
BEGIN
    DECLARE v_id_usuario INT;
    DECLARE v_usuario_existente INT;
    DECLARE v_correo_existente INT;

    START TRANSACTION;

    SELECT COUNT(*) INTO v_usuario_existente
    FROM USUARIOS
    WHERE nombre_usuario = p_nombre_usuario;

    SELECT COUNT(*) INTO v_correo_existente
    FROM PACIENTES
    WHERE correo_electronico = p_correo_electronico;

    IF v_usuario_existente > 0 THEN
        ROLLBACK; 
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error. El nombre de usuario ingresado ya está registrado.';
    ELSEIF v_correo_existente > 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error. El correo electrónico ingresado ya está registrado.';
    ELSE
        INSERT INTO USUARIOS (nombre_usuario, contrasenia)
        VALUES (p_nombre_usuario, p_contrasenia);

        SET v_id_usuario = LAST_INSERT_ID();

        INSERT INTO PACIENTES (id_usuario, nombre, apellido_paterno, apellido_materno, telefono, fecha_nacimiento, correo_electronico, id_direccion)
        VALUES (v_id_usuario, p_nombre_paciente, p_apellido_paterno, p_apellido_materno, p_telefono, p_fecha_nacimiento, p_correo_electronico, p_id_direccion);

        COMMIT;
    END IF;
END//
DELIMITER ;

--
DELIMITER //
CREATE PROCEDURE AGREGAR_CITA_EMERGENCIA(
    IN ac_especialidad VARCHAR(100),
    IN ac_id_usuario_paciente INT
)
BEGIN
    DECLARE id_usuario_medico INT;
    DECLARE fecha_hora_emergencia DATETIME;
    DECLARE folio_emergencia VARCHAR(8);
    DECLARE id_cita INT;
    DECLARE citas_misma_fecha_mismo_doctor INT;
    DECLARE horario_ocupado INT;

    SELECT vh.id_usuario_medico, vh.fecha + INTERVAL TIME(vh.hora_disponible) HOUR_SECOND
    INTO id_usuario_medico, fecha_hora_emergencia
    FROM vista_horarios_medicos vh
    JOIN medicos m ON vh.id_usuario_medico = m.id_usuario
    LEFT JOIN CITAS c ON vh.id_usuario_medico = c.id_usuario_medico
                      AND vh.fecha + INTERVAL TIME(vh.hora_disponible) HOUR_SECOND = c.fecha_hora
                      AND c.estado != 'Cancelada'
    WHERE m.especialidad = ac_especialidad
      AND c.id_cita IS NULL
    ORDER BY vh.fecha ASC, vh.hora_disponible ASC
    LIMIT 1;

    IF fecha_hora_emergencia IS NULL THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'No hay horarios disponibles para esa especialidad en este momento';
    ELSE
        SELECT COUNT(*) INTO citas_misma_fecha_mismo_doctor 
        FROM CITAS
        WHERE id_usuario_paciente = ac_id_usuario_paciente
        AND id_usuario_medico = id_usuario_medico
        AND DATE(fecha_hora) = DATE(fecha_hora_emergencia)
        AND estado != 'Cancelada';

        IF citas_misma_fecha_mismo_doctor > 0 THEN
            SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'El paciente ya tiene una cita con este médico en esa fecha';
        ELSE
            SET folio_emergencia = LPAD(FLOOR(RAND() * 100000000), 8, '0');

            INSERT INTO CITAS (fecha_hora, estado, tipo, id_usuario_paciente, id_usuario_medico)
            VALUES (fecha_hora_emergencia, 'Activa', 'Cita Emergencia', ac_id_usuario_paciente, id_usuario_medico);

            SET id_cita = LAST_INSERT_ID();  

            INSERT INTO CITAS_SINCITA (id_cita, folio_emergencia)
            VALUES (id_cita, folio_emergencia);

            SELECT cs.folio_emergencia, c.fecha_hora, c.id_usuario_medico, m.especialidad
            FROM CITAS c
            JOIN CITAS_SINCITA cs ON c.id_cita = cs.id_cita
            JOIN MEDICOS m ON c.id_usuario_medico = m.id_usuario
            WHERE c.id_cita = id_cita;
        END IF;
    END IF;
END //
DELIMITER ;

