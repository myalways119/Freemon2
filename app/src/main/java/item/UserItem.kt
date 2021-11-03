package item

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserItem : Serializable
{
    @SerializedName("phone_num")
    var phone_num: String? = ""

    @SerializedName("name")
    var name: String? = ""

    @SerializedName("gender")
    var gender: String? = ""

    @SerializedName("berth")
    var berth: String? = ""

    @SerializedName("profile_pic")
    var profile_pic: String? = ""

    @SerializedName("target_city")
    var target_city: String? = ""

    @SerializedName("rec_question")
    var rec_question: String? = ""

    @SerializedName("rec_answer")
    var rec_answer: String? = ""

    @SerializedName("android_id")
    var android_id: String? = ""
}