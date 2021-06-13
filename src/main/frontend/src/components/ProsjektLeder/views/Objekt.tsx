import React, { BaseSyntheticEvent, FC, Fragment, useState } from "react";
import { Accordion, Button, Card, Col, Form, Row } from "react-bootstrap";
import { Link, useHistory } from "react-router-dom";
import "../styles/Styles.css";
import { Delete } from "context/hooks/crud";
import { toast } from "react-toastify";
import { erAdmin } from "helpers/utils";
import Bekreftelse from "../../Felles/Bekreftelse";
import IProsjektLeder from "components/ProsjektLeder/models/IProsjektLeder";
import moment from "moment";
import DefaultProsjektLeder from "components/ProsjektLeder/types/DefaultProsjektLeder";

interface ExternalProps {
  objekt: IProsjektLeder;
  refetch: () => void;
  objektNavn: string;
  apiURL: string;
  routeURL: string;
}

const Objekt: FC<ExternalProps> = ({
  objekt,
  refetch,
  objektNavn,
  routeURL,
}) => {
  const history = useHistory();

  // bekreftelse
  const [visBekreftelse, setVisBekreftelse] = useState(false);
  const [tittel, setTittel] = useState("Slett!");
  const [melding, setMelding] = useState("");

  const [admin, setAdmin] = useState(erAdmin());
  const [valgt, setValgt] = useState<IProsjektLeder>(DefaultProsjektLeder);

  const endre = (objekt: IProsjektLeder) => {
    history.push(`/${routeURL}/endre/${objekt.id}`);
  };

  const slett = (objekt: IProsjektLeder) => {
    if (objekt.id && admin)
      Delete(objekt.id, objektNavn)
        .then(() => history.push(`/${routeURL}`))
        .then(() => toast.success("Slettet !"))
        .then(() => refetch())
        .catch((error: Error) => toast.error(error.message));
  };

  const bekreftSlett = (objekt: IProsjektLeder) => {
    setValgt(objekt);
    setMelding(
      `Du skal slette ${objekt}: <span class="text-danger font-weight-bold">${objekt.navn}</span>. Vennligst bekreft?`
    );
    setVisBekreftelse(true);
  };

  const bekreft = () => {
    setVisBekreftelse(false);
    slett(valgt);
    refetch();
  };

  const avbryt = () => {
    setVisBekreftelse(false);
  };

  const visSkjemaer = () => {};

  return (
    <Fragment>
      <Bekreftelse
        tittel={tittel}
        melding={melding}
        visBekreftelse={visBekreftelse}
        bekreft={bekreft}
        avbryt={avbryt}
      />

      <Accordion className="mb-2">
        <Card>
          <Accordion.Toggle as={Card.Header} eventKey={objekt.navn}>
            <Row>
              <Col sm="8">
                <h6 className="mt-2">{objekt.navn}</h6>
              </Col>
              <Col sm="4" className="ml-auto">
                <div className="d-flex">
                  <Button
                    onClick={() => endre(objekt)}
                    className="btn btn-sm btn-primary ml-4"
                  >
                    <i className="fas fa-edit"></i> Endre
                  </Button>
                  <Button
                    onClick={() => bekreftSlett(objekt)}
                    className="btn btn-sm btn-danger ml-2"
                    disabled={!admin}
                    title={
                      !admin
                        ? "Du har ikke tilgang til denne funksjonen !"
                        : `Slett ${objektNavn}`
                    }
                  >
                    <i className="fas fa-trash"></i> Fjern
                  </Button>
                </div>
              </Col>
            </Row>
          </Accordion.Toggle>
          <Accordion.Collapse eventKey={objekt.navn}>
            <Card.Body>
              <p className="py-2">{objekt.navn}</p>
              <Row>
                <Col sm="4">
                  <p>
                    <span className="font-weight-bold">Initialer: </span>
                    {objekt.initialer}
                  </p>
                  <p>
                    <span className="font-weight-bold">Epost: </span>
                    {objekt.epost}
                  </p>
                </Col>
              </Row>
            </Card.Body>
          </Accordion.Collapse>
        </Card>
      </Accordion>
    </Fragment>
  );
};

export default Objekt;
