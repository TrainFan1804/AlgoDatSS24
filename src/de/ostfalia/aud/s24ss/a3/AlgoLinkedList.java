package de.ostfalia.aud.s24ss.a3;

import java.util.Iterator;
import de.ostfalia.aud.s24ss.a2.AlgoArrayList;
import de.ostfalia.aud.s24ss.base.Department;
import de.ostfalia.aud.s24ss.base.IEmployee;

public class AlgoLinkedList implements Iterable<IEmployee> {

	private class Node {
	
		private IEmployee data;
		private Node nextNode;
		
		Node(IEmployee data) {
			
			this.data = data;
		}

		public IEmployee getData() {
			
			return this.data;
		}

		public Node getNextNode() {
			
			return this.nextNode;
		}

		public void setNextNode(Node nextNode) {
			
			this.nextNode = nextNode;
		}
		
	}
	
	private Node head;
	private Node tail;
	private int lenght;
	
	
	public AlgoLinkedList() {
		
		this.lenght = 0;
	}
	
	public int size() {
		
		return this.lenght;
	}
	
	public void insert(IEmployee employee) {
		
		Node newNode = new Node(employee);
		this.lenght++;
		
		if (head == null) {
			
			this.head = newNode;
			this.tail = this.head;
			return;
		}
		
		this.tail.setNextNode(newNode);
		this.tail = newNode;
	}
	
	public boolean delete(int key) {
		
		if (this.head == null) {
			
			return false;
		}
		
		if (this.head.getData().getKey() == key) {
			
			this.head = this.head.getNextNode();
			this.lenght--;
			return true;
		}
		
		Node current = this.head;
		Node beforeCurrent = null;
		
		while(current != null) {
			
			if (current.getData().getKey() == key) {
				
				beforeCurrent.setNextNode(current.getNextNode());
				this.lenght--;

				if (current == this.tail) {
					
					this.tail = beforeCurrent;
					this.tail.setNextNode(null);
				}
				
				return true;
			}
			
			beforeCurrent = current;
			current = current.getNextNode();
		}
		
		return false;
	}
	
	public IEmployee searchKey(int key) {
		
		Node current = this.head;
		
		while(current != null) {
			
			if (current.getData().getKey() == key) {
				
				return current.getData();
			}
			
			current = current.getNextNode();
		}
		
		return null;
	}
	
	public IEmployee[] searchName(String name, String firstName) {
		
		Node current = this.head;
		AlgoArrayList temp = new AlgoArrayList();
		
		while(current != null) {
			
			if (current.getData().getName().equals(name) && current.getData().getFirstName().equals(firstName)) {
				
				temp.add(current.getData());
			}
			
			if (current.getNextNode() == null) {
				
				return temp.toArray();
			}
			
			current = current.getNextNode();
		}
		
		return temp.toArray();
	}

	public int departmentSize(Department department) {

		int count = 0;
		Iterator<IEmployee> iter = this.iterator();

		while(iter.hasNext()) {

			IEmployee current = iter.next();

			if (current.getDepartment().equals(department)) {

				count++;
			}
		}

		return count;
	}
	
	public IEmployee[] memberSize(Department department) {
		
		Iterator<IEmployee> iter = this.iterator();
		AlgoArrayList temp = new AlgoArrayList();

		while(iter.hasNext()) {

			IEmployee current = iter.next();

			if (current.getDepartment().equals(department)) {

				temp.add(current);;
			}
		}

		return temp.toArray();
	}

	public Iterator<IEmployee> iterator() {

		return new Iterator<IEmployee>() {

			private Node current = head;

            @Override
            public boolean hasNext() {

                return this.current != null;
            }

            @Override
            public IEmployee next() {

				IEmployee temp = this.current.getData();
				this.current = this.current.getNextNode();
                return temp;
            }
		};
	}
	
	public IEmployee[] toArray() {
		
		Node current = this.head;
		AlgoArrayList temp = new AlgoArrayList();
		
		while(current != null) {
			
			temp.add(current.getData());
			current = current.getNextNode();
		}
		
		return temp.toArray();
	}

}
