import { Component, OnInit } from '@angular/core';
import { EvaluateeService } from 'app/evaluatee/evaluatee.service';
import { GroupOfGoals } from 'app/admin/models/group-of-goals.model';
import { DataSharingService } from 'app/shared/data-sharing.service';
import { GoalService } from 'app/evaluatee/goal.service';
import { NotificationsService } from 'angular2-notifications';
import { RelationshipWithObjectsModel } from 'app/admin/models/relationship-with-objects.model';
import { Employee } from 'app/admin/models/employee.model';
import { GroupService } from 'app/evaluatee/group.service';

@Component({
    selector: 'jhi-evaluatee',
    templateUrl: './evaluatee.component.html',
    styleUrls: ['./evaluatee.scss']
})
export class EvaluateeComponent implements OnInit {
    groups: GroupOfGoals[];
    evaluatee: Employee;
    relationship: RelationshipWithObjectsModel;
    estimationStatus: number;
    finalAchievement: number = 0;

    constructor(
        private evaluateeService: EvaluateeService,
        private dataSharingService: DataSharingService,
        private goalService: GoalService,
        private groupService: GroupService,
        private notificationsService: NotificationsService
    ) {}

    ngOnInit() {
        this.relationship = this.dataSharingService.storage;

        this.evaluatee = this.relationship.evaluatee;
        this.estimationStatus = this.relationship.estimation.status.id;

        this.getGroupsAndGoals();
    }

    getGroupsAndGoals() {
        this.groupService.getGroupsByEmployeeAndEstimationWithGoals(this.evaluatee.id, this.relationship.estimation.id).subscribe(
            (data: GroupOfGoals[]) => {
                this.groups = data;
            },
            () => console.log('Unsuccessful'),
            () => this.setFinalAchievement()
        );
    }

    onGroupsEmit(event) {
        this.groups = event;
        this.finalAchievement = 0;
        this.setFinalAchievement();
        console.log('Hello to parent, groups: ' + this.groups + ',event:' + event);
    }

    setFinalAchievement() {
        for (let group of this.groups) {
            this.finalAchievement += group.totalAchievementForGroup;
        }
    }
}
