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
import IProsjektLeder from "components/ProsjektLeder/models/IProsjektLeder";
import { EnumArray, ToArray } from "helpers/utils";
import useProsjektLedere from "components/ProsjektLeder/hooks/useProsjektLedere";
import PaginationControll from "components/Felles/PaginationControll";
import ProsjektLederSorter from "components/ProsjektLeder/enums/ProsjektLederSorter";
import { BiSortDown, BiSortUp } from "react-icons/bi";
import DefaultProsjektLederSok from "components/ProsjektLeder/types/DefaultProsjektLederSok";
import Objekt from "./Objekt";
import IProsjektLederSok from "../models/IProsjektLederSok";

const ProsjektLederListe: FC = () => {
  const [sok, setSok] = useState<IProsjektLederSok>(DefaultProsjektLederSok);
  const [sort, setSort] = useState(1);

  const history = useHistory();
  const { data, isFetching, refetch } = useProsjektLedere(sok);

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
    data && data.objekter && data.objekter.length > 0 ? true : false;

  const setSide = (e: BaseSyntheticEvent) => {
    const side = e.target.getAttribute("data-text");
    if (side) {
      setSok({ ...sok, side: side });
      refetch();
    }
  };

  const opprett = () => {
    history.push("/prosjektLedere/opprett");
  };

  return (
    <>
      <Card className="mt-3">
        <Card.Header>
          <Row>
            <Col sm="10">
              <div className="mt-1">
                <span className="font-weight-bolder text-uppercase">
                  ProsjektLeder søk
                </span>
              </div>
            </Col>
            <Col sm="2">
              <Button
                onClick={opprett}
                className="btn btn-sm mb-0 ml-auto mr-0 btn-secondary"
              >
                Nytt ProsjektLeder
              </Button>
            </Col>
          </Row>
        </Card.Header>
        <Card.Body>
          <Row className="mx-0">
            <Col sm="2" className="filter_col">
              <Form.Group as={Row} controlId="navn">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.navn}
                    name="navn"
                    onChange={haandterSok}
                    placeholder="Navn"
                  />
                  <Form.Text className="text-muted">Søk etter Navn</Form.Text>
                </Col>
              </Form.Group>
            </Col>
            <Col sm="3">
              <Form.Group as={Row} controlId="initialer">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.initialer}
                    name="initialer"
                    onChange={haandterSok}
                    placeholder="initialer"
                  />
                  <Form.Text className="text-muted">
                    Søk etter initialer
                  </Form.Text>
                </Col>
              </Form.Group>
            </Col>
            <Col sm="2">
              <Form.Group as={Row} controlId="epost">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.epost}
                    name="aargang"
                    onChange={haandterSok}
                    placeholder="Epost"
                  />
                  <Form.Text className="text-muted">Søk etter epost</Form.Text>
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
                    {EnumArray(ProsjektLederSorter).map((arr: any) => (
                      <option key={arr.key} value={arr.value}>
                        {arr.key}
                      </option>
                    ))}
                  </Form.Control>
                  <Form.Text className="text-muted">
                    Sorter prosjektLederliste
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
                ProsjektLederliste {finnesData && `(${data?.antall})`}
              </span>
            </Col>

            <Col sm="6">
              {finnesData && data && data.antallSider > 1 && (
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
            data.objekter &&
            data.objekter.length > 0 &&
            data.objekter.map(
              (prosjektLeder: IProsjektLeder, index: number) => (
                <Objekt
                  key={index}
                  refetch={refetch}
                  objekt={prosjektLeder}
                  objektNavn="prosjektLeder"
                  apiURL="prosjektLedere"
                  routeURL="prosjektLedere"
                />
              )
            )}
        </Card.Body>
      </Card>
    </>
  );
};

export default ProsjektLederListe;
