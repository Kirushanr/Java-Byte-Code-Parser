

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rasendran Kirushan
 */
public class A {
    public static void method1() {
    method2();
    B obj = new B(42.0);
    obj.method4();
}
public static void method2() {
method3(42);
method3("Hello", 42);
}
public static void method3(int x) {}
public static void method3(String x, int y) {}
    
}
