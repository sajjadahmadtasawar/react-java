import { hentBrukerInfo } from "helpers/utils";
import React, { FC, useState } from "react";
import { Button, Navbar } from "react-bootstrap";
import { Link } from "react-router-dom";
import { useHistory } from "react-router-dom";

const NavbarLayout: FC = () => {
  const [token, setToken] = useState(localStorage.token);
  const history = useHistory();
  const [bruker, setBruker] = useState(hentBrukerInfo());

  const handleLoggUt = () => {
    localStorage.removeItem("bruker");
    setToken("");

    history.push("/logginn");
  };

  const authLinks = (
    <Navbar bg="light" className="p-1 px-5" expand="md">
      <Navbar.Brand href="#home"><img src="./logo.png" /></Navbar.Brand>
      <div className="ml-auto">
        <span className="navbar-text mr-3">
          Logget inn som {bruker?.brukernavn}
        </span>
        <Button onClick={handleLoggUt} variant="outline-info">Logg ut</Button>
      </div>
    </Navbar>

  );

  const guestLinks = (
    <Navbar bg="light" expand="lg">
      <Navbar.Brand href="#home"><img src="./logo.png" /> Sivadmin</Navbar.Brand>
      <div className="ml-auto">
        <Link to="/logginn" className="nav-link">
          Logg inn
        </Link>
      </div>

    </Navbar>

  );

  return (

    token ? authLinks : guestLinks

  );
};

export default NavbarLayout;
