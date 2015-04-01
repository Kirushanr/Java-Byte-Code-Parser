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
        Represents Method inside a class File 
    */
public class Method {

    /*
    Getter Methods 
    */
    public String getName() {
        return name;
    }

    public int getAccessFlag() {
        return accessFlag;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescIndex() {
        return descIndex;
    }

    public AttributeCollection getAttr() {
        return attr;
    }

    private String name;
    private int accessFlag;
    private int nameIndex;
    private int descIndex;
    private AttributeCollection attr;

    
    
    /*
        Constructor
    */
    Method(DataInputStream dis, ConstantPool constantPool, String substring) 
            throws IOException, InvalidConstantPoolIndex, CodeParsingException
    {
        name = substring;
        accessFlag =dis.readUnsignedShort();
        nameIndex = dis.readUnsignedShort();
        descIndex = dis.readUnsignedShort();
        attr = new AttributeCollection(dis, constantPool);
    }

    
    /*
        Resolves the return type of
        from descriptor index
    */
    public String ReturnType(String s) {
      
        String[] arr = s.split("\\(");
       
        String[] arr2 = arr[1].split("\\)");
        char[] para = arr2[0].toCharArray();
        String ret = "";
        for (int i = 0; i < para.length; i++) {
            char c = para[i];

            if (c == 'L') {
                c = para[i + 1];
                while (c != ';') 
                {
                   
                    c = para[i];
                    i++;
                    if (c != 'L') {
                        ret += c;
                    }

                }
                ret = ret.substring(0, ret.length() - 1);

            }

            switch (c) {

                case 'B':
                    ret += "byte";
                    break;
                case 'C':
                    ret += "char";
                    break;
                case 'D':
                    ret += "double";
                    break;
                case 'F':
                    ret += "float";
                    break;
                case 'I':
                    ret += "int";
                    break;
                case 'J':
                    ret += "long";
                    break;
                case 'S':
                    ret += "short";
                    break;
                case 'Z':
                    ret += "boolean";
                    break;
                case '[':
                    ret += "[]";
                    break;

            }
            ret += " ";

        }
        if (para.length > 0) {
            ret = ret.substring(0, ret.length() - 1);
        } else {
            ret = "void";
        }
        return ret;
    }

}
