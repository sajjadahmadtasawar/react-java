import { useQuery } from "react-query";
import axiosInstance from "helpers/axiosInstance";
import { genererSok } from "helpers/utils";
import IUtvalgListe from "models/Utvalg/IUtvalgListe";
import IUtvalgSok from "models/Utvalg/IUtvalgSok";

const hentUtvalger = async (sok: IUtvalgSok) => {
  const query = genererSok(sok);
  const { data } = await axiosInstance().get(`/utvalger/liste${query}`);
  return data;
};

export default function useUtvalger(sok: IUtvalgSok) {
  return useQuery<IUtvalgListe>(["utvalgListe", sok], () => hentUtvalger(sok));
}
