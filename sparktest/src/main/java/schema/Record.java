package schema;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Map;


/**
 * JavaBean for a veterinary record.
 */
public final class Record implements Serializable {
	private String _name;
	private long _numPets;
	private boolean _paidInFull;
	private Map<String, String> _preferences;
	private Date _registeredOn;
	private List<Date> _visits;

	/**
	 * Convenience constructor
	 */
	public Record(String name, long numPets, boolean paidInFull, Map<String, String> preferences, Date registeredOn,
			List<Date> visits) {
		setName(name);
		setNumPets(numPets);
		setPaidInFull(paidInFull);
		setPreferences(preferences);
		setRegisteredOn(registeredOn);
		setVisits(visits);
	}

	/**
	 * Accessor for the name field
	 */
	public String getName() {
		return (_name);
	}

	/**
	 * Accessor for the name field
	 */
	public void setName(String name) {
		_name = name;
	}

	/**
	 * Accessor for the numPets field
	 */
	public long getNumPets() {
		return (_numPets);
	}

	/**
	 * Accessor for the numPets field
	 */
	public void setNumPets(long numPets) {
		_numPets = numPets;
	}

	/**
	 * Accessor for the paidInFull field
	 */
	public boolean getPaidInFull() {
		return (_paidInFull);
	}

	/**
	 * Accessor for the paidInFull field
	 */
	public void setPaidInFull(boolean paidInFull) {
		_paidInFull = paidInFull;
	}

	/**
	 * Accessor for the preferences field
	 */
	public Map<String, String> getPreferences() {
		return (_preferences);
	}

	/**
	 * Accessor for the preferences field
	 */
	public void setPreferences(Map<String, String> preferences) {
		_preferences = preferences;
	}

	/**
	 * Accessor for the registeredOn field
	 */
	public Date getRegisteredOn() {
		return (_registeredOn);
	}

	/**
	 * Accessor for the registeredOn field
	 */
	public void setRegisteredOn(Date registeredOn) {
		_registeredOn = registeredOn;
	}

	/**
	 * Accessor for the visits field
	 */
	public List<Date> getVisits() {
		return (_visits);
	}

	/**
	 * Accessor for the visits field
	 */
	public void setVisits(List<Date> visits) {
		_visits = visits;
	}
}
