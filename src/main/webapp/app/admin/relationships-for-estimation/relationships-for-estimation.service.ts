import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '../../../../../../node_modules/@angular/common/http';
import {SERVER_API_URL} from 'app/app.constants';
import {Relationship} from 'app/admin/models/relationship.model';

@Injectable()
export class RelationshipsForEstimationService {
    constructor(private http: HttpClient) {
    }

    getEmployees() {
        return this.http.get(SERVER_API_URL + 'api/admin/relationships/employees');
    }

    getEmployeesExpectSelectedOne(employeeEvaluatorId: number) {
        return this.http.get(SERVER_API_URL + 'api/admin/relationships/employees/' + employeeEvaluatorId);
    }

    createNewRelationship(relationship: Relationship) {
        return this.http.post(SERVER_API_URL + 'api/admin/relationships/employees', relationship);
    }
}
