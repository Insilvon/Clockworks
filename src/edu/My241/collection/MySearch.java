package edu.My241.collection;

/**
 * Class built for search methods.
 * @author crcrowe0
 *
 */
public class MySearch {
	private MySearch(){

	}
	/**
	 * A method which performs a linear search on a Vector for a given value.
	 * @param vec Vector to be searched
	 * @param target The value to be searched for
	 * @return The index of the discovered value, or -1 for not found.
	 */
	public static int linearSearchSorted(MyVector vec, Comparable target){
		int j;
            for (j = 0;j<vec.size()&&target.compareTo(vec.elementAt(j))>0;++j);
			if (j<vec.size()&&target.compareTo(vec.elementAt(j))==0){
                            return j;
                        }
		return -1;
	}
	/**
	 * A binary search method, performed on a Vector for a given value.
	 * @param vec Vector to be searched
	 * @param target Value to be searched for
	 * @return Index of the found value, or -1 for not found.
	 */
	public static int binarySearch(MyVector vec, Comparable target){
		int left = 0;
		int right = vec.size()-1;
		int middle;
		while (right>=left){
			middle = (left+right)/2;
			if (target.compareTo(vec.elementAt(middle))>0)
				right = middle-1;
			else if (target.compareTo(vec.elementAt(middle))>0)
				left = middle+1;
			else
				return middle;
		}
		return -1;
	}

}
