import {Route} from '@angular/router';
import {EstimationsOverviewSelectedComponent} from 'app/admin/estimations-overview-selected/estimations-overview-selected.component';

export const ESTIMATIONS_OVERVIEW_SELECTED_ROUTE: Route = {
    path: 'estimations-overview-selected',
    component: EstimationsOverviewSelectedComponent,
    data: {
        authorities: [],
        pageTitle: 'EstimationsOverviewSelectedItem'
    }
};
