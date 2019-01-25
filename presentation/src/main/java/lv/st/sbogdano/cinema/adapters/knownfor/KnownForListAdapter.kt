package lv.st.sbogdano.cinema.adapters.knownfor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.databinding.FragmentKnownForListItemBinding
import lv.st.sbogdano.domain.model.MovieCreditDomainModel

class KnownForListAdapter(
    private val items: List<MovieCreditDomainModel>,
    private val imageSize: Pair<Int, Int>
) : RecyclerView.Adapter<KnownForListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: FragmentKnownForListItemBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_known_for_list_item, parent, false)
        setImageViewLayoutParams(binding)
        return ViewHolder(binding)
    }

    private fun setImageViewLayoutParams(binding: FragmentKnownForListItemBinding) {
        val imageView = binding.imageMoviePoster
        val layoutParams: ViewGroup.LayoutParams = imageView.layoutParams
        val (width, height) = imageSize
        layoutParams.width = width
        layoutParams.height = height
        imageView.requestLayout()
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.movieCredits = items[position]
        holder.binding.executePendingBindings()
    }

    inner class ViewHolder(val binding: FragmentKnownForListItemBinding) : RecyclerView.ViewHolder(binding.root)
}