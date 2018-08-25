import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'jhi-estimations-overview',
    templateUrl: './estimations-overview.component.html',
    styleUrls: ['./estimations-overview.scss']
})
export class EstimationsOverviewComponent implements OnInit {
    evaluators = ['Vedrana Š', 'Antun Štaba', 'Pajo Pajić', 'Miha Mihić'];

    constructor() {}

    ngOnInit() {}
}
