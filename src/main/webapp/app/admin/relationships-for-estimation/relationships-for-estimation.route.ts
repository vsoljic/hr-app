import { Route } from '@angular/router';
import { RelationshipsForEstimationComponent } from 'app/admin/relationships-for-estimation/relationships-for-estimation.component';

export const RELATIONSHIPS_FOR_ESTIMATION: Route = {
    path: 'relationships',
    component: RelationshipsForEstimationComponent,
    data: {
        authorities: [],
        pageTitle: 'Relationships'
    }
};
