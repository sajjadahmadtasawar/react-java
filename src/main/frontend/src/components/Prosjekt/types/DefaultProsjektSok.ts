import IProsjektSok from "../models/IProsjektSok";

const DefaultProsjektSok: IProsjektSok = {
    prosjektNavn: '',
    produktNummer: '',
    aargang: '',
    prosjektStatus: '',
    sort: 'id',
    asc: true,
    side: 1,
    visAntall: 10
}

export default DefaultProsjektSok;