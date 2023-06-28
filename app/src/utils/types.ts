

export interface Exercise {
    //idex: number;
    title: String;
    description: String;
    restInterval: number;
    repetitions: number;
    addedWeight: number
    targetMuscles: MuscleGroups
}

export interface Meal {
    //idme: number;
    title: String;
    description: String;
    calories: number;
    fat: number;
    carbohydrates: number;
    protein: number;
}

export interface HealthData {
    //idhd: number;
    weight: number;
    height: number;
    morningMuscleFatigue: MuscleGroups;
    diet: Array<Meal>;
    workout: Array<Exercise>;
    caloriesSpent: number;
    dateTime: Date;
}

export enum Intensity {
    none, low, medium, high, injury
}

// no need to save the muscle groups id, i can edit the muscle group by Exercise/HealthData id
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