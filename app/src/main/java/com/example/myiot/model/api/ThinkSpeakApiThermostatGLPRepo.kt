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

class ThinkSpeakApiThermostatGLPRepo(private val api: ThinkSpeakApi) {
    private val format = "json"

    private val channelIdThermostat = BuildConfig.CHANNEL_ID_THERMOSTAT_GLP_THINK_SPEAK
    private val readApiThermostat = BuildConfig.READ_KEY_THERMOSTAT_GLP
    private val writeApiThermostat = BuildConfig.WRITE_KEY_THERMOSTAT_GLP

    private val channelIdSignals = BuildConfig.CHANNEL_ID_SIGNALS_THINK_SPEAK
    private val readApiSignals = BuildConfig.READ_KEY_SIGNALS
    private val writeApiSignals = BuildConfig.WRITE_KEY_SIGNALS

    val readThermostatGLPData =  MutableStateFlow<NetworkResult<ReadDataResponse?>>(NetworkResult.Initial());

    fun getReadDataThermostatGLP() {
       readThermostatGLPData.value = NetworkResult.Loading();

        api.getReadData(channelIdThermostat.toInt(), format, readApiThermostat).enqueue(
            object : Callback<ReadDataResponse> {
                override fun onResponse(
                    call: Call<ReadDataResponse>,
                    response: Response<ReadDataResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readThermostatGLPData.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readThermostatGLPData.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadDataResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readThermostatGLPData.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val readThermostatField =  MutableStateFlow<NetworkResult<ReadFieldResponse?>>(NetworkResult.Initial());
    fun getReadFieldThermostatGLP(fieldId: Int) {
        readThermostatField.value = NetworkResult.Loading();

        api.getReadField(channelIdThermostat.toInt(), fieldId, format, readApiThermostat).enqueue(
            object : Callback<ReadFieldResponse> {
                override fun onResponse(
                    call: Call<ReadFieldResponse>,
                    response: Response<ReadFieldResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readThermostatField.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readThermostatField.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadFieldResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readThermostatField.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val readThermostatStatus =  MutableStateFlow<NetworkResult<ReadLastEntryResponse?>>(NetworkResult.Initial());
    fun getReadStatusThermostatGLP(fieldId: Int) {
        readThermostatStatus.value = NetworkResult.Loading();

        api.getReadStatus(channelIdThermostat.toInt(), format, readApiThermostat).enqueue(
            object : Callback<ReadLastEntryResponse> {
                override fun onResponse(
                    call: Call<ReadLastEntryResponse>,
                    response: Response<ReadLastEntryResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readThermostatStatus.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readThermostatStatus.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadLastEntryResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readThermostatStatus.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val readThermostatLastEntry =  MutableStateFlow<NetworkResult<ReadLastFieldEntryResponse?>>(NetworkResult.Initial());
    fun getReadLastEntryThermostatGLP() {
        readThermostatLastEntry.value = NetworkResult.Loading();

        api.getReadLastEntry(channelIdThermostat.toInt(), format, readApiThermostat).enqueue(
            object : Callback<ReadLastFieldEntryResponse> {
                override fun onResponse(
                    call: Call<ReadLastFieldEntryResponse>,
                    response: Response<ReadLastFieldEntryResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readThermostatLastEntry.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readThermostatLastEntry.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadLastFieldEntryResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readThermostatLastEntry.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val readThermostatLastFieldEntry =  MutableStateFlow<NetworkResult<ReadLastStatusResponse?>>(NetworkResult.Initial());
    fun getReadLastFieldEntryThermostatGLP(fieldId: Int) {
        readThermostatLastFieldEntry.value = NetworkResult.Loading();

        api.getReadLastFieldEntry(channelIdThermostat.toInt(), fieldId, format, readApiThermostat).enqueue(
            object : Callback<ReadLastStatusResponse> {
                override fun onResponse(
                    call: Call<ReadLastStatusResponse>,
                    response: Response<ReadLastStatusResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readThermostatLastFieldEntry.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readThermostatLastFieldEntry.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadLastStatusResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readThermostatLastFieldEntry.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val readThermostatLastStatus =  MutableStateFlow<NetworkResult<ReadLastStatusResponse?>>(NetworkResult.Initial());
    fun getReadLastStatusThermostatGLP() {
        readThermostatLastStatus.value = NetworkResult.Loading();

        api.getReadLastStatus(channelIdThermostat.toInt(), format, readApiThermostat).enqueue(
            object : Callback<ReadLastStatusResponse> {
                override fun onResponse(
                    call: Call<ReadLastStatusResponse>,
                    response: Response<ReadLastStatusResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            readThermostatLastStatus.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        readThermostatLastStatus.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ReadLastStatusResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        readThermostatLastStatus.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    val writeThermostatData =  MutableStateFlow<NetworkResult<WriteResponse?>>(NetworkResult.Initial());
    fun updateWriteDataThermostatGLP(data: WriteBody) {
        writeThermostatData.value = NetworkResult.Loading();
        data.api_key = writeApiThermostat

        Log.d("TEST>pre update", "HERE $data")
        api.updateWriteData(data).enqueue(
            object : Callback<WriteResponse> {
                override fun onResponse(
                    call: Call<WriteResponse>,
                    response: Response<WriteResponse>
                ) {
                    Log.d("TEST>onResponseThemp", "HERE $response")
                    if (response.isSuccessful) {
                        response.body()?.let {
                            writeThermostatData.value = NetworkResult.Success(it)
                        }
                    } else {
                        // Handle the error case as per your requirement
                        writeThermostatData.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<WriteResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        writeThermostatData.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }


//    Signals or numbers
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