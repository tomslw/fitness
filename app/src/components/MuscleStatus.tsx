import { ReactElement } from "react";
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
    defVal: Intensity, 
    intensityData: MuscleGroups
}

function ListItem({title, setData, defVal, intensityData} : ListProps) : ReactElement {
    return (
        <div className="slider-item">
            <div className="slider-title">{title}</div>
            <Slider 
                aria-label="back intensity"
                defaultValue={defVal}
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
    intensityData: MuscleGroups,
    setIntensityData: (intensityData: MuscleGroups) => void
}

function valuetext(value: number) {
    return Intensity[value];
}

export function MuscleStatus({intensityData, setIntensityData} : Props): ReactElement {

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
                <img src={triceps} alt="calves" className={"muscle " + translateIntensity(intensityData.triceps)}/>
            </div>
            <div className="middle-ui">
                <ListItem 
                    title={"Abdomen"} 
                    setData={(value) => setIntensityData({...intensityData, abdomen: value as Intensity})} 
                    defVal={intensityData.abdomen} intensityData={intensityData}
                />
                <ListItem title={"Back"} 
                    setData={(value) => setIntensityData({...intensityData, back: value as Intensity})}
                    defVal={intensityData.back} 
                    intensityData={intensityData}
                />
                <ListItem title={"Biceps"} 
                    setData={(value) => setIntensityData({...intensityData, biceps: value as Intensity})}
                    defVal={intensityData.biceps} 
                    intensityData={intensityData}
                />
                <ListItem title={"Deltoid"} 
                    setData={(value) => setIntensityData({...intensityData, deltoid: value as Intensity})}
                    defVal={intensityData.deltoid} 
                    intensityData={intensityData}
                />
                <ListItem title={"Chest"} 
                    setData={(value) => setIntensityData({...intensityData, chest: value as Intensity})}
                    defVal={intensityData.chest} 
                    intensityData={intensityData}
                />
                <ListItem title={"Calves"} 
                    setData={(value) => setIntensityData({...intensityData, calves: value as Intensity})}
                    defVal={intensityData.calves} 
                    intensityData={intensityData}
                />
                <ListItem title={"Forearms"} 
                    setData={(value) => setIntensityData({...intensityData, forearms: value as Intensity})}
                    defVal={intensityData.forearms} 
                    intensityData={intensityData}
                />
                <ListItem title={"Gluteus"} 
                    setData={(value) => setIntensityData({...intensityData, gluteus: value as Intensity})}
                    defVal={intensityData.gluteus} 
                    intensityData={intensityData}
                />
                <ListItem title={"Hamstrings"} 
                    setData={(value) => setIntensityData({...intensityData, hamstrings: value as Intensity})}
                    defVal={intensityData.hamstrings} 
                    intensityData={intensityData}
                />
                <ListItem title={"Quadriceps"} 
                    setData={(value) => setIntensityData({...intensityData, quadriceps: value as Intensity})}
                    defVal={intensityData.quadriceps} 
                    intensityData={intensityData}
                />
                <ListItem title={"Trapezius"} 
                    setData={(value) => setIntensityData({...intensityData, trapezius: value as Intensity})}
                    defVal={intensityData.trapezius} 
                    intensityData={intensityData}
                />
                <ListItem title={"Calves"} 
                    setData={(value) => setIntensityData({...intensityData, calves: value as Intensity})}
                    defVal={intensityData.calves} 
                    intensityData={intensityData}
                />

                

            </div>
        </div>
    );
}