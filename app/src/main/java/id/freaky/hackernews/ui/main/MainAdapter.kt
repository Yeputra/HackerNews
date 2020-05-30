package id.freaky.hackernews.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import id.freaky.hackernews.R
import id.freaky.hackernews.model.StoriesModel
import id.freaky.hackernews.ui.detail.DetailActivity
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class MainAdapter (private val context: Context, private val stories: List<StoriesModel>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_top_stories,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(stories[position])

        holder.itemView.setOnClickListener {
            val item = stories[position]
            context.startActivity<DetailActivity>(
                "id" to item.id,
                "data" to item
            )
        }
    }

    override fun getItemCount(): Int = stories.size

    class ViewHolder(itemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        private val cvItem: CardView = itemView.find(R.id.cv_stories_item)
        private val tvTitle: TextView = itemView.find(R.id.tv_title)
        private val tvAuthor: TextView  = itemView.find(R.id.tv_author)
        private val tvScores: TextView = itemView.find(R.id.tv_scores_count)
        private val tvComment: TextView =  itemView.find(R.id.tv_comment_count)

        fun bindItem(stories: StoriesModel) {
            tvTitle.text = stories.title
            tvAuthor.text = "by: " + stories.by
            tvScores.text = stories.score.toString()
            tvComment.text = stories.kids?.count().toString()
        }
    }

}