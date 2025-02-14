create database citasmedicasvss;
use citasmedicasvss;


Create table locales(
	id int auto_increment not null,
    nom_local varchar(30) not null,
    correo varchar(80) null,
    direccion varchar(250) null,
    celular varchar(15) null,
    estado boolean default 1,
    user_crea varchar(15) not null,
    fec_crea timestamp not null default now(),
    user_upd varchar(15) null,
    fec_upd timestamp null,
    primary key(id)
);

insert into locales(nom_local,correo, direccion, celular, user_crea) values
('Los Conquistadores','conquistadores@eyesillusion.com','Av. Los Conquistadores 589 - San Isidro','987654321','VSSBASS'),
('Cavenecia','cavencia@eyesillusion.com','Av. Emilio Cavenecia 215 - San Isidro','987654321','VSSBASS'),
('Miguel Dasso','dasso@eyesillusion.com','C. Miguel Dasso 130 - San Isidro','987654321','VSSBASS'),
('C.C. San Miguel','sanmiguel@eyesillusion.com','CC Plaza San Miguel, Av. de la Marina 2000 - San Miguel','987654321','VSSBASS'),
('C.C. Jockey Plaza','jockewyplaza@eyesillusion.com','Jockey Plaza, A1-56, Av. Javier Prado Este 4200 - Santiago de Surco','987654321','VSSBASS');

SELECT * FROM locales;

Create table motivo(
	id int auto_increment not null,
    descripcion varchar(30) not null,
    estado boolean default 1,
    user_crea varchar(15) not null,
    fec_crea timestamp not null default now(),
    user_upd varchar(15) null,
    fec_upd timestamp null,
    primary key(id)
);

insert into motivo(descripcion,user_crea) values('Examen visual','VSSBASS'),('Cambio de lunas','VSSBASS');
insert into motivo(descripcion,user_crea) values('Incomodidad Visual','VSSBASS'),('Entrega de producto','VSSBASS');
insert into motivo(descripcion,user_crea) values('Lentes de contacto','VSSBASS'),('Taller técnico','VSSBASS');

select * from motivo;
/*drop table horario;*/
create table horario(
	id int auto_increment not null,
    hora time not null,
    fecha date not null,
    id_local int null,
    estado boolean default 1,
    id_cita int null,
    c_cita varchar(6) null,
    user_crea varchar(15) not null,
    fec_crea timestamp not null default now(),
    user_upd varchar(15) null,
    fec_upd timestamp null,
    primary key(id,hora,fecha)
);

INSERT INTO horario(hora, fecha,user_crea) values
('09:00:00','2025-02-13','VSSBASS'),('09:30:00','2025-02-13','VSSBASS'),
('10:00:00','2025-02-13','VSSBASS'),('10:30:00','2025-02-13','VSSBASS'),
('11:00:00','2025-02-13','VSSBASS'),('11:30:00','2025-02-13','VSSBASS'),
('12:00:00','2025-02-13','VSSBASS'),('12:30:00','2025-02-13','VSSBASS');

INSERT INTO horario(hora, fecha,user_crea) values
('09:00:00','2025-02-14','VSSBASS'),('09:30:00','2025-02-14','VSSBASS'),
('10:00:00','2025-02-14','VSSBASS'),('10:30:00','2025-02-14','VSSBASS'),
('11:00:00','2025-02-14','VSSBASS'),('11:30:00','2025-02-14','VSSBASS'),
('12:00:00','2025-02-14','VSSBASS'),('12:30:00','2025-02-14','VSSBASS'),
('13:00:00','2025-02-14','VSSBASS'),('13:30:00','2025-02-14','VSSBASS'),
('14:00:00','2025-02-14','VSSBASS'),('14:30:00','2025-02-14','VSSBASS'),
('15:00:00','2025-02-14','VSSBASS'),('15:30:00','2025-02-14','VSSBASS'),
('16:00:00','2025-02-14','VSSBASS'),('16:30:00','2025-02-14','VSSBASS'),
('17:00:00','2025-02-14','VSSBASS'),('17:30:00','2025-02-14','VSSBASS'),
('18:00:00','2025-02-14','VSSBASS'),('18:30:00','2025-02-14','VSSBASS');

INSERT INTO horario(hora, fecha,user_crea) values
('13:00:00','2025-02-13','VSSBASS'),('13:30:00','2025-02-13','VSSBASS'),
('14:00:00','2025-02-13','VSSBASS'),('14:30:00','2025-02-13','VSSBASS'),
('15:00:00','2025-02-13','VSSBASS'),('15:30:00','2025-02-13','VSSBASS'),
('16:00:00','2025-02-13','VSSBASS'),('16:30:00','2025-02-13','VSSBASS'),
('17:00:00','2025-02-13','VSSBASS'),('17:30:00','2025-02-13','VSSBASS'),
('18:00:00','2025-02-13','VSSBASS'),('18:30:00','2025-02-13','VSSBASS');
SELECT * FROM horario;
/*drop table citas;*/
CREATE TABLE citas (
    id INT AUTO_INCREMENT NOT NULL,
    c_cita VARCHAR(6) NOT NULL,
    dniruc_cliente VARCHAR(30) NOT NULL,
    nombres_cliente VARCHAR(150) NOT NULL,
    email_cliente VARCHAR(150) NOT NULL,
    celular_cliente VARCHAR(15) NULL,
    estado BOOLEAN DEFAULT 1,
    id_local INT NOT NULL,
    id_motivo INT NOT NULL,
    observaciones VARCHAR(2000) NULL,
    venta_efectiva BOOLEAN DEFAULT 0,
    mail_send BOOLEAN DEFAULT 0,
    hora_cita TIME null,
    fecha_cita DATE null,
    user_crea VARCHAR(15) NULL DEFAULT 'CLIENTE',
    fec_crea TIMESTAMP NOT NULL DEFAULT NOW(),
    user_upd VARCHAR(15) NULL,
    fec_upd TIMESTAMP NULL,
    PRIMARY KEY (id,c_cita)
    /*UNIQUE (id_motivo, hora_cita, fecha_cita),
    CONSTRAINT fk_locales_idlocal FOREIGN KEY (id_local) REFERENCES locales(id),
    CONSTRAINT fk_motivo_idmotivo FOREIGN KEY (id_motivo) REFERENCES motivo(id),
    CONSTRAINT fk_horario_horafecha FOREIGN KEY (hora_cita, fecha_cita) REFERENCES horario(hora, fecha)*/
);
ALTER TABLE citas
ADD CONSTRAINT fk_locales_idlocal FOREIGN KEY (id_local) REFERENCES locales(id);

ALTER TABLE citas
ADD CONSTRAINT fk_motivo_idmotivo FOREIGN KEY (id_motivo) REFERENCES motivo(id);

ALTER TABLE horario ADD UNIQUE (hora, fecha);
ALTER TABLE citas
ADD CONSTRAINT fk_horario_horafecha FOREIGN KEY (hora_cita, fecha_cita) REFERENCES horario(hora, fecha);

/*truncate table citas;*/
insert into citas (celular_cliente,c_cita,user_crea,dniruc_cliente,email_cliente,estado,fecha_cita,hora_cita,id_local,id_motivo,nombres_cliente,observaciones) values (?,?,?,?,?,?,?,?,?,?,?,?)
insert into citas(c_cita, dniruc_cliente, nombres_cliente, email_cliente, celular_cliente, id_local, id_motivo, observaciones, hora_cita,fecha_cita) values
('C00001','46209506','VICTOR SANTOS','victorsantossantiago@gmail.com','945356861', 1, 1, null,'09:00','2024-11-23');
UPDATE horario SET id_cita = 1, c_cita = 'C00001', id_local=1  WHERE hora='09:00:00' AND fecha='2024-11-23' AND id_cita IS NULL AND c_cita IS NULL AND id_local IS NULL;

insert into citas(c_cita, dniruc_cliente, nombres_cliente, email_cliente, celular_cliente, id_local, id_motivo, observaciones, hora_cita,fecha_cita) values
('C00002','46209506','VICTOR SANTOS','victorsantossantiago@gmail.com','945356861', 1, 1, null,'09:30','2024-11-23');
UPDATE horario SET id_cita = 2, c_cita = 'C00002', id_local=1  WHERE hora='09:30:00' AND fecha='2024-11-23' AND id_cita IS NULL AND c_cita IS NULL AND id_local IS NULL;

DROP TABLE citas

truncate TABLE citas;
select * from citas WHERE hora_cita='09:00:00' AND fecha_cita='2024-11-23';
SELECT * FROM HORARIO where fecha = '2025-02-13' and estado is true;



 select he1_0.id,he1_0.user_crea,he1_0.estado,he1_0.fecha,he1_0.hora from horario he1_0 where he1_0.hora='09:00' and he1_0.fecha='2025-02-13' and he1_0.estado is true

select ce1_0.id,ce1_0.celular_cliente,ce1_0.c_cita,ce1_0.user_crea,ce1_0.dniruc_cliente,ce1_0.email_cliente,ce1_0.estado,ce1_0.id_local,ce1_0.nombres_cliente from citas ce1_0


/**************security springboot *******************************/
create table seg_user (
  user_id int not null,
  name varchar(255) default null unique,
  password varchar(255) default null,
  user_name varchar(255) default null unique,
  primary key (user_id)
);

create table seg_authority (
  authority_id int not null,
  name varchar(255) default null unique,
  primary key (authority_id)
);

create table seg_user_authority (
  user_id int not null,
  authority_id int not null,
  primary key (user_id,authority_id),
  constraint fk_user_authority_authority foreign key (authority_id) references seg_authority (authority_id),
  constraint fk_user_authority_user foreign key (user_id) references seg_user (user_id)
) ;


insert into seg_user(user_id ,user_name,password,name) values(1,'admin','$2a$10$YB6e/k5CuT2JXusu0wA89OvMRpBbvNYIfa34Nuy0akpr5F17VxdEG','Administrador'); /*123*/
insert into seg_user(user_id ,user_name,password,name) values(2,'demo','$2a$10$RM3onerOgRMeUD/evvcp3.gbFZ10ameu0O0GaHfCUWQ/X2dOkTBRS','Demo');/*123456*/


select * from seg_user;

insert into seg_authority(authority_id,name) values(1,'ROLE_ADMIN');  
insert into seg_authority(authority_id,name) values(2,'ROLE_USER');  
insert into seg_authority(authority_id,name) values(3,'ROLE_REPORTES');  

select * from seg_authority;


insert into seg_user_authority(user_id,authority_id) values(1,1);  
insert into seg_user_authority(user_id,authority_id) values(1,2); 
 
insert into seg_user_authority(user_id,authority_id) values(2,2); 
insert into seg_user_authority(user_id,authority_id) values(2,3); 

select * from seg_user_authority;



create table menu_modulos(
	id int auto_increment not null,
    menu_descripcion varchar(100) not null,
    estado  BOOLEAN DEFAULT 1,
    user_crea VARCHAR(15) NULL DEFAULT 'ADMIN',
    fec_crea TIMESTAMP NOT NULL DEFAULT NOW(),
    user_upd VARCHAR(15) NULL,
    fec_upd TIMESTAMP NULL,
    primary key (id)
);

drop table menu_modulos;
drop table menu_sub_modulos;
insert into menu_modulos(menu_descripcion) values ('Citas'),('Locales'); #,('Reportes');
insert into menu_modulos(menu_descripcion) values ('Horarios');
select * from menu_modulos;
/*drop table menu_sub_modulos*/
create table menu_sub_modulos(
	id int auto_increment not null,
    id_padre int not null,
    menu_sub_descripcion varchar(100) not null,
    estado  BOOLEAN DEFAULT 1,
    user_crea VARCHAR(15) NULL DEFAULT 'ADMIN',
    fec_crea TIMESTAMP NOT NULL DEFAULT NOW(),
    user_upd VARCHAR(15) NULL,
    fec_upd TIMESTAMP NULL,
    primary key (id,  id_padre)
);
ALTER TABLE menu_sub_modulos
ADD CONSTRAINT fk_menu_modulos_id_padre FOREIGN KEY (id_padre) REFERENCES menu_modulos(id);

#insert into menu_sub_modulos(id_padre, menu_sub_descripcion) values(1,'Registrar Clientes'),(1,'Actualizar Clientes'),(1,'Buscar Clientes');
insert into menu_sub_modulos(id_padre, menu_sub_descripcion) values(1,'Registrar Citas'),(1,'Actualizar Citas'),(1,'Buscar Citas');
#insert into menu_sub_modulos(id_padre, menu_sub_descripcion) values(3,'Registrar Horarios'),(3,'Carga Masiva'),(3,'Buscar Horarios');
insert into menu_sub_modulos(id_padre, menu_sub_descripcion) values(2,'Registrar Locales'),(2,'Actualizar Locales'),(2,'Buscar Locales');
#insert into menu_sub_modulos(id_padre, menu_sub_descripcion) values(3,'Reportes Clientes'),(3,'Reportes Citas'),(3,'Reportes Horarios'),(3,'Reportes Locales');
insert into menu_sub_modulos(id_padre, menu_sub_descripcion) values(3,'Buscar Horarios');
select * from menu_sub_modulos;

/*drop table seg_user_authority_menu*/
create table seg_user_authority_menu (
  user_id int not null,
  authority_id int not null,
  id_menu int null,
  id_sub_menu int null,
  user_crea VARCHAR(15) NULL DEFAULT 'ADMIN',
  fec_crea TIMESTAMP NOT NULL DEFAULT NOW(),
  user_upd VARCHAR(15) NULL,
  fec_upd TIMESTAMP NULL,
  primary key (user_id,authority_id, id_menu, id_sub_menu),
  constraint fk_user_authority_authority_authority_id foreign key (authority_id) references seg_authority (authority_id),
  constraint fk_user_authority_user_user_id foreign key (user_id) references seg_user (user_id)
);

insert into seg_user_authority_menu(user_id,authority_id,id_menu,id_sub_menu) values(1,1,1,1);
insert into seg_user_authority_menu(user_id,authority_id,id_menu,id_sub_menu) values(1,1,1,2);
insert into seg_user_authority_menu(user_id,authority_id,id_menu,id_sub_menu) values(1,1,1,3);
insert into seg_user_authority_menu(user_id,authority_id,id_menu,id_sub_menu) values(1,1,2,4);
insert into seg_user_authority_menu(user_id,authority_id,id_menu,id_sub_menu) values(1,1,2,5);
insert into seg_user_authority_menu(user_id,authority_id,id_menu,id_sub_menu) values(1,1,2,6);
insert into seg_user_authority_menu(user_id,authority_id,id_menu,id_sub_menu) values(1,1,3,7);
insert into seg_user_authority_menu(user_id,authority_id,id_menu,id_sub_menu) values(1,1,3,8);
insert into seg_user_authority_menu(user_id,authority_id,id_menu,id_sub_menu) values(1,1,3,9);
insert into seg_user_authority_menu(user_id,authority_id,id_menu,id_sub_menu) values(1,1,4,10);
insert into seg_user_authority_menu(user_id,authority_id,id_menu,id_sub_menu) values(1,1,4,11);
insert into seg_user_authority_menu(user_id,authority_id,id_menu,id_sub_menu) values(1,1,4,12);
insert into seg_user_authority_menu(user_id,authority_id,id_menu,id_sub_menu) values(1,1,5,13);
insert into seg_user_authority_menu(user_id,authority_id,id_menu,id_sub_menu) values(1,1,5,14);
insert into seg_user_authority_menu(user_id,authority_id,id_menu,id_sub_menu) values(1,1,5,15);
insert into seg_user_authority_menu(user_id,authority_id,id_menu,id_sub_menu) values(1,1,5,16);


insert into seg_user_authority_menu(user_id,authority_id,id_menu,id_sub_menu) values(2,3,5,13);
insert into seg_user_authority_menu(user_id,authority_id,id_menu,id_sub_menu) values(2,3,5,14);
insert into seg_user_authority_menu(user_id,authority_id,id_menu,id_sub_menu) values(2,3,5,15);
insert into seg_user_authority_menu(user_id,authority_id,id_menu,id_sub_menu) values(2,3,5,16);
/*delete from seg_user_authority_menu where user_id=2 and authority_id = 2*/

select * from seg_user_authority_menu;

CREATE OR REPLACE VIEW vw_menu_user AS
SELECT 
    T1.USER_ID AS USER_ID,
    T2.NAME AS USER_NAME,
    T1.AUTHORITY_ID,
    T4.NAME AS AUTHORITY_NAME,
    T1.ID_MENU,
    T6.MENU_DESCRIPCION AS MENU_NAME,
    T1.ID_SUB_MENU,
    T5.MENU_SUB_DESCRIPCION AS SUB_MENU_NAME,
    ##REPLACE(LOWER(T5.MENU_SUB_DESCRIPCION),' ','-') AS URL_MENU
    CASE
		WHEN LOWER(T5.MENU_SUB_DESCRIPCION) LIKE '%actualizar%' 
        THEN CONCAT(LOWER(T6.MENU_DESCRIPCION), '/', REPLACE(LOWER(T5.MENU_SUB_DESCRIPCION), ' ', '-'), '/')
        ELSE CONCAT(LOWER(T6.MENU_DESCRIPCION), '/', REPLACE(LOWER(T5.MENU_SUB_DESCRIPCION), ' ', '-'))
		END AS URL_MENU
FROM 
    seg_user_authority_menu T1
INNER JOIN 
    seg_user T2 ON T1.USER_ID = T2.USER_ID
INNER JOIN 
    seg_user_authority T3 ON T1.USER_ID = T3.USER_ID AND T1.AUTHORITY_ID = T3.AUTHORITY_ID
INNER JOIN 
    seg_authority T4 ON T3.AUTHORITY_ID = T4.AUTHORITY_ID
INNER JOIN 
    menu_modulos T6 ON T1.ID_MENU = T6.ID
INNER JOIN 
    menu_sub_modulos T5 ON T1.ID_MENU = T5.ID_PADRE AND T1.ID_SUB_MENU = T5.ID;
WHERE T1.USER_ID = 1 AND T1.AUTHORITY_ID = 1 ; 

SELECT * FROM VW_MENU_USER WHERE USER_ID = 1 AND AUTHORITY_ID = 1 ;  


CALL GetUserMenuDetails (1,1);
DELIMITER $$
CREATE OR REPLACE PROCEDURE GetUserMenuDetails (
    IN p_user_id INT,
    IN p_authority_id INT
)
BEGIN
    -- Primera consulta: Obtención de los menús principales
    SELECT DISTINCT
		T2.USER_ID AS USER_ID,
        T2.AUTHORITY_ID AS TAUTHORITY_ID,
        T1.ID AS ID_MENU,
        T1.MENU_DESCRIPCION AS MENU_NAME
    FROM 
        menu_modulos T1
    INNER JOIN 
        seg_user_authority_menu T2 ON T1.ID = T2.ID_MENU
    WHERE  
        T2.USER_ID = p_user_id 
        AND T2.AUTHORITY_ID = p_authority_id;

    -- Segunda consulta: Obtención de los detalles de menú y submenú
    SELECT 
        T1.USER_ID AS USER_ID,
        T2.NAME AS USER_NAME,
        T1.AUTHORITY_ID,
        T4.NAME AS AUTHORITY_NAME,
        T1.ID_MENU,
        T6.MENU_DESCRIPCION AS MENU_NAME,
        T1.ID_SUB_MENU,
        T5.MENU_SUB_DESCRIPCION AS SUB_MENU_NAME
    FROM 
        seg_user_authority_menu T1
    INNER JOIN 
        seg_user T2 ON T1.USER_ID = T2.USER_ID
    INNER JOIN 
        seg_user_authority T3 ON T1.USER_ID = T3.USER_ID AND T1.AUTHORITY_ID = T3.AUTHORITY_ID
    INNER JOIN 
        seg_authority T4 ON T3.AUTHORITY_ID = T4.AUTHORITY_ID
    INNER JOIN 
        menu_modulos T6 ON T1.ID_MENU = T6.ID
    INNER JOIN 
        menu_sub_modulos T5 ON T1.ID_MENU = T5.ID_PADRE AND T1.ID_SUB_MENU = T5.ID
    WHERE 
        T1.USER_ID = p_user_id 
        AND T1.AUTHORITY_ID = p_authority_id;
END$$

DELIMITER ;

    SELECT DISTINCT
		
		T2.USER_ID AS USER_ID,
        T2.AUTHORITY_ID AS AUTHORITY_ID,
        T1.ID AS ID_MENU,
        T1.MENU_DESCRIPCION AS MENU_NAME
    FROM 
        menu_modulos T1
    INNER JOIN 
        seg_user_authority_menu T2 ON T1.ID = T2.ID_MENU
    WHERE  
        T2.USER_ID = 1 
        AND T2.AUTHORITY_ID = 1;

SELECT * FROM VW_MENU_USER WHERE USER_ID = 1 AND AUTHORITY_ID = 1 ;  

SELECT DISTINCT
		T2.USER_ID AS USER_ID,
        T2.AUTHORITY_ID AS AUTHORITY_ID,
        T1.ID AS ID_MENU,
        T1.MENU_DESCRIPCION AS MENU_NAME
    FROM 
        menu_modulos T1
    INNER JOIN 
        seg_user_authority_menu T2 ON T1.ID = T2.ID_MENU
    WHERE  
        T2.USER_ID = 1
        AND T2.AUTHORITY_ID = 1
        ;
        
        
SELECT * FROM horario;

update horario set id_local = null, estado = 1, id_cita = null , c_cita = null where id in (1,2,3,4,5,6,7);

select * from citas;
truncate table citas;