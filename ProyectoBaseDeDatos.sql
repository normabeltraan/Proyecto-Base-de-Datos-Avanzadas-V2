
/**
Creación de base de datos SistemaConsultas
**/
CREATE DATABASE SistemaConsultas;

USE SistemaConsultas;

CREATE TABLE USUARIOS(
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(50) UNIQUE NOT NULL,
    contrasenia VARCHAR(20) NOT NULL
);
-- Cambio de la longitud de contraseña debido al momento de encriptación
ALTER TABLE USUARIOS MODIFY contrasenia VARCHAR(255);


CREATE TABLE DIRECCIONES(
    id_direccion INT AUTO_INCREMENT PRIMARY KEY,
    colonia VARCHAR(50) NOT NULL,
    ciudad VARCHAR(50) NOT NULL,
    calle VARCHAR(50) NOT NULL
);

CREATE TABLE PACIENTES(
    id_usuario INT PRIMARY KEY,
    nombre TEXT(100) NOT NULL,
    apellido_paterno VARCHAR(100) NOT NULL,
    apellido_materno VARCHAR(100),
    telefono VARCHAR(10) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    correo_electronico VARCHAR(50) UNIQUE NOT NULL,
    id_direccion INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES USUARIOS(id_usuario),
    FOREIGN KEY (id_direccion) REFERENCES DIRECCIONES(id_direccion)
);

CREATE TABLE MEDICOS(
    id_usuario INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido_paterno VARCHAR(100) NOT NULL,
    apellido_materno VARCHAR(100),
    estado ENUM("Activo", "Inactivo") NOT NULL, #ENUM
    especialidad VARCHAR(100) NOT NULL,
    cedula VARCHAR(10) UNIQUE NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)
);

CREATE TABLE CITAS(
    id_cita INT PRIMARY KEY AUTO_INCREMENT,
    fecha_hora DATETIME NOT NULL,
    estado ENUM("Activa", "Atendida", "No atendida", "Cancelada", "No asistio el paciente") NOT NULL,
    tipo ENUM("Cita Agendada", "Cita Emergencia") NOT NULL, 
    id_usuario_paciente INT NOT NULL,
    id_usuario_medico INT NOT NULL,
    FOREIGN KEY (id_usuario_paciente) REFERENCES Pacientes(id_usuario),
    FOREIGN KEY (id_usuario_medico) REFERENCES Medicos(id_usuario)
);

CREATE TABLE CONSULTAS(
    id_consulta INT PRIMARY KEY AUTO_INCREMENT,
    diagnostico TEXT NOT NULL,
    tratamiento TEXT NOT NULL,
    observaciones TEXT NOT NULL,
    id_cita INT NOT NULL,
    FOREIGN KEY (id_cita) REFERENCES Citas(id_cita)
);

CREATE TABLE CITAS_SINCITA (
	id_cita INT PRIMARY KEY,
	folio_emergencia VARCHAR(8) NOT NULL,
	FOREIGN KEY (id_cita) REFERENCES Citas(id_cita)
);

CREATE TABLE HORARIOS(
    id_horario INT PRIMARY KEY AUTO_INCREMENT,
    dia INT NOT NULL,
    hora_entrada TIME NOT NULL,
    hora_salida TIME NOT NULL,
    id_usuario_medico INT NOT NULL,
    FOREIGN KEY (id_usuario_medico) REFERENCES Medicos(id_usuario)
);

CREATE TABLE AUDITORIAS(
	id_auditoria INT PRIMARY KEY AUTO_INCREMENT,
    fecha_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
    tipo_accion enum("Cita programada", "Consulta realizada", "Cita cancelada"),
    id_usuario_paciente INT NOT NULL,
    id_cita INT NOT NULL,
	FOREIGN KEY (id_usuario_paciente) REFERENCES Pacientes(id_usuario),
    FOREIGN KEY (id_cita) REFERENCES Citas(id_cita)
);


/**
Creación de registros en las tablas de la base de datos
**/
INSERT INTO USUARIOS (nombre_usuario, contrasenia) 
VALUES
('karlacota', 'karlita123'),
('juanlopez', 'juan123'),
('josesanchez', 'josesan'),
('aliciasoto', 'alicia123'),
('javi', 'javierjavier'),
('danicano', 'danicano123'),
('fernando', 'fermendez');

INSERT INTO DIRECCIONES (colonia, ciudad, calle) 
VALUES
('Centro', 'Obregon', 'Pitagoras'),
('Bellavista', 'Hermosillo', 'Paris');

INSERT INTO PACIENTES (id_usuario, nombre, apellido_paterno, apellido_materno, telefono, fecha_nacimiento, correo_electronico, id_direccion)
VALUES
(1, 'Karla', 'Cota', 'Hernandez', '7715289543', '1990-12-12', 'karlacotaher@example.com', 1),
(7, 'Fernando', 'Mendez', 'Lizo', '123456789', '2004-04-19', 'fernando@example.com', 2);


INSERT INTO MEDICOS (id_usuario, nombre, apellido_paterno, apellido_materno, estado, especialidad, cedula) 
VALUES
(2, 'Juan', 'Lopez', 'Diaz', 'Activo', 'Cardiologia', '4698745213'),
(3, 'Jose', 'Sanchez', 'Martinez', 'Activo', 'Cardiologia', '6723984123'),
(4, 'Alicia', 'Soto', 'Felix', 'Activo', 'Dermatologia', '0123456789'),
(5, 'Javier', 'Alvarez', 'Garcia', 'Activo', 'Odontologia', '7321648554'),
(6, 'Daniela', 'Cano', 'Arias', 'Activo', 'Dermatologia', '2468864268');

INSERT INTO HORARIOS (dia, hora_entrada, hora_salida, id_usuario_medico) 
VALUES
('1', '07:00:00', '16:00:00', 2),
('2', '07:00:00', '16:00:00', 2),
('3', '07:00:00', '16:00:00', 2),
('4', '07:00:00', '16:00:00', 2),
('5', '08:00:00', '15:00:00', 2),
('6', '08:00:00', '15:00:00', 2),
('7', '07:00:00', '16:00:00', 4);

INSERT INTO HORARIOS (dia, hora_entrada, hora_salida, id_usuario_medico) 
VALUES
('1', '09:00:00', '16:00:00', 3),
('2', '09:00:00', '16:00:00', 3),
('3', '09:00:00', '16:00:00', 3),
('4', '09:00:00', '16:00:00', 3),
('5', '08:00:00', '16:00:00', 3),
('6', '07:00:00', '16:00:00', 4),
('7', '07:00:00', '16:00:00', 4);

INSERT INTO HORARIOS (dia, hora_entrada, hora_salida, id_usuario_medico) 
VALUES
('1', '07:00:00', '16:00:00', 4),
('2', '07:00:00', '16:00:00', 4),
('3', '07:00:00', '16:00:00', 4),
('4', '07:00:00', '16:00:00', 4),
('5', '07:00:00', '16:00:00', 4),
('6', '07:00:00', '16:00:00', 4),
('7', '07:00:00', '16:00:00', 4);

INSERT INTO HORARIOS (dia, hora_entrada, hora_salida, id_usuario_medico) 
VALUES
('1', '07:00:00', '16:00:00', 5),
('2', '08:00:00', '17:00:00', 5),
('3', '08:00:00', '17:00:00', 5),
('4', '08:00:00', '17:00:00', 5),
('5', '08:00:00', '17:00:00', 5),
('6', '09:00:00', '16:00:00', 5),
('7', '07:00:00', '16:00:00', 4);

INSERT INTO HORARIOS (dia, hora_entrada, hora_salida, id_usuario_medico) 
VALUES
('1', '09:00:00', '16:00:00', 6),
('2', '09:00:00', '16:00:00', 6),
('3', '09:00:00', '16:00:00', 6),
('4', '09:00:00', '16:00:00', 6),
('5', '09:00:00', '16:00:00', 6),
('6', '07:00:00', '16:00:00', 4),
('7', '07:00:00', '16:00:00', 4);

INSERT INTO CITAS (fecha_hora, estado, tipo, id_usuario_paciente, id_usuario_medico) 
VALUES
('2025-02-28 09:30:00', 'Activa', 'Cita Agendada', 1, 2),
('2025-02-27 12:30:00', 'Activa', 'Cita Agendada', 1, 3),
('2025-02-27 07:00:00', 'Activa', 'Cita Agendada', 1, 4),
('2025-02-27 15:00:00', 'Activa', 'Cita Agendada', 7, 6),
('2025-02-28 17:00:00', 'Activa', 'Cita Emergencia', 7, 5);

INSERT INTO CITAS_SINCITA(id_cita, folio_emergencia ) 
VALUES
(5, 12345678);

INSERT INTO CITAS (fecha_hora, estado, tipo, id_usuario_paciente, id_usuario_medico) 
VALUES
('2025-02-25 09:30:00', 'Atendida', 'Cita Agendada', 1, 2),
('2025-02-24 12:30:00', 'Atendida', 'Cita Agendada', 1, 3),
('2025-02-27 07:00:00', 'Atendida', 'Cita Agendada', 1, 4),
('2025-02-27 15:00:00', 'Activa', 'Cita Agendada', 7, 6),
('2025-02-28 17:00:00', 'Activa', 'Cita Emergencia', 7, 5);

INSERT INTO CITAS_SINCITA(id_cita, folio_emergencia ) 
VALUES
(10, 87654321);

INSERT INTO CONSULTAS (diagnostico, tratamiento, observaciones, id_cita) 
VALUES
('Hipertensión', 'Medicamentos antihipertensivos', 'Control de presión arterial', 6),
('Gripe', 'Descanso y líquidos', 'Seguir tratamiento y control', 7),
('Dermatitis', 'Crema reconstructora', 'Ninguna', 8),
('Caries', 'Limpieza profunda', 'Comprar pasta de dientes especial', 9),
('Problema de acne', 'Comprar jabon especial', 'Comprar marca profesional', 9);

/**
Creación de procedimientos, triggers, y vista
**/

/**
Trigger para generar registro al momento de agendar una cita
**/
DELIMITER //
CREATE TRIGGER auditoria_agendar_cita
AFTER INSERT ON CITAS
FOR EACH ROW
BEGIN
    INSERT INTO AUDITORIAS (tipo_accion, id_usuario_paciente, id_cita)
    VALUES ('Cita programada', NEW.id_usuario_paciente, NEW.id_cita);
END//
DELIMITER ;

/**
Trigger para generar registro al momento de que una consulta qude realizada
**/
DELIMITER //
CREATE TRIGGER auditoria_consulta_realizada
AFTER INSERT ON CONSULTAS
FOR EACH ROW
BEGIN
    INSERT INTO AUDITORIAS (tipo_accion, id_usuario_paciente, id_cita)
    VALUES ('Consulta realizada', (SELECT id_usuario_paciente FROM CITAS WHERE id_cita = NEW.id_cita), NEW.id_cita);
END//
DELIMITER ;

/**
Trigger para generar registro al momento de cancelar una cita
**/
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


/**
Procedimiento almacenado con transacción para agendar una cita
**/
DELIMITER //
CREATE PROCEDURE AGENDAR_CITA(
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

/**
Vista que muestra todos los horarios disponibles de cada médico en general 
(No filtra si ese horario esta ocupado o no, solo muestra los horarios disponibles de
30 minutos que un médico tiene en relación a su horario (entrada y salida)
**/
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

/**
Procedimiento almacenado para cancelar una cita
**/
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

    IF diferencia < 24 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'La cita solo puede cancelarse con 24 horas de anticipación.';
    ELSE
        UPDATE CITAS
        SET estado = 'Cancelada'
        WHERE id_cita = ac_id_cita;
    END IF;
END //
DELIMITER ;



/**
Procedimiento almacenado para registrar a un usuario/paciente
**/
DELIMITER //
CREATE PROCEDURE REGISTRAR_USUARIO_PACIENTE(
    IN p_nombre_usuario VARCHAR(50),
    IN p_contrasenia VARCHAR(255),
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

/**
Procedimiento almacenado para agendar una cita de emergencia
**/
DELIMITER //
CREATE PROCEDURE AGENDAR_CITA_EMERGENCIA(
    IN ac_especialidad VARCHAR(100),
    IN ac_id_usuario_paciente INT
)
BEGIN
    DECLARE medico_encontrado INT DEFAULT NULL;
    DECLARE fecha_hora_emergencia DATETIME DEFAULT NULL;
    DECLARE folio_emergencia VARCHAR(8);
    DECLARE id_cita INT;
    DECLARE citas_misma_fecha_mismo_doctor INT DEFAULT 0;
    SELECT vh.id_usuario_medico, vh.fecha + INTERVAL TIME(vh.hora_disponible) HOUR_SECOND
    INTO medico_encontrado, fecha_hora_emergencia
    FROM vista_horarios_medicos vh
    JOIN medicos m ON vh.id_usuario_medico = m.id_usuario
    LEFT JOIN CITAS c ON vh.id_usuario_medico = c.id_usuario_medico
                      AND vh.fecha + INTERVAL TIME(vh.hora_disponible) HOUR_SECOND = c.fecha_hora
                      AND c.estado != 'Cancelada'
    WHERE m.especialidad = ac_especialidad
      AND c.id_cita IS NULL
    ORDER BY vh.fecha ASC, vh.hora_disponible ASC
    LIMIT 1;
    IF medico_encontrado IS NULL OR fecha_hora_emergencia IS NULL THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'No hay horarios disponibles para esa especialidad en este momento';
    END IF;
    SELECT COUNT(*) INTO citas_misma_fecha_mismo_doctor  
    FROM CITAS
    WHERE id_usuario_paciente = ac_id_usuario_paciente
    AND id_usuario_medico = medico_encontrado
    AND DATE(fecha_hora) = DATE(fecha_hora_emergencia)
    AND estado != 'Cancelada';
    IF citas_misma_fecha_mismo_doctor > 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'El paciente ya tiene una cita con este médico en esa fecha';
    END IF;
    SET folio_emergencia = LPAD(FLOOR(RAND() * 100000000), 8, '0');
    INSERT INTO CITAS (fecha_hora, estado, tipo, id_usuario_paciente, id_usuario_medico)
    VALUES (fecha_hora_emergencia, 'Activa', 'Cita Emergencia', ac_id_usuario_paciente, medico_encontrado);
    COMMIT;
    SET id_cita = LAST_INSERT_ID();  
    INSERT INTO CITAS_SINCITA (id_cita, folio_emergencia)
    VALUES (id_cita, folio_emergencia);
    SELECT cs.folio_emergencia, c.fecha_hora, c.id_usuario_medico, m.especialidad
    FROM CITAS c
    JOIN CITAS_SINCITA cs ON c.id_cita = cs.id_cita
    JOIN MEDICOS m ON c.id_usuario_medico = m.id_usuario
    WHERE c.id_cita = id_cita;

END //
DELIMITER ;


/**
Procedimiento almacenado para actualizar los datos de un paciente
**/
DELIMITER //
CREATE PROCEDURE ACTUALIZAR_DATOS_PACIENTE(
    IN p_id_usuario INT, 
    IN p_nombre TEXT, 
    IN p_apellido_paterno VARCHAR(100), 
    IN p_apellido_materno VARCHAR(100), 
    IN p_telefono VARCHAR(10), 
    IN p_fecha_nacimiento DATE, 
    IN p_correo_electronico VARCHAR(50)
)
BEGIN
    DECLARE citas_activas INT;

    SELECT COUNT(*) INTO citas_activas
    FROM CITAS
    WHERE id_usuario_paciente = p_id_usuario AND estado = 'Activa';

    IF citas_activas = 0 THEN
        UPDATE PACIENTES
        SET nombre = p_nombre,
            apellido_paterno = p_apellido_paterno,
            apellido_materno = p_apellido_materno,
            telefono = p_telefono,
            fecha_nacimiento = p_fecha_nacimiento,
            correo_electronico = p_correo_electronico
        WHERE id_usuario = p_id_usuario;
    ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El paciente no puede actualizar sus datos mientras tenga citas activas';
    END IF;
END //
DELIMITER ;


/**
Evento para actualizar el estado de las citas sin cita después del tiempo máximo de retraso del paciente
**/
DELIMITER //
CREATE EVENT ACTUALIZAR_CITAS_SINCITA
ON SCHEDULE EVERY 1 MINUTE
DO
BEGIN
	UPDATE Citas C
    JOIN CITAS_SINCITA CSC ON C.id_cita = CSC.id_cita
    SET C.estado = 'No atendida'
    WHERE C.estado = 'Activa'
    AND TIMESTAMPDIFF(MINUTE, c.fecha_hora, NOW()) > 10;
END //
DELIMITER ;

DELIMITER //
CREATE EVENT ACTUALIZAR_CITAS_CON_CITA_PREVIA
ON SCHEDULE EVERY 1 MINUTE
DO
BEGIN
    UPDATE Citas C
    SET C.estado = 'No asistio el paciente'
    WHERE C.estado = 'Activa'
    AND C.tipo = 'Cita Agendada'
    AND TIMESTAMPDIFF(MINUTE, c.fecha_hora, NOW()) > 15;
END //
DELIMITER ;

