package hr.tvz.hrapp.domain.estimation_status;

/**
 * @author vedrana.soljic
 */
public enum EstimationStatusEnum {
    IN_PROCESS(1),
    DEFINING_GOALS(2),
    GOALS_OVERVIEW(3),
    GRADING(4),
    GRADE_OVERVIEW(5);

    private final Integer value;

    EstimationStatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
