package com.study.testuses

import java.util.*
import kotlin.math.max

/**
 * <p>
 * date: 2020/8/20 9:01
 * @author syd
 * @version 1.0
 */

fun main() {
//    practiceList()
//    practiceMap()
    forUse()
}


fun practiceList() {
    val list = listOf("Apple", "Banana", "Orange", "potato")
    for (fruit in list) {
        println("fruit:$fruit")
    }

    val fruit = list.maxBy { it.length }
    println(fruit)

    val lambda = { fruit: String -> fruit.length }
    val fruit1 = list.maxBy(lambda)
    println("fruit1:" + fruit1)

    val fruit2 = list.maxBy({ fruit: String -> fruit.length })

    println("fruit2:" + fruit2)

    val fruit3 = list.maxBy { fruit: String -> fruit.length }
    println("fruit3:" + fruit3)

    val fruit4 = list.maxBy { fruit -> fruit.length }

    println("fruit4:" + fruit4)

    val fruit5 = list.maxBy { it.length }
    println(fruit5)


}


fun practiceMap() {
    val list = listOf("Apple", "Banana", "Orange", "jujube")

    val lambda = { fruit: String -> "SYD:" + fruit }

    val listNew = list.map(lambda)

    for (fruit in listNew) {
        println(fruit)
    }

    val listNew1 = list.map { it + "===end" }

    for (fruit in listNew1) {
        println(fruit)
    }
}

fun largeNum(a: Int, b: Int): Int {
    return max(a, b)
}

fun largeNum1(a: Int, b: Int) = max(a, b)

fun forUse() {
    for (i in 10..20 step 2) {
        println(i)
    }

    for (i in 10 until 20 step 2) {
        println("i:" + i)
    }

    val lambda = { a: String, b: String -> a + b }

    val list = listOf("Apple", "Banana", "Potato", "Hello")
    val max = list.maxBy { it.length }
    println(max)

    val list1 = list.map { it.toUpperCase(Locale.ROOT) }
    for (fruit in list1){
        println(fruit)
    }

}