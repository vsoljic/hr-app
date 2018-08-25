import { EstimationComponent } from 'app/admin/estimation/estimation.component';
import { Route } from '@angular/router';

export const ESTIMATION_ROUTE: Route = {
    path: 'estimation',
    component: EstimationComponent,
    data: {
        authorities: [],
        pageTitle: 'Estimation'
    }
};
