package com.example.myapplication.learn.ext

fun <T> Iterable<T>.randomToGetFirst() = shuffled().first()

fun <T> Iterable<T>.randomToGetFirstToPrintln() = println(shuffled().first())