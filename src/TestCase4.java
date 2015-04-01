/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Rasendran Kirushan
 */
public class TestCase4 {

    public TestCase4() {
        
        TestCase5 t=new TestCase5() {

            @Override
            void v() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        A(t);
    }
    
    void A(TestCase5 t)
    {
    
    }
    
}
