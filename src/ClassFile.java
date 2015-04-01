import java.io.*;
import java.util.HashMap;

/**
 * Parses and stores a Java .class file. Parsing is currently incomplete.
 *
 * @author David Cooper
 */
public class ClassFile
{
    private String filename;
    private long magic;
    private int minorVersion;
    private int majorVersion;
    private ConstantPool constantPool;
    private int accessFlags;
    private int thisClass;
    private int superClass;
    private InterfaceCollection interfaces;
    private FieldCollection fields;
    private MethodCollection methods;
    private AttributeCollection attributes;
    private HashMap<String,Method>methodNames;
    
    
    
    
    // ...

    /**
     * Parses a class file an constructs a ClassFile object. At present, this
     * only parses the header and constant pool.
     */
    public ClassFile(String filename) throws ClassFileParserException,
                                             IOException
    {
        DataInputStream dis =
            new DataInputStream(new FileInputStream(filename));

        this.filename = filename;
        magic = (long)dis.readUnsignedShort() << 16 | dis.readUnsignedShort();
        minorVersion = dis.readUnsignedShort();
        majorVersion = dis.readUnsignedShort();
        constantPool = new ConstantPool(dis);
        accessFlags=dis.readUnsignedShort();
        thisClass=dis.readUnsignedShort();
        superClass=dis.readUnsignedShort();
        
        interfaces=new InterfaceCollection(dis);
        fields=new FieldCollection(dis,constantPool);
        methods=new MethodCollection(dis,constantPool,
                filename.substring(0,filename.length()-6));
        attributes=new AttributeCollection(dis, constantPool);
        methodNames=new HashMap<String,Method>(); 
        
        /*create a hasmap array with method object
        and its string representation*/
        for(int i=0;i<methods.getCount();i++)
        {
            Method name=methods.getArray(i);
            String s=name.getName()+"."+((ConstantUtf8)
                constantPool.getEntry(name.getNameIndex())).getBytes()
                   +"("+name.ReturnType(((ConstantUtf8)
                  constantPool.getEntry(name.getDescIndex())).getBytes())+")";
            methodNames.put(s,name);
        }
    }
    public MethodCollection getCollection()
    {
        return methods;
    }
    public ConstantPool getConstantpool()
    {
        return constantPool;
    }
    public HashMap<String,Method>getMethodCollection()
    {
        return methodNames;
    }
    public String getClassName()
    {
        return filename.substring(0,filename.length()-6);
    }
    
    
    /** Returns the contents of the class file as a formatted String. */
//    public String toString()
//    {
////        return String.format(
////            "Filename: %s\n" +
////            "Magic: 0x%08x\n" +
////            "Class file format version: %d.%d\n\n" +
////            "Constant pool:\n\n%s",
////            filename, magic, majorVersion, minorVersion, constantPool);
//        return"";
//    }
}

