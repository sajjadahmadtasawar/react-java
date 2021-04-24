package no.ssb.sivadmin.enums

enum Kilde {
    CATI("CATI"),
    CAPI("CAPI"),
    WEB("WEB")

    private final String guiName;

    Kilde(String guiName) {
        this.guiName = guiName
    }
}