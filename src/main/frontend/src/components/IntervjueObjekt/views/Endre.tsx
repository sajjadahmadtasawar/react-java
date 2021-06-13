import React, { BaseSyntheticEvent, FC, useState } from "react";

import { Link, useHistory, useParams } from "react-router-dom";
import { toast } from "react-toastify";
import axiosInstance from "helpers/axiosInstance";
import ProsjektSkjema from "./Skjema";
import IProsjekt from "components/Prosjekt/models/IProsjekt";
import { useQuery } from "react-query";
import DefaultProsjekt from "components/Prosjekt/types/DefaultProsjekt";

interface RouteParams {
  id: string;
}

const IntervjueObjektEndre: FC = () => {
  const params: RouteParams = useParams();
  const id = params.id;

  const [prosjekt, setProsjekt] = useState(DefaultProsjekt);
  const [validert, setValidert] = useState(false);

  const history = useHistory();

  const { status, data, error, isFetching, refetch } = useQuery<
    IProsjekt,
    Error
  >("prosjekt", async () => {
    const { data } = await axiosInstance(history).get(
      `/prosjekter/${params.id}`
    );
    setProsjekt({
      ...data,
      prosjektLeder_id:
        data.prosjektLeder.id && data.prosjektLeder ? data.prosjektLeder.id : 0,
    });
    return data;
  });

  const haandterSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    const skjema = e.currentTarget;
    e.preventDefault();
    e.stopPropagation();

    if (skjema.checkValidity() === false) {
      setValidert(false);
    } else {
      try {
        await axiosInstance(history)
          .patch(`/prosjekter/oppdater/${id}`, prosjekt)
          .then(() => history.push("/prosjekter"))
          .then(() => toast.success("Prosjekt oppdatert !"));
      } catch (error) {
        toast.error(`${error.message}`);
      }
    }
    setValidert(true);
  };

  const haandterEndring = (e: BaseSyntheticEvent) => {
    const felt_navn = e.target.id as string;
    let vurdi = e.currentTarget.value as string;

    if (felt_navn === "panel") {
      vurdi = vurdi === "on" ? "true" : "false";
    }

    if (felt_navn === "prosjektLeder") {
      setProsjekt({
        ...prosjekt,
        prosjektLeder_id: Number(vurdi),
      });
    }

    if (felt_navn === "finansiering" && vurdi === "STAT") {
      setProsjekt({
        ...prosjekt,
        finansiering: vurdi,
        prosentStat: 100,
        prosentMarked: 0,
      });
    } else if (felt_navn === "finansiering" && vurdi === "MARKED") {
      setProsjekt({
        ...prosjekt,
        finansiering: vurdi,
        prosentStat: 0,
        prosentMarked: 100,
      });
    } else if (felt_navn === "finansiering" && vurdi === "STAT_MARKED") {
      setProsjekt({
        ...prosjekt,
        finansiering: vurdi,
        prosentStat: 0,
        prosentMarked: 0,
      });
    } else {
      setProsjekt({ ...prosjekt, [felt_navn]: vurdi });
    }
  };

  return (
    <>
      <ol className="breadcrumb mb-4 mt-3">
        <li className="breadcrumb-item">
          <Link to="/prosjekter">Prosjekter</Link>
        </li>
        <li className="breadcrumb-item active">Endre Prosjekt</li>
      </ol>

      <ProsjektSkjema
        prosjekt={prosjekt}
        validert={validert}
        haandterEndring={(event) => haandterEndring(event)}
        haandterSubmit={(event) => haandterSubmit(event)}
      />
    </>
  );
};

export default IntervjueObjektEndre;
