
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
    Represents filed info of 
the Class file
*/
public class Fields {

    int flag;
    int nameindex;
    int descindex;
    private AttributeCollection attr;
    Fields(DataInputStream dis, ConstantPool cp)
            throws IOException, InvalidConstantPoolIndex, CodeParsingException {
        flag=dis.readUnsignedShort();
        nameindex=dis.readUnsignedShort();
        descindex=dis.readUnsignedShort();
        attr=new AttributeCollection(dis,cp);
           
    }
    
}
