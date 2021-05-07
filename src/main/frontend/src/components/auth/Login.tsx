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

const Login: FC = () => {
  const [validated, setValidated] = useState(false);

  const history = useHistory();
  const API_URL = "http://localhost:8181/api";

  const inputRef = useRef() as React.MutableRefObject<HTMLInputElement>;

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    inputRef.current.focus();
    localStorage.removeItem("user");
  }, []);

  const changeUserName = (e: BaseSyntheticEvent) => {
    setUsername(e.currentTarget.value);
  };

  const changePassword = (e: BaseSyntheticEvent) => {
    setPassword(e.currentTarget.value);
  };

  const handleLogin = async (e: FormEvent<HTMLFormElement>) => {
    const form = e.currentTarget;
    e.preventDefault();
    e.stopPropagation();

    const requestOptions: AxiosRequestConfig = {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },

      data: { username: username, password: password },
    };

    if (form.checkValidity() === false) {
      setValidated(false);
    } else {
      try {
        await axios(`${API_URL}/auth/login/`, requestOptions).then(
          (res) => {
            if (res.status === 200) {
              localStorage.token = res.data.accessToken;
              localStorage.user = JSON.stringify(res.data);

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
              onSubmit={handleLogin}
            >
              <Card>
                <Card.Header>
                  <h3 className="mb-0">Logg inn til Sivadmin</h3>
                </Card.Header>
                <Card.Body>
                  <Form.Group as={Row} controlId="username">
                    <Form.Label column sm={3}>
                      Brukernavn
                      </Form.Label>
                    <Col sm={8}>
                      <Form.Control
                        type="text"
                        ref={inputRef}
                        onChange={changeUserName}
                        value={username}
                        placeholder="Brukernavn"
                        aria-describedby="inputGroupPrepend"
                        required
                      />
                      <Form.Control.Feedback type="invalid">
                        Brukernavn er påkrevd.
                        </Form.Control.Feedback>
                    </Col>
                  </Form.Group>
                  <Form.Group as={Row} controlId="password">
                    <Form.Label column sm={3}>
                      Passord
                      </Form.Label>
                    <Col sm={8}>
                      <Form.Control
                        type="password"
                        onChange={changePassword}
                        value={password}
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

export default Login;
