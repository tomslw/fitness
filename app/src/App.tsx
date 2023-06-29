import React, { ReactElement, useCallback, useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import { Exercise, HealthData, HealthDataShort, Intensity, Meal, MuscleGroups } from './utils/types';
import { MuscleStatus } from './components/MuscleStatus';
import { HealthSummary } from './components/HealthSummary';
import { MealsList } from './components/MealsList';
import Button from "@mui/material/Button";
import { WorkoutList } from './components/WorkoutList';
import { HealthDataList } from './components/HealthDataList';

export function App(): ReactElement {

  const [viewSoreness, setViewSoreness] = useState(true);

  const [healthList, setHealthList] = useState<Array<HealthDataShort>>(new Array<HealthDataShort>())

  // selected HealthData entry
  const [health, setHealth] = useState<HealthData> ({
    idhe: -1,
    weight: 0,
    height: 0,
    muscleGroups: {
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
    },
    diet: new Array<Meal>(),
    workout: new Array<Exercise>(),
    caloriesSpent: 0,
    date: new Date(1999, 1, 1, 1, 1, 1, 1),
  });

  const fetchHealthData = useCallback((id: number) => {
    fetch('healthData/entry/' + id)
    .then(response => response.json())
    .then(data => {
      var translateSingleHealthData: HealthData = {
              idhe: data.idhe,
              weight: data.weight,
              height: data.height,
              muscleGroups: {
                chest: Intensity[data.muscleGroups.chest as keyof typeof Intensity],
                back: Intensity[data.muscleGroups.back as keyof typeof Intensity],
                biceps: Intensity[data.muscleGroups.biceps as keyof typeof Intensity],
                triceps: Intensity[data.muscleGroups.triceps as keyof typeof Intensity],
                forearms: Intensity[data.muscleGroups.forearms as keyof typeof Intensity],
                abdomen: Intensity[data.muscleGroups.abdomen as keyof typeof Intensity],
                gluteus: Intensity[data.muscleGroups.gluteus as keyof typeof Intensity],
                hamstrings: Intensity[data.muscleGroups.hamstrings as keyof typeof Intensity],
                quadriceps: Intensity[data.muscleGroups.quadriceps as keyof typeof Intensity],
                calves: Intensity[data.muscleGroups.calves as keyof typeof Intensity],
                trapezius: Intensity[data.muscleGroups.trapezius as keyof typeof Intensity],
                deltoid: Intensity[data.muscleGroups.deltoid as keyof typeof Intensity],
              },
              diet: data.diet,
              workout: data.workout.map((item: { idex: any; title: any; description: any; restInterval: any; repetitions: any; addedWeight: any; targetMuscles: { chest: string; back: string; biceps: string; triceps: string; forearms: string; abdomen: string; gluteus: string; hamstrings: string; quadriceps: string; calves: string; trapezius: string; deltoid: string; }; }) => { return {
                idex: item.idex,
                title: item.title,
                description: item.description,
                restInterval: item.restInterval,
                repetitions: item.repetitions,
                addedWeight: item.addedWeight,
                targetMuscles: {
                  chest: Intensity[item.targetMuscles.chest as keyof typeof Intensity],
                  back: Intensity[item.targetMuscles.back as keyof typeof Intensity],
                  biceps: Intensity[item.targetMuscles.biceps as keyof typeof Intensity],
                  triceps: Intensity[item.targetMuscles.triceps as keyof typeof Intensity],
                  forearms: Intensity[item.targetMuscles.forearms as keyof typeof Intensity],
                  abdomen: Intensity[item.targetMuscles.abdomen as keyof typeof Intensity],
                  gluteus: Intensity[item.targetMuscles.gluteus as keyof typeof Intensity],
                  hamstrings: Intensity[item.targetMuscles.hamstrings as keyof typeof Intensity],
                  quadriceps: Intensity[item.targetMuscles.quadriceps as keyof typeof Intensity],
                  calves: Intensity[item.targetMuscles.calves as keyof typeof Intensity],
                  trapezius: Intensity[item.targetMuscles.trapezius as keyof typeof Intensity],
                  deltoid: Intensity[item.targetMuscles.deltoid as keyof typeof Intensity],
                },
              }}),
              caloriesSpent: data.caloriesSpent,
              date: new Date(data.date),
            }
      setHealth(translateSingleHealthData);
  });
  }, []);

  useEffect(() => {
    // having the responses being handeled here is kind of meh, 
    // should probably have a seperate Requester.ts with all the safety checks in the world
    fetch('healthData/showAll')
      .then(response => response.json())
      .then(data => {
        var translateWeirdData: Array<HealthDataShort> = new Array<HealthDataShort>();
        data.forEach((entry: { idhe: string; date: any; }) => {
          translateWeirdData.push({
            idhe: +entry.idhe,
            date: new Date(entry.date),
          })
        });
        // sort it by date
        translateWeirdData = translateWeirdData.sort((n1, n2) => n2.date.getTime() - n1.date.getTime());
        setHealthList(translateWeirdData);

        // fetch latest HealthData entry by date
        // maybe turn this into its own CALLBACK?
        fetchHealthData(translateWeirdData[0].idhe);
      })

  // otherwise we might get into a nice little loop
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);
  
  return (
    <div className="App">
      <header className="App-header">
        <div className="health-data-list">
          <HealthDataList healthDataList={healthList} setHealthDataList={setHealthList} setSelectedItem={fetchHealthData}  />
        </div>
        <div className="health-data-side">
          <HealthSummary healthData={health} setHealthData={setHealth}/>
          <MealsList healthDataId={health.idhe} meals={health.diet} setMeals={(newList) => setHealth({ ...health, diet: newList })} />
        </div>
        <div className="muscle-side">
          <div className="title">{viewSoreness ? "Muscle soreness" : "Workout routine"}</div>
          <Button variant="contained" onClick={() => setViewSoreness(prevState => !prevState)}> Switch views </Button>
          { viewSoreness ?
          <MuscleStatus parentId={health.idhe} isWorkout={false} enableEdit={true} intensityData={health.muscleGroups} setIntensityData={(data => setHealth({ ...health, muscleGroups: data }))}/>
          :
          <WorkoutList healthDataId={health.idhe} workouts={health.workout} setWorkouts={(data) => setHealth({ ...health, workout: data })} />
          }
        </div>
        
      </header>
    </div>
  );
}

export default App;
