import React, { ReactElement, useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import { Excercise } from './utils/types';

export function App(): ReactElement {

  const [excercise, setExcercise] = useState<Excercise[]>([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    // having the response beeing handeled here is kind of meh, 
    // should probably have a seperate Requester.ts with all the safety checks in the world
    fetch('exercise/showAll')
      .then(response => response.json())
      .then(data => {
        setExcercise(data);
        setLoading(false);
      })
  }, []);

  if (loading) {
    return <p>Loading...</p>;
  }

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <div className="App-intro">
          <h2>Excercise List</h2>
          {excercise.map(group =>
            <div key={group.idex}>
              {group.title}
            </div>
          )}
        </div>
      </header>
    </div>
  );
}

export default App;
