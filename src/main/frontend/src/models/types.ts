import IBruker from "models/IBruker";
import IProsjekt from "./Prosjekt/IProsjekt";
import IProsjektSok from "./Prosjekt/IProsjektSok";

export const DefaultBruker: IBruker = {
    brukernavn: '',
    epost: ''
}


export const DefaultProsjekt: IProsjekt = {
    prosjektNavn: '',
    produktNummer: '',
    aargang: '',
    registerNummer: '',
    prosjektStatus: '',
    modus: '',
    finansiering: '',
    prosentStat: 0,
    prosentMarked: 0,
    oppstartDato: '',
    avslutningsDato: '',
    kommentar: ''
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