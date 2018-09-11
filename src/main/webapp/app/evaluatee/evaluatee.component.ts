import { Component, Input, OnInit } from '@angular/core';
import { EvaluateeService } from 'app/evaluatee/evaluatee.service';
import { GroupOfGoals } from 'app/admin/models/group-of-goals.model';
import { Relationship } from 'app/admin/models/relationship.model';
import { DataSharingService } from 'app/shared/data-sharing.service';
import { Goal } from 'app/admin/models/goal.model';
import { GoalService } from 'app/evaluatee/goal.service';
import { NotificationsService } from 'angular2-notifications';

@Component({
    selector: 'jhi-evaluatee',
    templateUrl: './evaluatee.component.html',
    styleUrls: ['./evaluatee.scss']
})
export class EvaluateeComponent implements OnInit {
    groups: GroupOfGoals[];
    @Input() employeeName: string;
    employeeId: number;
    relationship: Relationship;
    goalsOnGroup: Goal[];
    selectedGroup: GroupOfGoals;

    constructor(
        private evaluateeService: EvaluateeService,
        private dataSharingService: DataSharingService,
        private goalService: GoalService,
        private notificationsService: NotificationsService
    ) {}

    ngOnInit() {
        this.relationship = this.dataSharingService.storage;
        this.dataSharingService.storage = null;

        this.employeeId = this.relationship.evaluateeIdList.indexOf(0, 1);

        //TODO: HARDKODIRANA VRIJEDNOST
        this.evaluateeService.getGroupsByEstimation(this.relationship.estimationId).subscribe(
            (g: GroupOfGoals[]) => {
                this.groups = g;
                // TODO: ZAHARDKODIRANO
                for (let group of g) {
                    this.goalService.getGoalsByEmployeeAndGroupAndEst(1, group.id, this.relationship.estimationId).subscribe(
                        (data: Goal) => {
                            this.selectedGroup = data;
                        },
                        () => {
                            this.notificationsService.create(null, 'Došlo je do pogreške prilikom dohvata goalova!', 'error');
                        },
                        () => {
                            this.notificationsService.create(null, 'Uspješno ste dohvatili goalove', 'success');
                        }
                    );
                }
            },
            error => console.log('error fetching groups', error),
            () => console.log('success')
        );
    }
}
