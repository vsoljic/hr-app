import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ModalDismissReasons, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { NotificationsService } from 'angular2-notifications';
import { Goal } from 'app/admin/models/goal.model';
import { GoalService } from 'app/evaluatee/goal.service';
import { GroupOfGoals } from 'app/admin/models/group-of-goals.model';
import { Estimation } from 'app/admin/models/estimation.model';
import { Employee } from 'app/admin/models/employee.model';
import { GroupService } from 'app/evaluatee/group.service';

@Component({
    selector: 'jhi-create-new-goal-modal',
    templateUrl: './create-new-goal-modal.component.html',
    styleUrls: ['./create-new-goal-modal.scss']
})
export class CreateNewGoalModalComponent implements OnInit {
    closeResult: string;
    @Input() group: GroupOfGoals;
    @Input() estimation: Estimation;
    @Input() employee: Employee;
    savedGoal: Goal;
    inputPonder: number;
    inputTargetPercentage: number;
    inputGoalName: string;
    modalRef: NgbModalRef;
    groups: GroupOfGoals[];
    @Output() groupsEmitter: EventEmitter<GroupOfGoals[]> = new EventEmitter<GroupOfGoals[]>();
    employeeId: number;
    estimationId: number;
    illegalPonderValue: boolean;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private notificationsService: NotificationsService,
        private goalService: GoalService,
        private groupService: GroupService
    ) {}

    ngOnInit() {
        this.illegalPonderValue = false;
        this.employeeId = this.employee.id;
        this.estimationId = this.estimation.id;

        console.log('Emp ID ' + this.employeeId);
        console.log('EST ID ' + this.estimationId);
    }

    openCreateNewGoalModal(content) {
        this.illegalPonderValue = false;
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

    createNewGoal(goalTemplate: NgForm) {
        console.log('goal', goalTemplate);
        if (!goalTemplate.valid) {
            this.notificationsService.create(null, 'Cilj nije uspješno definiran! Pokušajte ponovo.', 'error');
            return; // to exit without calling backend
        }

        const isPonderValid = this.inputPonder + this.group.totalPonderForGroup;
        if (isPonderValid > 100) {
            this.illegalPonderValue = true;
            return;
        }

        const goal = this.prepareEstimationValues();
        console.log('Objekt goal' + goal);
        console.log('Objekt goal ponder' + goal.ponderPercentage);

        this.goalService.saveGoal(goal).subscribe(
            (data: Goal) => {
                this.savedGoal = data;

                console.log('Emp ID unutar next' + this.employeeId);
                console.log('EST ID unutar next' + this.estimationId);
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
                console.log('PONOVNI DOHVAT grupa ' + this.groups);
                console.log('PONOVNI DOHVAT grupa, groupsEmitter ' + this.groupsEmitter);
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
        this.deleteAllModalValues();
        this.modalRef.close();
    }

    /**
     * Prepares goal form for POST request to backend.
     */
    prepareEstimationValues(): Goal {
        // initialize form object

        console.log('GOAL NAME:' + this.inputGoalName);
        console.log('GOAL PONDER:' + this.inputPonder);
        console.log('GOAL TARGET:' + this.inputTargetPercentage);
        console.log('GOAL GROUP:' + this.group);
        console.log('GOAL EMP:' + this.employee);
        console.log('GOAL EST:' + this.estimation);

        const goal = new Goal(
            null,
            this.inputGoalName,
            this.inputPonder,
            this.inputTargetPercentage,
            null,
            this.group,
            this.employee,
            this.estimation
        );
        // return form estimation for backend
        return goal;
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
