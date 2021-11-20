CREATE TABLE IF NOT EXISTS public.item (
    id serial PRIMARY KEY NOT NULL,
    "name" varchar(1000) NOT NULL,
    category_id int4 NOT NULL,
    needed bool NOT NULL default false,
    bought bool NOT NULL default false,
    important bool NOT NULL default false,
    "comment" varchar(1000) NOT NULL default '',
    CONSTRAINT item_category_id_fk_category_id
        FOREIGN KEY (category_id)
            REFERENCES public.category(id)
);
