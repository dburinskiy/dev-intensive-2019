package ru.skillbranch.devintensive.models

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
        return if (question.answers.contains(answer)) {
            question = question.nextQuestion()
            "Good - it is right answer!\n${question.question}" to status.color
        } else {
            status = status.nextStatus()
            "Bad - it is wrong answer!\n${question.question}" to status.color
        }
    }


    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 255, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("What is my name?", listOf("бендер", "bender")) {
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("What is my profession?", listOf("сгибальщик", "bender")) {
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("What material I was made?", listOf("метал", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("When I was made?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("What is my id number?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("It is end. I don't have questions", listOf()) {
            override fun nextQuestion(): Question = NAME
        };

        abstract fun nextQuestion(): Question
    }
}