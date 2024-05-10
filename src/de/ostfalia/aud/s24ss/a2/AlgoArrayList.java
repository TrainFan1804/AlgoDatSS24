package de.ostfalia.aud.s24ss.a2;

import java.util.Iterator;

import de.ostfalia.aud.s24ss.a1.Employee;
import de.ostfalia.aud.s24ss.base.Department;
import de.ostfalia.aud.s24ss.base.IEmployee;

/**
 * @author ole
 * @since 12.03.2024
 */
public class AlgoArrayList {

	private static final int START_GROESSE = 8;
	
	private int currentSize;
	private IEmployee[] employeeData;
	
	public AlgoArrayList() {
		
		this.employeeData = new Employee[START_GROESSE];
	}
	
	public void add(IEmployee newEmployee) {
		
		if (this.currentSize == this.employeeData.length) {
			
			IEmployee[] temp = new Employee[this.employeeData.length + 1];
			
			for (int i = 0; i < this.currentSize; i++) {
				
				temp[i] = this.employeeData[i];
			}

			this.employeeData = temp;
		}
		
		this.employeeData[this.currentSize] = newEmployee;
		this.currentSize++;
	}

	public IEmployee getEmployeeAtIndex(int index) {
		
		return this.employeeData[index];
	}
	
	public int size() {
		
		return this.currentSize;
	}

	// ****** Das muss Jean erklären ****** //
	
	public void sortWithKey(ManagmenetKeyComparator comparator) {
		
		this.orderEmployeesByKey();
    }
	
	private void orderEmployeesByKey() {
		
	    // Schritt 1: Schlüssel für alle Mitarbeiter sammeln
	    int[] keys = new int[this.currentSize];
	    
	    for (int i = 0; i < this.currentSize; i++) {
	        
	    	if (this.employeeData[i] != null) {
	    		
	            keys[i] = this.employeeData[i].getKey();
	        }
	    }
	    
	    // Schritt 2: Merge-Sort auf den Schlüsseln anwenden
	    keys = mergeSort(keys, this.currentSize);
	    
	    // Schritt 3: Mitarbeiter gemäß den sortierten Schlüsseln anordnen
	    IEmployee[] sortedEmployees = new IEmployee[this.currentSize];
	    for (int i = 0; i < this.currentSize; i++) { // Iteration über die sortierten Schlüssel
	        
	    	for (int j = 0; j < this.currentSize; j++) { // Iteration über die Mitarbeiter
	            
	    		if (this.employeeData[j] != null && this.employeeData[j].getKey() == keys[i]) { // Prüfen, 
	    																						// ob der Mitarbeiter dem Schlüssel entspricht
	                
	    			// Mitarbeiter in das sortierte Array einfügen
	                for (int k = 0; k < this.currentSize; k++) { // Iteration über das sortierte Array
	                    
	                	if (sortedEmployees[k] == null) { // Suche nach der ersten leeren Position im sortierten Array
	                        
	                		sortedEmployees[k] = this.employeeData[j];
	                        break;
	                    }
	                }
	            }
	        }
	    }
	    
	    // Das ursprüngliche Mitarbeiter-Array durch das sortierte Array ersetzen
	    this.employeeData = sortedEmployees;
	}

	private int[] mergeSort(int[] array, int length) {
	    
		if (length < 2) {
	        
	    	return array; // Basisfall: Wenn das Array leer oder nur ein Element hat, ist es bereits sortiert
	    }
	    
	    // Array in zwei Hälften teilen
	    int mid = length / 2;
	    int[] leftArray = new int[mid];
	    int[] rightArray = new int[length - mid];
	    
	    for (int i = 0; i < mid; i++) {
	        
	    	leftArray[i] = array[i];
	    }
	    for (int i = mid; i < length; i++) {
	        
	    	rightArray[i - mid] = array[i];
	    }
	    
	    // Rekursiv beide Hälften sortieren
	    this.mergeSort(leftArray, mid);
	    this.mergeSort(rightArray, length - mid);
	    
	    // Sortierte Hälften zusammenführen
	    this.merge(array, leftArray, rightArray, mid, length - mid);
	    
	    return array;
	}

	private void merge(int[] array, int[] leftArray, int[] rightArray, int leftLength, int rightLength) {
	    
		int i = 0, j = 0, k = 0; // Indizes für leftArray, rightArray und das ursprüngliche Array
	    while (i < leftLength && j < rightLength) {
	        
	    	// Elemente vergleichen und in das ursprüngliche Array einfügen
	        if (leftArray[i] <= rightArray[j]) {
	            
	        	array[k++] = leftArray[i++];
	        } else {
	            
	        	array[k++] = rightArray[j++];
	        }
	    }
	    
	    // Restliche Elemente einfügen, falls vorhanden
	    while (i < leftLength) {
	        
	    	array[k++] = leftArray[i++];
	    }
	    while (j < rightLength) {
	        
	    	array[k++] = rightArray[j++];
	    }
	}
	
	// ***** Bis hier hin **** //
	
	// in merge sort
	private void sortWithName(ManagementNameComparator comparator) {
		
		this.mergeSort(this.employeeData);
	}
	
	private IEmployee[] mergeSort(IEmployee[] a) { //n ist länge von a.
		int n = a.length;
		if (n < 2) {return a;} //Wenn in a ein oder kein Elemente vorhanden, Rekursion beenden.
	    int mid = n/2; //Durchschnitt
	    IEmployee[] l = new IEmployee[mid]; //Linke Hälfte
	    IEmployee[] r = new IEmployee[n - mid]; //Rechte Hälfte
	    for (int i=0; i<mid; i++) {l[i] = a[i];} //Linke Hälfte füllen.
	    for (int i=mid; i<n; i++) {r[i - mid] = a[i];} //Rechte Hälfte füllen.
	    mergeSort(l); //Prozess wiederholen mit linker Hälfte.
	    mergeSort(r); //Prozess wiederholen mit rechter Hälfte.
	    merge(a,l,r); //a Array sortiert aus l und r zusammenfügen.
		return a;
	}
	
	private void merge(IEmployee[] a, IEmployee[] l, IEmployee[] r) {
		
		ManagementNameComparator comparator = new ManagementNameComparator();
		int left = l.length;
		int right = r.length;
		int i = 0, j = 0, k = 0; //i für l, j für r, k für a.
		while (i<left && j<right) { //r und l vergleichen, dann größeren in a.
			if (comparator.compare(l[i],r[j])>=0) {a[k++] = l[i++];}
			else {a[k++] = r[j++];}
		}
		//Wenn r oder l nicht gleiche Anzahl an Elementen:
		while (i<left) {a[k++] = l[i++];} //Restliche l
		while (j<right) {a[k++] = r[j++];} //Restliche r
	}
	
	// der findet den ersten, aber testSearchName sagt trotzdem falsch
	public IEmployee searchWithKey(int key) {
		
		this.sortWithKey(new ManagmenetKeyComparator());
		int left = 0, right = this.currentSize - 1;
		IEmployee result = null;
		
		while (left <= right) {
			
			int mid = left + ((right - left) / 2);
 
			IEmployee current = this.employeeData[mid];
			
			if (current.getKey() == key) {
				
				result = current;
				right = mid - 1;
			} else if (current.getKey() < key) {
				
				left = mid + 1;
			} else {
				
				right = mid - 1;
			}	
		}
		
		return result;
	}
	
	public IEmployee[] searchWithName(String name, String firstName) {
		
		this.sortWithName(new ManagementNameComparator());
		AlgoArrayList e = new AlgoArrayList();
		
		int left = 0, right = this.currentSize - 1;
		
		while (left <= right) {
			
			int mid = left + ((right - left) / 2);
 
			IEmployee current = this.employeeData[mid];
			
			if (current.getName().equals(name) && current.getFirstName().equals(firstName)) {
				
				e.add(current);
				right = mid - 1;
			} else {
				
	            // Ansonsten passen wir die 
				// Suche je nach Vergleich an
	            if (current.getName().compareTo(name) < 0 || (current.getName().equals(name) && current.getFirstName().compareTo(firstName) < 0)) {
	                left = mid + 1; // Suchen Sie in der rechten Hälfte weiter
	            } else {
	                right = mid - 1; // Suchen Sie in der linken Hälfte weiter
	            }
			}
		}
		
		return e.toArray();
	}
	
	public IEmployee[] toArray() {
		
		IEmployee[] e = new IEmployee[this.currentSize];
		
		for (int i = 0; i < this.currentSize; i++) {
			
			e[i] = this.getEmployeeAtIndex(i);
		}
		
		return e;
	}
	
	public int departmentSize(Department department) {
		
		Iterator<IEmployee> iter = this.iterator();
		
		int size = 0;
		while(iter.hasNext()) {
			
			if (iter.next().getDepartment().equals(department)) {
				
				size++;
			}
		}

		return size;
	}
	
	public IEmployee[] memberInDepartment(Department department) {
		
		Iterator<IEmployee> iter = this.iterator();
		
		AlgoArrayList e = new AlgoArrayList();
		
		while (iter.hasNext()) {
			
			IEmployee current = iter.next();
			if (current.getDepartment().equals(department)) {
				
				e.add(current);
			}
		}
		
		return e.toArray();
	}
	
	public boolean delete(int key) {
		
		int index = this.searchIndexWithKey(key);
		
		if (index == -1) {
			
			return false;
		}
		
		for (int i = index; i < this.currentSize - 1; i++) {
			
			this.employeeData[i] = this.employeeData[i + 1];
		}
		
		this.currentSize--;
		return true;
	}
	
	private int searchIndexWithKey(int key) {
		
		int left = 0, right = this.currentSize - 1;
		int result = -1;
		
		while (left <= right) {
			
			int mid = left + ((right - left) / 2);
 
			IEmployee current = this.employeeData[mid];
			
			if (current.getKey() == key) {
				
				result = mid;
				right = mid - 1;
			} else if (current.getKey() < key) {
				
				left = mid + 1;
			} else {
				
				right = mid - 1;
			}	
		}
		
		return result;
	}
	
	private Iterator<IEmployee> iterator() {
		
		return new Iterator<IEmployee>() {

			private int pos = 0;
			
			@Override
			public boolean hasNext() {
				
				return pos < currentSize; // && employeeData[pos + 1] != null;
			}

			@Override
			public IEmployee next() {
				
				if (!hasNext() ) {
					
					return null;
				}
				
				return employeeData[pos++];
			}
		};
	}
	
}
