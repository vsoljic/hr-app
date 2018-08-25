import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'jhi-create-relationship-roles-modal',
    templateUrl: './create-relationship-roles-modal.component.html',
    styleUrls: ['./create-relationshio-roles-modal.scss']
})
export class CreateRelationshipRolesModalComponent implements OnInit {
    closeResult: string;

    evaluatees = ['X Y0', 'Antun Štaba', 'Marko Markić', 'Luka Lukić'];

    constructor(private modalService: NgbModal, private router: Router) {}

    ngOnInit() {}

    openModalForRelationshipsAndRoles(content) {
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
