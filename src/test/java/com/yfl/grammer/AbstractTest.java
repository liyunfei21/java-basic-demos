package com.yfl.grammer;

public abstract class AbstractTest {
    /**
     * 验证1：抽象类方法可以私有
     */
    void demo() {
    }

    /*
        抽象的（abstract）方法是否可同时是静态的（static）,是否可同时是本地方法（native），是否可同时被synchronized修饰？

        都不能。抽象方法需要子类重写，而静态的方法是无法被重写的，因此二者是矛盾的。本地方法是由本地代码（如C代码）实现的方法，
        而抽象方法是没有实现的，也是矛盾的。synchronized和方法的实现细节有关，抽象方法不涉及实现细节，因此也是相互矛盾的。
     */
    abstract void demo2();

    native void demo3();

    // error example
    // abstract static demo4();
    // abstract native demo5();
    // abstract synchronized demo6();


}
