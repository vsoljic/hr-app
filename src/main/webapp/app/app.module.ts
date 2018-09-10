import './vendor.ts';

import {Injector, NgModule, LOCALE_ID } from '@angular/core';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {LocalStorageService, Ng2Webstorage, SessionStorageService} from 'ngx-webstorage';
import {JhiEventManager} from 'ng-jhipster';

import {AuthInterceptor} from './blocks/interceptor/auth.interceptor';
import {AuthExpiredInterceptor} from './blocks/interceptor/auth-expired.interceptor';
import {ErrorHandlerInterceptor} from './blocks/interceptor/errorhandler.interceptor';
import {NotificationInterceptor} from './blocks/interceptor/notification.interceptor';
import {HrAppSharedModule} from 'app/shared';
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
import {EvaluateeComponent} from 'app/evaluatee/evaluatee.component';
import {CreateNewGoalModalComponent} from 'app/create-new-goal-modal/create-new-goal-modal.component';
import {EstimationsEvaluatorComponent} from './estimations-evaluator/estimations-evaluator.component';
import {EstimationsEvaluateeComponent} from './estimations-evaluatee/estimations-evaluatee.component';
import {EstimationsEvaluatorService} from 'app/estimations-evaluator/estimations-evaluator.service';
import {EvaluateeService} from 'app/evaluatee/evaluatee.service';
import {NgbDateParserFormatter} from '@ng-bootstrap/ng-bootstrap';
import {NgbDateCustomParserFormatter} from 'app/shared/ngb-date-custom-parser-formatter';
import { EstimationsEvaluatorSelectEvaluateeModalComponent } from './estimations-evaluator-select-evaluatee-modal/estimations-evaluator-select-evaluatee-modal.component';
import { EditGoalModalComponent } from './edit-goal-modal/edit-goal-modal.component';

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
        HrAppEntityModule
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        ActiveMenuDirective,
        FooterComponent,
        GoalGroupComponent,
        EvaluateeComponent,
        CreateNewGoalModalComponent,
        EstimationsEvaluatorComponent,
        EstimationsEvaluateeComponent,
        EstimationsEvaluatorSelectEvaluateeModalComponent,
        EditGoalModalComponent
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
        },
        EstimationsEvaluatorService, EvaluateeService,
        {provide: NgbDateParserFormatter, useClass: NgbDateCustomParserFormatter}
    ],
    bootstrap: [JhiMainComponent]
})
export class HrAppAppModule {
}
