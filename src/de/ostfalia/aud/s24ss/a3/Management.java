package de.ostfalia.aud.s24ss.a3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import de.ostfalia.aud.s24ss.a1.Employee;
import de.ostfalia.aud.s24ss.base.Department;
import de.ostfalia.aud.s24ss.base.IEmployee;
import de.ostfalia.aud.s24ss.base.IManagement;

public class Management implements IManagement{

	private AlgoLinkedList employeedData;
	
	public Management() {
		
		this.employeedData = new AlgoLinkedList();
	}
	
	public Management(String filePath) throws IOException {
		
		this();
		// datei lesen
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
				
		reader.readLine(); // skip erste Zeile
		String line = reader.readLine();
				
		while (line != null) {
					
			this.employeedData.insert(new Employee(line));
			line = reader.readLine();
		}
				
		reader.close();
	}
	
	public Management(String[] data) {
		
		this();
		
		for (String d : data) {
			
			this.employeedData.insert(new Employee(d));
		}
	}
	
	@Override
	public int size() {
		
		return this.employeedData.size();
	}

	@Override
	public void insert(IEmployee member) {
		
		this.employeedData.insert(member);
	}

	@Override
	public boolean delete(int key) {
		
		return this.employeedData.delete(key);
	}

	@Override
	public IEmployee search(int key) {
		
		return this.employeedData.searchKey(key);
	}

	@Override
	public IEmployee[] search(String name, String firstName) {
		
		return this.employeedData.searchName(name, firstName);
	}

	@Override
	public int size(Department department) {
		
		return this.employeedData.departmentSize(department);
	}

	@Override
	public IEmployee[] members(Department department) {
		
		return this.employeedData.memberSize(department);
	}

	@Override
	public IEmployee[] toArray() {
		
		return this.employeedData.toArray();
	}

}
