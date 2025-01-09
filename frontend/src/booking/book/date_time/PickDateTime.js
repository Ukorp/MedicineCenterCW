import React, { useState, useEffect }  from "react";
import { Container, Pagination, Row } from "react-bootstrap";
import apiClient from "../../../axios_api/apiClient";
import DateTimeElem from "./DateTimeElem";


const PickDateTime = (props) => {

        const [dates, setDates] = useState([]);
        const [datesArray, setDatesArray] = useState([]);
        const [currentNumber, setCurrentNumber] = useState(0);
        const [picks, setPicks] = useState([]);
        const [userDates, setUserDates] = useState([]);
        const allDates = ["8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00"];

        useEffect(() => {
            const formatedDates = allDates.map(elem => {
                const newDate = new Date();
                const hm = elem.split(":");
                newDate.setHours(+hm[0]);
                newDate.setMinutes(+hm[1]);
                newDate.setSeconds(0);
                return newDate;
            });
    
            setPicks(formatedDates);
            console.log(formatedDates);
            console.log(picks);
        }, []);

        const makeRow = (array, size) => {
            const result = [];
            for (let i = 0; i < array.length; i += size) {
                result.push(array.slice(i, i + size));
            }
            return result
        }

    useEffect(() => {
        const fetchGet = async () => {
            const response = await apiClient.get(`/api/v1/doctor/bookings?doctorId=${sessionStorage.getItem("doctorId")}`);
            const response2 = await apiClient.get(`/api/v1/booking/relevant`);
            setDates(response.data.map(elem => new Date(elem)));
            setUserDates(response2.data.map(elem => new Date(elem.date)));
        }

        fetchGet();
    }, []);    

    useEffect(() => {
            setDatesArray(makeRow(picks, 4));
    }, [dates, picks]);

    const setDateForAll = (date) => {
        setPicks(picks.map(elem => {
            elem.setDate(elem.getDate() + date - currentNumber);
            return elem;
        }));
        setCurrentNumber(date);
    }

    let items = [];
    let currentDate = new Date();


    for (let number = 0; number <= 25; ++number) {
        items.push(
            <Pagination.Item key={number} onClick={() => setDateForAll(number)} active={number === currentNumber}>
            {currentDate.getDate()}
            </Pagination.Item>);

        currentDate.setDate(currentDate.getDate() + 1);
    }

    return (
        <>        
        <Container className="xl my-3"><h1>Выберите дату: </h1></Container>
        <Container><Pagination size="bg" >{items}</Pagination></Container>
        <Container className="mt-4">
            {
            datesArray.map(
                (elem, index) => {
                
                return <Row key = {index}> 
                        {elem.map((col, index) => <DateTimeElem func = {props.func} key = {index} busy = {[...dates, ...userDates, new Date()]} date = {col}/>)}
                    </Row>
                }
                )
            }
        </Container>
        </>
    )
}

export default PickDateTime;