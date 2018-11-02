package Tutoring;

import java.util.Scanner;

public class NumberArrays {
    public static void test(){
        Scanner scan = new Scanner(System.in);

        System.out.println("What is your first value to be added?");

        String store = scan.nextLine();

        System.out.println("What is your second value?");

        String shop = scan.nextLine();


        int pare = Math.max(store.length(),shop.length());
        int[] cart = new int[pare];
        int[] basket = new int[pare];

        int j = 1;
        for(int i = cart.length-1; i>-1; i--){

            if (j<=store.length()){
                int value = store.charAt(store.length()-j)-'0';
                cart[i] = value;
                j++;
            }
            else {
                cart[i] = 0;
            }
        }

        j = 1;
        for(int i = basket.length-1; i>-1; i--){

            if (j<=shop.length()){
                int value = shop.charAt(shop.length()-j)-'0';
                basket[i] = value;
                j++;
            }
            else {
                basket[i] = 0;
            }
        }

        int carry = 0;
        int[] aisle = new int[pare];
        for(int i = cart.length-1;i>-1;i--){

            int sum = 0;
            sum = basket[i]+cart[i]+carry;
            carry = 0;

            if (sum>9){
                int r = sum%10;
                aisle[i] = r;
                carry = 1;
            }

            else {
                aisle[i] = sum;
            }
        }

        if (carry!=0){
            int[] aisle2 = new int[pare+1];
            aisle2[0] = carry;
            for (int i = 0; i<aisle.length; i++){
                aisle2[i+1] = aisle[i];
            }

            for (int i = 0; i<aisle2.length; i++){
                System.out.print(aisle2[i]);
            }
        }
        else {
            for (int i = 0; i<aisle.length; i++){
                System.out.print(aisle[i]);
            }
        }
    }
}
