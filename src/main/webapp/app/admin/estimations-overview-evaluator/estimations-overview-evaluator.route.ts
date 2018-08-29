import { Route } from '@angular/router';
import { EstimationsOverviewEvaluatorComponent } from 'app/admin/estimations-overview-evaluator/estimations-overview-evaluator.component';

export const ESTIMATIONS_OVERVIEW_EVALUATOR_ROUTE: Route = {
    path: 'estimations-overview-evaluator',
    component: EstimationsOverviewEvaluatorComponent,
    data: {
        authorities: [],
        pageTitle: 'EstimationsOverviewPerEvaluator'
    }
};
