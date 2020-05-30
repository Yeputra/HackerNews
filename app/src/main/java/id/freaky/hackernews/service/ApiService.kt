package id.freaky.hackernews.service

import id.freaky.hackernews.model.CommentModel
import id.freaky.hackernews.model.StoriesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("topstories.json")
    fun getTopPosts(): Call<List<Int>>

    @GET("item/{id}.json")
    fun getDetailPost(@Path("id") id: Int): Call<StoriesModel>

    @GET("item/{id}.json")
    fun getComment(@Path("id") id: Int): Call<CommentModel>

}