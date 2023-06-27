import React, { ReactElement, useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';

export function App(): ReactElement {

  const [groups, setGroups] = useState<Excercise[]>([]);
  const [loading, setLoading] = useState(false);

  // temporay

interface Excercise {
    idex: number;
    title: String;
    description: String;
    restInterval: number;
    repetitions: number;
    addedWeight: number
    // health data goes here, once that has been defined, for now not nessesary
}

  useEffect(() => {
    setLoading(true);

    fetch('exercise/showAll')
      .then(response => response.json())
      .then(data => {
        setGroups(data);
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
          {groups.map(group =>
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
