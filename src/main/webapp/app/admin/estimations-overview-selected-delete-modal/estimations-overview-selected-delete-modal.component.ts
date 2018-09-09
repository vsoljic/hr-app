import {Component, Input} from '@angular/core';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {Router} from '@angular/router';
import {EstimationsOverviewService} from 'app/admin/estimations-overview/estimations-overview.service';
import {Employee} from 'app/admin/models/employee.model';

@Component({
    selector: 'jhi-estimations-overview-evaluator-delete-modal',
    templateUrl: './estimations-overview-selected-delete-modal.component.html',
    styles: []
})
export class EstimationsOverviewSelectedDeleteModalComponent {

    @Input() evaluatee: Employee;
    @Input() evaluateeName: string;
    @Input() estimationId: number;
    @Input() evaluatorId: number;

    closeResult: string;

    constructor(private modalService: NgbModal,
                private router: Router,
                private estimationOverviewService: EstimationsOverviewService) {
    }

    openDeleteEvaluateeModal(content) {
        this.modalService.open(content).result.then(result => {
            this.closeResult = `Closed with: ${result}`;
        }, reason => {
            this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        });
    }

    deleteEvaluatee() {
        this.estimationOverviewService.deleteSelectedEvaluateeForEvaluatorAndEstimation(this.estimationId,
            this.evaluatorId, this.evaluatee).subscribe();

        this.router.navigate(['admin/estimations-overview-selected']);

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
