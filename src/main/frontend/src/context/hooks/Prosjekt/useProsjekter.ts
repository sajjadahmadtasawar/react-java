import { useQuery } from "react-query";
import axiosInstance from "helpers/axiosInstance";
import IProsjektListe from "models/Prosjekt/IProsjektListe";
import IProsjektSok from "models/Prosjekt/IProsjektSok";
import { genererSok } from "helpers/utils";

const hentProsjekter = async (sok: IProsjektSok) => {
  const query = genererSok(sok);
  console.log(query);
  const { data } = await axiosInstance().get(`/prosjekter/liste${query}`);
  return data;
};

export default function useProsjekter(sok: IProsjektSok) {
  return useQuery<IProsjektListe>(["prosjektliste", sok], () => hentProsjekter(sok));
}