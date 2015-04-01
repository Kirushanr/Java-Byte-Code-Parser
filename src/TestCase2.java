/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Rasendran Kirushan
 */
public class TestCase2 {
    
    public TestCase2()
    {
        A();
    }
    void A()
    {
        B();
    }
    void B()
    {
     C();
    }
    void C()
    {
        A();
    }
    
}
