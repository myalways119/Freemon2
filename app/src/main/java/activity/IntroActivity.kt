package activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import co.kr.freemon2.R
import android.telephony.TelephonyManager
import android.widget.Toast

class IntroActivity : AppCompatActivity() {

    val SPLASH_SCREEN = 5000
    private lateinit var topAnimation : Animation
    private lateinit var bottomAnimation : Animation

    private lateinit var introImage : ImageView
    private lateinit var introText : TextView
    private var devicePhoneNum:String = ""
    private var deviceAndroidId:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        devicePhoneNum = GetDeviceInfo()
        deviceAndroidId = GetUuid();
        Toast.makeText(this, devicePhoneNum, Toast.LENGTH_LONG).show()
        Toast.makeText(this, deviceAndroidId, Toast.LENGTH_LONG).show()

        IntroAnimation()

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, SPLASH_SCREEN.toLong())
    }

    private fun GetDeviceInfo():String
    {
        var devicePhoneNum:String = ""

        // READ_PHONE_NUMBERS 또는 READ_PHONE_STATE 권한을 허가 받았는지 확인
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)
        {
            //val tm = getSystemService<Any>(Context.TELEPHONY_SERVICE) as TelephonyManager
            //tm.line1Number();

            var teleManager: TelephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            devicePhoneNum = teleManager.line1Number.toString()
        }

        return devicePhoneNum
    }

    private fun GetUuid() : String {
        val uuid =  android.provider.Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
        return uuid
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