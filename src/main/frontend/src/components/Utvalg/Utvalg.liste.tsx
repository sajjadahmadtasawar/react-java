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
import IUtvalg from "models/Utvalg/IUtvalg";
import Utvalg from "./Utvalg";
import { EnumArray } from "helpers/utils";
import useUtvalger from "context/hooks/Utvalg/useUtvalger";
import IUtvalgSok from "models/Utvalg/IUtvalgSok";
import PaginationControll from "components/Felles/PaginationControll";
import { BiSortDown, BiSortUp } from "react-icons/bi";
import DefaultUtvalgSok from "types/DefaultUtvalgSok";
import UtvalgSorter from "enums/UtvalgSorter";

const UtvalgListe: FC = () => {
  const [sok, setSok] = useState<IUtvalgSok>(DefaultUtvalgSok);
  const [sort, setSort] = useState(1);

  const history = useHistory();
  const { data, isFetching, refetch } = useUtvalger(sok);

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
    data && data.utvalger && data.utvalger.length > 0 ? true : false;

  const setSide = (e: BaseSyntheticEvent) => {
    const side = e.target.getAttribute("data-text");
    if (side) {
      setSok({ ...sok, side: side });
      refetch();
    }
  };

  const opprett = () => {
    history.push("/utvalger/opprett");
  };

  return (
    <>
      <Card className="mt-3">
        <Card.Header>
          <Row>
            <Col sm="10">
              <div className="mt-1">
                <span className="font-weight-bolder text-uppercase">
                  Utvalgsøk
                </span>
              </div>
            </Col>
            <Col sm="2">
              <Button
                onClick={opprett}
                className="btn btn-sm mb-0 ml-auto mr-0 btn-secondary"
              >
                Nytt utvalg
              </Button>
            </Col>
          </Row>
        </Card.Header>
        <Card.Body>
          <Row className="mx-0">
            <Col sm="3">
              <Form.Group as={Row} controlId="skjemaKortNavn">
                <Col>
                  <Form.Control
                    type="text"
                    value={sok.skjemaKortNavn}
                    name="skjemaKortNavn"
                    onChange={haandterSok}
                    placeholder="skjema"
                  />
                  <Form.Text className="text-muted">Søk etter skjema</Form.Text>
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
                    {EnumArray(UtvalgSorter).map((arr: any) => (
                      <option key={arr.key} value={arr.value}>
                        {arr.key}
                      </option>
                    ))}
                  </Form.Control>
                  <Form.Text className="text-muted">
                    Sorter utvalgliste
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
                Utvalgliste {finnesData && `(${data?.antall})`}
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
            data.utvalger &&
            data.utvalger.length > 0 &&
            data.utvalger.map((utvalg: IUtvalg, index: number) => (
              <Utvalg
                key={index}
                refetch={refetch}
                objekt={utvalg}
                objektNavn="utvalg"
                apiURL="utvalger"
                routeURL="utvalger"
              />
            ))}
        </Card.Body>
      </Card>
    </>
  );
};

export default UtvalgListe;
