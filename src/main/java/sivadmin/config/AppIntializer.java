package sivadmin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import sivadmin.models.*;
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

        // Lag test prosjektledere
        ProsjektLeder prosjektLeder = new ProsjektLeder();
        prosjektLeder.setNavn("Braakjekk Seeberg");
        prosjektLeder.setInitialer("raz");
        prosjektLeder.setEpost("raz@ssb.no");
        prosjektLederRepository.save(prosjektLeder);

        String navn = "Levekaar";

        // Lag test prosjekter
        for (int i = 0; i < 36; i++) {

            if(i > 10) {
                navn = "Demo";
            }

            Calendar calen = Calendar.getInstance();
            calen.add(Calendar.DAY_OF_YEAR, 60 + i);

            ProsjektLeder prosjektLeder1 = prosjektLederRepository.findFirstByOrderByIdAsc();

            Prosjekt prosjekt = new Prosjekt();
            prosjekt.setPanel(true);
            prosjekt.setProduktNummer("0549" + i);
            prosjekt.setModus("En");
            prosjekt.setRegisterNummer("012" + i);

            prosjekt.setProsjektNavn(navn + i);
            prosjekt.setAargang("201" + i);
            prosjekt.setProsjektLeder(prosjektLeder1);
            prosjekt.setOppstartDato(new Date());
            prosjekt.setAvslutningsDato(calen.getTime());
            prosjekt.setProsjektStatus("Aktiv");
            prosjekt.setFinansiering("Stat");
            prosjekt.setProsentStat(100L);
            prosjekt.setProsentMarked(0L);
            prosjekt.setKommentar("ingen");


            Skjema skjema = new Skjema();
            skjema.setSkjemaNavn("skjema" + i);
            skjema.setSkjemaKortNavn("kortnavn" + i);
            skjema.setDelProduktNummer("3883" + i);
            skjema.setProsjekt(prosjekt);

            prosjekt.getSkjemaer().add(skjema);
            prosjektRepository.save(prosjekt);

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

       klyngeRepository.save(klynge2);
       klyngeRepository.save(klynge3);
       klyngeRepository.save(klynge1);
       klyngeRepository.save(klynge4);
       klyngeRepository.save(klynge5);
       klyngeRepository.save(klynge6);
       klyngeRepository.save(klynge7);
       klyngeRepository.save(klynge8);
       klyngeRepository.save(klynge9);
    }
    
}
