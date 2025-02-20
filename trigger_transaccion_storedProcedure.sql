
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


