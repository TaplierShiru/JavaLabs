import MyFirstPackage.*;


public class MyFirstClass{

     public static void main(String []args){

        MySecondClass mySecondClass = new MySecondClass(4);

        mySecondClass.showArray();
        System.out.println("Before set new value, avg equal to: " + mySecondClass.getAvgNumberOfArray());

        mySecondClass.setNum(2, -2);

        System.out.println("After set new value, avg equal to: " + mySecondClass.getAvgNumberOfArray());
        mySecondClass.showArray();

     }
}
