package id.freaky.hackernews.model

import com.google.gson.annotations.SerializedName

data class CommentModel(

	@field:SerializedName("parent")
	val parent: Int? = null,

	@field:SerializedName("by")
	val by: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("time")
	val time: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("kids")
	val kids: List<Int?>? = null
)
