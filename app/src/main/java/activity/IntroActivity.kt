package activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import co.kr.freemon2.R

class IntroActivity : AppCompatActivity() {

    val SPLASH_SCREEN = 5000
    private lateinit var topAnimation : Animation
    private lateinit var bottomAnimation : Animation

    private lateinit var introImage : ImageView
    private lateinit var introText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        IntroAnimation()*

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, SPLASH_SCREEN.toLong())
    }

    fun IntroAnimation()
    {
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        introImage = findViewById(R.id.intro_imgLogo)
        introText = findViewById(R.id.intro_txtMain)

        introImage.animation = topAnimation
        introText.animation = bottomAnimation

    }
}