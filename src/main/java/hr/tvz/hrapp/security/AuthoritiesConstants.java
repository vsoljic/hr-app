package hr.tvz.hrapp.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String EVALUATOR = "ROLE_EVALUATOR";

    public static final String EVALUATEE = "ROLE_EVALUATEE";

    private AuthoritiesConstants() {
    }
}
