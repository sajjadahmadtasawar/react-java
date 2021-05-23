import React, { BaseSyntheticEvent, FC, useState } from "react";
import Spinner from "react-bootstrap/esm/Spinner";
import { useHistory } from "react-router-dom";
import {
  Button,
  Card,
  Col,
  Form,
  Row,
  ToggleButton,
  ToggleButtonGroup,
} from "react-bootstrap";
import IProsjekt from "models/Prosjekt/IProsjekt";
import Prosjekt from "./Prosjekt";
import ProsjektStatus from "enums/ProsjektStatus";
import { EnumArray, ToArray } from "helpers/utils";
import useProsjekter from "context/hooks/Prosjekt/useProsjekter";
import IProsjektSok from "models/Prosjekt/IProsjektSok";
import PaginationControll from "components/Felles/PaginationControll";
import ProsjektSorter from "enums/ProsjektSorter";
import { BiSortDown, BiSortUp } from "react-icons/bi";
import DefaultProsjektSok from "types/DefaultProsjektSok";

const ProsjektListe: FC = () => {
  const [sok, setSok] = useState<IProsjektSok>(DefaultProsjektSok);
  const [sort, setSort] = useState(1);

  const history = useHistory();
  const { data, isFetching, refetch } = useProsjekter(sok);

  const haandterSok = (e: BaseSyntheticEvent) => {
    const name = e.currentTarget.name as string;
    const value = e.currentTarget.value as string;

    setSok({ ...sok, [name]: value, side: 1 });
    refetch();
  };

  const haandterSokSort = (val: number) => {
    const asc = val === 1 ? true : false;
    setSort(val);
    setSok({ ...sok, asc: asc, side: 1 });
    refetch();
  };

  const finnesData: boolean =
    data && data.prosjekter && data.prosjekter.length > 0 ? true : false;

  const setSide = (e: BaseSyntheticEvent) => {
    const side = e.target.getAttribute("data-text");
    if (side) {
      setSok({ ...sok, side: side });
      refetch();
    }
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
              <Button
                onClick={opprett}
                className="btn btn-sm mb-0 ml-auto mr-0 btn-secondary"
              >
                Nytt prosjekt
              </Button>
            </Col>
          </Row>
        </Card.Header>
        <Card.Body>
          <Row className="mx-0">
            <Col sm="2" className="filter_col">
              <Form.Group as={Row} controlId="produktNummer">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.produktNummer}
                    name="produktNummer"
                    onChange={haandterSok}
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
                    onChange={haandterSok}
                    placeholder="Prosjektnavn"
                  />
                  <Form.Text className="text-muted">Søk etter navn</Form.Text>
                </Col>
              </Form.Group>
            </Col>
            <Col sm="2">
              <Form.Group as={Row} controlId="aargang">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.aargang}
                    name="aargang"
                    onChange={haandterSok}
                    placeholder="Årgang"
                  />
                  <Form.Text className="text-muted">Søk etter Årgang</Form.Text>
                </Col>
              </Form.Group>
            </Col>
            <Col sm="2">
              <Form.Group as={Row} controlId="aargang">
                <Col>
                  <Form.Control
                    as="select"
                    value={sok.prosjektStatus}
                    name="prosjektStatus"
                    onChange={haandterSok}
                  >
                    <option key="" value="">
                      Alle
                    </option>
                    {EnumArray(ProsjektStatus).map((arr: any) => (
                      <option key={arr.value} value={arr.key}>
                        {arr.key}
                      </option>
                    ))}
                  </Form.Control>
                  <Form.Text className="text-muted">
                    Søk etter prosjektstatus
                  </Form.Text>
                </Col>
              </Form.Group>
            </Col>
            <Col sm="2">
              <Form.Group as={Row} controlId="sorter">
                <Col>
                  <Form.Control
                    as="select"
                    value={sok.sort}
                    name="sort"
                    onChange={haandterSok}
                  >
                    {EnumArray(ProsjektSorter).map((arr: any) => (
                      <option key={arr.key} value={arr.value}>
                        {arr.key}
                      </option>
                    ))}
                  </Form.Control>
                  <Form.Text className="text-muted">
                    Sorter prosjektliste
                  </Form.Text>
                </Col>
              </Form.Group>
            </Col>
            <Col sm="1">
              <ToggleButtonGroup
                type="radio"
                name="options"
                defaultValue={sok.asc ? 1 : 2}
                value={sort}
                onChange={haandterSokSort}
              >
                <ToggleButton variant="outline-info" className="mr-1" value={1}>
                  <BiSortDown />
                </ToggleButton>
                <ToggleButton variant="outline-info" value={2}>
                  <BiSortUp />
                </ToggleButton>
              </ToggleButtonGroup>
            </Col>
          </Row>
        </Card.Body>
      </Card>

      <Card className="mt-3">
        <Card.Header>
          <Row className="mb-0 d-flex">
            <Col sm="6" className="mt-1 align-self-start">
              <span className="font-weight-bolder text-uppercase">
                Prosjektliste {finnesData && `(${data?.antall})`}
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
        </Card.Body>
      </Card>
    </>
  );
};

export default ProsjektListe;
