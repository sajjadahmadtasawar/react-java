import React, { FC } from "react";
import { Link } from "react-router-dom";
import { useLocation } from "react-router-dom";

const Sidebar: FC = () => {
  const location = useLocation();

  return (
    <div id="layoutSidenav_nav">
      <nav
        className="sb-sidenav accordion sb-sidenav-dark"
        id="sidenavAccordion"
      >
        <div className="sb-sidenav-menu">
          <div className="nav">
            <Link
              className={
                location.pathname === "/"
                  ? "nav-link active"
                  : "nav-link"
              }
              to={`/`}
            >
              <div className="sb-nav-link-icon">
                <i className="fas fa-server"></i>
              </div>
              Dashboard
            </Link>
            <div className="sb-sidenav-menu-heading">Undersøkelse</div>

            <Link
              className={
                location.pathname.includes("prosjekt")
                  ? "nav-link active"
                  : "nav-link"
              }
              to={`/prosjekt`}
            >
              <div className="sb-nav-link-icon">
                <i className="fas fa-bell"></i>
              </div>
              Prosjekt
            </Link>
            <div className="sb-sidenav-menu-heading">Administrasjon</div>
            <div className="sb-sidenav-menu-heading">Intervjuer</div>
          </div>
        </div>
      </nav>
    </div>
  );
};
export default Sidebar;
