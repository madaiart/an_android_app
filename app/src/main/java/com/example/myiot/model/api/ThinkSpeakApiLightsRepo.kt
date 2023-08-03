package com.example.myiot.model.api

import android.util.Log
import com.example.myiot.BuildConfig
import com.example.myiot.model.ReadDataResponse
import com.example.myiot.model.ReadFieldResponse
import com.example.myiot.model.ReadLastEntryResponse
import com.example.myiot.model.ReadLastFieldEntryResponse
import com.example.myiot.model.ReadLastStatusResponse
import com.example.myiot.model.WriteBody
import com.example.myiot.model.WriteResponse
import com.example.myiot.model.api.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThinkSpeakApiLightsRepo(private val api: ThinkSpeakApi) {
    private val format = "json"
    private val channelIdLights = BuildConfig.CHANNEL_ID_LIGHTS_THINK_SPEAK
    private val readApiLights = BuildConfig.READ_KEY_LIGHTS
    private val writeApiLights = BuildConfig.WRITE_KEY_LIGHTS

    val readLightsData =  MutableStateFlow<NetworkResult<ReadDataResponse?>>(NetworkResult.Initial());
    fun getReadData() {
       readLightsData.value = NetworkResult.Loading();

        api.getReadData(channelIdLights.toInt(), format, readApiLights).enqueue(
            object : Callback<ReadDataResponse> {
                override fun onResponse(
                    call: Call<ReadDataResponse>,
                    response: Response<ReadDataResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readLightsData.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readLightsData.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadDataResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readLightsData.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val readLightField =  MutableStateFlow<NetworkResult<ReadFieldResponse?>>(NetworkResult.Initial());
    fun getReadField(fieldId: Int) {
        readLightField.value = NetworkResult.Loading();

        api.getReadField(channelIdLights.toInt(), fieldId, format, readApiLights).enqueue(
            object : Callback<ReadFieldResponse> {
                override fun onResponse(
                    call: Call<ReadFieldResponse>,
                    response: Response<ReadFieldResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readLightField.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readLightField.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadFieldResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readLightField.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val readLightStatus =  MutableStateFlow<NetworkResult<ReadLastEntryResponse?>>(NetworkResult.Initial());
    fun getReadStatus(fieldId: Int) {
        readLightStatus.value = NetworkResult.Loading();

        api.getReadStatus(channelIdLights.toInt(), format, readApiLights).enqueue(
            object : Callback<ReadLastEntryResponse> {
                override fun onResponse(
                    call: Call<ReadLastEntryResponse>,
                    response: Response<ReadLastEntryResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readLightStatus.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readLightStatus.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadLastEntryResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readLightStatus.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val readLightLastEntry =  MutableStateFlow<NetworkResult<ReadLastFieldEntryResponse?>>(NetworkResult.Initial());
    fun getReadLastEntry() {
        readLightLastEntry.value = NetworkResult.Loading();

        api.getReadLastEntry(channelIdLights.toInt(), format, readApiLights).enqueue(
            object : Callback<ReadLastFieldEntryResponse> {
                override fun onResponse(
                    call: Call<ReadLastFieldEntryResponse>,
                    response: Response<ReadLastFieldEntryResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readLightLastEntry.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readLightLastEntry.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadLastFieldEntryResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readLightLastEntry.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val readLightLastFieldEntry =  MutableStateFlow<NetworkResult<ReadLastStatusResponse?>>(NetworkResult.Initial());
    fun getReadLastFieldEntry(fieldId: Int) {
        readLightLastFieldEntry.value = NetworkResult.Loading();

        api.getReadLastFieldEntry(channelIdLights.toInt(), fieldId, format, readApiLights).enqueue(
            object : Callback<ReadLastStatusResponse> {
                override fun onResponse(
                    call: Call<ReadLastStatusResponse>,
                    response: Response<ReadLastStatusResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readLightLastFieldEntry.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readLightLastFieldEntry.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadLastStatusResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readLightLastFieldEntry.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val readLightLastStatus =  MutableStateFlow<NetworkResult<ReadLastStatusResponse?>>(NetworkResult.Initial());
    fun getReadLastStatus() {
        readLightLastStatus.value = NetworkResult.Loading();

        api.getReadLastStatus(channelIdLights.toInt(), format, readApiLights).enqueue(
            object : Callback<ReadLastStatusResponse> {
                override fun onResponse(
                    call: Call<ReadLastStatusResponse>,
                    response: Response<ReadLastStatusResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readLightLastStatus.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readLightLastStatus.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadLastStatusResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readLightLastStatus.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val writeLightData =  MutableStateFlow<NetworkResult<WriteResponse?>>(NetworkResult.Initial());
    fun updateWriteData(data: WriteBody) {
        writeLightData.value = NetworkResult.Loading();

        data.api_key = writeApiLights
        api.updateWriteData(data).enqueue(
            object : Callback<WriteResponse> {
                override fun onResponse(
                    call: Call<WriteResponse>,
                    response: Response<WriteResponse>
                ) {
                    Log.d("Tag-response>>", response.body().toString())
                    if (response.isSuccessful) {
                        response.body()?.let {
                            writeLightData.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        writeLightData.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<WriteResponse>, t: Throwable) {
                    Log.d("Tag-error>>", t.toString())
                    t.localizedMessage?.let{
                        writeLightData.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

}