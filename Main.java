/*
    Hello and welcome to my Banking Program Project.  In this code I will build out a full application for a
    theoretical banking client.  As I am learning I will constantly updating the program to add in new features
    that display the skills that I have picked up.  I will properly comment this code to explain my thoughts
    and also note areas that I am curious about and if they could be handled differently.
 */



import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.swing.JOptionPane;
import java.io.*;
import java.io.PrintWriter;
import java.util.Random;



public class Main {
    public static void main(String[] args)throws IOException
    {

        int loop = 0;
        String input;

        while(loop == 0) {
            input = JOptionPane.showInputDialog(null, "          Welcome to Bank of Java.  \n\n     Please enter one of the following options"
                    + "\n\n\"Access\"  :   Access Your Account" + "\n\"Create\" :   Create Account" + "\n\"End\" :       To Quit");

            if(input.equals("End") || input.equals("end"))
            {
                System.out.println("End was chosen");
                loop = 1;
            }else if(input.equals("Create") || input.equals("create"))
            {
                createAccount();
                System.out.println("Create was chosen");
            }else if(input.equals("Access") || input.equals("access"))
            {
                accessAccount();
                System.out.println("Select was Chosen");
            }else
            {
                JOptionPane.showMessageDialog(null, "Invalid Entry.  Please try again");
            }


        }

    }
   //This section of the code handles the complete account creation process
    /*Curiosities:  Would it be more efficient to further break down this method into new methods, ie:  a write file
    method...an append Class method..
     */

    public static void accessAccount() throws IOException {
      int acc_number, validated, found;
      String input;


        Account_Access account = new Account_Access();
        found = 0;

        //validate if account exist
        while(found == 0) {
            try{
                acc_number = Integer.parseInt(JOptionPane.showInputDialog("Welcome to your account access! Please enter your account number"
                 + "Or enter \"0\" to exit"));
            if(acc_number == 0)
                break;
                account.pullAccount(acc_number);
            found++;
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Invalid account number! Please try again");
        }}
        validated = account.validate();
        while(validated == 1) {
            input = JOptionPane.showInputDialog("What would you like to do?\n" + "\n\"Summary\": For Account Summary" +
                     "\n\"Deposit\": To make a deposit" + "\n\"Withdraw\": To make a Withdraw" +
                    "\n\"End\": To exit");
            input = input.toLowerCase();
            switch (input) {
                case "deposit": account.deposit(); break;
                case "withdraw": account.withdraw(); break;
                case "end": validated = account.append();break;
                case "summary": account.summary();break;
                default: break;

        }
    }}



    public static void createAccount()throws IOException
    {
        //initialize environment variables
        Random newRandom = new Random();
        String input, pass, repass;
        int i = 0; int tempnum;
        double input2;
        Account accCreate = new Account();

        //create random account number
        tempnum = newRandom.nextInt(5000);

        //account details to be stored in a file named after the account number
        String fileName = Integer.toString(tempnum);
        PrintWriter outFile = new PrintWriter( fileName );
        outFile.println(tempnum);
        accCreate.setacc_num(tempnum);

        //Collect account details

       //Welcome splash screen
        JOptionPane.showMessageDialog(null, "             Welcome to the Bank of Java!" + "\nPlease click \"Okay\" to begin the account creation");

        //Collect user info
        input = JOptionPane.showInputDialog("What is your first name?");
        outFile.println(input);
        accCreate.setF_Name(input);
        input = JOptionPane.showInputDialog("What is your last name?");
        outFile.println(input);
        accCreate.setL_Name( input );

       //Initial Deposit
        input2 = accCreate.setBalance();
        outFile.println(input2);

       //Collect user password
        //Curious if it is safer to nest this method in the Account Class...call a public set_password that calls
        //a private method to keep it secure..
        while(i==0){
            pass = JOptionPane.showInputDialog("Please enter an account password");
            repass = JOptionPane.showInputDialog("Please re-enter your password");
            if(pass.equals(repass)){
                accCreate.setAcc_password(pass);
                outFile.println(pass);
                JOptionPane.showMessageDialog(null, "Account Successfully Created!");
                i=1;
            }else{
                JOptionPane.showMessageDialog(null, "The passwords didn't match, please try again");
            }

        }outFile.close();

      accCreate.show_summary();

    }

}
