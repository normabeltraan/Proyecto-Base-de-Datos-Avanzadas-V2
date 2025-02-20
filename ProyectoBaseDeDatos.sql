CREATE DATABASE SistemaConsultas;

USE SistemaConsultas;

CREATE TABLE USUARIOS(
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(50) UNIQUE NOT NULL,
    contrasenia VARCHAR(20) NOT NULL
);

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

INSERT INTO USUARIOS (nombre_usuario, contrasenia) 
VALUES
('karlacota', 'karlita12'),
('juanlopez', 'juan123'),
('josesanchez', 'josesan78');

INSERT INTO USUARIOS (nombre_usuario, contrasenia) 
VALUES
('fer', 'fer123');

INSERT INTO DIRECCIONES (colonia, ciudad, calle) 
VALUES
('Centro', 'Obregon', 'Pitagoras'),
('Bellavista', 'Hermosillo', 'Paris'),
('Itson', 'Navojoa', 'Milan');

INSERT INTO DIRECCIONES (colonia, ciudad, calle) 
VALUES
('Azul', 'Obregon', 'Nainari');
select * from direcciones;

INSERT INTO PACIENTES (id_usuario, nombre, apellido_paterno, apellido_materno, telefono, fecha_nacimiento, correo_electronico, id_direccion)
VALUES
(1, 'Karla', 'Cota', 'Hernandez', '7715289543', '1990-12-12', 'karlacotaher@example.com', 1),
(2, 'Juan', 'López', 'Espinoza', '9912734234', '1988-03-20', 'juanlopezes@example.com', 2);


INSERT INTO MEDICOS (id_usuario, nombre, apellido_paterno, apellido_materno, estado, especialidad, cedula) 
VALUES
(3, 'José', 'Sanchez', 'Martinez', 'Activo', 'Cardiologia', '6723984');

INSERT INTO MEDICOS (id_usuario, nombre, apellido_paterno, apellido_materno, estado, especialidad, cedula) 
VALUES
(6, 'Adel', 'Mendez', 'Lizo', 'Activo', 'Dermatologia', '12345');

INSERT INTO MEDICOS (id_usuario, nombre, apellido_paterno, apellido_materno, estado, especialidad, cedula) 
VALUES
(5, 'Alicia', 'Cano', 'Arias', 'Activo', 'Cardiologia', '123456');


INSERT INTO CITAS (fecha_hora, estado, tipo, id_usuario_paciente, id_usuario_medico) 
VALUES
('2025-04-20 09:30:00', 'Activa', 'Cita Agendada', 1, 3),
('2025-01-19 10:00:00', 'Cancelada', 'Cita Emergencia', 2, 3);

INSERT INTO CONSULTAS (diagnostico, tratamiento, observaciones, id_cita) 
VALUES
('Hipertensión', 'Medicamentos antihipertensivos', 'Control de presión arterial', 1),
('Gripe', 'Descanso y líquidos', 'Seguir tratamiento y control', 2);

INSERT INTO CITAS_SINCITA(id_cita, folio_emergencia) 
VALUES
(1, '836712'),
(2, '193645');

INSERT INTO HORARIOS (dia, hora_entrada, hora_salida, id_usuario_medico) 
VALUES
('1', '08:00:00', '16:00:00', 3),
('2', '07:00:00', '15:00:00', 3),
('3', '07:00:00', '14:00:00', 3);

INSERT INTO HORARIOS (dia, hora_entrada, hora_salida, id_usuario_medico) 
VALUES
('1', '08:00:00', '16:00:00', 6),
('2', '07:00:00', '15:00:00', 6),
('3', '07:00:00', '14:00:00', 6);


INSERT INTO HORARIOS (dia, hora_entrada, hora_salida, id_usuario_medico) 
VALUES
('1', '08:00:00', '16:00:00', 5);

INSERT INTO AUDITORIAS(tipo_accion, id_usuario_paciente, id_cita)
VALUES ("Cita programada", 1, 1 );


