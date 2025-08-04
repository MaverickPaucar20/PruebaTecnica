--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3 (Debian 16.3-1.pgdg120+1)
-- Dumped by pg_dump version 16.3

-- Started on 2025-08-04 01:09:55

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3422 (class 1262 OID 65611)
-- Name: administrativo; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE administrativo WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE administrativo OWNER TO postgres;

\connect administrativo

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 7 (class 2615 OID 65612)
-- Name: core; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA core;


ALTER SCHEMA core OWNER TO postgres;

--
-- TOC entry 2 (class 3079 OID 65613)
-- Name: pgcrypto; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA core;


--
-- TOC entry 3423 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION pgcrypto; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 65656)
-- Name: cliente; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE core.cliente (
    cliente_id uuid DEFAULT gen_random_uuid() NOT NULL,
    persona_id uuid NOT NULL,
    contrasena character varying(100) NOT NULL,
    estado boolean NOT NULL
);


ALTER TABLE core.cliente OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 65671)
-- Name: cuenta; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE core.cuenta (
    cuenta_id uuid DEFAULT gen_random_uuid() NOT NULL,
    cliente_id uuid NOT NULL,
    numero_cuenta character varying(30) NOT NULL,
    tipo_cuenta character varying(30) NOT NULL,
    saldo_inicial numeric(15,2) NOT NULL,
    estado boolean NOT NULL
);


ALTER TABLE core.cuenta OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 65684)
-- Name: movimiento; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE core.movimiento (
    movimiento_id uuid DEFAULT gen_random_uuid() NOT NULL,
    cuenta_id uuid NOT NULL,
    fecha timestamp without time zone DEFAULT now() NOT NULL,
    tipo_movimiento character varying(30) NOT NULL,
    valor numeric(15,2) NOT NULL,
    saldo numeric(15,2) NOT NULL
);


ALTER TABLE core.movimiento OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 65650)
-- Name: persona; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE core.persona (
    persona_id uuid DEFAULT gen_random_uuid() NOT NULL,
    nombre character varying(100) NOT NULL,
    genero character varying(1) NOT NULL,
    edad integer NOT NULL,
    identificacion character varying(50) NOT NULL,
    direccion character varying(255) NOT NULL,
    telefono character varying(30) NOT NULL
);


ALTER TABLE core.persona OWNER TO postgres;

--
-- TOC entry 3414 (class 0 OID 65656)
-- Dependencies: 218
-- Data for Name: cliente; Type: TABLE DATA; Schema: core; Owner: postgres
--

INSERT INTO core.cliente VALUES ('e5a1742d-5d48-4f79-990a-1b6c0f8b9c27', 'd7be7d19-9a22-41fc-b495-7b47c128c0e7', '1234', true) ON CONFLICT DO NOTHING;
INSERT INTO core.cliente VALUES ('d43a0fcb-7c42-4a89-bc59-9a8e6f0baf8c', '37b65e3a-7c4c-40f8-826c-81f7a8e4a123', '5678', true) ON CONFLICT DO NOTHING;
INSERT INTO core.cliente VALUES ('4e8097ba-55ce-4085-82a5-4312ea2d332b', '50383b8a-2aee-4a65-bd5a-df86858e87a9', '1245', true) ON CONFLICT DO NOTHING;


--
-- TOC entry 3415 (class 0 OID 65671)
-- Dependencies: 219
-- Data for Name: cuenta; Type: TABLE DATA; Schema: core; Owner: postgres
--

INSERT INTO core.cuenta VALUES ('78fa61b5-0d3e-42fd-a60f-d728b6ef85c7', 'e5a1742d-5d48-4f79-990a-1b6c0f8b9c27', '585545', 'Corriente', 1000.00, true) ON CONFLICT DO NOTHING;
INSERT INTO core.cuenta VALUES ('bb1a9d84-f995-446f-9e89-88b5e7cbb5a1', 'e5a1742d-5d48-4f79-990a-1b6c0f8b9c27', '478758', 'Ahorro', 1425.00, true) ON CONFLICT DO NOTHING;
INSERT INTO core.cuenta VALUES ('e0f3d4d9-1437-4cd5-8879-7e94cabc5683', 'd43a0fcb-7c42-4a89-bc59-9a8e6f0baf8c', '225487', 'Corriente', 700.00, true) ON CONFLICT DO NOTHING;
INSERT INTO core.cuenta VALUES ('c13c3a11-fb90-4cfc-8bca-7d1b222ed875', '4e8097ba-55ce-4085-82a5-4312ea2d332b', '495878', 'Ahorros', 150.00, true) ON CONFLICT DO NOTHING;
INSERT INTO core.cuenta VALUES ('23f96b8b-fcc0-48ae-beb6-23c0acb41f8b', 'd43a0fcb-7c42-4a89-bc59-9a8e6f0baf8c', '496825', 'Ahorros', 0.00, true) ON CONFLICT DO NOTHING;


--
-- TOC entry 3416 (class 0 OID 65684)
-- Dependencies: 220
-- Data for Name: movimiento; Type: TABLE DATA; Schema: core; Owner: postgres
--

INSERT INTO core.movimiento VALUES ('9e037a35-1b6f-4ca5-82e1-89b83d51faf7', 'bb1a9d84-f995-446f-9e89-88b5e7cbb5a1', '2022-02-08 09:00:00', 'RETIRO', -575.00, 1425.00) ON CONFLICT DO NOTHING;
INSERT INTO core.movimiento VALUES ('cf4802cf-5c60-417c-aa9a-dc07e8aefd62', 'e0f3d4d9-1437-4cd5-8879-7e94cabc5683', '2022-02-10 14:30:00', 'DEPOSITO', 600.00, 700.00) ON CONFLICT DO NOTHING;
INSERT INTO core.movimiento VALUES ('b43b7dd7-8da8-4191-8faf-b6168d94e547', 'c13c3a11-fb90-4cfc-8bca-7d1b222ed875', '2022-02-11 10:45:00', 'DEPOSITO', 150.00, 150.00) ON CONFLICT DO NOTHING;
INSERT INTO core.movimiento VALUES ('83487033-8738-434b-ad67-9e3d253309c6', '23f96b8b-fcc0-48ae-beb6-23c0acb41f8b', '2022-02-08 17:30:00', 'RETIRO', -540.00, 0.00) ON CONFLICT DO NOTHING;


--
-- TOC entry 3413 (class 0 OID 65650)
-- Dependencies: 217
-- Data for Name: persona; Type: TABLE DATA; Schema: core; Owner: postgres
--

INSERT INTO core.persona VALUES ('d7be7d19-9a22-41fc-b495-7b47c128c0e7', 'Jose Lema', 'M', 30, '1234567890', 'Otavalo sn y principal', '098254785') ON CONFLICT DO NOTHING;
INSERT INTO core.persona VALUES ('37b65e3a-7c4c-40f8-826c-81f7a8e4a123', 'Marianela Montalvo', 'F', 28, '2345678901', 'Amazonas y NNUU', '097548965') ON CONFLICT DO NOTHING;
INSERT INTO core.persona VALUES ('50383b8a-2aee-4a65-bd5a-df86858e87a9', 'Juan Osorio', 'M', 35, '3456789012', '13 junio y Equinoccial', '098874587') ON CONFLICT DO NOTHING;


--
-- TOC entry 3260 (class 2606 OID 65661)
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY core.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (cliente_id);


--
-- TOC entry 3262 (class 2606 OID 65678)
-- Name: cuenta cuenta_numero_cuenta_key; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY core.cuenta
    ADD CONSTRAINT cuenta_numero_cuenta_key UNIQUE (numero_cuenta);


--
-- TOC entry 3264 (class 2606 OID 65676)
-- Name: cuenta cuenta_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY core.cuenta
    ADD CONSTRAINT cuenta_pkey PRIMARY KEY (cuenta_id);


--
-- TOC entry 3266 (class 2606 OID 65690)
-- Name: movimiento movimiento_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY core.movimiento
    ADD CONSTRAINT movimiento_pkey PRIMARY KEY (movimiento_id);


--
-- TOC entry 3258 (class 2606 OID 65655)
-- Name: persona persona_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY core.persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (persona_id);


--
-- TOC entry 3267 (class 2606 OID 65662)
-- Name: cliente fk_cliente_persona; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY core.cliente
    ADD CONSTRAINT fk_cliente_persona FOREIGN KEY (persona_id) REFERENCES core.persona(persona_id) ON DELETE CASCADE;


--
-- TOC entry 3268 (class 2606 OID 65679)
-- Name: cuenta fk_cuenta_cliente; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY core.cuenta
    ADD CONSTRAINT fk_cuenta_cliente FOREIGN KEY (cliente_id) REFERENCES core.cliente(cliente_id) ON DELETE CASCADE;


--
-- TOC entry 3269 (class 2606 OID 65691)
-- Name: movimiento fk_movimiento_cuenta; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY core.movimiento
    ADD CONSTRAINT fk_movimiento_cuenta FOREIGN KEY (cuenta_id) REFERENCES core.cuenta(cuenta_id) ON DELETE CASCADE;


-- Completed on 2025-08-04 01:09:57

--
-- PostgreSQL database dump complete
--

