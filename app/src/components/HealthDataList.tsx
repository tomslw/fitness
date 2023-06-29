import { ReactElement, useCallback } from "react";
import { Exercise, HealthData, HealthDataShort, Intensity, Meal } from "../utils/types";
import "./HealthDataList.css"

import Button from "@mui/material/Button";
import Snackbar from '@mui/material/Snackbar';
import MuiAlert, { AlertProps } from '@mui/material/Alert';
import React from "react";


const Alert = React.forwardRef<HTMLDivElement, AlertProps>(function Alert(
    props,
    ref,
  ) {
    return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
  });

interface Props {
    healthDataList: Array<HealthDataShort>,
    setHealthDataList: (newData: Array<HealthDataShort>) => void,
    setSelectedItem: (id: number) => void,
}

export function HealthDataList({healthDataList, setHealthDataList, setSelectedItem} : Props): ReactElement {

    const [open, setOpen] = React.useState(false);

    const addEntry = useCallback(() => {
        // TODO: maybe check if theres an entry for todays date or not

        const todayFilter = healthDataList.filter((item) => {
            var leDate = item.date;
            var now = new Date();
            return (leDate.getDate() === now.getDate() && leDate.getMonth() === now.getMonth() && leDate.getFullYear() === now.getFullYear());
        })
        if (todayFilter.length === 0) {
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
        } else {
            setOpen(true);
        }

    }, [healthDataList, setHealthDataList, setSelectedItem]);

    console.log(healthDataList[0]);

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
            <Snackbar open={open} autoHideDuration={6000} onClose={() => setOpen(false)}>
                <Alert onClose={() => setOpen(false)} severity="error" sx={{ width: '100%' }}>
                    There's already an entry for today!
                </Alert>
            </Snackbar>
        </>
    );
}