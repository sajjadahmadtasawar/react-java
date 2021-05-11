import React, { BaseSyntheticEvent, FC, FormEvent, useState } from "react";

import { DefaultProsjekt } from "models/types";

import { Button, Card, Col, Form, Row } from "react-bootstrap";
import { useQuery } from "react-query";
import { Link, useHistory } from "react-router-dom";
import { toast } from "react-toastify";
import axiosInstance from "helpers/axiosInstance";
import ProsjektSkjema from "./Prosjekt.Skjema";

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
          .post(`/api/prosjekter/`, prosjekt)
          .then(() => history.push("/prosjekter"))
          .then(() => toast.success("Prosjekt opprettet !"));
      } catch (error) {
        return error;
      }
    }
    setValidert(true);
  };

  const haandterEndring = (e: BaseSyntheticEvent) => {
    const felt_navn = e.target.id as string;
    const vurdi = e.currentTarget.value as string;

    setProsjekt({ ...prosjekt, [felt_navn]: vurdi });
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
