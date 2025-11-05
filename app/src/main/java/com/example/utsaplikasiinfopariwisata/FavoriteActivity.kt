package com.example.utsaplikasiinfopariwisata

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.utsaplikasiinfopariwisata.adapter.TourismAdapter
import com.example.utsaplikasiinfopariwisata.databinding.ActivityFavoriteBinding
import com.example.utsaplikasiinfopariwisata.model.Tourism
import com.google.android.material.snackbar.Snackbar

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var tourismAdapter: TourismAdapter
    private var favoriteList = mutableListOf<Tourism>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🟢 Set judul halaman
        supportActionBar?.title = "Destinasi Favorit"

        setupRecyclerView()
        loadFavoriteData()
    }

    override fun onResume() {
        super.onResume()
        loadFavoriteData()
    }

    private fun setupRecyclerView() {
        tourismAdapter = TourismAdapter(
            listTourism = mutableListOf(),
            onItemClick = { item ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("tourism", item)
                startActivity(intent)
            },
            onFavoriteClick = { clickedTourism ->
                clickedTourism.isFavorite = !clickedTourism.isFavorite
                loadFavoriteData() // refresh halaman favorit
            }
        )
        binding.rvFavorites.layoutManager = LinearLayoutManager(this)
        binding.rvFavorites.adapter = tourismAdapter
    }

    private fun loadFavoriteData() {
        val favoriteList = MainActivity.globalTourismData.filter { it.isFavorite }.toMutableList()

        if (favoriteList.isEmpty()) {
            binding.rvFavorites.visibility = View.GONE
            binding.tvEmpty.visibility = View.VISIBLE
        } else {
            binding.rvFavorites.visibility = View.VISIBLE
            binding.tvEmpty.visibility = View.GONE
            tourismAdapter.updateData(favoriteList)
        }
    }
}
