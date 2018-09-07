import {Component, Input, OnInit} from '@angular/core';
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
    @Input() employeeName: string;

    constructor(private evaluateeService: EvaluateeService) {
    }

    ngOnInit() {
        //TODO: HARDKODIRANA VRIJEDNOST
        this.evaluateeService.getGroupsByEstimation(2).subscribe(
            (g: GroupOfGoals[]) => this.groups = g,
            error => console.log('error fetching groups', error),
            () => console.log('success')
        );
    }
}
