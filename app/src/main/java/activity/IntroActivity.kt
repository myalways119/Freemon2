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
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import api.UserApi
import common.Common
import common.CommonConst
import common.GlobalVariables
import common.SharedPreferencesManager
import item.UserItem
import item.UserResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IntroActivity : AppCompatActivity() {

    val SPLASH_SCREEN = 2500
    private lateinit var topAnimation : Animation
    private lateinit var bottomAnimation : Animation

    private lateinit var introImage : ImageView
    private lateinit var introText : TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
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
            val intent = GetNextActivity()
            startActivity(intent)
            finish()
        }, delayTime.toLong())
    }

    private fun Initialize()
    {
        CheckPermission() //Check Permission always should be first.
        IntroAnimation() //Intro Activity Animation
        GlobalVariables.initialize(); //Initialize Global Variables (device phone num, device android id
        // Next Activity 결정
    }

    private fun GetNextActivity():Intent
    {
        val intent:Intent

        var savedAndroidId:String =  SharedPreferencesManager.GetStringValue(this, SharedPreferencesManager.KEY_ANDROID_ID,"")
        var savedPhoneNum:String = SharedPreferencesManager.GetStringValue(this, SharedPreferencesManager.KEY_PHONE_NUM,"")
        var deviceAndrodiId:String = GlobalVariables.deviceAndroidId
        var devicePhoneNo:String = GlobalVariables.devicePhoneNum

        if (deviceAndrodiId.isNullOrEmpty() == true || devicePhoneNo.isNullOrEmpty() == true)
        {
            intent = Intent(this, MainActivity::class.java)
            //메세지 팝업 후 시스템 종료
            //디바이스의 정보를 가져올 수 없습니다.
        }
        else if (savedAndroidId == deviceAndrodiId && savedPhoneNum == devicePhoneNo)
        {
            var userInfo:UserItem = GetUserInfo(savedPhoneNum);
            intent = Intent(this, MainActivity::class.java)
            //로그인 정보 DB에서 조회
            //DB에 정보가 존재 한다면
            //바로 Main Activity화면으로 이동
        }
        else
        {
            intent = Intent(this, MainActivity::class.java)
            //로그인 화면으로 이동
            //해당 로그인 화면에서 "기존계정 찾기" 클릭해서 질문 답변 입력하도록 하면 자동으로 로그인되도록 설정.
        }

        return intent
    }

    private fun CheckPermission()
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

    private fun IntroAnimation()
    {
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        introImage = findViewById(R.id.intro_imgLogo)
        introText = findViewById(R.id.intro_txtMain)

        introImage.animation = topAnimation
        introText.animation = bottomAnimation

    }

    private fun GetUserInfo(phoneNum: String):UserItem?
    {
        var returnItem:UserItem? = null

        if (phoneNum.isNullOrEmpty() == true) return returnItem;

        val api: UserApi = Common.client!!.create(UserApi::class.java)
        val update: Call<UserResponseItem?>? = api.search("SELECT", phoneNum)

        update!!.enqueue(object : Callback<UserResponseItem?> {
            override fun onResponse(call: Call<UserResponseItem?>?, response: Response<UserResponseItem?>?)
            {
                if (response?.body() == null) {
                    //showInfoDialog(a, "ERROR", "Response or ResponseBody is null")
                    Toast.makeText(null, "Response or ResponseBody is null", Toast.LENGTH_LONG).show()
                    return
                }
                val myResponseCode: String = response.body()!!.code!!
                if (myResponseCode.equals(CommonConst.DbResponseCode.SUCCESS, ignoreCase = true))
                {
                    val userItems = response.body()?.result!!

                    for(userItem in userItems)
                    {
                        returnItem = userItem
                    }

                    finish()
                } else if (myResponseCode.equals(CommonConst.DbResponseCode.FAIL, ignoreCase = true)) {
                    Toast.makeText(null, "UNSUCCESSFUL", Toast.LENGTH_LONG).show()

                } else if (myResponseCode.equals(CommonConst.DbResponseCode.ERROR, ignoreCase = true)) {
                    Toast.makeText(null, "NO MYSQL CONNECTION", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(
                call: Call<UserResponseItem?>?,
                t: Throwable
            ) {
                Log.d("RETROFIT", "ERROR THROWN DURING UPDATE: " + t.message)
                Toast.makeText(null, "FAILURE THROWN", Toast.LENGTH_LONG).show()
            }
        })
        return returnItem
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