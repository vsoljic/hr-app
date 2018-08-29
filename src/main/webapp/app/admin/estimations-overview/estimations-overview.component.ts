import {Component, OnInit} from '@angular/core';
import {EstimationService} from 'app/admin/estimation/estimation.service';
import {Router} from '@angular/router';
import {NotificationsService} from 'angular2-notifications';
import {DataSharingService} from 'app/shared/data-sharing.service';

@Component({
    selector: 'jhi-estimations-overview',
    templateUrl: './estimations-overview.component.html',
    styleUrls: ['./estimations-overview.scss']
})
export class EstimationsOverviewComponent implements OnInit {
    evaluators = ['Vedrana Š', 'Antun Štaba', 'Pajo Pajić', 'Miha Mihić'];

    constructor(private estimationService: EstimationService,
                private dataSharingService: DataSharingService,
                private notificationsService: NotificationsService,
                private router: Router) {
    }

    ngOnInit() {
    }


}
