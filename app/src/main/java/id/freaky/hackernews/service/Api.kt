package id.freaky.hackernews.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {

    fun create(): ApiService {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl("https://hacker-news.firebaseio.com/v0/")
            .build()

        return retrofit.create(ApiService::class.java)
    }

}