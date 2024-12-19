import React from "react";
import {Col, Card} from "react-bootstrap";

const date = ["января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря"];
const day = ["Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"];

const ListElem = (props) => {
    const localDate = new Date(props.booking.date.replace('T', ' '));
    console.dir(localDate.getDate());
    return (
        <Col md = {3} className="mb-4">
        <Card className = "h-100">
            <Card.Body >
                <Card.Title>{props.booking.serviceName}</Card.Title>
                <Card.Text>{props.booking.doctorName}</Card.Text>
                <Card.Text>{props.booking.branchName}</Card.Text>
                <Card.Text>{props.booking.branchAddress}</Card.Text>
                <Card.Text>{day[localDate.getDay()] + ', ' + localDate.getDate() + ' ' + date[localDate.getMonth()] + ', ' + localDate.getFullYear() + ' ' + localDate.getHours() + ':' + (localDate.getMinutes() < 10 ? '0' : '') + localDate.getMinutes()}</Card.Text>
                <Card.Text>{props.booking.price} руб</Card.Text>
            </Card.Body>
        </Card>
        </Col>
    )
}

export default ListElem;