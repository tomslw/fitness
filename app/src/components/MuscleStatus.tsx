import { ReactElement, useCallback } from "react";
import { Intensity, MuscleGroups } from "../utils/types";
import "./MuscleStatus.css"
import Slider from "@mui/material/Slider"

import abdomen from "../images/muscles/abdomen.png";
import back from "../images/muscles/back.png";
import biceps from "../images/muscles/biceps.png";
import calves from "../images/muscles/calves.png";
import chest from "../images/muscles/chest.png";
import deltoid from "../images/muscles/deltoid.png";
import forearms from "../images/muscles/forearms.png";
import gluteus from "../images/muscles/gluteus.png";
import hamstrings from "../images/muscles/hamstrings.png";
import quadriceps from "../images/muscles/quadriceps.png";
import trapezius from "../images/muscles/trapezius.png";
import triceps from "../images/muscles/triceps.png";

interface ListProps {
    title: String, 
    setData: (newVal: Number) => void, 
    value: Intensity, 
    enableEdit: boolean,
}

function ListItem({title, setData, value: defVal, enableEdit} : ListProps) : ReactElement {
    return (
        <div className="slider-item">
            <div className="slider-title">{title}</div>
            <Slider 
                disabled={!enableEdit}
                aria-label="back intensity"
                value={defVal}
                getAriaValueText={valuetext}
                valueLabelFormat={valuetext}
                valueLabelDisplay="auto"
                step={1}
                size="small"
                marks
                min={0}
                max={4}
                onChange={(event, value) => setData(Array.isArray(value) ? value[0] : value)}
            />
        </div>
    );
}

interface Props {
    parentId: number,
    intensityData: MuscleGroups,
    setIntensityData: (intensityData: MuscleGroups) => void,
    enableEdit: boolean,
    isWorkout: boolean,
}

function valuetext(value: number) {
    return Intensity[value];
}

export function MuscleStatus({parentId, intensityData, setIntensityData, enableEdit, isWorkout} : Props): ReactElement {

    function translateIntensity (intensity: Intensity): String {
        switch (intensity) {
            case Intensity.none: {
                return "muscle-none";
            }
            case Intensity.low: {
                return "muscle-low";
            }
            case Intensity.medium: {
                return "muscle-medium";
            }
            case Intensity.high: {
                return "muscle-high";
            }
            case Intensity.injury: {
                return "muscle-injury";
            }
        }
    }

    const handleSaveIntensityData = useCallback((newIntense: MuscleGroups) => {

        const requestOptions = {
            method: 'POST',
            body: JSON.stringify({
                chest: Intensity[newIntense.chest],
                back: Intensity[newIntense.back],
                biceps: Intensity[newIntense.biceps],
                triceps: Intensity[newIntense.triceps],
                forearms: Intensity[newIntense.forearms],
                abdomen: Intensity[newIntense.abdomen],
                gluteus: Intensity[newIntense.gluteus],
                hamstrings: Intensity[newIntense.hamstrings],
                quadriceps: Intensity[newIntense.quadriceps],
                calves: Intensity[newIntense.calves],
                trapezius: Intensity[newIntense.trapezius],
                deltoid: Intensity[newIntense.deltoid],
            }),
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json',
            },
        };

        fetch((isWorkout ? 'muscle/updateByExercise/' : 'muscle/updateByHealthData/') + parentId, requestOptions);
            // .then(response => response.json())
            // .then(data => {
            //     console.log(data);
            //     // check if success i guess
            // });
        
    }, [isWorkout, parentId]);

    return (
        <div className="body-window">
            <div className="muscle-container">
                <img src={abdomen} alt="abdomen" className={"muscle " + translateIntensity(intensityData.abdomen)}/>
                <img src={back} alt="back" className={"muscle " + translateIntensity(intensityData.back)}/>
                <img src={biceps} alt="biceps" className={"muscle " + translateIntensity(intensityData.biceps)}/>
                <img src={deltoid} alt="deltoid" className={"muscle " + translateIntensity(intensityData.deltoid)}/>
                <img src={chest} alt="chest" className={"muscle " + translateIntensity(intensityData.chest)}/>
                <img src={calves} alt="calves" className={"muscle " + translateIntensity(intensityData.calves)}/>
                <img src={forearms} alt="forearms" className={"muscle " + translateIntensity(intensityData.forearms)}/>
                <img src={gluteus} alt="gluteus" className={"muscle " + translateIntensity(intensityData.gluteus)}/>
                <img src={hamstrings} alt="hamstrings" className={"muscle " + translateIntensity(intensityData.hamstrings)}/>
                <img src={quadriceps} alt="quadriceps" className={"muscle " + translateIntensity(intensityData.quadriceps)}/>
                <img src={trapezius} alt="trapezius" className={"muscle " + translateIntensity(intensityData.trapezius)}/>
                <img src={triceps} alt="triceps" className={"muscle " + translateIntensity(intensityData.triceps)}/>
            </div>
            <div className="middle-ui">
                <ListItem 
                    title={"Abdomen"} 
                    setData={(value) => {
                        var newGroup = {...intensityData, abdomen: value as Intensity};
                        setIntensityData(newGroup);
                        handleSaveIntensityData (newGroup);
                    }}
                    value={intensityData.abdomen}
                    enableEdit={enableEdit}
                />
                <ListItem title={"Back"} 
                    setData={(value) => {
                        var newGroup = {...intensityData, back: value as Intensity};
                        setIntensityData(newGroup);
                        handleSaveIntensityData (newGroup);
                    }}
                    value={intensityData.back} 
                    enableEdit={enableEdit}
                />
                <ListItem title={"Biceps"} 
                    setData={(value) => {
                        var newGroup = {...intensityData, biceps: value as Intensity};
                        setIntensityData(newGroup);
                        handleSaveIntensityData (newGroup);
                    }}
                    value={intensityData.biceps} 
                    enableEdit={enableEdit}
                />
                <ListItem title={"Deltoid"} 
                    setData={(value) => {
                        var newGroup = {...intensityData, deltoid: value as Intensity};
                        setIntensityData(newGroup);
                        handleSaveIntensityData (newGroup);
                    }}
                    value={intensityData.deltoid} 
                    enableEdit={enableEdit}
                />
                <ListItem title={"Chest"} 
                    setData={(value) => {
                        var newGroup = {...intensityData, chest: value as Intensity};
                        setIntensityData(newGroup);
                        handleSaveIntensityData (newGroup);
                    }}
                    value={intensityData.chest} 
                    enableEdit={enableEdit}
                />
                <ListItem title={"Calves"} 
                    setData={(value) => {
                        var newGroup = {...intensityData, calves: value as Intensity};
                        setIntensityData(newGroup);
                        handleSaveIntensityData (newGroup);
                    }}
                    value={intensityData.calves} 
                    enableEdit={enableEdit}
                />
                <ListItem title={"Forearms"} 
                    setData={(value) => {
                        var newGroup = {...intensityData, forearms: value as Intensity};
                        setIntensityData(newGroup);
                        handleSaveIntensityData (newGroup);
                    }}
                    value={intensityData.forearms} 
                    enableEdit={enableEdit}
                />
                <ListItem title={"Gluteus"} 
                    setData={(value) => {
                        var newGroup = {...intensityData, gluteus: value as Intensity};
                        setIntensityData(newGroup);
                        handleSaveIntensityData (newGroup);
                    }}
                    value={intensityData.gluteus} 
                    enableEdit={enableEdit}
                />
                <ListItem title={"Hamstrings"} 
                    setData={(value) => {
                        var newGroup = {...intensityData, hamstrings: value as Intensity};
                        setIntensityData(newGroup);
                        handleSaveIntensityData (newGroup);
                    }}
                    value={intensityData.hamstrings} 
                    enableEdit={enableEdit}
                />
                <ListItem title={"Quadriceps"} 
                    setData={(value) => {
                        var newGroup = {...intensityData, quadriceps: value as Intensity};
                        setIntensityData(newGroup);
                        handleSaveIntensityData (newGroup);
                    }}
                    value={intensityData.quadriceps} 
                    enableEdit={enableEdit}
                />
                <ListItem title={"Trapezius"} 
                    setData={(value) => {
                        var newGroup = {...intensityData, trapezius: value as Intensity};
                        setIntensityData(newGroup);
                        handleSaveIntensityData (newGroup);
                    }}
                    value={intensityData.trapezius} 
                    enableEdit={enableEdit}
                />
                <ListItem title={"Triceps"} 
                    setData={(value) => {
                        var newGroup = {...intensityData, triceps: value as Intensity};
                        setIntensityData(newGroup);
                        handleSaveIntensityData (newGroup);
                    }}
                    value={intensityData.triceps} 
                    enableEdit={enableEdit}
                />

                

            </div>
        </div>
    );
}