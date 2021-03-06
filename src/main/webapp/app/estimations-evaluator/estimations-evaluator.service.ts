import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SERVER_API_URL } from 'app/app.constants';

@Injectable()
export class EstimationsEvaluatorService {
    constructor(private http: HttpClient) {}

    getEstimationsForEvaluator(evaluatorId: number) {
        return this.http.get(SERVER_API_URL + 'api/estimations-evaluator/' + evaluatorId);
    }
}
