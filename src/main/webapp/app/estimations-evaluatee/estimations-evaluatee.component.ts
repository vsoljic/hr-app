import { Component, OnInit } from '@angular/core';
import { Estimation } from 'app/admin/models/estimation.model';
import { Account } from 'app/core';
import { Employee } from 'app/admin/models/employee.model';
import { EmployeeUserService } from 'app/shared/employee-user.service';
import { EstimationsEvaluateeService } from 'app/estimations-evaluatee/estimations-evaluatee.service';
import { RelationshipWithObjectsModel } from 'app/admin/models/relationship-with-objects.model';
import { DataSharingService } from 'app/shared/data-sharing.service';
import { Router } from '@angular/router';

@Component({
    selector: 'jhi-estimations-evaluatee',
    templateUrl: './estimations-evaluatee.component.html',
    styles: []
})
export class EstimationsEvaluateeComponent implements OnInit {
    estimations: Estimation[];
    account: Account;
    loggedInEmployee: Employee;
    relationship: RelationshipWithObjectsModel;

    constructor(
        private employeeUserService: EmployeeUserService,
        private estimationsEvaluateeService: EstimationsEvaluateeService,
        private router: Router,
        private dataSharingService: DataSharingService
    ) {}

    ngOnInit() {
        this.employeeUserService
            .getEmployeeForUser()
            .subscribe(
                (employee: Employee) => (this.loggedInEmployee = employee),
                error => console.log('error fetching employee for user', error),
                () => this.getEstimations()
            );
    }

    getEstimations() {
        this.estimationsEvaluateeService
            .getEstimationForEvaluatee(this.loggedInEmployee.id)
            .subscribe(
                (estimations: Estimation[]) => (this.estimations = estimations),
                error => console.log('error fetching estimations', error),
                () => console.log('success')
            );
    }

    openEvaluateeOverview(estimation) {
        this.relationship = new RelationshipWithObjectsModel(estimation, estimation.evaluator, this.loggedInEmployee);
        this.dataSharingService.storage = this.relationship;

        this.router.navigate(['/evaluatee']);
    }
}
