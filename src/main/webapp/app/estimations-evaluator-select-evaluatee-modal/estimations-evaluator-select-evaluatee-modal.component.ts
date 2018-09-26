import { Component, Input, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Employee } from 'app/admin/models/employee.model';
import { Router } from '@angular/router';
import { NotificationsService } from 'angular2-notifications';
import { DataSharingService } from 'app/shared/data-sharing.service';
import { RelationshipsForEstimationService } from 'app/admin/relationships-for-estimation/relationships-for-estimation.service';
import { Estimation } from 'app/admin/models/estimation.model';
import { RelationshipWithObjectsModel } from 'app/admin/models/relationship-with-objects.model';

@Component({
    selector: 'jhi-estimations-evaluator-select-evaluatee-modal',
    templateUrl: './estimations-evaluator-select-evaluatee-modal.component.html',
    styles: []
})
export class EstimationsEvaluatorSelectEvaluateeModalComponent implements OnInit {
    modalReference: NgbModalRef;
    closeResult: string;
    @Input() estimation: Estimation;
    @Input() estimationName: string;
    @Input() evaluator: Employee;
    employees: Employee[];
    selectedEvaluatee: Employee;
    relationship: RelationshipWithObjectsModel;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private notificationsService: NotificationsService,
        private dataSharingService: DataSharingService,
        private relationshipsService: RelationshipsForEstimationService
    ) {}

    ngOnInit() {}

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

        this.relationshipsService.getEvaluateesForEvaluatorOnEstimation(this.estimation.id).subscribe(
            (employees: Employee[]) => {
                this.employees = employees.map(e => {
                    e.fullName = e.firstName + ' ' + e.lastName;
                    return e;
                });
            },
            error => console.log('error fetching employees', error),
            () => console.log('success')
        );
    }

    onSelect(employee) {
        console.log('Odabir employeea' + employee);

        this.selectedEvaluatee = employee;
    }

    openEvaluateePageAndSave(content) {
        this.modalReference.close();

        this.relationship = new RelationshipWithObjectsModel(this.estimation, this.evaluator, this.selectedEvaluatee);
        this.dataSharingService.storage = this.relationship;

        this.router.navigate(['/evaluatee']);
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
