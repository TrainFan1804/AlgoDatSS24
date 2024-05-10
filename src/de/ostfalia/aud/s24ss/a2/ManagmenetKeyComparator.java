package de.ostfalia.aud.s24ss.a2;

import java.util.Comparator;

import de.ostfalia.aud.s24ss.base.IEmployee;

/**
 * @author ole
 * @since 13.03.2024
 */
public class ManagmenetKeyComparator implements Comparator<IEmployee>{

	@Override
	public int compare(IEmployee o1, IEmployee o2) {
		
		return o1.getKey() - o2.getKey();
	}

}
