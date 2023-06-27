import React, { ReactElement, useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import { Exercise, HealthData, Intensity, Meal, MuscleGroups } from './utils/types';
import { MuscleStatus } from './components/MuscleStatus';
import { HealthSummary } from './components/HealthSummary';

export function App(): ReactElement {

  const [exercise, setExercise] = useState<Exercise[]>([]);
  const [loading, setLoading] = useState(false);

  const [intensity, setIntensity] = useState<MuscleGroups>({
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
  })

  const [health, setHealth] = useState<HealthData> ({
    weight: 60,
    height: 180,
    morning_muscle_fatigue: intensity,
    diet: new Array<Meal>(),
    workout: new Array<Exercise>(),
    calories_spent: 2000,
    date_time: new Date(2023, 4, 8, 10, 13, 41, 12),
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
          <HealthSummary />
        </div>
        <div className="muscle-side">
          <div className="title">Muscle soreness</div>
          <MuscleStatus intensityData={intensity} setIntensityData={setIntensity}/>
        </div>
        
      </header>
    </div>
  );
}

export default App;
