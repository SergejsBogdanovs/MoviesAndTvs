package lv.st.sbogdano.data.repository.mapper

import lv.st.sbogdano.data.local.model.ReviewLocalModel
import lv.st.sbogdano.data.remote.model.ReviewRemoteModel

class ReviewsListMapper {

    private fun toLocal(review: ReviewRemoteModel) =
            ReviewLocalModel(review.id, review.author, review.content)

    fun toLocal(items: List<ReviewRemoteModel>) = items.map { toLocal(it) }
}