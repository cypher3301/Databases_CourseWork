create table if not exists users
(
    username varchar(15) ,
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
    car_number            varchar(10)                 ,
    mark                  varchar(255),
    transported_volume_m3 double precision default 9  ,
    transported_weight_kg double precision default 50 ,
    constraint car_pkey
        primary key (car_number)
);

create table if not exists client
(
    id         bigserial,
    firstname  varchar(24) ,
    patronymic varchar(24) ,
    phone      varchar(24) ,
    surname    varchar(24) ,
    constraint client_pkey
        primary key (id)
--     constraint uk_client_phone
--         unique (phone)
);

create table if not exists client_account
(
    email     varchar(255) ,
    password  bytea        ,
    client_id bigint,
    constraint client_account_pkey
        primary key (email),
    constraint account_client
        foreign key (client_id) references client
);

create table if not exists position
(
    id    bigserial,
    name  varchar(255)                    ,
    price double precision default 4250.0 ,
    constraint position_pkey
        primary key (id),
    constraint uk_position_name
        unique (name)
);

create table if not exists employee
(
    id                  bigserial,
    firstname           varchar(24)  ,
    patronymic          varchar(24)  ,
    phone               varchar(24)  ,
    surname             varchar(24)  ,
    apartment           varchar(4),
    building            varchar(4)   ,
    campus              varchar(4),
    city                varchar(24)  ,
    region              varchar(24)  ,
    street              varchar(24),
    email               varchar(255) ,
    identification_code varchar(12)  ,
    price_card_number   varchar(20)  ,
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
    car_number  varchar(10) ,
    employee_id bigint      ,
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
    building  varchar(4)  ,
    campus    varchar(4),
    city      varchar(24) ,
    region    varchar(24) ,
    street    varchar(24),
    number    smallint    ,
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
    login               varchar(255) ,
    station_id          bigint       ,
    employee_id bigint      ,
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
    datetime             timestamp                  ,
    delivery_type        varchar(24)                ,
    quantity             double precision default 1 ,
    type                 varchar(20)                ,
    recipient_id         bigint                     ,
    sender_id            bigint                     ,
    operator_id          bigint                     ,
    station_recipient_id bigint                     ,
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
    status            varchar(24) ,
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
    insurance_uah double precision default 200 ,
    volume_m3     double precision             ,
    weight_kg     double precision             ,
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
    datetime             timestamp   ,
    quantity             integer     ,
    type                 varchar(20) ,
    driver_id            bigint      ,
    operator_id          bigint      ,
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
    datetime    timestamp   ,
    type        varchar(20) ,
    operator_id bigint      ,
    station_id  bigint      ,
    constraint work_shift_pkey
        primary key (id),
    constraint work_shifts_operator
        foreign key (operator_id) references operator,
    constraint work_shifts_station
        foreign key (station_id) references station
);

create table if not exists client_stations
(
    client_id   bigint ,
    stations_id bigint ,
    constraint uk_client_stations_id
        unique (stations_id),
    constraint fk_client_stations_station_id
        foreign key (stations_id) references station,
    constraint fk_client_stations_client_id
        foreign key (client_id) references client
);

create table if not exists waybill_invoices
(
    waybill_id  bigint ,
    invoices_id bigint ,
    constraint fk_waybill_invoices_invoices_id
        foreign key (invoices_id) references invoice,
    constraint fk_waybill_invoices_waybill_id
        foreign key (waybill_id) references waybill
);

create table if not exists work_shift_invoices
(
    work_shift_id bigint ,
    invoices_id   bigint ,
    constraint uk_work_shift_invoices_id
        unique (invoices_id),
    constraint fk_work_shift_invoices_work_invoices_id
        foreign key (invoices_id) references invoice,
    constraint fk_work_shift_invoices_work_shift_id
        foreign key (work_shift_id) references work_shift
);



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
