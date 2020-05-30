package id.freaky.hackernews.ui.detail

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.freaky.hackernews.R
import id.freaky.hackernews.di.Injection
import id.freaky.hackernews.model.CommentModel
import id.freaky.hackernews.model.StoriesModel
import org.jetbrains.anko.find

class DetailActivity : AppCompatActivity() {

    private var id: Int = 0
    private lateinit var viewModel: DetailViewModel
    private var comments = ArrayList<CommentModel>()
    private lateinit var mAdapter: CommentAdapter
    private var data = StoriesModel()

    lateinit var llDetail:LinearLayout
    lateinit var pbDetail:ProgressBar
    lateinit var tvTitleDetail: TextView
    lateinit var tvAuthorDetail: TextView
    lateinit var tvDateDetail: TextView
    lateinit var tvDescriptionDetail: TextView
    lateinit var rvComment: RecyclerView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val extras = intent.extras
        if (extras != null) {
            id = extras.getInt("id")
            data = intent.getParcelableExtra("data")
            initView()
            setupViewModel()
            getComment()
        }
    }

    private fun initView(){
        llDetail = find(R.id.ll_detail)
        pbDetail = find(R.id.pb_detail)
        tvTitleDetail = find(R.id.tv_title_detail)
        tvAuthorDetail = find(R.id.tv_author_detail)
        tvDateDetail = find(R.id.tv_date_detail)
        tvDescriptionDetail = find(R.id.ttv_description_detail)
        rvComment = find(R.id.rv_comment_detail)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this,
            this.let { Injection.provideViewModelFactory(this) })
            .get(DetailViewModel::class.java)
    }

    private fun getComment() {
        viewModel.getDetailStories(id).observe(this, Observer { detailStories ->

            tvTitleDetail.text = detailStories.title
            tvAuthorDetail.text = "by: " + detailStories.by

            val sdf = java.text.SimpleDateFormat("dd-MM-yyyy")
            val date = java.util.Date(detailStories.time?.toLong()!!.times(1000))
            sdf.format(date)

            tvDateDetail.text = sdf.format(date)
            tvDescriptionDetail.text = detailStories.url

            var counter = 0
            for (commentCount in detailStories.kids!!) {
                viewModel.getComment(commentCount!!)
                    .observe(this, Observer { detailComment ->
                        comments.add(detailComment)
                        counter += 1
                        if (counter == detailStories.kids.count()){
                            populateData()
                        }
                    })
            }
        })
    }

    private fun populateData(){
        this.supportActionBar!!.setTitle("Story Detail")
        llDetail.visibility = View.VISIBLE
        pbDetail.visibility = View.GONE
        mAdapter = CommentAdapter(this, comments)
        rvComment.layoutManager = LinearLayoutManager(this)
        rvComment.setHasFixedSize(true)
        rvComment.adapter = mAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()

        if (id == R.id.action_fav) {

            when(data.isFaved){
                true -> {
                    data.isFaved = false
                    Toast.makeText(this, "Story Deleted from fav", Toast.LENGTH_LONG).show()
                    item?.icon = ContextCompat.getDrawable(this, R.drawable.ic_start_empty)

                }

                false -> {
                    data.isFaved = true
                    Toast.makeText(this, "Story Faved", Toast.LENGTH_LONG).show()
                    item?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_filled)

                    val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
                    with (sharedPref.edit()) {
                        putString(getString(R.string.story_faved), data.title)
                        commit()
                    }
                }
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}