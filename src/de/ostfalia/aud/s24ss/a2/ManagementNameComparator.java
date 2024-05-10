package de.ostfalia.aud.s24ss.a2;

import java.util.Comparator;

import de.ostfalia.aud.s24ss.base.IEmployee;

/**
 * @author ole
 * @since 13.03.2024
 */
public class ManagementNameComparator implements Comparator<IEmployee>{

	@Override
	public int compare(IEmployee o1, IEmployee o2) {
		
		int r = o1.getName().compareTo(o2.getName()); // speichert, wie Nachnamen sortiert werden müssen
		
		// Wenn Nachnamen gleich, gebe den Wert der Vornamen zurück, sonst den der Nachnamen
		if (r == 0) {
			
			return o1.getFirstName().compareTo(o2.getFirstName());
		}
		
		return r;
	}
	
}
