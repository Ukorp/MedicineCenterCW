import React, { useEffect, useState } from "react";
import { Row, Col, Container, Card, Button } from "react-bootstrap";
import apiClient from "../../apiClient";

const date = ["января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря"];
const day = ["Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"];

const AdminListElem = (props) => {

    const deleteUser = async () => {
        try {
            await apiClient.delete(`api/v1/admin/user/delete?userId=${props.user.id}`);
            window.location.reload();
        }catch(err) {
            console.log(err);
        }
    }

    return (
        <Col md = {3}>
        <Card className = "h-100">
            <Card.Body >
                <Card.Title>{props.user.email}</Card.Title>
                <Card.Text>{props.user.firstName + ' ' + props.user.lastName}</Card.Text>
                <Card.Text>{props.user.phoneNumber}</Card.Text>
                <Card.Text>{props.user.role}</Card.Text>
                <Button variant="danger" onClick={deleteUser}>Удалить запись</Button>
            </Card.Body>
        </Card>
        </Col>
    )

}

export default AdminListElem;