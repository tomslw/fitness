

export interface Exercise {
    idex: number;
    title: String;
    description: String;
    rest_interval: number;
    repetitions: number;
    added_weight: number
    target_muscles: MuscleGroups
}

export interface Meal {
    title: String;
    description: String;
    calories: number;
    fat: number;
    carbohydrates: number;
    protein: number;
}

export interface HealthData {
    weight: number;
    height: number;
    morning_muscle_fatigue: MuscleGroups;
    diet: Array<Meal>;
    workout: Array<Exercise>;
    calories_spent: number;
    date_time: Date;
}

export enum Intensity {
    none, low, medium, high, injury
}

export interface MuscleGroups {
    chest: Intensity;
    back: Intensity;
    biceps: Intensity;
    triceps: Intensity;
    forearms: Intensity;
    abdomen: Intensity;
    gluteus: Intensity;
    hamstrings: Intensity;
    quadriceps: Intensity;
    calves: Intensity;
    trapezius: Intensity;
    deltoid: Intensity;
}