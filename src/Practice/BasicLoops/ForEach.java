package Practice.BasicLoops;

public class ForEach {
  public static void main(String[] args){
    int[] test = new int[10];
    for (int i = 0; i<test.length;i++){
        test[i] = i ;
    }
    for (int current : test){
        System.out.println(current);
    }
  }
}