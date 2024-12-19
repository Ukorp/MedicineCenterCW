import React from "react";
import Login from "../auth/login/Login";
import PrivateRoute from "../router/PrivateRoute";
import HelloPage from "../start_page/HelloPage";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'react-phone-number-input/style.css'
import { BrowserRouter, Routes, Route, Navigate } from "react-router";
import Register from "../auth/register/Register";
import Archive from "../booking/archive/Archive";
import Book from "../booking/book/Book";
import Relevant from "../booking/relevant/Relevant";
import AdminPanel from "../admin/AdminPanel";

class App extends React.Component {

  getUser() {
    return localStorage.getItem("user");
  }

  render () {
    return (
      <BrowserRouter>
        <Routes>
          <Route path = "/login" element = {<Login/>}/>
          <Route path = "/register" element = {<Register/>}/>
          <Route path = "/booking/book" element = {<Book/>}/>
          <Route path = "/hello-page" element = {<PrivateRoute><HelloPage user = {this.getUser() ? this.getUser() : "unknown"} /></PrivateRoute>}/>
          <Route path = "*" element = {<Navigate to="/login"/>}/>
          <Route path = "/booking/archived" element = {<Archive/>}/>
          <Route path = "/booking/relevant" element = {<Relevant/>}/>
          <Route path = "/admin/panel" element = {<AdminPanel/>}/>
        </Routes>
      </BrowserRouter>
    )
  }
}

export default App;
