import React, { useState, useEffect }  from "react";
import { Container, Row } from "react-bootstrap";
import ListElem from "./ListElemArchive";
import apiClient from "../../axios_api/apiClient";
import NavHeader from "../../header/NavHeader";

const Archive = () => {

        const [bookings, setBookings] = useState([]);
        const [bookingArray, setBookingArray] = useState([]);

        const makeRow = (array, size) => {
            const result = [];
            for (let i = 0; i < array.length; i += size) {
                result.push(array.slice(i, i + size));
            }
            return result
        }

    useEffect(() => {
        const fetchGet = async () => {
            const response = await apiClient.get("api/v1/booking/archive");
            setBookings(response.data)
            console.log(response.data)
        }

        fetchGet();
    }, []);    

    useEffect(() => {
        if (bookings.length > 0) {
            setBookingArray(makeRow(bookings, 4));
        }
    }, [bookings]);

    console.log(bookingArray);
    return (
        <>
        <NavHeader/>
        <Container className="xl my-3"><h1>Прошедшие записи</h1></Container>
        <Container className="mt-4">
            {bookingArray.map(
                (elem, index) => {
                return <Row   key = {index}>
                        {elem.map((col, index) => <ListElem key = {index} booking = {col}/>)}
                    </Row>
                }
                )
            }
        </Container>
        </>
    )
}

export default Archive;