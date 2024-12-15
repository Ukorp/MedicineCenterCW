import React, { useEffect, useState } from "react";
import NavHeader from "../header/NavHeader";
import PickService from "./PickService";
import PickDoctor from "./PickDoctor";
import PickDateTime from "./PickDateTime";
import Confirm from "./Confirm";



const Book = () => {

    const [stateNum, setStateNum] = useState(0);
    const states = [<PickService func = {setStateNum}/>,
                    <PickDoctor func = {setStateNum}/>,
                    <PickDateTime func = {setStateNum}/>, 
                    <Confirm func = {setStateNum}/>];

    console.log(stateNum);

    return (
        <>
        <NavHeader/>
            {states[stateNum]}
        </>
    )
}

export default Book;