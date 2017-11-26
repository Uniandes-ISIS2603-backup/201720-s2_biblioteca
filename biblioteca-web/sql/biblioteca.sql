delete from libroEntity;
delete from videoEntity;
delete from salaEntity;
delete from mediopagoEntity;
delete from multaEntity;
delete from reservaEntity;
delete from comentarioEntity;
delete from blogEntity;
delete from ebookEntity;
delete from ecomentarioEntity;
delete from evideoEntity;
delete from recursoEntity;
delete from reservaEntity_recursoEntity;
delete from prestamoEntity;
delete from usuarioEntity;
delete from bibliotecaEntity;



insert into BibliotecaEntity (id, name, ubicacion, imagen) values(1, 'Biblioteca pública Virgilio Barco', 'Bogotá', 'https://talkcontract.contractdesign.com/wp-content/themes/talkcontract/images/typepad/6a00d83451b6a969e201310fcb3701970c-800wi.jpg');
insert into BibliotecaEntity (id, name, ubicacion, imagen) values(2, 'Biblioteca pública Gabriel García Márquez', 'Bogotá', 'http://contenidos.civico.com/wp-content/uploads/2015/07/biblioteca-publica-gabriel-garcia-marquez-wikipedia-1.jpg');
insert into BibliotecaEntity (id, name, ubicacion, imagen) values(3, 'Parque Biblioteca León de Grieff', 'Medellín', 'https://images.adsttc.com/media/images/5742/57f1/e58e/ce6e/c900/026e/newsletter/BLGM_F_ext_B_01.jpg?1463965670');
insert into BibliotecaEntity (id, name, ubicacion, imagen) values(4, 'Parque Biblioteca Fernando Botero', 'Medellín', 'http://farm7.staticflickr.com/6154/6264832674_c4680165e9_b.jpg');
insert into BibliotecaEntity (id, name, ubicacion, imagen) values(5, 'Biblioteca Departamental Jorge Garcés Borrero', 'Cali', 'https://upload.wikimedia.org/wikipedia/commons/7/7c/Biblioteca_Departamental_Jorge_Garces_Borrero_%282%29.JPG');
insert into BibliotecaEntity (id, name, ubicacion, imagen) values(6, 'Biblioteca EPM', 'Medellín', 'https://albeiror24.files.wordpress.com/2007/12/biblioteca.jpg');
insert into BibliotecaEntity (id, name, ubicacion, imagen) values(7, 'Parque Biblioteca Doce de Octubre', 'Medellín', 'http://www.aquiestamos.net/w3r/wp-content/uploads/2014/09/100_1059-1024x768.jpg');
insert into BibliotecaEntity (id, name, ubicacion, imagen) values(8, 'Biblioteca Piloto del Caribe', 'Barranquilla', 'http://3.bp.blogspot.com/-ie6YMObeXVU/Unb3gkkE9hI/AAAAAAAAAI0/W94kYaCmCRs/s1600/Barranquilla_Edificio_Administraci%C3%B3n_Aduana.jpg');
insert into BibliotecaEntity (id, name, ubicacion, imagen) values(9, 'Centro Cultural Biblioteca Pública Julio Mario Santo Domingo', 'Bogotá', 'https://media-cdn.tripadvisor.com/media/photo-s/09/36/49/be/teatro-mayor-julio-mario.jpg');
insert into BibliotecaEntity (id, name, ubicacion, imagen) values(10, 'Corporación Biblioteca Rafael Carrillo Luquez', 'Valledupar', 'http://1.bp.blogspot.com/-JgQ3NXdkU2s/VWTaCL98hII/AAAAAAAAAH4/Xdayj05r65k/s1600/DSC_7408.JPG');
insert into BibliotecaEntity (id, name, ubicacion, imagen) values(11, 'Biblioteca Pública Centro Bicentenario Antonia Santos', 'Socorro', 'http://recursos.bibliotecanacional.gov.co/nuevo2/recursos_user/imagenes//red_bibliotecas/IMG_1419.JPG');


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


