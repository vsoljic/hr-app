import {Route} from '@angular/router';
import {EstimationsEvaluateeComponent} from 'app/estimations-evaluatee/estimations-evaluatee.component';

export const ESTIMATIONS_EVALUATEE_ROUTE: Route = {
    path: 'estimations-evaluatee',
    component: EstimationsEvaluateeComponent,
    data: {
        authorities: [],
        pageTitle: 'Estimations Evaluatee'
    }
};
