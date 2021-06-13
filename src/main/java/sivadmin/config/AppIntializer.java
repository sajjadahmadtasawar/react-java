package sivadmin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import sivadmin.models.*;
import sivadmin.repository.*;

import javax.annotation.PostConstruct;
import java.util.*;

public class AppIntializer {
    @Autowired
    BrukerRepository brukerRepository;

    @Autowired
    RolleRepository rolleRepository;

    @Autowired
    ProsjektRepository prosjektRepository;

    @Autowired
    ProsjektLederRepository prosjektLederRepository;

    @Autowired
    SkjemaRepository skjemaRepository;

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
        }
       
        if(brukerRepository.count() == 0) {
            opprettAdminBruker(rolleAdmin);
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
        Optional<Bruker> imr = brukerRepository.findByBrukernavn("imr");

        if(si4 == null) {
            Bruker nyBruker = new Bruker("si4", "si4", "si4@ssb.no", passwordEncoder.encode("12345"));
            Set<Rolle> roller = new HashSet<>();
            roller.add(rolleAdmin);

            nyBruker.setRoller(roller);
            brukerRepository.save(nyBruker);
        }

        if(imr == null) {
            Bruker nyBruker = new Bruker("ibrahim", "imr", "ibrahim@xala.no", passwordEncoder.encode("12345"));
            Set<Rolle> roller = new HashSet<>();
            roller.add(rolleAdmin);

            nyBruker.setRoller(roller);
            brukerRepository.save(nyBruker);
        }

    }
}
