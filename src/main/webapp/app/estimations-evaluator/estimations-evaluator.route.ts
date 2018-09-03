import {Route} from '@angular/router';
import {EstimationsEvaluatorComponent} from 'app/estimations-evaluator/estimations-evaluator.component';

export const ESTIMATIONS_EVALUATOR_ROUTE: Route = {
    path: 'estimations-evaluator',
    component: EstimationsEvaluatorComponent,
    data: {
        authorities: [],
        pageTitle: 'Estimations Evaluator'
    }
};
