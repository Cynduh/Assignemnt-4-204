package application;

/**
 * @author Johnathan Duong
 *
 */
/**
 * @author John
 *
 */
public class CourseDBElement implements Comparable<CourseDBElement> {

	private String course;
	private int CRN;
	private int credits;
	private String room;
	private String instructor;
	
	
	public CourseDBElement(String course, int CRN, int credits, String room, String instructor) {
		
		this.course = course;
		this.credits = credits;
		this.CRN = CRN;
		this.instructor = instructor;
		this.room = room;
	}
	
	public CourseDBElement() {
		this.course = "";
		this.credits = 0;
		this.CRN = 0;
		this.instructor = "";
		this.room = "";
		
	}
	
	/**
	 * @return course
	 */
	public String getID() {
		return course;
	}

	/**
	 * @param course
	 */
	public void setCourse(String course) {
		this.course = course;
	}

	/**
	 * @return CRN
	 */
	public int getCRN() {
		return CRN;
	}

	/**
	 * @param CRN
	 */
	public void setCRN(int CRN) {
		this.CRN = CRN;
	}

	/**
	 * @return credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * @param credits
	 */
	public void setCredits(int credits) {
		this.credits = credits;
	}

	/**
	 * @return room
	 */
	public String getRoomNum() {
		return room;
	}

	/**
	 * @param room
	 */
	public void setRoom(String room) {
		this.room = room;
	}

	/**
	 * @return instructor
	 */
	public String getInstructor() {
		return instructor;
	}

	/**
	 * @param instructor
	 */
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	/**
	 *@return hashcode of the CRN
	 */
	@Override
	public int hashCode() {
		String s = String.valueOf(getCRN());
		long hash =0;
		int prime = 31;
		for(int i =0; i<s.length(); i++) {
			hash = prime * hash + s.charAt(i);
		}
		return (int) hash;
	}
	
	/**
	 *@return comparison
	 *@param obj
	 */
	@Override
	public int compareTo(CourseDBElement obj) {
		if(CRN == obj.CRN)
			return 0;
		else if(CRN> obj.CRN)
			return 1;
		else 
			return -1;
	}
	
	/**
	 *@return as a formatted string
	 */
	@Override
	public String toString() {
		return "\nCourse:" + course + " CRN:" + CRN + " Credits:" + credits
				+ " Instructor:" + instructor + " Room:" + room;
	}
	
	
}
