import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NgbCalendar, NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap';
import { EstimationService } from 'app/admin/estimation/estimation.service';
import { Model } from 'app/admin/models/model.model';
import { DataSharingService } from 'app/shared/data-sharing.service';
import { NotificationsService } from 'angular2-notifications';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Estimation } from 'app/admin/models/estimation.model';
import { Status } from 'app/admin/models/status.model';
import { Employee } from 'app/admin/models/employee.model';

@Component({
    selector: 'jhi-estimation',
    templateUrl: './estimation.component.html',
    styleUrls: ['./estimation.scss']
})
export class EstimationComponent implements OnInit {
    modelEstimation: Model[];
    selectedModel: Model;
    dateFrom: Date;
    dateTo: Date;
    statuses: Status[];
    status: Status;
    inputEstimationName: string;
    updatedEstimation: Estimation;

    constructor(
        private http: HttpClient,
        private ngbDateParserFormatter: NgbDateParserFormatter,
        private estimationService: EstimationService,
        private dataSharingService: DataSharingService,
        private notificationsService: NotificationsService,
        private router: Router,
        private calendar: NgbCalendar
    ) {}

    ngOnInit() {
        this.dateFrom = new Date();
        this.dateTo = new Date();

        this.estimationService
            .getModels()
            .subscribe(
                (models: Model[]) => (this.modelEstimation = models),
                error => console.log('error fetching models', error),
                () => (this.selectedModel = this.modelEstimation.find(model => model.id === 1))
            );

        this.estimationService
            .getStatuses()
            .subscribe(
                (statuses: Status[]) => (this.statuses = statuses),
                error => console.log('Error fetching statuses', error),
                () => (this.status = this.statuses.find(status => status.id === 1))
            );
    }

    createNewEstimation(estimationTemplate: NgForm) {
        console.log('estimation', estimationTemplate);
        if (!estimationTemplate.valid) {
            // if form is not valid and user sent it, show error
            this.notificationsService.create(null, 'Procjena nije uspješno definirana! Pokušajte ponovo.', 'error');
            return; // to exit without calling backend
        }

        const estimation = this.prepareEstimationValues();

        this.estimationService.createNewEstimation(estimation).subscribe(
            (data: Estimation) => {
                this.updatedEstimation = data;
            },
            () => {
                this.notificationsService.create(null, 'Došlo je do pogreške prilikom kreiranja procjene!', 'error');
            },
            () => {
                this.notificationsService.create(null, 'Uspješno ste kreirali procjenu', 'success');
                this.storeEstimationAndNavigateToRelationships(this.updatedEstimation);
            }
        );
    }

    /**
     * Prepares estimation form for POST request to backend.
     */
    prepareEstimationValues(): Estimation {
        const evaluators: Employee[] = [];
        const evaluatees: Employee[] = [];
        // initialize form object
        const estimation = new Estimation(
            null,
            this.status,
            this.selectedModel,
            this.inputEstimationName,
            this.dateFrom,
            this.dateTo,
            evaluators,
            evaluatees
        );
        // return form estimation for backend
        return estimation;
    }

    /**
     * Stores estimation response of created estimation into a shared service which later passes the same estimation to another screen.
     * After a short delay, navigates to another screen.
     * @param created estimation from backend
     */
    async storeEstimationAndNavigateToRelationships(estimation: Estimation) {
        this.dataSharingService.storage = estimation; // store orderForm to application wide storage
        await this.delay(2000).then(() => this.router.navigate(['admin/relationships']));
    }

    /**
     * Creates delay for given time.
     * @param timeInMs given time of delay
     */
    private delay(timeInMs: number) {
        return new Promise(resolve => setTimeout(resolve, timeInMs));
    }
}
