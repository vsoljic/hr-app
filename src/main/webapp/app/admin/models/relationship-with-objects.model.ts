import { Estimation } from 'app/admin/models/estimation.model';
import { Employee } from 'app/admin/models/employee.model';

export class RelationshipWithObjectsModel {
    estimation: Estimation;
    evaluator: Employee;
    evaluatee: Employee;

    constructor(estimation: Estimation, evaluator: Employee, evaluatee: Employee) {
        this.estimation = estimation;
        this.evaluator = evaluator;
        this.evaluatee = evaluatee;
    }
}
