
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rasendran Kirushan
 */

/*Represents CodeAttribute 
    of Method
*/
class CodeAttribute extends Attributes {

    public ArrayList<Instruction> getList() {
        return List;
    }

    public void setList(ArrayList<Instruction> List) {
        this.List = List;
    }

    private int maxStack;
    private int maxLocal;
    private long length;
    private byte[]code;
    private int eLength;
    private byte []expTable;
    private AttributeCollection attrbutes;
    private ArrayList<Instruction>List;
    public CodeAttribute(DataInputStream dis, int temp, ConstantPool cp)
            throws IOException, InvalidConstantPoolIndex, CodeParsingException {
          nameIndex=temp;
          attrLength=(long)dis.readUnsignedShort()<< 16|dis.readUnsignedShort();
          maxStack=dis.readUnsignedShort();
          maxLocal=dis.readUnsignedShort();
          length=(long)dis.readUnsignedShort()<<16|dis.readUnsignedShort();
          code=new byte[(int)length];
          dis.readFully(code);
          eLength=dis.readUnsignedShort();
          expTable=new byte[eLength*8];
          dis.readFully(expTable);
          attrbutes=new AttributeCollection(dis, cp);
          AddInstruction();        
    }
    /*
        Stores the Instructions of
        particular code 
    */
    private ArrayList<Instruction> AddInstruction()
                throws CodeParsingException 
    {
       int offset=0;
       List=new ArrayList<Instruction>();
       do
       {
           Instruction ins=new Instruction(code, offset);
           offset+=ins.getSize();
           List.add(ins);
           
           
       }while(offset<code.length);
            return List;
    }
    
}
