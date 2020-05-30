package id.freaky.hackernews.repository

import android.content.Context
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.lifecycle.MutableLiveData
import id.freaky.hackernews.R

class LocalRepository(context: Context) {

    val sharedpreferences = context.applicationContext.getSharedPreferences("faved-story", Context.MODE_PRIVATE);

    fun faveStory(title: String){
        val editor = sharedpreferences.edit()
        editor.putString("faved-story", title)
        editor.apply()
    }

    fun getFaveStory(): String? {
        return sharedpreferences.getString("faved-story", "Please chosee favorite stories")
    }
}