package de.ostfalia.aud.s24ss.a4;

import java.util.Iterator;

import de.ostfalia.aud.s24ss.a1.Employee;
import de.ostfalia.aud.s24ss.a2.AlgoArrayList;
import de.ostfalia.aud.s24ss.base.Department;
import de.ostfalia.aud.s24ss.base.IEmployee;

// TODO
public class AlgoHashMap {

    private static final int DEFAULT_INITIAL_CAPACITY = 14;
    private static final double DEFAULT_LOAD_FACTOR = 0.8;

    private int length;
    private IEmployee[] dataBucket;

    public AlgoHashMap() {

        this(DEFAULT_INITIAL_CAPACITY);
    }

    public AlgoHashMap(int initialCapacity) {

        this.length = 0;
        this.dataBucket = new IEmployee[initialCapacity];
    }

    public int size() {

        return this.length;
    }

    public void insert(IEmployee employee) {
        
        if ((double) (this.length + 1) / this.dataBucket.length > DEFAULT_LOAD_FACTOR) {

            this.resizeMap(2);
        }

        int keyIndex = this.findHashIndex(employee.getKey());

        while(this.dataBucket[keyIndex] != null) {

            keyIndex = (keyIndex + 1) % this.dataBucket.length;
        } 

        this.dataBucket[keyIndex] = employee;
        this.length++;
    }

    private void resizeMap(int factor) {

        int newCap = this.dataBucket.length * factor;
        IEmployee[] tempNew = new IEmployee[newCap];

        for (IEmployee e : this.dataBucket) {

            if (e != null) {

                int newIndex = (Integer.hashCode(e.getKey() & Integer.MAX_VALUE) % newCap);
                while(tempNew[newIndex] != null) {

                    newIndex = (newIndex + 1) % tempNew.length;
                }

                tempNew[newIndex] = e;
            }
        }

        this.dataBucket = tempNew;
    }

    private int findHashIndex(int key) {

        return (Integer.hashCode(key) & Integer.MAX_VALUE) % this.dataBucket.length;
    }

    public boolean delete(int key) {

        int index = this.findHashIndex(key);

        while(this.dataBucket[index] != null) {

            if (this.dataBucket[index].getKey() == key) {

                this.dataBucket[index] = null;
                this.length--;
                /*
                 * factor = 1 weil this.dataBucket gleich lang bleiben soll, aber alle momentan
                 * gespeicherten werte nochmal neu gehashed werden sollen
                 */
                this.resizeMap(1);
                return true;
            }

            index = (index + 1) % this.dataBucket.length;
        }

        return false;
    }

    public IEmployee search(int key) {

        int index = this.findHashIndex(key);

        while(this.dataBucket[index] != null) {

            if (this.dataBucket[index].getKey() == key) {

                return this.dataBucket[index];
            }

            index = (index + 1) % this.dataBucket.length;
        }

        return null;
    }

    public IEmployee[] search(String name, String firstName) {

        AlgoArrayList e = new AlgoArrayList();
        Iterator<IEmployee> iter = this.iterator();

        while(iter.hasNext()) {

            IEmployee current = iter.next();

            if (current != null) {

                if (current.getName().equals(name) && current.getFirstName().equals(firstName)) {

                    e.add(current);
                }
            }
        }

        return e.toArray();
    }

    public int size(Department department) {

        return this.member(department).length;
    }

    public IEmployee[] member(Department department) {

        Iterator<IEmployee> iter = this.iterator();

        AlgoArrayList e = new AlgoArrayList();

        while(iter.hasNext()) {

            IEmployee current = iter.next();

            if (current != null) {

                if (current.getDepartment().equals(department)) {
    
                    e.add(current);
                }
            }
        }

        return e.toArray();
    }

    public IEmployee[] toArray() {

        AlgoArrayList e = new AlgoArrayList();

        for (int i = 0; i < this.dataBucket.length; i++) {

            if (this.dataBucket[i] != null) {

                e.add(this.dataBucket[i]);
            }
        }

        return e.toArray();
    }

    public Iterator<IEmployee> iterator() {
		
		return new Iterator<IEmployee>() {

			private int pos = 0;
			
			@Override
			public boolean hasNext() {
				
				return pos < dataBucket.length;
			}

			@Override
			public IEmployee next() {
				
				if (!hasNext() ) {
					
					return null;
				}
				
				return dataBucket[pos++];
			}
		};
	}

    public static void main(String[] args) {
        
        AlgoHashMap hm = new AlgoHashMap();

        hm.insert(new Employee("10855;1957-08-07;Breannda;Billingsley;F;1991-08-05;Finance"));

        System.out.println(hm.toArray()[0]);
        boolean f = hm.delete(10855);
        System.out.println(f);
    }
    
}
