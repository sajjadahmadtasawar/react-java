package no.ssb.sivadmin.enums

enum AdresseType {
    BESOK("Besøk"), POST("Post");

    private final String guiName;

    AdresseType(String guiName) {
        this.guiName = guiName
    }
}