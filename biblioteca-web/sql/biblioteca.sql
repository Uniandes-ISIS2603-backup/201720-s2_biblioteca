delete from libroEntity;
delete from prestamoEntity;
delete from mediopagoEntity;
delete from multaEntity;
delete from reservaEntity;
delete from comentarioEntity;
delete from bibliotecaEntity;
delete from blogEntity;
delete from ebookEntity;
delete from evideoEntity;
delete from recursoEntity;
delete from reservaEntity_recursoEntity;
delete from salaEntity;
delete from videoEntity;
delete  from usuarioEntity;

insert into bibliotecaEntity (id,name,ubicacion) values (1,'VirgilioBarco','Calle63');


insert into usuarioEntity (id,direccion,name,telefono) values (1,'Direccion1','Nombre1','Telefono1');
insert into usuarioEntity (id,direccion,name,telefono) values (2,'Direccion2','Nombre2','Telefono2');

insert into mediopagoEntity (id,name,descripcion,tipo,miusuario_id) values (1,NULL,'0000 1111 2222',1,1);


insert into libroEntity(id,aniopublicacion,autor,name,numpaginas) values (1,1996,'James','Moby Dick',123);
insert into libroEntity(id,aniopublicacion,autor,name,numpaginas) values (2,1997,'Javier','Oliver Twist',123);
insert into libroEntity(id,aniopublicacion,autor,name,numpaginas,mibiblioteca_id) values (3,1998,'Jax','Twister',123,1);



