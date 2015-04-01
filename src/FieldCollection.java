
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
    FieldCollection represents the collection of fields
    inside the Class File
*/
class FieldCollection {

      private Fields[]fields;
        private int count;
    FieldCollection(DataInputStream dis,ConstantPool cp)
            throws IOException, InvalidConstantPoolIndex, CodeParsingException
    {
      
            count=dis.readUnsignedShort();
            fields=new Fields[count];
            for(int i=0;i<count;i++)
            {
                fields[i]=new Fields(dis,cp);
            }
            
    }
    
}
