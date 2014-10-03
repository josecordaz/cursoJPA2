select e 
from Equipos e
--1.- Mostrar todos los partidos
select p 
from Partidos p
--2.- Mostrar todos los estadioss
select es 
from Estadios es
--3.- Mostrar todos los grupos
select g 
from Grupos g
--4.- Mostrar abreviatura y nombre de todos los equipos
select e.abreviatura,e.nombre 
from Equipos e
--5.- Mostrar nombre de los estadios ordenados por nombre
select es.nombre 
from Estadios es 
order by es.nombre
--6.- Mostrar los equipos que el nombre empiezen con D
select e 
from Equipos e 
where e.nombre 
    like "U%"
--6.- Mostrar los equipos que sean del grupo "B"
select e 
from Equipos e 
    join e.idGrupo g 
where g.nombre = "B"
--7.- Mostrar los partidos jugados en el estadio "Arena de Sao Paulo"
select p 
from Partidos p 
    join p.idEstadio es 
where es.nombre = "Arena de Sao Paulo"
--8.- Mostrar los partidos jugados en la ciudad de "Brasilia"
select p 
from Partidos p 
    join p.idEstadio es 
where es.ciudad = "Brasilia"
--9.- Mostrar los equipos que se han enfrentado en la ciudad de "Rio De Janeiro"
select e1.nombre, 
    e2.nombre 
from Partidos p 
    join p.idEquipo1 e1 
    join p.idEquipo2 e2 
    join p.idEstadio es 
where es.ciudad = "Rio De Janeiro"
--10.- Mostrar los equipo que han jugado en la ciudad de "Fortaleza"
select e1.nombre, e2.nombre
from Partidos p
    join p.idEquipo1 e1 
    join p.idEquipo2 e2
    join p.idEstadio es
where es.ciudad = "Fortaleza"
--11.- Mostrar los estadios donde han jugado equipos del grupo "D"
select es
from Partidos p 
    join p.idEstadio es 
    join p.idEquipo1 e1 
    join p.idEquipo2 e2 
    join e1.idGrupo g1 
    join e2.idGrupo g2 
where g1.nombre = "D" 
    or g2.nombre = "D"