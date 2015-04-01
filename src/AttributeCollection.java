
import java.io.DataInputStream;
import java.io.IOException;

/*
 * 
 *AttributeCollection  consists collection of Different Attribute 
/**Types CodeAttribute LineNumber Table etc
 *
 * @author Rasendran Kirushan
 */

/*
Collection of Attributes which can be 
either Code Attribute ,Line Number Table or Other Attribute
*/
public class AttributeCollection {

    //GetAttribute Collection
    public Attributes[] getAttr() {
        return attr;
    }
    public Attributes getAttr(int i)
    {
        return attr[i];
    }

    public int getCount() {
        return count;
    }

    private Attributes[]attr;
    private int count;
   
    /*
        Generates the Attribute Collection 
    */
    AttributeCollection(DataInputStream dis, ConstantPool cp) throws IOException, InvalidConstantPoolIndex, CodeParsingException {
        count=dis.readUnsignedShort();
        attr=new Attributes[count];
        for(int i=0;i<count;i++)
        {
            attr[i]=Attributes.returnType(dis,cp);
        }
        
    }
    
}
