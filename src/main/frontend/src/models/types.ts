import ProsjektFinansiering from "enums/ProsjektFinansiering";
import ProsjektModus from "enums/ProsjektModus";
import ProsjektStatus from "enums/ProsjektStatus";
import { aarene } from "helpers/utils";
import IBruker from "models/IBruker";
import IProsjekt from "./Prosjekt/IProsjekt";
import IProsjektSok from "./Prosjekt/IProsjektSok";
import IProsjektLeder from "./ProsjektLeder/IProsjektLeder";
import IProsjektLederSok from "./ProsjektLeder/IProsjektLederSok";

export const DefaultBruker: IBruker = {
    brukernavn: '',
    epost: ''
}

export const DefaultProsjektLeder: IProsjektLeder = {
    id: 0,
    navn: '',
    initialer: '',
    epost: ''
 }


export const DefaultProsjekt: IProsjekt = {
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
    panel: "false"
}

export const DefaultProsjektSok: IProsjektSok = {
    prosjektNavn: '',
    produktNummer: '',
    aargang: '',
    prosjektStatus: '',
    sort: 'id',
    asc: true,
    side: 1,
    visAntall: 10
 }

 export const DefaultProsjektLederSok: IProsjektLederSok = {
    navn: '',
    initialer: '',
    epost: '',
    sort: 'id',
    asc: true,
    side: 1,
    visAntall: 10
 }