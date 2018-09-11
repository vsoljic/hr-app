import { Component, Input, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { NotificationsService } from 'angular2-notifications';
import { Goal } from 'app/admin/models/goal.model';
import { GoalService } from 'app/evaluatee/goal.service';
import { DataSharingService } from 'app/shared/data-sharing.service';

@Component({
    selector: 'jhi-create-new-goal-modal',
    templateUrl: './create-new-goal-modal.component.html',
    styleUrls: ['./create-new-goal-modal.scss']
})
export class CreateNewGoalModalComponent implements OnInit {
    closeResult: string;
    @Input() groupId: number;
    @Input() estimationId: number;
    @Input() employeeId: number;
    savedGoal: Goal;
    inputPonder: number;
    inputTargetPercentage: number;
    inputGoalName: string;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private notificationsService: NotificationsService,
        private goalService: GoalService,
        private dataSharingService: DataSharingService
    ) {}

    ngOnInit() {}

    openCreateNewGoalModal(content) {
        this.modalService.open(content).result.then(
            result => {
                this.closeResult = `Closed with: ${result}`;
            },
            reason => {
                this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
            }
        );
    }

    createNewGoal(goalTemplate: NgForm) {
        console.log('goal', goalTemplate);
        /*if (!goalTemplate.valid) {
            this.notificationsService.create(null, 'Cilj nije uspješno definiran! Pokušajte ponovo.', 'error');
            return; // to exit without calling backend
        }
*/
        const goal = this.prepareEstimationValues();

        this.goalService.createNewGoal(goal).subscribe(
            (data: Goal) => {
                this.savedGoal = data;
            },
            () => {
                this.notificationsService.create(null, 'Došlo je do pogreške prilikom kreiranja cilja!', 'error');
            },
            () => {
                this.notificationsService.create(null, 'Uspješno ste kreirali cilj!', 'success');
                this.storeGoal(this.savedGoal);
            }
        );
    }

    /**
     * Prepares goal form for POST request to backend.
     */
    prepareEstimationValues(): Goal {
        // initialize form object
        // TODO: zahardkodirano
        const goal = new Goal(null, 'Blabla', 10, 100, null, this.groupId, this.employeeId, this.estimationId);
        // return form estimation for backend
        return goal;
    }

    /**
     * Stores estimation response of created estimation into a shared service which later passes the same estimation to another screen.
     * After a short delay, navigates to another screen.
     * @param created estimation from backend
     */
    async storeGoal(goal: Goal) {
        this.dataSharingService.storage = goal;
        await this.delay(50).then(() => this.router.navigate(['/evaluatee']));
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

    /**
     * Creates delay for given time.
     * @param timeInMs given time of delay
     */
    private delay(timeInMs: number) {
        return new Promise(resolve => setTimeout(resolve, timeInMs));
    }
}
