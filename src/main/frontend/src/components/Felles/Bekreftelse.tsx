import React, { FC } from "react";

import Button from "react-bootstrap/esm/Button";
import Modal from "react-bootstrap/esm/Modal";

interface ExternalProps {
  melding: string;
  tittel: string;
  visBekreftelse: boolean;
  bekreft: () => void;
  avbryt: () => void;
}

const Bekreftelse: FC<ExternalProps> = ({
  melding,
  tittel,
  visBekreftelse,
  bekreft,
  avbryt,
}) => {
  function lagMarkup() {
    return { __html: melding };
  }

  return (
    <>
      <Modal show={visBekreftelse} size="lg">
        <Modal.Header closeButton>
          <Modal.Title>{tittel}</Modal.Title>
        </Modal.Header>
        <Modal.Body className="px-4">
          <span dangerouslySetInnerHTML={lagMarkup()}></span>
        </Modal.Body>
        <Modal.Footer className="justify-content-center">
          <Button type="submit" variant="danger" onClick={bekreft}>
            <i className="fas fa-trash"></i> Bekreft
          </Button>
          <Button variant="secondary" onClick={avbryt}>
            Avbryt
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default Bekreftelse;
