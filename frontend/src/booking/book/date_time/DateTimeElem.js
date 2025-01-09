import React, { useEffect, useState } from "react";
import { Row, Col, Container, Card, Button } from "react-bootstrap";

const DateTimeElem = (props) => {
    const [disabled, setDisabled] = useState(false); 

    const handleClick = () => {
        sessionStorage.setItem("date", props.date.getTime());
        props.func(3);
    }

    useEffect(() => {
        let currentDate = new Date();
        console.log(props);
        if (currentDate.getHours() > 19) {
            currentDate.setHours(currentDate.getHours() + 5);
            console.log(currentDate);
        }
        if (props.busy.filter((elem) =>{ 
            elem.setMilliseconds(0);
            props.date.setMilliseconds(0);
            return (elem.getTime() === props.date.getTime())}).length != 0
        ) {
            setDisabled(true)
        }
        else if (props.date.getTime() < currentDate.getTime()) {
            setDisabled(true)
        }
        else {
            setDisabled(false)
        }
    }, [props]);



    return (
        <Col md = {3} className="mb-4">
        <Button variant="outline-primary" size="lg" disabled = {disabled} onClick={() => handleClick()}>
                {props.date.getHours() + ":" + ((props.date.getMinutes() < 10) ? '0' : "") + props.date.getMinutes()}

        </Button>
        </Col>
    )

}

export default DateTimeElem;