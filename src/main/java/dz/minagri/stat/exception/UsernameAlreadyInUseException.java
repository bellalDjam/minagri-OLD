/**
 * 
 */
package dz.minagri.stat.exception;

/**
 * @author bellal djamel
 *
 */
public class UsernameAlreadyInUseException extends Exception {

	private static final long serialVersionUID = 6999557331381577491L;

	public UsernameAlreadyInUseException(String username) {
		super("The username '" + username + "' is already in use.");
	}
}
