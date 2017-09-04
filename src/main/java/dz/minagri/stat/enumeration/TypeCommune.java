/**
 * 
 */
package dz.minagri.stat.enumeration;

/**
 * @author bellal djamel
 *
 */
public enum TypeCommune {
	 COMMUNAL("Comm"), DAIRA("daira"), WILAYA("Wilaya"),SUBDIV("Subdiv");

    private final String name;

    private TypeCommune(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
