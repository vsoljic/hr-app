import {Model} from 'app/admin/models/model.model';
import {Status} from 'app/admin/models/status.model';

export class Estimation {
    id: number;
    status: Status;
    model: Model;
    name: string;
    periodFrom: Date;
    periodTo: Date;


    constructor(id: number, status: Status, model: Model, name: string, periodFrom: Date, periodTo: Date) {
        this.id = id;
        this.status = status;
        this.model = model;
        this.name = name;
        this.periodFrom = periodFrom;
        this.periodTo = periodTo;
    }
}
