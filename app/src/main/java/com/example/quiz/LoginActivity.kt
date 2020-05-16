package com.example.quiz

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.media.AudioAttributes
import android.media.SoundPool
import android.widget.Toast
import android.content.Intent
import android.icu.text.CaseMap
import kotlinx.android.synthetic.main.activity_login.*
import android.media.MediaPlayer
import android.text.Editable

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // get reference to all views
        val name  = "strawberry"
        val password  = "Password"
        val et_user_name : EditText = findViewById(R.id.et_user_name)
        val et_password : EditText = findViewById(R.id.et_password)
        val btn_reset : Button = findViewById(R.id.btn_reset)
        val btn_login : Button = findViewById(R.id.btn_login)
        val returnBtn1 : Button = findViewById(R.id.returnBtn1)
       // et_user_name.text = Editable.Factory.getInstance().newEditable(name)




        btn_reset.setOnClickListener {
            // clearing user_name and password edit text views on reset button click
            sp0.play(snd0,1.0f,1.0f,0,0,1.0f)
            et_user_name.setText("strawberry")
            et_password.setText("apple15")
        }

        // set on-click listener
        btn_login.setOnClickListener {

            //  Toast.makeText(this@LoginActivity, user_name, Toast.LENGTH_LONG).show()
            val intent = Intent(applicationContext, SelectActivity::class.java)
            sp0.play(snd0,1.0f,1.0f,0,0,1.0f)
          //  if( et_user_name.text == Editable.Factory.getInstance().newEditable(name) &&  et_password.text == Editable.Factory.getInstance().newEditable(password)){
                startActivity(intent)
          //  }


            // your code to validate the user_name and password combination
            // and verify the same

        }
        returnBtn1.setOnClickListener {
            sp0.play(snd0,1.0f,1.0f,0,0,1.0f)
            val intent = Intent(applicationContext, TitleActivity::class.java)
            startActivity(intent)
        }
    }
}

