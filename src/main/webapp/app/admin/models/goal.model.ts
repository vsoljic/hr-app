import { GroupOfGoals } from 'app/admin/models/group-of-goals.model';
import { Employee } from 'app/admin/models/employee.model';
import { Estimation } from 'app/admin/models/estimation.model';

export class Goal {
    id: number;
    name: string;
    ponderPercentage: number;
    targetValue: number;
    achievementPercentage: number;
    group: GroupOfGoals;
    employee: Employee;
    estimation: Estimation;

    constructor(
        id: number,
        name: string,
        ponderPercentage: number,
        targetValue: number,
        achievementPercentage: number,
        group: GroupOfGoals,
        employee: Employee,
        estimation: Estimation
    ) {
        this.id = id;
        this.name = name;
        this.ponderPercentage = ponderPercentage;
        this.targetValue = targetValue;
        this.achievementPercentage = achievementPercentage;
        this.group = group;
        this.employee = employee;
        this.estimation = estimation;
    }
}
