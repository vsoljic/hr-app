import {Component, Input, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ModalDismissReasons, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {Employee} from 'app/admin/models/employee.model';
import {RelationshipsForEstimationService} from 'app/admin/relationships-for-estimation/relationships-for-estimation.service';
import {NotificationsService} from 'angular2-notifications';
import {Relationship} from 'app/admin/models/relationship.model';
import {DataSharingService} from 'app/shared/data-sharing.service';
import {Estimation} from 'app/admin/models/estimation.model';

@Component({
    selector: 'jhi-create-relationship-roles-modal',
    templateUrl: './create-relationship-roles-modal.component.html',
    styleUrls: ['./create-relationship-roles-modal.scss']
})
export class CreateRelationshipRolesModalComponent implements OnInit {
    modalReference: NgbModalRef;
    closeResult: string;
    @Input() estimation: Estimation;
    @Input() employeeId: number;
    @Input() employeeName: string;
    employees: Employee[];
    employeeString: string;
    evaluateeIdList: number[] = [];
    isEvaluator: boolean = false;

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

        this.relationshipsService.getEmployeesExpectSelectedOne(this.employeeId).subscribe(
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
        const relationship = new Relationship(this.estimation.id, this.employeeId, this.evaluateeIdList);

        return relationship;
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
                this.isEvaluator = true;
                this.storeRelationshipsAndNavigateToMain(relationship);
                this.modalReference.close();
            }
        );
    }

    /**
     * Stores estimation response of created estimation into a shared service which later passes the same estimation to another screen.
     * After a short delay, navigates to another screen.
     * @param estimation created estimation from backend
     */
    async storeRelationshipsAndNavigateToMain(relationship: Relationship) {
        this.dataSharingService.storage = relationship; // store orderForm to application wide storage
        this.dataSharingService.storage = this.isEvaluator;
        await this.delay(10).then(() => this.modalReference.close());
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
