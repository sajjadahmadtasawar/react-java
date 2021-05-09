import IProsjektLeder from "./IProsjektLeder";

export default interface IProsjekt {
    id?: number;
    prosjektNavn: string;
    produktNummer: string;
    aargang: string;
    registerNummer: string;
    prosjektStatus: string;
    modus: string;
    finansiering: string;
    prosentStat: number;
    prosentMarked: number;
    panel?: boolean;
    oppstartDato: string;
    avslutningsDato: string;
    kommentar: string;
    prosjektLeder?: IProsjektLeder[];
}