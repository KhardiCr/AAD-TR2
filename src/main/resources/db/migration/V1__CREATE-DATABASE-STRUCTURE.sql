CREATE TABLE IF NOT EXISTS ligas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_liga VARCHAR(255) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS equipos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_equipo VARCHAR(255) NOT NULL,
    ciudad VARCHAR(255) NOT NULL,
    id_liga INT,
    FOREIGN KEY (id_liga) REFERENCES ligas(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS jugadores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    posicion VARCHAR(50) NOT NULL,
    valor_mercado DECIMAL(10,2) NOT NULL,
    goles INT NOT NULL DEFAULT 0,
    nacionalidad VARCHAR(50) NOT NULL,
    id_equipo INT,
    FOREIGN KEY (id_equipo) REFERENCES equipos(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS entrenadores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    calificacion INT NOT NULL CHECK (calificacion BETWEEN 1 AND 10),
    titulos INT NOT NULL DEFAULT 0,
    id_equipo INT UNIQUE,
    FOREIGN KEY (id_equipo) REFERENCES equipos(id) ON DELETE SET NULL
);