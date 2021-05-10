import React, { BaseSyntheticEvent, FC, useState } from "react";
import Spinner from "react-bootstrap/esm/Spinner";
import { Link, useHistory } from "react-router-dom";
import { Button, Card, Col, Form, Row } from "react-bootstrap";
import IProsjekt from "models/Prosjekt/IProsjekt";
import Prosjekt from "./Prosjekt";
import ProsjektStatus from "enums/ProsjektStatus";
import { ToArray } from "helpers/utils";
import useProsjekter from "context/hooks/Prosjekt/useProsjekter";
import IProsjektSok from "models/Prosjekt/IProsjektSok";
import { DefaultProsjektSok } from "models/types";

const ProsjektListe: FC = () => {
  const [sok, setSok] = useState<IProsjektSok>(DefaultProsjektSok);

  const history = useHistory();
  const { data, isFetching, refetch } = useProsjekter(sok);

  const handleChange = (e: BaseSyntheticEvent) => {
    const name = e.currentTarget.name as string;
    const value = e.currentTarget.value as string;

    setSok({ ...sok, [name]: value });
    refetch();
  };

  const opprett = () => {
    history.push("/prosjekter/opprett");
  };

  return (
    <>
      <Card className="mt-3">
        <Card.Header>
          <Row>
            <Col sm="10">
              <div className="mt-1">
                <span className="font-weight-bolder text-uppercase">
                  Prosjektsøk
                </span>
              </div>
            </Col>
            <Col sm="2">
              <Button className="btn btn-sm mb-0 btn-secondary">
                Nytt prosjekt
              </Button>
            </Col>
          </Row>
        </Card.Header>
        <Card.Body>
          <Row>
            <Col sm="3">
              <Form.Group as={Row} controlId="produktNummer">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.produktNummer}
                    name="produktNummer"
                    onChange={handleChange}
                    placeholder="Produktnummer"
                  />
                  <Form.Text className="text-muted">
                    Søk etter produktnummer
                  </Form.Text>
                </Col>
              </Form.Group>
            </Col>
            <Col sm="3">
              <Form.Group as={Row} controlId="prosjektNavn">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.prosjektNavn}
                    name="prosjektNavn"
                    onChange={handleChange}
                    placeholder="Prosjektnavn"
                  />
                  <Form.Text className="text-muted">Søk etter navn</Form.Text>
                </Col>
              </Form.Group>
            </Col>
            <Col sm="3">
              <Form.Group as={Row} controlId="aargang">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.aargang}
                    name="aargang"
                    onChange={handleChange}
                    placeholder="Årgang"
                  />
                  <Form.Text className="text-muted">Søk etter Årgang</Form.Text>
                </Col>
              </Form.Group>
            </Col>
            <Col sm="3">
              <Form.Group as={Row} controlId="aargang">
                <Col>
                  <Form.Control
                    as="select"
                    value={sok.prosjektStatus}
                    name="prosjektStatus"
                    onChange={handleChange}
                  >
                    <option key="" value="">
                      Velg status
                    </option>
                    {ToArray(ProsjektStatus).map((key: number) => (
                      <option key={key} value={ProsjektStatus[key]}>
                        {ProsjektStatus[key]}
                      </option>
                    ))}
                  </Form.Control>
                  <Form.Text className="text-muted">
                    Søk etter prosjekt status
                  </Form.Text>
                </Col>
              </Form.Group>
            </Col>
          </Row>
        </Card.Body>
      </Card>

      <ol className="breadcrumb mb-3 mt-3">
        <li className="breadcrumb-item">
          <Link to={`/`}>Dashboard</Link>
        </li>
        <li className="breadcrumb-item active">
          Prosjekter{" "}
          {data &&
            data.prosjekter &&
            data.prosjekter.length > 0 &&
            `(${data.antall})`}
        </li>
      </ol>
      <Spinner
        animation="border"
        className="m-auto"
        role="status"
        hidden={!isFetching}
      >
        <span className="sr-only">Laster...</span>
      </Spinner>
      {data &&
        data.prosjekter &&
        data.prosjekter.length > 0 &&
        data.prosjekter.map((prosjekt: IProsjekt, index: number) => (
          <Prosjekt
            key={index}
            refetch={refetch}
            objekt={prosjekt}
            objektNavn="prosjekt"
            apiURL="prosjekter"
            routeURL="prosjekter"
          />
        ))}
    </>
  );
};

export default ProsjektListe;
