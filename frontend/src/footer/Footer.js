import React from "react";
import { Container } from "react-bootstrap";

const Footer = () => {
    return <footer className="bg-secondary text-white fixed-bottom">
        <Container fluid className="d-flex justify-content-center align-items-center">
            <span>Не является публичной офертой</span>
        </Container>
        </footer>
}

export default Footer;