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
import androidx.core.content.ContextCompat
import common.CommonConst
import common.GlobalVariables
import common.SharedPreferencesManager

class IntroActivity : AppCompatActivity() {

    val SPLASH_SCREEN = 2500
    private lateinit var topAnimation : Animation
    private lateinit var bottomAnimation : Animation

    private lateinit var introImage : ImageView
    private lateinit var introText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        var startTime: Long
        var endTime: Long
        var delayTime: Long = 0
        var elapsedTime: Long

        startTime = System.currentTimeMillis();

        Initialize()

        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime
        if (SPLASH_SCREEN > elapsedTime)
        {
            delayTime = SPLASH_SCREEN - elapsedTime
        }

        //Delay
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, delayTime.toLong())
    }

    fun Initialize()
    {
        CheckPermission() //Check Permission always should be first.
        IntroAnimation() //Intro Activity Animation
        GlobalVariables.initialize(); //Initialize Global Variables (device phone num, device android id
        // Next Activity 결정
    }

    fun GetNextActivity()
    {
        var savedAndroidId:String =  SharedPreferencesManager.GetStringValue(this, SharedPreferencesManager.KEY_ANDROID_ID,"")
        var savedPhoneNum:String = SharedPreferencesManager.GetStringValue(this, SharedPreferencesManager.KEY_PHONE_NUM,"")

        if (savedAndroidId.isNullOrEmpty() == true || savedPhoneNum.isNullOrEmpty() == true)
        {
            //Login Activity
        }
        else if (savedAndroidId == GlobalVariables.deviceAndroidId
                && savedPhoneNum == GlobalVariables.devicePhoneNum)
        {//자동 로그인
            //저장된 정보로 DB 조회 유저 정보 가져오기.
            //DATA가 있으면 Main Activity 없으면 Login Activity
        }
        else
        {
            //Login Activity
        }


        //저잗된
    }

    fun CheckPermission()
    {
        var hasPermission: Boolean = true

        for(p in CommonConst.requiredPermissions)
        {
            if(ContextCompat.checkSelfPermission(this, p) == PackageManager.PERMISSION_DENIED)
            {
                hasPermission = false
            }
        }

        if (hasPermission == false)
        {
            val intent = Intent(this, PermissionActivity::class.java)
            startActivity(intent)
        }
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

/*
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

 */

}