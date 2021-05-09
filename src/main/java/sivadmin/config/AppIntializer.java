package sivadmin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import sivadmin.models.Bruker;
import sivadmin.models.Prosjekt;
import sivadmin.models.ProsjektLeder;
import sivadmin.repository.BrukerRepository;
import sivadmin.repository.ProsjektLederRepository;
import sivadmin.repository.ProsjektRepository;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class AppIntializer {
    @Autowired
    BrukerRepository brukerRepository;

    @Autowired
    ProsjektRepository prosjektRepository;

    @Autowired
    ProsjektLederRepository prosjektLederRepository;

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

        // Lag test prosjekter
        for (int i = 0; i < 10; i++) {
            Calendar calen = Calendar.getInstance();
            calen.add(Calendar.DAY_OF_YEAR, 60 + i);

            ProsjektLeder prosjektLeder1 = prosjektLederRepository.findFirstByOrderByIdAsc();

            Prosjekt prosjekt = new Prosjekt();
            prosjekt.setPanel(true);
            prosjekt.setProduktNummer("0549" + i);
            prosjekt.setModus("En");
            prosjekt.setRegisterNummer("012" + i);

            prosjekt.setProsjektNavn("Levekaar" + i);
            prosjekt.setAargang("201" + i);
            prosjekt.setProsjektLeder(prosjektLeder1);
            prosjekt.setOppstartDato(new Date());
            prosjekt.setAvslutningsDato(calen.getTime());
            prosjekt.setProsjektStatus("Aktiv");
            prosjekt.setFinansiering("Stat");
            prosjekt.setProsentStat(100L);
            prosjekt.setProsentMarked(0L);
            prosjekt.setKommentar("ingen");


            prosjektRepository.save(prosjekt);
        }

        prosjektLederRepository.save(prosjektLeder);


    }
}
