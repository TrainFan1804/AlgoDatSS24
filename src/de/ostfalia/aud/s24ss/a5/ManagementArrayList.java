package de.ostfalia.aud.s24ss.a5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import de.ostfalia.aud.s24ss.a1.Employee;
import de.ostfalia.aud.s24ss.a2.ManagmenetKeyComparator;
import de.ostfalia.aud.s24ss.base.Department;
import de.ostfalia.aud.s24ss.base.IEmployee;
import de.ostfalia.aud.s24ss.base.IManagement;

public class ManagementArrayList implements IManagement {

	private ArrayList<IEmployee> dataArray;
	
	public ManagementArrayList() {

		this.dataArray = new ArrayList<IEmployee>();
	}

	// hier wird die datei mit den daten gelesen
	public ManagementArrayList(String fileName) throws IOException {

		this();
		
		// datei lesen
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		
		reader.readLine(); // skip erste Zeile
		String line = reader.readLine();
		
		while (line != null) {
			
			System.out.println(line);
			this.dataArray.add(new Employee(line));
			line = reader.readLine();
		}
		
		reader.close();
		// sortier algo aufrufen
		this.dataArray.sort(new ManagmenetKeyComparator());
	}

	// hier werden einzelne employee gelesen
	public ManagementArrayList(String[] data) {

		this();

		for (String d : data) {
		
			this.dataArray.add(new Employee(d));
		}

		// sortier algo aufrufen
		this.dataArray.sort(new ManagmenetKeyComparator());
	}
	
	@Override
	public int size() {

		return this.dataArray.size();
	}

	@Override
	public void insert(IEmployee member) {

		this.dataArray.add(member);
	}
	
	@Override
	public boolean delete(int key) {

		for (int i = 0; i < this.dataArray.size(); i++) {
			
			IEmployee current = this.dataArray.get(i);
			
			if (current.getKey() == key) {
				
				this.dataArray.remove(i);
				return true;
			}
		}
		
		return false;
	}

	@Override
	public IEmployee search(int key) {
		
	    for (IEmployee e : this.dataArray) {
	        
	    	if (e.getKey() == key) {

	        	return e;
	        }
	    }
	    
	    return null;
	}

	@Override
	public IEmployee[] search(String name, String firstName) {
		
		ArrayList<IEmployee> temp = new ArrayList<IEmployee>();
		
	    for (IEmployee e : this.dataArray) {
	        
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

		ArrayList<IEmployee> temp = new ArrayList<IEmployee>();
		
		Iterator<IEmployee> iter = this.dataArray.iterator();
		
		while(iter.hasNext()) {
			
			IEmployee current = iter.next();
			if (current.getDepartment().equals(department)) {
				
				temp.add(current);
			}
		}
		
		return temp.toArray(new IEmployee[0]);
	}

	@Override
	public IEmployee[] toArray() {

		return this.dataArray.toArray(new IEmployee[0]);
	}

}
