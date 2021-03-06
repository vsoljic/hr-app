import { Component, OnInit } from '@angular/core';
import { Employee } from 'app/admin/models/employee.model';
import { HttpClient } from '../../../../../../node_modules/@angular/common/http';
import { RelationshipsForEstimationService } from 'app/admin/relationships-for-estimation/relationships-for-estimation.service';
import { Router } from '@angular/router';
import { DataSharingService } from 'app/shared/data-sharing.service';
import { Estimation } from 'app/admin/models/estimation.model';

@Component({
    selector: 'jhi-evaluator',
    templateUrl: './relationships-for-estimation.component.html',
    styles: []
})
export class RelationshipsForEstimationComponent implements OnInit {
    employees: Employee[];
    estimation: Estimation;
    isEvaluator = false;
    evaluator: Employee;
    evaluatorsList: Employee[] = [];

    constructor(
        private http: HttpClient,
        private relationshipsService: RelationshipsForEstimationService,
        private router: Router,
        private dataSharingService: DataSharingService
    ) {}

    ngOnInit() {
        // dohvat podataka s prvog ekrana
        this.estimation = this.dataSharingService.storage;
        // obrisati sve iz dataSharing-a kako se ne bi nepotrebno zadržavali objekti
        this.dataSharingService.storage = null;

        this.relationshipsService
            .getEmployees()
            .subscribe(
                (employees: Employee[]) => (this.employees = employees),
                error => console.log('error fetching employees', error),
                () => console.log('success')
            );
    }

    onEmit(evaluator) {
        this.evaluator = evaluator;
        this.isEvaluator = true;

        if (this.employees.find(e => evaluator === e.id)) {
            for (const e of this.employees) {
                if (e.id === evaluator) {
                    e.isEvaluator = true;
                }
            }
        }

        console.log('Evo me u parentu, isEvaluator = ' + this.isEvaluator + ', evaluator: ' + this.evaluator);
    }
}
