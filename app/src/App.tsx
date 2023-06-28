import React, { ReactElement, useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import { Exercise, HealthData, Intensity, Meal, MuscleGroups } from './utils/types';
import { MuscleStatus } from './components/MuscleStatus';
import { HealthSummary } from './components/HealthSummary';
import { MealsList } from './components/MealsList';
import Button from "@mui/material/Button";
import { WorkoutList } from './components/WorkoutList';
import { HealthDataList } from './components/HealthDataList';

export function App(): ReactElement {

  const [loading, setLoading] = useState(false);
  const [viewSoreness, setViewSoreness] = useState(true);

  const [healthList, setHealthList] = useState<Array<HealthData>>(new Array<HealthData>())

  // selected HealthData entry
  const [health, setHealth] = useState<HealthData> ({
    idhd: -1,
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

  useEffect(() => {
    setLoading(true);
    // having the response beeing handeled here is kind of meh, 
    // should probably have a seperate Requester.ts with all the safety checks in the world
    fetch('healthData/showAll')
      .then(response => response.json())
      .then(data => {
        console.log(data);
        
        var nonWeirdData: Array<HealthData> = new Array<HealthData>();
        data.forEach((entry: { idhd: any; weight: any; height: any; muscleGroups: { chest: string; back: string; biceps: string; triceps: string; forearms: string; abdomen: string; gluteus: string; hamstrings: string; quadriceps: string; calves: string; trapezius: string; deltoid: string; }; diet: any; workout: any; caloriesSpent: any; date: string | number | Date; }) => {
          nonWeirdData.push(
            {
              idhd: entry.idhd,
              weight: entry.weight,
              height: entry.height,
              muscleGroups: {
                chest: Intensity[entry.muscleGroups.chest as keyof typeof Intensity],
                back: Intensity[entry.muscleGroups.back as keyof typeof Intensity],
                biceps: Intensity[entry.muscleGroups.biceps as keyof typeof Intensity],
                triceps: Intensity[entry.muscleGroups.triceps as keyof typeof Intensity],
                forearms: Intensity[entry.muscleGroups.forearms as keyof typeof Intensity],
                abdomen: Intensity[entry.muscleGroups.abdomen as keyof typeof Intensity],
                gluteus: Intensity[entry.muscleGroups.gluteus as keyof typeof Intensity],
                hamstrings: Intensity[entry.muscleGroups.hamstrings as keyof typeof Intensity],
                quadriceps: Intensity[entry.muscleGroups.quadriceps as keyof typeof Intensity],
                calves: Intensity[entry.muscleGroups.calves as keyof typeof Intensity],
                trapezius: Intensity[entry.muscleGroups.trapezius as keyof typeof Intensity],
                deltoid: Intensity[entry.muscleGroups.deltoid as keyof typeof Intensity],
              },
              diet: entry.diet,
              workout: entry.workout,
              caloriesSpent: entry.caloriesSpent,
              date: new Date(entry.date),
            }
          )
        });


        setHealthList(nonWeirdData);
        setHealth(nonWeirdData[0]);
        setLoading(false);
      })
  }, []);

  if (loading) {
    return <p>Loading...</p>;
  }



  return (
    <div className="App">
      <header className="App-header">
        <div className="health-data-list">
          <HealthDataList healthDataList={healthList} setHealthDataList={() => {}} setSelectedItem={setHealth}  />
        </div>
        <div className="health-data-side">
          <HealthSummary healthData={health} setHealthData={setHealth}/>
          <MealsList meals={health.diet} setMeals={(newList) => setHealth({ ...health, diet: newList })} />
        </div>
        <div className="muscle-side">
          <div className="title">{viewSoreness ? "Muscle soreness" : "Workout routine"}</div>
          <Button variant="contained" onClick={() => setViewSoreness(prevState => !prevState)}> Switch views </Button>
          { viewSoreness ?
          <MuscleStatus enableEdit={true} intensityData={health.muscleGroups} setIntensityData={(data => setHealth({ ...health, muscleGroups: data }))}/>
          :
          <WorkoutList workouts={health.workout} setWorkouts={(data) => setHealth({ ...health, workout: data })} />
          }
        </div>
        
      </header>
    </div>
  );
}

export default App;
