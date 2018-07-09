--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: seq-atividade; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."seq-atividade"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."seq-atividade" OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: atividade; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.atividade (
    id_at integer DEFAULT nextval('public."seq-atividade"'::regclass) NOT NULL,
    nomeat character varying NOT NULL,
    email character varying NOT NULL,
    dataat date NOT NULL,
    tempo character varying NOT NULL,
    duracao character varying NOT NULL,
    distancia double precision NOT NULL,
    kcal integer NOT NULL,
    passos bigint NOT NULL,
    velocidade_media double precision,
    velocidade_max double precision,
    ritmo_medio character varying,
    ritmo_max character varying,
    menor_elev bigint,
    maior_elev bigint,
    ritmos character varying
);


ALTER TABLE public.atividade OWNER TO postgres;

--
-- Name: seq-cliente; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."seq-cliente"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."seq-cliente" OWNER TO postgres;

--
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.cliente (
    id integer DEFAULT nextval('public."seq-cliente"'::regclass) NOT NULL,
    nome character varying NOT NULL,
    sexo character varying NOT NULL,
    altura double precision NOT NULL,
    peso double precision NOT NULL,
    dtnasc character varying NOT NULL,
    email character varying NOT NULL
);


ALTER TABLE public.cliente OWNER TO postgres;


--
-- Name: seq-atividade; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."seq-atividade"', 477, true);


--
-- Name: seq-cliente; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."seq-cliente"', 191, true);


--
-- Name: cliente_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_email_key UNIQUE (email);


--
-- Name: pk_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT pk_id PRIMARY KEY (id);


--
-- Name: pk_id_at; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.atividade
    ADD CONSTRAINT pk_id_at PRIMARY KEY (id_at);


--
-- Name: unique_atividade; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.atividade
    ADD CONSTRAINT unique_atividade UNIQUE (email, dataat, tempo);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

