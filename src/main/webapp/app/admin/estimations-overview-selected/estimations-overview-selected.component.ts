import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NotificationsService } from 'angular2-notifications';
import { DataSharingService } from 'app/shared/data-sharing.service';
import { Estimation } from 'app/admin/models/estimation.model';
import { Employee } from 'app/admin/models/employee.model';
import { EstimationsOverviewService } from 'app/admin/estimations-overview/estimations-overview.service';

@Component({
    selector: 'jhi-estimations-overview',
    templateUrl: './estimations-overview-selected.component.html',
    styleUrls: ['./estimations-overview-selected.scss']
})
export class EstimationsOverviewSelectedComponent implements OnInit {
    estimation: Estimation;
    evaluators: Employee[];
    selectedEvaluator: Employee;
    evaluatees: Employee[];
    selectedEvaluatee: Employee;
    evaluatorsIds: number[];

    constructor(
        private estimationsOverviewService: EstimationsOverviewService,
        private dataSharingService: DataSharingService,
        private notificationsService: NotificationsService,
        private router: Router
    ) {}

    ngOnInit() {
        this.estimation = this.dataSharingService.storage;
        this.dataSharingService.storage = null;

        this.estimationsOverviewService
            .getEvaluators(this.estimation.id)
            .subscribe(
                (evaluators: Employee[]) => (this.evaluators = evaluators),
                error => console.log('error fetching evaluators', error),
                () => console.log('success')
            );
    }

    getAllEvaluateesForEvaluator() {
        if (this.estimation != null && this.selectedEvaluator != null) {
            this.estimationsOverviewService
                .getEvaluateesForEvaluator(this.estimation.id, this.selectedEvaluator.id)
                .subscribe(
                    (evaluatees: Employee[]) => (this.evaluatees = evaluatees),
                    error => console.log('error fetching evaluatees', error),
                    () => console.log('success')
                );
        }
    }

    onEvaluatorsEmit(evaluatorsIds) {
        this.evaluatorsIds = evaluatorsIds;
        console.log('U parentu sam s ovim: ' + this.evaluatorsIds);
        console.log('U parentu sam s ovim postojećim evaluatorima: ' + this.evaluators);

        this.estimationsOverviewService.getEmployeesIn(evaluatorsIds).subscribe(
            (evaluators: Employee[]) => {
                console.log('Evaluators: ' + evaluators.length);
                if (this.evaluators.length === 0) {
                    this.evaluators = evaluators;
                } else {
                    for (const e of evaluators) {
                        this.evaluators.push(e);
                        this.evaluators = [...this.evaluators];
                        console.log('evaluator id: ' + e.id);
                    }
                }
            },
            () => console.log('error on emit evaluators'),
            () => console.log('success')
        );
    }

    onEvaluateeEmit(evaluateeIds) {
        console.log('U parentu sam s ovim za evaluatees: ' + evaluateeIds);

        this.estimationsOverviewService.getEmployeesIn(evaluateeIds).subscribe(
            (evaluatees: Employee[]) => {
                if (this.evaluatees.length === 0) {
                    this.evaluatees = evaluatees;
                } else {
                    for (const e of evaluatees) {
                        if (!this.evaluatees.includes(e)) {
                            this.evaluatees.push(e);
                        }
                    }
                }
            },
            () => console.log('error on emit evaluatees'),
            () => console.log('success')
        );
    }

    onBla(event) {
        console.log('Event');
    }

    onEvaluateeDeleteEmit(evaluatee) {
        console.log('Parent deleted one evaluatee ' + evaluatee.id + ', evaluatees: ' + this.evaluatees);

        this.evaluatees = this.evaluatees.filter(e => e.id !== evaluatee.id);
    }
}
