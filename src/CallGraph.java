import java.io.IOException;
import java.util.Stack;

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
 Generates the method call graph
 */
public class CallGraph {

    public int getCount() {
        return count;
    }

    public int getCounter() {
        return counter;
    }

    private int h; //Height of the call graph
    private ClassFile name;
    Stack<Method> stack;
    int count = 0;
    int counter = 0;

    public CallGraph(ClassFile c) {
        name = c;
    }

    /*
        
     */
    public int Graph(Method m) throws InvalidConstantPoolIndex {
        stack = new Stack<Method>();
        recursive(m);
        return count;
    }

    private void recursive(Method m) throws InvalidConstantPoolIndex {
        String space = "";
        //Creates space  to give appropriate call tree
        for (int i = 0; i < h; i++) {
            space = space + "____";
        }

        String s = m.getName() + "." + ((ConstantUtf8) name.getConstantpool()
                .getEntry(m.getNameIndex())).getBytes()
               + "(" + m.ReturnType(((ConstantUtf8)name.getConstantpool()
                .getEntry(m.getDescIndex())).getBytes()) + ")";
        
        count++;
        System.out.println(space + "" + s);

        /*Gets the attribute attribute information out of the particula
         Method
         */
        AttributeCollection codeAttribute = m.getAttr();

        for (int v = 0; v < codeAttribute.getCount(); v++) {
            if (codeAttribute.getAttr(v) instanceof CodeAttribute) {
                CodeAttribute c = (CodeAttribute) codeAttribute.getAttr(v);

                for (int j = 0; j < c.getList().size(); j++) {

                    Instruction ins = c.getList().get(j);
                    if (ins.getOpcode().getMnemonic().equals("invokestatic")
                            || ins.getOpcode().getMnemonic()
                            .equals("invokevirtual")
                            || ins.getOpcode().getMnemonic()
                            .equals("invokeinterface")
                            || ins.getOpcode().getMnemonic()
                            .equals("invokespecial")) {
                        byte[] bytes = ins.getExtraBytes();
                        int index = bytes[0] << 8 | bytes[1];
                        ConstantMethodRef method = (ConstantMethodRef) 
                                name.getConstantpool().getEntry(index);

                         //Check the method name of the class else 
                        //if different class  
                        if (!(method.getClassName().
                                equals(name.getClassName())))
                        {
                            try {
                                counter++;
                                 ClassFile cf = new ClassFile
                                            (method.getClassName() + ".class");
                                 
                                Method temp = cf.getCollection().getArray(0);
                                String value = method.getClassName() + "."
                                      + method.getName() + "("
                                      + temp.ReturnType(method.getType()) + ")";
                                temp = (cf.getMethodCollection()).get(value);

                                CallGraph c2 = new CallGraph(cf);
                                count += c2.Graph(temp);

                            } catch (IOException e) {
                                System.out.println(method.getClassName() 
                                        + "[missing]");
                            } catch (ClassFileParserException e) {
                                e.printStackTrace();
                            }
                        } else {
                            h++;
                            stack.push(m);
                            Method m1 = SearchMethod(method);
                           //Check the method is abstract
                            if (m1.getAccessFlag()> 1024) {
                                System.out.println(space + "" + s 
                                        + "(abstract)");
                            } else {
                                if (stack.contains(m1)) {
                                    System.out.println(space + "" + 
                                            s + "(recursive)");
                                } else {
                                    //Recurse 
                                    recursive(m1);
                                }

                            }
                            stack.pop();
                            h--;

                        }

                    }

                }
            }
        }

    }

    //Get the Method Object 
    private Method SearchMethod(ConstantMethodRef method) {
        Method temp = name.getCollection().getArray(0);

        String value = method.getClassName() + "." + method.getName() + "("
                + temp.ReturnType(method.getType()) + ")";

        temp = (name.getMethodCollection()).get(value);
        return temp;
    }

}
