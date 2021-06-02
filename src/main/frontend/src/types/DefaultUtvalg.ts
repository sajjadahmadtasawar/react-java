import IUtvalg from "models/Utvalg/IUtvalg";
import DefaultSkjema from "../components/Skjema/types/DefaultSkjema";

const DefaultUtvalg: IUtvalg = {
  importDato: new Date(),
  antallFil: 0,
  antallImportert: 0,
  importertAv: "",
  melding: "",
  skjema: DefaultSkjema,
  skjema_id: 0,
};

export default DefaultUtvalg;
