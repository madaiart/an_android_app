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

class ThinkSpeakApiSignalsRepo(private val api: ThinkSpeakApi) {
    private val format = "json"

    private val channelIdSignals = BuildConfig.CHANNEL_ID_SIGNALS_THINK_SPEAK
    private val readApiSignals = BuildConfig.READ_KEY_SIGNALS
    private val writeApiSignals = BuildConfig.WRITE_KEY_SIGNALS

    val readSignalsData =  MutableStateFlow<NetworkResult<ReadDataResponse?>>(NetworkResult.Initial());
    fun getReadData() {
       readSignalsData.value = NetworkResult.Loading();

        api.getReadData(channelIdSignals.toInt(), format, readApiSignals).enqueue(
            object : Callback<ReadDataResponse> {
                override fun onResponse(
                    call: Call<ReadDataResponse>,
                    response: Response<ReadDataResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readSignalsData.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readSignalsData.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadDataResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readSignalsData.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val readSignalsField =  MutableStateFlow<NetworkResult<ReadFieldResponse?>>(NetworkResult.Initial());
    fun getReadField(fieldId: Int) {
        readSignalsField.value = NetworkResult.Loading();

        api.getReadField(channelIdSignals.toInt(), fieldId, format, readApiSignals).enqueue(
            object : Callback<ReadFieldResponse> {
                override fun onResponse(
                    call: Call<ReadFieldResponse>,
                    response: Response<ReadFieldResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readSignalsField.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readSignalsField.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadFieldResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readSignalsField.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val readSignalStatus =  MutableStateFlow<NetworkResult<ReadLastEntryResponse?>>(NetworkResult.Initial());
    fun getReadStatus(fieldId: Int) {
        readSignalStatus.value = NetworkResult.Loading();

        api.getReadStatus(channelIdSignals.toInt(), format, readApiSignals).enqueue(
            object : Callback<ReadLastEntryResponse> {
                override fun onResponse(
                    call: Call<ReadLastEntryResponse>,
                    response: Response<ReadLastEntryResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readSignalStatus.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readSignalStatus.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadLastEntryResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readSignalStatus.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val readSignalsLastEntry =  MutableStateFlow<NetworkResult<ReadLastFieldEntryResponse?>>(NetworkResult.Initial());
    fun getReadLastEntry() {
        readSignalsLastEntry.value = NetworkResult.Loading();

        api.getReadLastEntry(channelIdSignals.toInt(), format, readApiSignals).enqueue(
            object : Callback<ReadLastFieldEntryResponse> {
                override fun onResponse(
                    call: Call<ReadLastFieldEntryResponse>,
                    response: Response<ReadLastFieldEntryResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readSignalsLastEntry.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readSignalsLastEntry.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadLastFieldEntryResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readSignalsLastEntry.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val readSignalsLastFieldEntry =  MutableStateFlow<NetworkResult<ReadLastStatusResponse?>>(NetworkResult.Initial());
    fun getReadLastFieldEntry(fieldId: Int) {
        readSignalsLastFieldEntry.value = NetworkResult.Loading();

        api.getReadLastFieldEntry(channelIdSignals.toInt(), fieldId, format, readApiSignals).enqueue(
            object : Callback<ReadLastStatusResponse> {
                override fun onResponse(
                    call: Call<ReadLastStatusResponse>,
                    response: Response<ReadLastStatusResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readSignalsLastFieldEntry.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readSignalsLastFieldEntry.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadLastStatusResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readSignalsLastFieldEntry.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val readSignalsLastStatus =  MutableStateFlow<NetworkResult<ReadLastStatusResponse?>>(NetworkResult.Initial());
    fun getReadLastStatus() {
        readSignalsLastStatus.value = NetworkResult.Loading();

        api.getReadLastStatus(channelIdSignals.toInt(), format, readApiSignals).enqueue(
            object : Callback<ReadLastStatusResponse> {
                override fun onResponse(
                    call: Call<ReadLastStatusResponse>,
                    response: Response<ReadLastStatusResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readSignalsLastStatus.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readSignalsLastStatus.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadLastStatusResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readSignalsLastStatus.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val writeSignalsData =  MutableStateFlow<NetworkResult<WriteResponse?>>(NetworkResult.Initial());
    fun updateWriteData(data: WriteBody) {
        writeSignalsData.value = NetworkResult.Loading();

        data.api_key = writeApiSignals
        api.updateWriteData(data).enqueue(
            object : Callback<WriteResponse> {
                override fun onResponse(
                    call: Call<WriteResponse>,
                    response: Response<WriteResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            writeSignalsData.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        writeSignalsData.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<WriteResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        writeSignalsData.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

}