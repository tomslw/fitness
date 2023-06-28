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

  const [loading, setLoading] = useState(false);
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
    setLoading(true);
    fetch('healthData/entry/' + id)
    .then(response => response.json())
    .then(data => {
      console.log(data);
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
              workout: data.workout,
              caloriesSpent: data.caloriesSpent,
              date: new Date(data.date),
            }
      setHealth(translateSingleHealthData);
      setLoading(false);
  });
  }, []);

  useEffect(() => {
    setLoading(true);
    // having the responses being handeled here is kind of meh, 
    // should probably have a seperate Requester.ts with all the safety checks in the world
    fetch('healthData/showAll')
      .then(response => response.json())
      .then(data => {
        console.log(data);
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

  if (loading) {
    return <p>Loading...</p>;
  }



  return (
    <div className="App">
      <header className="App-header">
        <div className="health-data-list">
          <HealthDataList healthDataList={healthList} setHealthDataList={setHealthList} setSelectedItem={fetchHealthData}  />
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
