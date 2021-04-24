package no.ssb.sivadmin.enums

enum UndersokelsesType {
    PERSON("Person"), BEDRIFT("Bedrift"), HUSHOLDNING("Husholdning"), ADRESSE("Adresse")

    private final String guiName;

    UndersokelsesType(String guiName) {
        this.guiName = guiName
    }
}