package com.example.quiz
import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import androidx.appcompat.app.AlertDialog


class ResultJActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        val aa0= AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(
            AudioAttributes.CONTENT_TYPE_SPEECH).build()
        sp0= SoundPool.Builder().setAudioAttributes(aa0).setMaxStreams(2).build()
        snd0=sp0.load(this,R.raw.kouka,1)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

       /* val resultLabel : TextView = findViewById(R.id.resultLabel)
        val t : TextView = findViewById(R.id.t)
        val s : TextView = findViewById(R.id.s)*/
        val result : TextView = findViewById(R.id.result)
        val rankLabel : TextView = findViewById(R.id.rankLabel)
        val answerRate : TextView = findViewById(R.id.answerRate)
        val totalScoreLabel : TextView = findViewById(R.id.totalScoreLabel)
        val returnBtn1 : Button = findViewById(R.id.returnBtn1)

        // 正解数を取得
        val score = intent.getIntExtra("RIGHT_ANSWER_COUNT", 0)

        // トータルスコアの読み出し
        var prefs = getSharedPreferences("quizApp", Context.MODE_PRIVATE)
        var totalScore  = prefs.getInt("totalScore", 0)

        //問題数
        val questionI = intent.getIntExtra("quiz_count",0)
        val question = questionI.toDouble()
        var totalQuestionI = prefs.getInt("totalQuestion", 0)


      //  totalQuestion = 0.0

        // トータルスコアに今回のスコアを加算
        totalScore += score
        totalQuestionI += questionI
        var totalQuestion = totalQuestionI.toDouble()
        var accuracy = ((totalScore.div(totalQuestion)).times(100)).toInt()

       /* s.text = "$totalScore" //score under
        t.text = "$totalQuestion"*/

       /* totalScore = 0
        totalQuestionI = 0*/



        // TextViewに表示する
        if(question == 0.0) {
            var rank = when{
                (90.0<=accuracy)                    -> "S"
                (80.0<=accuracy && accuracy < 90.0) -> "A"
                (60.0<=accuracy && accuracy < 80.0) -> "B"
                (40.0<=accuracy && accuracy < 60.0) -> "C"
                (20.0<=accuracy && accuracy < 40.0) -> "D"
                else -> "F"
            }
            rankLabel.text = rank
            Dialog()
        }





          //  resultLabel.text = "$score / 5"
        if(question >= 1.0) {
            var  rank = when(score){
                5 -> "S"
                4 -> "A"
                3 -> "B"
                2 -> "C"
                1 -> "D"
                else -> "F"
            }
            rankLabel.text = rank
            result.text = "RESULT"
            answerRate.text ="TOTAL CORRECT ANSWER RATE"
            totalScoreLabel.text = "$accuracy % "
        }


        // トータルスコアを保存
        var editor = prefs.edit()
        editor.putInt("totalScore", totalScore)
        editor.apply()

        // 問題数を保存
        var editorQ = prefs.edit()
        editorQ.putInt("totalQuestion", totalQuestionI)
        editorQ.apply()

        returnBtn1.setOnClickListener{
            sp0.play(snd0,1.0f,1.0f,0,0,1.0f)
            val intent = Intent(applicationContext, TitleActivity::class.java)
            startActivity(intent)
        }
    }
    fun Dialog(){
        // ダイアログを作成
        val builder = AlertDialog.Builder(this)
        builder.setTitle("RANK :  [%]")

        val text ="""
            |S : 90～100
            |A : 80～89
            |B : 60～79
            |C : 40～59
            |D : 20～39
            |F : ～19
            """.trimMargin()

        builder.setMessage(text)
        builder.setNegativeButton("cancel",null)

        builder.setCancelable(false)
        builder.show()
    }

    fun returnTop(view: View) {
        sp0.play(snd0,1.0f,1.0f,0,0,1.0f)
        val intent = Intent(applicationContext, SelectJActivity::class.java)
        startActivity(intent)
    }


}
