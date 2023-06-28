import { ReactElement, useCallback, useEffect, useState } from "react"
import { Exercise, Intensity, MuscleGroups } from "../utils/types"
import { MuscleStatus } from "./MuscleStatus";
import "./WorkoutList.css";

import Button from "@mui/material/Button";
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';

interface Props {
    healthDataId: number,
    workouts: Array<Exercise>,
    setWorkouts: (newData: Array<Exercise>) => void
}

export function WorkoutList({healthDataId, workouts, setWorkouts} : Props): ReactElement {

    const [workoutIntensity, setWorkoutIntensity] = useState<MuscleGroups>({
        chest: Intensity.none,
        back: Intensity.none,
        biceps: Intensity.none,
        triceps: Intensity.none,
        forearms: Intensity.none,
        abdomen: Intensity.none,
        gluteus: Intensity.none,
        hamstrings: Intensity.none,
        quadriceps: Intensity.none,
        calves: Intensity.none,
        trapezius: Intensity.none,
        deltoid: Intensity.none,
      });

    const [open, setOpen] = useState(false);
    // index
    const [selectedWorkout, setSelectedWorkout] = useState<number | null>();

    const handleClickOpen = (i: number) => {
        setSelectedWorkout(i);
        setOpen(true);
    };

    const handleClose = () => {
        setSelectedWorkout(null);
        setOpen(false);
    };

    const handleDelete = useCallback( () => {
        if (selectedWorkout != null) {
            fetch('exercise/delete/' + workouts[selectedWorkout].idex);
                // .then(response => response.json())
                // .then(data => {
                //     console.log(data);
                // });
        }

        setWorkouts(workouts.filter((value, index) => index !== selectedWorkout));
        handleClose();
    }, [selectedWorkout, setWorkouts, workouts]);

    const addNewWorkout = useCallback(() => {
        
        fetch('exercise/insertNewExercise/' + healthDataId)
            .then(response => response.json())
            .then(data => {
                console.log("Response data:");
                console.log(data);
                setWorkouts( [ ...workouts, {
                    idex: data.idex,
                    title: data.title,
                    description: data.description,
                    restInterval: data.restInterval,
                    repetitions: data.repetitions,
                    addedWeight: data.addedWeight,
                    targetMuscles: {
                      chest: Intensity[data.targetMuscles.chest as keyof typeof Intensity],
                      back: Intensity[data.targetMuscles.back as keyof typeof Intensity],
                      biceps: Intensity[data.targetMuscles.biceps as keyof typeof Intensity],
                      triceps: Intensity[data.targetMuscles.triceps as keyof typeof Intensity],
                      forearms: Intensity[data.targetMuscles.forearms as keyof typeof Intensity],
                      abdomen: Intensity[data.targetMuscles.abdomen as keyof typeof Intensity],
                      gluteus: Intensity[data.targetMuscles.gluteus as keyof typeof Intensity],
                      hamstrings: Intensity[data.targetMuscles.hamstrings as keyof typeof Intensity],
                      quadriceps: Intensity[data.targetMuscles.quadriceps as keyof typeof Intensity],
                      calves: Intensity[data.targetMuscles.calves as keyof typeof Intensity],
                      trapezius: Intensity[data.targetMuscles.trapezius as keyof typeof Intensity],
                      deltoid: Intensity[data.targetMuscles.deltoid as keyof typeof Intensity],
                    },
                }]);
            });

    }, [healthDataId, setWorkouts, workouts])

        useEffect(() => {
            const totalIntensity = {
                chest: Intensity.none,
                back: Intensity.none,
                biceps: Intensity.none,
                triceps: Intensity.none,
                forearms: Intensity.none,
                abdomen: Intensity.none,
                gluteus: Intensity.none,
                hamstrings: Intensity.none,
                quadriceps: Intensity.none,
                calves: Intensity.none,
                trapezius: Intensity.none,
                deltoid: Intensity.none,
        };
        // holly ternary statement
        workouts.forEach((item) => {
            totalIntensity.chest = item.targetMuscles.chest > totalIntensity.chest ? item.targetMuscles.chest : totalIntensity.chest;
            totalIntensity.back = item.targetMuscles.back > totalIntensity.back ? item.targetMuscles.back : totalIntensity.back;
            totalIntensity.biceps = item.targetMuscles.biceps > totalIntensity.biceps ? item.targetMuscles.biceps : totalIntensity.biceps;
            totalIntensity.triceps = item.targetMuscles.triceps > totalIntensity.triceps ? item.targetMuscles.triceps : totalIntensity.triceps;
            totalIntensity.forearms = item.targetMuscles.forearms > totalIntensity.forearms ? item.targetMuscles.forearms : totalIntensity.forearms;
            totalIntensity.abdomen = item.targetMuscles.abdomen > totalIntensity.abdomen ? item.targetMuscles.abdomen : totalIntensity.abdomen;
            totalIntensity.gluteus = item.targetMuscles.gluteus > totalIntensity.gluteus ? item.targetMuscles.gluteus : totalIntensity.gluteus;
            totalIntensity.hamstrings = item.targetMuscles.hamstrings > totalIntensity.hamstrings ? item.targetMuscles.hamstrings : totalIntensity.hamstrings;
            totalIntensity.quadriceps = item.targetMuscles.quadriceps > totalIntensity.quadriceps ? item.targetMuscles.quadriceps : totalIntensity.quadriceps;
            totalIntensity.calves = item.targetMuscles.calves > totalIntensity.calves ? item.targetMuscles.calves : totalIntensity.calves;
            totalIntensity.trapezius = item.targetMuscles.trapezius > totalIntensity.trapezius ? item.targetMuscles.trapezius : totalIntensity.trapezius;
            totalIntensity.deltoid = item.targetMuscles.deltoid > totalIntensity.deltoid ? item.targetMuscles.deltoid : totalIntensity.deltoid;
        });
        setWorkoutIntensity(totalIntensity);
      }, [workouts, setWorkouts]);

      const handleUpdatePost = useCallback((newValue: Exercise) => {
        const requestOptions = {
            method: 'POST',
            body: JSON.stringify(newValue),
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json',
            },
        };

        fetch('exercise/update/' + newValue.idex, requestOptions);
            // .then(response => response.json())
            // .then(data => {
            //     console.log(data);
            //     // check if success i guess
            // });
    }, []);

    return (
        <>
            <MuscleStatus parentId={-1} isWorkout={true} enableEdit={false} intensityData={workoutIntensity} setIntensityData={(data => {})}/>
            <div className="workout-row">
                {
                    workouts.map((value, index) => 
                        <div className="workout-item" onClick={() => handleClickOpen(index)}> 
                            <div className="workout-title"> {value.title} </div>
                            <div className="workout-stat"> {value.repetitions + " reps"} </div>
                            <div className="workout-stat"> {value.restInterval + " min rest"} </div>
                        </div>
                    )
                }
            </div>
            <Dialog 
                open={open} 
                onClose={handleClose}
                >

                    <DialogTitle>Modify health data</DialogTitle>
                    <DialogContent>
                        {selectedWorkout != null ?
                        (                        
                            <MuscleStatus
                                parentId={workouts[selectedWorkout].idex}
                                isWorkout={true}
                                enableEdit={true} 
                                intensityData={workouts[selectedWorkout].targetMuscles} 
                                setIntensityData={(data => {
                                    const newWorkouts = workouts;
                                    if (selectedWorkout != null) {
                                        newWorkouts[selectedWorkout] = {...newWorkouts[selectedWorkout], targetMuscles: data};
                                    }
                                    setWorkouts(newWorkouts);
                                })}
                            />
                        )
                        :
                        ''
                    }

                        <TextField
                            autoFocus
                            margin="dense"
                            id="weight"
                            label="Workout name"
                            type="text"
                            fullWidth
                            defaultValue={selectedWorkout != null ? workouts[selectedWorkout].title : ''}
                            variant="standard"
                            onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
								const titleRegex = /^[A-Z]{1}[a-z\\ ]+$/
                                const newWorkouts = workouts;
                                if (selectedWorkout != null) {
									if(event.target.value.length>=3 && event.target.value.length<=20 && titleRegex.test(event.target.value)){
										newWorkouts[selectedWorkout] = {...newWorkouts[selectedWorkout], title: event.target.value};
                                    	handleUpdatePost(newWorkouts[selectedWorkout]);
									}
                                    else{
										newWorkouts[selectedWorkout] = {...newWorkouts[selectedWorkout], title: "Exercise"};
                                    	handleUpdatePost(newWorkouts[selectedWorkout]);
									}
                                }
                                setWorkouts(newWorkouts);
                            }}
                        />
                        <TextField
                            autoFocus
                            margin="dense"
                            id="weight"
                            label="Rest Interval"
                            type="number"
                            fullWidth
                            defaultValue={selectedWorkout != null ? workouts[selectedWorkout].restInterval : ''}
                            variant="standard"
                            onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                                const newWorkouts = workouts;
                                if (selectedWorkout != null) {
									if(Number(event.target.value)>=0&&Number(event.target.value)<=10){
	                                    newWorkouts[selectedWorkout] = {...newWorkouts[selectedWorkout], restInterval: +event.target.value};
	                                    handleUpdatePost(newWorkouts[selectedWorkout]);
	                                }
	                                else{
										newWorkouts[selectedWorkout] = {...newWorkouts[selectedWorkout], restInterval: 1};
	                                    handleUpdatePost(newWorkouts[selectedWorkout]);
									}
                                }
                                setWorkouts(newWorkouts);
                            }}
                        />
                        <TextField
                            autoFocus
                            margin="dense"
                            id="weight"
                            label="Repetitions"
                            type="number"
                            fullWidth
                            defaultValue={selectedWorkout != null ? workouts[selectedWorkout].repetitions : ''}
                            variant="standard"
                            onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                                const newWorkouts = workouts;
                                if (selectedWorkout != null) {
                                    if(Number(event.target.value)>=1&&Number(event.target.value)<=100){
	                                    newWorkouts[selectedWorkout] = {...newWorkouts[selectedWorkout], repetitions: +event.target.value};
	                                    handleUpdatePost(newWorkouts[selectedWorkout]);
	                                }
	                                else{
										newWorkouts[selectedWorkout] = {...newWorkouts[selectedWorkout], repetitions: 1};
	                                    handleUpdatePost(newWorkouts[selectedWorkout]);
									}
                                }
                                setWorkouts(newWorkouts);
                            }}
                        />
                        <TextField
                            autoFocus
                            margin="dense"
                            id="weight"
                            label="Added Weight"
                            type="number"
                            fullWidth
                            defaultValue={selectedWorkout != null ? workouts[selectedWorkout].addedWeight : ''}
                            variant="standard"
                            onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                                const newWorkouts = workouts;
                                if (selectedWorkout != null) {
                                    if(Number(event.target.value)>=1&&Number(event.target.value)<=1000){
	                                    newWorkouts[selectedWorkout] = {...newWorkouts[selectedWorkout], addedWeight: +event.target.value};
	                                    handleUpdatePost(newWorkouts[selectedWorkout]);
	                                }
	                                else{
										newWorkouts[selectedWorkout] = {...newWorkouts[selectedWorkout], addedWeight: 1};
	                                    handleUpdatePost(newWorkouts[selectedWorkout]);
									}
                                }
                                setWorkouts(newWorkouts);
                            }}
                        />

                    </DialogContent>
                    <DialogActions>
                        <Button color="warning" variant="contained" onClick={handleDelete}>Delete</Button>
                        <Button onClick={handleClose}>Close</Button>
                    </DialogActions>
                </Dialog>
                <Button variant="contained" onClick={addNewWorkout} className="add-meal-button">Add workout</Button>
        </>
    )
}