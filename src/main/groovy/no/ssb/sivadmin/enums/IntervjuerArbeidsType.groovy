package no.ssb.sivadmin.enums

enum IntervjuerArbeidsType {
    BESOK("Besøk"), TELEFON("Telefon"), BEGGE("Begge")

    private final String guiName;

    IntervjuerArbeidsType(String guiName) {
        this.guiName = guiName
    }
}