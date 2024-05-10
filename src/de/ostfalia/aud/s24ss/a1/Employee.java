package de.ostfalia.aud.s24ss.a1;

import java.time.LocalDate;
import java.util.Objects;

import de.ostfalia.aud.s24ss.base.Department;
import de.ostfalia.aud.s24ss.base.Gender;
import de.ostfalia.aud.s24ss.base.IEmployee;

/**
 * @author ole
 * @since 06.03.2024
 */
public class Employee implements IEmployee {

	private int key;
	private String lastName;
	private String firstName;
	private Gender gender;
	private LocalDate birthdate;
	private LocalDate hiredate;
	private Department department;

	public Employee(String data) {

		String[] dataArr = data.split(";");

		this.key = Integer.parseInt(dataArr[0]);
		this.birthdate = LocalDate.parse(dataArr[1]);
		this.firstName = dataArr[2];
		this.lastName = dataArr[3];
		this.gender = Gender.valueOf(dataArr[4]); // findet den passenden Enum Wert zum gegebenen String
		this.department = Department.getDepartment(dataArr[6]); // siehe kommentar oben
		this.hiredate = LocalDate.parse(dataArr[5]); // findet das passende Datum zum geparsten String
	}

	@Override
	public int compareTo(IEmployee o) {

		if (this.key > o.getKey()) {

			return 1;
		} else if (this.key < o.getKey()) {

			return -1;
		}

		return 0;
	}

	@Override
	public int getKey() {

		return this.key;
	}

	@Override
	public String getName() {

		return this.lastName;
	}

	@Override
	public String getFirstName() {

		return this.firstName;
	}

	@Override
	public Gender getGender() {

		return this.gender;
	}

	@Override
	public LocalDate getBirthdate() {

		return this.birthdate;
	}

	@Override
	public LocalDate getHiredate() {

		return this.hiredate;
	}

	@Override
	public Department getDepartment() {

		return this.department;
	}

	@Override
	public int hashCode() {

		return Objects.hash(birthdate, department, firstName, gender, hiredate, key, lastName);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {

			return true;
		}

		if (obj == null) {

			return false;
		}

		if (getClass() != obj.getClass()) {

			return false;
		}

		Employee other = (Employee) obj;
		return Objects.equals(this.birthdate, other.birthdate) && this.department == other.department
				&& Objects.equals(this.firstName, other.firstName) && this.gender == other.gender
				&& Objects.equals(this.hiredate, other.hiredate) && this.key == other.key
				&& Objects.equals(this.lastName, other.lastName);
	}

	@Override
	public String toString() {

		return this.key + ";" + this.birthdate + ";" + this.firstName + ";" + this.lastName + ";" + this.gender + ";"
				+ this.hiredate + ";" + this.department;
	}

}
