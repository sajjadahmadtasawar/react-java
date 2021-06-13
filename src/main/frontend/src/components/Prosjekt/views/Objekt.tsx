import React, { BaseSyntheticEvent, FC, Fragment, useState } from "react";
import { Accordion, Button, Card, Col, Form, Row } from "react-bootstrap";
import { Link, useHistory } from "react-router-dom";
import "../styles/Styles.css";
import { Delete } from "context/hooks/crud";
import { toast } from "react-toastify";
import { erAdmin } from "helpers/utils";
import Bekreftelse from "../../Felles/Bekreftelse";
import IProsjekt from "components/Prosjekt/models/IProsjekt";
import moment from "moment";
import DefaultProsjekt from "components/Prosjekt/types/DefaultProsjekt";
import DefaultSkjemaSok from "components/Skjema/types/DefaultSkjemaSok";
import ISkjemaSok from "components/Skjema/models/ISkjemaSok";
import useSkjemaer from "components/Skjema/hooks/useSkjemaer";
import ISkjema from "components/Skjema/models/ISkjema";

interface ExternalProps {
  objekt: IProsjekt;
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
  const [valgt, setValgt] = useState<IProsjekt>(DefaultProsjekt);
  const [skjemaSok, setSkjemaSok] = useState<ISkjemaSok>(DefaultSkjemaSok);

  const endre = (objekt: IProsjekt) => {
    history.push(`/${routeURL}/endre/${objekt.id}`);
  };

  const { data: skjemaListe } = useSkjemaer(skjemaSok);

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
                <Col sm="4">
                  <p>
                    <span className="font-weight-bold">Prodkutnummer: </span>
                    {objekt.produktNummer}
                  </p>
                  <p>
                    <span className="font-weight-bold">Registernummer: </span>
                    {objekt.registerNummer}
                  </p>
                  <p>
                    <span className="font-weight-bold">Modus: </span>
                    {objekt.modus}
                  </p>
                  <p>
                    <span className="font-weight-bold">Prosjektstatus: </span>
                    {objekt.prosjektStatus}
                  </p>
                </Col>
                <Col sm="4">
                  <p>
                    <span className="font-weight-bold">Årgang: </span>
                    {objekt.aargang}
                  </p>
                  <p>
                    <span className="font-weight-bold">Oppstartdato: </span>
                    {moment(objekt.oppstartDato).format("DD.MM.YYYY")}
                  </p>
                  <p>
                    <span className="font-weight-bold">Avslutningsdato: </span>
                    {moment(objekt.avslutningsDato).format("DD.MM.YYYY")}
                  </p>
                </Col>
                <Col sm="4">
                  <p>
                    <span className="font-weight-bold">Finansiering: </span>
                    {objekt.finansiering}
                  </p>
                  <p>
                    <span className="font-weight-bold">Stat: </span>
                    {objekt.prosentStat} %
                  </p>
                  <p>
                    <span className="font-weight-bold">Marked: </span>
                    {objekt.prosentMarked} %
                  </p>
                </Col>
              </Row>
              <Row>
                <Col sm="12" className="mt-3">
                  <Card>
                    <Card.Header>Prosjekt leder</Card.Header>
                    <Card.Body>
                      {objekt && objekt.prosjektLeder ? (
                        <table className="table table-bordered">
                          <thead>
                            <tr>
                              <th>Navn</th>
                              <th>Initialer</th>
                              <th>Epost</th>
                            </tr>
                          </thead>
                          <tbody>
                            <tr>
                              <td>{objekt.prosjektLeder.navn}</td>
                              <td>{objekt.prosjektLeder.initialer}</td>
                              <td>{objekt.prosjektLeder.epost}</td>
                            </tr>
                          </tbody>
                        </table>
                      ) : (
                        <span>Ingen Prosjektledere !</span>
                      )}
                    </Card.Body>
                  </Card>
                </Col>
              </Row>
              <Row>
                <Col sm="12" className="mt-3">
                  <Card>
                    <Card.Header>Skjemaer</Card.Header>
                    <Card.Body>
                      {objekt &&
                      objekt.skjemaer &&
                      objekt.skjemaer.length > 0 ? (
                        objekt.skjemaer.map((skjema: ISkjema) => (
                          <table className="table table-bordered">
                            <thead>
                              <tr>
                                <th>Skjemanavn</th>
                                <th>Skjemakortnavn</th>
                                <th>Delproduktnummer</th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr>
                                <td>{skjema.skjemaNavn}</td>
                                <td>{skjema.skjemaKortNavn}</td>
                                <td>{skjema.delProduktNummer}</td>
                              </tr>
                            </tbody>
                          </table>
                        ))
                      ) : (
                        <span>Ingen Skjemaer !</span>
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

export default Objekt;
