package com.example.quiz

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.graphics.BitmapFactory
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool

import java.util.ArrayList
import java.util.Collections
import java.util.Random

import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class SelectJActivity : AppCompatActivity() {

    private var menuLabel: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val aa0=AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(
            AudioAttributes.CONTENT_TYPE_SPEECH).build()
        sp0= SoundPool.Builder().setAudioAttributes(aa0).setMaxStreams(2).build()
        snd3=sp0.load(this,R.raw.kouka,1)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selectj)

        val selectBtn1 : Button = findViewById(R.id.selectBtn1)
        val selectBtn2 : Button = findViewById(R.id.selectBtn2)
        val selectBtn3 : Button = findViewById(R.id.selectBtn3)
        val selectBtn4 : Button = findViewById(R.id.selectBtn4)
        val selectBtn5 : Button = findViewById(R.id.selectBtn5)
        val selectBtn6 : Button = findViewById(R.id.selectBtn6)
        val returnBtn2 : Button = findViewById(R.id.returnBtn2)
        val sucre : Button = findViewById(R.id.score_view)
        val change : Button = findViewById(R.id.change_view)

        selectBtn1.setOnClickListener {//どうぶつ
            sp0.play(snd3,1.0f,1.0f,0,0,1.0f)
            val intent = Intent(applicationContext, MainJActivity::class.java)
            startActivity(intent)
        }
        selectBtn2.setOnClickListener {//たべもの
            sp0.play(snd3,1.0f,1.0f,0,0,1.0f)
            val intent = Intent(applicationContext, EatJActivity::class.java)
            startActivity(intent)
        }
        selectBtn3.setOnClickListener {//スポーツ
            sp0.play(snd3,1.0f,1.0f,0,0,1.0f)
            val intent = Intent(applicationContext, SportsJActivity::class.java)
            startActivity(intent)
        }
        selectBtn4.setOnClickListener {//のりもの
            sp0.play(snd3,1.0f,1.0f,0,0,1.0f)
            val intent = Intent(applicationContext, VehicleJActivity::class.java)
            startActivity(intent)
        }
        selectBtn5.setOnClickListener {//ばしょ
            sp0.play(snd3,1.0f,1.0f,0,0,1.0f)
            val intent = Intent(applicationContext, FieldJActivity::class.java)
            startActivity(intent)
        }
        selectBtn6.setOnClickListener {//ようす

            sp0.play(snd3,1.0f,1.0f,0,0,1.0f)
            val intent = Intent(applicationContext, FeelJActivity::class.java)
            startActivity(intent)
        }
        returnBtn2.setOnClickListener { //タイトルに戻る
            sp0.play(snd3,1.0f,1.0f,0,0,1.0f)
            val intent = Intent(applicationContext, TitleActivity::class.java)
            startActivity(intent)
        }

        sucre.setOnClickListener {//トータルランク表示

            sp0.play(snd3,1.0f,1.0f,0,0,1.0f)
            val intent = Intent(applicationContext, ResultJActivity::class.java)
            startActivity(intent)
        }
        change.setOnClickListener {//変換

            sp0.play(snd3,1.0f,1.0f,0,0,1.0f)
            val intent = Intent(applicationContext, SelectActivity::class.java)
            startActivity(intent)
        }



    }


}




