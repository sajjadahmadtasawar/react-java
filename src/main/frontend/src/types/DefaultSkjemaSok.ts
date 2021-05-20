import ISkjemaSok from "models/Skjema/ISkjemaSok";

const DefaultSkjemaSok: ISkjemaSok = {
    skjemaNavn: '',
    skjemaKortNavn: '',
    delProduktNummer: '',
    undersokelseType: '',
    status: '',
    sort: 'id',
    asc: true,
    side: 1,
    visAntall: 10
}

export default DefaultSkjemaSok;