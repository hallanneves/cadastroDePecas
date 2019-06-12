-- Table: public.pecas

-- DROP TABLE public.pecas;

CREATE TABLE public.pecas
(
    nome character varying(200) COLLATE pg_catalog."default" NOT NULL,
    veiculo character varying(200) COLLATE pg_catalog."default",
    pesoliquido double precision NOT NULL,
    pesobruto double precision NOT NULL,
    id integer NOT NULL DEFAULT nextval('pecas_id_seq'::regclass)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.pecas
    OWNER to postgres;