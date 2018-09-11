import { Component, Input, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Employee } from 'app/admin/models/employee.model';
import { Relationship } from 'app/admin/models/relationship.model';
import { Router } from '@angular/router';
import { NotificationsService } from 'angular2-notifications';
import { DataSharingService } from 'app/shared/data-sharing.service';
import { RelationshipsForEstimationService } from 'app/admin/relationships-for-estimation/relationships-for-estimation.service';

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
    evaluateeId: number;
    relationship: Relationship;

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

        this.relationshipsService
            .getEvaluateesForEvaluatorOnEstimation(this.estimationId)
            .subscribe(
                (employees: Employee[]) => (this.employees = employees),
                error => console.log('error fetching employees', error),
                () => console.log('success')
            );
    }

    onSelect(employee) {
        this.evaluateeId = employee.id;
    }

    openEvaluateePageAndSave(content) {
        this.modalReference.close();

        const evaluateeIdList: number[] = [this.evaluateeId];

        //todo: hardcoded, find a way to get logged employee id
        this.relationship = new Relationship(this.estimationId, 2, evaluateeIdList);

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
