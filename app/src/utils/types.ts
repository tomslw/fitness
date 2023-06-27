

export interface Excercise {
    idex: number;
    title: String;
    description: String;
    restInterval: number;
    repetitions: number;
    addedWeight: number
    // health data goes here, once that has been defined, for now not nessesary
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