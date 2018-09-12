import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SERVER_API_URL } from 'app/app.constants';

@Injectable()
export class GroupService {
    constructor(private http: HttpClient) {}

    getGroupsByEmployeeAndEstimationWithGoals(employeeId: number, estimationId: number) {
        return this.http.get(SERVER_API_URL + 'api/groups/estimation/' + estimationId + '/employee/' + employeeId);
    }
}
