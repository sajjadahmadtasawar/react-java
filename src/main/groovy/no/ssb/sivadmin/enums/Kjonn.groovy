package no.ssb.sivadmin.enums

enum Kjonn {
    MANN("Mann"), KVINNE("Kvinne")

    private final String guiName;

    Kjonn(String guiName) {
        this.guiName = guiName
    }
}