import ProsjektFinansiering from "enums/ProsjektFinansiering";
import ProsjektModus from "enums/ProsjektModus";
import ProsjektStatus from "enums/ProsjektStatus";
import { aarene } from "helpers/utils";
import DefaultProsjektLeder from "./DefaultProsjektLeder";
import IProsjekt from "models/Prosjekt/IProsjekt";

const DefaultProsjekt: IProsjekt = {
    prosjektNavn: '',
    produktNummer: '',
    aargang: aarene[0].toString(),
    registerNummer: '',
    prosjektStatus: ProsjektStatus.Planlegging,
    modus: ProsjektModus.ENMODUS,
    finansiering: ProsjektFinansiering.STAT,
    prosentStat: 100,
    prosentMarked: 0,
    oppstartDato: new Date(),
    avslutningsDato: new Date(),
    prosjektLeder: DefaultProsjektLeder,
    prosjektLeder_id: 0,
    kommentar: '',
    panel: "false",
    skjemaer: []
}

export default DefaultProsjekt;