import React, { BaseSyntheticEvent, FC, useState } from "react";

import { Link, useHistory } from "react-router-dom";
import { toast } from "react-toastify";
import axiosInstance from "helpers/axiosInstance";
import SkjemaSkjema from "./Skjema.Skjema";
import DefaultSkjema from "types/DefaultSkjema";

const SkjemaOpprett: FC = () => {
  const [skjema, setSkjema] = useState(DefaultSkjema);
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
          .post(`/skjemaer/opprett`, skjema)
          .then(() => history.push("/skjemaer"))
          .then(() => toast.success("Skjema opprettet !"));
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

    setSkjema({ ...skjema, [felt_navn]: vurdi });
  };

  return (
    <>
      <ol className="breadcrumb mb-4 mt-3">
        <li className="breadcrumb-item">
          <Link to="/skjemaer">Skjemaer</Link>
        </li>
        <li className="breadcrumb-item active">Opprett nytt Skjema</li>
      </ol>

      <SkjemaSkjema
        skjema={skjema}
        validert={validert}
        haandterEndring={(event) => haandterEndring(event)}
        haandterSubmit={(event) => haandterSubmit(event)}
      />
    </>
  );
};

export default SkjemaOpprett;
