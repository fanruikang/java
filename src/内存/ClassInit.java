package 内存;

/**
 *@Title:
 *@Description: 类变量初始化顺序
 *@Copyright: 2019-10-25 
 *@Author: FanRuikang(樊瑞康)
 *@Version:1.0
 */
public class ClassInit {
    static Table table = new Table();
    static Cupboard cupboard = new Cupboard();
    public static void main(String[] args) {
        Price.print();
        System.out.println("main creating new Cupboard()");
        new Cupboard();
        System.out.println("main creating new Cupboard()");
        new Cupboard();
        table.f2(1);
        cupboard.f3(1);
    }

}

class Price {
    static Price INSTANCE = new Price(2.8);
    static double initPrice = 20;
    double currentPrice;
    public Price(double discount){
        currentPrice = initPrice - discount;
    }

    static void print() {
        Price p = new Price(2.8);
        System.out.println("p.currentPrice = " + p.currentPrice);
        p = INSTANCE;
        System.out.println("p.currentPrice = " + initPrice);
        System.out.printf(String.valueOf(INSTANCE.currentPrice));
    }
}


// housekeeping/StaticInitialization.java
// Specifying initial values in a class definition

class Bowl {
    Bowl(int marker) {
        System.out.println("Bowl(" + marker + ")");
    }

    void f1(int marker) {
        System.out.println("f1(" + marker + ")");
    }
}

class Table {
    static Bowl bowl1 = new Bowl(1);
    {
        System.out.println("table not static");
    }
    static {
        System.out.println("table static");
    }
    Table() {
        System.out.println("Table()");
        bowl2.f1(1);
    }

    void f2(int marker) {
        System.out.println("f2(" + marker + ")");
    }

    static Bowl bowl2 = new Bowl(2);
}

class Cupboard {
    Bowl bowl3 = new Bowl(3);
    static Bowl bowl4 = new Bowl(4);

    Cupboard() {
        System.out.println("Cupboard()");
        bowl4.f1(2);
    }

    void f3(int marker) {
        System.out.println("f3(" + marker + ")");
    }

    static Bowl bowl5 = new Bowl(5);
}


