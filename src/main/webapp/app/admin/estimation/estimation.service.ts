import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SERVER_API_URL} from 'app/app.constants';
import {Estimation} from 'app/admin/models/estimation.model';

@Injectable()
export class EstimationService {
    constructor(private http: HttpClient) {
    }

    getModels() {
        return this.http.get(SERVER_API_URL + 'api/admin/estimation/model');
    }

    getStatuses() {
        return this.http.get(SERVER_API_URL + 'api/admin/estimation/statuses');
    }

    createNewEstimation(estimation: Estimation) {
        return this.http.post(SERVER_API_URL + '/api/admin/estimation', estimation);
    }

    editSelectedEstimation(estimation: Estimation) {
        return this.http.post(SERVER_API_URL + '/api/admin/estimation/edit' , estimation);
    }

}
