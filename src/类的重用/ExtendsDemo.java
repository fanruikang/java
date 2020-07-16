package 类的重用;

/**
 * 继承
 * 方法覆盖：实例方法看声明类型；类方法看引用类型，覆盖不生效；final方法子类不能覆盖，编译报错
 * @author: fanruikang
 * @date: 2020-07-02 
 */
public class ExtendsDemo {
    public static void main(String[] args) {
        A a = new B("a");
        B b = new B("b");
        a.a1();
        b.a1();
        a.b1();
        b.b1();
    }
}

class A {
    A(String a) {

    }
     static void a1() {
        System.out.println("a1");
    }
      void b1() {
        System.out.println("b1");
    }
      final void c1() {
        System.out.println("c1");
    }
}

class B extends A{
    B(String b) {
        super(b); // 不主动调用，jvm会隐式调用父类无参构造参数，如果父类有有参构造参数，jvm就不会为其自动生成无参构造参数，此时就会报错
    }
     static void a1() {
        System.out.println("b:a1");
    }
      void b1() {
        System.out.println("b:b1");
    }
//       void c1() {
//        System.out.println("b:c1");
//    }
}