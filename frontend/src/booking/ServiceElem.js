import React, { useEffect, useState } from "react";
import { Row, Col, Container, Card, Button } from "react-bootstrap";

const ServiceElem = (props) => {

    const handleClick = () => {
        sessionStorage.setItem("serviceName", props.service.name);
        sessionStorage.setItem("serviceId", props.service.id);
        sessionStorage.setItem("servicePrice", props.service.price);
        props.func(1);
    }

    return (
        <Col md = {3} className="mb-4">
        <Card className = "h-100">
            <Card.Body >
                <Card.Title>{props.service.name}</Card.Title>
                <Card.Text>{props.service.price} руб</Card.Text>
                <Button variant="primary" onClick={() => handleClick()}>Записаться</Button>
            </Card.Body>
        </Card>
        </Col>
    )

}

export default ServiceElem;