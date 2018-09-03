import {User} from 'app/core';
import {Estimation} from 'app/admin/models/estimation.model';

export class Employee {
    id: number;
    user: User;
    employeeCode: number;
    firstName: string;
    lastName: string;
    workPosition: string;
    estimationsForEvaluator: Estimation[];
    estimationsForEvaluatee: Estimation[];


    constructor(id: number, user: User, employeeCode: number, firstName: string, lastName: string, workPosition: string,
                estimationsForEvaluator: Estimation[], estimationsForEvaluatee: Estimation[]) {
        this.id = id;
        this.user = user;
        this.employeeCode = employeeCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.workPosition = workPosition;
        this.estimationsForEvaluator = estimationsForEvaluator;
        this.estimationsForEvaluatee = estimationsForEvaluatee;
    }
}
