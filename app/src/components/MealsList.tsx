import { ReactElement, useCallback, useState } from "react";
import { Meal } from "../utils/types";
import "./MealsList.css";

import Button from "@mui/material/Button";
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';

interface Props {
    healthDataId: number,
    meals: Array<Meal>,
    setMeals: (newData: Array<Meal>) => void
}

export function MealsList({healthDataId, meals, setMeals} : Props): ReactElement {

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

    const handleDelete = useCallback(() => {
        if (selectedMeal != null) {
            fetch('meal/delete/' + meals[selectedMeal].idme);
                // .then(response => response.json())
                // .then(data => {
                //     console.log(data);
                // });
        }

        setMeals(meals.filter((value, index) => index !== selectedMeal));
        handleClose()
    }, [meals, selectedMeal, setMeals]);

    const addNewMeal = useCallback(() => {
        
        fetch('meal/getFresh/' + healthDataId)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                setMeals( [ ...meals, {
                    idme: data.idme,
                    title: data.title,
                    description: data.description,
                    calories: data.calories,
                    fat: data.fat,
                    carbohydrates: data.carbohydrates,
                    protein: data.protein,
                }]);
                console.log(meals);
            });
        //handleClickOpen(meals.length); // cant do this now, need to change something, other wise there is no component at that array loco

    }, [healthDataId, meals, setMeals]);

    const handleUpdatePost = useCallback((newValue: Meal) => {
        const requestOptions = {
            method: 'POST',
            body: JSON.stringify(newValue),
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json',
            },
        };

        fetch('meal/update/' + newValue.idme, requestOptions)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                // check if success i guess
            });
    }, []);

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
                                    handleUpdatePost(newMeals[selectedMeal]);
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
                            defaultValue={selectedMeal != null ? meals[selectedMeal].protein : ''}
                            variant="standard"
               
                            onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
								const numberRegex = /^[0-9]+$/;
                                const newMeals = meals;
                                if (selectedMeal != null) {
									if(numberRegex.test(event.target.value)){
										newMeals[selectedMeal] = {...newMeals[selectedMeal], protein: +event.target.value};
                                    	handleUpdatePost(newMeals[selectedMeal]);
									} 
									else{
										newMeals[selectedMeal] = {...newMeals[selectedMeal], protein: 0};
                                    	handleUpdatePost(newMeals[selectedMeal]);
									}
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
                            defaultValue={selectedMeal != null ? meals[selectedMeal].carbohydrates : ''}
                            variant="standard"
                            onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
								const numberRegex = /^[0-9]+$/;
                                const newMeals = meals;
                                if (selectedMeal != null) {
                                   if(numberRegex.test(event.target.value)){
										newMeals[selectedMeal] = {...newMeals[selectedMeal], carbohydrates: +event.target.value};
                                    	handleUpdatePost(newMeals[selectedMeal]);
									} 
									else{
										newMeals[selectedMeal] = {...newMeals[selectedMeal], carbohydrates: 0};
                                    	handleUpdatePost(newMeals[selectedMeal]);
									}
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
                            defaultValue={selectedMeal != null ? meals[selectedMeal].fat : ''}
                            variant="standard"
                            onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
								const numberRegex = /^[0-9]+$/;
                                const newMeals = meals;
                                if (selectedMeal != null) {
                                    if(numberRegex.test(event.target.value)){
										newMeals[selectedMeal] = {...newMeals[selectedMeal], fat: +event.target.value};
                                    	handleUpdatePost(newMeals[selectedMeal]);
									} 
									else{
										newMeals[selectedMeal] = {...newMeals[selectedMeal], fat: 0};
                                    	handleUpdatePost(newMeals[selectedMeal]);
									}
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
                            defaultValue={selectedMeal != null ? meals[selectedMeal].calories : ''}
                            variant="standard"
                            onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
								const numberRegex = /^[0-9]+$/;
                                const newMeals = meals;
                                if (selectedMeal != null) {
                                    if(numberRegex.test(event.target.value)){
										newMeals[selectedMeal] = {...newMeals[selectedMeal], calories: +event.target.value};
                                    	handleUpdatePost(newMeals[selectedMeal]);
									} 
									else{
										newMeals[selectedMeal] = {...newMeals[selectedMeal], calories: 0};
                                    	handleUpdatePost(newMeals[selectedMeal]);
									}
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