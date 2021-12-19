create table if not exists car
(
    car_number varchar(10) not null,
    mark varchar(32),
    transported_volume_m3 double precision default 9 not null,
    transported_weight_kg double precision default 50 not null,
    constraint car_pkey
        primary key (car_number)
);

create table if not exists client
(
    id bigserial not null,
    firstname varchar(20) not null,
    patronymic varchar(20) not null,
    phone varchar(16) not null,
    surname varchar(20) not null,
    constraint client_pkey
        primary key (id),
    constraint uk_client_phone
        unique (phone)
);

create table if not exists client_account
(
    email varchar(255) not null,
    password bytea not null,
    client_id bigint,
    constraint client_account_pkey
        primary key (email),
    constraint account_client
        foreign key (client_id) references client
);

create table if not exists position
(
    id bigserial not null,
    name varchar(255) not null,
    price double precision default 4250.0 not null,
    constraint position_pkey
        primary key (id),
    constraint uk_position_name
        unique (name)
);

create table if not exists driver
(
    id bigserial not null,
    firstname varchar(20) not null,
    patronymic varchar(20) not null,
    phone varchar(16) not null,
    surname varchar(20) not null,
    apartment varchar(4),
    building varchar(4) not null,
    campus varchar(4),
    city varchar(24) not null,
    region varchar(24) not null,
    street varchar(24),
    email varchar(255) not null,
    identification_code varchar(10) not null,
    price_card_number varchar(16) not null,
    position_id varchar(255),
    car_number varchar(10) not null,
    constraint driver_pkey
        primary key (id),
    constraint uk_driver_car_number
        unique (car_number),
    constraint uk_driver_email
        unique (email),
    constraint uk_driver_identification_code
        unique (identification_code),
    constraint uk_driver_price_card_number
        unique (price_card_number),
    constraint driver_car
        foreign key (car_number) references car,
    constraint fk_iwv8j0h2n0osr64qe9vojbst5
        foreign key (position_id) references position (name)
);

create table if not exists station
(
    id bigserial not null,
    apartment varchar(4),
    building varchar(4) not null,
    campus varchar(4),
    city varchar(24) not null,
    region varchar(24) not null,
    street varchar(24),
    number smallint not null,
    constraint station_pkey
        primary key (id),
    constraint region_city_street_building
        unique (region, city, street, building)
);

create table if not exists operator
(
    id bigserial not null,
    firstname varchar(20) not null,
    patronymic varchar(20) not null,
    phone varchar(16) not null,
    surname varchar(20) not null,
    apartment varchar(4),
    building varchar(4) not null,
    campus varchar(4),
    city varchar(24) not null,
    region varchar(24) not null,
    street varchar(24),
    email varchar(255) not null,
    identification_code varchar(10) not null,
    price_card_number varchar(16) not null,
    position_id varchar(255),
    login varchar(255) not null,
    station_id bigint not null,
    constraint operator_pkey
        primary key (id),
    constraint uk_7yaaquemhw0quk7s8qs5iwl3b
        unique (login),
    constraint operators_station
        foreign key (station_id) references station,
    constraint fk_7mtx8b76y5842jywm6uckwfdy
        foreign key (position_id) references position (name)
);

create table if not exists invoice
(
    id bigserial not null,
    datetime timestamp not null,
    delivery_type integer not null,
    quantity double precision default 1 not null,
    type varchar(16) not null,
    recipient_id bigint not null,
    sender_id bigint not null,
    operator_id bigint not null,
    station_recipient_id bigint not null,
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

create table if not exists invoice_timeline
(
    id bigserial not null,
    datetime timestamp,
    status varchar(24) not null,
    actual_station_id bigint,
    invoice_id bigint,
    constraint invoice_timeline_pkey
        primary key (id),
    constraint timeline_station
        foreign key (actual_station_id) references station,
    constraint timeline_invoice
        foreign key (invoice_id) references invoice
);

create table if not exists package
(
    id bigserial not null,
    insurance_uah double precision default 200 not null,
    volume_m3 double precision not null,
    weight_kg double precision not null,
    invoice_id bigint,
    constraint package_pkey
        primary key (id),
    constraint package_invoice
        foreign key (invoice_id) references invoice
);

create table if not exists waybill
(
    id bigserial not null,
    datetime timestamp not null,
    quantity integer not null,
    type varchar(12) not null,
    driver_id bigint not null,
    operator_id bigint not null,
    station_recipient_id bigint,
    station_sender_id bigint,
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

create table if not exists work_shift
(
    id bigserial not null,
    datetime timestamp not null,
    type varchar(10) not null,
    operator_id bigint not null,
    station_id bigint not null,
    constraint work_shift_pkey
        primary key (id),
    constraint work_shifts_operator
        foreign key (operator_id) references operator,
    constraint work_shifts_station
        foreign key (station_id) references station
);

create table if not exists client_stations
(
    client_id bigint not null,
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
    waybill_id bigint not null,
    invoices_id bigint not null,
    constraint uk_waybill_invoices_id
        unique (invoices_id),
    constraint fk_waybill_invoices_invoices_id
        foreign key (invoices_id) references invoice,
    constraint fk_waybill_invoices_waybill_id
        foreign key (waybill_id) references waybill
);

create table if not exists work_shift_invoices
(
    work_shift_id bigint not null,
    invoices_id bigint not null,
    constraint uk_work_shift_invoices_id
        unique (invoices_id),
    constraint fk_work_shift_invoices_work_invoices_id
        foreign key (invoices_id) references invoice,
    constraint fk_work_shift_invoices_work_shift_id
        foreign key (work_shift_id) references work_shift
);



-- security ---------------------------

create table users
(
    username varchar(15) not null,
    password varchar(100),
    enabled  numeric(1),
    constraint users_pkey
        primary key (username)
);

create table authorities
(
    username  varchar(15),
    authority varchar(25),
    constraint authorities_username_fkey
        foreign key (username) references users
);


-- indexes --------------------------

create index if not exists driver_city
    on driver (city);

create index if not exists driver_phone
    on driver (phone);

create index if not exists invoice_type
    on invoice (type);

create index if not exists invoice_datetime
    on invoice (datetime);

create index if not exists invoice_timeline_status
    on invoice_timeline (status);

create index if not exists invoice_timeline_datetime
    on invoice_timeline (datetime);

create index if not exists operator_city
    on operator (city);

create index if not exists operator_phone
    on operator (phone);

create index if not exists insurance_uah
    on package (insurance_uah);

create index if not exists city
    on station (city);

create index if not exists waybill_type
    on waybill (type);

create index if not exists waybill_datetime
    on waybill (datetime);

create index if not exists waybill_quantity
    on waybill (quantity);



