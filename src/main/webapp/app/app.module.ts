import './vendor.ts';

import {Injector, NgModule} from '@angular/core';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {LocalStorageService, Ng2Webstorage, SessionStorageService} from 'ngx-webstorage';
import {JhiEventManager} from 'ng-jhipster';

import {AuthInterceptor} from './blocks/interceptor/auth.interceptor';
import {AuthExpiredInterceptor} from './blocks/interceptor/auth-expired.interceptor';
import {ErrorHandlerInterceptor} from './blocks/interceptor/errorhandler.interceptor';
import {NotificationInterceptor} from './blocks/interceptor/notification.interceptor';
import {HrAppSharedCommonModule, HrAppSharedModule} from 'app/shared';
import {HrAppCoreModule} from 'app/core';
import {HrAppAppRoutingModule} from './app-routing.module';
import {HrAppHomeModule} from './home/home.module';
import {HrAppAccountModule} from './account/account.module';
import {HrAppEntityModule} from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import {
    ActiveMenuDirective,
    ErrorComponent,
    FooterComponent,
    JhiMainComponent,
    NavbarComponent,
    PageRibbonComponent
} from './layouts';
import {GoalGroupComponent} from './goal-group/goal-group.component';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {SimpleNotificationsModule} from 'angular2-notifications';

@NgModule({
    imports: [
        BrowserModule,
        HrAppAppRoutingModule,
        BrowserAnimationsModule,
        Ng2Webstorage.forRoot({prefix: 'jhi', separator: '-'}),
        HrAppSharedModule,
        HrAppCoreModule,
        HrAppHomeModule,
        HrAppAccountModule,
        HrAppEntityModule,
        SimpleNotificationsModule.forRoot({
            position: ['top', 'right'],
            timeOut: 5000,
            showProgressBar: true,
            pauseOnHover: true,
            clickToClose: true,
            animate: 'scale'
        })
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        ActiveMenuDirective,
        FooterComponent,
        GoalGroupComponent
    ],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthInterceptor,
            multi: true,
            deps: [LocalStorageService, SessionStorageService]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthExpiredInterceptor,
            multi: true,
            deps: [Injector]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: ErrorHandlerInterceptor,
            multi: true,
            deps: [JhiEventManager]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: NotificationInterceptor,
            multi: true,
            deps: [Injector]
        }
    ],
    bootstrap: [JhiMainComponent]
})
export class HrAppAppModule {
}
