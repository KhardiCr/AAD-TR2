INSERT INTO ligas (id, nombre_liga, fecha_inicio, fecha_fin) VALUES
(1, 'Liga Nacional', '2025-03-01', '2025-11-30');

INSERT INTO equipos (id, nombre_equipo, ciudad, id_liga) VALUES
(1, 'Tigres FC', 'Ciudad A', 1),
(2, 'Águilas FC', 'Ciudad B', 1),
(3, 'Leones FC', 'Ciudad C', 1);

INSERT INTO jugadores (nombre, posicion, valor_mercado, goles, nacionalidad, id_equipo) VALUES
('Juan Pérez', 'Delantero', 2896571, 26, 'Alemania', 1),
('Luis García', 'Portero', 2898790, 17, 'Brasil', 1),
('Carlos Sánchez', 'Delantero', 3951958, 21, 'Alemania', 2),
('Pedro Ramírez', 'Delantero', 1704376, 1, 'Argentina', 2),
('Andrés Gómez', 'Portero', 1362797, 15, 'Francia', 3),
('Manuel Torres', 'Portero', 3961513, 25, 'Francia', 3);

INSERT INTO entrenadores (nombre, calificacion, titulos, id_equipo) VALUES
('José Martínez', 9, 5, 1),
('Miguel Herrera', 6, 3, 2),
('Pablo López', 6, 4, 3);
