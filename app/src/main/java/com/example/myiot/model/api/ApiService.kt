package com.example.myiot.model.api
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiService {
    private const val BASE_URL = "https://api.thingspeak.com/"

    private fun getRetrofit(): Retrofit {
        val clientInterceptor = Interceptor { chain ->
            var request: Request = chain.request()
            val url: HttpUrl = request.url.newBuilder()
                .build()

            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }

        val client = OkHttpClient.Builder().addInterceptor(clientInterceptor).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val api: ThinkSpeakApi = getRetrofit().create(ThinkSpeakApi::class.java)
}