import { Route } from '@angular/router';
import { EvaluatorComponent } from 'app/admin/evaluator/evaluator.component';

export const EVALUATOR_ROUTE: Route = {
    path: 'evaluator',
    component: EvaluatorComponent,
    data: {
        authorities: [],
        pageTitle: 'EvaluatorPage'
    }
};
