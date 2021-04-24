package no.ssb.sivadmin.enums

enum IntervjuerStatus {
    AKTIV("Aktiv"), SLUTTET("Sluttet"), OPPLAERING("Opplæring"), PERMISJON("Permisjon")

    private final String guiName;

    IntervjuerStatus(String guiName) {
        this.guiName = guiName
    }
}