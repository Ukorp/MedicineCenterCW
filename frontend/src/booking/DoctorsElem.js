import React, { useEffect, useState } from "react";
import { Row, Col, Container, Card, Button } from "react-bootstrap";

const DoctorsElem = (props) => {

    const handleClick = () => {
        sessionStorage.setItem("doctorName", props.doctor.name);
        sessionStorage.setItem("doctorId", props.doctor.id);
        props.func(2);
    }

    return (
        <Col md = {3} className="mb-4">
        <Card className = "h-100">
            <Card.Body >
                <Card.Title>{props.doctor.name}</Card.Title>
                <Button variant="primary" onClick={() => handleClick()}>Записаться</Button>
            </Card.Body>
        </Card>
        </Col>
    )

}

export default DoctorsElem;