package no.ssb.sivadmin.enums

enum ProsjektStatus {
    PLANLEGGING("Planlegging"), TEST("Test"), AKTIV("Aktiv"), AVSLUTTET("Avsluttet")

    private final String guiName;

    ProsjektStatus(String guiName) {
        this.guiName = guiName
    }
}