package com.example.myapplication.learn.annotation;

/**
 * Created by zl on 2021/11/13.
 */

public class UseKt {

    public static void main(String[] args) {
        //TODO JvmNames
        JNA.show();

        //TODO JvmField
        System.out.println(new JvmFieldAnnotation().names);

        //TODO JvmOverloads
        JvmOverloadsAnnotationKt.show("zl");

        //TODO JvmStatic
        System.out.println(JvmStaticAnnotation.TARGET);
        JvmStaticAnnotation.showAction("zl");
    }

}
