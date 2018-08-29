import {Component, OnInit} from '@angular/core';
import {EstimationsOverviewService} from 'app/admin/estimations-overview/estimations-overview.service';
import {Estimation} from 'app/admin/models/estimation.model';
import {DataSharingService} from 'app/shared/data-sharing.service';
import {NotificationsService} from 'angular2-notifications';
import {Router} from '@angular/router';

@Component({
    selector: 'jhi-estimations-overview',
    templateUrl: './estimations-overview.component.html',
    styles: []
})
export class EstimationsOverviewComponent implements OnInit {
    estimations: Estimation[];

    constructor(private estimationsOverviewService: EstimationsOverviewService,
                private dataSharingService: DataSharingService,
                private notificationsService: NotificationsService,
                private router: Router) {
    }

    ngOnInit() {

        this.estimationsOverviewService.getEstimations().subscribe(
            (estimations: Estimation[]) => this.estimations = estimations,
            error => console.log('error fetching estimations', error),
            () => console.log('success')
        );
    }

    editEstimation(estimation: Estimation) {
        this.storeEstimationAndNavigateToEdit(estimation);
    }

    editRelationships(estimation: Estimation) {
        this.storeEstimationAndNavigateToRelationships(estimation);
    }

    /**
     * Stores estimation response of created estimation into a shared service which later passes the same estimation to another screen.
     * After a short delay, navigates to another screen.
     * @param estimation created estimation from backend
     */
    async storeEstimationAndNavigateToEdit(estimation: Estimation) {
        this.dataSharingService.storage = estimation; // store orderForm to application wide storage
        await this.delay(500).then(() => this.router.navigate(['admin/estimation/edit']));
    }

    async storeEstimationAndNavigateToRelationships(estimation: Estimation) {
        this.dataSharingService.storage = estimation; // store orderForm to application wide storage
        await this.delay(500).then(() => this.router.navigate(['admin/estimations-overview-evaluator']));
    }

    /**
     * Creates delay for given time.
     * @param timeInMs given time of delay
     */
    private delay(timeInMs: number) {
        return new Promise(resolve => setTimeout(resolve, timeInMs));
    }

}
