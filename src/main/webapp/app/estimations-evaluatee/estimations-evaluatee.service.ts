import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SERVER_API_URL } from 'app/app.constants';

@Injectable()
export class EstimationsEvaluateeService {
    constructor(private http: HttpClient) {}

    getEstimationForEvaluatee(evaluateeId: number) {
        return this.http.get(SERVER_API_URL + 'api/estimations-evaluatee/' + evaluateeId);
    }
}
