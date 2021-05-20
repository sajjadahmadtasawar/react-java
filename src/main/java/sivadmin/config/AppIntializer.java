package sivadmin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import sivadmin.models.Bruker;
import sivadmin.models.Prosjekt;
import sivadmin.models.ProsjektLeder;
import sivadmin.models.Skjema;
import sivadmin.repository.BrukerRepository;
import sivadmin.repository.ProsjektLederRepository;
import sivadmin.repository.ProsjektRepository;
import sivadmin.repository.SkjemaRepository;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

public class AppIntializer {
    @Autowired
    BrukerRepository brukerRepository;

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

        Bruker nyBruker = new Bruker("ibrahim", "ibr", "ibrahim@xala.no", passwordEncoder.encode("12345"));
        //String navn, String brukernavn, String epost, String passord
        brukerRepository.save(nyBruker);

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
}
