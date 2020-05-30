package id.freaky.hackernews.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.freaky.hackernews.model.CommentModel
import id.freaky.hackernews.model.StoriesModel
import id.freaky.hackernews.repository.Repository

class DetailViewModel(val repository: Repository): ViewModel() {

    fun getDetailStories(id: Int): LiveData<StoriesModel>{
        return repository.getDetailStories(id)
    }

    fun getComment(id: Int): LiveData<CommentModel> {
        return repository.getDetailComment(id)
    }
}