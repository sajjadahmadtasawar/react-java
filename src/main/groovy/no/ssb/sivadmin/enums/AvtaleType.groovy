package no.ssb.sivadmin.enums

enum AvtaleType {
    INGEN("Ingen"),
    HARD("Hard"),
    LOS("Løs"),
    HAR_HATT_HARD("Har hatt hard"),
    HAR_HATT_LOS("Har hatt løs")

    private final String guiName;

    AvtaleType(String guiName) {
        this.guiName = guiName
    }
}