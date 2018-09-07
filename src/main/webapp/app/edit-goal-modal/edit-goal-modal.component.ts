import { Component, OnInit } from '@angular/core';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {Router} from '@angular/router';

@Component({
  selector: 'jhi-edit-goal-modal',
  templateUrl: './edit-goal-modal.component.html',
  styles: []
})
export class EditGoalModalComponent implements OnInit {

    closeResult: string;

    constructor(private modalService: NgbModal, private router: Router) {}

    ngOnInit() {}

    openEditGoalModal(content) {
        this.modalService.open(content).result.then(
            result => {
                this.closeResult = `Closed with: ${result}`;
            },
            reason => {
                this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
            }
        );
    }

    private getDismissReason(reason: any): string {
        if (reason === ModalDismissReasons.ESC) {
            return 'by pressing ESC';
        } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
            return 'by clicking on a backdrop';
        } else {
            return `with: ${reason}`;
        }
    }

}
