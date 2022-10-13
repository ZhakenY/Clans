# Clans
Сreating a game repository.



Must to create table: 


create table if not exists clan
(
    id               serial
        constraint clan_pk
            primary key,
    name             varchar(16),
    gold_sum         double precision,
    types_of_earning varchar(32),
    cause_of_loss    varchar(32),
    insert_date      timestamp default now()
);

alter table clan
    owner to postgres;

create unique index if not exists clan_id_uindex
    on clan (id);

