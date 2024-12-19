import React, { useEffect, useState } from "react";
import NavHeader from "../../header/NavHeader";
import PickService from "./service/PickService";
import PickDoctor from "./doctors/PickDoctor";
import PickDateTime from "./date_time/PickDateTime";
import Confirm from "./confirm/Confirm";



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