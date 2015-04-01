
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
    Represents Collection of methods
*/
public class MethodCollection {

    public int getCount() {
        return count;
    }

    public Method[] getArray() {
        return array;
    }
    public Method getArray(int i)
    {
        return array[i];
    }
    private int count;
    private Method []array;
    
    
    

    MethodCollection(DataInputStream dis, ConstantPool constantPool, 
            String substring) throws IOException, InvalidConstantPoolIndex, 
                                CodeParsingException 
    {
            count=dis.readUnsignedShort();
            array=new Method[count];
            for(int i=0;i<count;i++)
            {
                //implement
                array[i]=new Method(dis,constantPool,substring);
            }
        
      }
    
    
    
    /*
        Prints all the methods that are in a 
        particular class file
    */
     public void print(ConstantPool cp) throws InvalidConstantPoolIndex
    {
        for(int k=0;k<count;k++)
        {
            Method value=array[k];
            
            
            String parameter=value.ReturnType(((ConstantUtf8)
                    cp.getEntry(value.getDescIndex())).getBytes());
            int count=1+k;
            System.out.printf(count+": "+value.getName() +
                    ".%s("+parameter+")\n",((ConstantUtf8)
                            cp.getEntry(value.getNameIndex())).getBytes());
        }
    }
    
}
