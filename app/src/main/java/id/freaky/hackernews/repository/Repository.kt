package id.freaky.hackernews.repository

import androidx.lifecycle.MutableLiveData
import id.freaky.hackernews.model.CommentModel
import id.freaky.hackernews.model.StoriesModel

class Repository(val remoteRepository: RemoteRepository, val localRepository: LocalRepository): NewsDataSource {

    override fun getTopStories(): MutableLiveData<List<Int>> {
        return remoteRepository.getTopStories()
    }

    override fun getDetailStories(id: Int): MutableLiveData<StoriesModel> {
        return remoteRepository.getDetailStories(id)
    }

    override fun getDetailComment(id: Int): MutableLiveData<CommentModel> {
        return remoteRepository.getDetailComment(id)
    }

    override fun faveStory(title: String) {
        localRepository.faveStory(title)
    }

    override fun getSavedStory(): String? {
        return localRepository.getFaveStory()
    }

    override fun saveStory(title: String) {
        localRepository.saveStory(title)
    }

    override fun getLastStory(): String? {
        return localRepository.getLastStory()
    }
}