import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;


/**
* File: Lab11Prob01.java
* Class: CSCI 1302
* Author: Anthony Martinez, Maya Witry
* Created on: Apr 24, 2026
* Last Modified: Apr 24, 2026
* Description: Used person class to populate ArrayList, sort and write person to binary file
*/

public class Lab11Prob02 {

	public static void main(String[] args) {
		int age, zipCode;
		String name, address;
		double salary;

		ArrayList<Person> personList = new ArrayList<>();

		try ( // Create an input stream for file pricelist.dat

				DataInputStream input = new DataInputStream(new FileInputStream("src/people.dat"));

		) {
			while (true) {
				age = input.readInt();
				name = input.readUTF();
				address = input.readUTF();
				zipCode = input.readInt();
				salary = input.readDouble();

				personList.add(new Person(age, name, address, zipCode, salary));

			}
		} catch (EOFException ex) {

		} catch (Exception ex) {
			System.out.println("Error");
		}
		
		java.util.Collections.sort(personList);
		
		try ( // Create an input stream for file pricelist.dat

				DataOutputStream output = new DataOutputStream(new FileOutputStream("src/people-salary-sorted.dat"));) {

			for (Person p : personList) {
				System.out.println(p);
				output.writeUTF(p.toString());
			}
		} catch (Exception ex) {
			System.out.println("Error");
		}

	}

}

class Person implements Comparable<Person> {

	private int age, zipCode;
	private String name, address;
	private double salary;

	public Person() {
		setAge(0);
		setZipCode(12345);
		setName("John Doe");
		setAddress("123 Oak St");
		setSalary(0);

	}

	public Person(int age, String name, String address, int zipCode, double salary) {
		setAge(age);
		setZipCode(zipCode);
		setName(name);
		setAddress(address);
		setSalary(salary);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public int compareTo(Person person) {
		if (getSalary() > person.getSalary()) {
			return -1;
		} else if (getSalary() < person.getSalary()) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public String toString() {
		return String.format("%d %s %s %d $%,.2f%n", getAge(), getName(), getAddress(), getZipCode(), getSalary());
	}

}
