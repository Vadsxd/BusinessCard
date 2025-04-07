alter table if exists card drop constraint if exists fk_user_id;
alter table if exists block drop constraint if exists fk_card_id;
alter table if exists content drop constraint if exists fk_block_id;

drop table if exists "user" cascade;
drop table if exists card cascade;
drop table if exists block cascade;
drop table if exists content cascade;

create table "user"
(
    user_id bigint generated by default as identity,
    login varchar(255) not null,
    password varchar(255) not null,
    role varchar(255) not null,
    primary key (user_id)
);

create table card
(
    card_id bigint generated by default as identity,
    user_id bigint not null,
    name varchar(255) not null,
    primary key (card_id)
);

create table block
(
    block_id bigint generated by default as identity,
    card_id bigint,
    name varchar(255) not null,
    description varchar(255) not null,
    primary key (block_id)
);

create table content
(
    content_id bigint generated by default as identity,
    block_id bigint,
    name varchar(255) not null,
    description varchar(255) not null,
    primary key (content_id)
);

alter table if exists card add constraint fk_user_id foreign key (user_id) references "user";
alter table if exists content add constraint fk_block_id foreign key (block_id) references block;
alter table if exists block add constraint fk_card_id foreign key (card_id) references card;