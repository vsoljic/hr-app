import { Component, OnInit } from '@angular/core';
import {Estimation} from 'app/admin/models/estimation.model';
import {DataSharingService} from 'app/shared/data-sharing.service';
import {Model} from 'app/admin/models/model.model';
import {EstimationService} from 'app/admin/estimation/estimation.service';
import {NgForm} from '@angular/forms';
import {NotificationsService} from 'angular2-notifications';
import {Router} from '@angular/router';
import {Status} from 'app/admin/models/status.model';

@Component({
  selector: 'jhi-estimation-edit',
  templateUrl: './estimation-edit.component.html',
  styleUrls: ['./estimation-edit.scss']
})
export class EstimationEditComponent implements OnInit {
    estimation: Estimation;
    selectedModel: Model;
    modelEstimation: Model[];
    inputEstimationName: string;
    statuses: Status[];
    selectedStatus: Status;
    dateFrom: Date;
    dateTo: Date;

  constructor(private dataSharingService: DataSharingService,
              private estimationService: EstimationService,
              private notificationsService: NotificationsService,
              private router: Router) { }

  ngOnInit() {
      this.estimation = this.dataSharingService.storage;
      this.dataSharingService.storage = null;

      this.estimationService.getModels().subscribe(
          (models: Model[]) => this.modelEstimation = models,
          error => console.log('error fetching models', error),
          () => this.selectedModel = this.modelEstimation.find(model => model.id === this.estimation.model.id)
      );

      this.estimationService.getStatuses().subscribe(
          (allStatuses: Status[]) => this.statuses = allStatuses,
          error => console.log('error fetching models', error),
          () => this.selectedStatus = this.statuses.find(status => status.id === this.estimation.status.id)
      );

      this.inputEstimationName = this.estimation.name;
      this.dateFrom = this.estimation.periodFrom;
      this.dateTo = this.estimation.periodTo;
  }

    editSelectedEstimation(selectedEstimationTemplate: NgForm) {
        console.log('estimationEdit', selectedEstimationTemplate);
        if (!selectedEstimationTemplate.valid) { // if form is not valid and user sent it, show error
            this.notificationsService.create(null, 'Procjena nije uspješno definirana! Pokušajte ponovo.', 'error');
            return; // to exit without calling backend
        }

        const estimation = this.prepareEstimationValues();
        this.estimationService.editSelectedEstimation(estimation).subscribe(
            (createdEstimation: Estimation) => estimation,
            () => {
                this.notificationsService.create(null, 'Došlo je do pogreške prilikom izmjene procjene!', 'error');
            },
            () => {
                this.notificationsService.create(null, 'Uspješno ste izmijenili procjenu', 'success');
                this.storeEstimationAndNavigateToEstimationsOverview(estimation);
            }
        );
    }

    prepareEstimationValues(): Estimation {
        // initialize form object
        const estimation = new Estimation(this.estimation.id, this.selectedStatus, this.selectedModel, this.inputEstimationName, new Date(),
            new Date());
        // return form model for backend
        return estimation;
    }

    /**
     * Stores estimation response of created estimation into a shared service which later passes the same estimation to another screen.
     * After a short delay, navigates to another screen.
     * @param estimation created estimation from backend
     */
    async storeEstimationAndNavigateToEstimationsOverview(estimation: Estimation) {
        this.dataSharingService.storage = estimation; // store orderForm to application wide storage
        await this.delay(2000).then(() => this.router.navigate(['admin/estimations-overview']));
    }

    /**
     * Creates delay for given time.
     * @param timeInMs given time of delay
     */
    private delay(timeInMs: number) {
        return new Promise(resolve => setTimeout(resolve, timeInMs));
    }
}
