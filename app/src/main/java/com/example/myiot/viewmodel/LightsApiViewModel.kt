package com.example.myiot.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myiot.model.WriteBody
import com.example.myiot.model.api.ThinkSpeakApiLightsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import javax.inject.Inject
import android.util.Log
import androidx.compose.runtime.MutableState
import com.example.myiot.view.utils.ThinkSpeakParser

@HiltViewModel
class LightsApiViewModel @Inject constructor(
    private val repo: ThinkSpeakApiLightsRepo
) : ViewModel() {
    val readNames = repo.readLightsData
    val readResultData = repo.readLightLastEntry

    private val updateFunction: (WriteBody) -> Unit = { body ->
        repo.updateWriteData(body)
    }
    private val common = Common(updateFunction)

//    val queryText = MutableStateFlow("")
//    private val queryInput = Channel<String>(Channel.CONFLATED) // Buffering channel for search input

    init {
        repo.getReadLastEntry();
        repo.getReadData();
    }

    //    private fun retrieveLights() {
//        viewModelScope.launch(Dispatchers.IO) {
//            queryInput.receiveAsFlow()
//                .filter { validateQuery(it) }
//                .debounce(1000)
//                .collect { query ->
//                    repo.fetchData()
//                }
//        }
//    }
//
//    private fun validateQuery(query: String): Boolean = query.length >= 2
//
//    fun onQueryUpdate(input: String) {
//        queryText.value = input
//        queryInput.trySend(input)
//    }

    fun writeLights(updateWriteData: List<MutableState<Boolean>>) {
        val booleanToIntConverter: (MutableState<Boolean>) -> Int? = { state ->
            ThinkSpeakParser.booleanToInt(state.value)
        }
        common.writeThinkSpeak(updateWriteData, booleanToIntConverter)
    }

//    val booleanToIntConverter: (MutableState<Boolean>) -> Int? = { state ->
//        ThinkSpeakParser.booleanToInt(state.value)
//    }


    fun updateLights() {
        repo.getReadLastEntry()
    }
}
