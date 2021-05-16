drop table client, package,operator,invoice,courier;

create table client
(
    client_id          bigint unique not null
        constraint pk_client primary key,
    firstname          varchar(12)   not null,
    middlename         varchar(12)   not null,
    lastname           varchar(12)   not null,
    phone_number       varchar(12)   not null,

    region             varchar(24)   not null,
    city               varchar(24)   not null,
    street             varchar(24),
    post_office_number varchar(3)    not null

);
create table operator
(
    operator_id         bigint unique not null
        constraint pk_operator primary key,

    firstname           varchar(12)   not null,
    middlename          varchar(12)   not null,
    lastname            varchar(12)   not null,
    phone_number        varchar(12)   not null,

    region              varchar(24)   not null,
    city                varchar(24)   not null,
    street              varchar(24)   not null,
    house_number        varchar(8)    not null,
    apartment_number    varchar(8)    not null,
    zipcode             varchar(5),

    identification_code bigint unique not null,
    price_card_number   bigint,
    price               float8,

    login               varchar(8) unique,
    password            varchar

);

create table courier
(
    courier_id          bigint unique not null
        constraint pk_courier primary key,

    firstname           varchar(12)   not null,
    middlename          varchar(12)   not null,
    lastname            varchar(12)   not null,
    phone_number        varchar(12)   not null,

    region              varchar(24)   not null,
    city                varchar(24)   not null,
    street              varchar(24)   not null,
    house_number        varchar(8)    not null,
    apartment_number    varchar(8)    not null,
    zipcode             varchar(5),

    identification_code bigint unique not null,
    price_card_number   bigint,
    price               float8,

    car_number          varchar(8)    not null

);

create table invoice
(
    invoice_id              bigint unique not null
        constraint pk_invoice primary key
        constraint "FK_CourierInvoiceId"
            references courier
        constraint "FK_OperatorInvoiceId"
            references operator,

    operator_id             bigint        not null,
    courier_id              bigint        not null,


    from_region             varchar(24)   not null,
    from_city               varchar(24)   not null,
    from_street             varchar(24),
    from_post_office_number varchar(3)    not null,
    from_datetime           timestamp     not null,

    to_region               varchar(24)   not null,
    to_city                 varchar(24)   not null,
    to_street               varchar(24),
    to_post_office_number   varchar(3)    not null,
    to_datetime             timestamp
);

create table package
(
    package_id              bigint unique  not null
        constraint pk_package primary key
        constraint "FK_ClientPackageId"
            references client
        constraint "FK_InvoicePackageId"
            references invoice
        constraint "FK_OperatorPackageId"
            references operator,
    client_id               bigint         not null,
    operator_id             bigint         not null,
    invoice_id              bigint         not null,

    weight                  float4         not null,
    volume                  float4         not null,
    insurance               numeric(10, 2) not null default 200,
    quantity                int            not null default 1,

    from_region             varchar(24)    not null,
    from_city               varchar(24)    not null,
    from_street             varchar(24),
    from_post_office_number varchar(3)     not null,
    from_datetime           timestamp      NOT NULL,

    to_region               varchar(24)    not null,
    to_city                 varchar(24)    not null,
    to_street               varchar(24),
    to_post_office_number   varchar(3)     not null,
    to_datetime             timestamp
);
--
-- alter table package
--     add constraint FK_ClientPackageId
--         foreign key (package_id) references client (client_id) on DELETE no action on UPDATE no action;
--
-- alter table package
--     add constraint FK_OperatorPackageId
--         foreign key (package_id) references operator (operator_id) on DELETE no action on UPDATE no action;
--
--
-- alter table package
--     add constraint FK_InvoicePackageId
--         foreign key (package_id) references invoice (invoice_id) on delete no action on update no action;
--
--
-- alter table invoice
--     add constraint FK_OperatorInvoiceId
--         foreign key (invoice_id) references operator (operator_id) on delete no action on update no action;
--
-- alter table invoice
--     add constraint FK_CourierInvoiceId
--         foreign key (invoice_id) references courier (courier_id) on delete no action on update no action;
--


