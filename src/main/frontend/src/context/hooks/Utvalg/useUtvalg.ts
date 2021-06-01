import { useQuery } from "react-query";
import axiosInstance from "helpers/axiosInstance";
import IUtvalg from "models/Utvalg/IUtvalg";

const hentUtvalg = async (id: Number, history: any = null) => {
  const { data: utvalg } = await axiosInstance(history).get(
    `/api/utvalger/${id}`
  );
  return utvalg;
};

export default function useUtvalg(id: Number) {
  return useQuery<IUtvalg>(["utvalg", id], () => hentUtvalg(id));
}
