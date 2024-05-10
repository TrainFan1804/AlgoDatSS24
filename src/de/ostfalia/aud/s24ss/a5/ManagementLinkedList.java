package de.ostfalia.aud.s24ss.a5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import de.ostfalia.aud.s24ss.a1.Employee;
import de.ostfalia.aud.s24ss.base.Department;
import de.ostfalia.aud.s24ss.base.IEmployee;
import de.ostfalia.aud.s24ss.base.IManagement;

public class ManagementLinkedList implements IManagement {

	private LinkedList<IEmployee> dataList;
	
	public ManagementLinkedList() {
		
		this.dataList = new LinkedList<IEmployee>();
	}
	
	public ManagementLinkedList(String fileName) throws IOException {

		this();
		
		// datei lesen
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		
		reader.readLine(); // skip erste Zeile
		String line = reader.readLine();
		
		while (line != null) {
			
			this.dataList.add(new Employee(line));
			line = reader.readLine();
		}
		
		reader.close();
	}

	// hier werden einzelne employee gelesen
	public ManagementLinkedList(String[] data) {

		this();

		for (String d : data) {
		
			this.dataList.add(new Employee(d));
		}
	}

	@Override
	public int size() {
		
		return this.dataList.size();
	}

	@Override
	public void insert(IEmployee member) {
		
		this.dataList.add(member);
	}

	@Override
	public boolean delete(int key) {
		
		for (IEmployee e : this.dataList) {
			
			if (e.getKey() == key) {
				
				return this.dataList.remove(e);
			}
		}

		return false;
	}

	@Override
	public IEmployee search(int key) {

		for (IEmployee e : this.dataList) {
			
			if (e.getKey() == key) {
				
				return e;
			}
		}

		return null;
	}

	@Override
	public IEmployee[] search(String name, String firstName) {

		LinkedList<IEmployee> temp = new LinkedList<>();
		
        for (IEmployee e : this.dataList) {
        	
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

		LinkedList<IEmployee> temp = new LinkedList<>();
		
		Iterator<IEmployee> iter = this.dataList.iterator();
		
		while(iter.hasNext()) {
			
			IEmployee current = iter.next();
			if(current.getDepartment().equals(department)) {
				
				temp.add(current);
			}
		}
        
        return temp.toArray(new IEmployee[0]);
	}

	@Override
	public IEmployee[] toArray() {
		
		// speicher alle elemente in das neue array
		return this.dataList.toArray(new IEmployee[0]);
	}
	
}
