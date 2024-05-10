package de.ostfalia.aud.s24ss.a5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import de.ostfalia.aud.s24ss.a1.Employee;
import de.ostfalia.aud.s24ss.base.Department;
import de.ostfalia.aud.s24ss.base.IEmployee;
import de.ostfalia.aud.s24ss.base.IManagement;

public class ManagementHashMap implements IManagement {

	private HashMap<Integer, IEmployee> dataMap;
	
	public ManagementHashMap() {
		
		this.dataMap = new HashMap<Integer, IEmployee>();
	}
	
	public ManagementHashMap(String fileName) throws IOException {

		this();
		
		// datei lesen
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		
		reader.readLine(); // skip erste Zeile
		String line = reader.readLine();
		
		while (line != null) {

			Employee temp = new Employee(line);
			
			this.dataMap.put(temp.getKey(), temp);
			line = reader.readLine();
		}
		
		reader.close();
	}

	// hier werden einzelne employee gelesen
	public ManagementHashMap(String[] data) {

		this();

		for (String d : data) {
		
			Employee temp = new Employee(d);
			this.dataMap.put(temp.getKey(), temp);
		}
	}
	
	@Override
	public int size() {

		return this.dataMap.size();
	}

	@Override
	public void insert(IEmployee member) {
		
		this.dataMap.put(member.getKey(), member);
	}

	@Override
	public boolean delete(int key) {

		if (this.dataMap.containsKey(key)) {

			this.dataMap.remove(key);
			return true;
		}

		return false;
	}

	@Override
	public IEmployee search(int key) {

		return this.dataMap.get(key);
	}

	@Override
	public IEmployee[] search(String name, String firstName) {

		ArrayList<IEmployee> temp = new ArrayList<>();

		for (IEmployee e : this.dataMap.values()) {

			if (e.getName().equals(name) && e.getFirstName().equals(firstName)) {

				temp.add(e);
			}
		}

		return temp.toArray(new IEmployee[0]);
	}

	@Override
	public int size(Department department) {

		return this.members(department).length;
	}

	@Override
	public IEmployee[] members(Department department) {

		ArrayList<IEmployee> temp = new ArrayList<>();

		for (IEmployee e : this.dataMap.values()) {

			if (e.getDepartment().equals(department)) {

				temp.add(e);
			}
		}

		return temp.toArray(new IEmployee[0]);
	}

	@Override
	public IEmployee[] toArray() {

		return 	this.dataMap.values().toArray(new IEmployee[0]);
	}

}
