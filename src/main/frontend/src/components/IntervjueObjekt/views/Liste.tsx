import React, { BaseSyntheticEvent, FC, useState } from "react";
import Spinner from "react-bootstrap/esm/Spinner";
import { useHistory } from "react-router-dom";
import { Button, Card, Col, Form, Row } from "react-bootstrap";
import IntervjuObjekt from "./Objekt";
import { EnumArray } from "helpers/utils";
import PaginationControll from "components/Felles/PaginationControll";
import IIntervjuObjektSok from "../models/IIntervjuObjektSok";
import DefaultIntervjuObjektSok from "../types/DefaultIntervjuObjektSok";
import useIntervjuObjekter from "../hooks/useIntervjuObjekter";
import IIntervjuObjekt from "../models/IIntervjuObjekt";
import useSkjemaer from "components/Skjema/hooks/useSkjemaer";
import DefaultSkjemaSok from "components/Skjema/types/DefaultSkjemaSok";
import ISkjemaSok from "components/Skjema/models/ISkjemaSok";
import ISkjema from "components/Skjema/models/ISkjema";
import SkjemaStatus from "components/Skjema/enums/SkjemaStatus";
import IntervjuStatus from "../enums/IntervjuStatus";

import moment from "moment";
import AvtaleType from "../enums/AvtaleType";
import Kjonn from "../enums/Kjonn";

const IntervjuObjektListe: FC = () => {
  const [sok, setSok] = useState<IIntervjuObjektSok>(DefaultIntervjuObjektSok);
  const [skjemaSok, setSkjemaSok] = useState<ISkjemaSok>(DefaultSkjemaSok);

  const [avansertSok, setAvansertSok] = useState(false);

  const history = useHistory();
  const { data, isFetching, refetch } = useIntervjuObjekter(sok);

  const { data: skjemaListe } = useSkjemaer(skjemaSok);

  const haandterSok = (e: BaseSyntheticEvent) => {
    const name = e.currentTarget.name as string;
    const value = e.currentTarget.value as string;

    setSok({ ...sok, [name]: value, side: 1 });
  };

  const sokIntervjuObjekt = () => {
    refetch();
  };

  const finnesData: boolean =
    data && data.intervjuObjekter && data.intervjuObjekter.length > 0
      ? true
      : false;

  const setSide = (e: BaseSyntheticEvent) => {
    const side = e.target.getAttribute("data-text");
    if (side) {
      setSok({ ...sok, side: side });
      refetch();
    }
  };

  return (
    <>
      <Card className="mt-3">
        <Card.Header>
          <Row>
            <Col sm="10">
              <div className="mt-1">
                <span className="font-weight-bolder text-uppercase">
                  IntervjuObjektsøk
                </span>
              </div>
            </Col>
          </Row>
        </Card.Header>
        <Card.Body>
          <Row className="mx-0">
            <Col sm="3">
              <Form.Group as={Row} controlId="navn">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.navn}
                    name="navn"
                    onChange={haandterSok}
                    placeholder="Navn"
                  />
                  <Form.Text className="text-muted">
                    Søk etter IO Navn
                  </Form.Text>
                </Col>
              </Form.Group>
            </Col>

            <Col sm="3">
              <Form.Group as={Row} controlId="telefon">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.telefon}
                    name="telefon"
                    onChange={haandterSok}
                    placeholder="Telefon"
                  />
                  <Form.Text className="text-muted">
                    Søk etter IO Telefon
                  </Form.Text>
                </Col>
              </Form.Group>
            </Col>

            <Col sm="2">
              <Form.Group as={Row} controlId="skjema_id">
                <Col>
                  <Form.Control
                    as="select"
                    value={sok.skjema_id}
                    name="skjema_id"
                    onChange={haandterSok}
                  >
                    <option key="0" value="0">
                      Skjema
                    </option>
                    {skjemaListe &&
                      skjemaListe.skjemaer &&
                      skjemaListe.skjemaer.length > 0 &&
                      skjemaListe.skjemaer.map((skjema: ISkjema) => (
                        <option key={skjema.id} value={skjema.id}>
                          {skjema.skjemaKortNavn}
                        </option>
                      ))}
                  </Form.Control>
                  <Form.Text className="text-muted">Søk etter Skjema</Form.Text>
                </Col>
              </Form.Group>
            </Col>

            <Col sm="2">
              <Form.Group as={Row} controlId="skjemaStatus">
                <Col>
                  <Form.Control
                    as="select"
                    value={sok.skjemaStatus}
                    name="skjemaStatus"
                    onChange={haandterSok}
                  >
                    <option key="0" value="0">
                      Skjemastatus
                    </option>
                    {EnumArray(SkjemaStatus).map((arr: any) => (
                      <option key={arr.key} value={arr.key}>
                        {arr.value}
                      </option>
                    ))}
                  </Form.Control>
                  <Form.Text className="text-muted">
                    Søk etter skjemastatus
                  </Form.Text>
                </Col>
              </Form.Group>
            </Col>

            <Col sm="2">
              <Form.Group as={Row} controlId="interjuStatus">
                <Col>
                  <Form.Control
                    as="select"
                    value={sok.interjuStatus}
                    name="interjuStatus"
                    onChange={haandterSok}
                  >
                    <option key="0" value="0">
                      Intervjustatus
                    </option>
                    {EnumArray(IntervjuStatus).map((arr: any) => (
                      <option key={arr.key} value={arr.key}>
                        {arr.value}
                      </option>
                    ))}
                  </Form.Control>
                  <Form.Text className="text-muted">
                    Søk etter skjemastatus
                  </Form.Text>
                </Col>
              </Form.Group>
            </Col>

            <Col sm="3">
              <Form.Group as={Row} controlId="intervjuObjektId">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.intervjuObjektId}
                    name="intervjuObjektId"
                    onChange={haandterSok}
                    placeholder="IO Id"
                  />
                  <Form.Text className="text-muted">Søk etter IO Id</Form.Text>
                </Col>
              </Form.Group>
            </Col>

            <Col sm="3">
              <Form.Group as={Row} controlId="intervjuObjektNummer">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.intervjuObjektNummer}
                    name="intervjuObjektNummer"
                    onChange={haandterSok}
                    placeholder="IO nummer"
                  />
                  <Form.Text className="text-muted">
                    Søk etter IO nummer
                  </Form.Text>
                </Col>
              </Form.Group>
            </Col>

            <Col sm="2">
              <Form.Group as={Row} controlId="intervjuStatusEndretFra">
                <Col>
                  <Form.Control
                    type="date"
                    value={moment(sok.intervjuStatusEndretFra).format(
                      "YYYY-MM-DD"
                    )}
                    name="intervjuStatusEndretFra"
                    onChange={haandterSok}
                    placeholder="Intervjustatus endret"
                  />
                  <Form.Text className="text-muted">
                    Status endret (fra)
                  </Form.Text>
                </Col>
              </Form.Group>
            </Col>

            <Col sm="2">
              <Form.Group as={Row} controlId="intervjuStatusEndretTil">
                <Col>
                  <Form.Control
                    type="date"
                    value={moment(sok.intervjuStatusEndretTil).format(
                      "YYYY-MM-DD"
                    )}
                    onChange={haandterSok}
                    name="intervjuStatusEndretTil"
                    placeholder="Intervjustatus endret"
                  />
                  <Form.Text className="text-muted">
                    Status endret (tom)
                  </Form.Text>
                </Col>
              </Form.Group>
            </Col>

            <Col sm="2">
              <Form.Group as={Row} controlId="avtaleType">
                <Col>
                  <Form.Control
                    as="select"
                    value={sok.avtaleType}
                    name="avtaleType"
                    onChange={haandterSok}
                  >
                    <option key="0" value="0">
                      Avtaletype
                    </option>
                    {EnumArray(AvtaleType).map((arr: any) => (
                      <option key={arr.key} value={arr.key}>
                        {arr.value}
                      </option>
                    ))}
                  </Form.Control>
                  <Form.Text className="text-muted">
                    Søk etter avtaletype
                  </Form.Text>
                </Col>
              </Form.Group>
            </Col>
          </Row>

          <Row className="mx-0" hidden={!avansertSok}>
            <Col sm="3">
              <Form.Group as={Row} controlId="fodselsnummer">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.fodselsnummer}
                    name="fodselsnummer"
                    onChange={haandterSok}
                    placeholder="Fødselsnummer"
                  />
                  <Form.Text className="text-muted">
                    Søk etter Fødselsnummer
                  </Form.Text>
                </Col>
              </Form.Group>
            </Col>
            <Col sm="3">
              <Form.Group as={Row} controlId="familienummer">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.familienummer}
                    name="familienummer"
                    onChange={haandterSok}
                    placeholder="Familienummer"
                  />
                  <Form.Text className="text-muted">
                    Søk etter familienummer
                  </Form.Text>
                </Col>
              </Form.Group>
            </Col>
            <Col sm="2">
              <Form.Group as={Row} controlId="alder">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.alder}
                    name="alder"
                    onChange={haandterSok}
                    placeholder="alder (fra-til)"
                  />
                  <Form.Text className="text-muted">Søk etter alder</Form.Text>
                </Col>
              </Form.Group>
            </Col>

            <Col sm="2">
              <Form.Group as={Row} controlId="kjonn">
                <Col>
                  <Form.Control
                    as="select"
                    value={sok.kjonn}
                    name="kjonn"
                    onChange={haandterSok}
                  >
                    <option key="0" value="0">
                      Kjønn
                    </option>
                    {EnumArray(Kjonn).map((arr: any) => (
                      <option key={arr.key} value={arr.key}>
                        {arr.value}
                      </option>
                    ))}
                  </Form.Control>
                  <Form.Text className="text-muted">Søk etter Kjønn</Form.Text>
                </Col>
              </Form.Group>
            </Col>
            <Col sm="2">
              <Form.Group as={Row} controlId="epost">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.epost}
                    name="epost"
                    onChange={haandterSok}
                    placeholder="Epost"
                  />
                  <Form.Text className="text-muted">Søk etter epost</Form.Text>
                </Col>
              </Form.Group>
            </Col>
            <Col sm="3">
              <Form.Group as={Row} controlId="adresse">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.adresse}
                    name="adresse"
                    onChange={haandterSok}
                    placeholder="Adresse"
                  />
                  <Form.Text className="text-muted">
                    Søk etter adresse
                  </Form.Text>
                </Col>
              </Form.Group>
            </Col>
            <Col sm="3">
              <Form.Group as={Row} controlId="postnummer">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.postnummer}
                    name="postnummer"
                    onChange={haandterSok}
                    placeholder="Postnummer"
                  />
                  <Form.Text className="text-muted">
                    Søk etter postnummer
                  </Form.Text>
                </Col>
              </Form.Group>
            </Col>
            <Col sm="2">
              <Form.Group as={Row} controlId="postSted">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.poststed}
                    name="postSted"
                    onChange={haandterSok}
                    placeholder="Poststed"
                  />
                  <Form.Text className="text-muted">
                    Søk etter poststed
                  </Form.Text>
                </Col>
              </Form.Group>
            </Col>
            <Col sm="2">
              <Form.Group as={Row} controlId="bolignummer">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.bolignummer}
                    name="bolignummer"
                    onChange={haandterSok}
                    placeholder="Bolignummer"
                  />
                  <Form.Text className="text-muted">
                    Søk etter bolignummer
                  </Form.Text>
                </Col>
              </Form.Group>
            </Col>
            <Col sm="2">
              <Form.Group as={Row} controlId="kommunenummer">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.kommunenummer}
                    name="kommunenummer"
                    onChange={haandterSok}
                    placeholder="Kommunenummer"
                  />
                  <Form.Text className="text-muted">
                    Søk etter kommunenummer
                  </Form.Text>
                </Col>
              </Form.Group>
            </Col>
          </Row>
          <Row className="mx-0">
            <Button
              variant="secondary"
              onClick={sokIntervjuObjekt}
              className="btn btn-sm mr-2 ml-2"
            >
              Søk intervjuobjekt
            </Button>
            <Button
              variant="info"
              onClick={() => setAvansertSok(!avansertSok)}
              className="btn btn-sm"
            >
              Vis avansertsøk
            </Button>
          </Row>
        </Card.Body>
      </Card>

      <Card className="mt-3">
        <Card.Header>
          <Row className="mb-0 d-flex">
            <Col sm="6" className="mt-1 align-self-start">
              <span className="font-weight-bolder text-uppercase">
                Resultat {finnesData && `(${data?.antall})`}
              </span>
            </Col>

            <Col sm="6">
              {finnesData && data && (
                <PaginationControll
                  side={data.side}
                  antallSider={data.antallSider}
                  setSide={setSide}
                />
              )}
            </Col>
          </Row>
        </Card.Header>
        <Card.Body>
          <Spinner
            animation="border"
            className="m-auto"
            role="status"
            hidden={!isFetching}
          >
            <span className="sr-only">Laster...</span>
          </Spinner>
          {data &&
            data.intervjuObjekter &&
            data.intervjuObjekter.length > 0 &&
            data.intervjuObjekter.map(
              (intervjuObjekt: IIntervjuObjekt, index: number) => (
                <IntervjuObjekt
                  key={index}
                  refetch={refetch}
                  objekt={intervjuObjekt}
                  objektNavn="intervjuObjekt"
                  apiURL="intervjuObjekter"
                  routeURL="intervjuObjekter"
                />
              )
            )}
        </Card.Body>
      </Card>
    </>
  );
};

export default IntervjuObjektListe;
