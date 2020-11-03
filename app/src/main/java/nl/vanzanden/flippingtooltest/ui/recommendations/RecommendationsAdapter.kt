package nl.vanzanden.flippingtooltest.ui.recommendations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        with(recommendation) {
            itemView.apply {
                name.text = recommendation.name
                image.loadImageFromUrl(recommendation.getLogoUrl(), centerCrop = true)
                root.setOnClickListener {
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
