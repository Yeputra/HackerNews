package id.freaky.hackernews.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.freaky.hackernews.repository.Repository
import id.freaky.hackernews.ui.detail.DetailActivity
import id.freaky.hackernews.ui.detail.DetailViewModel
import id.freaky.hackernews.ui.main.MainViewModel

class ViewModelFactory constructor(private val repository: Repository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) ->
                    MainViewModel(repository)
                isAssignableFrom(DetailViewModel::class.java) ->
                    DetailViewModel(repository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

}