delete from libroEntity;
delete from videoEntity;
delete from mediopagoEntity;
delete from multaEntity;
delete from reservaEntity;
delete from comentarioEntity;
delete from bibliotecaEntity;
delete from blogEntity;
delete from ebookEntity;
delete from ecomentarioEntity;
delete from evideoEntity;
delete from recursoEntity;
delete from reservaEntity_recursoEntity;
delete from salaEntity;
delete from prestamoEntity;
delete from usuarioEntity;



insert into bibliotecaEntity (id,name,ubicacion) values (1,'VirgilioBarco','Calle63');


insert into usuarioEntity (id,direccion,name,telefono) values (1,'Cra 75','Juan Perez','11112222');
insert into usuarioEntity (id,direccion,name,telefono) values (2,'Direccion2','Nombre2','Telefono2');

insert into mediopagoEntity (id,name,descripcion,tipo,miusuario_id) values (1,NULL,'0000 1111 2222',1,1);

insert into ebookEntity (id, name, autor, numeroPaginas) values (1,'El tio petros y la conjetur de goldbach' , 'Aposotolos Dioxiadis', 172);
insert into ebookEntity (id, name, autor, numeroPaginas) values (2,'Calculo Vectorial' , 'Jerrold Marsden, Antony Tromba ', 666);

insert into prestamoentity (id,fechainicio,fechafinal,generomulta,externo,retornado,miusuario_id) values (1,'2017-11-09','2017-11-23',0,1,0,1);
insert into prestamoentity (id,fechainicio,fechafinal,generomulta,externo,retornado,miusuario_id) values (2,'2017-10-09','2017-10-23',0,1,0,1);


insert into evideoEntity (id, name, autor, duracion) values (1,'Curso AngularJS - Introduccion' , 'codigoFacilito', 10);
insert into evideoEntity (id, name, autor, duracion) values (2,'Curso AngularJS - Hola Mundo' , 'codigoFacilito', 5);


insert into libroEntity(id,aniopublicacion,autor,name,numpaginas, miprestamo_id) values (1,1996,'James','Moby Dick',123,1);
insert into libroEntity(id,aniopublicacion,autor,name,numpaginas) values (2,1997,'Javier','Oliver Twist',123);
insert into libroEntity(id,aniopublicacion,autor,name,numpaginas) values (3,1998,'Jax','Twister',123);

insert into videoEntity (id,name,autor,duracion) values (1,'Cosmos','Carl Sagan',92);


