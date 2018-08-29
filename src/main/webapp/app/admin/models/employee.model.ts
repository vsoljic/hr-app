import {User} from 'app/core';

export class Employee {
    id: number;
    user: User;
    employeeCode: number;
    firstName: string;
    lastName: string;
    workPosition: string;

    constructor(id: number, user: User, employeeCode: number, firstName: string, lastName: string, workPosition: string) {
        this.id = id;
        this.user = user;
        this.employeeCode = employeeCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.workPosition = workPosition;
    }
}
