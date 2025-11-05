package com.example.utsaplikasiinfopariwisata.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filter.FilterResults
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.utsaplikasiinfopariwisata.R
import com.example.utsaplikasiinfopariwisata.databinding.ItemTourismBinding
import com.example.utsaplikasiinfopariwisata.model.Tourism
import java.util.Locale

class TourismAdapter(
    private var listTourism: MutableList<Tourism>,
    private val onItemClick: (Tourism) -> Unit,
    private val onFavoriteClick: (Tourism) -> Unit
) : RecyclerView.Adapter<TourismAdapter.TourismViewHolder>(), Filterable {

    private var originalList: List<Tourism> = listTourism.toList()
    private var filteredList: MutableList<Tourism> = listTourism.toMutableList()

    inner class TourismViewHolder(val binding: ItemTourismBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Tourism) {
            // Set teks
            binding.tvTitle.text = item.name
            binding.tvLocation.text = item.location
            binding.ivPhoto.setImageResource(item.image)
            binding.tvRating.text = "⭐ ${item.rating}"

            // Set ikon favorit sesuai status
            binding.ivFavorite.setImageResource(
                if (item.isFavorite) R.drawable.ic_star_filled
                else R.drawable.ic_star_border
            )

            // Klik favorit
            binding.ivFavorite.setOnClickListener {
                item.isFavorite = !item.isFavorite
                binding.ivFavorite.setImageResource(
                    if (item.isFavorite) R.drawable.ic_star_filled
                    else R.drawable.ic_star_border
                )
                onFavoriteClick(item)
            }
            // Klik item untuk buka detail
            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourismViewHolder {
        val binding =
            ItemTourismBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TourismViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TourismViewHolder, position: Int) {
        val item = filteredList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = filteredList.size

    // Fungsi untuk update data adapter (misal untuk halaman favorit)
    fun updateData(newList: List<Tourism>) {
        listTourism.clear()
        listTourism.addAll(newList)
        filteredList = listTourism.toMutableList()
        notifyDataSetChanged()
    }

    // Filterable implementation (search)
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.trim()?.lowercase(Locale.getDefault()) ?: ""
                val results = FilterResults()
                results.values = if (query.isEmpty()) {
                    originalList
                } else {
                    originalList.filter { tourism ->
                        tourism.name.lowercase(Locale.getDefault()).contains(query) ||
                                tourism.location.lowercase(Locale.getDefault()).contains(query)
                    }
                }
                return results
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = (results?.values as? List<Tourism>)?.toMutableList() ?: mutableListOf()
                notifyDataSetChanged()
            }
        }
    }
}
