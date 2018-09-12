import { Injectable } from '@angular/core';

@Injectable()
/**
 *  Application wide provider used to share data across all pages in application. Storage can store any object or array which will then be acessible from
 *  any page in application.
 */
export class DataSharingService {
    public storage: any;

    public second_storage: any;

    public constructor() {}
}
