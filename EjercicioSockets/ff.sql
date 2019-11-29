-- =============================================================================
-- Diagram Name: Noname1
-- Created on: 18/08/2018 23:02:06
-- Diagram Version: 
-- =============================================================================
CREATE SEQUENCE "hibernate_sequence"
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1;

ALTER TABLE "hibernate_sequence" OWNER TO "postgres";




CREATE TABLE "asistencia" (
	"idasistencia" SERIAL NOT NULL,
	"fecharegistro" date NOT NULL,
	"horaentrada" time,
	"horasalida" time,
	"idempleado" int4,
	CONSTRAINT "asistencia_pkey" PRIMARY KEY("idasistencia")
)
WITH (
	OIDS = False
);

ALTER TABLE "asistencia" OWNER TO "postgres";

CREATE TABLE "cambiohorario" (
	"id" SERIAL NOT NULL,
	"fecha" date NOT NULL,
	"horaentrada" time NOT NULL,
	"horasalida" time NOT NULL,
	"justificacion" varchar(500) NOT NULL,
	"idjustificante" SERIAL NOT NULL,
	CONSTRAINT "cambiohorario_pkey" PRIMARY KEY("id")
)
WITH (
	OIDS = False
);

ALTER TABLE "cambiohorario" OWNER TO "postgres";

CREATE TABLE "comisionoficial" (
	"id" SERIAL NOT NULL,
	"fechafin" date NOT NULL,
	"fechainicio" date NOT NULL,
	"invitacionarchivo" varchar(255) NOT NULL,
	"idjustificante" SERIAL NOT NULL,
	CONSTRAINT "comisionoficial_pkey" PRIMARY KEY("id")
)
WITH (
	OIDS = False
);

ALTER TABLE "comisionoficial" OWNER TO "postgres";

CREATE TABLE "constanciatiempo" (
	"id" SERIAL NOT NULL,
	"licenciaarchivo" varchar(255) NOT NULL,
	"segfecha" date,
	"tipo" varchar(255) NOT NULL,
	"idjustificante" SERIAL NOT NULL,
	CONSTRAINT "constanciatiempo_pkey" PRIMARY KEY("id"),
	CONSTRAINT "uk_5dis2h10krxolwqvfgga9i1uu" UNIQUE("segfecha")
)
WITH (
	OIDS = False
);

ALTER TABLE "constanciatiempo" OWNER TO "postgres";

CREATE TABLE "departamento" (
	"iddepartamento" int4 NOT NULL,
	"nombre" varchar(60) NOT NULL,
	"permisodocente" bool NOT NULL,
	"permisodocpaee" bool NOT NULL,
	"permisopaee" bool NOT NULL,
	"idsuperior" int4,
	CONSTRAINT "departamento_pkey" PRIMARY KEY("iddepartamento")
)
WITH (
	OIDS = False
);

ALTER TABLE "departamento" OWNER TO "postgres";

CREATE TABLE "dia" (
	"iddia" int4 NOT NULL,
	"horaentrada" time NOT NULL,
	"horasalida" time NOT NULL,
	"nombre" varchar(3) NOT NULL,
	"idhorario" int4,
	CONSTRAINT "dia_pkey" PRIMARY KEY("iddia")
)
WITH (
	OIDS = False
);

ALTER TABLE "dia" OWNER TO "postgres";

CREATE TABLE "horarioactual" (
	"idhorario" int4 NOT NULL,
	CONSTRAINT "horarioactual_pkey" PRIMARY KEY("idhorario")
)
WITH (
	OIDS = False
);

ALTER TABLE "horarioactual" OWNER TO "postgres";

CREATE TABLE "incidencia" (
	"idincidencia" int4 NOT NULL,
	"fecharegistro" date NOT NULL,
	"horasfaltantes" int4,
	"tipo" varchar(2),
	"idjustificante" int4,
	"idempleado" int4,
	"idquincena" int4,
	CONSTRAINT "incidencia_pkey" PRIMARY KEY("idincidencia")
)
WITH (
	OIDS = False
);

ALTER TABLE "incidencia" OWNER TO "postgres";

CREATE TABLE "jefesuperior" (
	"idsuperior" int4 NOT NULL,
	"apellidomaterno" varchar(30) NOT NULL,
	"apellidopaterno" varchar(30) NOT NULL,
	"nombre" varchar(60) NOT NULL,
	"jefe" int4,
	CONSTRAINT "jefesuperior_pkey" PRIMARY KEY("idsuperior")
)
WITH (
	OIDS = False
);

ALTER TABLE "jefesuperior" OWNER TO "postgres";

CREATE TABLE "justificante" (
	"idjustificante" SERIAL NOT NULL,
	"estado" int4,
	"fecha" date NOT NULL,
	"tipo" int4,
	"idempleado" int4,
	CONSTRAINT "justificante_pkey" PRIMARY KEY("idjustificante")
)
WITH (
	OIDS = False
);

ALTER TABLE "justificante" OWNER TO "postgres";

CREATE TABLE "licpaternidad" (
	"id" SERIAL NOT NULL,
	"actamatrimonio" varchar(255) NOT NULL,
	"actanacimiento" varchar(255) NOT NULL,
	"comprobanteingresos" varchar(255) NOT NULL,
	"constanciacurso" varchar(255) NOT NULL,
	"justificacion" varchar(600) NOT NULL,
	"registrolicencia" varchar(255) NOT NULL,
	"idjustificante" SERIAL NOT NULL,
	CONSTRAINT "licpaternidad_pkey" PRIMARY KEY("id")
)
WITH (
	OIDS = False
);

ALTER TABLE "licpaternidad" OWNER TO "postgres";

CREATE TABLE "login" (
	"correo" varchar(255) NOT NULL,
	"passwordhash" varchar(255) NOT NULL,
	"idempleado" int4,
	CONSTRAINT "login_pkey" PRIMARY KEY("correo")
)
WITH (
	OIDS = False
);

ALTER TABLE "login" OWNER TO "postgres";

CREATE TABLE "motivo" (
	"idmotivo" SERIAL NOT NULL,
	"descripcion" varchar(255) NOT NULL,
	CONSTRAINT "motivo_pkey" PRIMARY KEY("idmotivo")
)
WITH (
	OIDS = False
);

ALTER TABLE "motivo" OWNER TO "postgres";

CREATE TABLE "notificacion" (
	"id" SERIAL NOT NULL,
	"archivo" varchar(255) NOT NULL,
	"fecha" date NOT NULL,
	"idmotivo" SERIAL NOT NULL,
	"idempleado" int4,
	CONSTRAINT "notificacion_pkey" PRIMARY KEY("id")
)
WITH (
	OIDS = False
);

ALTER TABLE "notificacion" OWNER TO "postgres";

CREATE TABLE "omisionentrsal" (
	"id" SERIAL NOT NULL,
	"justificacion" varchar(600) NOT NULL,
	"tipo" bool NOT NULL,
	"idjustificante" SERIAL NOT NULL,
	CONSTRAINT "omisionentrsal_pkey" PRIMARY KEY("id")
)
WITH (
	OIDS = False
);

ALTER TABLE "omisionentrsal" OWNER TO "postgres";

CREATE TABLE "periodoinhabil" (
	"idperiodo" int4 NOT NULL,
	"descripcion" varchar(500) NOT NULL,
	"fin" date NOT NULL,
	"inicio" date NOT NULL,
	"justificacionarchivo" varchar(255),
	"aplicadocente" bool NOT NULL,
	"aplicapaee" bool NOT NULL,
	CONSTRAINT "periodoinhabil_pkey" PRIMARY KEY("idperiodo")
)
WITH (
	OIDS = False
);

ALTER TABLE "periodoinhabil" OWNER TO "postgres";

CREATE TABLE "permisoeconomico" (
	"id" SERIAL NOT NULL,
	"idjustificante" SERIAL NOT NULL,
	CONSTRAINT "permisoeconomico_pkey" PRIMARY KEY("id")
)
WITH (
	OIDS = False
);

ALTER TABLE "permisoeconomico" OWNER TO "postgres";

CREATE TABLE "personal" (
	"idempleado" int4 NOT NULL,
	"activo" bool NOT NULL,
	"apellidomaterno" varchar(30) NOT NULL,
	"apellidopaterno" varchar(30) NOT NULL,
	"habierto" bool NOT NULL,
	"noempleado" varchar(8) NOT NULL,
	"notarjeta" varchar(8) NOT NULL,
	"nombre" varchar(60) NOT NULL,
	"sexo" char(1) NOT NULL,
	"tipo" varchar(10) NOT NULL,
	"iddepartamento" int4,
	"idhorario" int4 NOT NULL,
	CONSTRAINT "personal_pkey" PRIMARY KEY("idempleado"),
	CONSTRAINT "uk_bhbittwansac2caq5a2cqk36k" UNIQUE("noempleado"),
	CONSTRAINT "uk_5gn9np9b8rx1fmbq23cv74lgt" UNIQUE("notarjeta")
)
WITH (
	OIDS = False
);

ALTER TABLE "personal" OWNER TO "postgres";

CREATE TABLE "personalperiodoinhabil" (
	"idpersonalperiodoinhabil" int4 NOT NULL,
	"idperiodo" int4,
	"idempleado" int4,
	CONSTRAINT "personalperiodoinhabil_pkey" PRIMARY KEY("idpersonalperiodoinhabil")
)
WITH (
	OIDS = False
);

ALTER TABLE "personalperiodoinhabil" OWNER TO "postgres";

CREATE TABLE "personalquincena" (
	"idpersonalquincena" int4 NOT NULL,
	"diaseconomicossolicitados" int4,
	"justificacionessuplementario" int4,
	"idempleado" int4,
	"idquincena" int4,
	CONSTRAINT "personalquincena_pkey" PRIMARY KEY("idpersonalquincena")
)
WITH (
	OIDS = False
);

ALTER TABLE "personalquincena" OWNER TO "postgres";

CREATE TABLE "quincena" (
	"idquincena" int4 NOT NULL,
	"fechalimpersonal" timestamp NOT NULL,
	"fechalimite" timestamp NOT NULL,
	"fin" date NOT NULL,
	"inicio" date NOT NULL,
	"quincenareportada" varchar(255) NOT NULL,
	"quincenaenqueseraprocesada" int4,
	CONSTRAINT "quincena_pkey" PRIMARY KEY("idquincena"),
	CONSTRAINT "uk_ix24up55suks2cw5odbwvlxnu" UNIQUE("fin"),
	CONSTRAINT "uk_duuxjurpdm2wskbd376egsbaf" UNIQUE("inicio")
)
WITH (
	OIDS = False
);

ALTER TABLE "quincena" OWNER TO "postgres";

CREATE TABLE "quincenaperhabil" (
	"idpersonalquincena" int4 NOT NULL,
	"idperiodo" int4,
	"idquincena" int4,
	CONSTRAINT "quincenaperhabil_pkey" PRIMARY KEY("idpersonalquincena")
)
WITH (
	OIDS = False
);

ALTER TABLE "quincenaperhabil" OWNER TO "postgres";

CREATE TABLE "retardo" (
	"id" SERIAL NOT NULL,
	"justificacion" varchar(600) NOT NULL,
	"idjustificante" SERIAL NOT NULL,
	CONSTRAINT "retardo_pkey" PRIMARY KEY("id")
)
WITH (
	OIDS = False
);

ALTER TABLE "retardo" OWNER TO "postgres";

CREATE TABLE "tiemposuplementario" (
	"id" SERIAL NOT NULL,
	"fecha" date NOT NULL,
	"tiempocubrir" int4 NOT NULL,
	"idjustificante" SERIAL NOT NULL,
	CONSTRAINT "tiemposuplementario_pkey" PRIMARY KEY("id")
)
WITH (
	OIDS = False
);

ALTER TABLE "tiemposuplementario" OWNER TO "postgres";

CREATE TABLE "tiemposuplgenerado" (
	"idtiemposuplgenerado" SERIAL NOT NULL,
	"fecharegistro" date NOT NULL,
	"horas" time NOT NULL,
	"usado" bool NOT NULL,
	"idempleado" int4,
	CONSTRAINT "tiemposuplgenerado_pkey" PRIMARY KEY("idtiemposuplgenerado")
)
WITH (
	OIDS = False
);

ALTER TABLE "tiemposuplgenerado" OWNER TO "postgres";

CREATE TABLE "tipoa" (
	"id" SERIAL NOT NULL,
	"fechafin" date NOT NULL,
	"folio" varchar(255) NOT NULL,
	"fechainicio" date NOT NULL,
	"licenciaarchivo" varchar(255) NOT NULL,
	"tipo" varchar(255) NOT NULL,
	"idjustificante" SERIAL NOT NULL,
	"idunidad" varchar(255),
	CONSTRAINT "tipoa_pkey" PRIMARY KEY("id")
)
WITH (
	OIDS = False
);

ALTER TABLE "tipoa" OWNER TO "postgres";

CREATE TABLE "unidadmedica" (
	"idunidad" varchar(255) NOT NULL,
	"nombre" varchar(180) NOT NULL,
	"idzona" varchar(35),
	CONSTRAINT "unidadmedica_pkey" PRIMARY KEY("idunidad")
)
WITH (
	OIDS = False
);

ALTER TABLE "unidadmedica" OWNER TO "postgres";

CREATE TABLE "zona" (
	"idzona" varchar(35) NOT NULL,
	"nombre" varchar(180) NOT NULL,
	CONSTRAINT "zona_pkey" PRIMARY KEY("idzona")
)
WITH (
	OIDS = False
);

ALTER TABLE "zona" OWNER TO "postgres";


ALTER TABLE "asistencia" ADD CONSTRAINT "personal_fk" FOREIGN KEY ("idempleado")
	REFERENCES "personal"("idempleado")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "cambiohorario" ADD CONSTRAINT "justificante_fk" FOREIGN KEY ("idjustificante")
	REFERENCES "justificante"("idjustificante")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "comisionoficial" ADD CONSTRAINT "justificante_fk" FOREIGN KEY ("idjustificante")
	REFERENCES "justificante"("idjustificante")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "constanciatiempo" ADD CONSTRAINT "justificante_fk" FOREIGN KEY ("idjustificante")
	REFERENCES "justificante"("idjustificante")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "departamento" ADD CONSTRAINT "jefesuperior_fk" FOREIGN KEY ("idsuperior")
	REFERENCES "jefesuperior"("idsuperior")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "dia" ADD CONSTRAINT "horarioactual_fk" FOREIGN KEY ("idhorario")
	REFERENCES "horarioactual"("idhorario")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "incidencia" ADD CONSTRAINT "justificante_fk" FOREIGN KEY ("idjustificante")
	REFERENCES "justificante"("idjustificante")
	MATCH SIMPLE
	ON DELETE SET NULL
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "incidencia" ADD CONSTRAINT "empleado_fk" FOREIGN KEY ("idempleado")
	REFERENCES "personal"("idempleado")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "incidencia" ADD CONSTRAINT "quincena_fk" FOREIGN KEY ("idquincena")
	REFERENCES "quincena"("idquincena")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "jefesuperior" ADD CONSTRAINT "jefesuperior_fk" FOREIGN KEY ("idsuperior")
	REFERENCES "jefesuperior"("idsuperior")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "justificante" ADD CONSTRAINT "empleado_fk" FOREIGN KEY ("idempleado")
	REFERENCES "personal"("idempleado")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "licpaternidad" ADD CONSTRAINT "justificante_fk" FOREIGN KEY ("idjustificante")
	REFERENCES "justificante"("idjustificante")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "login" ADD CONSTRAINT "personal_fk" FOREIGN KEY ("idempleado")
	REFERENCES "personal"("idempleado")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "notificacion" ADD CONSTRAINT "motivo_fk" FOREIGN KEY ("idmotivo")
	REFERENCES "motivo"("idmotivo")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "notificacion" ADD CONSTRAINT "personal_fk" FOREIGN KEY ("idempleado")
	REFERENCES "personal"("idempleado")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "omisionentrsal" ADD CONSTRAINT "justificante_fk" FOREIGN KEY ("idjustificante")
	REFERENCES "justificante"("idjustificante")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "permisoeconomico" ADD CONSTRAINT "justificante_fk" FOREIGN KEY ("idjustificante")
	REFERENCES "justificante"("idjustificante")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "personal" ADD CONSTRAINT "departamento_fk" FOREIGN KEY ("iddepartamento")
	REFERENCES "departamento"("iddepartamento")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "personal" ADD CONSTRAINT "horarioactual_fk" FOREIGN KEY ("idhorario")
	REFERENCES "horarioactual"("idhorario")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "personalperiodoinhabil" ADD CONSTRAINT "periodoinhabil_fk" FOREIGN KEY ("idperiodo")
	REFERENCES "periodoinhabil"("idperiodo")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "personalperiodoinhabil" ADD CONSTRAINT "empleado_fk" FOREIGN KEY ("idempleado")
	REFERENCES "personal"("idempleado")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "personalquincena" ADD CONSTRAINT "empleado_fk" FOREIGN KEY ("idempleado")
	REFERENCES "personal"("idempleado")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "personalquincena" ADD CONSTRAINT "quincena_fk" FOREIGN KEY ("idquincena")
	REFERENCES "quincena"("idquincena")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "quincena" ADD CONSTRAINT "quincena_fk" FOREIGN KEY ("idquincena")
	REFERENCES "quincena"("idquincena")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "quincenaperhabil" ADD CONSTRAINT "periodo_fk" FOREIGN KEY ("idperiodo")
	REFERENCES "periodoinhabil"("idperiodo")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "quincenaperhabil" ADD CONSTRAINT "quincena_fk" FOREIGN KEY ("idquincena")
	REFERENCES "quincena"("idquincena")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "retardo" ADD CONSTRAINT "justificante_fk" FOREIGN KEY ("idjustificante")
	REFERENCES "justificante"("idjustificante")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "tiemposuplementario" ADD CONSTRAINT "justificante_fk" FOREIGN KEY ("idjustificante")
	REFERENCES "justificante"("idjustificante")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "tiemposuplgenerado" ADD CONSTRAINT "personal_fk" FOREIGN KEY ("idempleado")
	REFERENCES "personal"("idempleado")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "tipoa" ADD CONSTRAINT "justificante_fk" FOREIGN KEY ("idjustificante")
	REFERENCES "justificante"("idjustificante")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "tipoa" ADD CONSTRAINT "unidadmedica_fk" FOREIGN KEY ("idunidad")
	REFERENCES "unidadmedica"("idunidad")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "unidadmedica" ADD CONSTRAINT "zona_fk" FOREIGN KEY ("idzona")
	REFERENCES "zona"("idzona")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;


