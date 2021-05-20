import React, { BaseSyntheticEvent, FC, Fragment, useState } from "react";
import { Accordion, Button, Card, Col, Form, Row } from "react-bootstrap";
import { Link, useHistory } from "react-router-dom";
import "./Skjema.css";
import { Delete } from "context/hooks/crud";
import { toast } from "react-toastify";
import { erAdmin } from "helpers/utils";
import Bekreftelse from "../Felles/Bekreftelse";
import ISkjema from "models/Skjema/ISkjema";
import moment from "moment";
import DefaultSkjema from "types/DefaultSkjema";

interface ExternalProps {
  objekt: ISkjema;
  refetch: () => void;
  objektNavn: string;
  apiURL: string;
  routeURL: string;
}

const Skjema: FC<ExternalProps> = ({
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
  const [valgt, setValgt] = useState<ISkjema>(DefaultSkjema);

  const endre = (objekt: ISkjema) => {
    history.push(`/${routeURL}/endre/${objekt.id}`);
  };

  const slett = (objekt: ISkjema) => {
    if (objekt.id && admin)
      Delete(objekt.id, objektNavn)
        .then(() => history.push(`/${routeURL}`))
        .then(() => toast.success("Slettet !"))
        .then(() => refetch())
        .catch((error: Error) => toast.error(error.message));
  };

  const bekreftSlett = (objekt: ISkjema) => {
    setValgt(objekt);
    setMelding(
      `Du skal slette ${objekt}: <span class="text-danger font-weight-bold">${objekt.skjemaNavn}</span>. Vennligst bekreft?`
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
          <Accordion.Toggle as={Card.Header} eventKey={objekt.skjemaNavn}>
            <Row>
              <Col sm="8">
                <h6 className="mt-2">{objekt.skjemaNavn}</h6>
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
          <Accordion.Collapse eventKey={objekt.skjemaNavn}>
            <Card.Body>
              <p className="py-2">{objekt.skjemaNavn}</p>
              <Row>
                <Col sm="4">
                  <p>
                    <span className="font-weight-bold">Skjekakortnavn: </span>
                    {objekt.skjemaKortNavn}
                  </p>
                  <p>
                    <span className="font-weight-bold">DelProdkutnummer: </span>
                    {objekt.delProduktNummer}
                  </p>

                  <p>
                    <span className="font-weight-bold">Status: </span>
                    {objekt.status}
                  </p>
                </Col>
                <Col sm="4">
                  <p>
                    <span className="font-weight-bold">Undersøkelsetype: </span>
                    {objekt.undersokelseType}
                  </p>
                  <p>
                    <span className="font-weight-bold">
                      OppstartDataInnsamling:{" "}
                    </span>
                    {moment(objekt.oppstartDataInnsamling).format("DD.MM.YYYY")}
                  </p>
                  <p>
                    <span className="font-weight-bold">
                      PlanlagtSluttDato:{" "}
                    </span>
                    {moment(objekt.planlagtSluttDato).format("DD.MM.YYYY")}
                  </p>
                </Col>
                <Col sm="4">
                  <p>
                    <span className="font-weight-bold">
                      AktivertForIntervjuing:{" "}
                    </span>
                    <input
                      type="checkbox"
                      checked={objekt.aktivertForIntervjuing}
                    />
                  </p>
                  <p>
                    <span className="font-weight-bold">KlarTilUtsending: </span>
                    <input type="checkbox" checked={objekt.klarTilUtsending} />
                  </p>
                  <p>
                    <span className="font-weight-bold">
                      KlarTilGenerering:{" "}
                    </span>
                    <input type="checkbox" checked={objekt.klarTilGenerering} />
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

export default Skjema;
