package org.xmolecules.ide.intellij;

public class Concept implements Comparable<Concept> {
	private final String name, plural, defaultColorName;

	Concept(final String name, final String plural, final String defaultColorName) {
		this.name = name;
		this.plural = plural;
		this.defaultColorName = defaultColorName;
	}

	public String getName() {
		return name;
	}

	public String getPlural() {
		return plural;
	}

	public String getDefaultColorName() {
		return defaultColorName;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(final Concept o) {
		return getName().compareTo(o.getName());
	} //TODO Compare by instance
}
