import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ModalDismissReasons, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {Employee} from 'app/admin/models/employee.model';
import {Router} from '@angular/router';
import {RelationshipsForEstimationService} from 'app/admin/relationships-for-estimation/relationships-for-estimation.service';
import {NotificationsService} from 'angular2-notifications';
import {DataSharingService} from 'app/shared/data-sharing.service';
import {Relationship} from 'app/admin/models/relationship.model';

@Component({
    selector: 'jhi-estimations-overview-evaluator-add-rel-modal',
    templateUrl: './estimations-overview-selected-add-rel-modal.component.html',
    styles: []
})
export class EstimationsOverviewSelectedAddRelModalComponent implements OnInit {

    modalReference: NgbModalRef;
    closeResult: string;
    @Input() estimationId: number;
    @Input() selectedEvaluatorId: number;
    @Input() selectedEvaluatorName: string;
    employees: Employee[];
    evaluateeIdList: number[] = [];


    constructor(private modalService: NgbModal,
                private router: Router,
                private relationshipsService: RelationshipsForEstimationService,
                private notificationsService: NotificationsService,
                private dataSharingService: DataSharingService) {
    }

    ngOnInit() {
    }

    openModalForRelationshipsAndRoles(content) {
        this.modalReference = this.modalService.open(content);
        this.modalReference.result.then(
            result => {
                this.closeResult = `Closed with: ${result}`;
            },
            reason => {
                this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
            }
        );

        this.relationshipsService.getNotConnectedEmployeesForEvaluator(this.selectedEvaluatorId, this.estimationId).subscribe(
            (employees: Employee[]) => this.employees = employees,
            error => console.log('error fetching employees', error),
            () => console.log('success')
        );
    }

    onSelect(employee) {
        this.evaluateeIdList.push(employee.id);
    }

    /**
     * Prepares estimation form for POST request to backend.
     */
    prepareRelationshipValues(): Relationship {
        const relationship = new Relationship(this.estimationId, this.selectedEvaluatorId, this.evaluateeIdList);

        return relationship;
    }

    /**
     * Stores estimation response of created estimation into a shared service which later passes the same estimation to another screen.
     * After a short delay, navigates to another screen.
     * @param estimation created estimation from backend
     */
    async storeRelationshipsAndNavigateToMain(relationship: Relationship) {
        this.dataSharingService.storage = relationship; // store orderForm to application wide storage
        await this.delay(10).then(() => this.router.navigate(['admin/estimations-overview-selected']));
    }

    private createNewRelationship(relationshipTemplate) {
        console.log('relationshipTemplate', relationshipTemplate);
        if (!relationshipTemplate.valid) { // if form is not valid and user sent it, show error
            this.notificationsService.create(null, 'Veze nisu uspješno definirane! Pokušajte ponovo.', 'error');
            return; // to exit without calling backend
        }

        const relationship = this.prepareRelationshipValues();
        this.relationshipsService.createNewRelationship(relationship).subscribe(
            (relationship: Relationship) => relationship,
            () => {
                this.notificationsService.create(null, 'Došlo je do pogreške prilikom kreiranja veza!', 'error');
            },
            () => {
                this.notificationsService.create(null, 'Uspješno ste kreirali veze', 'success');
                this.storeRelationshipsAndNavigateToMain(relationship);
                this.modalReference.close();
            }
        );
    }

    /**
     * Creates delay for given time.
     * @param timeInMs given time of delay
     */
    private delay(timeInMs: number) {
        return new Promise(resolve => setTimeout(resolve, timeInMs));
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

}
