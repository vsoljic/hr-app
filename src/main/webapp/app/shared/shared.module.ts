import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {NgbDateAdapter} from '@ng-bootstrap/ng-bootstrap';

import {NgbDateMomentAdapter} from './util/datepicker-adapter';
import {HasAnyAuthorityDirective, HrAppSharedCommonModule, HrAppSharedLibsModule, JhiLoginModalComponent} from './';
import {SimpleNotificationsModule} from 'angular2-notifications';
import {DataSharingService} from 'app/shared/data-sharing.service';

@NgModule({
    imports: [HrAppSharedLibsModule, HrAppSharedCommonModule,
        SimpleNotificationsModule.forRoot({
            position: ['top', 'right'],
            timeOut: 5000,
            showProgressBar: true,
            pauseOnHover: true,
            clickToClose: true,
            animate: 'scale'
        })],
    declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
    providers: [{provide: NgbDateAdapter, useClass: NgbDateMomentAdapter}, DataSharingService],
    entryComponents: [JhiLoginModalComponent],
    exports: [HrAppSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HrAppSharedModule {
}
