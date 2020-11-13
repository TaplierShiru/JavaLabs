package MyFirstPackage;

import java.util.Random; 


public class MySecondClass{

	private int[] arrOfRandomNumbers;

	public MySecondClass(int sizeOfArray){
		arrOfRandomNumbers = new int[sizeOfArray];

		Random randGen = new Random();

		for (int i = 0;i < sizeOfArray;i++){
			arrOfRandomNumbers[i] = randGen.nextInt(sizeOfArray);
		}

	}

	public int getNum(int index){
		return arrOfRandomNumbers[index];
	}

	public void setNum(int index, int value){
		arrOfRandomNumbers[index] = value; 
	}

	public float getAvgNumberOfArray(){
		float avg = 0.0f;

		for (int elem : arrOfRandomNumbers){
			avg += elem;
		}

		avg /= arrOfRandomNumbers.length;

		return avg;
	}

	public void showArray(){
		for (int elem : arrOfRandomNumbers){
			System.out.print(elem + " ");
		}

		System.out.println();
	}
}