package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Johnathan Duong
 *
 */
public class CourseDBStructure<T> implements CourseDBStructureInterface{

	protected LinkedList<CourseDBElement>[] hashTable;
	
	@SuppressWarnings("unchecked")
	public CourseDBStructure(int i) {
		hashTable = new LinkedList[i];
	}
	
	@SuppressWarnings("unchecked")
	public CourseDBStructure(String s, int i) {
		hashTable = new LinkedList[i];
	}
	
	/** 
	* Adds a CourseDBElement object to the CourseDBStructure using the hashcode
	* of the CourseDatabaseElemen object's crn value.
	* If the CourseDatabaseElement already exists, exit quietly
	*  
	* @param element the CourseDBElement to be added to CourseDBStructure
	*/
	@Override
	public void add(CourseDBElement element) {
		int index = getHashIndex(element);
		
		if(hashTable[index]==null) {
			hashTable[index]=new LinkedList<CourseDBElement>();
			hashTable[index].add(element);
		}
		else {
			if(hashTable[index].contains(element))
				return;
			else {
			hashTable[index].add(element);
			}
		}	
	}
	/**
	 * Find a courseDatabaseElement based on the key (crn) of the
	 * courseDatabaseElement If the CourseDatabaseElement is found return it If not,
	 * throw an IOException
	 * 
	 * @param crn crn (key) whose associated courseDatabaseElement is to be returned
	 * @return a CourseDBElement whose crn is mapped to the key
	 * @throws IOException if key is not found
	 */
	@Override
	public CourseDBElement get(int crn) throws IOException {
		CourseDBElement temp = new CourseDBElement();
		
		temp.setCRN(crn);
		int index = getHashIndex(temp);
		LinkedList<CourseDBElement> list = hashTable[index];
		return list.stream().filter(c -> c.getCRN() == crn).findAny().orElseThrow(IOException::new);
	}
	
	/**
	 * @return an array list of string representation of each course in 
	 * the data structure separated by a new line. 
	 * Refer to the following example:
	 * Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular Room:SC100
	 * Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody Room:SC200
	 */
	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> all = new ArrayList<String>();
		int counter = 0;
		
		for(int i = 0; i < getTableSize(); i++) {
			if(hashTable[i] != null) {
				for(int j = 0; j < hashTable[i].size(); j++) {
					all.add(hashTable[i].get(j).toString());
				}
			}
		}
		
		for(int i = 0; i < all.size(); i++) {
			for(int j = 0; j < all.size(); j++) {
				if(all.get(i).equals(all.get(j))) {
					counter++;
					
				}
				if(counter > 1) {
					all.remove(i);
					counter--;
				}
			}
		}

		return all;
	}	
	/**
	* Returns the size of the ConcordanceDataStructure (number of indexes in the array)
	*/
	@Override
	public int getTableSize() {
		return hashTable.length;
	}
	
	/**
	 * gets the index position in array
	 * @param element
	 * @return index
	 */
	private int getHashIndex(CourseDBElement element) {
		int hashIndex = element.hashCode() % hashTable.length;
		if(hashIndex < 0) {
			hashIndex += hashTable.length;
		}
		return hashIndex;
	}

}
