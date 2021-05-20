import IProsjektLeder from "models/ProsjektLeder/IProsjektLeder";
import ISkjema from "models/Skjema/ISkjema";

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
    panel: string;
    oppstartDato: Date;
    avslutningsDato: Date;
    kommentar: string;
    prosjektLeder_id?: number;
    prosjektLeder: IProsjektLeder;
    skjemaer: ISkjema[];
}