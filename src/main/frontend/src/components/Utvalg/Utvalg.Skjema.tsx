import { BaseSyntheticEvent, FC, FormEvent, useState } from "react";

import { Button, Card, Col, Form, InputGroup, Row } from "react-bootstrap";
import IUtvalg from "models/Utvalg/IUtvalg";
import { Link } from "react-router-dom";
import { aarene, EnumArray } from "helpers/utils";
import moment from "moment";

interface Parametere {
  utvalg: IUtvalg;
  haandterEndring: (e: BaseSyntheticEvent) => void;
  haandterSubmit: (e: FormEvent<HTMLFormElement>) => void;
  validert: boolean;
}

const UtvalgSkjema: FC<Parametere> = ({
  utvalg,
  haandterEndring,
  haandterSubmit,
  validert,
}) => {
  return (
    <>
      <Form onSubmit={haandterSubmit} noValidate validated={validert}>
        <Card>
          <Card.Header>Utvalg Skjema</Card.Header>
          <Card.Body></Card.Body>

          <Card.Footer>
            <Form.Group
              as={Row}
              className="flex justify-content-center"
              controlId="actions"
            >
              <Button variant="primary" type="submit" className="mr-2">
                Lagre Utvalg
              </Button>
              <Link className="btn btn-dark" to="/utvalger">
                Avbryt
              </Link>
            </Form.Group>
          </Card.Footer>
        </Card>
      </Form>
    </>
  );
};

export default UtvalgSkjema;
