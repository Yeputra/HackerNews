package id.freaky.hackernews.repository

import androidx.lifecycle.MutableLiveData
import id.freaky.hackernews.model.CommentModel
import id.freaky.hackernews.model.StoriesModel

interface NewsDataSource {
    fun getTopStories(): MutableLiveData<List<Int>>
    fun getDetailStories(id: Int): MutableLiveData<StoriesModel>
    fun getDetailComment(id: Int): MutableLiveData<CommentModel>
    fun faveStory(title:  String)
    fun getSavedStory(): String?
    fun saveStory(title:  String)
    fun getLastStory(): String?
}