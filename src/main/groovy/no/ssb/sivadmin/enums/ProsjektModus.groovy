package no.ssb.sivadmin.enums

enum ProsjektModus {
    ENMODUS("En"), MIXMODUS("Mix"), MULTIMODUS("Multi")

    private final String guiName;

    ProsjektModus(String guiName) {
        this.guiName = guiName
    }
}