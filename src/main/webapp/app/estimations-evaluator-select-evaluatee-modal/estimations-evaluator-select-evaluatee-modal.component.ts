import {Component, Input, OnInit} from '@angular/core';
import {ModalDismissReasons, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {Employee} from 'app/admin/models/employee.model';
import {Relationship} from 'app/admin/models/relationship.model';
import {Router} from '@angular/router';
import {NotificationsService} from 'angular2-notifications';
import {DataSharingService} from 'app/shared/data-sharing.service';

@Component({
    selector: 'jhi-estimations-evaluator-select-evaluatee-modal',
    templateUrl: './estimations-evaluator-select-evaluatee-modal.component.html',
    styles: []
})
export class EstimationsEvaluatorSelectEvaluateeModalComponent implements OnInit {

    modalReference: NgbModalRef;
    closeResult: string;
    @Input() estimationId: number;
    @Input() estimationName: string;
    employees: Employee[];
    employeeString: string;
    evaluatorsIdList: number[] = [];
    relationshipEvaluatorList: Relationship[] = [];

    constructor(private modalService: NgbModal,
                private router: Router,
                private notificationsService: NotificationsService,
                private dataSharingService: DataSharingService) {
    }

    ngOnInit() {
    }

    openModalToChooseEvaluatee(content) {
        this.modalReference = this.modalService.open(content);
        this.modalReference.result.then(
            result => {
                this.closeResult = `Closed with: ${result}`;
            },
            reason => {
                this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
            }
        );

        /*  this.relationshipsService.getNotConnectedEmployeesForEstimation(this.estimationId).subscribe(
              (employees: Employee[]) => this.employees = employees,
              error => console.log('error fetching employees', error),
              () => console.log('success')
          );*/
    }

    onSelect(employee) {
        this.evaluatorsIdList.push(employee.id);
    }

    /**
     * Prepares estimation form for POST request to backend.
     */
    prepareRelationshipValues(evaluatorIdList: number[]): Relationship[] {
        const evaluateeIdList: number[] = [0];

        for (let idEvaluator of evaluatorIdList) {
            this.relationshipEvaluatorList.push(new Relationship(this.estimationId, idEvaluator, evaluateeIdList));
        }

        return this.relationshipEvaluatorList;
    }

    /**
     * Stores estimation response of created estimation into a shared service which later passes the same estimation to another screen.
     * After a short delay, navigates to another screen.
     * @param estimation created estimation from backend
     */
    async storeRelationshipsAndNavigateToMain(relationships: Relationship[]) {
        this.dataSharingService.storage = relationships; // store orderForm to application wide storage
        await this.delay(2000).then(() => this.router.navigate(['admin/estimations-overview']));
    }

    private getDismissReason(reason: any): string {
        if (reason === ModalDismissReasons.ESC) {
            return 'by pressing ESC';
        } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
            return 'by clicking on a backdrop';
        } else {
            return `with: ${reason}`;
        }
    }

    private createNewRelationship(relationshipTemplate) {
        /* console.log('relationshipTemplate', relationshipTemplate);
         if (!relationshipTemplate.valid) { // if form is not valid and user sent it, show error
             this.notificationsService.create(null, 'Veze nisu uspješno definirane! Pokušajte ponovo.', 'error');
             return; // to exit without calling backend
         }

         this.prepareRelationshipValues(this.evaluatorsIdList);
         this.relationshipsService.createNewRelationshipList(this.relationshipEvaluatorList).subscribe(
             (relationship: Relationship) => relationship,
             () => {
                 this.notificationsService.create(null, 'Došlo je do pogreške prilikom kreiranja veza!', 'error');
             },
             () => {
                 this.notificationsService.create(null, 'Uspješno ste kreirali veze', 'success');
                 this.storeRelationshipsAndNavigateToMain(this.relationshipEvaluatorList);
                 this.modalReference.close();
             }
         );*/
    }

    /**
     * Creates delay for given time.
     * @param timeInMs given time of delay
     */
    private delay(timeInMs: number) {
        return new Promise(resolve => setTimeout(resolve, timeInMs));
    }

}
