/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.My241.collection;

/**
 * Foundations for the MySet Class - a subclass of MyVector.
 * @author Colin
 */
public class MySet extends MyVector {
    public MySet() {
    
    }
    /**
     * Returns the number of elements in the current MySet.
     * @return integer size of current MySet.
     */
    public int cardinality(){
        return this.size;
    }
    /**
     * Adds an element to the set at the current location.
     * @param element Element to be inserted.
     */
    public void add(Object element){
        //HOW TO CHECK FOR DOUBLES?
    	if (this.contains(element)==true){}
    	else{
            this.append(element);}        
    }
    /**
     * Expands the MyVector Superclass if enough size isn't present.
     */
    
    /**
     * Generates a complement set for A-B. The set which calls
     * this method becomes A, while B consists of the defined set. 
     * @param B The secondary set to check against. (Must be provided)
     * @return Returns MySet compliment set A, leaving the original in tact.
     */
   //CHECK ON THE NULL ISSUE, MYVECTOR MERGE AS WELL 
    public MySet compliment(MySet B){
    	MySet A = new MySet();
    	A = this.setClone();
    	for (int i = 0;i<A.size;++i){
    		for (int k = 0;k<B.size;++k){
    			while (A.data[i]==B.data[k]) {
    				A.removeAt(i);
    				A.size--;
    			}
    		}
    	}
    	return A;
    }
    /**
     * Generates a new MySet which consists of the intersection of two sets.
     * @param B The secondary set to check against. (Must be provided)
     * @return A MySet with intersection of A and B within.
     */
    
    public MySet intersection(MySet B) {
    	MySet A = new MySet();
    	A = this.setClone();
    	MySet C = new MySet();
    	for (int i = 0;i<A.size;i++){
    		for (int k = 0;k<B.size;k++){
    			if (A.data[i]==B.data[k]){
    				C.add(A.data[i]);
    			}
    			else {
    			}
    		}
    	}
    	return C;
    }
    public MySet setClone(){
    	MySet scopy = new MySet();
    	scopy.ensureCapacity(this.size);
    	for (int i = 0;i<size;++i){
    		scopy.data[i]=this.data[i];
    	}
    	scopy.size = this.size;
    	return scopy;
    }
    //NEEDS TESTING
    public boolean subsetOf(MySet B){
    	for (int i = 0;i<B.size;++i){
    		if (this.contains(B.elementAt(i))==false)
    			return false;
    	}
    	return true;
    }
    //NEEDS UNION
    public MySet symmetricDifference(MySet B){
    	MySet A = new MySet();
    	A = this.setClone();
    	
    	(A.compliment(B)).union(B.compliment(A));
    	return B;
    }
    
    public MySet union(MySet B){
    	MySet A = new MySet();
    	A = this.setClone();
    	for (int i = 0;i<B.size;++i){
    		A.add(B.elementAt(i));
    	}
    	return A;
    }

}
