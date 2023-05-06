package com.example.a15

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_end_game.*

class endGame : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_game)

//        fun soundWin(): MediaPlayer? {
//            var mediaPlayerWin: MediaPlayer? = MediaPlayer.create(this, R.raw.endgame)
//            mediaPlayerWin?.setOnPreparedListener{}
//            return mediaPlayerWin
//        }
//        soundWin()?.start()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        btn_navigationAganToMainActivity.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}