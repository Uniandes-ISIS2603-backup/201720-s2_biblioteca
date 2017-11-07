delete from libroEntity;
delete from prestamoEntity;
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
delete from videoEntity;
delete from usuarioEntity;



insert into bibliotecaEntity (id,name,ubicacion) values (1,'VirgilioBarco','Calle63');


insert into usuarioEntity (id,direccion,name,telefono) values (1,'Direccion1','Nombre1','Telefono1');
insert into usuarioEntity (id,direccion,name,telefono) values (2,'Direccion2','Nombre2','Telefono2');

insert into mediopagoEntity (id,name,descripcion,tipo,miusuario_id) values (1,NULL,'0000 1111 2222',1,1);

insert into ebookEntity (id, name, autor, numeroPaginas) values (1,'El tio petros y la conjetur de goldbach' , 'Aposotolos Dioxiadis', 172);
insert into ebookEntity (id, name, autor, numeroPaginas) values (2,'Calculo Vectorial' , 'Jerrold Marsden, Antony Tromba ', 666);


insert into evideoEntity (id, name, autor, duracion) values (1,'Curso AngularJS - Introduccion' , 'codigoFacilito', 10);
insert into evideoEntity (id, name, autor, duracion) values (2,'Curso AngularJS - Hola Mundo' , 'codigoFacilito', 5);


insert into libroEntity(id,aniopublicacion,autor,name,numpaginas) values (1,1996,'James','Moby Dick',123);
insert into libroEntity(id,aniopublicacion,autor,name,numpaginas) values (2,1997,'Javier','Oliver Twist',123);
insert into libroEntity(id,aniopublicacion,autor,name,numpaginas,mibiblioteca_id) values (3,1998,'Jax','Twister',123,1);



