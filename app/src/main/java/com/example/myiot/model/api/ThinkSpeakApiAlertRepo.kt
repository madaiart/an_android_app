package com.example.myiot.model.api

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

class ThinkSpeakApiAlertRepo(private val api: ThinkSpeakApi) {
    private val format = "json"

    private val channelIdAlert = BuildConfig.CHANNEL_ID_ALERT_THINK_SPEAK
    private val readApiAlert = BuildConfig.READ_KEY_ALERT
    private val writeApiAlert = BuildConfig.WRITE_KEY_ALERT

    val readAlertsData =  MutableStateFlow<NetworkResult<ReadDataResponse?>>(NetworkResult.Initial());
    fun getReadData() {
       readAlertsData.value = NetworkResult.Loading();

        api.getReadData(channelIdAlert.toInt(), format, readApiAlert).enqueue(
            object : Callback<ReadDataResponse> {
                override fun onResponse(
                    call: Call<ReadDataResponse>,
                    response: Response<ReadDataResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readAlertsData.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readAlertsData.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadDataResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readAlertsData.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val readAlertField =  MutableStateFlow<NetworkResult<ReadFieldResponse?>>(NetworkResult.Initial());
    fun getReadField(fieldId: Int) {
        readAlertField.value = NetworkResult.Loading();

        api.getReadField(channelIdAlert.toInt(), fieldId, format, readApiAlert).enqueue(
            object : Callback<ReadFieldResponse> {
                override fun onResponse(
                    call: Call<ReadFieldResponse>,
                    response: Response<ReadFieldResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readAlertField.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readAlertField.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadFieldResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readAlertField.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val readAlertStatus =  MutableStateFlow<NetworkResult<ReadLastEntryResponse?>>(NetworkResult.Initial());
    fun getReadStatus(fieldId: Int) {
        readAlertStatus.value = NetworkResult.Loading();

        api.getReadStatus(channelIdAlert.toInt(), format, readApiAlert).enqueue(
            object : Callback<ReadLastEntryResponse> {
                override fun onResponse(
                    call: Call<ReadLastEntryResponse>,
                    response: Response<ReadLastEntryResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readAlertStatus.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readAlertStatus.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadLastEntryResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readAlertStatus.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val readAlertLastEntry =  MutableStateFlow<NetworkResult<ReadLastFieldEntryResponse?>>(NetworkResult.Initial());
    fun getReadLastEntry() {
        readAlertLastEntry.value = NetworkResult.Loading();

        api.getReadLastEntry(channelIdAlert.toInt(), format, readApiAlert).enqueue(
            object : Callback<ReadLastFieldEntryResponse> {
                override fun onResponse(
                    call: Call<ReadLastFieldEntryResponse>,
                    response: Response<ReadLastFieldEntryResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readAlertLastEntry.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readAlertLastEntry.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadLastFieldEntryResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readAlertLastEntry.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val readAlertLastFieldEntry =  MutableStateFlow<NetworkResult<ReadLastStatusResponse?>>(NetworkResult.Initial());
    fun getReadLastFieldEntry(fieldId: Int) {
        readAlertLastFieldEntry.value = NetworkResult.Loading();

        api.getReadLastFieldEntry(channelIdAlert.toInt(), fieldId, format, readApiAlert).enqueue(
            object : Callback<ReadLastStatusResponse> {
                override fun onResponse(
                    call: Call<ReadLastStatusResponse>,
                    response: Response<ReadLastStatusResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readAlertLastFieldEntry.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readAlertLastFieldEntry.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadLastStatusResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readAlertLastFieldEntry.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val readAlertLastStatus =  MutableStateFlow<NetworkResult<ReadLastStatusResponse?>>(NetworkResult.Initial());
    fun getReadLastStatus() {
        readAlertLastStatus.value = NetworkResult.Loading();

        api.getReadLastStatus(channelIdAlert.toInt(), format, readApiAlert).enqueue(
            object : Callback<ReadLastStatusResponse> {
                override fun onResponse(
                    call: Call<ReadLastStatusResponse>,
                    response: Response<ReadLastStatusResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readAlertLastStatus.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readAlertLastStatus.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadLastStatusResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readAlertLastStatus.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val writeAlertData =  MutableStateFlow<NetworkResult<WriteResponse?>>(NetworkResult.Initial());
    fun updateWriteData(data: WriteBody) {
        writeAlertData.value = NetworkResult.Loading();

        data.api_key = writeApiAlert
        api.updateWriteData(data).enqueue(
            object : Callback<WriteResponse> {

                override fun onResponse(call: Call<WriteResponse>, response: Response<WriteResponse>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            writeAlertData.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        writeAlertData.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<WriteResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        writeAlertData.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

}