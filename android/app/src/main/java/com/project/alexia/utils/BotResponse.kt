package com.project.alexia.utils

import com.project.alexia.utils.Constants.OPEN_GOOGLE
import com.project.alexia.utils.Constants.OPEN_SEARCH
import java.util.*

object BotResponse {

    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val message = _message.lowercase(Locale.getDefault())

        return when {

            //Hello
            message.contains("hello") -> {
                when (random) {
                    0 -> "Hello there! Nice to meet you"
                    1 -> "Hi! So nice to meet you"
                    2 -> "Buongiorno! Nice to meet you"
                    else -> "error" }
            }

            //How are you?
            message.contains("how are you") -> {
                when (random) {
                    0 -> "I'm doing fine:) Thanks! Would you like me recommend some songs?"
                    1 -> "I'm hungry:( Would you like me recommend some songs?"
                    2 -> "Pretty good:) Would you like me recommend some songs?"
                    else -> "error"
                }
            }

            //Open Google
            message.contains("open") && message.contains("google")-> {
                OPEN_GOOGLE
            }

            //Search on the internet
            message.contains("search")-> {
                OPEN_SEARCH
            }

            //Song request
            message.contains("sure") || message.contains("ok") || message.contains("yes") || message.contains("fine") -> {
                "How are you feeling today ?"
            }

//            //Song request
//            message.contains("happy") || message.contains("glad") ||
//                    message.contains("LOL") || message.contains("excited") ||
//                    message.contains("Amazing") || message.contains("good")-> {
//                OPEN_SEARCH_HAPPY
//            }

//            //Song request
//            message.contains("sad") || message.contains("disappoint") || message.contains("not")
//                    || message.contains("low") -> {
//                OPEN_SEARCH_SAD
//            }

            //When the programme doesn't understand...
            else -> {
                when (random) {
                    0 -> "Please ask something else"
                    1 -> "Try asking me something different"
                    2 -> "Is there anything else I can do?"
                    else -> "error"
                }
            }
        }
    }
}