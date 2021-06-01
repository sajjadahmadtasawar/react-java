import IUtvalg from "./IUtvalg";

export default interface IUtvalgListe {
  utvalger: IUtvalg[];
  antall: number;
  antallSider: number;
  side: number;
}
