package common

import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Common
{
    /**
     * Let's define some Constants
     */
    //supply your ip address. Type ipconfig while connected to internet to get your
    //ip address in cmd.
    private const val base_url = "https://camposha.info/PHP/scientists/"

    private var retrofit: Retrofit? = null
    const val DATE_FORMAT = "yyyy-MM-dd"

    /**
     * This method will return us our Retrofit instance which we can use to initiate HTTP calls.
     */
    val client: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

    /**
     * THis method will allow us show Toast messages throughout all activities
     */
    fun show(c: Context?, message: String?) {
        Toast.makeText(c, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * This method will allow us validate edittexts
     */
    fun validate(vararg editTexts: EditText): Boolean {
        val nameTxt = editTexts[0]
        val descriptionTxt = editTexts[1]
        val galaxyTxt = editTexts[2]
        if (nameTxt.text == null || nameTxt.text.toString().isEmpty()) {
            nameTxt.error = "Name is Required Please!"
            return false
        }
        if (descriptionTxt.text == null || descriptionTxt.text.toString().isEmpty()) {
            descriptionTxt.error = "Description is Required Please!"
            return false
        }
        if (galaxyTxt.text == null || galaxyTxt.text.toString().isEmpty()) {
            galaxyTxt.error = "Galaxy is Required Please!"
            return false
        }
        return true
    }

    /**
     * This utility method will allow us clear arbitrary number of edittexts
     */
    fun clearEditTexts(vararg editTexts: EditText) {
        for (editText in editTexts) {
            editText.setText("")
        }
    }

    /**
     * This utility method will allow us open any activity.
     */
    fun openActivity(c: Context, clazz: Class<*>?) {
        val intent = Intent(c, clazz)
        c.startActivity(intent)
    }

    /**
     * This method will allow us convert a string into a java.util.Date object and
     * return it.
     */
    fun giveMeDate(stringDate: String?): Date? {
        return try {
            val sdf =
                SimpleDateFormat(DATE_FORMAT)
            sdf.parse(stringDate)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    /**
     * This method will allow us show a progressbar
     */
    fun showProgressBar(pb: ProgressBar) {
        pb.visibility = View.VISIBLE
    }

    /**
     * This method will allow us hide a progressbar
     */
    fun hideProgressBar(pb: ProgressBar) {
        pb.visibility = View.GONE
    }

    /**
     * This method will allow us show an Info dialog anywhere in our app.
     */
}