package com.example.myiot.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.myiot.model.WriteBody
import com.example.myiot.model.api.ThinkSpeakApiThermostatGLPRepo
import com.example.myiot.view.utils.ThinkSpeakParser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThermostatApiViewModel @Inject constructor(private val repo: ThinkSpeakApiThermostatGLPRepo) :
    ViewModel() {

    val readResultData = repo.readThermostatLastEntry
    val readResultDataSignal = repo.readSignalsLastEntry
    val readNames = repo.readThermostatGLPData

    private val updateFunction: (WriteBody) -> Unit = { body ->
        repo.updateWriteDataThermostatGLP(body)
    }
    private val common = Common(updateFunction)

    init {
        repo.getReadLastEntryThermostatGLP()
        repo.getReadLastEntry() //Signals
        repo.getReadDataThermostatGLP()
    }

    fun writeThermostatGLP(updateWriteData: List<MutableState<Boolean>>) {
        val booleanToIntConverter: (MutableState<Boolean>) -> Int? = { state ->
            ThinkSpeakParser.booleanToInt(state.value)
        }
        Log.d("TEST>Thermostat", "HERE $booleanToIntConverter")
        common.writeThinkSpeak(updateWriteData, booleanToIntConverter)
    }

    fun updateThermostatGLP() {
        repo.getReadLastEntryThermostatGLP()
        repo.getReadLastEntry()
    }

}