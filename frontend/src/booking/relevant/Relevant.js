import React, { useState, useEffect }  from "react";
import { Container, Row } from "react-bootstrap";
import apiClient from "../../axios_api/apiClient";
import NavHeader from "../../header/NavHeader";
import ListElemRelevant from "./ListElemRelevant";

const Relevant = () => {

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
            const response = await apiClient.get("api/v1/booking/relevant");
            setBookings(response.data)
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
        <Container className="xl my-3"><h1>Текущие записи</h1></Container>
        <Container className="mt-4">
            {bookingArray.map(
                (elem, index) => {
                return <Row className="mb-4"  key = {index}>
                        {elem.map((col, index) => <ListElemRelevant key = {index} booking = {col}/>)}
                    </Row>
                }
                )
            }
        </Container>
        </>
    )
}

export default Relevant;