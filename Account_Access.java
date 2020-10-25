import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.io.PrintWriter;
public class Account_Access {

    private String F_Name;
    private String L_Name;
    private int acc_num;
    private double balance;
    private String acc_password;

    public void pullAccount(int acc_number) throws IOException {

            File myFile = new File(String.valueOf(acc_number));
            Scanner newscan = new Scanner(myFile);
            this.acc_num = Integer.parseInt(newscan.nextLine());
            this.F_Name = newscan.nextLine();
            this.L_Name = newscan.nextLine();
            this.balance = Double.parseDouble(newscan.nextLine());
            this.acc_password = newscan.nextLine();

    }
    public int validate() {
        String temppass;
        int i = 0;
        while(i==0) {
            temppass = JOptionPane.showInputDialog("Please enter your account password: ");
            if(temppass.equals(this.acc_password)){
                JOptionPane.showMessageDialog(null, "Success! Press \"Okay\" to continue.");
                i++;

            }else{
                JOptionPane.showMessageDialog(null, "The password you entered was not correct." + "\nPlease try again.");
            }
        }return 1;
    }
    public void deposit(){
        double dep_amt;
        dep_amt = Double.parseDouble(JOptionPane.showInputDialog(String.format("Your Account Balance Is: %.2f", this.balance) +
                 "\n\nHow much would you like to deposit?"));
        this.balance += dep_amt;
        JOptionPane.showMessageDialog(null,"Deposit complete!" + "\n" + String.format("Your new balance is: %.2f", this.balance));

    }
    public void withdraw(){
        double wit_amt;
        int loop = 0;
        while(loop == 0){
            wit_amt = Double.parseDouble(JOptionPane.showInputDialog(String.format("Your account balance is: %.2f", this.balance) + "\n\nHow much would you like to withdraw?"));
        if(wit_amt > this.balance){
            JOptionPane.showMessageDialog(null, "You do not have enough funds for this Withdraw Amount" +
                     "\nPlease try again");
        }else {
            this.balance -= wit_amt;
            JOptionPane.showMessageDialog(null, "Success!" + "\n\n" +
                    String.format("Your new balance is: %.2f", this.balance));
            loop++;
        }
        }

    }
    public void summary(){
        JOptionPane.showMessageDialog(null,"First Name: " + this.F_Name + "\nLast Name: " + this.L_Name + "\nAccount Balance: " + this.balance
                + "\nPassword: " + this.acc_password);
    }
    public int append() throws IOException{
        String fileName = Integer.toString(this.acc_num);
        File old = new File(String.valueOf(this.acc_num));
        old.delete();

        PrintWriter outFile = new PrintWriter( fileName );
        outFile.println(this.acc_num);
        outFile.println(this.F_Name);
        outFile.println(this.L_Name);
        outFile.println(this.balance);
        outFile.println(this.acc_password);
        outFile.close();
        JOptionPane.showMessageDialog(null,"Account Updated!");
        return 0;
    }
}
