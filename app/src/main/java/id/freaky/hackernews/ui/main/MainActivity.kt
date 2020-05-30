package id.freaky.hackernews.ui.main

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import id.freaky.hackernews.R
import id.freaky.hackernews.di.Injection
import id.freaky.hackernews.model.StoriesModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private var stories = ArrayList<StoriesModel>()
    private lateinit var mAdapter: MainAdapter

    @BindView(R.id.rv_top_stories)lateinit var rvTopStories: RecyclerView
    @BindView(R.id.tv_title_fav_stories)lateinit var tvTitleFavSories: TextView
    @BindView(R.id.ll_main)lateinit var llMain: LinearLayout
    @BindView(R.id.pb_main)lateinit var pbMain: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

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
        llMain.visibility = View.VISIBLE
        pbMain.visibility = View.GONE
        mAdapter = MainAdapter(this, stories)
        rvTopStories.layoutManager = LinearLayoutManager(this)
        rvTopStories.setHasFixedSize(true)
        rvTopStories.adapter = mAdapter
    }
}