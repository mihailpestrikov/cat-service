create table if not exists public.owners
(
    id         uuid not null
    constraint owners_pk
    primary key,
    name       varchar(50),
    birth_date date
    );

alter table public.owners
    owner to pestr;

create table if not exists public.cats
(
    id          uuid not null
    constraint cats_pk
    primary key,
    name        varchar(50),
    birth_date  date,
    color       varchar(50),
    owner_id    uuid
    constraint cats_owners_id_fk
    references public.owners,
    cat_friends integer
    );

alter table public.cats
    owner to pestr;

create table if not exists public.cat_friends
(
    cat_id    uuid
    constraint cat_friends_cats_id_fk
    references public.cats,
    friend_id uuid
    constraint cat_friends_cats_id_fk_2
    references public.cats
);

alter table public.cat_friends
    owner to pestr;

