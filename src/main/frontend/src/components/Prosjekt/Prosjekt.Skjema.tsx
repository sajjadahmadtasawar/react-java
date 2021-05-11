import React, { BaseSyntheticEvent, FC, FormEvent } from "react";

import { Button, Card, Col, Form, Row } from "react-bootstrap";
import IProsjekt from "models/Prosjekt/IProsjekt";
import { Link } from "react-router-dom";

interface Parametere {
  prosjekt: IProsjekt;
  haandterEndring: (e: BaseSyntheticEvent) => void;
  haandterSubmit: (e: FormEvent<HTMLFormElement>) => void;
  validert: boolean;
}

const ProsjektSkjema: FC<Parametere> = ({
  prosjekt,
  haandterEndring,
  haandterSubmit,
  validert,
}) => {
  return (
    <>
      <Form onSubmit={haandterSubmit} noValidate validated={validert}>
        <Card>
          <Card.Header>Prosjekt Skjema</Card.Header>
          <Card.Body>
            <Form.Group as={Row} controlId="prosjektNavn">
              <Form.Label column sm="2">
                Prosjektnavn
              </Form.Label>
              <Col sm="9">
                <Form.Control
                  type="text"
                  value={prosjekt.prosjektNavn}
                  onChange={haandterEndring}
                  placeholder="Skriv prosjektnavn (minst 5 tegn)"
                  required
                  minLength={5}
                />
                <Form.Control.Feedback type="invalid" className="text-muted">
                  Prosjektnavn er påkrevd og må vare minst 5 tegn!
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
                Lagre Prosjekt
              </Button>
              <Link className="btn btn-dark" to="/services">
                Avbryt
              </Link>
            </Form.Group>
          </Card.Footer>
        </Card>
      </Form>
    </>
  );
};

export default ProsjektSkjema;
