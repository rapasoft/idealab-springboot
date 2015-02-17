package ch.erni.community.idealab.enums;

/**
 * @author rap
 */
public enum SecurityRoles {

	USER, ADMIN, AUTHOR, RESPONSIBLE;

	/**
	 * Mmm, spring rolls...
	 */
	public String asSpringRole() {
		return "ROLE_" + name();
	}

}
