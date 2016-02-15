package com.excilys.computerdatabase.model;

/**
 * @author Vincent Galloy
 *         The Enum Role.
 */
public enum Role {
    NONE(null),
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    SUPER_ADMIN("ROLE_SUPER_ADMIN");
    private final String value;

    Role(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    /**
     * Builds the role from a String
     *
     * @return the role
     */
    public static Role build(String roleAsAString) {
        if (roleAsAString == null) {
            return NONE;
        }
        for (Role role : values()) {
            if (roleAsAString.equals(role.toString())) {
                return role;
            }
        }
        return NONE;
    }
}
