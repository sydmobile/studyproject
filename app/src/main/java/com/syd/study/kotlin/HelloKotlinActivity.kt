package com.syd.study.kotlin

import java.util.*

/**
 * 说明：Kotlin
 * <p>
 * date: 2019/9/30 17:16
 * @author syd
 * @version 1.0
 */
class HelloKotlinActivity {
    var languageNmae = "Kotlin"
    fun test() {
        var uppercase = languageNmae.toUpperCase(Locale.CHINA)
        var languageRange: String? = null

        var count = 30
        if (count==42){
            println("Hello Kotlin!")
        }else{
            println("I am coming Kotlin!")
            println("I am")
        }

        var answerString = if(count ==42){
           " I have the answer."
        }else if(count>35){
            "The answer is close."
        }else{
            "The answer eludes me."
        }
        println(answerString)

        val whenString = when{
            count== 42->"I have the answer."
            count>35-> "The answer is close."
            else -> "The answer eludes me."
        }
        println(whenString)

    }

}