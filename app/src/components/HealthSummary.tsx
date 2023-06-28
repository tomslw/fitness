import { ReactElement, useState } from "react";
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


    return (
        <div className="base-container">
            <div className="selected-item-title">{healthData.dateTime.toLocaleString()}</div>
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
                        onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                            setHealthData({...healthData, weight: +event.target.value})
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
                        onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                            setHealthData({...healthData, height: +event.target.value})
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
                        onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                            setHealthData({...healthData, caloriesSpent: +event.target.value})
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