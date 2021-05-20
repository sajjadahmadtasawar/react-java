import { useQuery } from "react-query";
import axiosInstance from "helpers/axiosInstance";
import ISkjemaSok from "models/Skjema/ISkjemaSok";
import { genererSok } from "helpers/utils";
import ISkjemaListe from "models/Skjema/ISkjematListe";

const hentSkjemaer = async (sok: ISkjemaSok) => {
  const query = genererSok(sok);
  const { data } = await axiosInstance().get(`/skjemaer/liste${query}`);
  return data;
};

export default function useSkjemaer(sok: ISkjemaSok) {
  return useQuery<ISkjemaListe>(["skjemaliste", sok], () => hentSkjemaer(sok));
}