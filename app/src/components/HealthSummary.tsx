import { ReactElement, useCallback, useState } from "react";
import { HealthData } from "../utils/types";
import "./HealthSummary.css";
import Button from "@mui/material/Button";
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';


interface Props {
    healthData: HealthData,
    setHealthData: (newData: HealthData) => void
}

export function HealthSummary({healthData, setHealthData} : Props): ReactElement {
    const [open, setOpen] = useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };
    
    const handleClose = () => {
        setOpen(false);
    };

    const updateHealthData = useCallback((updatedHD: HealthData) => {
        const requestOptions = {
            method: 'POST',
            body: JSON.stringify(updatedHD),
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json',
            },
        };

        fetch('healthData/update/' + updatedHD.idhe, requestOptions)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                // check if success i guess
            });
    }, []);

    const [weightError, setWeightError] = useState(false);
    const [heightError, setHeightError] = useState(false);
    const [caloriesError, setCaloriesError] = useState(false);

    return (
        <div className="base-container">
            <div className="selected-item-title">{healthData.date.toLocaleString()}</div>
            <div className="stats">
                <div className="stat-entry">{"Weight: " + healthData.weight + "kg"}</div>
                <div className="stat-entry">{"Height: " + healthData.height + "cm"}</div>
                <div className="stat-entry">{"Calories Spent: " + healthData.caloriesSpent + "kcal"}</div>
            </div>
            <Button variant="contained" onClick={handleClickOpen}>
                edit
            </Button>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>Modify health data</DialogTitle>
                <DialogContent>
                    <TextField
                        autoFocus
                        margin="dense"
                        id="weight"
                        label="Weight"
                        type="number"
                        fullWidth
                        variant="standard"
                        defaultValue={healthData.weight /* this might spawn a bug when switching between entries */ }
                        error={weightError}
                        helperText={weightError ? "0-500" : ""}
                        onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                            const err = (+event.target.value < 0 || +event.target.value > 500);
                            setWeightError(err);
                            
                            if (!err) {
                                const updatedHD = {...healthData, weight: +event.target.value};
                                updateHealthData(updatedHD)
                                setHealthData(updatedHD)
                            }
                        }}
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        id="height"
                        label="Height"
                        type="number"
                        fullWidth
                        variant="standard"
                        defaultValue={healthData.height}
                        error={heightError}
                        helperText={heightError ? "0-300" : ""}
                        onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                            const err = (+event.target.value < 0 || +event.target.value > 300);
                            setHeightError(err);
                            
                            if (!err) {
                                const updatedHD = {...healthData, height: +event.target.value};
                                updateHealthData(updatedHD)
                                setHealthData(updatedHD)
                            }
                        }}
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        id="calories"
                        label="Calories Spent"
                        type="number"
                        fullWidth
                        variant="standard"
                        defaultValue={healthData.caloriesSpent}
                        error={caloriesError}
                        helperText={caloriesError ? "0-10000" : ""}
                        onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                            const err = (+event.target.value < 0 || +event.target.value > 10000);
                            setCaloriesError(err);
                            
                            if (!err) {
                                const updatedHD = {...healthData, caloriesSpent: +event.target.value};
                                updateHealthData(updatedHD)
                                setHealthData(updatedHD)
                            }
                        }}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Close</Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}