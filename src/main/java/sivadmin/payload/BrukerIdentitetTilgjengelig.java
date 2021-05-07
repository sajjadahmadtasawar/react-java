package sivadmin.payload;

public class BrukerIdentitetTilgjengelig {
    private Boolean tilgjenglig;

    public BrukerIdentitetTilgjengelig(Boolean tilgjenglig) {
        this.tilgjenglig = tilgjenglig;
    }

    public Boolean hentTilgjenglig() {
        return tilgjenglig;
    }

    public void setTilgjenglig(Boolean tilgjenglig) {
        this.tilgjenglig = tilgjenglig;
    }
}
