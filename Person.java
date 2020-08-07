/*
Person.java

COMP 1020 Section A03
INSTRUCTOR      Bryan Wodi
ASSIGNMENT      Assignment 3
AUTHOR          Daniel La Rocque
VERSION         March 20, 2020

PURPOSE         Operate an airline
*/

public class Person extends Payload {

	protected String firstName; 
	protected String lastName;

	public Person(){}	//Person constructor

	public Person (String firstName, String lastName, int id, double weight) { //Second person constructor
		//Assign all our instance variables to the variables were given in the constructor.
		this.firstName = firstName;	
		this.lastName = lastName;
		super.id = id;
		super.payloadWeight = weight;
		isCargo = false;
	}

	public String toString(){
		String data = "ID: " + id + ", Weight: ";
		String weight = Integer.toString((int)payloadWeight);
		for(int i = 0 ; i < 4-weight.length() ; i++){
			data += " ";
		}
		data +=  weight + " kg, Name: " + firstName;
		return data;
		/*
		This toString method puts all the information a person payload can contain into a readable String
		*/
	}
}
