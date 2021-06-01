import React, { BaseSyntheticEvent, FC, useState } from "react";

import { Link, useHistory, useParams } from "react-router-dom";
import { toast } from "react-toastify";
import axiosInstance from "helpers/axiosInstance";
import UtvalgSkjema from "./Utvalg.Skjema";
import IUtvalg from "models/Utvalg/IUtvalg";
import { useQuery } from "react-query";
import DefaultUtvalg from "types/DefaultUtvalg";

interface RouteParams {
  id: string;
}

const UtvalgEndre: FC = () => {
  const params: RouteParams = useParams();
  const id = params.id;

  const [utvalg, setUtvalg] = useState(DefaultUtvalg);
  const [validert, setValidert] = useState(false);

  const history = useHistory();

  const { status, data, error, isFetching, refetch } = useQuery<IUtvalg, Error>(
    "utvalg",
    async () => {
      const { data } = await axiosInstance(history).get(
        `/utvalger/${params.id}`
      );
      setUtvalg({
        ...data,
        utvalgLeder_id:
          data.utvalgLeder.id && data.utvalgLeder ? data.utvalgLeder.id : 0,
      });
      return data;
    }
  );

  const haandterSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    const utvalg = e.currentTarget;
    e.preventDefault();
    e.stopPropagation();

    if (utvalg.checkValidity() === false) {
      setValidert(false);
    } else {
      try {
        await axiosInstance(history)
          .patch(`/utvalger/oppdater/${id}`, utvalg)
          .then(() => history.push("/utvalger"))
          .then(() => toast.success("Utvalg oppdatert !"));
      } catch (error) {
        toast.error(`${error.message}`);
      }
    }
    setValidert(true);
  };

  const haandterEndring = (e: BaseSyntheticEvent) => {
    const felt_navn = e.target.id as string;
    let vurdi = e.currentTarget.value as string;

    if (felt_navn === "delProduktNummer" && vurdi.length > 5) {
      vurdi = vurdi.slice(0, 5);
    }

    setUtvalg({ ...utvalg, [felt_navn]: vurdi });
  };

  return (
    <>
      <ol className="breadcrumb mb-4 mt-3">
        <li className="breadcrumb-item">
          <Link to="/utvalger">Utvalger</Link>
        </li>
        <li className="breadcrumb-item active">Endre Utvalg</li>
      </ol>

      <UtvalgSkjema
        utvalg={utvalg}
        validert={validert}
        haandterEndring={(event) => haandterEndring(event)}
        haandterSubmit={(event) => haandterSubmit(event)}
      />
    </>
  );
};

export default UtvalgEndre;
