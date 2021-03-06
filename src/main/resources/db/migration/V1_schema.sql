CREATE TABLE `brukere` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `navn` varchar(40) NOT NULL,
 `brukernavn` varchar(15) NOT NULL,
 `epost` varchar(40) NOT NULL,
 `passord` varchar(100) NOT NULL,
 `opprettet_dato` timestamp DEFAULT CURRENT_TIMESTAMP,
 `oppdatert_dato` timestamp DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`),
 UNIQUE KEY `uk_brukere_brukernavn` (`brukernavn`),
 UNIQUE KEY `uk_brukere_epost` (`epost`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `roller` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `rolle_navn` varchar(60) NOT NULL,
 PRIMARY KEY (`id`),
 UNIQUE KEY `uk_roller_rolle_navn` (`rolle_navn`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


CREATE TABLE `bruker_roller` (
  `bruker_id` bigint(20) NOT NULL,
  `rolle_id` bigint(20) NOT NULL,
  PRIMARY KEY (`bruker_id`,`rolle_id`),
  KEY `fk_bruker_roller_rolle_id` (`rolle_id`),
  CONSTRAINT `fk_bruker_roller_rolle_id` FOREIGN KEY (`rolle_id`) REFERENCES `roller` (`id`),
  CONSTRAINT `fk_bruker_roller_bruker_id` FOREIGN KEY (`bruker_id`) REFERENCES `brukere` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE prosjekt_leder (id bigint generated by default as identity, oppdatert_dato timestamp, opprettet_dato timestamp, epost varchar(255), initialer varchar(255), navn varchar(255), primary key (id))
CREATE TABLE prosjekter (id bigint generated by default as identity, oppdatert_dato timestamp, opprettet_dato timestamp, aargang varchar(255), avslutnings_dato timestamp, finansiering varchar(255), kommentar varchar(255), modus varchar(255), oppstart_dato timestamp, panel boolean, produkt_nummer varchar(255), prosent_marked bigint, prosent_stat bigint, prosjekt_navn varchar(255), prosjekt_status varchar(255), register_nummer varchar(255), primary key (id))