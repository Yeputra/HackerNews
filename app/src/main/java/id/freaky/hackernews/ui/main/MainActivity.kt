package id.freaky.hackernews.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.freaky.hackernews.R
import id.freaky.hackernews.di.Injection
import id.freaky.hackernews.model.StoriesModel
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private var stories = ArrayList<StoriesModel>()
    private lateinit var mAdapter: MainAdapter

    lateinit var rvTopStories: RecyclerView
    lateinit var tvTitleFavSories: TextView
    lateinit var llMain: LinearLayout
    lateinit var pbMain: ProgressBar

    override fun onResume() {
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        getData()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this,
            this.let { Injection.provideViewModelFactory(this) })
            .get(MainViewModel::class.java)
    }

    private fun getData() {
        viewModel.getTopStories().observe(this, Observer { topStories ->
            var counter:Int = 0
            for (id in topStories)
                viewModel.getDetailStories(id).observe(this, Observer { detailStories ->
                    stories.add(detailStories)
                    counter += 1
                    if (counter == topStories.count()){
                        initView()
                    }
                })
        })
    }

    private fun initView(){
        this.supportActionBar!!.setTitle("Top Stories")

        tvTitleFavSories = find(R.id.tv_title_fav_stories)
        llMain = find(R.id.ll_main)
        pbMain = find(R.id.pb_main)
        rvTopStories = find(R.id.rv_top_stories)

        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        tvTitleFavSories.text = sharedPref.getString(getString(R.string.story_faved),"Please choosee favorite stories")
        llMain.visibility = View.VISIBLE
        pbMain.visibility = View.GONE

        mAdapter = MainAdapter(this, stories)
        rvTopStories.layoutManager = LinearLayoutManager(this)
        rvTopStories.setHasFixedSize(true)
        rvTopStories.adapter = mAdapter

    }
}