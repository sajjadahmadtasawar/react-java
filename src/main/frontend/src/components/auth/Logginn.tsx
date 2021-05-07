import React, {
  BaseSyntheticEvent,
  FC,
  FormEvent,
  useEffect,
  useRef,
  useState,
} from "react";
import { useHistory } from "react-router-dom";
import { Alert, Button, Card, Col, Form, Row } from "react-bootstrap";
import axios, { AxiosRequestConfig } from "axios";

const Logginn: FC = () => {
  const [validated, setValidated] = useState(false);

  const history = useHistory();
  const API_URL = "http://localhost:8181/api";

  const inputRef = useRef() as React.MutableRefObject<HTMLInputElement>;

  const [brukernavn, setBrukernavn] = useState("");
  const [passord, setPassord] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    inputRef.current.focus();
    localStorage.removeItem("bruker");
  }, []);

  const handleBrukernavn = (e: BaseSyntheticEvent) => {
    setBrukernavn(e.currentTarget.value);
  };

  const handlePassord = (e: BaseSyntheticEvent) => {
    setPassord(e.currentTarget.value);
  };

  const handleLogginn = async (e: FormEvent<HTMLFormElement>) => {
    const form = e.currentTarget;
    e.preventDefault();
    e.stopPropagation();

    const requestOptions: AxiosRequestConfig = {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },

      data: { brukernavn: brukernavn, passord: passord },
    };

    if (form.checkValidity() === false) {
      setValidated(false);
    } else {
      try {
        await axios(`${API_URL}/auth/logginn/`, requestOptions).then(
          (res) => {
            if (res.status === 200) {
              localStorage.token = res.data.accessToken;

              window.location.href = "/";
            } else {
              setValidated(false);
              setErrorMessage(
                "Brukernavn eller passord er ikke riktig, Vennligst prøve igjen."
              );
              inputRef.current.focus();
            }
          }
        );
      } catch (error) {
        setValidated(false);
        setErrorMessage("Brukernavn eller passord er ikke riktig, Vennligst prøve igjen.");
        inputRef.current.focus();
      }
    }

    setValidated(true);
  };

  return (
    <>
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-lg-6 pt-5">
            <Form
              className="mt-5"
              noValidate
              validated={validated}
              onSubmit={handleLogginn}
            >
              <Card>
                <Card.Header>
                  <h3 className="mb-0">Logg inn til Sivadmin</h3>
                </Card.Header>
                <Card.Body>
                  <Form.Group as={Row} controlId="brukernavn">
                    <Form.Label column sm={3}>
                      Brukernavn
                      </Form.Label>
                    <Col sm={8}>
                      <Form.Control
                        type="text"
                        ref={inputRef}
                        onChange={handleBrukernavn}
                        value={brukernavn}
                        placeholder="Brukernavn"
                        aria-describedby="inputGroupPrepend"
                        required
                      />
                      <Form.Control.Feedback type="invalid">
                        Brukernavn er påkrevd.
                        </Form.Control.Feedback>
                    </Col>
                  </Form.Group>
                  <Form.Group as={Row} controlId="passord">
                    <Form.Label column sm={3}>
                      Passord
                      </Form.Label>
                    <Col sm={8}>
                      <Form.Control
                        type="password"
                        onChange={handlePassord}
                        value={passord}
                        placeholder="Passord"
                        aria-describedby="inputGroupPrepend"
                        required
                      />
                      <Form.Control.Feedback type="invalid">
                        Passord er påkrevd.
                        </Form.Control.Feedback>
                    </Col>
                  </Form.Group>
                </Card.Body>
                <Card.Footer>
                  <Button type="submit" className="btn-primary mr-2">
                    Logg inn
                    </Button>
                  <Button type="button" className="btn-info">
                    Glemt passord ?
                    </Button>
                </Card.Footer>
              </Card>
            </Form>
            <Alert variant="error" hidden={!errorMessage}>
              {errorMessage}
            </Alert>
          </div>
        </div>
      </div>
    </>
  );
};

export default Logginn;
