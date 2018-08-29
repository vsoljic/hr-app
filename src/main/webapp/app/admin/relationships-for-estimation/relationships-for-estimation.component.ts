import {Component, OnInit} from '@angular/core';
import {Employee} from 'app/admin/models/employee.model';
import {HttpClient} from '../../../../../../node_modules/@angular/common/http';
import {RelationshipsForEstimationService} from 'app/admin/relationships-for-estimation/relationships-for-estimation.service';
import {Router} from '@angular/router';

@Component({
    selector: 'jhi-evaluator',
    templateUrl: './relationships-for-estimation.component.html',
    styles: []
})
export class RelationshipsForEstimationComponent implements OnInit {
    employees: Employee[];

    constructor(private http: HttpClient,
                private relationshipsService: RelationshipsForEstimationService,
                private router: Router) {
    }

    ngOnInit() {
        this.relationshipsService.getEmployees().subscribe(
            (employees: Employee[]) => this.employees = employees,
            error => console.log('error fetching employees', error),
            () => console.log('success')
        );
    }
}
