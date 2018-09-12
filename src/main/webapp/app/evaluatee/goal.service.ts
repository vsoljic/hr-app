import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SERVER_API_URL } from 'app/app.constants';
import { Goal } from 'app/admin/models/goal.model';

@Injectable()
export class GoalService {
    constructor(private http: HttpClient) {}
    getGoalsByEmployeeAndGroupAndEst(employeeId: number, groupId: number, estimationId: number) {
        return this.http.get(SERVER_API_URL + 'api/goals/estimation/' + estimationId + '/group/' + groupId + '/employee/' + employeeId);
    }

    getGoalsByEmployeeAndEstimationGroupByGroups(employeeId: number, estimationId: number) {
        return this.http.get(SERVER_API_URL + 'api/goals/estimation/' + estimationId + '/employee/' + employeeId);
    }

    saveGoal(goal: Goal) {
        return this.http.post(SERVER_API_URL + 'api/goals/new', goal);
    }
}
