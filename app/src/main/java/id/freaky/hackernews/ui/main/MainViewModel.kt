package id.freaky.hackernews.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.freaky.hackernews.model.StoriesModel
import id.freaky.hackernews.repository.Repository


class MainViewModel(val repository: Repository) : ViewModel() {

    fun getTopStories(): LiveData<List<Int>> {
        return repository.getTopStories()
    }

    fun getDetailStories(id: Int): LiveData<StoriesModel>{
        return repository.getDetailStories(id)
    }
}