import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SERVER_API_URL} from 'app/app.constants';

@Injectable()
export class EstimationService {
    constructor(private http: HttpClient) {
    }

    getEstimations() {
        return this.http.get(SERVER_API_URL + 'api/admin/estimation');
    }

    getModels() {
        return this.http.get(SERVER_API_URL + 'api/admin/estimation/model');
    }

}
