package common

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context)
{
    companion object
    {
        public const val KEY_PHONE_NUM:String = "KEY_PHONE_NUM";
        public const val KEY_ANDROID_ID:String = "KEY_ANDROID_ID";

        private var sp: SharedPreferences? = null
        private var editor: SharedPreferences.Editor? = null
        private fun getInstance(context: Context): SharedPreferences
        {
            synchronized(this)
            { sp = context.getSharedPreferences("SharedPreferencesFile", Context.MODE_PRIVATE)
                return sp!! }
        }

        fun SetLongValue(context: Context, key: String, value: Long)
        { editor = getInstance(context).edit()
          editor!!.putLong(key, value)
          editor!!.apply() }

        fun GetLongValue(context: Context, key: String, default: Long): Long
        { return getInstance(context).getLong(key, default) }

        fun SetStringValue(context: Context, key: String, value: String)
        { editor = getInstance(context).edit()
          editor!!.putString(key, value)
          editor!!.apply() }

        fun GetStringValue(context: Context, key: String, default: String): String
        { return getInstance(context).getString(key, default)!! }

        fun SetIntValue(context: Context, key: String, value: Int)
        { editor = getInstance(context).edit()
          editor!!.putInt(key, value)
          editor!!.apply() }

        fun GetIntValue(context: Context, key: String, default: Int): Int
        { return getInstance(context).getInt(key, default) }
    }

}

