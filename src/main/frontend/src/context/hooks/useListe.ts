import { useQuery } from "react-query";
import axiosInstance from "helpers/axiosInstance";
import IRespons from "models/IRespons";

const hentListe = async (uri: string) => {
  const { data } = await axiosInstance().get(`/${uri}`);
  return data;
};

export default function useListe(uri: string) {
  return useQuery<IRespons>(["respons", uri], () => hentListe(uri));
}