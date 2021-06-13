import IIntervjuObjektSok from "../models/IIntervjuObjektSok";

const DefaultIntervjuObjektSok: IIntervjuObjektSok = {
    intervjuObjektNummer: '',
    navn: '',
    telefon: '',
    interjuStatus: '',
    skjemaStatus: '',
    intervjuStatusEndretFra: '',
    intervjuStatusEndretTil: '',
    avtaleType: '',
    kjonn: '',
    adresse: '',
    husnr: '',
    postnummer: '',
    poststed: '',
    kommunenummer: '',
    bolignummer: '',
    fodselsnummer: '',
    familienummer: '',
    alder: '',
    epost: '',
    sort: 'id',
    asc: true,
    side: 1,
    visAntall: 10
}

export default DefaultIntervjuObjektSok;