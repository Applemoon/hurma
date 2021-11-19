CREATE TABLE IF NOT EXISTS public.item (
    id serial PRIMARY KEY NOT NULL,
    "name" varchar(1000) NOT NULL,
    needed bool NOT NULL,
    bought bool NOT NULL,
    category_id int4 NOT NULL,
    important bool NULL,
    "comment" varchar(1000) NOT NULL,
    CONSTRAINT item_category_id_fk_category_id
        FOREIGN KEY (category_id)
            REFERENCES public.category(id)
);
