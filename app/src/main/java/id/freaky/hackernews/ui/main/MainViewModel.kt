package id.freaky.hackernews.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.freaky.hackernews.model.StoriesModel
import id.freaky.hackernews.repository.Repository


class MainViewModel(val repository: Repository) : ViewModel() {

    private var favedStories = MutableLiveData<String>()

    fun getTopStories(): LiveData<List<Int>> {
        return repository.getTopStories()
    }

    fun getDetailStories(id: Int): LiveData<StoriesModel>{
        return repository.getDetailStories(id)
    }

    fun getFaveStories(): MutableLiveData<String> {
        favedStories.value = repository.getSavedStory()
        return favedStories
    }
}