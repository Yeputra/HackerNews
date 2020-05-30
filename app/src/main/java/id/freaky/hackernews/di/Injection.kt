package id.freaky.hackernews.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import id.freaky.hackernews.repository.RemoteRepository
import id.freaky.hackernews.repository.Repository
import id.freaky.hackernews.viewmodel.ViewModelFactory

object Injection {
    private fun provideRepository(context: Context): Repository {

        val remoteRepository = RemoteRepository()

        return Repository(remoteRepository)
    }

    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideRepository(context))
    }
}