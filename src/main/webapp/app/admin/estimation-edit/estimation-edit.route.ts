import {Route} from '@angular/router';
import {EstimationEditComponent} from 'app/admin/estimation-edit/estimation-edit.component';

export const ESTIMATION_EDIT_ROUTE: Route = {
    path: 'estimation/edit',
    component: EstimationEditComponent,
    data: {
        authorities: [],
        pageTitle: 'EstimationEdit'
    }
};
