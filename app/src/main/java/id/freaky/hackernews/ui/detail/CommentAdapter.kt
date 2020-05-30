package id.freaky.hackernews.ui.detail

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import id.freaky.hackernews.R
import id.freaky.hackernews.model.CommentModel
import org.jetbrains.anko.find

class CommentAdapter (private val context: Context, private val comments: List<CommentModel>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_comment,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(comments[position])

        holder.itemView.setOnClickListener {
            val item = comments[position]
        }
    }

    override fun getItemCount(): Int = comments.size

    class ViewHolder(itemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        private val tvAuthor: TextView = itemView.find(R.id.tv_author_comment)
        private val tvComment: TextView =  itemView.find(R.id.tv_comment)

        fun bindItem(comments: CommentModel) {
            tvAuthor.text = comments.by
            if (comments.text != null) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    tvComment.text = Html.fromHtml(comments.text,Html.FROM_HTML_MODE_LEGACY)
                } else {
                    tvComment.text = Html.fromHtml(comments.text)
                }
            } else {
                tvComment.text = "-"
            }
        }
    }
}