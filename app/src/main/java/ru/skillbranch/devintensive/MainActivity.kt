package ru.skillbranch.devintensive

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.KeyEvent
import android.view.KeyboardShortcutGroup
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.models.Bender
import androidx.core.content.ContextCompat.getSystemService
import android.view.inputmethod.EditorInfo
import ru.skillbranch.devintensive.extensions.hideKeyboard


class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var benderImage: ImageView
    lateinit var textTxt: TextView
    lateinit var messageEt: EditText
    lateinit var sendBtn: ImageView
    lateinit var benderObj: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //benderImage = findViewById(R.id.iv_bander) as ImageView
        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send

        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        val userText = savedInstanceState?.getString("USER_TEXT") ?: ""

        benderObj = Bender(status = Bender.Status.valueOf(status), question = Bender.Question.valueOf(question))

        val (r, g, b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)

        Log.d("M_MainActivity", "onCreate: $status $question")

        textTxt.text = benderObj.askQuestion() //textTxt.setText(askQuestion()
        messageEt.setText(userText)
        sendBtn.setOnClickListener(this)

        messageEt.setOnEditorActionListener { v: TextView, i: Int, k: KeyEvent? ->
            if (i === EditorInfo.IME_ACTION_DONE) {

                sendMessageToBender()
                hideKeyboard()
                true
            }
            false
        }

    }

    override fun onRestart() {
        super.onRestart()
        Log.d("M_MainActivity", "onRestart: test")
    }

    override fun onStart() {
        super.onStart()
        Log.d("M_MainActivity", "onStart:  test")
    }

    override fun onResume() {
        super.onResume()
        Log.d("M_MainActivity", "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d("M_MainActivity", "onPause: test")
    }

    override fun onStop() {
        super.onStop()
        Log.d("M_MainActivity", "onStop: test")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_MainActivity", "onDestroy: test")
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putString("STATUS", benderObj.status.name)
        outState?.putString("QUESTION", benderObj.question.name)
        outState?.putString("USER_TEXT", messageEt.text.toString())

        Log.d("M_MainActivity", "onSaveInstanceState: ${benderObj.status.name}")
        Log.d("M_MainActivity", "onSaveInstanceState: ${benderObj.question.name}")
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.iv_send) {
            sendMessageToBender()
        }
    }

    fun sendMessageToBender() {
        val (phrase, color) = benderObj.listenAnswer(messageEt.text.toString())
        messageEt.setText("")
        val (r, g, b) = color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
        textTxt.text = phrase

    }
}
