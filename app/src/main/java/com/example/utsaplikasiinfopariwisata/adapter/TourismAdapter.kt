package com.example.utsaplikasiinfopariwisata.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.utsaplikasiinfopariwisata.R
import com.example.utsaplikasiinfopariwisata.databinding.ItemTourismBinding
import com.example.utsaplikasiinfopariwisata.model.Tourism
import java.util.Locale

class TourismAdapter(
    private var originalList: List<Tourism>,
    private val onItemClick: (Tourism) -> Unit
) : RecyclerView.Adapter<TourismAdapter.ViewHolder>(), Filterable {

    private var filteredList: MutableList<Tourism> = originalList.toMutableList()

    inner class ViewHolder(val binding: ItemTourismBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Tourism) {
            binding.tvTitle.text = item.name
            binding.tvLocation.text = item.location
            binding.ivPhoto.setImageResource(item.image)

            // Tampilkan rating tanpa RatingBar
            binding.tvRating.text = "⭐ ${item.rating}"

            // Klik item untuk buka detail
            binding.root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTourismBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = filteredList[position]  // gunakan filteredList, bukan data
        holder.bind(item) // panggil fungsi bind di ViewHolder
    }

    override fun getItemCount(): Int = filteredList.size

    // Filterable implementation (search)
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.trim()?.lowercase(Locale.getDefault()) ?: ""
                val results = FilterResults()
                results.values = if (query.isEmpty()) {
                    originalList
                } else {
                    originalList.filter {
                        it.name.lowercase(Locale.getDefault()).contains(query) ||
                                it.location.lowercase(Locale.getDefault()).contains(query)
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
