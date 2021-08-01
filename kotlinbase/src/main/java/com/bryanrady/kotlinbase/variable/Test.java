package com.bryanrady.kotlinbase.variable;

public class Test {

    public static void main(String[] args) {
        new Property().printListSize();

        String userName = new Function().getUserName();
        System.out.println("用户名字为: " + userName);
    }

}
