import React from "react";
import {Navigate} from "react-router-dom";
import isTokenExpired from "../auth/isTokenExpired";

const PrivateRoute = ({children}) => {
    return (!isTokenExpired()) ? children : <Navigate to="/login"/>;
}

export default PrivateRoute