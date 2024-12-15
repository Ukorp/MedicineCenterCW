import React, { useEffect, useState } from "react";
import { Row, Col, Container, Card, Button } from "react-bootstrap";
import apiClient from "../../apiClient";

const date = ["января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря"];
const day = ["Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"];

const LogElem = (props) => {


    const localDate = new Date(props.log.changeTime.replace('T', ' '));
    console.log(props.log.Button);


    return (
        <Col md = {3}>
        <Card className = "h-100">
            <Card.Body >
                <Card.Title>{props.log.tableName}</Card.Title>
                <Card.Text>{props.log.action}</Card.Text>
                <Card.Text>{props.log.details}</Card.Text>
                <Card.Text>{day[localDate.getDay()] + ', ' + localDate.getDate() + ' ' + date[localDate.getMonth()] + ', ' + localDate.getFullYear() + ' ' + localDate.getHours() + ':' + (localDate.getMinutes() < 10 ? '0' : '') + localDate.getMinutes()}</Card.Text>
            </Card.Body>
        </Card>
        </Col>
    )

}

export default LogElem;