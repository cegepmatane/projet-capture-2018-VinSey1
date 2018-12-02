--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.14
-- Dumped by pg_dump version 9.5.14

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: capture; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE capture WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'fr_FR.UTF-8' LC_CTYPE = 'fr_FR.UTF-8';


ALTER DATABASE capture OWNER TO postgres;

\connect capture

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: capteur; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.capteur (
    id integer NOT NULL,
    date timestamp with time zone NOT NULL,
    latitude real NOT NULL,
    longitude real NOT NULL,
    valeur real NOT NULL
);


ALTER TABLE public.capteur OWNER TO postgres;

--
-- Name: capteur_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.capteur_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.capteur_id_seq OWNER TO postgres;

--
-- Name: capteur_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.capteur_id_seq OWNED BY public.capteur.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.capteur ALTER COLUMN id SET DEFAULT nextval('public.capteur_id_seq'::regclass);


--
-- Data for Name: capteur; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.capteur (id, date, latitude, longitude, valeur) FROM stdin;
3	2018-10-23 19:07:58-04	34.0979996	54.0900002	2
10	2018-10-31 20:07:15-04	56	-40	152
18	2018-10-31 20:07:15-04	-34.2130013	52.4249992	42
19	2018-10-31 20:07:15-04	-34.2130013	52.4249992	42
20	2018-10-31 20:07:15-04	-34.2130013	52.4249992	42
21	2018-10-31 20:07:15-04	-34.2130013	52.4249992	42
22	2018-10-31 20:07:15-04	-34.2130013	52.4249992	42
30	2018-11-29 07:00:00-05	37	40	3
31	2018-11-29 07:00:00-05	40	40	3
32	2018-11-29 08:00:00-05	40	40	3
34	2018-11-29 11:56:10-05	48.8406906	-67.4974518	129
35	2018-11-29 11:57:10-05	48.8406906	-67.4974518	128
36	2018-11-29 11:58:10-05	48.8406906	-67.4974518	128
37	2018-11-29 11:59:10-05	48.8406906	-67.4974518	128
38	2018-11-29 12:00:10-05	48.8406906	-67.4974518	128
39	2018-11-29 12:01:11-05	48.8406906	-67.4974518	127
40	2018-11-29 12:02:11-05	48.8406906	-67.4974518	127
41	2018-11-29 12:03:11-05	48.8406906	-67.4974518	127
42	2018-11-29 12:04:11-05	48.8406906	-67.4974518	127
43	2018-11-29 12:05:11-05	48.8406906	-67.4974518	127
44	2018-11-29 11:57:10-05	48	-67	1
45	2018-11-30 11:57:10-05	48	-67	1
46	2018-11-29 13:57:10-05	48	-67	0.5
47	2018-10-29 13:57:10-04	48	-67	128
\.


--
-- Name: capteur_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.capteur_id_seq', 47, true);


--
-- Name: capteur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.capteur
    ADD CONSTRAINT capteur_pkey PRIMARY KEY (id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: TABLE capteur; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE public.capteur FROM PUBLIC;
REVOKE ALL ON TABLE public.capteur FROM postgres;
GRANT ALL ON TABLE public.capteur TO postgres;
GRANT SELECT ON TABLE public.capteur TO capturexml;
GRANT INSERT ON TABLE public.capteur TO capturejson;
GRANT INSERT ON TABLE public.capteur TO jsoncapture;
GRANT SELECT ON TABLE public.capteur TO xmlcapture;


--
-- Name: SEQUENCE capteur_id_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE public.capteur_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE public.capteur_id_seq FROM postgres;
GRANT ALL ON SEQUENCE public.capteur_id_seq TO postgres;
GRANT SELECT,USAGE ON SEQUENCE public.capteur_id_seq TO jsoncapture;


--
-- PostgreSQL database dump complete
--

