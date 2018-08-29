import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {NgbDateAdapter} from '@ng-bootstrap/ng-bootstrap';

import {NgbDateMomentAdapter} from './util/datepicker-adapter';
import {HasAnyAuthorityDirective, HrAppSharedCommonModule, HrAppSharedLibsModule, JhiLoginModalComponent} from './';
import {DataSharingService} from 'app/shared/data-sharing.service';

@NgModule({
    imports: [HrAppSharedLibsModule, HrAppSharedCommonModule],
    declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
    providers: [{provide: NgbDateAdapter, useClass: NgbDateMomentAdapter}, DataSharingService],
    entryComponents: [JhiLoginModalComponent],
    exports: [HrAppSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HrAppSharedModule {
}
