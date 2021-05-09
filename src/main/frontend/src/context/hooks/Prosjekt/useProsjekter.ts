import { useQuery } from "react-query";
import axiosInstance from "helpers/axiosInstance";
import IProsjekt from "models/Prosjekt/IProsjekt";

const hentProsjekter = async () => {
  const { data } = await axiosInstance().get("/prosjekter");
  return data;
};

export default function useProsjekter() {
  return useQuery<IProsjekt[]>("prosjekter", hentProsjekter);
}