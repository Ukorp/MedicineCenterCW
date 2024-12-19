import React from "react";
import { Navbar, Nav, Container, NavLink, NavDropdown } from "react-bootstrap";
import "./NavHeader.css";
import LogoutButton from "../auth/LogoutButton";

class NavHeader extends React.Component {
    constructor(props) {
        super(props); 
        this.state = {
            user: this.props.user
        }  
    }

    componentDidUpdate(prevProps) {
        if (prevProps.user !== this.props.user) {
            this.setState({ user: this.props.user });
        }
    }

    render() {
        
        return (
            <header>
                <Navbar expand = "lg" className="bg-light">
                    <Container className="align-items-center justify-content-center">
                        <Navbar.Brand href="/hello-page">Мед Часть</Navbar.Brand>
                        <Navbar.Toggle aria-controls="navbar-nav"/>
                        <Navbar.Collapse className="justify-content-end" id = "navbar-nav">
                            <Nav id="nav-list" >
                                <NavLink className="me-lg-3" href="/booking/book">Записаться</NavLink>
                                <NavLink className="me-lg-3" href="/booking/relevant">Записи</NavLink>
                                <NavLink className="me-lg-3" href="/booking/archived">Архив</NavLink>
                                <NavDropdown  title="Профиль" className="d-flex flex-column me-lg-3 text-center center-text" id ="nav-dropdown">
                                    <NavDropdown.Item href="/">Профиль</NavDropdown.Item>
                                    <NavDropdown.Item href="/">Изменить</NavDropdown.Item>
                                    { (localStorage.getItem("role")=== "ADMIN") ? (<NavDropdown.Item href="/admin/panel">Админ панель</NavDropdown.Item>) : null}
                                    <NavDropdown.Divider/>
                                    <Container><LogoutButton/></Container>
                                </NavDropdown>
                            </Nav>
                        </Navbar.Collapse>
                    </Container>
                </Navbar>
            </header>
        )
    }
}

export default NavHeader;