package com.example.myapplication.learn

/**
 *  TODO 手写RxJava
 */

fun rxJavaTest() {
    create {
        123
    }.map {
        "【$this】"
    }.map {
        "@@$this@@"
    }.observer {
        println(this)
    }
}


fun <T> create(lambda: () -> T) = RxJavaCoreClassObject(lambda())

class RxJavaCoreClassObject<T>(val value: T)

fun <I, O> RxJavaCoreClassObject<I>.map(lambda: I.() -> O) = RxJavaCoreClassObject(lambda(value))

fun <I> RxJavaCoreClassObject<I>.observer(lambda: I.() -> Unit) = lambda(value)
