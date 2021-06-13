import { useQuery } from "react-query";
import axiosInstance from "helpers/axiosInstance";
import IProsjektListe from "../models/IProsjektListe";
import IProsjektSok from "../models/IProsjektSok";
import { genererSok } from "helpers/utils";

const hentProsjekter = async (sok: IProsjektSok) => {
  const query = genererSok(sok);
  const { data } = await axiosInstance().get(`/prosjekter/liste${query}`);
  return data;
};

export default function useProsjekter(sok: IProsjektSok) {
  return useQuery<IProsjektListe>(["prosjektliste", sok], () => hentProsjekter(sok));
}