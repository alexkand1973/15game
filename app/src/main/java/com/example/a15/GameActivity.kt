package com.example.a15

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.AssetManager
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game.*


open class GameActivity : AppCompatActivity() {

    //Воспроизведение звуков
    private lateinit var soundPool: SoundPool
    private lateinit var assetManager: AssetManager
    private val maxStreams: Int = 3
    private val streamType: Int = 3
    private val srcQuality: Int = 0
    private var streamID = 0

    private val attributes = AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_GAME)
        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
        .build()


    val clickButtonSound = loadSound(this, R.raw.clickbutton)
    val endGameSound = loadSound(this, R.raw.endgame)
    val nullSound = loadSound(this, R.raw.soundnull)
    val crushGameSound = loadSound(this, R.raw.crushgame)



//    load(context: Context!, resId: Int, priority: Int) //Загрузите звук из указанного ресурса APK.
//
//    play(soundID: Int, leftVolume: Float, rightVolume: Float, priority: Int, loop: Int, rate: Float)
//    //Воспроизведение звука из идентификатора звука.
//
//    setVolume(streamID: Int, leftVolume: Float, rightVolume: Float)
//    //Установите громкость потока.
//
//    setOnLoadCompleteListener(listener: SoundPool.OnLoadCompleteListener!)
//    //Устанавливает обработчик обратного вызова для OnLoadCompleteListener.
//
//    setPriority(streamID: Int, priority: Int)
//    //Изменить приоритет потока.
//
//
//    unload(soundID: Int)
//    //Выгрузить звук из идентификатора звука.
//
//
//    SoundPool.maxStreams
//
//    SoundPool.stop(streamID: Int)
//    //Остановить воспроизведение потока.
//
//    SoundPool.play()
//    streamID
//    SoundPool.release() //завершает все потоки
//    finalize()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

//Блокировка переворота экрана
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

// создаем звуковой ряд для игры
        soundPool = SoundPool.Builder().setAudioAttributes(attributes).build()
        assetManager = assets




//Возвращение на начальную страницу
        btn_navigationToMainActivity.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //Заполняю кнопки элементами массива:
        fun showValue(index: Int) {
            if (index == 0) btnOne.text = intent.getIntExtra(arrayOfNumbers[0])
            else if (index == 1) btnTwo.text = intent.getIntExtra(arrayOfNumbers[1])
            else if (index == 2) btnThree.text = intent.getIntExtra(arrayOfNumbers[2])
            else if (index == 3) btnFour.text = intent.getIntExtra(arrayOfNumbers[3])
            else if (index == 4) btnFive.text = intent.getIntExtra(arrayOfNumbers[4])
            else if (index == 5) btnSix.text = intent.getIntExtra(arrayOfNumbers[5])
            else if (index == 6) btnSeven.text = intent.getIntExtra(arrayOfNumbers[6])
            else if (index == 7) btnEight.text = intent.getIntExtra(arrayOfNumbers[7])
            else if (index == 8) btnNine.text = intent.getIntExtra(arrayOfNumbers[8])
            else if (index == 9) btnTen.text = intent.getIntExtra(arrayOfNumbers[9])
            else if (index == 10) btnEleven.text = intent.getIntExtra(arrayOfNumbers[10])
            else if (index == 11) btnTwelve.text = intent.getIntExtra(arrayOfNumbers[11])
            else if (index == 12) btnThirteen.text = intent.getIntExtra(arrayOfNumbers[12])
            else if (index == 13) btnFourteen.text = intent.getIntExtra(arrayOfNumbers[13])
            else if (index == 14) btnFifteen.text = intent.getIntExtra(arrayOfNumbers[14])
            else if (index == 15) btnNull.text = intent.getIntExtra(arrayOfNumbers[15])
        }

        //На пустую клетку передаем пустое значение
        fun getNothing(indexZero: Int) {
            if (indexZero == 0) btnOne.text = intent.getStringExtra("")
            else if (indexZero == 1) btnTwo.text = intent.getStringExtra("")
            else if (indexZero == 2) btnThree.text = intent.getStringExtra("")
            else if (indexZero == 3) btnFour.text = intent.getStringExtra("")
            else if (indexZero == 4) btnFive.text = intent.getStringExtra("")
            else if (indexZero == 5) btnSix.text = intent.getStringExtra("")
            else if (indexZero == 6) btnSeven.text = intent.getStringExtra("")
            else if (indexZero == 7) btnEight.text = intent.getStringExtra("")
            else if (indexZero == 8) btnNine.text = intent.getStringExtra("")
            else if (indexZero == 9) btnTen.text = intent.getStringExtra("")
            else if (indexZero == 10) btnEleven.text = intent.getStringExtra("")
            else if (indexZero == 11) btnTwelve.text = intent.getStringExtra("")
            else if (indexZero == 12) btnThirteen.text = intent.getStringExtra("")
            else if (indexZero == 13) btnFourteen.text = intent.getStringExtra("")
            else if (indexZero == 14) btnFifteen.text = intent.getStringExtra("")
            else if (indexZero == 15) btnNull.text = intent.getStringExtra("")
        }
        arrayOfNumbers.forEachIndexed { index, element ->
            if (element == 0) {
                getNothing(index)
            } else {
                showValue(index)
            }
        }

        btnNull.setOnClickListener {
            if (arrayOfNumbers[15] != 0) {
                val wherePass: String = lookOffNull(15)
                if (wherePass == "up") {
                    changeParametrs(15, 11)
                    btnTwelve.text = intent.getIntExtra(arrayOfNumbers[11])
                    btnNull.text = intent.getStringExtra("")
                } else if (wherePass == "left") {
                    changeParametrs(15, 14)
                    btnFifteen.text = intent.getIntExtra(arrayOfNumbers[14])
                    btnNull.text = intent.getStringExtra("")
                } else playSound(nullSound)
            }
            endGame(checkEnd(arrayOfNumbers))
        }

        btnFifteen.setOnClickListener {
            if (arrayOfNumbers[14] != 0) {
                val wherePass = lookOffNull(14)
                if (wherePass == "up") {
                    changeParametrs(14, 10)
                    btnEleven.text = intent.getIntExtra(arrayOfNumbers[10])
                    btnFifteen.text = intent.getStringExtra("")
                } else if (wherePass == "right") {
                    changeParametrs(14, 15)
                    btnNull.text = intent.getIntExtra(arrayOfNumbers[15])
                    btnFifteen.text = intent.getStringExtra("")
                } else if (wherePass == "left") {
                    changeParametrs(14, 13)
                    btnFourteen.text = intent.getIntExtra(arrayOfNumbers[13])
                    btnFifteen.text = intent.getStringExtra("")
                } else playSound(nullSound)
            }
            endGame(checkEnd(arrayOfNumbers))
        }

        btnFourteen.setOnClickListener {
            if (arrayOfNumbers[13] != 0) {
                val wherePass = lookOffNull(13)
                if (wherePass == "up") {
                    changeParametrs(13, 9)
                    btnTen.text = intent.getIntExtra(arrayOfNumbers[9])
                    btnFourteen.text = intent.getStringExtra("")
                } else if (wherePass == "right") {
                    changeParametrs(13, 14)
                    btnFourteen.text = intent.getStringExtra("")
                    btnFifteen.text = intent.getIntExtra(arrayOfNumbers[14])
                } else if (wherePass == "left") {
                    changeParametrs(13, 12)
                    btnThirteen.text = intent.getIntExtra(arrayOfNumbers[12])
                    btnFourteen.text = intent.getStringExtra("")
                } else playSound(nullSound)
            }
            endGame(checkEnd(arrayOfNumbers))
        }

        btnThirteen.setOnClickListener {
            if (arrayOfNumbers[12] != 0) {
                val wherePass = lookOffNull(12)
                if (wherePass == "up") {
                    changeParametrs(12, 8)
                    btnNine.text = intent.getIntExtra(arrayOfNumbers[8])
                    btnThirteen.text = intent.getStringExtra("")
                } else if (wherePass == "right") {
                    changeParametrs(12, 13)
                    btnFourteen.text = intent.getIntExtra(arrayOfNumbers[13])
                    btnThirteen.text = intent.getStringExtra("")
                } else playSound(nullSound)
            }
            endGame(checkEnd(arrayOfNumbers))
        }

        btnTwelve.setOnClickListener {
            if (arrayOfNumbers[11] != 0) {
                val wherePass = lookOffNull(11)
                if (wherePass == "up") {
                    changeParametrs(11, 7)
                    btnEight.text = intent.getIntExtra(arrayOfNumbers[7])
                    btnTwelve.text = intent.getStringExtra("")
                } else if (wherePass == "left") {
                    changeParametrs(11, 10)
                    btnEleven.text = intent.getIntExtra(arrayOfNumbers[10])
                    btnTwelve.text = intent.getStringExtra("")
                } else if (wherePass == "down") {
                    changeParametrs(11, 15)
                    btnNull.text = intent.getIntExtra(arrayOfNumbers[15])
                    btnTwelve.text = intent.getStringExtra("")
                } else playSound(nullSound)
            }
            endGame(checkEnd(arrayOfNumbers))
        }

        btnEleven.setOnClickListener {
            if (arrayOfNumbers[10] != 0) {
                val wherePass = lookOffNull(10)
                if (wherePass == "up") {
                    changeParametrs(10, 6)
                    btnSeven.text = intent.getIntExtra(arrayOfNumbers[6])
                    btnEleven.text = intent.getStringExtra("")
                } else if (wherePass == "right") {
                    changeParametrs(10, 11)
                    btnEleven.text = intent.getStringExtra("")
                    btnTwelve.text = intent.getIntExtra(arrayOfNumbers[11])
                } else if (wherePass == "left") {
                    changeParametrs(10, 9)
                    btnTen.text = intent.getIntExtra(arrayOfNumbers[9])
                    btnEleven.text = intent.getStringExtra("")
                } else if (wherePass == "down") {
                    changeParametrs(10, 14)
                    btnFifteen.text = intent.getIntExtra(arrayOfNumbers[14])
                    btnEleven.text = intent.getStringExtra("")
                } else playSound(nullSound)
            }
            endGame(checkEnd(arrayOfNumbers))
        }

        btnTen.setOnClickListener {
            if (arrayOfNumbers[9] != 0) {
                val wherePass = lookOffNull(9)
                if (wherePass == "up") {
                    changeParametrs(9, 5)
                    btnSix.text = intent.getIntExtra(arrayOfNumbers[5])
                    btnTen.text = intent.getStringExtra("")
                } else if (wherePass == "right") {
                    changeParametrs(9, 10)
                    btnEleven.text = intent.getIntExtra(arrayOfNumbers[10])
                    btnTen.text = intent.getStringExtra("")
                } else if (wherePass == "left") {
                    changeParametrs(9, 8)
                    btnTen.text = intent.getStringExtra("")
                    btnNine.text = intent.getIntExtra(arrayOfNumbers[8])
                } else if (wherePass == "down") {
                    changeParametrs(9, 13)
                    btnFourteen.text = intent.getIntExtra(arrayOfNumbers[13])
                    btnTen.text = intent.getStringExtra("")
                } else playSound(nullSound)
            }
            endGame(checkEnd(arrayOfNumbers))
        }

        btnNine.setOnClickListener {
            if (arrayOfNumbers[8] != 0) {
                val wherePass = lookOffNull(8)
                if (wherePass == "up") {
                    changeParametrs(8, 4)
                    btnFive.text = intent.getIntExtra(arrayOfNumbers[4])
                    btnNine.text = intent.getStringExtra("")
                } else if (wherePass == "right") {
                    changeParametrs(8, 9)
                    btnNine.text = intent.getStringExtra("")
                    btnTen.text = intent.getIntExtra(arrayOfNumbers[9])
                } else if (wherePass == "down") {
                    changeParametrs(8, 12)
                    btnThirteen.text = intent.getIntExtra(arrayOfNumbers[12])
                    btnNine.text = intent.getStringExtra("")
                } else playSound(nullSound)
            }
            endGame(checkEnd(arrayOfNumbers))
        }

        btnEight.setOnClickListener {
            if (arrayOfNumbers[7] != 0) {
                val wherePass = lookOffNull(7)
                if (wherePass == "up") {
                    changeParametrs(7, 3)
                    btnFour.text = intent.getIntExtra(arrayOfNumbers[3])
                    btnEight.text = intent.getStringExtra("")
                } else if (wherePass == "left") {
                    changeParametrs(7, 6)
                    btnSeven.text = intent.getIntExtra(arrayOfNumbers[6])
                    btnEight.text = intent.getStringExtra("")
                } else if (wherePass == "down") {
                    changeParametrs(7, 11)
                    btnTwelve.text = intent.getIntExtra(arrayOfNumbers[11])
                    btnEight.text = intent.getStringExtra("")
                } else playSound(nullSound)
            }
            endGame(checkEnd(arrayOfNumbers))
        }

        btnSeven.setOnClickListener {
            if (arrayOfNumbers[6] != 0) {
                val wherePass = lookOffNull(6)
                if (wherePass == "up") {
                    changeParametrs(6, 2)
                    btnThree.text = intent.getIntExtra(arrayOfNumbers[2])
                    btnSeven.text = intent.getStringExtra("")
                } else if (wherePass == "right") {
                    changeParametrs(6, 7)
                    btnEight.text = intent.getIntExtra(arrayOfNumbers[7])
                    btnSeven.text = intent.getStringExtra("")
                } else if (wherePass == "left") {
                    changeParametrs(6, 5)
                    btnSix.text = intent.getIntExtra(arrayOfNumbers[5])
                    btnSeven.text = intent.getStringExtra("")
                } else if (wherePass == "down") {
                    changeParametrs(6, 10)
                    btnEleven.text = intent.getIntExtra(arrayOfNumbers[10])
                    btnSeven.text = intent.getStringExtra("")
                } else playSound(nullSound)
            }
            endGame(checkEnd(arrayOfNumbers))
        }

        btnSix.setOnClickListener {
            if (arrayOfNumbers[5] != 0) {
                val wherePass = lookOffNull(5)
                if (wherePass == "up") {
                    changeParametrs(5, 1)
                    btnTwo.text = intent.getIntExtra(arrayOfNumbers[1])
                    btnSix.text = intent.getStringExtra("")
                } else if (wherePass == "right") {
                    changeParametrs(5, 6)
                    btnSeven.text = intent.getIntExtra(arrayOfNumbers[6])
                    btnSix.text = intent.getStringExtra("")
                } else if (wherePass == "left") {
                    changeParametrs(5, 4)
                    btnFive.text = intent.getIntExtra(arrayOfNumbers[4])
                    btnSix.text = intent.getStringExtra("")
                } else if (wherePass == "down") {
                    changeParametrs(5, 9)
                    btnTen.text = intent.getIntExtra(arrayOfNumbers[9])
                    btnSix.text = intent.getStringExtra("")
                } else playSound(nullSound)
            }
            endGame(checkEnd(arrayOfNumbers))
        }

        btnFive.setOnClickListener {
            if (arrayOfNumbers[4] != 0) {
                val wherePass = lookOffNull(4)
                if (wherePass == "up") {
                    changeParametrs(4, 0)
                    btnOne.text = intent.getIntExtra(arrayOfNumbers[0])
                    btnFive.text = intent.getStringExtra("")
                } else if (wherePass == "right") {
                    changeParametrs(4, 5)
                    btnSix.text = intent.getIntExtra(arrayOfNumbers[5])
                    btnFive.text = intent.getStringExtra("")
                } else if (wherePass == "down") {
                    changeParametrs(4, 8)
                    btnNine.text = intent.getIntExtra(arrayOfNumbers[8])
                    btnFive.text = intent.getStringExtra("")
                } else playSound(nullSound)
            }
            endGame(checkEnd(arrayOfNumbers))
        }

        btnFour.setOnClickListener {
            if (arrayOfNumbers[3] != 0) {
                val wherePass = lookOffNull(3)
                if (wherePass == "left") {
                    changeParametrs(3, 2)
                    btnThree.text = intent.getIntExtra(arrayOfNumbers[2])
                    btnFour.text = intent.getStringExtra("")
                } else if (wherePass == "down") {
                    changeParametrs(3, 7)
                    btnEight.text = intent.getIntExtra(arrayOfNumbers[7])
                    btnFour.text = intent.getStringExtra("")
                } else playSound(nullSound)
            }
            endGame(checkEnd(arrayOfNumbers))
        }

        btnThree.setOnClickListener {
            if (arrayOfNumbers[2] != 0) {
                val wherePass = lookOffNull(2)
                if (wherePass == "right") {
                    changeParametrs(2, 3)
                    btnFour.text = intent.getIntExtra(arrayOfNumbers[3])
                    btnThree.text = intent.getStringExtra("")
                } else if (wherePass == "left") {
                    changeParametrs(2, 1)
                    btnTwo.text = intent.getIntExtra(arrayOfNumbers[1])
                    btnThree.text = intent.getStringExtra("")
                } else if (wherePass == "down") {
                    changeParametrs(2, 6)
                    btnSeven.text = intent.getIntExtra(arrayOfNumbers[6])
                    btnThree.text = intent.getStringExtra("")
                } else playSound(nullSound)
            }
            endGame(checkEnd(arrayOfNumbers))
        }

        btnTwo.setOnClickListener {
            if (arrayOfNumbers[1] != 0) {
                val wherePass = lookOffNull(1)
                if (wherePass == "right") {
                    changeParametrs(1, 2)
                    btnThree.text = intent.getIntExtra(arrayOfNumbers[2])
                    btnTwo.text = intent.getStringExtra("")
                } else if (wherePass == "left") {
                    changeParametrs(1, 0)
                    btnOne.text = intent.getIntExtra(arrayOfNumbers[0])
                    btnTwo.text = intent.getStringExtra("")
                } else if (wherePass == "down") {
                    changeParametrs(1, 5)
                    btnSix.text = intent.getIntExtra(arrayOfNumbers[5])
                    btnTwo.text = intent.getStringExtra("")
                } else playSound(nullSound)
            }
            endGame(checkEnd(arrayOfNumbers))
        }

        btnOne.setOnClickListener {
            if (arrayOfNumbers[0] != 0) {
                val wherePass = lookOffNull(0)
                if (wherePass == "right") {
                    changeParametrs(0, 1)
                    btnTwo.text = intent.getIntExtra(arrayOfNumbers[1])
                    btnOne.text = intent.getStringExtra("")
                } else if (wherePass == "down") {
                    changeParametrs(0, 4)
                    btnFive.text = intent.getIntExtra(arrayOfNumbers[4])
                    btnOne.text = intent.getStringExtra("")
                } else playSound(nullSound)
            }
            endGame(checkEnd(arrayOfNumbers))
        }
    }

    //Проверка правильности расположения элементов
    private fun checkEnd(array: Array<Int>): String {
        val result: String?
        val rightArray = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0)
        val wrongArray = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 11, 13, 14, 15, 0)
        if (array.contentEquals(rightArray)) {
            result = "end"
            return result
        } else if (array.contentEquals(wrongArray)) {
            result = "crush"
            return result
        } else {
            result = "clicksound"
            return result
        }
    }

    //завершение игры при правильной и неправильной раскладке
    private fun endGame(string: String) {
        if (string == "end") {
            playSound(endGameSound)
            val intent = Intent(this, endGame::class.java)
//            stopSound(loadSound("endgame"))
            startActivity(intent)
        } else if (string == "crush") {
            playSound(crushGameSound)
            val intent = Intent(this, CrushActivity::class.java)
//            stopSound(loadSound("crushgame"))
            startActivity(intent)
        } else if (string == "clicksound") {
            playSound(clickButtonSound)
        }
//        stopSound(loadSound("clickbutton"))
    }

    private fun Intent.getIntExtra(value: Int): String {
        return value.toString()
    }

//    private fun Intent.getStringExtra(value: String): String {
//        return value
//    }

    // Получил случайный мвссив
    var arrayOfNumb = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
    var arrayOfNumbers: Array<Int> = createArray(arrayOfNumb)
    fun createArray(array: Array<Int>): Array<Int> {
        array.shuffle()
        return array
    }

    //Замена параметров
    fun changeParametrs(
        index1: Int,
        index2: Int
    ) {
        arrayOfNumbers[index2] = arrayOfNumbers[index1]
        arrayOfNumbers[index1] = 0
    }

    //Проверка значения на 0
    fun lookOffNull(index: Int): String {
        var indexLeft = index - 1
        var indexUp = index - 4
        var indexRight = index + 1
        var indexDown = index + 4
        if (indexLeft >= 0 && indexLeft <= 15 && arrayOfNumbers[indexLeft] == 0) {
            return "left"
        } else if (indexUp >= 0 && indexUp <= 15 && arrayOfNumbers[indexUp] == 0) {
            return "up"
        } else if (indexRight >= 0 && indexRight <= 15 && arrayOfNumbers[indexRight] == 0) {
            return "right"
        } else if (indexDown >= 0 && indexDown <= 15 && arrayOfNumbers[indexDown] == 0) {
            return "down"
        } else return ""
    }


override fun onPause() {
    super.onPause()

    soundPool.release()
}

private fun playSound(soundID: SoundPool): Int {
    if (soundID > 0) {
        streamID = soundPool.play(soundID, 1F, 1F, 1, 0, 1F)
    }
    return streamID
}

private fun loadSound(context: Context, resID: Int): SoundPool {
    return soundPool.load(context, resID, 1)

}
}
