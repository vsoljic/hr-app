import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ModalDismissReasons, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { GroupOfGoals } from 'app/admin/models/group-of-goals.model';
import { Estimation } from 'app/admin/models/estimation.model';
import { Employee } from 'app/admin/models/employee.model';
import { Goal } from 'app/admin/models/goal.model';
import { NotificationsService } from 'angular2-notifications';
import { GoalService } from 'app/evaluatee/goal.service';
import { GroupService } from 'app/evaluatee/group.service';
import { NgForm } from '@angular/forms';

@Component({
    selector: 'jhi-edit-goal-modal',
    templateUrl: './edit-goal-modal.component.html',
    styles: []
})
export class EditGoalModalComponent implements OnInit {
    closeResult: string;
    @Input() group: GroupOfGoals;
    @Input() estimation: Estimation;
    @Input() employee: Employee;
    @Input() selectedGoal: Goal;
    savedGoal: Goal;
    inputPonder: number;
    inputTargetPercentage: number;
    inputGoalName: string;
    inputAchievementPercentage: number;
    modalRef: NgbModalRef;
    groups: GroupOfGoals[];
    @Output() groupsEmitter: EventEmitter<GroupOfGoals[]> = new EventEmitter<GroupOfGoals[]>();
    employeeId: number;
    estimationId: number;
    illegalPonderValue: boolean;
    estimationStatus: number;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private notificationsService: NotificationsService,
        private goalService: GoalService,
        private groupService: GroupService
    ) {}

    ngOnInit() {
        this.employeeId = this.employee.id;
        this.estimationId = this.estimation.id;
        this.setValues();
    }

    openEditGoalModal(content) {
        this.setValues();
        this.modalRef = this.modalService.open(content);
        this.modalRef.result.then(
            result => {
                this.closeResult = `Closed with: ${result}`;
            },
            reason => {
                this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
            }
        );
    }

    editGoal(goalTemplate: NgForm) {
        console.log('goal', goalTemplate);
        if (!goalTemplate.valid) {
            this.notificationsService.create(null, 'Cilj nije uspješno definiran! Pokušajte ponovo.', 'error');
            return; // to exit without calling backend
        }

        const isPonderValid = this.group.totalPonderForGroup + this.inputPonder - this.selectedGoal.ponderPercentage;
        if (isPonderValid > 100) {
            this.illegalPonderValue = true;
            return;
        }

        this.group.totalPonderForGroup = isPonderValid;
        const goal = this.prepareEstimationValues();
        console.log('Objekt goal' + goal);
        console.log('Objekt goal ponder' + goal.ponderPercentage);

        this.goalService.saveGoal(goal).subscribe(
            (data: Goal) => {
                this.savedGoal = data;
            },
            () => {
                this.notificationsService.create(null, 'Došlo je do pogreške prilikom kreiranja cilja!', 'error');
            },
            () => {
                this.modalRef.close();
                this.deleteAllModalValues();
                this.notificationsService.create(null, 'Uspješno ste kreirali cilj!', 'success');
                this.getGroupsAndGoals();
            }
        );
    }

    getGroupsAndGoals() {
        this.groupService.getGroupsByEmployeeAndEstimationWithGoals(this.employeeId, this.estimationId).subscribe(
            (data: GroupOfGoals[]) => {
                this.groups = data;
            },
            () => console.log('Unsuccessful'),
            () => this.groupsEmitter.emit(this.groups)
        );
    }

    deleteAllModalValues() {
        this.inputTargetPercentage = null;
        this.inputPonder = null;
        this.inputGoalName = null;
    }

    closeModal() {
        this.modalRef.close();
    }

    /**
     * Prepares goal form for POST request to backend.
     */
    prepareEstimationValues(): Goal {
        let achievementPercentage = null;

        if (this.estimationStatus === 4) {
            achievementPercentage = this.inputAchievementPercentage;
        }

        const goal = new Goal(
            this.selectedGoal.id,
            this.inputGoalName,
            this.inputPonder,
            this.inputTargetPercentage,
            achievementPercentage,
            this.group,
            this.employee,
            this.estimation
        );

        return goal;
    }

    private setValues() {
        this.illegalPonderValue = false;
        this.inputPonder = this.selectedGoal.ponderPercentage;
        this.inputGoalName = this.selectedGoal.name;
        this.inputTargetPercentage = this.selectedGoal.targetValue;
        this.inputAchievementPercentage = this.selectedGoal.achievementPercentage;
        this.estimationStatus = this.estimation.status.id;
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
