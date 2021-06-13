import React, { BaseSyntheticEvent, FC, Fragment, useState } from "react";
import { Accordion, Button, Card, Col, Form, Row } from "react-bootstrap";
import { Link, useHistory } from "react-router-dom";
import "../styles/Styles.css";
import { Delete } from "context/hooks/crud";
import { toast } from "react-toastify";
import { erAdmin } from "helpers/utils";
import Bekreftelse from "../../Felles/Bekreftelse";
import moment from "moment";
import IIntervjuObjekt from "../models/IIntervjuObjekt";
import DefaultIntervjuObjekt from "../types/DefaultIntervjuObjekt";

interface ExternalProps {
  objekt: IIntervjuObjekt;
  refetch: () => void;
  objektNavn: string;
  apiURL: string;
  routeURL: string;
}

const IntervjuObjekt: FC<ExternalProps> = ({
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
  const [valgt, setValgt] = useState<IIntervjuObjekt>(DefaultIntervjuObjekt);

  const endre = (objekt: IIntervjuObjekt) => {
    history.push(`/${routeURL}/endre/${objekt.id}`);
  };

  const slett = (objekt: IIntervjuObjekt) => {
    if (objekt.id && admin)
      Delete(objekt.id, objektNavn)
        .then(() => history.push(`/${routeURL}`))
        .then(() => toast.success("Slettet !"))
        .then(() => refetch())
        .catch((error: Error) => toast.error(error.message));
  };

  const bekreftSlett = (objekt: IIntervjuObjekt) => {
    setValgt(objekt);
    setMelding(
      `Du skal slette ${objekt}: <span class="text-danger font-weight-bold">${objekt}</span>. Vennligst bekreft?`
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
          <Accordion.Toggle as={Card.Header} eventKey={objekt.intervjuObjektId}>
            <Row>
              <Col sm="8">
                <h6 className="mt-2">{objekt.intervjuObjektId}</h6>
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
          <Accordion.Collapse eventKey={objekt.intervjuObjektId}>
            <Card.Body>
              <p className="py-2">{objekt.intervjuObjektId}</p>
              <Row>
                <Col sm="4">
                  <p>
                    <span className="font-weight-bold">io nummer: </span>
                    {objekt.intervjuObjektNummer}
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

export default IntervjuObjekt;
