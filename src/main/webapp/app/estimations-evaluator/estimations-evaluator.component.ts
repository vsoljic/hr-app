import { Component, OnInit } from '@angular/core';
import { Estimation } from 'app/admin/models/estimation.model';
import { Account, IUser, Principal, User, UserService } from 'app/core';
import { EstimationsEvaluatorService } from 'app/estimations-evaluator/estimations-evaluator.service';
import { EmployeeUserService } from 'app/shared/employee-user.service';
import { Employee } from 'app/admin/models/employee.model';

@Component({
    selector: 'jhi-estimations-evaluator',
    templateUrl: './estimations-evaluator.component.html',
    styles: []
})
export class EstimationsEvaluatorComponent implements OnInit {
    estimations: Estimation[];
    account: Account;
    loggedInEmployee: Employee;

    constructor(private employeeUserService: EmployeeUserService, private estimationsEvaluatorService: EstimationsEvaluatorService) {}

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
        this.estimationsEvaluatorService
            .getEstimationsForEvaluator(this.loggedInEmployee.id)
            .subscribe(
                (estimations: Estimation[]) => (this.estimations = estimations),
                error => console.log('error fetching estimations', error),
                () => console.log('success')
            );
    }
}
