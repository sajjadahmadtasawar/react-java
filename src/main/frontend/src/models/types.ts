import IBruker from "models/IBruker";
import IProsjekt from "./Prosjekt/IProsjekt";

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