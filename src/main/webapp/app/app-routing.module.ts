import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {errorRoute, navbarRoute} from './layouts';
import {DEBUG_INFO_ENABLED} from 'app/app.constants';
import {EVALUATEE_ROUTE} from 'app/evaluatee/evaluatee.route';
import {ESTIMATIONS_EVALUATOR_ROUTE} from 'app/estimations-evaluator/estimations-evaluator.route';
import {ESTIMATIONS_EVALUATEE_ROUTE} from 'app/estimations-evaluatee/estimations-evaluatee.route';

const LAYOUT_ROUTES = [navbarRoute, ...errorRoute];

@NgModule({
    imports: [
        RouterModule.forRoot(
            [
                ...LAYOUT_ROUTES,
                {
                    path: 'admin',
                    loadChildren: './admin/admin.module#HrAppAdminModule'
                },
                EVALUATEE_ROUTE,
                ESTIMATIONS_EVALUATOR_ROUTE,
                ESTIMATIONS_EVALUATEE_ROUTE
            ],
            {useHash: true, enableTracing: DEBUG_INFO_ENABLED}
        )
    ],
    exports: [RouterModule]
})
export class HrAppAppRoutingModule {
}
