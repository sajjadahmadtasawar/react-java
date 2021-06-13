import IIntervjuObjekt from "./IIntervjuObjekt";

export default interface IIntervjuObjektListe {
    intervjuObjekter: IIntervjuObjekt[];
    antall: number;
    antallSider: number;
    side: number;
}