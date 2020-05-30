package id.freaky.hackernews.repository

import androidx.lifecycle.MutableLiveData
import id.freaky.hackernews.model.CommentModel
import id.freaky.hackernews.model.StoriesModel

class Repository(val remoteRepository: RemoteRepository): NewsDataSource {

    override fun getTopStories(): MutableLiveData<List<Int>> {
        return remoteRepository.getTopStories()
    }

    override fun getDetailStories(id: Int): MutableLiveData<StoriesModel> {
        return remoteRepository.getDetailStories(id)
    }

    override fun getDetailComment(id: Int): MutableLiveData<CommentModel> {
        return remoteRepository.getDetailComment(id)
    }
}