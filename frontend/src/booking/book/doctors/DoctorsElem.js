import React from "react";
import { Col, Card, Button } from "react-bootstrap";

const DoctorsElem = (props) => {

    const handleClick = () => {
        sessionStorage.setItem("doctorName", props.doctor.name);
        sessionStorage.setItem("doctorId", props.doctor.id);
        sessionStorage.setItem("branchName", props.doctor.branchId.name)
        sessionStorage.setItem("branchAddress", props.doctor.branchId.address)
        props.func(2);
    }

    return (
        <Col md = {3} className="mb-4">
        <Card className = "h-100">
            <Card.Body className="d-flex flex-column justify-content-between">
                <Card.Title>{props.doctor.name}</Card.Title>
                <Card.Text>{props.doctor.branchId.name}</Card.Text>
                <Card.Text>{props.doctor.branchId.address}</Card.Text>
                <Button variant="primary" onClick={() => handleClick()}>Записаться</Button>
            </Card.Body>
        </Card>
        </Col>
    )

}

export default DoctorsElem;