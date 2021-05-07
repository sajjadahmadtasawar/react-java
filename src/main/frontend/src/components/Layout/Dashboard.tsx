import React, { FC } from "react";
import { Link } from "react-router-dom";

const Dashbaord: FC = () => {
  return (
    <>
      <h3 className="mt-4">Velkommen til SivAdmin</h3>
      <p>Dette er en hjelpeapplikasjon for å administrere og gjennomføre intervjuundersøkelser i Statistisk sentralbyrå.</p>

      <div className="row">
        <div className="col-xl-10 col-md-10">
          <div className="card bg-dark text-white mb-4">
            <div className="card-header">Snarvei til hoved funksjonaliteter</div>
            <div className="card-body">
              <div className="d-flex">
                <Link to="/prosjekt/leggTil" className="btn btn-primary ml-2">
                  Legg til ny Prosjekt
                </Link>
                <Link to="/alerts/add" className="btn btn-warning ml-2">
                  Add new Alert
                </Link>
                <Link to="/alerts/add" className="btn btn-info ml-2">
                  Add new Team
                </Link>
                <Link to="/alerts/add" className="btn btn-danger ml-2">
                  Add new Business Domain
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
export default Dashbaord;
