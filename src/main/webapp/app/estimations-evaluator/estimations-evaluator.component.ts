import {Component, OnInit} from '@angular/core';
import {Account, AccountService, Principal} from 'app/core';
import {EstimationsEvaluatorService} from 'app/estimations-evaluator/estimations-evaluator.service';

@Component({
    selector: 'jhi-estimations-evaluator',
    templateUrl: './estimations-evaluator.component.html',
    styles: []
})
export class EstimationsEvaluatorComponent implements OnInit {
   /* account: Account;*/

    constructor(private principal: Principal, private estimationsEvaluatorService: EstimationsEvaluatorService) {
    }

    ngOnInit() {

        this.estimationsEvaluatorService.getEstimationsForEvaluator().subscribe();

    /*    this.principal.identity().then(account => {
            this.account = account;
        });

       console.log('acc: ' + this.account);*/
    }

}
