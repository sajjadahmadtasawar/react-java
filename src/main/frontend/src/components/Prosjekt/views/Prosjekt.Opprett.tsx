import React, { BaseSyntheticEvent, FC, useState } from "react";

import { Link, useHistory } from "react-router-dom";
import { toast } from "react-toastify";
import axiosInstance from "helpers/axiosInstance";
import ProsjektSkjema from "./Prosjekt.Skjema";
import DefaultProsjekt from "components/Prosjekt/types/DefaultProsjekt";

const ProsjektOpprett: FC = () => {
  const [prosjekt, setProsjekt] = useState(DefaultProsjekt);
  const [validert, setValidert] = useState(false);

  const history = useHistory();

  const haandterSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    const skjema = e.currentTarget;
    e.preventDefault();
    e.stopPropagation();

    if (skjema.checkValidity() === false) {
      setValidert(false);
    } else {
      try {
        await axiosInstance(history)
          .post(`/prosjekter/opprett`, prosjekt)
          .then(() => history.push("/prosjekter"))
          .then(() => toast.success("Prosjekt opprettet !"));
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

    if (felt_navn === "produktNummer" && vurdi.length > 5) {
      vurdi = vurdi.slice(0, 5);
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
        <li className="breadcrumb-item active">Opprett nytt Prosjekt</li>
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

export default ProsjektOpprett;
