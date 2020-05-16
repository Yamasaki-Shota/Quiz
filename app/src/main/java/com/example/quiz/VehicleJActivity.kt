package com.example.quiz

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
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


class VehicleJActivity : AppCompatActivity() {

    private var countLabel: TextView? = null
    private var questionLabel: TextView? = null
    private var answerBtn1: Button? = null
    private var answerBtn2: Button? = null
    private var answerBtn3: Button? = null
    private var answerBtn4: Button? = null
    private var rightAnswer: String? = null
    private var rightAnswerCount = 0
    private var quizCount = 1


    internal var quizArray = ArrayList<ArrayList<String>>()


    internal var quizData = arrayOf(
        // {"English", "Bingo Japanese" ,"image"}
        arrayOf("ship","ふね"),
        arrayOf("train","でんしゃ"),
        arrayOf("balloon","ききゅう"),
        arrayOf("rocket","ろけっと"),
        arrayOf("yacht","よっと"),
        arrayOf("tricycle","さんりんしゃ"),
        arrayOf("cable car","けーぶるかー"),
        arrayOf("police car","ぱとかー"),
        arrayOf("unicycle","いちりんしゃ"),
        arrayOf("truck","とらっく"),
        arrayOf("airship","ひこうせん"),
        arrayOf("bicycle","じてんしゃ"),
        arrayOf("car","くるま"),
        arrayOf("ufo","ゆーふぉー"),
        arrayOf("ambulance","きゅうきゅうしゃ"),
        arrayOf("bus","ばす"),
        arrayOf("cart","にぐるま"),
        arrayOf("canoe","かぬー"),
        arrayOf("bulldozer","ぶるどーざー")
    )

    internal var animal =
        arrayOf("ship","train","balloon","rocket","yacht","tricycle","cable car","police car","unicycle","truck","airship","bicycle","car","ufo"
            ,"ambulance","bus","cart","canoe","bulldozer")


    override fun onCreate(savedInstanceState: Bundle?) {
        val aa0= AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(
            AudioAttributes.CONTENT_TYPE_SPEECH).build()
        sp0= SoundPool.Builder().setAudioAttributes(aa0).setMaxStreams(2).build()
        snd0=sp0.load(this,R.raw.atari,1)
        snd1=sp0.load(this,R.raw.hazure,1)
        snd2=sp0.load(this,R.raw.fanfare,1)
        snd3=sp0.load(this,R.raw.kouka,1)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //画像の読み込み

        //image_view_image.setImageResource(R.drawable.bear)

        countLabel = findViewById(R.id.countLabel)
        questionLabel = findViewById(R.id.questionLabel)
        answerBtn1 = findViewById(R.id.answerBtn1)
        answerBtn2 = findViewById(R.id.answerBtn2)
        answerBtn3 = findViewById(R.id.answerBtn3)
        answerBtn4 = findViewById(R.id.answerBtn4)

        // quizDataからクイズ出題用のquizArrayを作成する
        for (i in quizData.indices) {

            // 新しいArrayListを準備
            val tmpArray = ArrayList<String>()

            //選択肢の準備
            var random_s = arrayListOf(997,998,999)
            for(j in 0..2){
                do{
                    val random = Random()
                    random_s[j] = random.nextInt(animal.size)
                }while(animal[random_s[j]] == quizData[i][0] || random_s[0] == random_s[1] || random_s[1] == random_s[2] || random_s[2] == random_s[0])
            }

            // クイズデータを追加
            tmpArray.add(quizData[i][1])  // 問題文
            tmpArray.add(quizData[i][0])  // 正解
            tmpArray.add(animal[random_s[0]])  // 選択肢１
            tmpArray.add(animal[random_s[1]])  // 選択肢２
            tmpArray.add(animal[random_s[2]])  // 選択肢３


            // tmpArrayをquizArrayに追加する
            quizArray.add(tmpArray)
        }

        showNextQuiz()
    }

    fun image_show(i:String){
        try {
            resources.assets.open("${i}.jpg").use  { istream ->
                val bitmap = BitmapFactory.decodeStream(istream)
                image_view_image.setImageBitmap(bitmap)
            }
        } catch(e: IOException){
            e.printStackTrace()
        }
    }



    fun showNextQuiz() {
        // クイズカウントラベルを更新
        countLabel!!.text = "Q$quizCount"

        // ランダムな数字を取得
        val random = Random()
        val randomNum = random.nextInt(quizArray.size)

        // randomNumを使って、quizArrayからクイズを一つ取り出す
        val quiz = quizArray[randomNum]

        // 問題文を表示
        questionLabel!!.text = quiz[0]

        // 正解をrightAnswerにセット
        rightAnswer = quiz[1]

        //画像の読み込み
        image_show(quiz[1])


        // クイズ配列から問題文を削除
        quiz.removeAt(0)

        // 正解と選択肢３つをシャッフル
        Collections.shuffle(quiz)

        // 回答ボタンに正解と選択肢３つを表示
        answerBtn1!!.text = quiz[0]
        answerBtn2!!.text = quiz[1]
        answerBtn3!!.text = quiz[2]
        answerBtn4!!.text = quiz[3]

        // このクイズをquizArrayから削除
        quizArray.removeAt(randomNum)
    }



    fun checkAnswer(view: View) {

        // どの回答ボタンが押されたか
        val answerBtn = findViewById<Button>(view.id)
        val btnText = answerBtn.text.toString()

        val alertTitle: String
        if (btnText == rightAnswer) {
            alertTitle = "◯　Right!"
            sp0.play(snd0,1.0f,1.0f,0,0,1.0f)
            rightAnswerCount++

        } else {
            sp0.play(snd1,1.0f,1.0f,0,0,1.0f)
            alertTitle = "❌　Wrong..."
        }

        // ダイアログを作成
        val builder = AlertDialog.Builder(this)
        builder.setTitle(alertTitle)

        builder.setMessage("Answer : " + rightAnswer!!)
        builder.setPositiveButton("OK") { dialogInterface, i ->
            if (quizCount == QUIZ_COUNT) {
                // 結果画面へ移動
                val intent = Intent(applicationContext, ResultJActivity::class.java)
                intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount)
                intent.putExtra("quiz_count", quizCount)
                sp0.play(snd2,1.0f,1.0f,0,0,1.0f)
                startActivity(intent)

            } else {
                quizCount++
                showNextQuiz()
            }
        }
        builder.setCancelable(false)
        builder.show()


    }

    companion object {
        private val QUIZ_COUNT = 5
    }
    fun returnTop(view: View) {
        val intent = Intent(applicationContext, SelectJActivity::class.java)
        sp0.play(snd3,1.0f,1.0f,0,0,1.0f)
        startActivity(intent)
    }
    fun returnTitle(view: View) {
        val intent = Intent(applicationContext, TitleActivity::class.java)
        sp0.play(snd3,1.0f,1.0f,0,0,1.0f)
        startActivity(intent)
    }

}