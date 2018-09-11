export class Relationship {
    estimationId: number;
    evaluatorId: number;
    evaluateeIdList: number[];

    constructor(estimationId: number, evaluatorId: number, evaluateeIdList: number[]) {
        this.estimationId = estimationId;
        this.evaluatorId = evaluatorId;
        this.evaluateeIdList = evaluateeIdList;
    }
}
