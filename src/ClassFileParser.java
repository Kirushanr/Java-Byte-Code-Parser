import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Parses and displays a Java .class file.
 *
 * @author David Cooper
 */
public class ClassFileParser
{
    HashSet<String>method=new HashSet<String>();
    public static void main(String[] args)
    {
        if(args.length == 1)
        {
            try
            {
                ClassFile cf = new ClassFile(args[0]);
                //System.out.println(cf);
                System.out.println();
                cf.getCollection().print(cf.getConstantpool());
                System.out.println("\nChoose a number to view the call tree");
                Scanner userInput=new Scanner(System.in);
                int input=-1;
               //Take user input
                if(!(input>0 && input<=cf.getCollection().getCount()))
                {
                
                    input=userInput.nextInt();
                   
                }
                 userInput.close();
                 int temp=input-1;
                 //Get the method which chosen by the user 
                 Method callmethod=cf.getCollection().getArray(temp);
                 CallGraph cg=new CallGraph(cf);
                 
                 //Generate the Call Graph & prints the number of method 
                 //invocations
                System.out.println("Total Number of Methods: "
                                +cg.Graph(callmethod));
                //Print the number of Classes
                 System.out.println("Total Number of Classes :"
                                    +cg.getCounter());
               
            }
            catch(IOException e)
            {
                System.out.printf("Cannot read \"%s\": %s\n",
                    args[0], e.getMessage());
            }
            catch(ClassFileParserException e)
            {
                System.out.printf("Class file format error in \"%s\": %s\n",
                    args[0], e.getMessage());
            }
        }
        else
        {
            System.out.println("Usage: java ClassFileParser <class-file>");
        }
    }
}
