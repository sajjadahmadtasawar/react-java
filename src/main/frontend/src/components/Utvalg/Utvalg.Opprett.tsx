import React, { BaseSyntheticEvent, FC, useState } from "react";

import { Link, useHistory } from "react-router-dom";
import { toast } from "react-toastify";
import axiosInstance from "helpers/axiosInstance";
import UtvalgSkjema from "./Utvalg.Skjema";
import DefaultUtvalg from "types/DefaultUtvalg";

const UtvalgOpprett: FC = () => {
  const [utvalg, setUtvalg] = useState(DefaultUtvalg);
  const [validert, setValidert] = useState(false);

  const history = useHistory();

  const haandterSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    const utvalg = e.currentTarget;
    e.preventDefault();
    e.stopPropagation();

    if (utvalg.checkValidity() === false) {
      setValidert(false);
    } else {
      try {
        await axiosInstance(history)
          .post(`/utvalger/opprett`, utvalg)
          .then(() => history.push("/utvalger"))
          .then(() => toast.success("Utvalg opprettet !"));
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
        <li className="breadcrumb-item active">Opprett nytt Utvalg</li>
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

export default UtvalgOpprett;
