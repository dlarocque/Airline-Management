/*
Cargo.java

COMP 1020 Section A03
INSTRUCTOR      Bryan Wodi
ASSIGNMENT      Assignment 3
AUTHOR          Daniel La Rocque
VERSION         March 20, 2020

PURPOSE         Operate an airline
*/

public class Cargo extends Payload {
	public Cargo(double weight) { //Cargo constructor
		payloadWeight = weight;		//assign its weight that was given	
		id = 400000000 + totalItems++; //create the id based on the amount of items
		isCargo = true;
	}

}
