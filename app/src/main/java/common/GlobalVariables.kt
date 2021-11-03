package common

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.provider.Settings
import android.telephony.TelephonyManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService

object GlobalVariables : AppCompatActivity(){
    var devicePhoneNum:String = ""
    var deviceAndroidId:String = ""

    public fun initialize()
    {
        devicePhoneNum = GetDevicePhoneNum()
        deviceAndroidId = GetDeviceAndroidId()
    }

    private fun GetDevicePhoneNum():String
    {
        var devicePhoneNum : String = ""

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

    private fun GetDeviceAndroidId() : String {
        val uuid =  android.provider.Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
        return uuid
    }
}