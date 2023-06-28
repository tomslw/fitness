import { ReactElement } from "react";
import { Exercise, HealthData, Intensity, Meal } from "../utils/types";
import "./HealthDataList.css"

import Button from "@mui/material/Button";


interface Props {
    healthDataList: Array<HealthData>,
    setHealthDataList: (newData: Array<HealthData>) => void,
    setSelectedItem: (newSelection: HealthData) => void,
}

export function HealthDataList({healthDataList, setHealthDataList, setSelectedItem} : Props): ReactElement {

    const addEntry = () => {
        // get request to backend to get a fresh entry, i need the idmg
        // fresh HealthData entry AND a MuscleGroups entry

        // setHealthDataList([...healthDataList, {
        //     weight: 60,
        //     height: 180,
        //     muscleGroups: {
        //       chest: Intensity.none,
        //       back: Intensity.none,
        //       biceps: Intensity.none,
        //       triceps: Intensity.none,
        //       forearms: Intensity.none,
        //       abdomen: Intensity.none,
        //       gluteus: Intensity.none,
        //       hamstrings: Intensity.none,
        //       quadriceps: Intensity.none,
        //       calves: Intensity.none,
        //       trapezius: Intensity.none,
        //       deltoid: Intensity.none,
        //     },
        //     diet: new Array<Meal>(),
        //     workout: new Array<Exercise>(),
        //     caloriesSpent: 0,
        //     date: new Date(),
        //   }]);
        setSelectedItem(healthDataList[healthDataList.length]);
        // sort by date tho
    };

    console.log(healthDataList[0]);

    return (
        <>
            <div className="health-list-container">
                <Button variant="contained" onClick={addEntry} className="add-entry-button">Add new entry</Button>
                {
                    healthDataList.map((value, index) =>
                    // wont actually save it locally in the list tho, is basically going to be a local variable
                    <div className="health-item" onClick={() => setSelectedItem(healthDataList[index])}>
                        <div className="health-name">{value.date.toLocaleDateString()}</div>
                    </div>
                    )
                }
            </div>
        </>
    );
}