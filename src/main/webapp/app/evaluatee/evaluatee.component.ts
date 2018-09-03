import {Component, OnInit} from '@angular/core';
import {EvaluateeService} from 'app/evaluatee/evaluatee.service';
import {Estimation} from 'app/admin/models/estimation.model';
import {GroupOfGoals} from 'app/admin/models/group-of-goals.model';

@Component({
    selector: 'jhi-evaluatee',
    templateUrl: './evaluatee.component.html',
    styleUrls: ['./evaluatee.scss']
})
export class EvaluateeComponent implements OnInit {
    estimation: Estimation;
    groups: GroupOfGoals[];

    constructor(private evaluateeService: EvaluateeService) {
    }

    ngOnInit() {
        this.evaluateeService.getGroupsByEstimation(14).subscribe(
            (g: GroupOfGoals[]) => this.groups = g,
            error => console.log('error fetching groups', error),
            () => console.log('success')
        );
    }
}
