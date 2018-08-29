import {Routes} from '@angular/router';

import {auditsRoute, configurationRoute, docsRoute, healthRoute, logsRoute, metricsRoute, userMgmtRoute} from './';

import {UserRouteAccessService} from 'app/core';
import {ESTIMATION_ROUTE} from 'app/admin/estimation/estimation.route';
import {RELATIONSHIPS_FOR_ESTIMATION} from 'app/admin/relationships-for-estimation/relationships-for-estimation.route';
import {EVALUATEE_ROUTE} from 'app/admin/evaluatee/evaluatee.route';
import {ESTIMATIONS_OVERVIEW_EVALUATOR_ROUTE} from 'app/admin/estimations-overview-evaluator/estimations-overview-evaluator.route';
import {ESTIMATIONS_OVERVIEW_ROUTE} from 'app/admin/estimations-overview/estimations-overview.route';
import {ESTIMATION_EDIT_ROUTE} from 'app/admin/estimation-edit/estimation-edit.route';

const ADMIN_ROUTES = [
    auditsRoute,
    configurationRoute,
    docsRoute,
    healthRoute,
    logsRoute,
    ...userMgmtRoute,
    metricsRoute,
    ESTIMATION_ROUTE,
    RELATIONSHIPS_FOR_ESTIMATION,
    EVALUATEE_ROUTE,
    ESTIMATIONS_OVERVIEW_ROUTE,
    ESTIMATIONS_OVERVIEW_EVALUATOR_ROUTE,
    ESTIMATION_EDIT_ROUTE
];

export const adminState: Routes = [
    {
        path: '',
        data: {
            authorities: ['ROLE_ADMIN']
        },
        canActivate: [UserRouteAccessService],
        children: ADMIN_ROUTES
    }
];
