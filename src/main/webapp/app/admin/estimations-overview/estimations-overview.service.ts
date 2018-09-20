import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SERVER_API_URL } from 'app/app.constants';
import { Employee } from 'app/admin/models/employee.model';

@Injectable()
export class EstimationsOverviewService {
    constructor(private http: HttpClient) {}

    getEstimations() {
        return this.http.get(SERVER_API_URL + 'api/admin/estimations-overview');
    }

    getEvaluators(estimationId: number) {
        return this.http.get(SERVER_API_URL + 'api/admin/estimations-overview/' + estimationId + '/evaluators/');
    }

    getEmployeesIn(employeesIds: number[]) {
        return this.http.get(SERVER_API_URL + 'api/admin/estimations-overview/employees', {
            params: new HttpParams().set('employeesIds', employeesIds.toString())
        });
    }

    getEvaluateesForEvaluator(estimationId: number, evaluatorId: number) {
        return this.http.get(
            SERVER_API_URL + 'api/admin/estimations-overview/' + estimationId + '/evaluators/' + evaluatorId + '/evaluatees'
        );
    }

    deleteSelectedEvaluateeForEvaluatorAndEstimation(estimationId: number, evaluatorId: number, employeeEvaluatee: Employee) {
        return this.http.post(
            SERVER_API_URL + 'api/admin/estimations-overview/' + estimationId + '/evaluators/' + evaluatorId + '/evaluatees',
            employeeEvaluatee
        );
    }
}
