import ISkjema from "./ISkjema";

export default interface ISkjemaListe {
    skjemaer: ISkjema[];
    antall: number;
    antallSider: number;
    side: number;
}