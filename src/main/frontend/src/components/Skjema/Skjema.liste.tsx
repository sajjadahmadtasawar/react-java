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
import ISkjema from "models/Skjema/ISkjema";
import Skjema from "./Skjema";
import { EnumArray } from "helpers/utils";
import useSkjemaer from "context/hooks/Skjema/useSkjemaer";
import ISkjemaSok from "models/Skjema/ISkjemaSok";
import PaginationControll from "components/Felles/PaginationControll";
import { BiSortDown, BiSortUp } from "react-icons/bi";
import DefaultSkjemaSok from "types/DefaultSkjemaSok";
import UndersokelseType from "enums/UndersokelseType";
import SkjemaSorter from "enums/SkjemaSorter";

const SkjemaListe: FC = () => {
  const [sok, setSok] = useState<ISkjemaSok>(DefaultSkjemaSok);
  const [sort, setSort] = useState(1);

  const history = useHistory();
  const { data, isFetching, refetch } = useSkjemaer(sok);

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
    data && data.skjemaer && data.skjemaer.length > 0 ? true : false;

  const setSide = (e: BaseSyntheticEvent) => {
    const side = e.target.getAttribute("data-text");
    if (side) {
      setSok({ ...sok, side: side });
      refetch();
    }
  };

  const opprett = () => {
    history.push("/skjemaer/opprett");
  };

  return (
    <>
      <Card className="mt-3">
        <Card.Header>
          <Row>
            <Col sm="10">
              <div className="mt-1">
                <span className="font-weight-bolder text-uppercase">
                  Skjemasøk
                </span>
              </div>
            </Col>
            <Col sm="2">
              <Button
                onClick={opprett}
                className="btn btn-sm mb-0 ml-auto mr-0 btn-secondary"
              >
                Nytt skjema
              </Button>
            </Col>
          </Row>
        </Card.Header>
        <Card.Body>
          <Row className="mx-0">
            <Col sm="2" className="filter_col">
              <Form.Group as={Row} controlId="delProduktNummer">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.delProduktNummer}
                    name="delProduktNummer"
                    onChange={haandterSok}
                    placeholder="Delproduktnummer"
                  />
                  <Form.Text className="text-muted">
                    Søk etter delproduktnummer
                  </Form.Text>
                </Col>
              </Form.Group>
            </Col>
            <Col sm="3">
              <Form.Group as={Row} controlId="skjemaNavn">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.skjemaNavn}
                    name="skjemaNavn"
                    onChange={haandterSok}
                    placeholder="Skjemanavn"
                  />
                  <Form.Text className="text-muted">Søk etter navn</Form.Text>
                </Col>
              </Form.Group>
            </Col>
            <Col sm="2">
              <Form.Group as={Row} controlId="skjemaKortNavn">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.skjemaKortNavn}
                    name="skjemaKortNavn"
                    onChange={haandterSok}
                    placeholder="Skjemakortnavn"
                  />
                  <Form.Text className="text-muted">
                    Søk etter Skjema Kort Navn
                  </Form.Text>
                </Col>
              </Form.Group>
            </Col>
            <Col sm="2">
              <Form.Group as={Row} controlId="undersokelseType">
                <Col>
                  <Form.Control
                    as="select"
                    value={sok.undersokelseType}
                    name="undersokelseType"
                    onChange={haandterSok}
                  >
                    <option key="" value="">
                      Alle
                    </option>
                    {EnumArray(UndersokelseType).map((arr: any) => (
                      <option key={arr.value} value={arr.key}>
                        {arr.key}
                      </option>
                    ))}
                  </Form.Control>
                  <Form.Text className="text-muted">
                    Søk etter Undersøkelsetype
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
                    {EnumArray(SkjemaSorter).map((arr: any) => (
                      <option key={arr.key} value={arr.value}>
                        {arr.key}
                      </option>
                    ))}
                  </Form.Control>
                  <Form.Text className="text-muted">
                    Sorter skjemaliste
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
                Skjemaliste {finnesData && `(${data?.antall})`}
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
            data.skjemaer &&
            data.skjemaer.length > 0 &&
            data.skjemaer.map((skjema: ISkjema, index: number) => (
              <Skjema
                key={index}
                refetch={refetch}
                objekt={skjema}
                objektNavn="skjema"
                apiURL="skjemaer"
                routeURL="skjemaer"
              />
            ))}
        </Card.Body>
      </Card>
    </>
  );
};

export default SkjemaListe;
