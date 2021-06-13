import { useQuery } from "react-query";
import axiosInstance from "helpers/axiosInstance";
import { genererSok } from "helpers/utils";
import IIntervjuObjektSok from "../models/IIntervjuObjektSok";
import IIntervjuObjektListe from "../models/IIntervjuObjektListe";

const hentIO = async (sok: IIntervjuObjektSok) => {
  const query = genererSok(sok);
  const { data } = await axiosInstance().get(`/intervjuObjekter/liste${query}`);
  return data;
};

export default function useIntervjuObjekter(sok: IIntervjuObjektSok) {
  return useQuery<IIntervjuObjektListe>(["intervjuObjekter", sok], () => hentIO(sok));
}