package no.ssb.sivadmin.models

import no.ssb.sivadmin.enums.Kilde
import no.ssb.sivadmin.enums.Kjonn
import no.ssb.sivadmin.enums.SkjemaStatus

import javax.persistence.*

@Entity
class IntervjuObjekt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @ManyToOne
    @JoinColumn(name = "INTERVJUE_OBJEKT_ID", referencedColumnName = "ID")
    Periode periode

    @OneToOne(optional=false)
    @JoinColumn(name = "INTERVJUER_ID", referencedColumnName = "ID")
    Intervjuer onsketIntervjuer

    @OneToOne(optional=false)
    @JoinColumn(name = "UTVALG_IMPORT_ID", referencedColumnName = "ID")
    UtvalgImport utvalgImport

    @OneToOne(optional=false)
    @JoinColumn(name = "AVTALE_ID", referencedColumnName = "ID")
    Avtale avtale

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "HUSHOLDNING_ID", referencedColumnName = "ID")
    List<Husholdning> husholdninger

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "HISTORIE_ID", referencedColumnName = "ID")
    List<Historie> historier

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESSE_ID", referencedColumnName = "ID")
    List<Adresse> adresser

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "TELEFON_ID", referencedColumnName = "ID")
    List<Telefon> telefoner

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "STAT_HIST_ID", referencedColumnName = "ID")
    List<StatHist> statusHistorikk

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "TILDELT_HIST_ID", referencedColumnName = "ID")
    List<TildeltHist> tildeltHistorikk

    @Enumerated(EnumType.STRING)
    SkjemaStatus katSkjemaStatus

    @Enumerated(EnumType.STRING)
    Kilde kilde

    @Enumerated(EnumType.STRING)
    Kjonn kjonn

    String intervjuObjektNummer
    String kontaktPersonFodselsNummer
    String delutvalg
    String navn
    String sivilstand
    String familienummer
    String statsborgerskap
    String personKode
    String referansePerson
    String organisasjonsNummer
    String kontaktperiode
    String meldingTilIntervjuer
    String statusKommentar
    Boolean onskerKontaktMedProsjektleder
    Boolean onskerResultatEpost
    Boolean harLestBrev
    Long fullforingsGrad
    Long fullforingsStatus
    Integer intervjuStatus
    String internStatus
    Integer dayBatchKode
    Boolean nekterBrevSendt
    Date postaltTilleggMottatt
    Long antallTilleggMottatt
    Boolean ferdig
    Date ferdigDato
    Date paVentDato
    String fodselsNummer
    Boolean parkert
    Date parkertDato
    String kommentar
    Long alder
    String utvalgsOmraade
    String utvalgAvtaleDato
    String overIoNummer
    String epost
    Boolean reservasjon
    String passordWeb
    String redigertAv
    Date redigertDato
    String intervjuer
    String laastAv
    Date laastTidspunkt
    String maalform
    String varslingsstatus
}
