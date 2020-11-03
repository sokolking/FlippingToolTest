package nl.vanzanden.flippingtooltest.ui.recommendations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recommendation.view.*
import nl.vanzanden.flippingtooltest.R
import nl.vanzanden.flippingtooltest.domain.entities.Recommendation
import nl.vanzanden.flippingtooltest.util.loadImageFromUrl

class RecommendationsAdapter(
    private var recommedations: List<Recommendation>,
    private var onRecItemClicked: (recommendation: Recommendation) -> Unit
) : RecyclerView.Adapter<RecommendationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recommendation, parent, false)
        return RecommendationViewHolder(view)
    }

    override fun getItemCount() = recommedations.size

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        holder.bindRecommendationItem(recommedations[position], onRecItemClicked)
    }

}

class RecommendationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var mLastClickTime = 0L

    fun bindRecommendationItem(
        recommendation: Recommendation,
        onRecommendationClicked: (recItem: Recommendation) -> Unit
    ) {
        var clickable = false
        with(recommendation) {
            itemView.apply {
                name.text = recommendation.name
                if (recommendation.name == "Aldi Nord") {
                    clickable = true
                    root.setCardBackgroundColor(
                        ContextCompat.getColor(context, R.color.green)
                    )
                    recommendation.newsId = 90049
                } else {
                    clickable = false
                    root.setCardBackgroundColor(
                        ContextCompat.getColor(context, R.color.white)
                    )
                    recommendation.newsId = 0
                }
                image.loadImageFromUrl(recommendation.getLogoUrl())
                root.setOnClickListener {
                    if (!clickable) return@setOnClickListener
                    val now = System.currentTimeMillis()
                    if (now - mLastClickTime > 1000) {
                        mLastClickTime = now
                        onRecommendationClicked.invoke(this@with)
                    }
                }
            }
        }
    }
}
