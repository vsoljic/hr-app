import {Route} from '@angular/router';
import {EstimationsOverviewComponent} from 'app/admin/estimations-overview/estimations-overview.component';

export const ESTIMATIONS_OVERVIEW_ROUTE: Route = {
    path: 'estimations-overview',
    component: EstimationsOverviewComponent,
    data: {
        authorities: [],
        pageTitle: 'EstimationsOverview'
    }
};
