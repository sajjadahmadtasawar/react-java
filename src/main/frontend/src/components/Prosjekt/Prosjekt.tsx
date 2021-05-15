import React, { BaseSyntheticEvent, FC, Fragment, useState } from "react";
import { Accordion, Button, Card, Col, Form, Row } from "react-bootstrap";
import { Link, useHistory } from "react-router-dom";
import "./Prosjekt.css";
import { Delete } from "context/hooks/crud";
import { toast } from "react-toastify";
import { DefaultProsjekt } from "models/types";
import { erAdmin } from "helpers/utils";
import Bekreftelse from "../Felles/Bekreftelse";
import IProsjekt from "models/Prosjekt/IProsjekt";

interface ExternalProps {
  objekt: IProsjekt;
  refetch: () => void;
  objektNavn: string;
  apiURL: string;
  routeURL: string;
}

const Prosjekt: FC<ExternalProps> = ({
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
  const [valgt, setValgt] = useState<IProsjekt>(DefaultProsjekt);

  const endre = (objekt: IProsjekt) => {
    history.push(`/${routeURL}/endre/${objekt.id}`);
  };

  const slett = (objekt: IProsjekt) => {
    if (objekt.id && admin)
      Delete(objekt.id, objektNavn)
        .then(() => history.push(`/${routeURL}`))
        .then(() => toast.success("Slettet !"))
        .then(() => refetch())
        .catch((error: Error) => toast.error(error.message));
  };

  const bekreftSlett = (objekt: IProsjekt) => {
    setValgt(objekt);
    setMelding(
      `Du skal slette ${objekt}: <span class="text-danger font-weight-bold">${objekt.prosjektNavn}</span>. Vennligst bekreft?`
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
          <Accordion.Toggle as={Card.Header} eventKey={objekt.prosjektNavn}>
            <Row>
              <Col sm="8">
                <h6 className="mt-2">{objekt.prosjektNavn}</h6>
              </Col>
              <Col sm="4" className="ml-auto">
                <div className="d-flex">
                  <Button
                    onClick={() => visSkjemaer()}
                    className="btn btn-sm btn-info"
                  >
                    <i className="fas fa-meteor"></i> Skjemaer
                  </Button>
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
          <Accordion.Collapse eventKey={objekt.prosjektNavn}>
            <Card.Body>
              <p className="py-2">{objekt.prosjektNavn}</p>
              <Row>
                <Col sm="5">
                  <p>
                    <span className="font-weight-bold">Prodkutnummer: </span>
                    {objekt.produktNummer}
                  </p>
                  <p>
                    <span className="font-weight-bold">Årgang:</span>
                    {objekt.aargang}
                  </p>
                </Col>
                <Col sm="4">
                  <p>
                    <span className="font-weight-bold">Oppstartdato: </span>
                    {objekt.oppstartDato}
                  </p>
                  <p>
                    <span className="font-weight-bold">Avslutningsdato: </span>
                    {objekt.avslutningsDato}
                  </p>
                </Col>
              </Row>

              <Row>
                <Col sm="12" className="mt-3">
                  <Card>
                    <Card.Header>Prosjekt ledere</Card.Header>
                    <Card.Body>
                      {objekt && objekt.prosjektLeder ? (
                        <span>Ingen Prosjektledere !</span>
                      ) : (
                        "ldere"
                      )}
                    </Card.Body>
                  </Card>
                </Col>
              </Row>
            </Card.Body>
          </Accordion.Collapse>
        </Card>
      </Accordion>
    </Fragment>
  );
};

export default Prosjekt;
