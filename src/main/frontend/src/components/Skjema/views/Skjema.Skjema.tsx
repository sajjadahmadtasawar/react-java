import { BaseSyntheticEvent, FC, FormEvent, useState } from "react";

import { Button, Card, Col, Form, InputGroup, Row } from "react-bootstrap";
import ISkjema from "components/Skjema/models/ISkjema";
import { Link } from "react-router-dom";
import { aarene, EnumArray } from "helpers/utils";
import moment from "moment";

interface Parametere {
  skjema: ISkjema;
  haandterEndring: (e: BaseSyntheticEvent) => void;
  haandterSubmit: (e: FormEvent<HTMLFormElement>) => void;
  validert: boolean;
}

const SkjemaSkjema: FC<Parametere> = ({
  skjema,
  haandterEndring,
  haandterSubmit,
  validert,
}) => {
  return (
    <>
      <Form onSubmit={haandterSubmit} noValidate validated={validert}>
        <Card>
          <Card.Header>Skjema Skjema</Card.Header>
          <Card.Body>
            <Form.Group as={Row} controlId="skjemaNavn">
              <Form.Label column sm="2">
                Skjemanavn
              </Form.Label>
              <Col sm="4">
                <Form.Control
                  type="text"
                  value={skjema.skjemaNavn}
                  onChange={haandterEndring}
                  placeholder="Skriv skjemanavn"
                  required
                />

                <Form.Control.Feedback type="invalid" className="text-muted">
                  Skjemanavn er påkrevd og må vare minst 5 tegn!
                </Form.Control.Feedback>
              </Col>
            </Form.Group>

            <Form.Group as={Row} controlId="skjemaKortNavn">
              <Form.Label column sm="2">
                Kortnavn
              </Form.Label>
              <Col sm="4">
                <Form.Control
                  type="text"
                  value={skjema.skjemaKortNavn}
                  onChange={haandterEndring}
                  placeholder="Skriv skjema kortnavn"
                  required
                />

                <Form.Control.Feedback type="invalid" className="text-muted">
                  Skjema kortnavn er påkrevd !
                </Form.Control.Feedback>
              </Col>
            </Form.Group>

            <Form.Group as={Row} controlId="delProduktNummer">
              <Form.Label column sm="2">
                DelProduktnummer
              </Form.Label>
              <Col sm="4">
                <Form.Control
                  type="number"
                  value={skjema.delProduktNummer}
                  onChange={haandterEndring}
                  placeholder="delProduktNummer"
                  maxLength={5}
                  required
                />
                <Form.Control.Feedback type="invalid" className="text-muted">
                  DelProduktnummer er påkrevd !
                </Form.Control.Feedback>
              </Col>
            </Form.Group>
          </Card.Body>

          <Card.Footer>
            <Form.Group
              as={Row}
              className="flex justify-content-center"
              controlId="actions"
            >
              <Button variant="primary" type="submit" className="mr-2">
                Lagre Skjema
              </Button>
              <Link className="btn btn-dark" to="/skjemaer">
                Avbryt
              </Link>
            </Form.Group>
          </Card.Footer>
        </Card>
      </Form>
    </>
  );
};

export default SkjemaSkjema;
