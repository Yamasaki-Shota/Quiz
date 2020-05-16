package com.example.quiz

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import android.content.DialogInterface
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

import java.util.ArrayList
import java.util.Collections
import java.util.Random

import kotlinx.android.synthetic.main.activity_main.*

var mp:MediaPlayer? = null

class TitleActivity : AppCompatActivity() {

    private var titleLabel: TextView? = null
    private var login_startBtn1: Button? = null
    private var login_startBtn2: Button? = null

    private var rightAnswer: String? = null
    private var rightAnswerCount = 0
    private var quizCount = 1

    internal var quizArray = ArrayList<ArrayList<String>>()


    override fun onCreate(savedInstanceState: Bundle?) {
        val aa0= AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(
            AudioAttributes.CONTENT_TYPE_SPEECH).build()
        sp0= SoundPool.Builder().setAudioAttributes(aa0).setMaxStreams(2).build()
        snd0=sp0.load(this,R.raw.kouka,1)

        //musicの読み込み
        if (mp == null) {
            mp = MediaPlayer.create(applicationContext,R.raw.main)
            mp?.isLooping=true
            //  mp?.isLooping(true)
            mp?.start()
            //   mp?.stop()
            // mp?.reset()
            //mp?.release()
        }



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title)

        titleLabel = findViewById(R.id.titleLabel)
        val login_startBtn1 : Button = findViewById(R.id.login_startBtn1)
        val login_startBtn2 : Button = findViewById(R.id.login_startBtn2)


        login_startBtn1.setOnClickListener {
            sp0.play(snd0,1.0f,1.0f,0,0,1.0f)
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
        login_startBtn2.setOnClickListener {
            sp0.play(snd0,1.0f,1.0f,0,0,1.0f)
            val intent = Intent(applicationContext, SelectActivity::class.java)
            startActivity(intent)
        }


    }



}




