export default interface IIntervjuObjektSok {
    intervjuObjektNummer?: string;
    intervjuObjektId?: number;
    navn: string;
    telefon: string;
    skjema_id?: number;
    interjuStatus: string;
    skjemaStatus: string;
    intervjuStatusEndretFra: string; 
    intervjuStatusEndretTil: string;
    avtaleType: string;
    kjonn: string;
    adresse: string;
    husnr: string;
    postnummer: string;
    poststed: string;
    kommunenummer: string;
    bolignummer: string;
    fodselsnummer: string;
    familienummer: string;
    alder: string;
    epost: string;
    sort: string;
    asc: boolean;
    side: number;
    visAntall: number;
}