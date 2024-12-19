import { useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';

function ModalElem(props) {
  const [show, setShow] = useState(props.showModal);


  useEffect(() => {
    setShow(props.showModal);
  }, [props.showModal])

  return (
    <>
      <Modal show={show} onHide={() => window.location.href = "/"}>
        <Modal.Header closeButton>
          <Modal.Title>Результат записи</Modal.Title>
        </Modal.Header>
        <Modal.Body>{props.text}</Modal.Body>
        <Modal.Footer>
          <Button variant="primary" onClick={() => window.location.href = "/"}>
            На главную
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default ModalElem;