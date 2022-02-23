package com.game.core

class Logger(
    private val tag: String,
    private val isDebug: Boolean = true
) {
    fun log(msg: String){
        if (!isDebug){

        }else{
            printLogd(tag , msg)
        }
    }

    companion object Factory{
        fun buildDebug(tag: String): Logger{
            return Logger(
                tag , false
            )
        }
    }
}

fun printLogd(tag: String? , msg: String?){
    println("$tag: $msg")
}