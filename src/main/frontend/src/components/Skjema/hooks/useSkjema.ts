import { useQuery } from "react-query";
import axiosInstance from "helpers/axiosInstance";
import ISkjema from "components/Skjema/models/ISkjema";

const hentSkjema = async (id: Number, history: any = null) => {
    const { data: skjema } = await axiosInstance(history).get(`/api/skjemaer/${id}`);
    return skjema;
};

export default function useSkjema(id: Number) {
  return useQuery<ISkjema>(["skjema", id], () => hentSkjema(id));
}