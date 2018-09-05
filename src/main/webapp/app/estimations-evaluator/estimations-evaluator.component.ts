import {Component, OnInit} from '@angular/core';
import {Principal} from 'app/core';
import {EstimationsEvaluatorService} from 'app/estimations-evaluator/estimations-evaluator.service';
import {Estimation} from 'app/admin/models/estimation.model';

@Component({
    selector: 'jhi-estimations-evaluator',
    templateUrl: './estimations-evaluator.component.html',
    styles: []
})
export class EstimationsEvaluatorComponent implements OnInit {

    estimations: Estimation[];

    constructor(private principal: Principal, private estimationsEvaluatorService: EstimationsEvaluatorService) {
    }

    ngOnInit() {

        this.estimationsEvaluatorService.getEstimationsForEvaluator().subscribe(
            (estimations: Estimation[]) => this.estimations = estimations,
            error => console.log('error fetching estimations', error),
            () => console.log('success')

        );

    }

}
