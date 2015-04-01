
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

/*Attributes that are used in class file format ,Code Attribute field Info
Method info  represented by these class which is further extended by 
CodeAttribute , LineNumber Classes*/
public abstract class Attributes {

    public int getNameIndex() {
        return nameIndex;
    }

    public long getAttrLength() {
        return attrLength;
    }

    protected int nameIndex;
    protected long attrLength;
    
    /*To Determine which type of the class file attribute  
    (E.g Code/Constant Value/Line Number Table)*/
     public static Attributes returnType(DataInputStream dis, ConstantPool cp)
             throws IOException, InvalidConstantPoolIndex, CodeParsingException
     {
            int temp=dis.readUnsignedShort();
            String s=((ConstantUtf8)cp.getEntry(temp)).getBytes();
            if(s.equals("Code"))
            {
                return new CodeAttribute(dis,temp,cp);
            }
            else
            {
                return new LineNumber(dis,temp);
            }
    }
        
    
    
}
