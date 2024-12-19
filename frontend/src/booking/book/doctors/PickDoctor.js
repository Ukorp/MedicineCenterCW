import React, { useState, useEffect }  from "react";
import { Container, Row } from "react-bootstrap";
import apiClient from "../../../axios_api/apiClient";
import DoctorsElem from "./DoctorsElem";

const PickDoctor = (props) => {

        const [doctors, setDoctors] = useState([]);
        const [doctorsArray, setDoctorsArray] = useState([]);

        const makeRow = (array, size) => {
            const result = [];
            for (let i = 0; i < array.length; i += size) {
                result.push(array.slice(i, i + size));
            }
            return result
        }

    useEffect(() => {
        const fetchGet = async () => {
            const response = await apiClient.get(`/api/v1/doctor/byService?serviceId=${sessionStorage.getItem("serviceId")}`);
            setDoctors(response.data);
            
        }

        fetchGet();
    }, []);    

    useEffect(() => {
        if (doctors.length > 0) {
            setDoctorsArray(makeRow(doctors, 4));
        }
    }, [doctors]);

    return (
        <>
        <Container className="xl my-3"><h1>Выберите врача:</h1></Container>
        <Container className="mt-4">
            {doctorsArray.map(
                (elem, index) => {
                return <Row key = {index}>
                        {elem.map((col, index) => <DoctorsElem func = {props.func} key = {index} doctor = {col}/>)}
                    </Row>
                }
                )
            }
        </Container>
        </>
    )
}

export default PickDoctor;