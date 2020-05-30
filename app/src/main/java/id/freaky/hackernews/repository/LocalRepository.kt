package id.freaky.hackernews.repository

import android.content.Context

class LocalRepository(context: Context) {

    val favePreferences = context.applicationContext.getSharedPreferences("faved-story", Context.MODE_PRIVATE)
    val lastPreferences = context.applicationContext.getSharedPreferences("last-story", Context.MODE_PRIVATE)

    fun faveStory(title: String){
        val editor = favePreferences.edit()
        editor.putString("faved-story", title)
        editor.apply()
    }

    fun getFaveStory(): String? {
        return favePreferences.getString("faved-story", "Please choose favorite stories")
    }

    fun saveStory(title: String){
        val editor = favePreferences.edit()
        editor.putString("last-story", title)
        editor.apply()
    }

    fun getLastStory(): String? {
        return favePreferences.getString("last-story", "Please choose 1 stories")
    }
}