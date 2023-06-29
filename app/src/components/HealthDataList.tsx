import { ReactElement, useCallback } from "react";
import { Exercise, HealthData, HealthDataShort, Intensity, Meal } from "../utils/types";
import "./HealthDataList.css"

import Button from "@mui/material/Button";


interface Props {
    healthDataList: Array<HealthDataShort>,
    setHealthDataList: (newData: Array<HealthDataShort>) => void,
    setSelectedItem: (id: number) => void,
}

export function HealthDataList({healthDataList, setHealthDataList, setSelectedItem} : Props): ReactElement {

    const addEntry = useCallback(() => {
        // TODO: maybe check if theres an entry for todays date or not

        fetch('healthData/getFresh')
            .then(response => response.json())
            .then(data => {
                setSelectedItem(data.idhe);
                setHealthDataList( [ {
                    idhe: data.idhe,
                    date: new Date(data.date),
                }, 
                ...healthDataList,
                ])
            });
    }, [healthDataList, setHealthDataList, setSelectedItem]);

    return (
        <>
            <div className="health-list-container">
                <Button variant="contained" onClick={addEntry} className="add-entry-button">Add new entry</Button>
                {
                    healthDataList.map((value, index) =>
                    // wont actually save it locally in the list tho, is basically going to be a local variable
                    <div className="health-item" onClick={() => setSelectedItem(value.idhe)}>
                        <div className="health-name">{value.date.toLocaleDateString()}</div>
                    </div>
                    )
                }
            </div>
        </>
    );
}