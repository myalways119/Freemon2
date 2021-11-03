package api

import item.UserItem
import item.UserResponseItem
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface  UserApi
{
    /**
     * This method will allow us perform a HTTP POST request to the specified url.In the process
     * we will insert data to mysql and return a ResponseModel object
     */
    @FormUrlEncoded
    @POST("UserApi.php")
    fun insertData(
        @Field("action") action: String?,
        @Field("name") name: String?,
        @Field("description") description: String?,
        @Field("galaxy") galaxy: String?,
        @Field("star") star: String?,
        @Field("dob") dob: String?,
        @Field("died") died: String?
    ): Call<UserResponseItem?>?

    /**
     * This method will allow us update our mysql data by making a HTTP POST request.
     * After that
     * we will receive a ResponseModel model object
     */
    @FormUrlEncoded
    @POST("UserApi.php")
    fun updateData(
        @Field("action") action: String?,
        @Field("id") id: String?,
        @Field("name") name: String?,
        @Field("description") description: String?,
        @Field("galaxy") galaxy: String?,
        @Field("star") star: String?,
        @Field("dob") dob: String?,
        @Field("died") died: String?
    ): Call<UserResponseItem?>?

    /**
     * This method will allow us to search our data while paginating the search results. We
     * specify the search and pagination parameters as fields.
     */
    @FormUrlEncoded
    @POST("UserApi.php")
    fun search(
        @Field("action") action: String?,
        @Field("phone_num") query: String?
    ): Call<UserResponseItem?>?

    /**
     * This method will aloow us to remove or delete from database the row with the
     * specified
     * id.
     */
    @FormUrlEncoded
    @POST("UserApi.php")
    fun remove(
        @Field("action") action: String?,
        @Field("id") id: String?
    ): Call<UserResponseItem?>?
}