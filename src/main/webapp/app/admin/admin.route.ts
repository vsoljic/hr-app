import {Routes} from '@angular/router';

import {auditsRoute, configurationRoute, docsRoute, healthRoute, logsRoute, metricsRoute, userMgmtRoute} from './';

import {UserRouteAccessService} from 'app/core';
import {ESTIMATION_ROUTE} from 'app/admin/estimation/estimation.route';
import {RELATIONSHIPS_FOR_ESTIMATION} from 'app/admin/relationships-for-estimation/relationships-for-estimation.route';
import {ESTIMATIONS_OVERVIEW_SELECTED_ROUTE} from 'app/admin/estimations-overview-selected/estimations-overview-selected.route';
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
    ESTIMATIONS_OVERVIEW_ROUTE,
    ESTIMATIONS_OVERVIEW_SELECTED_ROUTE,
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
