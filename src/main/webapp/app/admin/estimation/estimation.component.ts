import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap/datepicker/ngb-date-struct';
import { NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'jhi-estimation',
    templateUrl: './estimation.component.html',
    styleUrls: ['./estimation.scss']
})
export class EstimationComponent implements OnInit {
    typeEstimation = [
        'Model A',
        'Model B',
        'Ovo nema smisla',
        'Ne želim više raditi na šalteru',
        'Krivo sam ispunila zahtjev',
        'Sve sam krivo napravila',
        'Zašto je život ovako težak???'
    ];
    dateFrom: NgbDateStruct;
    dateTo: NgbDateStruct;
    dateString: string;

    constructor(private http: HttpClient, private ngbDateParserFormatter: NgbDateParserFormatter) {}

    ngOnInit() {
        this.dateFrom = this.setDefaultDate();
        this.dateTo = this.setDefaultDate();
    }

    /**
     * Returns today's date as a NgbDateStruct model {day, month, year}
     */
    setDefaultDate(): NgbDateStruct {
        return this.convertToNgbDate(new Date()); // new date = today
    }

    convertToNgbDate(date: Date): NgbDateStruct {
        const year = date.getFullYear().toString();
        const month = date.getMonth() + 1;
        const day = date.getDate();

        return this.ngbDateParserFormatter.parse(day + '.' + month.toString() + '.' + year);
    }

    /**
     * Checks if newly selected date is greater than date to and if yes, disallows change.
     * @param date selected date
     */
    onSelectDateFrom(date: NgbDateStruct) {
        if (date != null) {
            // this.dateFrom = date;
            // if selected dateFrom is greater than dateTo just set it to dateTo
            this.dateFrom = this.isDateGreaterThan(date, this.dateTo) ? this.dateTo : date;
            console.log(this.dateFrom);
            this.dateString = this.ngbDateParserFormatter.format(this.dateFrom);
        }
    }

    onSelectDateTo(date: NgbDateStruct) {
        if (date != null) {
            this.dateTo = date;
            this.dateString = this.ngbDateParserFormatter.format(date);
        }
    }

    isDateGreaterThan(date1: NgbDateStruct, date2: NgbDateStruct): boolean {
        // get dates from NgbDateStruct type to javascript type
        const date1JsFormat = new Date(date1.year, date1.month, date1.day);
        const date2JsFormat = new Date(date2.year, date2.month, date2.day);

        console.log('isGreaterThan poziv i rezultat', date1, date2, date1JsFormat > date2JsFormat ? true : false);
        // return true if date1 is greater, else return false
        return date1JsFormat > date2JsFormat ? true : false;
    }
}
