import React, { BaseSyntheticEvent, FC, useState } from "react";
import Spinner from "react-bootstrap/esm/Spinner";
import { Link, useHistory } from "react-router-dom";
import { Button, Card, Col, Form, Row } from "react-bootstrap";
import IProsjekt from "models/Prosjekt/IProsjekt";
import Prosjekt from "./Prosjekt";
import useListe from "context/hooks/useListe";
import ProsjektStatus from "enums/ProsjektStatus";
import { ToArray } from "helpers/utils";

const ProsjektListe: FC = () => {
  const [produktNummer, setProduktNummer] = useState("");
  const [prosjektNavn, setProsjektNavn] = useState("");
  const [aargang, setAargang] = useState("");
  const [prosjektStatus, setProsjektStatus] = useState("");

  const [filteredData, setFilteredData] = useState<IProsjekt[]>([]);

  const history = useHistory();
  const { data, isFetching, refetch } = useListe("prosjekter");

  console.log(data);
  const handleChange = (e: BaseSyntheticEvent) => {
    //   setKeyword(e.currentTarget.value);
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
                    value={produktNummer}
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
                    value={prosjektNavn}
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
                    value={aargang}
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
                    value={prosjektStatus}
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
            data._embedded &&
            data._embedded.prosjekter &&
            data._embedded.prosjekter.length > 0 &&
            `(${data.page.totalElements})`}
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
        data._embedded &&
        data._embedded.prosjekter &&
        data._embedded.prosjekter.length > 0 &&
        data._embedded.prosjekter.map((prosjekt: IProsjekt, index: number) => (
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
