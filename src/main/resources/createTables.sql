create table client
(
    client_id          bigint       not null
        constraint client_pkey
            primary key,
    firstname          varchar(255) not null,
    lastname           varchar(255) not null,
    middlename         varchar(255) not null,
    phone              varchar(255),
    post_office_number smallint     not null,
    city               varchar(24)  not null,
    region             varchar(24)  not null,
    street             varchar(24)
);

create table courier
(
    courier_id          bigserial    not null
        constraint courier_pkey
            primary key,
    firstname           varchar(255) not null,
    lastname            varchar(255) not null,
    middlename          varchar(255) not null,
    phone               varchar(255),
    identification_code bigint       not null
        constraint uk_bxbl0cqg1dt2f0dyydoeioit6
            unique,
    apartment_number    varchar(8)   not null,
    house_number        varchar(8)   not null,
    zipcode             varchar(5)   not null,
    city                varchar(24)  not null,
    region              varchar(24)  not null,
    street              varchar(24),
    price               double precision,
    price_card_number   bigint,
    car_number          varchar(255) not null
);

create table station
(
    station_id         bigint      not null
        constraint station_pkey
            primary key,
    post_office_number smallint    not null,
    city               varchar(24) not null,
    region             varchar(24) not null,
    street             varchar(24)
);

create table operator
(
    operator_id         bigint       not null
        constraint operator_pkey
            primary key,
    firstname           varchar(255) not null,
    lastname            varchar(255) not null,
    middlename          varchar(255) not null,
    phone               varchar(255),
    identification_code bigint       not null
        constraint uk_74ggh480k7vfbpljps3hjm0iy
            unique,
    apartment_number    varchar(8)   not null,
    house_number        varchar(8)   not null,
    zipcode             varchar(5)   not null,
    city                varchar(24)  not null,
    region              varchar(24)  not null,
    street              varchar(24),
    price               double precision,
    price_card_number   bigint,
    login               varchar(8),
    password            bytea,
    station_station_id  bigint
        constraint fk8eg5q7sd0n253kt3jp1goamhb
            references station
);

create table invoice
(
    invoice_id                 bigint    not null
        constraint invoice_pkey
            primary key,
    to_datetime                timestamp,
    from_datetime              timestamp not null,
    courier_courier_id         bigint    not null
        constraint fkme0iw17jc596el30r9htw7bry
            references courier,
    endstation_station_id      bigint
        constraint fkiolycli318g3mca6u4mq0hrcc
            references station,
    operator_operator_id       bigint
        constraint fkgwejqc1jew5cj56x6ctlnyk56
            references operator,
    startingstation_station_id bigint
        constraint fkn1rrlp9ny0am2djr2teip4erx
            references station
);

create table package
(
    package_id         bigint           not null
        constraint package_pkey
            primary key,
    post_office_number smallint         not null,
    city               varchar(24)      not null,
    region             varchar(24)      not null,
    street             varchar(24),
    to_datetime        timestamp,
    from_datetime      timestamp        not null,
    insurance          double precision not null,
    quantity           double precision not null,
    status             varchar(255)     not null,
    volume             double precision not null,
    weight             double precision not null,
    operator_id        bigint           not null
        constraint person_id_fk
            references operator
);

create table work_shift
(
    work_shift_id        bigint    not null
        constraint work_shift_pkey
            primary key,
    status               varchar(10),
    start_time           timestamp not null,
    operator_operator_id bigint
        constraint fkikkp5jawgtoxsirk0urqpneqb
            references operator
);

create table invoice_package
(
    invoice_invoice_id  bigint not null
        constraint fkrc77i2dueylqif6nschw31ygl
            references invoice,
    packages_package_id bigint not null
        constraint uk_gm4v2x9rvfr3f3obkqi3mpc3m
            unique
        constraint fk1ws98o7mai4p21qvpbkji2fri
            references package
);

create table sender_recipient_package
(
    id                  bigint not null
        constraint sender_recipient_package_pkey
            primary key,
    package_id          bigint not null
        constraint fklamx0u80apagiela9xkvjq48s
            references package,
    client_recipient_id bigint not null
        constraint fkk2on0f2m1852d1ryu0bx0jhir
            references client,
    client_sender_id    bigint not null
        constraint fki1ox8uuwc4j9u374o9e6jdbpr
            references client
);

create table work_shift_packages
(
    work_shift_work_shift_id bigint not null
        constraint fk145vvascshjd08q8781l7j0pw
            references work_shift,
    packages_package_id      bigint not null
        constraint uk_ja9f91igdu2w0odtst38akorm
            unique
        constraint fkiojxye3pmf1lqsuutwhxnxwqh
            references package
);




-- -- Old schema
-- create table client
-- (
--     client_id          bigint unique not null
--         constraint pk_client
--             primary key,
--     firstname          varchar(12)   not null,
--     middlename         varchar(12)   not null,
--     lastname           varchar(12)   not null,
--     phone_number       varchar(12)   not null,
--     region             varchar(24)   not null,
--     city               varchar(24)   not null,
--     street             varchar(24),
--     post_office_number varchar(3)    not null
-- );
--
-- create table courier
-- (
--     courier_id          bigint unique not null
--         constraint pk_courier primary key,
--
--     firstname           varchar(12)   not null,
--     middlename          varchar(12)   not null,
--     lastname            varchar(12)   not null,
--     phone_number        varchar(12)   not null,
--
--     region              varchar(24)   not null,
--     city                varchar(24)   not null,
--     street              varchar(24)   not null,
--     house_number        varchar(8)    not null,
--     apartment_number    varchar(8)    not null,
--     zipcode             varchar(5),
--
--     identification_code bigint unique not null,
--     price_card_number   bigint,
--     price               float8,
--
--     car_number          varchar(8)    not null
--
-- );
--
-- create table operator
-- (
--     operator_id         bigint unique not null
--         constraint pk_operator primary key,
--
--     firstname           varchar(12)   not null,
--     middlename          varchar(12)   not null,
--     lastname            varchar(12)   not null,
--     phone_number        varchar(12)   not null,
--
--     region              varchar(24)   not null,
--     city                varchar(24)   not null,
--     street              varchar(24)   not null,
--     house_number        varchar(8)    not null,
--     apartment_number    varchar(8)    not null,
--     zipcode             varchar(5),
--
--     identification_code bigint unique not null,
--     price_card_number   bigint,
--     price               float8,
--
--     login               varchar(8) unique,
--     password            varchar
--
-- );
--
--
--
-- create table invoice
-- (
--     invoice_id              bigint unique not null
--         constraint pk_invoice primary key
--         constraint "FK_CourierInvoiceId"
--             references courier
--         constraint "FK_OperatorInvoiceId"
--             references operator,
--
--     operator_id             bigint        not null,
--     courier_id              bigint        not null,
--
--
--     from_region             varchar(24)   not null,
--     from_city               varchar(24)   not null,
--     from_street             varchar(24),
--     from_post_office_number varchar(3)    not null,
--     from_datetime           timestamp     not null,
--
--     to_region               varchar(24)   not null,
--     to_city                 varchar(24)   not null,
--     to_street               varchar(24),
--     to_post_office_number   varchar(3)    not null,
--     to_datetime             timestamp
-- );
--
-- create table package
-- (
--     package_id              bigint unique  not null
--         constraint pk_package primary key
--         constraint "FK_ClientPackageId"
--             references client
--         constraint "FK_InvoicePackageId"
--             references invoice
--         constraint "FK_OperatorPackageId"
--             references operator,
--     client_id               bigint         not null,
--     operator_id             bigint         not null,
--     invoice_id              bigint         not null,
--
--     weight                  float4         not null,
--     volume                  float4         not null,
--     insurance               numeric(10, 2) not null default 200,
--     quantity                int            not null default 1,
--
--     from_region             varchar(24)    not null,
--     from_city               varchar(24)    not null,
--     from_street             varchar(24),
--     from_post_office_number varchar(3)     not null,
--     from_datetime           timestamp      NOT NULL,
--
--     to_region               varchar(24)    not null,
--     to_city                 varchar(24)    not null,
--     to_street               varchar(24),
--     to_post_office_number   varchar(3)     not null,
--     to_datetime             timestamp
-- );
-- --
-- -- alter table package
-- --     add constraint FK_ClientPackageId
-- --         foreign key (package_id) references client (client_id) on DELETE no action on UPDATE no action;
-- --
-- -- alter table package
-- --     add constraint FK_OperatorPackageId
-- --         foreign key (package_id) references operator (operator_id) on DELETE no action on UPDATE no action;
-- --
-- --
-- -- alter table package
-- --     add constraint FK_InvoicePackageId
-- --         foreign key (package_id) references invoice (invoice_id) on delete no action on update no action;
-- --
-- --
-- -- alter table invoice
-- --     add constraint FK_OperatorInvoiceId
-- --         foreign key (invoice_id) references operator (operator_id) on delete no action on update no action;
-- --
-- -- alter table invoice
-- --     add constraint FK_CourierInvoiceId
-- --         foreign key (invoice_id) references courier (courier_id) on delete no action on update no action;
-- --
--
--
