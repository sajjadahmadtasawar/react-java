import { useQuery } from "react-query";
import axiosInstance from "helpers/axiosInstance";
import { genererSok } from "helpers/utils";
import IProsjektLederListe from "models/ProsjektLeder/IProsjektLederListe";
import IProsjektLederSok from "models/ProsjektLeder/IProsjektLederSok";

const hentProsjektLedere = async (sok: IProsjektLederSok) => {
  const query = genererSok(sok);
  const { data } = await axiosInstance().get(`/prosjekt_ledere/liste${query}`);
  return data;
};

export default function useProsjektLedereSok(sok: IProsjektLederSok) {
  return useQuery<IProsjektLederListe>(["objekter", sok], () => hentProsjektLedere(sok));
}