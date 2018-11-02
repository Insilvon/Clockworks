/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My241.collection;

/**
 * Creates MyVector Object with array of objects contained within.
 * @author Colin
 */
public class MyVector implements Cloneable {
    protected Object[] data;
    protected static final int INITIAL_CAPACITY = 100;
    protected int size;
    
    /**
     * Declares new Vector Object
     */
    public MyVector(){
        data = new Object[INITIAL_CAPACITY];
        size = 0;
        //MyVector V1 = new MyVector();
        //V1.append("ABC");
    }
    /**
     * Changes value at given index to specified value.
     * @param element 
     */
    public void append(Object element){
       
        if(size==data.length){
            expand();
        }
        data[size++]= element;
        
        

    }
    private void expand() {
        Object[] temp = new Object[data.length*2];
        for (int i = 0;i<size;++i){
            temp[i]=data[i];
        }
        data = temp;
    }
    /**
     * Removes all values present in the MyVector.
     */
    public void clear(){
      this.removeRange(0,size);
    }
    /**
     * Checks to see if a specific value is located in the MyVector.
     * @param element
     * @return 
     */
    public boolean contains(Object element){
        for (int i = 0;i<size;++i){
            if (element.equals(data[i]))
                    return true;
            
        }
        return false;
    }
    /**
     * Returns the value at a given index, if the index is valid.
     * @param index
     * @return 
     */
    public Object elementAt(int index){
        if (index<0||index>=size)
            return null;
        return data[index];
    }
    /**
     * Returns the index of a specified element, if index is valid.
     * @param element
     * @return 
     */
    public int indexOf(Object element){
        for (int i = 0;i<size;++i){
            if (element.equals(data[i]))
                return i;
        }
        return -1;
    }
    /**
     * Adds a new value into the MyVector at a specified location.
     * If MyVector is too small, it expands.
     * @param index
     * @param element
     * @return 
     */
    public boolean insertAt(int index, Object element){
        if (index<0||index>=size)
            return false;
        if (size == data.length)
            expand();
        for (int i = size; i<index;--i){
            data[i]=data[i-1];
        }
        data[index]=element;
        ++size;
        return true;
    }
    /**
     * Checks to see if the MyVector is empty.
     * @return 
     */
    public boolean isEmpty(){
        if (size == 0)
            return true;
        return false;
    }
    /**
     * Removes an element at a given index, then shifts remaining
     * values down one position to fill the gap.
     * @param index
     * @return 
     */
    public Object removeAt(int index){
        if (index<0||index>=size)
            return null;
        Object temp = data[index];
        for (int i = index;i<size-1;++i){
            data[i]=data[i+1];
        }
        data[size-1]=null;
        return temp;
    }
    
    /**
     * Returns whether a removal of an object was successful.
     * @param element
     * @return 
     */
    public boolean remove(Object element){
        if (indexOf(element)==-1) {
            return false;
        }
        else {
        removeAt(indexOf(element));
        return true;
        }
    }
    
    /**
     * Replaces a value at a specified index.
     * @param index
     * @param element
     * @return 
     */
    public boolean replace(int index,Object element) {
        if (index<0||index>=size)
            return false;
        data[index]=element;
        return true;
    }
    /**
     * Returns the size of the current MyVector
     * @return 
     */
    public int size() {
        return this.size;
    }
    /**
     * Makes sure the minimum capacity for the MyVector
     * is maintained. If there is overflow, it will save temporarily.
     * @param minCapacity 
     */
    public void ensureCapacity(int minCapacity){
        if (minCapacity<=data.length-size) 
            return;
        Object[] temp = new Object[size+minCapacity];
        for(int i = 0;i<size;++i){
            temp[i]=data[i];
        }
        data = temp;
    }
    /**
     * Makes a copy of the MyVector object specified.
     * @return 
     */
    public MyVector clone(){
        MyVector vcopy = new MyVector();
        vcopy.ensureCapacity(this.size);
        for (int i = 0;i<size;++i){
            vcopy.data[i]=this.data[i];
        }
        vcopy.size=this.size;
        return vcopy;
    }
    /**
     * Reverses the order of the MyVector
     */
    public void reverse(){
        Object temp;
        for (int i = 0;i<size/2;++i){
            temp = data[i];
            data[i]=data[size-i-1];
            data[size-i-1]=temp;
        }
    }
    
    /**
     * Removes a range of values from index to index.
     * @param fromIndex
     * @param toIndex 
     */
    public void removeRange(int fromIndex, int toIndex){
        if(fromIndex>=toIndex)
            return;
        if(fromIndex<0)
            fromIndex=0;
        if(toIndex>=size)
            toIndex=size;
        int num = toIndex-fromIndex;
        for (int i = fromIndex;i<size-num;++i){
            data[i]=data[i+num];
        }
        for (int i = size-num;i<size;++i){
            data[i]=null;
        }
        size = size-num;
    }
    
    /**
     * Prints current vector values.
     * @return 
     */
    public String toString(){
        String str = "+++----------++\n"+
                "The current vector contains \n";
        str +="size="+size+"\n";
        str += "capacity ="+(data.length-1)+"\n";
        for (int i = 0;i<size;++i) {
            str =+ i+ ":" +data[i]+ "\n";
            if ((i+1)%5==0)
            str +="\n";
        }
        str += "========\n";
        return str;
    }
    /**
     * Merges two MyVectors together. Currently Broken.
     * @param v3
     * @param v2
     * @return 
     */
    public static void merge(MyVector v2, MyVector v3){
        int count = 0;
        for (int i = v2.size();i>0;--i){
            v2.insertAt(v2.size()+count, v3.data[count]);
        }
        for (int i = v2.size-14;i>0;--i){
            System.out.print(v2.elementAt(i)+" ");
        }
        for (int i = 0;i<v2.size-14;++i){
            System.out.print(v2.elementAt(i)+" ");
        }

}
}

