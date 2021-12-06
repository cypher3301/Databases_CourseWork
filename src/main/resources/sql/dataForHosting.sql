create table if not exists users
(
    username varchar(15) not null,
    password varchar(100),
    enabled  numeric(1),
    constraint users_pkey
        primary key (username)
);

create table if not exists authorities
(
    username  varchar(15),
    authority varchar(25),
    constraint authorities_username_fkey
        foreign key (username) references users
);

create table if not exists car
(
    car_number            varchar(10)                 not null,
    mark                  varchar(255),
    transported_volume_m3 double precision default 9  not null,
    transported_weight_kg double precision default 50 not null,
    constraint car_pkey
        primary key (car_number)
);

create table if not exists client
(
    id         bigserial,
    firstname  varchar(24) not null,
    patronymic varchar(24) not null,
    phone      varchar(24) not null,
    surname    varchar(24) not null,
    constraint client_pkey
        primary key (id),
    constraint uk_client_phone
        unique (phone)
);

create table if not exists client_account
(
    email     varchar(255) not null,
    password  bytea        not null,
    client_id bigint,
    constraint client_account_pkey
        primary key (email),
    constraint account_client
        foreign key (client_id) references client
);

create table if not exists position
(
    id    bigserial,
    name  varchar(255)                    not null,
    price double precision default 4250.0 not null,
    constraint position_pkey
        primary key (id),
    constraint uk_position_name
        unique (name)
);

create table if not exists employee
(
    id                  bigserial,
    firstname           varchar(24)  not null,
    patronymic          varchar(24)  not null,
    phone               varchar(24)  not null,
    surname             varchar(24)  not null,
    apartment           varchar(4),
    building            varchar(4)   not null,
    campus              varchar(4),
    city                varchar(24)  not null,
    region              varchar(24)  not null,
    street              varchar(24),
    email               varchar(255) not null,
    identification_code varchar(12)  not null,
    price_card_number   varchar(20)  not null,
    position_id         bigint,
    constraint employee_pkey
        primary key (id),
    constraint uk_employee_email
        unique (email),
    constraint uk_employee_identification_code
        unique (identification_code),
    constraint uk_employee_price_card_number
        unique (price_card_number),
    constraint fk_employee_position_id
        foreign key (position_id) references position
);

create index if not exists employee_city
    on employee (city);

create index if not exists employee_phone
    on employee (phone);

create table if not exists driver
(
    id                  bigserial,
    car_number  varchar(10) not null,
    employee_id bigint      not null,
    constraint driver_pkey
        primary key (id),
    constraint uk_driver_car_number
        unique (car_number),
    constraint driver_car
        foreign key (car_number) references car,
    constraint driver_employee
        foreign key (employee_id) references employee
);

create table if not exists station
(
    id        bigserial,
    apartment varchar(4),
    building  varchar(4)  not null,
    campus    varchar(4),
    city      varchar(24) not null,
    region    varchar(24) not null,
    street    varchar(24),
    number    smallint    not null,
    constraint station_pkey
        primary key (id),
    constraint region_city_street_building
        unique (region, city, street, building)
);

create index if not exists city
    on station (city);

create table if not exists operator
(
    id                  bigserial,
    login               varchar(255) not null,
    station_id          bigint       not null,
    employee_id bigint      not null,
    constraint operator_pkey
        primary key (id),
    constraint uk_operator_login
        unique (login),
    constraint operator_employee
        foreign key (employee_id) references employee,
    constraint fk_operators_station
        foreign key (station_id) references station,
    constraint fk_operator_authentication
        foreign key (login) references users
);

create table if not exists invoice
(
    id                   bigserial,
    datetime             timestamp                  not null,
    delivery_type        varchar(24)                not null,
    quantity             double precision default 1 not null,
    type                 varchar(20)                not null,
    recipient_id         bigint                     not null,
    sender_id            bigint                     not null,
    operator_id          bigint                     not null,
    station_recipient_id bigint                     not null,
    constraint invoice_pkey
        primary key (id),
    constraint invoices_client_recipient
        foreign key (recipient_id) references client,
    constraint invoices_client_sender
        foreign key (sender_id) references client,
    constraint invoices_operator
        foreign key (operator_id) references operator,
    constraint invoice_station
        foreign key (station_recipient_id) references station
);

create index if not exists invoice_type
    on invoice (type);

create index if not exists invoice_datetime
    on invoice (datetime);

create table if not exists invoice_timeline
(
    id                bigserial,
    datetime          timestamp,
    status            varchar(24) not null,
    actual_station_id bigint,
    invoice_id        bigint,
    constraint invoice_timeline_pkey
        primary key (id),
    constraint timeline_station
        foreign key (actual_station_id) references station,
    constraint timeline_invoice
        foreign key (invoice_id) references invoice
);

create index if not exists invoice_timeline_status
    on invoice_timeline (status);

create index if not exists invoice_timeline_datetime
    on invoice_timeline (datetime);

create table if not exists package
(
    id            bigserial,
    insurance_uah double precision default 200 not null,
    volume_m3     double precision             not null,
    weight_kg     double precision             not null,
    invoice_id    bigint,
    constraint package_pkey
        primary key (id),
    constraint package_invoice
        foreign key (invoice_id) references invoice
);

create index if not exists insurance_uah
    on package (insurance_uah);

create table if not exists waybill
(
    id                   bigserial,
    datetime             timestamp   not null,
    quantity             integer     not null,
    type                 varchar(20) not null,
    driver_id            bigint      not null,
    operator_id          bigint      not null,
    station_recipient_id bigint,
    station_sender_id    bigint,
    constraint waybill_pkey
        primary key (id),
    constraint waybills_driver
        foreign key (driver_id) references driver,
    constraint waybills_operator
        foreign key (operator_id) references operator,
    constraint waybills_station_recipient
        foreign key (station_recipient_id) references station,
    constraint waybills_station_sender
        foreign key (station_sender_id) references station
);

create index if not exists waybill_type
    on waybill (type);

create index if not exists waybill_datetime
    on waybill (datetime);

create index if not exists waybill_quantity
    on waybill (quantity);

create table if not exists work_shift
(
    id          bigserial,
    datetime    timestamp   not null,
    type        varchar(20) not null,
    operator_id bigint      not null,
    station_id  bigint      not null,
    constraint work_shift_pkey
        primary key (id),
    constraint work_shifts_operator
        foreign key (operator_id) references operator,
    constraint work_shifts_station
        foreign key (station_id) references station
);

create table if not exists client_stations
(
    client_id   bigint not null,
    stations_id bigint not null,
    constraint uk_client_stations_id
        unique (stations_id),
    constraint fk_client_stations_station_id
        foreign key (stations_id) references station,
    constraint fk_client_stations_client_id
        foreign key (client_id) references client
);

create table if not exists waybill_invoices
(
    waybill_id  bigint not null,
    invoices_id bigint not null,
    constraint fk_waybill_invoices_invoices_id
        foreign key (invoices_id) references invoice,
    constraint fk_waybill_invoices_waybill_id
        foreign key (waybill_id) references waybill
);

create table if not exists work_shift_invoices
(
    work_shift_id bigint not null,
    invoices_id   bigint not null,
    constraint uk_work_shift_invoices_id
        unique (invoices_id),
    constraint fk_work_shift_invoices_work_invoices_id
        foreign key (invoices_id) references invoice,
    constraint fk_work_shift_invoices_work_shift_id
        foreign key (work_shift_id) references work_shift
);


--

insert into station(id, region, city, street, building, number)
VALUES (1, 'Днепропетровская', 'Днепр', 'Улица Дерибасовская', '124', 4),
       (2, 'Одесская', 'Донецк', 'Улица Крещатик', '12', 2),
       (3, 'Черниговская', 'Запорожье', 'Андреевский спуск', '24', 1),
       (4, 'Харьковская', 'Львов', 'Улица Сумская', '14', 14),
       (5, 'Житомирская', 'Севастополь', 'Улица Главная', '65', 34);

insert into position(id, name, price)
VALUES (1, 'operator', 5400.12),
       (2, 'driver', 6125.86);

insert into car(car_number, mark, transported_volume_m3, transported_weight_kg)
VALUES ('AM7362AS', 'Mercedes', 8, 300),
       ('DM8212SS', 'Volvo', 4, 170),
       ('RR8672AA', 'Daf', 12, 560),
       ('JA8765LM', 'Renault', 24, 1200),
       ('AT2716OS', 'Mitsubishi', 2, 120);

insert into employee(id,firstname, patronymic, surname, region, city, street, building, campus, apartment, phone, email,
                     identification_code, price_card_number, position_id)
VALUES (1,'Антон', 'Дмитриевич', 'Кузнецов', 'Днепропетровская', 'Днепр', 'Улица Дерибасовская', '124', '/3', '45',
        '+380974718361', 'kljerh@gmail.com', '1928374651', '1926375916271645', 2),
       (2,'Дмитрий', 'Владиславович', 'Смирнов', 'Одесская', 'Донецк', 'Улица Крещатик', '12', 'А', '5', '+380974718121',
        'afsdfgw@gmail.com', '1028374651', '19263759726181645', 2),
       (3,'Иван', 'Антонович', 'Иванов', 'Черниговская', 'Запорожье', 'Андреевский спуск', '24', '/2', '4',
        '+380974715631', 'qereb@gmail.com', '1412434651', '1926375992718164', 2),
       (4,'Валентин', 'Иванович', 'Соколов', 'Харьковская', 'Львов', 'Улица Сумская', '14', 'Б', '2', '+380974718826',
        'argqregqe@gmail.com', '1928377251', '19263759182771645', 2),
       (5,'Богдан', 'Валентинович', 'Попов', 'Житомирская', 'Севастополь', 'Улица Главная', '65', '/2', '15',
        '+380972518361', 'asgqwreg@gmail.com', '1826374651', '1926284716271645', 2);


insert into driver(id,car_number, employee_id)
VALUES (1 ,'AM7362AS', 1),
       (2 ,'DM8212SS', 2),
       (3 ,'RR8672AA', 3),
       (4 ,'JA8765LM', 4),
       (5 ,'AT2716OS', 5);

insert into users(username, password, enabled)
VALUES ('AM7362AS', '12345678', 0),
       ('DM8212SS', '12345678', 0),
       ('RR8672AA', '12345678', 0),
       ('JA8765LM', '12345678', 0),
       ('AT2716OS', '12345678', 0);

insert into authorities(username, authority)
VALUES ('AM7362AS', 'operator'),
       ('DM8212SS', 'operator'),
       ('RR8672AA', 'operator'),
       ('JA8765LM', 'operator'),
       ('AT2716OS', 'operator');

insert into operator(id,login, station_id, employee_id)
VALUES (1 ,'AM7362AS',1 , 1),
       (2 ,'DM8212SS',2 , 2),
       (3 ,'RR8672AA',3 , 3),
       (4 ,'JA8765LM',4 , 4),
       (5 ,'AT2716OS',5 , 5);

insert into work_shift(id, datetime, type, operator_id, station_id)
VALUES (1, '08-08-2021', 'STARTED', 1, 1),
       (2, '03-05-2020', 'STARTED', 2, 2),
       (3, '03-11-2020', 'STARTED', 3, 3),
       (4, '06-17-2021', 'STARTED', 4, 4),
       (5, '06-21-2021', 'STARTED', 5, 5);

insert into client(id, firstname, patronymic, surname, phone)
VALUES (1, 'Антон', 'Дмитриевич', 'Кузнецов', '+380974718361'),
       (2, 'Дмитрий', 'Владиславович', 'Смирнов', '+380974718121'),
       (3, 'Иван', 'Антонович', 'Иванов', '+380974715631'),
       (4, 'Валентин', 'Иванович', 'Соколов', '+380974718826'),
       (5, 'Богдан', 'Валентинович', 'Попов', '+380972518361');

insert into invoice(id, datetime, delivery_type, quantity, type, sender_id, recipient_id, operator_id,
                    station_recipient_id)
VALUES (1, '08-08-2021', 'DELIVERY', 1, 'ELECTRONICS', 1, 2, 1, 2),
       (2, '03-05-2020', 'DELIVERY', 1, 'PRODUCTS', 2, 3, 2, 3),
       (3, '03-11-2020', 'DELIVERY', 3, 'CHEMISTRY', 3, 4, 3, 4),
       (4, '06-17-2021', 'DELIVERY', 1, 'FURNITURE', 4, 5, 4, 5),
       (5, '06-21-2021', 'DELIVERY', 2, 'CLOTHES', 5, 1, 5, 1);

insert into package(id, insurance_uah, volume_m3, weight_kg, invoice_id)
VALUES (1, 1200, 0.2, 0.4, 1),
       (2, 200, 0.5, 4.72, 2),
       (3, 6900, 5, 7.4, 3),
       (4, 6900, 5, 7.4, 3),
       (5, 6900, 5, 7.4, 3),
       (6, 2324, 1.43, 3.5, 4),
       (7, 1139, 1.12, 0.9, 5),
       (8, 1488, 0.72, 0.67, 5);

insert into invoice_timeline(id, datetime, status, actual_station_id, invoice_id)
VALUES (1, '08-08-2021', 'AWAITING_DISPATCH', 1, 1),
       (2, '03-05-2020', 'AWAITING_DISPATCH', 2, 2),
       (3, '03-11-2020', 'AWAITING_DISPATCH', 3, 3),
       (4, '06-17-2021', 'AWAITING_DISPATCH', 4, 4),
       (5, '06-21-2021', 'AWAITING_DISPATCH', 5, 5);

insert into waybill(id, datetime, quantity, type, driver_id, operator_id, station_recipient_id, station_sender_id)
VALUES (1, '08-08-2021', 0, 'LEAVING', 1, 1, 2, 1),
       (2, '03-05-2020', 0, 'LEAVING', 2, 2, 3, 2),
       (3, '03-11-2020', 0, 'LEAVING', 3, 3, 4, 3),
       (4, '06-17-2021', 0, 'LEAVING', 4, 4, 5, 4),
       (5, '06-21-2021', 0, 'LEAVING', 5, 5, 1, 5);

insert into waybill(id, datetime, quantity, type, driver_id, operator_id, station_recipient_id, station_sender_id)
VALUES (6, '08-08-2021', 0, 'COMING', 1, 1, 1, 2),
       (7, '03-05-2020', 0, 'COMING', 2, 2, 2, 3),
       (8, '03-11-2020', 0, 'COMING', 3, 3, 3, 4),
       (9, '06-17-2021', 0, 'COMING', 4, 4, 4, 5),
       (10, '06-21-2021', 0, 'COMING', 5, 5, 5, 1);

insert into waybill(id, datetime, quantity, type, driver_id, operator_id, station_recipient_id, station_sender_id)
VALUES (11, '08-08-2021', 1, 'LEAVING', 1, 1, 2, 1),
       (12, '03-05-2020', 1, 'LEAVING', 2, 2, 3, 2),
       (13, '03-11-2020', 1, 'LEAVING', 3, 3, 4, 3),
       (14, '06-17-2021', 1, 'LEAVING', 4, 4, 5, 4),
       (15, '06-21-2021', 1, 'LEAVING', 5, 5, 1, 5);

insert into waybill_invoices(waybill_id, invoices_id)
VALUES (11, 1),
       (12, 2),
       (13, 3),
       (14, 4),
       (15, 5);

insert into invoice_timeline(id, datetime, status, actual_station_id, invoice_id)
VALUES (6, '08-08-2021', 'IN_TRANSIT', 1, 1),
       (7, '03-05-2020', 'IN_TRANSIT', 2, 2),
       (8, '03-11-2020', 'IN_TRANSIT', 3, 3),
       (9, '06-17-2021', 'IN_TRANSIT', 4, 4),
       (10, '06-21-2021', 'IN_TRANSIT', 5, 5);

insert into waybill(id, datetime, quantity, type, driver_id, operator_id, station_recipient_id, station_sender_id)
VALUES (16, '08-08-2021', 1, 'COMING', 1, 1, 2, 1),
       (17, '03-05-2020', 1, 'COMING', 2, 2, 3, 2),
       (18, '03-11-2020', 1, 'COMING', 3, 3, 4, 3),
       (19, '06-17-2021', 1, 'COMING', 4, 4, 5, 4),
       (20, '06-21-2021', 1, 'COMING', 5, 5, 1, 5);

insert into waybill_invoices(waybill_id, invoices_id)
VALUES (16, 1),
       (17, 2),
       (18, 3),
       (19, 4),
       (20, 5);

insert into invoice_timeline(id, datetime, status, actual_station_id, invoice_id)
VALUES (11, '08-08-2021', 'ARRIVED', 2, 1),
       (12, '03-05-2020', 'ARRIVED', 3, 2),
       (13, '03-11-2020', 'ARRIVED', 4, 3),
       (14, '06-17-2021', 'ARRIVED', 5, 4),
       (15, '06-21-2021', 'ARRIVED', 1, 5);

insert into work_shift(id, datetime, type, operator_id, station_id)
VALUES (6, '08-08-2021', 'COMPLETED', 1, 1),
       (7, '03-05-2020', 'COMPLETED', 2, 2),
       (8, '03-11-2020', 'COMPLETED', 3, 3),
       (9, '06-17-2021', 'COMPLETED', 4, 4),
       (10, '06-21-2021', 'COMPLETED', 5, 5);

insert into work_shift(id, datetime, type, operator_id, station_id)
VALUES (11, '08-09-2021', 'STARTED', 1, 1),
       (12, '03-06-2020', 'STARTED', 2, 2),
       (13, '03-12-2020', 'STARTED', 3, 3),
       (14, '06-18-2021', 'STARTED', 4, 4),
       (15, '06-22-2021', 'STARTED', 5, 5);

insert into invoice_timeline(id, datetime, status, actual_station_id, invoice_id)
VALUES (16, '08-09-2021', 'ARRIVED', 2, 1),
       (17, '03-06-2020', 'ARRIVED', 3, 2),
       (18, '03-12-2020', 'ARRIVED', 4, 3),
       (19, '06-18-2021', 'ARRIVED', 5, 4),
       (20, '06-22-2021', 'ARRIVED', 1, 5);

insert into invoice_timeline(id, datetime, status, actual_station_id, invoice_id)
VALUES (21, '08-09-2021', 'RECEIVED', 2, 1),
       (22, '03-06-2020', 'RECEIVED', 3, 2),
       (23, '03-12-2020', 'RECEIVED', 4, 3),
       (24, '06-18-2021', 'RECEIVED', 5, 4),
       (25, '06-22-2021', 'RECEIVED', 1, 5);