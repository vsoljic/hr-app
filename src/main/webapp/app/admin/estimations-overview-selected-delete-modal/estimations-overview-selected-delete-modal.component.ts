import {Component, Input} from '@angular/core';
import {ModalDismissReasons, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {Router} from '@angular/router';
import {EstimationsOverviewService} from 'app/admin/estimations-overview/estimations-overview.service';
import {Employee} from 'app/admin/models/employee.model';
import {NotificationsService} from 'angular2-notifications';

@Component({
    selector: 'jhi-estimations-overview-evaluator-delete-modal',
    templateUrl: './estimations-overview-selected-delete-modal.component.html',
    styles: []
})
export class EstimationsOverviewSelectedDeleteModalComponent {
    modalReference: NgbModalRef;
    @Input() evaluatee: Employee;
    @Input() evaluateeName: string;
    @Input() estimationId: number;
    @Input() evaluatorId: number;

    closeResult: string;

    constructor(private modalService: NgbModal,
                private router: Router,
                private estimationOverviewService: EstimationsOverviewService,
                private notificationsService: NotificationsService) {
    }

    openDeleteEvaluateeModal(content) {
        this.modalReference = this.modalService.open(content);
        this.modalReference.result.then(result => {
            this.closeResult = `Closed with: ${result}`;
        }, reason => {
            this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        });
    }

    deleteEvaluatee() {
        this.estimationOverviewService.deleteSelectedEvaluateeForEvaluatorAndEstimation(this.estimationId,
            this.evaluatorId, this.evaluatee).subscribe(
            () => console.log('Success on deleting evaluatee'),
            () => {
                this.notificationsService.create(null, 'Došlo je do pogreške prilikom kreiranja veza!', 'error');
            },
            () => {
                this.notificationsService.create(null, 'Uspješno ste izbrisali procjenjenika', 'success');
                this.modalReference.close();
            }
        );

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
