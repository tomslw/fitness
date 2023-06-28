import { ReactElement, useState } from "react";
import { Meal } from "../utils/types";
import "./MealsList.css";

import Button from "@mui/material/Button";
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';

interface Props {
    meals: Array<Meal>,
    setMeals: (newData: Array<Meal>) => void
}

export function MealsList({meals, setMeals} : Props): ReactElement {

    const [open, setOpen] = useState(false);
    // index
    const [selectedMeal, setSelectedMeal] = useState<number | null>();

    const handleClickOpen = (i: number) => {
        setSelectedMeal(i);
        setOpen(true);
    };
    
    const handleClose = () => {
        setSelectedMeal(null);
        setOpen(false);
    };

    function totalKcal() {
        const sum = meals.reduce((sum, current) => sum + current.calories, 0)
        return sum
    }

    const handleDelete = () => {
        setMeals(meals.filter((value, index) => index !== selectedMeal));
        handleClose()
    }

    const addNewMeal = () => {
        setMeals([...meals, {    
            title: "New meal entry!",
            description: "unused variable",
            calories: 300,
            fat: 0,
            carbohydrates: 0,
            protein: 0,
        }]);
        handleClickOpen(meals.length);
    }

    return (
        <>
            <div className="base-meals-container">
                <div className="meals-title-item">
                    <div className="meals-title">Meals</div>
                    <div className="meals-total">{"Total: " + totalKcal() + "Kcal"}</div>
                </div>
                {
                    meals.map((value, index) =>
                    <div className="meal-item" onClick={() => handleClickOpen(index)}>
                        <div className="meal-name">{value.title}</div>
                        <div className="meal-stats"> 
                            <div> {value.protein + "g protein"} </div>
                            <div> {value.carbohydrates + " carbs"} </div>
                            <div> {value.fat + "g fat"} </div>
                            <div> {value.calories + "kcal"} </div>
                        </div>
                    </div>
                    )
                }
                <Dialog open={open} onClose={handleClose}>
                    <DialogTitle>Meal dialog</DialogTitle>
                    <DialogContent>
                        <TextField
                            autoFocus
                            margin="dense"
                            id="weight"
                            label="The name of the quisine"
                            type="text"
                            fullWidth
                            value={selectedMeal != null ? meals[selectedMeal].title : ''}
                            variant="standard"
                            onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                                const newMeals = meals;
                                if (selectedMeal != null) {
                                    newMeals[selectedMeal] = {...newMeals[selectedMeal], title: event.target.value};
                                }
                                setMeals(newMeals);
                            }}
                        />
                        <TextField
                            autoFocus
                            margin="dense"
                            id="weight"
                            label="Protein"
                            type="number"
                            fullWidth
                            value={selectedMeal != null ? meals[selectedMeal].protein : ''}
                            variant="standard"
                            onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                                const newMeals = meals;
                                if (selectedMeal != null) {
                                    newMeals[selectedMeal] = {...newMeals[selectedMeal], protein: +event.target.value};
                                }
                                setMeals(newMeals);
                            }}
                        />
                        <TextField
                            autoFocus
                            margin="dense"
                            id="height"
                            label="Carbohydrates"
                            type="number"
                            fullWidth
                            value={selectedMeal != null ? meals[selectedMeal].carbohydrates : ''}
                            variant="standard"
                            onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                                const newMeals = meals;
                                if (selectedMeal != null) {
                                    newMeals[selectedMeal] = {...newMeals[selectedMeal], carbohydrates: +event.target.value};
                                }
                                setMeals(newMeals);
                            }}
                        />
                        <TextField
                            autoFocus
                            margin="dense"
                            id="calories"
                            label="Fat"
                            type="number"
                            fullWidth
                            value={selectedMeal != null ? meals[selectedMeal].fat : ''}
                            variant="standard"
                            onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                                const newMeals = meals;
                                if (selectedMeal != null) {
                                    newMeals[selectedMeal] = {...newMeals[selectedMeal], fat: +event.target.value};
                                }
                                setMeals(newMeals);
                            }}
                        />
                        <TextField
                            autoFocus
                            margin="dense"
                            id="calories"
                            label="Calories"
                            type="number"
                            fullWidth
                            value={selectedMeal != null ? meals[selectedMeal].calories : ''}
                            variant="standard"
                            onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                                const newMeals = meals;
                                if (selectedMeal != null) {
                                    newMeals[selectedMeal] = {...newMeals[selectedMeal], calories: +event.target.value};
                                }
                                setMeals(newMeals);
                            }}
                        />
                    </DialogContent>
                    <DialogActions>
                        <Button color="warning" variant="contained" onClick={handleDelete}>Delete</Button>
                        <Button onClick={handleClose}>Close</Button>
                    </DialogActions>
                </Dialog>
            </div>
            <Button variant="contained" onClick={addNewMeal} className="add-meal-button">Add meal</Button>
        </>
    );
}