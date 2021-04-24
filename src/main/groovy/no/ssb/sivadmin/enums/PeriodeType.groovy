package no.ssb.sivadmin.enums

enum PeriodeType {
    AAR("År"), KVRT("Kvartal"), MND("Måned"), UKE("Uke"), TILF("Tilfeldig")

    private final String guiName;

    PeriodeType(String guiName) {
        this.guiName = guiName
    }
}