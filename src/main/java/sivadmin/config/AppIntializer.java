package sivadmin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import sivadmin.models.*;
import sivadmin.payload.Request.AdresseRequest;
import sivadmin.payload.Request.PeriodeRequest;
import sivadmin.payload.Request.SkjemaRequest;
import sivadmin.repository.*;

import javax.annotation.PostConstruct;
import java.time.MonthDay;
import java.util.*;

public class AppIntializer {
    @Autowired
    BrukerRepository brukerRepository;

    @Autowired
    KlyngeRepository klyngeRepository;

    @Autowired
    IntervjuerRepository intervjuerRepository;

    @Autowired
    RolleRepository rolleRepository;

    @Autowired
    ProsjektRepository prosjektRepository;

    @Autowired
    ProsjektLederRepository prosjektLederRepository;

    @Autowired
    SkjemaRepository skjemaRepository;

    @Autowired
    KommuneRepository kommuneRepository;

    @Autowired
    FravaerRepository fravaerRepository;

    @Autowired
    ProduktRepository produktRepository;

    @Autowired
    PeriodeRepository periodeRepository;

    @Autowired
    IntervjuObjektRepository intervjuObjektRepository;

    @Autowired
    AdresseRepository adresseRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        // Opprett roller
        Rolle rolleSil = new Rolle("ROLE_SIL");
        Rolle rolleAdmin = new Rolle("ROLE_ADMIN");
        Rolle rolleIntervjuer = new Rolle("ROLE_INTERVJUER");
        Rolle rolleIntervjuerkontakt = new Rolle("ROLE_INTERVJUERKONTAKT");
        Rolle rollePlanlegger = new Rolle("ROLE_PLANLEGGER");
        Rolle rolleSporingsperson = new Rolle("ROLE_SPORINGSPERSON");
        Rolle rolleCapiTildeling = new Rolle("ROLE_CAPITILDELING");
        Rolle rolleSupervisor = new Rolle("ROLE_SUPERVISOR");

        if(rolleRepository.count() == 0) {
            rolleRepository.save(rolleAdmin);
            rolleRepository.save(rolleIntervjuer);
            rolleRepository.save(rolleSil);
            rolleRepository.save(rolleIntervjuerkontakt);
            rolleRepository.save(rollePlanlegger);
            rolleRepository.save(rolleSporingsperson);
            rolleRepository.save(rolleCapiTildeling);
            rolleRepository.save(rolleSupervisor);
        }
       
        if(brukerRepository.count() == 0) {
            opprettAdminBruker(rolleAdmin);
            opprettTestBrukere(rolleAdmin, rolleSil, rolleSupervisor, rollePlanlegger, rolleIntervjuer, rolleSporingsperson);
        }

        if(klyngeRepository.count() == 0) {
            opprettKlynger();
        }

        if(produktRepository.count() == 0) {
            opprettProdukter();
        }

        if(intervjuerRepository.count() < 2 ) {
            opprettIntervjuere();
        }

        if(prosjektRepository.count() == 0) {
            opprettProsjekter();
        }

        if(intervjuObjektRepository.count() == 0) {
     //       opprettIntervjueObjekter();
        }

    }

    void opprettAdminBruker(Rolle rolleAdmin) {
        Optional<Bruker> si4 = brukerRepository.findByBrukernavn("si4");
        Optional<Bruker> ibr = brukerRepository.findByBrukernavn("ibr");

        if(si4.isEmpty()) {
            Bruker nyBruker = new Bruker("si4", "si4", "si4@ssb.no", passwordEncoder.encode("12345"));
            Set<Rolle> roller = new HashSet<>();
            roller.add(rolleAdmin);

            nyBruker.setRoller(roller);
            brukerRepository.save(nyBruker);
        }

        if(ibr.isEmpty()) {
            Bruker nyBruker = new Bruker("ibrahim", "ibr", "ibrahim@xala.no", passwordEncoder.encode("12345"));
            Set<Rolle> roller = new HashSet<>();
            roller.add(rolleAdmin);

            nyBruker.setRoller(roller);
            brukerRepository.save(nyBruker);
        }
    }

    void opprettTestBrukere(Rolle rolleAdmin, Rolle rolleSil, Rolle rolleSupervisor, Rolle rollePlanlegger, Rolle rolleIntervjuer, Rolle rolleSporingsperson) {
        Optional<Bruker> admmKrn = brukerRepository.findByBrukernavn("krn");
        if(admmKrn.isEmpty()) {
            Bruker nyBruker = new Bruker("Stian Karlsen", "krn", "krn@ssb.no", passwordEncoder.encode("x"));
            Set<Rolle> roller = new HashSet<>();
            roller.add(rolleAdmin);
            roller.add(rolleSil);

            nyBruker.setRoller(roller);
            brukerRepository.save(nyBruker);
        }

        Optional<Intervjuer> adminIntervjuerKrn = intervjuerRepository.findByInitialer("krn");
        if(adminIntervjuerKrn.isEmpty()) {
            opprettAdminIntervjuerKrn();
        }

        Optional<Bruker> si1 = brukerRepository.findByBrukernavn("si1");
        if(si1.isEmpty()) {
            Bruker nyBruker = new Bruker("Supervisor", "si1", "si1@ssb.no", passwordEncoder.encode("S1vtest00"));
            Set<Rolle> roller = new HashSet<>();
            roller.add(rolleSupervisor);

            nyBruker.setRoller(roller);
            brukerRepository.save(nyBruker);
        }

        Optional<Bruker> si2 = brukerRepository.findByBrukernavn("si2");
        if(si2.isEmpty()) {
            Bruker nyBruker = new Bruker("Sporing", "si2", "si2@ssb.no", passwordEncoder.encode("S1vtest00"));
            Set<Rolle> roller = new HashSet<>();
            roller.add(rolleSporingsperson);

            nyBruker.setRoller(roller);
            brukerRepository.save(nyBruker);
        }

        Optional<Bruker> si3 = brukerRepository.findByBrukernavn("si3");
        if(si3.isEmpty()) {
            Bruker nyBruker = new Bruker("Planlegger", "si3", "si3@ssb.no", passwordEncoder.encode("S1vtest00"));
            Set<Rolle> roller = new HashSet<>();
            roller.add(rollePlanlegger);

            nyBruker.setRoller(roller);
            brukerRepository.save(nyBruker);
        }

        Optional<Bruker> si4 = brukerRepository.findByBrukernavn("si4");
        if(si4.isEmpty()) {
            Bruker nyBruker = new Bruker("Adm", "si4", "si4@ssb.no", passwordEncoder.encode("S1vtest00"));
            Set<Rolle> roller = new HashSet<>();
            roller.add(rolleAdmin);

            nyBruker.setRoller(roller);
            brukerRepository.save(nyBruker);
        }

        Optional<Bruker> imr = brukerRepository.findByBrukernavn("imr");
        if(imr.isEmpty()) {
            Bruker nyBruker = new Bruker("Ibrahim", "imr", "imr@ssb.no", passwordEncoder.encode("12345"));
            Set<Rolle> roller = new HashSet<>();
            roller.add(rolleAdmin);

            nyBruker.setRoller(roller);
            brukerRepository.save(nyBruker);
        }

        Optional<Bruker> si6 = brukerRepository.findByBrukernavn("si6");
        if(si6.isEmpty()) {
            Bruker nyBruker = new Bruker("Intervjuer", "si6", "si6@ssb.no", passwordEncoder.encode("12345"));
            Set<Rolle> roller = new HashSet<>();
            roller.add(rolleIntervjuer);

            nyBruker.setRoller(roller);
            brukerRepository.save(nyBruker);
        }

        Optional<Intervjuer> intervjuer_imr = intervjuerRepository.findByInitialer("imr");
        if(intervjuer_imr.isEmpty()) {
            opprettAdminIntervjuerImr();
        }
    }

    void opprettAdminIntervjuerKrn() {
        Intervjuer intervjuer = new Intervjuer();

        List<Klynge> klynge = klyngeRepository.findByKlyngeNavn("Klynge Oslo");
        if(klynge.isEmpty()) {
            Klynge nyKlynge = new Klynge("Klynge Oslo", "Test", "test_test@ssb.no", "Klynge for Oslo");
            intervjuer.setKlynge(nyKlynge);

            klyngeRepository.save(nyKlynge);

            Optional<Kommune> kommune = kommuneRepository.findByKommuneNummer("0301");
            if(kommune.isEmpty()) {
                Kommune nyKommune = new Kommune("0301", "Oslo", nyKlynge);
                intervjuer.setKommuneNummer(nyKommune.getKommuneNummer());

                kommuneRepository.save(nyKommune);
            }

        }

        intervjuer.setInitialer("krn");
        intervjuer.setIntervjuerNummer(113355L);
        intervjuer.setNavn("Stian Karlsen");
        intervjuer.setKjonn("MANN");

        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR, 1974);
        cal.set(Calendar.MONTH, 2);
        cal.set(Calendar.DAY_OF_MONTH, 15);
        intervjuer.setFodselsDato(cal.getTime());

        intervjuer.setGateAdresse("Etterstadsletta 68");
        intervjuer.setPostNummer("0659");
        intervjuer.setPostSted("Oslo");
        intervjuer.setKommuneNummer("0301");
        intervjuer.setEpostPrivat("texasslim@gmail.com");
        intervjuer.setEpostJobb("krn@ssb.no");
        intervjuer.setStatus("AKTIV");

        cal.set(Calendar.YEAR, 2010);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 0);

        intervjuer.setAnsattDato(cal.getTime());

        intervjuer.setAvtaltAntallTimer(1500L);
        intervjuer.setArbeidsType("BEGGE");

        cal.set(Calendar.YEAR, 2050);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        intervjuer.setSluttDato(cal.getTime());

        intervjuerRepository.save(intervjuer);

        Date now = new Date();

        cal.setTime(now);

        for (int i=0; i<20; i++) {
            cal.add(Calendar.DATE, i);
            Fravaer fravaer = new Fravaer(cal.getTime(), cal.getTime(), "ANNET", 100L, "Fravær "+(i+1), intervjuer);
            fravaerRepository.save(fravaer);
        }


    }

    void opprettAdminIntervjuerImr() {
        Intervjuer intervjuer = new Intervjuer();

        List<Klynge> klynge = klyngeRepository.findByKlyngeNavn("Klynge Skui");
        if(klynge.isEmpty()) {
            Klynge nyKlynge = new Klynge("Klynge Skui", "Test", "test_test@ssb.no", "Klynge for Skui");
            klyngeRepository.save(nyKlynge);

            intervjuer.setKlynge(nyKlynge);

            Optional<Kommune> kommune = kommuneRepository.findByKommuneNummer("1340");
            if(kommune.isEmpty()) {
                Kommune nyKommune = new Kommune("1340", "Skui", nyKlynge);
                kommuneRepository.save(nyKommune);
                intervjuer.setKommuneNummer(nyKommune.getKommuneNummer());
            }

        }

        intervjuer.setInitialer("imr");
        intervjuer.setIntervjuerNummer(113355L);
        intervjuer.setNavn("Stian Karlsen");
        intervjuer.setKjonn("MANN");

        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR, 1974);
        cal.set(Calendar.MONTH, 2);
        cal.set(Calendar.DAY_OF_MONTH, 15);
        intervjuer.setFodselsDato(cal.getTime());

        intervjuer.setGateAdresse("Etterstadsletta 68");
        intervjuer.setPostNummer("1340");
        intervjuer.setPostSted("Skui");
        intervjuer.setKommuneNummer("1340");
        intervjuer.setEpostPrivat("texasslim@gmail.com");
        intervjuer.setEpostJobb("krn@ssb.no");
        intervjuer.setStatus("AKTIV");

        cal.set(Calendar.YEAR, 2010);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 0);

        intervjuer.setAnsattDato(cal.getTime());

        intervjuer.setAvtaltAntallTimer(1500L);
        intervjuer.setArbeidsType("BEGGE");

        cal.set(Calendar.YEAR, 2050);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        intervjuer.setSluttDato(cal.getTime());

        intervjuerRepository.save(intervjuer);

        Date now = new Date();

        cal.setTime(now);

        for (int i=0; i<20; i++) {
            cal.add(Calendar.DATE, i);
            Fravaer fravaer = new Fravaer(cal.getTime(), cal.getTime(), "ANNET", 100L, "Fravær "+(i+1), intervjuer);
            fravaerRepository.save(fravaer);
        }


    }

    void opprettKlynger() {
        Klynge klynge1 = new Klynge("Klynge Oslo", "ato", "klyngeoslo@ssb.no", "Klynge for Oslo");
        Klynge klynge2 = new Klynge("Klynge Østlandet", "hag", "klyngeost@ssb.no", "Klynge for Østlandet");
        Klynge klynge3 = new Klynge("Klynge Innlandet", "aee", "klyngeinnlandet@ssb.no", "Klynge for Innlandet");
        Klynge klynge4 = new Klynge("Klynge Sør-Vest", "hbl", "klyngesorvest@ssb.no", "Klynge for Sør-Vestlandet");
        Klynge klynge5 = new Klynge("Klynge Vest", "hbs", "klyngevest@ssb.no", "Klynge for Vestlandet");
        Klynge klynge6 = new Klynge("Klynge Midt-Norge", "kag", "klyngemidtnorge@ssb.no", "Klynge for Midt-Norge");
        Klynge klynge7 = new Klynge("Klynge Nord", "hbl", "klyngenord@ssb.no", "Klynge for Nord-Norge");
        Klynge klynge8 = new Klynge("Klynge Ringesenter Oslo", "ruw", "klyngeringesenteroslo@ssb.no", "Klynge for Ringesenter Oslo");
        Klynge klynge9 = new Klynge("Klynge Ringesenter Kongsvinger", "ord", "klyngeringesenterkongsvinger@ssb.no", "Klynge for Ringesenter Kongsvinger");
        Klynge klynge0 = new Klynge("Klynge Skui", "imr", "skui@ssb.no", "Klynge for Skui");

       klyngeRepository.save(klynge2);
       klyngeRepository.save(klynge3);
       klyngeRepository.save(klynge1);
       klyngeRepository.save(klynge4);
       klyngeRepository.save(klynge5);
       klyngeRepository.save(klynge6);
       klyngeRepository.save(klynge7);
       klyngeRepository.save(klynge8);
       klyngeRepository.save(klynge9);
       klyngeRepository.save(klynge0);
    }
    
    void opprettProdukter() {
        Produkt produkt1 = new Produkt("Etter avtale med kontoret", "00980-0", "STAT_MARKED", 50L, 50L);
        Produkt produkt2 = new Produkt("Opplæring", "00980-1", "STAT_MARKED", 50L, 50L);
        Produkt produkt3 = new Produkt("SIV - System for intervjuvirksomhet, FOSS", "07413-0", "STAT_MARKED", 50L, 50L);
        Produkt produkt4 = new Produkt("Utredning av intervjuvirksomheten", "01209-0", "STAT_MARKED", 50L, 50L);
        Produkt produkt5 = new Produkt("Helse, miljø og sikkerhet", "01830-0", "STAT_MARKED", 50L, 50L);
        Produkt produkt6 = new Produkt("Problemer med PCen", "01860-0", "STAT_MARKED", 50L, 50L);
        Produkt produkt7 = new Produkt("KPI: Prisinnsamling", "02480-9", "STAT_MARKED", 50L, 50L);
        Produkt produkt8 = new Produkt("Fagforeningsarbeid", "03220-0", "STAT_MARKED", 50L, 50L);
        Produkt produkt9 = new Produkt("Administrasjon", "01004-0", "STAT_MARKED", 50L, 50L);

        produktRepository.save(produkt1);
        produktRepository.save(produkt2);
        produktRepository.save(produkt3);
        produktRepository.save(produkt4);
        produktRepository.save(produkt5);
        produktRepository.save(produkt6);
        produktRepository.save(produkt7);
        produktRepository.save(produkt8);
        produktRepository.save(produkt9);
    }

    void opprettIntervjuere() {
        Klynge klynge1 = klyngeRepository.getByKlyngeNavn("Klynge Oslo");
        Klynge klynge0 = klyngeRepository.getByKlyngeNavn("Klynge Skui");
        Klynge klynge2 = klyngeRepository.getByKlyngeNavn("Klynge Østlandet");
        Klynge klynge3 = klyngeRepository.getByKlyngeNavn("Klynge Innlandet");
        Klynge klynge4 = klyngeRepository.getByKlyngeNavn("Klynge Sør-Vest");
        Klynge klynge5 = klyngeRepository.getByKlyngeNavn("Klynge Vest");
        Klynge klynge6 = klyngeRepository.getByKlyngeNavn("Klynge Midt-Norge");
        Klynge klynge7 = klyngeRepository.getByKlyngeNavn("Klynge Nord");
        Klynge klynge8 = klyngeRepository.getByKlyngeNavn("Klynge Ringesenter Oslo");
        Klynge klynge9 = klyngeRepository.getByKlyngeNavn("Klynge Ringesenter Kongsvinger");

        Rolle rolleIntervjuer = rolleRepository.findByRolleNavn("ROLE_INTERVJUER");

        Intervjuer intervjuer1 = new Intervjuer();
        intervjuer1.setInitialer("adn");
        intervjuer1.setIntervjuerNummer(101604l);
        intervjuer1.setNavn("Nordnes Åshild");
        intervjuer1.setKjonn("KVINNE");
        intervjuer1.setKlynge(klynge6);

        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR, 1974);
        cal.set(Calendar.MONTH, 2);
        cal.set(Calendar.DAY_OF_MONTH, 15);
        intervjuer1.setFodselsDato(cal.getTime());

        intervjuer1.setGateAdresse("Etterstadsletta 68");
        intervjuer1.setPostNummer("0659");
        intervjuer1.setPostSted("Oslo");
        intervjuer1.setKommuneNummer("1663");
        intervjuer1.setEpostPrivat("texasslim@gmail.com");
        intervjuer1.setEpostJobb("krn@ssb.no");
        intervjuer1.setStatus("AKTIV");

        cal.set(Calendar.YEAR, 2010);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 0);

        intervjuer1.setAnsattDato(cal.getTime());

        intervjuer1.setAvtaltAntallTimer(1500L);
        intervjuer1.setArbeidsType("BEGGE");

        cal.set(Calendar.YEAR, 2050);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        intervjuer1.setSluttDato(cal.getTime());

        intervjuerRepository.save(intervjuer1);
    }

    void opprettProsjekter() {
        if(prosjektLederRepository.count() == 0) {
            opprettProsjektLedere();
        }

        ProsjektLeder prosjektLeder1 = prosjektLederRepository.findByInitialer("raz");
        ProsjektLeder prosjektLeder2 = prosjektLederRepository.findByInitialer("imr");

        Calendar calen = Calendar.getInstance();
        calen.add(Calendar.DAY_OF_YEAR, 60);

        Prosjekt prosjekt = new Prosjekt();

        prosjekt.setPanel(true);
        prosjekt.setProduktNummer("05490");
        prosjekt.setModus("En");
        prosjekt.setRegisterNummer("0123");
        prosjekt.setProsjektNavn("Levekaar");
        prosjekt.setAargang("2010");
        prosjekt.setProsjektLeder(prosjektLeder1);
        prosjekt.setOppstartDato(new Date());
        prosjekt.setAvslutningsDato(calen.getTime());
        prosjekt.setProsjektStatus("Aktiv");
        prosjekt.setFinansiering("Stat");
        prosjekt.setProsentStat(100L);
        prosjekt.setProsentMarked(0L);
        prosjekt.setKommentar("test");

        prosjektRepository.save(prosjekt);

        Prosjekt prosjekt2 = new Prosjekt();

        prosjekt2.setPanel(true);
        prosjekt2.setProduktNummer("00345");
        prosjekt2.setModus("En");
        prosjekt2.setRegisterNummer("0234");
        prosjekt2.setProsjektNavn("Medie");
        prosjekt2.setAargang("2010");
        prosjekt2.setProsjektLeder(prosjektLeder2);
        prosjekt2.setOppstartDato(new Date());
        prosjekt2.setAvslutningsDato(calen.getTime());
        prosjekt2.setProsjektStatus("Aktiv");
        prosjekt2.setFinansiering("Stat");
        prosjekt2.setProsentStat(100L);
        prosjekt2.setProsentMarked(0L);
        prosjekt2.setKommentar("test");

        prosjektRepository.save(prosjekt2);

        Prosjekt prosjekt3 = new Prosjekt();

        prosjekt3.setPanel(true);
        prosjekt3.setProduktNummer("05776");
        prosjekt3.setModus("En");
        prosjekt3.setRegisterNummer("0234");
        prosjekt3.setProsjektNavn("PIAAC");
        prosjekt3.setAargang("2011");
        prosjekt3.setProsjektLeder(prosjektLeder1);
        prosjekt3.setOppstartDato(new Date());
        prosjekt3.setAvslutningsDato(calen.getTime());
        prosjekt3.setProsjektStatus("Aktiv");
        prosjekt3.setFinansiering("Stat");
        prosjekt3.setProsentStat(100L);
        prosjekt3.setProsentMarked(0L);
        prosjekt3.setKommentar("piaac test");

        prosjektRepository.save(prosjekt3);

        Prosjekt prosjekt4 = new Prosjekt();

        prosjekt4.setPanel(true);
        prosjekt4.setProduktNummer("02090");
        prosjekt4.setModus("En");
        prosjekt4.setRegisterNummer("0234");
        prosjekt4.setProsjektNavn("AKU");
        prosjekt4.setAargang("2011");
        prosjekt4.setProsjektLeder(prosjektLeder1);
        prosjekt4.setOppstartDato(new Date());
        prosjekt4.setAvslutningsDato(calen.getTime());
        prosjekt4.setProsjektStatus("Aktiv");
        prosjekt4.setFinansiering("Stat");
        prosjekt4.setProsentStat(100L);
        prosjekt4.setProsentMarked(0L);
        prosjekt4.setKommentar("aku test");

        prosjektRepository.save(prosjekt4);

        opprettSkjemaer(prosjekt);

    }

    void opprettProsjektLedere() {
        ProsjektLeder prosjektLeder1 = new ProsjektLeder("Braakjekk Seeberg", "raz", "raz@ssb.no");
        ProsjektLeder prosjektLeder2 = new ProsjektLeder("Ibrahim Rahmani", "imr", "imr@ssb.no");
        ProsjektLeder prosjektLeder3 = new ProsjektLeder("Trond", "trond", "trond@ssb.no");
        ProsjektLeder prosjektLeder4 = new ProsjektLeder("Ole Nordmann", "ole", "ole@ssb.no");
        ProsjektLeder prosjektLeder5 = new ProsjektLeder("Daud Rahmani", "ddr", "ddr@ssb.no");

        prosjektLederRepository.save(prosjektLeder1);
        prosjektLederRepository.save(prosjektLeder2);
        prosjektLederRepository.save(prosjektLeder3);
        prosjektLederRepository.save(prosjektLeder4);
        prosjektLederRepository.save(prosjektLeder5);
    }

    void opprettPerioder(Skjema skjema) {
        PeriodeRequest periodeRequest = new PeriodeRequest();

        periodeRequest.setAar("2010");
        periodeRequest.setPeriodeNummer(1L);
        periodeRequest.setPeriodeType("KVRT");
        periodeRequest.setOppstartDataInnsamling(new Date());
        periodeRequest.setHentesTidligst(new Date());
        periodeRequest.setPlanlagtSluttDato(new Date());
        periodeRequest.setSluttDato(new Date());
        periodeRequest.setIncentiver("in");
        periodeRequest.setKommentar("periode1-1");
        periodeRequest.setDelregisterNummer(1234L);

        Periode periode = new Periode(periodeRequest);
        periode.setSkjema(skjema);

        periodeRepository.save(periode);

        periodeRequest.setAar("2010");
        periodeRequest.setPeriodeNummer(2L);
        periodeRequest.setPeriodeType("KVRT");
        periodeRequest.setOppstartDataInnsamling(new Date());
        periodeRequest.setHentesTidligst(new Date());
        periodeRequest.setPlanlagtSluttDato(new Date());
        periodeRequest.setSluttDato(new Date());
        periodeRequest.setIncentiver("in");
        periodeRequest.setKommentar("periode2-1");
        periodeRequest.setDelregisterNummer(1234L);

        Periode periode2 = new Periode(periodeRequest);
        periode2.setSkjema(skjema);

        periodeRepository.save(periode2);
    }

    void opprettSkjemaer(Prosjekt prosjekt) {
        Calendar calen = Calendar.getInstance();
        calen.set(Calendar.YEAR, 2012);
        calen.set(Calendar.MONTH, 1);
        calen.set(Calendar.DATE, 25);

        SkjemaRequest skjema = new SkjemaRequest();
        skjema.setSkjemaNavn("levekaar_skjema");
        skjema.setSkjemaKortNavn("lev");
        skjema.setDelProduktNummer("05490-0");
        skjema.setUndersokelseType("Bedrift");
        skjema.setInstrumentId("skjema1");
        skjema.setIntervjuTypeBesok(true);
        skjema.setIntervjuTypeTelefon(true);
        skjema.setIntervjuTypePapir(false);
        skjema.setIntervjuTypeWeb(false);
        skjema.setKanSlettesLokalt(false);
        skjema.setAltIBlaise5(true);
        skjema.setOppstartDataInnsamling(calen.getTime());
        skjema.setStatus("S");
        skjema.setPlanlagtSluttDato(new Date());
        skjema.setSluttDato(new Date());
        skjema.setOvertid(true);
        skjema.setOnsketSvarProsent(80L);
        skjema.setDataUttaksDato(new Date());
        skjema.setHentAlleOppdrag(true);
        skjema.setKanSlettesLokalt(true);
        skjema.setIntervjuVarighet(20L);
        skjema.setAdminTid(20L);
        skjema.getRegoverforingDato();
        skjema.setRegoverforingInitialer("imr");
        skjema.setRegoverforingSeksjon("750");
        skjema.setIoBrevGodkjentDato(new Date());
        skjema.setIoBrevGodkjentInitialer("imr");
        skjema.setMalVersjon(2L);
        skjema.setKommentar("test");

        Skjema nySkjema = new Skjema(skjema);
        nySkjema.setProsjekt(prosjekt);

        skjemaRepository.save(nySkjema);

        opprettPerioder(nySkjema);

    }

    void opprettIntervjueObjekter() {
        Calendar calen_fom = Calendar.getInstance();
        Calendar calen_tom = Calendar.getInstance();

        calen_fom.set(Calendar.YEAR, 2014);
        calen_fom.set(Calendar.MONTH, 4);
        calen_fom.set(Calendar.DATE, 3);

        calen_tom.set(Calendar.YEAR, 2015);
        calen_tom.set(Calendar.MONTH, 4);
        calen_tom.set(Calendar.DATE, 3);

        AdresseRequest adresseRequest = new AdresseRequest();
        adresseRequest.setAdresseType("Besøk");
        adresseRequest.setGateAdresse("Olav Kyres gt.");
        adresseRequest.setPostNummer("5000");
        adresseRequest.setPostSted("Bergen");
        adresseRequest.setGyldigFom(calen_fom.getTime());
        adresseRequest.setGyldigTom(calen_tom.getTime());
        adresseRequest.setGjeldende(true);

        Adresse adresse = new Adresse(adresseRequest);
        adresseRepository.save(adresse);

        adresseRequest.setAdresseType("Post");
        adresseRequest.setGateAdresse("Torgalmenningen 4");
        adresseRequest.setPostNummer("5000");
        adresseRequest.setPostSted("Bergen");
        adresseRequest.setGyldigFom(calen_fom.getTime());
        adresseRequest.setGyldigTom(calen_tom.getTime());
        adresseRequest.setGjeldende(true);

        Adresse adresse2 = new Adresse(adresseRequest);
        adresseRepository.save(adresse2);


    }
}
