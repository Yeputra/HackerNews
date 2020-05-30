package id.freaky.hackernews.repository

import androidx.lifecycle.MutableLiveData
import id.freaky.hackernews.model.CommentModel
import id.freaky.hackernews.model.StoriesModel
import id.freaky.hackernews.service.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository {
    val hackernewsApi = Api.create()

    fun getTopStories(): MutableLiveData<List<Int>>{
        val storiesData: MutableLiveData<List<Int>> = MutableLiveData<List<Int>>()
        hackernewsApi?.getTopPosts()?.enqueue(object : Callback<List<Int>> {
            override fun onResponse(
                call: Call<List<Int>>,
                response: Response<List<Int>>
            ) {
                if (response.isSuccessful()) {
                    storiesData?.setValue(response.body())
                }
            }

            override fun onFailure(
                call: Call<List<Int>>,
                t: Throwable
            ) {
                storiesData?.setValue(null)
            }
        })
        return storiesData
    }

    fun getDetailStories(id:Int): MutableLiveData<StoriesModel>{
        val storiesData: MutableLiveData<StoriesModel> = MutableLiveData<StoriesModel>()
        hackernewsApi?.getDetailPost(id)?.enqueue(object : Callback<StoriesModel> {
            override fun onResponse(
                call: Call<StoriesModel>,
                response: Response<StoriesModel>
            ) {
                if (response.isSuccessful()) {
                    storiesData?.setValue(response.body())
                }
            }

            override fun onFailure(
                call: Call<StoriesModel>,
                t: Throwable
            ) {
                storiesData?.setValue(null)
            }
        })
        return storiesData
    }

    fun getDetailComment(id:Int): MutableLiveData<CommentModel>{
        val commmentData: MutableLiveData<CommentModel> = MutableLiveData<CommentModel>()
        hackernewsApi?.getComment(id)?.enqueue(object : Callback<CommentModel> {
            override fun onResponse(
                call: Call<CommentModel>,
                response: Response<CommentModel>
            ) {
                if (response.isSuccessful()) {
                    commmentData?.setValue(response.body())
                }
            }

            override fun onFailure(
                call: Call<CommentModel>,
                t: Throwable
            ) {
                commmentData?.setValue(null)
            }
        })
        return commmentData
    }
}