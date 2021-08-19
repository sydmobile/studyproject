package com.study.study_module.kotlin

/**
 * <p>
 * date: 2020/8/28 8:54
 * @author syd
 * @version 1.0
 */

var str: String? = null

val person: Person? = null

fun main() {
    var a: String
    val c = str?.length
    println(c)
    val aa = person?.add(1, 2)
    println(aa)

    val cc = person?.add(1,2)?:4

    println(cc)
    

}




