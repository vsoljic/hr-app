import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {JhiLanguageService} from 'ng-jhipster';
import {JhiLanguageHelper} from 'app/core';
import {HrAppSharedModule} from 'app/shared';
import {
    adminState,
    AuditsComponent,
    JhiConfigurationComponent,
    JhiDocsComponent,
    JhiHealthCheckComponent,
    JhiHealthModalComponent,
    JhiMetricsMonitoringComponent,
    JhiMetricsMonitoringModalComponent,
    LogsComponent,
    UserMgmtComponent,
    UserMgmtDeleteDialogComponent,
    UserMgmtDetailComponent,
    UserMgmtUpdateComponent
} from './';
import {EstimationComponent} from 'app/admin/estimation/estimation.component';
import {CreateRelationshipRolesModalComponent} from 'app/admin/create-relationship-roles-modal/create-relationship-roles-modal.component';
import {EstimationsOverviewComponent} from './estimations-overview/estimations-overview.component';
import {EstimationService} from 'app/admin/estimation/estimation.service';
import {NgbDateParserFormatter} from '@ng-bootstrap/ng-bootstrap';
import {NgbDateCustomParserFormatter} from 'app/shared/ngb-date-custom-parser-formatter';
import {RelationshipsForEstimationComponent} from 'app/admin/relationships-for-estimation/relationships-for-estimation.component';
import {RelationshipsForEstimationService} from 'app/admin/relationships-for-estimation/relationships-for-estimation.service';
import {EstimationsOverviewSelectedComponent} from 'app/admin/estimations-overview-selected/estimations-overview-selected.component';
import {EstimationsOverviewService} from 'app/admin/estimations-overview/estimations-overview.service';
import {EstimationEditComponent} from './estimation-edit/estimation-edit.component';
import {EstimationsOverviewSelectedDeleteModalComponent} from 'app/admin/estimations-overview-selected-delete-modal/estimations-overview-selected-delete-modal.component';
import {EstimationsOverviewSelectedAddRelModalComponent} from 'app/admin/estimations-overview-selected-add-rel-modal/estimations-overview-selected-add-rel-modal.component';
import { CreateRelationshipAddEvaluatorModalComponent } from './create-relationship-add-evaluator-modal/create-relationship-add-evaluator-modal.component';

/* jhipster-needle-add-admin-module-import - JHipster will add admin modules imports here */

@NgModule({
    imports: [
        HrAppSharedModule,
        RouterModule.forChild(adminState)
        /* jhipster-needle-add-admin-module - JHipster will add admin modules here */
    ],
    declarations: [
        AuditsComponent,
        UserMgmtComponent,
        UserMgmtDetailComponent,
        UserMgmtUpdateComponent,
        UserMgmtDeleteDialogComponent,
        LogsComponent,
        JhiConfigurationComponent,
        JhiHealthCheckComponent,
        JhiHealthModalComponent,
        JhiDocsComponent,
        JhiMetricsMonitoringComponent,
        JhiMetricsMonitoringModalComponent,
        EstimationComponent,
        RelationshipsForEstimationComponent,
        CreateRelationshipRolesModalComponent,
        EstimationsOverviewComponent,
        EstimationsOverviewSelectedComponent,
        EstimationEditComponent,
        EstimationsOverviewSelectedDeleteModalComponent,
        EstimationsOverviewSelectedAddRelModalComponent,
        CreateRelationshipAddEvaluatorModalComponent
    ],
    providers: [EstimationService, RelationshipsForEstimationService, EstimationsOverviewService,
        {
            provide: NgbDateParserFormatter,
            useClass: NgbDateCustomParserFormatter
        }],
    entryComponents: [UserMgmtDeleteDialogComponent, JhiHealthModalComponent, JhiMetricsMonitoringModalComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HrAppAdminModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
