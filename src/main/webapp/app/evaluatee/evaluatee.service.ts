import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SERVER_API_URL} from 'app/app.constants';
import {Employee} from 'app/admin/models/employee.model';

@Injectable()
export class EvaluateeService {
    constructor(private http: HttpClient) {
    }

    getGroupsByEstimation(id: number) {
        return this.http.get(SERVER_API_URL + 'api/evaluatee/estimation/' + id + '/groups');
    }

}
