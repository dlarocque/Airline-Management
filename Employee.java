/*
Employee.java

COMP 1020 Section A03
INSTRUCTOR      Bryan Wodi
ASSIGNMENT      Assignment 3
AUTHOR          Daniel La Rocque
VERSION         March 20, 2020

PURPOSE         Operate an airline
*/

public class Employee extends Person {

	private int empID;
	private static int amountOfEmployees = 0;
	private String job;

	public Employee(String firstName, String lastName, String job, int id, double weight) { // Employee constructor
		// Assign given values to all of the instance variables and set the employee id
		super.firstName = firstName;
		super.lastName = lastName;
		this.job = job;
		super.id = id;
		super.payloadWeight = weight;
		empID = 500000000 + amountOfEmployees++;
	}

	public boolean hasJob(String isItThisJob) {
		return job.equals(isItThisJob);
	}

	public String toString() {
		String data = "ID: " + id + ", Weight: ";
		String weight = Integer.toString((int) payloadWeight);
		for (int i = 0; i < 4 - weight.length(); i++) {
			data += " ";
		}
		data += weight + " kg, Name: " + firstName + ", EMP: " + empID + ", Job: " + job;
		return data;
		// toString method puts all the data of the payload into a readable string to be
		// printed
	}

}
