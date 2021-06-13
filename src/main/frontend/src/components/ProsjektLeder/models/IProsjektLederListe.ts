import IProsjektLeder from "./IProsjektLeder";

export default interface IProsjektLederListe {
    objekter: IProsjektLeder[];
    antall: number;
    antallSider: number;
    side: number;
}