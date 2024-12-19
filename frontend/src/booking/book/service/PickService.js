import React, { useState, useEffect }  from "react";
import { Container, Row } from "react-bootstrap";
import apiClient from "../../../axios_api/apiClient";
import ServiceElem from "./ServiceElem";

const PickService = (props) => {

        const [services, setServices] = useState([]);
        const [servicesArray, setServicesArray] = useState([]);

        const makeRow = (array, size) => {
            const result = [];
            for (let i = 0; i < array.length; i += size) {
                result.push(array.slice(i, i + size));
            }
            return result
        }

    useEffect(() => {
        const fetchGet = async () => {
            const response = await apiClient.get("api/v1/service/all");
            setServices(response.data)
        }

        fetchGet();
    }, []);    

    useEffect(() => {
        if (services.length > 0) {
            setServicesArray(makeRow(services, 4));
        }
    }, [services]);

    return (
        <>
        <Container className="xl my-3"><h1>Доступные услуги:</h1></Container>
        <Container className="mt-4">
            {servicesArray.map(
                (elem, index) => {
                return <Row key = {index}>
                        {elem.map((col, index) => <ServiceElem func = {props.func} key = {index} service = {col}/>)}
                    </Row>
                }
                )
            }
        </Container>
        </>
    )
}

export default PickService;