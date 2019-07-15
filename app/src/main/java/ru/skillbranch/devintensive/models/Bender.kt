package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.isAnyDigits
import ru.skillbranch.devintensive.extensions.isDigitsOnly


class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {


    fun askQuestion(): String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        if(question == Question.IDLE) {
            return "${question.question}" to status.color
        }
        var validation = question.validate(answer)
        if (validation.first) {

            return if (answer.isNotEmpty() && question.answers.contains(answer)) {

                question = question.nextQuestion()
                "Отлично - ты справился\n${question.question}" to status.color

            } else {

                status = status.nextStatus()
                if (status == Status.NORMAL) {
                    question = Question.NAME
                    "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
                } else {
                    "Это неправильный ответ\n${question.question}" to status.color
                }

            }

        } else {
            return "${validation.second}\n${question.question}" to status.color
        }

    }


//    Валидация
//
//    Question.NAME ->
//
//    Question.PROFESSION ->
//
//    Question.MATERIAL ->
//
//    Question.BDAY ->
//
//    Question.SERIAL ->
//
//    Question.IDLE -> //игнорировать валидацию

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>, val errorMsg: String) {
        NAME("Как меня зовут?", listOf("бендер", "bender", "Bender"), "Имя должно начинаться с заглавной буквы") {
            override fun nextQuestion(): Question = PROFESSION
            override fun validate(answer: String): Pair<Boolean, String> {
                if (answer.isNotEmpty() && answer.trimIndent().take(1).toCharArray()[0].isUpperCase()) {
                    return true to ""
                }

                return false to errorMsg
            }
        }
        ,
        PROFESSION(
            "Назови мою профессию?",
            listOf("сгибальщик", "bender"),
            "Профессия должна начинаться со строчной буквы"
        ) {
            override fun nextQuestion(): Question = MATERIAL

            override fun validate(answer: String): Pair<Boolean, String> {
                if (answer.isNotEmpty() && answer.trimIndent().take(1).toCharArray()[0].isLowerCase()) {
                    return true to ""
                }

                return false to errorMsg
            }
        },
        MATERIAL(
            "Из чего я сделан?",
            listOf("металл", "дерево", "metal", "iron", "wood"),
            "Материал не должен содержать цифр"
        ) {
            override fun nextQuestion(): Question = BDAY
            override fun validate(answer: String): Pair<Boolean, String> {
                if (answer.isNotEmpty() && answer.isAnyDigits()) {
                    return true to ""
                }

                return false to errorMsg
            }

        },
        BDAY("Когда меня создали?", listOf("2993"), "Год моего рождения должен содержать только цифры") {
            override fun nextQuestion(): Question = SERIAL
            override fun validate(answer: String): Pair<Boolean, String> {
                if (answer.isNotEmpty() && answer.isDigitsOnly()) {
                    return true to ""
                }

                return false to errorMsg
            }
        },
        SERIAL("Мой серийный номер?", listOf("2716057"), "Серийный номер содержит только цифры, и их 7") {
            override fun nextQuestion(): Question = IDLE
            override fun validate(answer: String): Pair<Boolean, String> {
                if (answer.isNotEmpty() && answer.isDigitsOnly() && answer.length <= 7) {
                    return true to ""
                }

                return false to errorMsg
            }
        },
        IDLE("На этом все, вопросов больше нет", listOf(), "") {
            override fun nextQuestion(): Question = NAME
            override fun validate(answer: String): Pair<Boolean, String> {
                return true to ""
            }
        };

        abstract fun nextQuestion(): Question
        abstract fun validate(answer: String): Pair<Boolean, String>
    }
}