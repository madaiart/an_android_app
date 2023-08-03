package com.example.myiot

import com.example.myiot.model.api.ApiService
import com.example.myiot.model.api.ThinkSpeakApiAlertRepo
import com.example.myiot.model.api.ThinkSpeakApiSignalsRepo
import com.example.myiot.model.api.ThinkSpeakApiLightsRepo
import com.example.myiot.model.api.ThinkSpeakApiThermostatGLPRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
    @Provides
    fun provideLightsApiRepo() = ThinkSpeakApiLightsRepo(ApiService.api)

    @Provides
    fun provideThermostatGLPApiRepo() = ThinkSpeakApiThermostatGLPRepo(ApiService.api)

    @Provides
    fun provideSignalsApiRepo() = ThinkSpeakApiSignalsRepo(ApiService.api)

    @Provides
    fun provideAlertApiRepo() = ThinkSpeakApiAlertRepo(ApiService.api)
}