package no.ssb.sivadmin.enums

enum ProsjektFinansiering {
    STAT("Stat"), MARKED("Marked"), STAT_MARKED("Stat/marked")

    private final String guiName;

    ProsjektFinansiering(String guiName) {
        this.guiName = guiName
    }
}