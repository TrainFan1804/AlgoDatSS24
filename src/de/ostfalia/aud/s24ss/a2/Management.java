package de.ostfalia.aud.s24ss.a2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import de.ostfalia.aud.s24ss.a1.Employee;
import de.ostfalia.aud.s24ss.base.Department;
import de.ostfalia.aud.s24ss.base.IEmployee;
import de.ostfalia.aud.s24ss.base.IManagement;

/**
 * @author ole
 * @since 12.03.2024
 */
public class Management implements IManagement {
	
	private AlgoArrayList dataList;
	
	public AlgoArrayList getData() {
		
		return this.dataList;
	}
	
	public Management() {

		this.dataList = new AlgoArrayList();
	}

	// hier wird die datei mit den daten gelesen
	public Management(String fileName) throws IOException {

		this();
		
		// datei lesen
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		
		reader.readLine(); // skip erste Zeile
		String line = reader.readLine();
		
		while (line != null) {
			
			System.out.println(line);
			this.dataList.add(new Employee(line));
			line = reader.readLine();
		}
		
		reader.close();
		// sortier algo aufrufen
		this.dataList.sortWithKey(new ManagmenetKeyComparator());
	}

	// hier werden einzelne employee gelesen
	public Management(String[] data) {

		this();

		for (String d : data) {
		
			this.dataList.add(new Employee(d));
		}

		// sortier algo aufrufen
		this.dataList.sortWithKey(new ManagmenetKeyComparator());
	}

	@Override
	public int size() {
		
		return this.dataList.size();
	}

	@Override
	public void insert(IEmployee member) {
		
		this.dataList.add(member);
//		this.dataList.sortWithKey(new ManagmenetKeyComparator());	// wenn ich das hinzufüge, 
															//laufen die anderen testInsert.. 
															//nicht mehr durhc, aber testInsertSearch 
															//läuft dann
	}

	@Override
	public boolean delete(int key) {
		
		return this.dataList.delete(key);
	}

	@Override
	public IEmployee search(int key) {
		
		return this.dataList.searchWithKey(key);
	}

	@Override
	public IEmployee[] search(String name, String firstName) {
			
		return this.dataList.searchWithName(name, firstName);
	}

	/**
	 * gibt die anzahl der mitarbeiter in dem department zurück
	 * 
	 * @param department Das department das durchgezählt wird
	 */
	@Override
	public int size(Department department) {
		
		return this.dataList.departmentSize(department);
	}

	/**
	 * gibt die mitarbeiter in dem department zurück
	 * 
	 * @param department Das department das durchsucht wird
	 */
	@Override
	public IEmployee[] members(Department department) {
		
		return this.dataList.memberInDepartment(department);
	}

	@Override
	public IEmployee[] toArray() {
		
		return this.dataList.toArray();
	}

}
