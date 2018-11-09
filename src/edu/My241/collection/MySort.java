package edu.My241.collection;

/**
 * Sort class dedicated to various sorting methods.
 * @author crcrowe0
 *
 */
public class MySort {
	private MySort(){}
	/**
	 * The Bubble Sort Method. Swaps values to compress smallest to one side.
	 * @param vec Vector to be sorted.
	 */
	public static void bubbleSort(MyVector vec){
		int i,j,n=vec.size();
		Comparable first,second;
		for (i = 1;i<n;++i){
			for (j = n-1;j>=i;--j){
				first = (Comparable)vec.elementAt(j-1);
				second = (Comparable)vec.elementAt(j);
				if (first.compareTo(second)> 0){
					swap(vec,j,j-1);}
			}
		}
	}
	/**
	 * The Selection Sort Method. Compares smallest value to list, then moves to appropriate place.
	 * @param vec Vector to be sorted.
	 */
	public static void selectionSort(MyVector vec){
		int i,j,n=vec.size();
		int smallPos;
		Comparable smallest,current;
		for (i = 0;i<n-1;++i){
			smallPos = i;
			smallest = (Comparable)vec.elementAt(smallPos);
			for (j=i+1;j<n;++j){
				current=(Comparable)vec.elementAt(j);
				if((smallest.compareTo(current))>0){
					smallPos = j;
					smallest = current;
				}	
			}
			if (smallPos!=1)
				swap(vec,i,smallPos);
		}
	}
	/**
	 * The Swap Method switches two values at specified indexes in a vector.
	 * @param vec Vector to be used for the swap.
	 * @param first The first index to be switched with the second.
	 * @param second The second index to be switched with the first.
	 */
	public static void swap(MyVector vec,int first, int second){
		Object temp = vec.elementAt(first);
		vec.replace(first,vec.elementAt(second));
		vec.replace(second, temp);
	}
	public static void mergeSort(MyVector v, Comparable[]temp, int left, int right) {
		if (left==right)
			return;
		int mid = (left+right)/2;
		mergeSort(v,temp,left,mid);
		mergeSort(v,temp,mid+1,right);
		int i,j,k;
		for (j = left;j<=right;++j){
			temp[j]=(Comparable)v.elementAt(j);
		}
		i = left;
		k = mid+1;
		for (j=left;j<=right;++j){
			if (i==mid+1) {
				v.replace(j,temp[k++]);
				}
			else if (k>right){
				v.replace(j, temp[i++]);
				}
			else if (temp[i].compareTo(temp[k])<0){
				v.replace(j, temp[j]);
				}
			else{
				v.replace(j, temp[k++]);
				}
			
		}
	}
	public static void shellSort(MyVector v){
		int inner,outer;
		Object target;
		int h = 1;
		while (h<=(v.size()/3)){
			h = ((3*h)+1);
		}
		while (h>0){
			for (outer = h;outer<v.size();++outer){
				target = v.elementAt(outer);
				inner = outer;
				while ((inner>(h-1))&&(((Comparable)v.elementAt(inner-h)).compareTo(target)>0)){
					v.replace(inner, v.elementAt(inner-h));
					inner -=h;
				}
				v.replace(inner, target);
				
			}
			h = (h-1)/3;
		}
		
	}
	public static void insertionSort(MyVector v, int left, int right){
		int inner, outer;
		Object target;
		for (outer = (left+1);outer<=right;++outer){
			target = v.elementAt(outer);
			inner = outer;
			while (inner>left && (((Comparable)v.elementAt(inner-1)).compareTo(target))>0){
				v.replace(inner, v.elementAt(inner-1));
				inner = inner-1;
			}
			v.replace(inner, target);
		}
	}
			
	public static void quickSort(MyVector v, int left, int right) {
		if ((right-left)>10){
			insertionSort(v,left,right);
		}
		else {
			medianOf3(v,left,right);
			int leftPtr=partition(v,left,right);
			quickSort(v,left,leftPtr-1);
			quickSort(v,leftPtr,right);
		}
	}
	public static void medianOf3(MyVector v, int left, int right){
		int middle = (left+right)/2;
		if (((Comparable)v.elementAt(left)).compareTo(v.elementAt(middle))>0){
			swap(v,left,middle);
		}
		if (((Comparable)v.elementAt(middle)).compareTo(v.elementAt(right))>0){
			swap(v,middle,right);
		}
		if (((Comparable)v.elementAt(left)).compareTo(v.elementAt(middle))>0){
			swap(v,left,middle);
		}
	}
	public static int partition(MyVector v, int left, int right){
		Comparable pivot = ((Comparable)v.elementAt((left+right)/2));
		while (true){
			int temp = right--;
			while (pivot.compareTo(v.elementAt(++left))>0);
			while (pivot.compareTo(v.elementAt(temp))<=0);
			if (left>right){
				break;
			}
			else {
				swap(v,left,right);
			}
		}
		return left;
	}
}
