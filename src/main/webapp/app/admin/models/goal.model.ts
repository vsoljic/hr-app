export class Goal {
    id: number;
    name: string;
    ponder: number;
    target_value: number;
    achievement_percentage: number;
    group_id: number;
    employee_id: number;
    estimation_id: number;

    constructor(
        id: number,
        name: string,
        ponder: number,
        target_value: number,
        achievement_percentage: number,
        group_id: number,
        employee_id: number,
        estimation_id: number
    ) {
        this.id = id;
        this.name = name;
        this.ponder = ponder;
        this.target_value = target_value;
        this.achievement_percentage = achievement_percentage;
        this.group_id = group_id;
        this.employee_id = employee_id;
        this.estimation_id = estimation_id;
    }
}
