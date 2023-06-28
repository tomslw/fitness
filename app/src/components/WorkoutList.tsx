import { ReactElement, useEffect, useState } from "react"
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
    workouts: Array<Exercise>,
    setWorkouts: (newData: Array<Exercise>) => void
}

export function WorkoutList({workouts, setWorkouts} : Props): ReactElement {

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
  
      const handleDelete = () => {
          setWorkouts(workouts.filter((value, index) => index !== selectedWorkout));
          handleClose()
      }
  
      const addNewWorkout = () => {
        //temp
          setWorkouts([...workouts, {
            idex: 0,
            title: "new workout",
            description: "just do it",
            restInterval: 2,
            repetitions: 0,
            addedWeight: 0,
            targetMuscles: {
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
            }
          }]);
          handleClickOpen(workouts.length);
      }

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
                            value={selectedWorkout != null ? workouts[selectedWorkout].title : ''}
                            variant="standard"
                            onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                                const newWorkouts = workouts;
                                if (selectedWorkout != null) {
                                    newWorkouts[selectedWorkout] = {...newWorkouts[selectedWorkout], title: event.target.value};
                                }
                                setWorkouts(newWorkouts);
                            }}
                        />
                        <TextField
                            autoFocus
                            margin="dense"
                            id="weight"
                            label="Rest Interval"
                            type="text"
                            fullWidth
                            value={selectedWorkout != null ? workouts[selectedWorkout].restInterval : ''}
                            variant="standard"
                            onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                                const newWorkouts = workouts;
                                if (selectedWorkout != null) {
                                    newWorkouts[selectedWorkout] = {...newWorkouts[selectedWorkout], restInterval: +event.target.value};
                                }
                                setWorkouts(newWorkouts);
                            }}
                        />
                        <TextField
                            autoFocus
                            margin="dense"
                            id="weight"
                            label="Repetitions"
                            type="text"
                            fullWidth
                            value={selectedWorkout != null ? workouts[selectedWorkout].repetitions : ''}
                            variant="standard"
                            onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                                const newWorkouts = workouts;
                                if (selectedWorkout != null) {
                                    newWorkouts[selectedWorkout] = {...newWorkouts[selectedWorkout], repetitions: +event.target.value};
                                }
                                setWorkouts(newWorkouts);
                            }}
                        />
                        <TextField
                            autoFocus
                            margin="dense"
                            id="weight"
                            label="Added Weight"
                            type="text"
                            fullWidth
                            value={selectedWorkout != null ? workouts[selectedWorkout].addedWeight : ''}
                            variant="standard"
                            onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                                const newWorkouts = workouts;
                                if (selectedWorkout != null) {
                                    newWorkouts[selectedWorkout] = {...newWorkouts[selectedWorkout], addedWeight: +event.target.value};
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