import IProsjekt from "./IProsjekt";

export default interface IProsjektListe {
    prosjekter: IProsjekt[];
    antall: number;
    antallSider: number;
    side: number;
}