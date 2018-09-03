import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NgbDateStruct} from '@ng-bootstrap/ng-bootstrap/datepicker/ngb-date-struct';
import {NgbDateParserFormatter} from '@ng-bootstrap/ng-bootstrap';
import {EstimationService} from 'app/admin/estimation/estimation.service';
import {Model} from 'app/admin/models/model.model';
import {DataSharingService} from 'app/shared/data-sharing.service';
import {NotificationsService} from 'angular2-notifications';
import {Router} from '@angular/router';
import {NgForm} from '@angular/forms';
import {Estimation} from 'app/admin/models/estimation.model';
import {Status} from 'app/admin/models/status.model';
import {Employee} from 'app/admin/models/employee.model';

@Component({
    selector: 'jhi-estimation',
    templateUrl: './estimation.component.html',
    styleUrls: ['./estimation.scss']
})
export class EstimationComponent implements OnInit {
    modelEstimation: Model[];
    selectedModel: Model;
    dateFrom: NgbDateStruct;
    dateTo: NgbDateStruct;
    dateString: string;
    statuses: Status[];
    status: Status;
    inputEstimationName: string;

    constructor(private http: HttpClient,
                private ngbDateParserFormatter: NgbDateParserFormatter,
                private estimationService: EstimationService,
                private dataSharingService: DataSharingService,
                private notificationsService: NotificationsService,
                private router: Router) {
    }

    ngOnInit() {
        this.dateFrom = this.setDefaultDate();
        this.dateTo = this.setDefaultDate();

        this.estimationService.getModels().subscribe(
            (models: Model[]) => this.modelEstimation = models,
            error => console.log('error fetching models', error),
            () => this.selectedModel = this.modelEstimation.find(model => model.id === 1)
        );

        this.estimationService.getStatuses().subscribe(
            (statuses: Status[]) => this.statuses = statuses,
            error => console.log('Error fetching statuses', error),
            () => this.status = this.statuses.find(status => status.id === 1)
        );
    }

    /**
     * Returns today's date as a NgbDateStruct model {day, month, year}
     */
    setDefaultDate(): NgbDateStruct {
        return this.convertToNgbDate(new Date()); // new date = today
    }

    convertToNgbDate(date: Date): NgbDateStruct {
        const year = date.getFullYear().toString();
        const month = date.getMonth() + 1;
        const day = date.getDate();

        return this.ngbDateParserFormatter.parse(day + '.' + month.toString() + '.' + year);
    }

    convertToDate(ngbDate: NgbDateStruct): Date {
        return new Date(ngbDate.year, ngbDate.month, ngbDate.day);
    }

    /**
     * Checks if newly selected date is greater than date to and if yes, disallows change.
     * @param date selected date
     */
    onSelectDateFrom(date: NgbDateStruct) {
        if (date != null) {
            // if selected dateFrom is greater than dateTo just set it to dateTo
            this.dateFrom = this.isDateGreaterThan(date, this.dateTo) ? this.dateTo : date;
            console.log('Ovo je trenutno postavljen datum FROM:' + this.dateFrom);
            this.dateString = this.ngbDateParserFormatter.format(this.dateFrom);
        }
    }

    onSelectDateTo(date: NgbDateStruct) {
        if (date != null) {
            this.dateTo = date;
            console.log('Ovo je trenutno postavljen datum TO:' + this.dateTo);
            this.dateString = this.ngbDateParserFormatter.format(date);
        }
    }

    isDateGreaterThan(date1: NgbDateStruct, date2: NgbDateStruct): boolean {
        // get dates from NgbDateStruct type to javascript type
        const date1JsFormat = new Date(date1.year, date1.month, date1.day);
        const date2JsFormat = new Date(date2.year, date2.month, date2.day);

        console.log('isGreaterThan poziv i rezultat', date1, date2, date1JsFormat > date2JsFormat ? true : false);
        // return true if date1 is greater, else return false
        return date1JsFormat > date2JsFormat ? true : false;
    }

    createNewEstimation(estimationTemplate: NgForm) {
        console.log('estimation', estimationTemplate);
        if (!estimationTemplate.valid) { // if form is not valid and user sent it, show error
            this.notificationsService.create(null, 'Procjena nije uspješno definirana! Pokušajte ponovo.', 'error');
            return; // to exit without calling backend
        }

        const estimation = this.prepareEstimationValues();
        this.estimationService.createNewEstimation(estimation).subscribe(
            (createdEstimation: Estimation) => estimation,
            () => {
                this.notificationsService.create(null, 'Došlo je do pogreške prilikom kreiranja procjene!', 'error');
            },
            () => {
                this.notificationsService.create(null, 'Uspješno ste kreirali procjenu', 'success');
                this.storeEstimationAndNavigateToRelationships(estimation);
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
        const estimation = new Estimation(null, this.status, this.selectedModel, this.inputEstimationName, new Date(),
            new Date(2018, 12, 31), evaluators, evaluatees);
        // return form model for backend
        return estimation;
    }

    /**
     * Stores estimation response of created estimation into a shared service which later passes the same estimation to another screen.
     * After a short delay, navigates to another screen.
     * @param estimation created estimation from backend
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
