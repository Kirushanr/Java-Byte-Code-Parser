
import java.io.DataInputStream;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rasendran Kirushan
 */



/*
    Represents Interfaces inside a 
    class File
*/
public class InterfaceCollection {
   private int[]array;
   private int count;
    
   public InterfaceCollection(DataInputStream dis) throws IOException
   {
        count=dis.readUnsignedShort();
        array=new int[count];
        for(int i=0;i<count;i++)
        {
            array[i]=dis.readUnsignedShort();
        }
        
   }
}
