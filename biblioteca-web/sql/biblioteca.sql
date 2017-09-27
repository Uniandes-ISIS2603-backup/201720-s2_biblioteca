delete  from usuarioEntity;
delete from bibliotecaEntity;
delete from blogEntity;
delete from comentarioEntity;
delete from ebookEntity;
delete from evideoEntity;
delete from mediopagoEntity;
delete from multaEntity;
delete from prestamoEntity;
delete from recursoEntity;
delete from reservaEntity;
delete from reservaEntity_recursoEntity;
delete from salaEntity;
delete from videoEntity;

insert into usuarioEntity (id,direccion,name,telefono) values (1,'Direccion1','Nombre1','Telefono1');
insert into usuarioEntity (id,direccion,name,telefono) values (2,'Direccion2','Nombre2','Telefono2');

insert into mediopagoEntity (id,name,descripcion,tipo,miusuario_id) values (1,NULL,'0000 1111 2222',1,1);

insert into prestamoEntity (id,externo,fechafinal,fechainicio,generomulta,name,retornado,miusuario_id) values(1,0,'2017-09-26','2017-09-28',0,NULL,0,1);
insert into prestamoEntity (id,externo,fechafinal,fechainicio,generomulta,name,retornado,miusuario_id) values(2,0,'2017-09-27','2017-09-29',0,NULL,0,1);

