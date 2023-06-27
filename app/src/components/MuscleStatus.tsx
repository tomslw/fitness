import { ReactElement } from "react";
import { Intensity, MuscleGroups } from "../utils/types";
import "./MuscleStatus.css"

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

interface Props {
    intensityData: MuscleGroups
}

export function MuscleStatus({intensityData} : Props): ReactElement {

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
    );
}