import ISkjema from "components/Skjema/models/ISkjema";

export default interface IUtvalg {
  id?: number;
  importDato: Date;
  antallFil: number;
  antallImportert: number;
  importertAv: string;
  melding: string;
  skjema: ISkjema;
  skjema_id: number;
}
