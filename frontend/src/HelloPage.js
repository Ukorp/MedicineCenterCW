import React, { useEffect, useState } from "react";
import LogoutButton from "./LogoutButton";
import apiClient from "./apiClient";
import NavHeader from "./header/NavHeader";
import { Spinner, Container } from "react-bootstrap";
import Slider from "./Slider";

const HelloPage = () => {
    
    const [user, setUser] = useState(null);

    useEffect(() => {
        const fetchGet = async () => {
            try {
                const response = await apiClient.get('api/v1/user/info');
                setUser(response.data);
                localStorage.setItem("role", response.data.role);
              } catch (err) {
                console.error(err);
              }
        };
        fetchGet()
    }, []);

    return (<>
            <NavHeader user = {user}/>
            
            <h1>Начало курсачу положено, {user === null ? <Spinner animation="border" role="status"/> : user.firstName}!</h1>
            <Container className="d-flex justify-content-center">
            <Slider/>
            </Container>
        </>
    )
}

export default HelloPage