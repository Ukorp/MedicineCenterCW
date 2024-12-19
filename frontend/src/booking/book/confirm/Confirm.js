import React, { useState } from "react";
import { Container, Card, Button } from "react-bootstrap";
import apiClient from "../../../axios_api/apiClient";
import ModalElem from "./ModalElem";

const date = ["января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря"];
const day = ["Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"];

const Confirm = (props) => {

    const [show, setShow] = useState(false);
    const [text, setText] = useState("Loading...");

    let tmp = +sessionStorage.getItem("date");
    const localDate = new Date();
    localDate.setTime(tmp);
    const handleClick = async () => {
        try {
            localDate.setHours(localDate.getHours() + 3);
            let response = await apiClient.post("api/v1/booking/book", {
                "serviceId": +sessionStorage.getItem("serviceId"),
                "doctorId": +sessionStorage.getItem("doctorId"),
                "date": localDate.toISOString().substring(0, 19)
            });
            console.log(response);
            setShow(true);
            sessionStorage.clear();
            setText("Успешно!");
        }
        catch (err) {
            setText(`Произошла ошибка: ${err.message}`);
        }
    }

    return (
        <>
        <ModalElem showModal = {show} text = {text}/>
        <Container className="my-4 col-4">
        <Card className = "h-100 ">
            <Card.Body >
                <Card.Title>{sessionStorage.getItem("serviceName")}</Card.Title>
                <Card.Text> Доктор: {sessionStorage.getItem("doctorName")}</Card.Text>
                <Card.Text> Стоимость: {sessionStorage.getItem("servicePrice")}</Card.Text>
                <Card.Text> Филиал: {sessionStorage.getItem("branchName")}</Card.Text>
                <Card.Text> Адрес: {sessionStorage.getItem("branchAddress")}</Card.Text>
                <Card.Text> Дата: {day[localDate.getDay()] + ', ' + localDate.getDate() + ' ' + date[localDate.getMonth()] + ', ' + localDate.getFullYear() + ' ' + localDate.getHours() + ':' + (localDate.getMinutes() < 10 ? '0' : '') + localDate.getMinutes()}</Card.Text>
                <Button variant="primary" onClick={() => handleClick()}>Подтвердить запись</Button>
            </Card.Body>
        </Card>
        </Container>
        </>
    )

}

export default Confirm;