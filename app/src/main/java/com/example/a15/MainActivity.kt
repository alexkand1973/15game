package com.example.a15

import android.content.Intent
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        btn_navigationToInfo.setOnClickListener {
            var intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }

        btn_navigationToStart.setOnClickListener {
            var intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

        fun audio1(view: View) {
            MediaPlayer.create(this, R.raw.hello).start()
        }

        audio1(btn_navigationToStart)
    }
}





