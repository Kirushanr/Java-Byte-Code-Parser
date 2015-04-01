
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
    Represents 
    all other Attributes including LineNumber Table , Constant value 
    Local variable table
*/
class LineNumber extends Attributes {

    private byte[]table;
    public LineNumber(DataInputStream dis, int temp) throws IOException {
        nameIndex=temp;
        attrLength=(long)dis.readUnsignedShort()<<16|dis.readUnsignedShort();
        table=new byte[(int)attrLength];
        dis.readFully(table);
    
    }
    
}
