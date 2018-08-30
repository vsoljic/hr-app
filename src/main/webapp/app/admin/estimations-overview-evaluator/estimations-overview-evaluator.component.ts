import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {NotificationsService} from 'angular2-notifications';
import {DataSharingService} from 'app/shared/data-sharing.service';
import {Estimation} from 'app/admin/models/estimation.model';
import {Employee} from 'app/admin/models/employee.model';
import {EstimationsOverviewService} from 'app/admin/estimations-overview/estimations-overview.service';

@Component({
    selector: 'jhi-estimations-overview',
    templateUrl: './estimations-overview-evaluator.component.html',
    styleUrls: ['./estimations-overview-evaluator.scss']
})
export class EstimationsOverviewEvaluatorComponent implements OnInit {
    estimation: Estimation;
    evaluators: Employee[];
    selectedEvaluator: Employee;
    evaluatees: Employee[];
    selectedEvaluatee: Employee;


    constructor(private estimationsOverviewService: EstimationsOverviewService,
                private dataSharingService: DataSharingService,
                private notificationsService: NotificationsService,
                private router: Router) {
    }

    ngOnInit() {
        this.estimation = this.dataSharingService.storage;
        this.dataSharingService.storage = null;

        this.estimationsOverviewService.getEvaluators(this.estimation.id).subscribe(
            (evaluators: Employee[]) => this.evaluators = evaluators,
            error => console.log('error fetching evaluators', error),
            () => console.log('success')
        );
    }

    getAllEvaluateesForEvaluator() {
        this.estimationsOverviewService.getEvaluateesForEvaluator(this.estimation.id, this.selectedEvaluator.id).subscribe(
            (evaluatees: Employee[]) => this.evaluatees = evaluatees,
            error => console.log('error fetching evaluatees', error),
            () => console.log('success')
        );
    }
}
