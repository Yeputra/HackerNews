package id.freaky.hackernews.ui.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.freaky.hackernews.model.CommentModel
import id.freaky.hackernews.model.StoriesModel
import id.freaky.hackernews.repository.Repository

class DetailViewModel(val repository: Repository): ViewModel() {

    private var favedStories = MutableLiveData<String>()

    fun getDetailStories(id: Int): LiveData<StoriesModel>{
        return repository.getDetailStories(id)
    }

    fun getComment(id: Int): LiveData<CommentModel> {
        return repository.getDetailComment(id)
    }

    fun faveStories(title: String) {
        repository.faveStory(title)
    }

    fun getFaveStories(): MutableLiveData<String> {
        favedStories.value = repository.getSavedStory()
        return favedStories
    }
}