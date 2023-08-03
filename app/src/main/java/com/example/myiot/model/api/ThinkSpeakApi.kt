package com.example.myiot.model.api


import com.example.myiot.model.ReadDataResponse
import com.example.myiot.model.ReadFieldResponse
import com.example.myiot.model.ReadLastEntryResponse
import com.example.myiot.model.ReadLastFieldEntryResponse
import com.example.myiot.model.ReadLastStatusResponse
import com.example.myiot.model.WriteBody
import com.example.myiot.model.WriteResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ThinkSpeakApi {
    //https://www.mathworks.com/help/thingspeak/readdata.html
    @GET("channels/{channel_id}/feeds.{format}")
    fun getReadData(
        @Path("channel_id") channelId: Int,
        @Path("format") format: String,
        @Query("api_key") apiKey: String
    ): Call<ReadDataResponse>

    //https://www.mathworks.com/help/thingspeak/readfield.html
    @GET("channels/{channel_id}/fields/{field_id}.{format}")
    fun getReadField(
        @Path("channel_id") channelId: Int,
        @Path("field_id") fieldId: Int,
        @Path("format") format: String,
        @Query("api_key") apiKey: String
    ): Call<ReadFieldResponse>

    //https://www.mathworks.com/help/thingspeak/readstatus.html
    @GET("channels/{channel_id}/status.{format}")
    fun getReadStatus(
        @Path("channel_id") channelId: Int,
        @Path("format") format: String,
        @Query("api_key") apiKey: String
    ): Call<ReadLastEntryResponse>

    //https://www.mathworks.com/help/thingspeak/readlastentry.html
    @GET("channels/{channel_id}/feeds/last.{format}")
    fun getReadLastEntry(
        @Path("channel_id") channelId: Int,
        @Path("format") format: String,
        @Query("api_key") apiKey: String
    ): Call<ReadLastFieldEntryResponse>

    //https://www.mathworks.com/help/thingspeak/readlastfieldentry.html
    @GET("channels/{channel_id}/fields/{field_id}/last.{format}")
    fun getReadLastFieldEntry(
        @Path("channel_id") channelId: Int,
        @Path("field_id") fieldId: Int,
        @Path("format") format: String,
        @Query("api_key") apiKey: String
    ): Call<ReadLastStatusResponse>

    //https://www.mathworks.com/help/thingspeak/readlaststatus.html
    @GET("channels/{channel_id}/status/last.{format}")
    fun getReadLastStatus(
        @Path("channel_id") channelId: Int,
        @Path("format") format: String,
        @Query("api_key") apiKey: String
    ): Call<ReadLastStatusResponse>

    //https://www.mathworks.com/help/thingspeak/writedata.html
    @Headers("Content-Type: application/json")
    @POST("update.json")
    fun updateWriteData(
        @Body() data: WriteBody
    ): Call<WriteResponse>
}

