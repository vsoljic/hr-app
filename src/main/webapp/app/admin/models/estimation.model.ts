import {Model} from 'app/admin/models/model.model';
import {Status} from 'app/admin/models/status.model';
import {Employee} from 'app/admin/models/employee.model';

export class Estimation {
    id: number;
    status: Status;
    model: Model;
    name: string;
    periodFrom: Date;
    periodTo: Date;
    employeesEvaluators: Employee[];
    employeesEvaluatees: Employee[];


    constructor(id: number, status: Status, model: Model, name: string, periodFrom: Date, periodTo: Date,
                employeesEvaluators: Employee[], employeesEvaluatees: Employee[]) {
        this.id = id;
        this.status = status;
        this.model = model;
        this.name = name;
        this.periodFrom = periodFrom;
        this.periodTo = periodTo;
        this.employeesEvaluators = employeesEvaluators;
        this.employeesEvaluatees = employeesEvaluatees;
    }
}
