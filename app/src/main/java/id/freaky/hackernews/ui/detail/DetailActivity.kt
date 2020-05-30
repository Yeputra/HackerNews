package id.freaky.hackernews.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        ButterKnife.bind(this)

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
            tvDateDetail.text = detailStories.time.toString()
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
            Toast.makeText(this, "Item fav Clicked", Toast.LENGTH_LONG).show()
            item?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_filled)
            return true
        }

        return super.onOptionsItemSelected(item)

    }
}