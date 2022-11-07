package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Johnathan Duong
 *
 */
public class CourseDBManager implements CourseDBManagerInterface{

	private CourseDBStructure cs;
	
	public CourseDBManager() {
		cs = new CourseDBStructure(10);
	}
	
	/**
	 * Adds a course (CourseDBElement) with the given information
	 * to CourseDBStructure.
	 * @param id course id 
	 * @param crn course crn
	 * @param credits number of credits
	 * @param roomNum course room number
	 * @param instructor name of the instructor
	 */
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement e = new CourseDBElement(id, crn, credits, roomNum, instructor);
		cs.add(e);
		
	}

	/**
	 * finds  CourseDBElement based on the crn key
	 * @param crn course crn (key)
	 * @return a CourseDBElement object
	 * 
	 */
	@Override
	public CourseDBElement get(int crn) {
		try {
			return cs.get(crn);
		} 

		catch (IOException e) {
			System.out.println("Exception was thrown in Manager get CRN");
			e.getMessage();
		}
		return null;
	}


	/**
	 * Reads the information of courses from a test file and adds them
	 * to the CourseDBStructure data structure
	 * @param input input file 
	 * @throws FileNotFoundException if file does not exists
	 */
	@Override
	public void readFile(File input) throws FileNotFoundException {
		 InputStream in = new FileInputStream(input);
		 BufferedReader br = new BufferedReader(new InputStreamReader(in));

		 List<String[]> list = br.lines().map(s -> s.split(" ")).collect(Collectors.toList());
		 for (String[] ar : list) {
			 if (ar.length > 5) {
				 StringBuilder instructor = new StringBuilder();
				 for (int i = 4; i < ar.length; i++) {
					 instructor.append(ar[i] + " ");
				 }
				 cs.add(new CourseDBElement(ar[0], Integer.valueOf(ar[1]), Integer.valueOf(ar[2]), ar[3],
						 instructor.toString().trim()));
			 } 
			 else {
				 cs.add(new CourseDBElement(ar[0], Integer.valueOf(ar[1]), Integer.valueOf(ar[2]), ar[3],
						 ar[4]));
			 }
		 }
	}

	/**
	 * @return an array list of string representation of each course in 
	 * the data structure separated by a new line. 
	 * Refer to the following example:
	 * Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular Room:SC100
	 * Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody Room:SC200
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> showAll() {
		ArrayList<CourseDBElement> tmp = new ArrayList<>();
		ArrayList<String> list;
		for (int i = 0; i < cs.getTableSize(); i++) {
			if (cs.hashTable[i] != null) {
				tmp.addAll(cs.hashTable[i]);
			}
		}
		list = (ArrayList<String>) tmp.stream().map(s -> s.toString()).collect(Collectors.toList());
		return list;
		}	
	}


