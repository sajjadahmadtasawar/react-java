import React, { FC } from "react";
import { Link } from "react-router-dom";
import { useLocation } from "react-router-dom";
import {
  BsBook,
  BsBookHalf,
  BsBriefcase,
  BsBriefcaseFill,
  BsBuilding,
  BsFillCollectionFill,
  BsFillCollectionPlayFill,
  BsFillGrid1X2Fill,
  BsFillInboxesFill,
  BsFillInboxFill,
  BsPeopleFill,
  BsReplyAll,
  BsReplyAllFill,
  BsReverseLayoutTextSidebarReverse,
  BsSearch,
  BsSkipBackward,
  BsSkipBackwardFill,
  BsSkipForward,
  BsSkipForwardFill,
  BsSubtract,
  BsTextIndentLeft,
  BsTools,
} from "react-icons/bs";

import { FaSearchPlus } from "react-icons/fa";
import { FcBusinessman, FcManager } from "react-icons/fc";
import { GrCluster } from "react-icons/gr";

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
            {/* <div className="sb-sidenav-menu-heading">
              Velkommen til Sivadmin
            </div>

            <Link
              className={
                location.pathname === "/" ? "nav-link active" : "nav-link"
              }
              to={`/`}
            >
              <div className="sb-nav-link-icon">
                <i className="fas fa-server"></i>
              </div>
              Dashboard
            </Link> */}
            <div className="sb-sidenav-menu-heading">Undersøkelse</div>
            <Link
              className={
                location.pathname === "/" ? "nav-link active" : "nav-link"
              }
              to={`/`}
            >
              <div className="sb-nav-link-icon">
                <i className="fas fa-server"></i>
              </div>
              Dashboard
            </Link>
            <Link
              className={
                location.pathname.includes("prosjekter")
                  ? "nav-link active"
                  : "nav-link"
              }
              to={`/prosjekter`}
            >
              <div className="sb-nav-link-icon">
                {location.pathname.includes("prosjekter") ? (
                  <BsBriefcaseFill />
                ) : (
                  <BsBriefcase />
                )}
              </div>
              Prosjekt
            </Link>
            <Link
              className={
                location.pathname.includes("skjemaer")
                  ? "nav-link active"
                  : "nav-link"
              }
              to={`/skjemaer`}
            >
              <div className="sb-nav-link-icon">
                {location.pathname.includes("skjemaer") ? (
                  <BsFillInboxesFill />
                ) : (
                  <BsFillInboxFill />
                )}
              </div>
              Skjema
            </Link>
            <Link
              className={
                location.pathname.includes("utvalg")
                  ? "nav-link active"
                  : "nav-link"
              }
              to={`/utvalg`}
            >
              <div className="sb-nav-link-icon">
                {location.pathname.includes("utvalg") ? (
                  <BsFillCollectionPlayFill />
                ) : (
                  <BsFillCollectionFill />
                )}
              </div>
              Utvalg
            </Link>

            <div className="sb-sidenav-menu-heading">Administrasjon</div>
            <Link
              className={
                location.pathname.includes("digikorr")
                  ? "nav-link active"
                  : "nav-link"
              }
              to={`/digikorr`}
            >
              <div className="sb-nav-link-icon">
                {location.pathname.includes("digikorr") ? (
                  <BsFillGrid1X2Fill />
                ) : (
                  <BsFillGrid1X2Fill />
                )}
              </div>
              DigiKorr metadata
            </Link>

            <Link
              className={
                location.pathname.includes("io_sok")
                  ? "nav-link active"
                  : "nav-link"
              }
              to={`/io_sok`}
            >
              <div className="sb-nav-link-icon">
                {location.pathname.includes("io_sok") ? (
                  <BsSearch />
                ) : (
                  <BsSearch />
                )}
              </div>
              Intervjuobjekt Søk
            </Link>
            <Link
              className={
                location.pathname.includes("lagrede_sok")
                  ? "nav-link active"
                  : "nav-link"
              }
              to={`/lagrede_sok`}
            >
              <div className="sb-nav-link-icon">
                {location.pathname.includes("lagrede_sok") ? (
                  <FaSearchPlus />
                ) : (
                  <FaSearchPlus />
                )}
              </div>
              Lagrede Søk
            </Link>
            <Link
              className={
                location.pathname.includes("intervjuer")
                  ? "nav-link active"
                  : "nav-link"
              }
              to={`/intervjuer`}
            >
              <div className="sb-nav-link-icon">
                {location.pathname.includes("intervjuer") ? (
                  <FcBusinessman />
                ) : (
                  <FcBusinessman />
                )}
              </div>
              Intervjuer
            </Link>

            <Link
              className={
                location.pathname.includes("prosjektLeder")
                  ? "nav-link active"
                  : "nav-link"
              }
              to={`/prosjektLeder`}
            >
              <div className="sb-nav-link-icon">
                {location.pathname.includes("prosjektLeder") ? (
                  <FcManager />
                ) : (
                  <FcManager />
                )}
              </div>
              Prosjektleder
            </Link>

            <Link
              className={
                location.pathname.includes("kommune")
                  ? "nav-link active"
                  : "nav-link"
              }
              to={`/kommune`}
            >
              <div className="sb-nav-link-icon">
                {location.pathname.includes("kommune") ? (
                  <BsBuilding />
                ) : (
                  <BsBuilding />
                )}
              </div>
              Kommune
            </Link>

            <Link
              className={
                location.pathname.includes("klynge")
                  ? "nav-link active"
                  : "nav-link"
              }
              to={`/klynge`}
            >
              <div className="sb-nav-link-icon">
                {location.pathname.includes("klynge") ? (
                  <BsSubtract />
                ) : (
                  <BsSubtract />
                )}
              </div>
              Klynge
            </Link>

            <Link
              className={
                location.pathname.includes("logg")
                  ? "nav-link active"
                  : "nav-link"
              }
              to={`/logg`}
            >
              <div className="sb-nav-link-icon">
                {location.pathname.includes("logg") ? (
                  <BsReverseLayoutTextSidebarReverse />
                ) : (
                  <BsReverseLayoutTextSidebarReverse />
                )}
              </div>
              Hendelseslogg
            </Link>

            <Link
              className={
                location.pathname.includes("brukere")
                  ? "nav-link active"
                  : "nav-link"
              }
              to={`/brukere`}
            >
              <div className="sb-nav-link-icon">
                {location.pathname.includes("brukere") ? (
                  <BsPeopleFill />
                ) : (
                  <BsPeopleFill />
                )}
              </div>
              Brukere
            </Link>

            <Link
              className={
                location.pathname.includes("produkt")
                  ? "nav-link active"
                  : "nav-link"
              }
              to={`/produkt`}
            >
              <div className="sb-nav-link-icon">
                {location.pathname.includes("produkt") ? (
                  <BsTools />
                ) : (
                  <BsTools />
                )}
              </div>
              Produkt
            </Link>

            <Link
              className={
                location.pathname.includes("melding_til_blaise")
                  ? "nav-link active"
                  : "nav-link"
              }
              to={`/melding_til_blaise`}
            >
              <div className="sb-nav-link-icon">
                {location.pathname.includes("melding_til_blaise") ? (
                  <BsSkipForwardFill />
                ) : (
                  <BsSkipForward />
                )}
              </div>
              Melding til Blaise
            </Link>

            <Link
              className={
                location.pathname.includes("melding_fra_blaise")
                  ? "nav-link active"
                  : "nav-link"
              }
              to={`/melding_fra_blaise`}
            >
              <div className="sb-nav-link-icon">
                {location.pathname.includes("melding_fra_blaise") ? (
                  <BsSkipBackwardFill />
                ) : (
                  <BsSkipBackward />
                )}
              </div>
              Melding fra Blaise
            </Link>

            <div className="sb-sidenav-menu-heading">Intervjuer</div>
            <Link
              className={
                location.pathname.includes("intervjuer_rapport")
                  ? "nav-link active"
                  : "nav-link"
              }
              to={`/intervjuer_rapport`}
            >
              <div className="sb-nav-link-icon">
                {location.pathname.includes("intervjuer_rapport") ? (
                  <BsBookHalf />
                ) : (
                  <BsBook />
                )}
              </div>
              Intervjuerrapport
            </Link>
            <Link
              className={
                location.pathname.includes("egne_resultater")
                  ? "nav-link active"
                  : "nav-link"
              }
              to={`/egne_resultater`}
            >
              <div className="sb-nav-link-icon">
                {location.pathname.includes("egne_resultater") ? (
                  <BsTextIndentLeft />
                ) : (
                  <BsTextIndentLeft />
                )}
              </div>
              Egne resultater
            </Link>
          </div>
        </div>
      </nav>
    </div>
  );
};
export default Sidebar;
