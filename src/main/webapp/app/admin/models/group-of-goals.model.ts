import { Goal } from 'app/admin/models/goal.model';

export class GroupOfGoals {
    id: number;
    name: string;
    ponderPercentage: number;
    goals: Goal[] = [];
    totalPonderForGroup: number;

    constructor(id: number, name: string, ponderPercentage: number, goals: Goal[], totalPonderForGroup: number) {
        this.id = id;
        this.name = name;
        this.ponderPercentage = ponderPercentage;
        this.goals = goals;
        this.totalPonderForGroup = totalPonderForGroup;
    }
}
