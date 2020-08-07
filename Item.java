/*
Item.java

COMP 1020 Section A03
INSTRUCTOR      Bryan Wodi
ASSIGNMENT      Assignment 3
AUTHOR          Daniel La Rocque
VERSION         March 20, 2020

PURPOSE         Operate an airline
*/
public abstract class Item {
	protected int id;
	protected static int totalItems = 0;

	public Item() {
	}
	
	public static int getTotalNumberOfItems(){  //return the total number of items
		return totalItems;
	}

	public int getID(){	//return the id of the item
		return id;
	}
}
