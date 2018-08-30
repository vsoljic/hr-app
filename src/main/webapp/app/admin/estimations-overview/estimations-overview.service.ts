import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SERVER_API_URL} from 'app/app.constants';

@Injectable()
export class EstimationsOverviewService {
    constructor(private http: HttpClient) {
    }

    getEstimations() {
        return this.http.get(SERVER_API_URL + 'api/admin/estimations-overview');
    }

    getEvaluators(estimationId: number) {
        return this.http.get(SERVER_API_URL + 'api/admin/estimations-overview/' + estimationId + '/evaluators/');
    }

    getEvaluateesForEvaluator(estimationId: number, evaluatorId: number) {
        return this.http.get(SERVER_API_URL + 'api/admin/estimations-overview/' + estimationId + '/evaluators/' + evaluatorId +
            '/evaluatees');
    }
}
