import javax.swing.*;
import javax.swing.JOptionPane;
import java.io.*;
import java.io.PrintWriter;

public class Account {
    private String F_Name;
    private String L_Name;
    private int acc_num;
    private double balance;
    private String acc_password;


    public void show_summary(){
        JOptionPane.showMessageDialog(null, "Your account was successfully created!"
        + "\n\nName: " + this.F_Name + " " + this.L_Name +
                "\nAccount Number: " + this.acc_num +
                 "\n" + String.format("Balance: $%.2f", this.balance));
    }

    public void setF_Name(String name) {
        this.F_Name = name;
    }
    public void setL_Name(String name){
        this.L_Name = name;
    }
    public void setacc_num(int acc){
        this.acc_num = acc;
    }
    public double setBalance(){

        int c=0;
        double input2 = 0;
        while(c==0){
            input2 = Double.parseDouble(JOptionPane.showInputDialog("What will be your initial deposit?"));
            if(input2 < 0){
                JOptionPane.showMessageDialog(null, "You must enter a value greater than 0");
            }else{
                c=1;
                this.balance = input2;


            }

        }
        return input2;


    }
    public int getAcc_num(){
        return this.acc_num;
    }
    public void setAcc_password(String pass){
        this.acc_password = pass;
    }

}

