import { BaseSyntheticEvent, FC, FormEvent, useState } from "react";

import { Button, Card, Col, Form, InputGroup, Row } from "react-bootstrap";
import IProsjekt from "components/Prosjekt/models/IProsjekt";
import { Link } from "react-router-dom";
import { aarene, EnumArray } from "helpers/utils";
import ProsjektModus from "components/Prosjekt/enums/ProsjektModus";
import ProsjektFinansiering from "components/Prosjekt/enums/ProsjektFinansiering";
import ProsjektStatus from "components/Prosjekt/enums/ProsjektStatus";
import IProsjektLeder from "components/ProsjektLeder/models/IProsjektLeder";
import IProsjektLederSok from "components/ProsjektLeder/models/IProsjektLederSok";
import useProsjektLedereSok from "components/ProsjektLeder/hooks/useProsjektLedere";
import moment from "moment";
import DefaultProsjektLederSok from "components/ProsjektLeder/types/DefaultProsjektLederSok";

interface Parametere {
  prosjekt: IProsjekt;
  haandterEndring: (e: BaseSyntheticEvent) => void;
  haandterSubmit: (e: FormEvent<HTMLFormElement>) => void;
  validert: boolean;
}

const Skjema: FC<Parametere> = ({
  prosjekt,
  haandterEndring,
  haandterSubmit,
  validert,
}) => {
  const [prosjektLederSok, setProsjektLederSok] = useState<IProsjektLederSok>(
    DefaultProsjektLederSok
  );

  const { data: lederListe } = useProsjektLedereSok(prosjektLederSok);

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
              <Col sm="4">
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
            <Form.Group as={Row} controlId="produktNummer">
              <Form.Label column sm="2">
                Produktnummer
              </Form.Label>
              <Col sm="4">
                <Form.Control
                  type="number"
                  value={prosjekt.produktNummer}
                  onChange={haandterEndring}
                  placeholder="Produktnummer"
                  maxLength={5}
                  required
                />
                <Form.Control.Feedback type="invalid" className="text-muted">
                  Produktnummer er påkrevd !
                </Form.Control.Feedback>
              </Col>
            </Form.Group>

            <Form.Group as={Row} controlId="aargang">
              <Form.Label column sm="2">
                Årgang
              </Form.Label>
              <Col sm="4">
                <Form.Control
                  as="select"
                  value={prosjekt.aargang}
                  name="aargang"
                  onChange={haandterEndring}
                >
                  {aarene.map((aar: number, index: number) => (
                    <option key={index} value={aar}>
                      {aar}
                    </option>
                  ))}
                </Form.Control>
              </Col>
            </Form.Group>

            <Form.Group as={Row} controlId="oppstartDato">
              <Form.Label column sm="2">
                Oppstartsdato
              </Form.Label>
              <Col sm="4">
                <Form.Control
                  type="date"
                  value={moment(prosjekt.oppstartDato).format("YYYY-MM-DD")}
                  onChange={haandterEndring}
                  placeholder="Prosjekt oppstartsdato"
                  required
                />
              </Col>
            </Form.Group>

            <Form.Group as={Row} controlId="avslutningsDato">
              <Form.Label column sm="2">
                Avslutningsdato
              </Form.Label>
              <Col sm="4">
                <Form.Control
                  type="date"
                  value={moment(prosjekt.avslutningsDato).format("YYYY-MM-DD")}
                  onChange={haandterEndring}
                  placeholder="Prosjekt avslutningsdato"
                  required
                />
              </Col>
            </Form.Group>

            <Form.Group as={Row} controlId="registerNummer">
              <Form.Label column sm="2">
                Registernummer
              </Form.Label>
              <Col sm="4">
                <Form.Control
                  type="text"
                  value={prosjekt.registerNummer}
                  onChange={haandterEndring}
                  placeholder="Registernummer"
                />
                <Form.Control.Feedback type="invalid" className="text-muted">
                  Produktnummer er påkrevd !
                </Form.Control.Feedback>
              </Col>
            </Form.Group>

            <Form.Group as={Row} controlId="panel">
              <Form.Label column sm="2"></Form.Label>
              <Col sm="4">
                <Form.Check
                  name="panel"
                  type="checkbox"
                  label="Panel"
                  checked={prosjekt.panel === "true" ? true : false}
                  onChange={haandterEndring}
                />
              </Col>
            </Form.Group>
            <Form.Group as={Row} controlId="prosjektLeder">
              <Form.Label column sm="2">
                Prosjektleder
              </Form.Label>
              <Col sm="4">
                <Form.Control
                  as="select"
                  value={prosjekt.prosjektLeder.id}
                  onChange={haandterEndring}
                >
                  <option key="0" value="0">
                    Velg prosjektleder
                  </option>
                  {lederListe &&
                    lederListe.objekter &&
                    lederListe.objekter.length > 0 &&
                    lederListe.objekter.map((leder: IProsjektLeder) => (
                      <option key={leder.id} value={leder.id}>
                        {leder.navn}
                      </option>
                    ))}
                </Form.Control>
              </Col>

              <Form.Text className="text-muted"></Form.Text>
            </Form.Group>

            <Form.Group as={Row} controlId="modus">
              <Form.Label column sm="2">
                Modus
              </Form.Label>
              <Col sm="4">
                <Form.Control
                  as="select"
                  value={prosjekt.modus}
                  name="modus"
                  onChange={haandterEndring}
                >
                  {EnumArray(ProsjektModus).map((arr: any) => (
                    <option key={arr.key} value={arr.key}>
                      {arr.value}
                    </option>
                  ))}
                </Form.Control>
              </Col>
            </Form.Group>
            <Form.Group as={Row} controlId="prosjektStatus">
              <Form.Label column sm="2">
                Prosjektstatus
              </Form.Label>
              <Col sm="4">
                <Form.Control
                  as="select"
                  value={prosjekt.prosjektStatus}
                  name="prosjektStatus"
                  onChange={haandterEndring}
                >
                  {EnumArray(ProsjektStatus).map((arr: any) => (
                    <option key={arr.value} value={arr.key}>
                      {arr.key}
                    </option>
                  ))}
                </Form.Control>
              </Col>
            </Form.Group>
            <Form.Group as={Row} controlId="finansiering">
              <Form.Label column sm="2">
                Finansiering
              </Form.Label>
              <Col sm="4">
                <Form.Control
                  as="select"
                  value={prosjekt.finansiering}
                  name="finansiering"
                  onChange={haandterEndring}
                >
                  {EnumArray(ProsjektFinansiering).map((arr: any) => (
                    <option key={arr.key} value={arr.key}>
                      {arr.value}
                    </option>
                  ))}
                </Form.Control>
              </Col>
              <Form.Label column sm="1">
                Stat
              </Form.Label>
              <Col sm="2">
                <InputGroup>
                  <Form.Control
                    type="text"
                    value={prosjekt.prosentStat}
                    onChange={haandterEndring}
                    disabled={prosjekt.finansiering !== "STAT_MARKED"}
                    placeholder="Stat %"
                  />
                  <InputGroup.Append>
                    <InputGroup.Text>%</InputGroup.Text>
                  </InputGroup.Append>
                </InputGroup>
              </Col>
              <Form.Label column sm="1">
                Marked
              </Form.Label>
              <Col sm="2">
                <InputGroup>
                  <Form.Control
                    type="text"
                    value={prosjekt.prosentMarked}
                    onChange={haandterEndring}
                    disabled={prosjekt.finansiering !== "STAT_MARKED"}
                    placeholder="Marked %"
                  />
                  <InputGroup.Append>
                    <InputGroup.Text>%</InputGroup.Text>
                  </InputGroup.Append>
                </InputGroup>
              </Col>
            </Form.Group>
            <Form.Group as={Row} controlId="finansiering">
              <Form.Label column sm="2">
                Kommentar
              </Form.Label>
              <Col sm="4">
                <Form.Control
                  as="textarea"
                  value={prosjekt.kommentar}
                  onChange={haandterEndring}
                  placeholder="Skriv kommentarer"
                />
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
              <Link className="btn btn-dark" to="/prosjekter">
                Avbryt
              </Link>
            </Form.Group>
          </Card.Footer>
        </Card>
      </Form>
    </>
  );
};

export default Skjema;
