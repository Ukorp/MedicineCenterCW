import React from "react";
import { Button, FormGroup, Form } from "react-bootstrap";
import "./Register.css"
import apiClient from "../../axios_api/apiClient";
import { Link, Navigate } from "react-router";
import isTokenExpired from "../isTokenExpired";

class Register extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            email: "",
            password: "",
            firstName: "",
            lastName: "",
            phoneNumber: "",
            greeting: ""
        }
        this.handleClick = this.handleClick.bind(this);
    }

    async handleClick() {
      try {
        const response = await apiClient.post("/api/v1/auth/register", {
            "firstName": this.state.firstName,
            "lastName": this.state.lastName,
            "phoneNumber": this.state.phoneNumber,
            'email': this.state.email,
            'password': this.state.password
        })
        localStorage.setItem("token", response.data.token);
  
        this.setState({greeting: `Добро пожаловать, ${this.state.user}`});
        window.location.href = "/hello-page";
      }
      catch (err) {
        console.log(err);
        this.setState({greeting: `Ошибка: ${err.message}`});
      }
    }

  render () {
    if (!isTokenExpired()) return <Navigate to="/hello-page"/>;
    return (
      <div>
        <h1 className="mx-auto d-flex flex-row justify-content-center mt-5">Регистрация</h1>
        <Form className = "register-form" >
        <FormGroup>
            <Form.Label htmlFor="firstName">Имя: </Form.Label>
            <Form.Control type="text" id="firstName" placeholder="Иван" onChange={(event) => this.setState({firstName: event.target.value})}></Form.Control>
          </FormGroup>
          <FormGroup>
            <Form.Label htmlFor="lastName">Фамилия: </Form.Label>
            <Form.Control type="text" id="lastName" placeholder="Иванов" onChange={(event) => this.setState({lastName: event.target.value})}></Form.Control>
          </FormGroup>
          <FormGroup>
            <Form.Label htmlFor="emailInput">Email: </Form.Label>
            <Form.Control type="email" id="emailInput" placeholder="Ivanov@mail.rur" onChange={(event) => this.setState({email: event.target.value})}></Form.Control>
          </FormGroup>
          <FormGroup>
            <Form.Label htmlFor="phoneNumber">Номер телефона: </Form.Label>
            <Form.Control type="text" id="phoneNumber" placeholder="+78005553535" onChange={(event) => this.setState({phoneNumber: event.target.value})}></Form.Control>
          </FormGroup>
          <FormGroup >
            <Form.Label htmlFor="passwordInput">Password: </Form.Label>
            <Form.Control type="password" id="passwordInput" placeholder="От 8 цифр" onChange={(event) => this.setState({password: event.target.value})}></Form.Control>
          </FormGroup>
          <Button className="m-4" variant ="outline-primary" onClick={this.handleClick}>Регистрация</Button>
          <Link to="/login">
            <Button  variant ="outline-secondary">Логин</Button>
          </Link>
        </Form>
        
        <div className="text-center">{this.state.greeting}</div>
      </div>
    )
  }  
}

export default Register;
