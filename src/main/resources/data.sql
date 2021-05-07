-- Roller
INSERT INTO roller(rolle_navn) VALUES('ROLE_INTERVJUER');
INSERT INTO roller(rolle_navn) VALUES('ROLE_ADMIN');

-- Brukere
INSERT INTO brukere (navn, brukernavn, passord, OPPRETTET_DATO, OPPDATERT_DATO) VALUES ('Intervjuer', 'krn', '$2a$10$OAGGSkXjvXSNuUwJclqtmuBKhHv61rTOVaLwE8ZVhWzYSrhM8I00.', PARSEDATETIME ('31-12-18 11:34:24','dd-MM-yy hh:mm:ss'), PARSEDATETIME ('31-12-18 11:34:24','dd-MM-yy hh:mm:ss'));
INSERT INTO brukere (navn, brukernavn, passord, OPPRETTET_DATO, OPPDATERT_DATO) VALUES ('Ibrahim', 'imr','$2a$10$M.a247CXdM0rrzsHKiQfCedxiX6jqy3Q6uTTQVCBhXdWcx3sVrqsO', PARSEDATETIME ('31-12-18 11:34:24','dd-MM-yy hh:mm:ss'), PARSEDATETIME ('31-12-18 11:34:24','dd-MM-yy hh:mm:ss'));
INSERT INTO brukere (navn, brukernavn, passord, OPPRETTET_DATO, OPPDATERT_DATO) VALUES ('si4', 'si4','$2a$10$C.AKdejlR/QBod61m/z6AuJbukAHQFcBNKemZmoSkMEhbZxw2I0ia', PARSEDATETIME ('31-12-18 11:34:24','dd-MM-yy hh:mm:ss'), PARSEDATETIME ('31-12-18 11:34:24','dd-MM-yy hh:mm:ss'));
