import { useQuery } from "react-query";
import axiosInstance from "helpers/axiosInstance";
import IProsjekt from "models/Prosjekt/IProsjekt";

const hentProsjekt = async (id: Number, history: any = null) => {
    const { data } = await axiosInstance(history).get(`/api/prosjekter/${id}`);
    return data;
};

export default function useProsjekt(id: Number) {
  return useQuery<IProsjekt>(["prosjekt", id], () => hentProsjekt(id));
}