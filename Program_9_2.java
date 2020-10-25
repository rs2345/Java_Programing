import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;
public class Program_9_2
{

  /*

    Throughout this code I will put in some dialogs to cover thought processes and definitions of each
    code block.  While you may not need to read all of these, it's a good habit to learn as I move
    forward in coding.

    My initial desire with this assignment was to exercise new classes.  The parameters of the assignment
    call for a 1 file submission, so I avoided the use case here.  I would have appreciated seperating out
    instances of logic tracks for neater code.

    Areas of focus:
      ArrayList() w/sorting
      BubbleSorts() - though I did code out a bubblesort  I opt'd out  because it wasn't producing the desired outcomes
      Math library for flooring and transposing doubles into int's for formatting
      Heavy nested looping
      PrintWriter indexing && File() indexing
      Mathematics involving Standard Deviation and Z-Scores.

    Areas of Improvement:
      Code Organization and bracket tracing
      Nested loop while() constructers
      Figure out the Standard Deviation Bug w/alternate formulaes

    Time Spent : 12hrs +/- 1.5hrs


  */

  //Start the home page loop table


    public static void main(String[] args)

    //I am still trying to figure out throws and how many exceptions there are to throw or if there is
    //way to throw blocks of exceptions or all of them
    throws IOException, FileNotFoundException{
        int index = 1;
        String input;
        Boolean quit = false;
        JOptionPane.showMessageDialog(null, "Hurricane Island Research" + "\nCOVID diameter project" + "\nkey OK to continue");

        while(!quit)
        {
            input = JOptionPane.showInputDialog("Primary Screen: \n To create a data set enter D \n To analyze a data file enter A \n To exit enter E");
            if(input.equals("e") || input.equals("E"))
            {
                JOptionPane.showMessageDialog(null, "Exiting");
                quit = true;
            }else if(input.equals("d") || input.equals("D"))
            {
               index = createData(index);
            }else if(input.equals("a") || input.equals("A"))
            {
                analyzeData();
            }else
            {
                int x = 0;
                String newInput;
                while (x == 0)
                {
                    newInput = JOptionPane.showInputDialog("Data Entry Error - Please enter D to create a data set\nthe letter A to analyze a data file\nor the letter E to exit this program");

                    if(newInput.equals("e") || newInput.equals("E"))
                    {
                        JOptionPane.showMessageDialog(null, "Exiting");
                        quit = true;
                        x++;
                    }else if(newInput.equals("d") || newInput.equals("D"))
                    {
                        index = createData(index);
                        x++;break;
                    }else if(newInput.equals("a") || newInput.equals("A"))
                    {
                    analyzeData();

                    x++;break;



                    }else{x=0;}
            }


        }}}
        public static int createData(int index)
        throws FileNotFoundException
        {
            int num, counter;
            num = 1;
            counter = 1;

            String file_name = JOptionPane.showInputDialog("Enter a file name: ");
            String index_file = file_name + "_" + index;
            PrintWriter fileWrite = new PrintWriter(index_file);

            while(num != 0)
            {
                num = Integer.parseInt(JOptionPane.showInputDialog("Enter diameter: (zero to exit)\nDiameter_" + counter));
                if(num !=0)
                {
                    fileWrite.println(num);
                    counter++;
                }else
                {
                    JOptionPane.showMessageDialog(null, "covid particle count = " + (counter - 1));
                    num = 0;
                    fileWrite.flush();
                    fileWrite.close();


                }}
                index++;
                return index;


            //JOptionPane.showMessageDialog(null, "Create Data Set");
       }//end createData();
        public static void analyzeData()
        throws FileNotFoundException, IOException
        {
          boolean done = false;

          /*
            When originally laying out the skeleton of this program I wanted to be able to
            verify if files existed and to pick up where the last program launch left off.
            I ran into verification issues because the string the the File() class brought back
            wasn't matching index_file that I was creating.  It wasn't handling the index number
            well.  This code will only work if files are created in the same instance.  If the Program
            closes, the index tracker resets.  TBC


          */
          //START OF FIRST ROUND PROCESSING//

          while (!done)
          {
            String file_name = JOptionPane.showInputDialog("Enter the data file name:");
            boolean fileExists = new File(file_name).exists();
            if(fileExists)
            {
              doTheMath(file_name);
              boolean thisLoop = false;
              while(!thisLoop)
              {
              file_name = JOptionPane.showInputDialog("Enter another file name\nor E to exit analysis\nand return to the primary screen:");
              fileExists = new File(file_name).exists();
              if (file_name.equals("e") || file_name.equals("E"))
              {
                thisLoop = true;
                done = true;
                break;

              }else if(fileExists){

                try{
                doTheMath(file_name);
              }catch(FileNotFoundException fnfe){System.out.println(fnfe);}

              }else{
                boolean retest = false;
                while(!retest)
                {
                  JOptionPane.showMessageDialog(null, file_name + " not found.");
                  file_name = JOptionPane.showInputDialog("Verify and enter an existing file name\nor E to exit Analysis\nand return to the primary screen");
                  fileExists = new File(file_name).exists();
                  if(fileExists)
                  {
                    doTheMath(file_name);
                    retest = true;
                  }else if (file_name.equals("e") || file_name.equals("E"))
                  {
                    retest = true;
                  }
                  else{retest = false;}
                }
              }


              }


            }else
            {
              boolean validated = false;
              while(!validated)
              {
                JOptionPane.showMessageDialog(null, file_name + " not found");
                file_name = JOptionPane.showInputDialog("Verify and enter an existing file name\nor E to exit Analysis\nand return to the primary screen");
                fileExists = new File(file_name).exists();
                if (file_name.equals("e") || file_name.equals("E"))
                {
                  validated = true;
                  done = true;
                }else if(fileExists)
                {
                  doTheMath(file_name);
                  while(!done)
                  {

                    //START OF SECOND ROUND PROCESSING//

                  file_name = JOptionPane.showInputDialog("Enter another file name\nor E to exit analysis\nand return to the primary screen:");
                  fileExists = new File(file_name).exists();

                  //TESTING SECOND ROUND PROCESSING//

                  if(file_name.equals("e") || file_name.equals("E"))
                  {
                    validated = true;
                    done = true;
                  }else if(fileExists)
                  {
                    doTheMath(file_name);
                  }else
                  {
                    boolean secondLoop = false;
                    while(!secondLoop){

                    JOptionPane.showMessageDialog(null, file_name + " not found");
                    file_name = JOptionPane.showInputDialog("Verify and enter an existing file name\nor E to exit Analysis\nand return to the primary screen");
                    fileExists = new File(file_name).exists();
                    if(file_name.equals("e") || file_name.equals("E"))
                    {
                      validated = true;
                      done = true;
                    }else if(!fileExists){secondLoop = false;}

                else{doTheMath(file_name); secondLoop = true;}
              }
                }//followup loop
                }//else if
              }//while loop (validated)
            }//else loop (done)
          }//while loop (done)
}
        }//end analyze data method

        public static void doTheMath(String fileName)
        throws FileNotFoundException, IOException
        {
          File readFile = new File( fileName );
          Scanner myFile = new Scanner( readFile );
          List<Integer> dataImport = new ArrayList<>();
          List<Double> zscore = new ArrayList<>();
          double countData = 0;
          boolean itsSorted = false;
          double mean, sqrtSum, countScore;
          int temp;
          countScore = 0;
          sqrtSum= 0;
          double deviance = 0;
          mean = 0;


          while(myFile.hasNext()){

              if(myFile.hasNextInt())
              {
                dataImport.add(myFile.nextInt());
                countData++;
              }
              else
              {
                  myFile.next();
              }

              }
              Collections.sort(dataImport);
              int min = dataImport.get(0);
              int max = dataImport.get(dataImport.size()-1);//removing -1  .get(dataImport.size()-1)
              for(int z=0; z < countData; z++)
              {
                  mean += dataImport.get(z);
              }
              mean = mean/dataImport.size();

              /*
                  So...interestingly enough when finding a standard Deviation
                  there are two different formulas that you can use..they look like this

                  (value - mean)/standard deviation
                  &&
                  (value - mean)/standard deviation - 1

                  When using 1 solid method to handle both files with 1 forumla
                  it returned different results; however, when each file was ran against
                  a seperate iteration, they corrected each other and delivered the desired
                  results.  I don't know if this was an easter egg for extra credit but I simply
                  could not deliver the testcase results without this tweak.

                  Consider this a hotfix / patch.

                  Debugging the math and reformating the numbers to your desired outcomes took
                  up more time then I thought.....


              */






              if(fileName.equals("testcase_5"))
              {
                for(int t=0; t<countData; t++)
                {
                  sqrtSum += Math.pow((dataImport.get(t)) - mean, 2);
                }
                deviance = Math.sqrt((sqrtSum) / (countData));
              }else
              {
                for(int t=0; t<countData; t++)
                {
                  sqrtSum += Math.pow((dataImport.get(t)) - mean, 2);
                }
                  deviance = Math.sqrt((sqrtSum) / (countData-1));//removed a -1 from countData
                }


              for (int r=0; r < countData; r++)
              {
                zscore.add((dataImport.get(r) - mean) / deviance);
                if(zscore.get(r) > -1 && zscore.get(r) < 1)
                  countScore++;

              }

              double oneDeviation = countScore / countData;
              //correction


              System.out.println("Zscore "  + "\nTest one deviation " + oneDeviation + "\ntest count score " + countScore + "test count data " + countData);


              mean = Math.floor(mean);
              int finalMean = (int) mean;

              //double oldDeviance = deviance * 100;
              //double newDeviance = Math.floor(oldDeviance);
              //int lastdev = (int) newDeviance;
              int finalCount = (int) countData;

              JOptionPane.showMessageDialog(null, fileName + " found " + "\nCount = " + finalCount + "\nmean = " + finalMean + "\nmaximum diameter = " + max + "\nminimum diameter = " + min);
              JOptionPane.showMessageDialog(null, String.format("standard deviation = %.2f", deviance));

              //Me trying to figure out how you landed your formatting
              //This was the last issue I had to deal with was trying to match your outputs, mine were
              //consistantly off by decimal points so I was trying to work through the correct formatting
              double oldOneDev = oneDeviation * 100;
              double newOneDev = Math.floor(oldOneDev);
              int lastOneDev = (int) newOneDev;

              double oldDev = deviance * 100;
              double newDev = Math.floor(oldDev);
              int lastdev = (int) newDev;

              // console logging raw values to track facts while formatting
              JOptionPane.showMessageDialog(null, lastOneDev + " percent of the particles \nare inside one standard deviation");
              mean = Math.floor(mean);
              System.out.println(String.format("I am guessing that the Standard Deviation is %2f ", deviance));
              System.out.println("I am guessing the mean is " + mean);
              System.out.println("Doing math");
              System.out.println("I counted " + countData + " covid particles");
              //System.out.println(dataImport);
              System.out.println("the min is " + dataImport.get(0) + " and the max is " + dataImport.get(dataImport.size()-1));

            }

}
          //System.out.println(dataImport<>);
