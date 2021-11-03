package item

import com.google.gson.annotations.SerializedName

/**
 * Our json response will be mapped to this class.
 */
class UserResponseItem {
    /**
     * Our ResponseModel attributes
     */
    @SerializedName("result")
    var result: ArrayList<UserItem>? = ArrayList()

    @SerializedName("code")
    var code: String? = "-1"

    @SerializedName("message")
    var message: String? = "UNKNOWN MESSAGE"

}