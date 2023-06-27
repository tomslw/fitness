import React, { ReactElement, useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import { Exercise, HealthData, Intensity, Meal, MuscleGroups } from './utils/types';
import { MuscleStatus } from './components/MuscleStatus';
import { HealthSummary } from './components/HealthSummary';
import { MealsList } from './components/MealsList';

export function App(): ReactElement {

  const [exercise, setExercise] = useState<Exercise[]>([]);
  const [loading, setLoading] = useState(false);

  // selected HealthData entry
  const [health, setHealth] = useState<HealthData> ({
    weight: 60,
    height: 180,
    morningMuscleFatigue: {
      chest: Intensity.high,
      back: Intensity.low,
      biceps: Intensity.low,
      triceps: Intensity.low,
      forearms: Intensity.low,
      abdomen: Intensity.low,
      gluteus: Intensity.medium,
      hamstrings: Intensity.low,
      quadriceps: Intensity.low,
      calves: Intensity.low,
      trapezius: Intensity.low,
      deltoid: Intensity.low,
    },
    diet: new Array<Meal>({    
      title: "pica",
      description: "neveseligs ediens",
      calories: 5000,
      fat: 100,
      carbohydrates: 101,
      protein: 20,
    },
    {    
      title: "Hamburgier",
      description: "amerikano",
      calories: 500,
      fat: 80,
      carbohydrates: 150,
      protein: 15,
    }),
    workout: new Array<Exercise>(),
    caloriesSpent: 2000,
    dateTime: new Date(2023, 4, 8, 10, 13, 41, 12),
  })

  useEffect(() => {
    setLoading(true);
    // having the response beeing handeled here is kind of meh, 
    // should probably have a seperate Requester.ts with all the safety checks in the world
    fetch('exercise/showAll')
      .then(response => response.json())
      .then(data => {
        setExercise(data);
        setLoading(false);
      })
  }, []);

  if (loading) {
    return <p>Loading...</p>;
  }



  return (
    <div className="App">
      <header className="App-header">
        <div className="health-data-side">
          <HealthSummary healthData={health} setHealthData={setHealth}/>
          <MealsList meals={health.diet} setMeals={(newList) => setHealth({ ...health, diet: newList })} />
        </div>
        <div className="muscle-side">
          <div className="title">Muscle soreness</div>
          <MuscleStatus intensityData={health.morningMuscleFatigue} setIntensityData={(data => setHealth({ ...health, morningMuscleFatigue: data }))}/>
        </div>
        
      </header>
    </div>
  );
}

export default App;
