import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;

/*
    This program is wild.  I knew it was going to be interesting because essentially its developing a version
    of the CC algroithm that authenticates credit cards.  It has been enjoyable coding real life examples
    and how it presents itself in class.  One interesting note:  I usually start by building menu logic first and
    then wire in the blocks of logic/methods.  This time around I went in the opposite direction with building
    the methods first to handle the conversion logic - which was what I thought drove this assignment.


    Areas of Focus:
    - Methods:  truely being able to reuse methods with two possible entry points and making sure the looping
                structure was set up to handle the 15 and 16 digit input.

    -Primitive conversion:  At the bottom of this code you will see failed methods that I had originally tried
                to use to evaluate and dissect the input.

    -Arrays:  I was originally going to use Lists<> but the situation really didn't call for a dynanimc listing.

    -Toolkits:  Again, I am learning the value of building in some toolkits into the code to constantly evaluate
                where the program is taking a wrong turn.  I left some active but you will find I greyed out
                others to clean up the console output.

    -Indentation and code cleanliness:  just making different code blocks neat and organized.  I do believe I
                missed some end bracket labeling which caught me a snag or two when nesting.






*/
public class Program_11_1{

// Things to clean up:
//  -declare 15 as a Constant


    public static void main(String [] args)
    {
      boolean quit = false;
      final int ACC_NUM_MAX_LEN = 15;

      while(!quit)
      {

        int menuOption = Integer.parseInt(JOptionPane.showInputDialog("Enter 0 to quit; \nEnter 1 to validate a credit card number \nEnter 2 calculate the check digit for an account number"));

        if(menuOption == 0)
          quit = true;
        else if(menuOption == 1)
        {

          int tempCheckDigit;
          String input = JOptionPane.showInputDialog("Enter the 16 digit card number");
          if(input.length() != 16)
            JOptionPane.showMessageDialog(null, "Data Entry Error \nInvalid Account Number Length: length = " + input.length());
          else
          {
            int charDigit;
              char tempChar = input.charAt(ACC_NUM_MAX_LEN);
              System.out.println(tempChar);
              charDigit = Character.getNumericValue(tempChar);
              int[] inputRack = new int[ACC_NUM_MAX_LEN];
              inputRack = hardWork(input);

              int sum = checkDigit(inputRack);
              int sumTwo = checkSecond(inputRack);
              int checkDigit = findDigit(sum, sumTwo);

              if(checkDigit == charDigit)
              {
                JOptionPane.showMessageDialog(null, "The Card Number is valid");
              }
              else
              {
                JOptionPane.showMessageDialog(null, "Invalid Card Number");
              }
          }



        }else if(menuOption == 2)
        {
          String input = JOptionPane.showInputDialog("Enter the 15 digit account number");
          if(input.length() != ACC_NUM_MAX_LEN)
          {
            JOptionPane.showMessageDialog(null, "Data Entry Error \nInvalid Account Number Length: length = " + input.length());
          }else
          {
            int[] inputRack = new int[ACC_NUM_MAX_LEN];
            inputRack = hardWork(input);

            int sum = checkDigit(inputRack);
            int sumTwo = checkSecond(inputRack);
            int checkDigit = findDigit(sum, sumTwo);
            JOptionPane.showMessageDialog(null, "Check Digit = " + checkDigit);
          }

        }else
          {quit=true;}
            //JOptionPane.showMessageDialog(null, "Invalid Entry this window was not included in the test cases");}

      }//end while(!quit);

// *********THE TESTER************//
    // for(int i = 0; i < 15; i++)
    // {
    //   System.out.println(inputRack[i]);
    //
    // }
    // System.out.println(sum);
    // System.out.println(sumTwo);
    // System.out.println("The check digit is = " + checkDigit);
//********************************//



 }
public static int[] hardWork(String input)
{
  int[] inputRack = new int[15];
  char[] stringArray = new char[15];
  int MAX_UPPER_LIMIT = 15;                   //Naming conventions..
  for(int i = 0; i < MAX_UPPER_LIMIT; i++)
  {
    stringArray[i] = input.charAt(i);
  }

  for(int i = 0; i < MAX_UPPER_LIMIT; i++)
  {
    inputRack[i] = Character.getNumericValue(stringArray[i]);

  }
  return inputRack;

}//end hardWork();

public static int checkDigit(int[] inputRack)
{
  int firstSum = firstRound(inputRack);
  return firstSum;


}//end checkDigit();

public static int firstRound(int[] inputRack)
{
  int sum = 0;
  int lameCounter = 1;
  for(int i = 0; i < 7; i++)
  {
    sum += inputRack[lameCounter];
    lameCounter +=2;
  }
  return sum;
}//end firstRound();

public static int checkSecond(int[] inputRack)
{
  int sum = 0;
  int lameCounter = 0;
  int tempSum = 0;
  for(int i = 0; i < 8; i++)
  {
    tempSum = inputRack[lameCounter]*2;

    if (tempSum > 9){           //I am not sure if I needed the bracket for the single execute line, or
      tempSum = quickSnip(tempSum); sum += tempSum;} //because I needed and else
    else
      sum += tempSum;


    lameCounter +=2;
  }
  return sum;


}//end checkSecond();

public static int quickSnip(int tempSum)
{
  String tempString = String.valueOf(tempSum);

  tempSum = Character.getNumericValue(tempString.charAt(0)) + Character.getNumericValue(tempString.charAt(1));
  System.out.println("The temp sum here was = " + tempSum);
  return tempSum;

}//end quickSnip();

public static int findDigit(int sum, int sumTwo)
{
int  tempDigit = sum + sumTwo;
  tempDigit = 10 - (tempDigit%10);
  return tempDigit;


}//end checkDigit();


}



//**********RETIRED TOOLKIT***********
// //Realizing that this right here was probably going to be the toughest part of the assignment but learning
//to build these key's has really made life easier..

// System.out.println("Input = " + input);
// System.out.println("input%10 = " + input%10 + "\ninput%100 = " + input%100 + " and also (input%100/10) = " + (input%100)/10 +
//  "\ninput%1000(3) = " + input%1000 + "and also (input%1000)/100 = " + (input%1000)/100 +
// "\ninput%10000(4) = " + input%10000 + " and also (input%10000)/1000 = " + (input%10000)/1000 + "\ninput%100000(5) = : " + input%100000);


//  ********FAILED METHOD ONE*************
// public static List breakItDown(String input) {
//     System.out.println("Made it to breakItDown");
//     Array<Integer> inputRack = new Array<Integer>(15);
//
//     for(int i = 0; i < 15; i++){  //Just for the record - I was trying to find a way to do hasNextChar in a string scan but
//                               //ended up hardcoding the known max of possible char's
//     inputRack[i] = Integer.parseInt(input.charAt(i));
//
//     }
//     System.out.println(inputRack);
//     return inputRack;
//

//**********Failed Method TWO***************
//Interestingly enough, I knew that this was going to store my array in reverse order.
// int[] inputRack = new int[15];
// long modulo = 10;

//Inside a for loop:
      // int placeInArray = 14;
      // placeInArray--;
      // modulo = modulo*10;
      // long placeHolder = input%modulo;
      // int moduloTwo = (int) modulo;
      // int division = moduloTwo/10;
      // long finalDivide = (long) division;
      // finalDivide = placeHolder/finalDivide;
      // int finalMath = (int) finalDivide;
      // inputRack[placeInArray] = finalMath;
