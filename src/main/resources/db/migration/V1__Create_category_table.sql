CREATE TABLE IF NOT EXISTS public.category (
    id serial PRIMARY KEY NOT NULL,
    "name" varchar(20) NOT NULL UNIQUE,
    full_name varchar(200) NOT NULL,
    "position" int2 NOT NULL
);
