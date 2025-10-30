package com.example.utsaplikasiinfopariwisata

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.utsaplikasiinfopariwisata.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.example.utsaplikasiinfopariwisata.adapter.TourismAdapter
import com.example.utsaplikasiinfopariwisata.model.Tourism

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TourismAdapter
    private lateinit var data: List<Tourism>
    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🟢 TAMBAHKAN INISIALISASI INI DI SINI!
        bottomNavigation = binding.bottomNav
        // Ini memastikan saat MainActivity dibuat, ikon Home aktif.
        bottomNavigation.menu.findItem(R.id.nav_home)?.isChecked = true
        bottomNavigation.menu.findItem(R.id.nav_favorites)?.isChecked = false


        // 🟢 1. Setup SearchView
        val searchView = binding.searchView
        val searchSrcTextId =
            searchView.context.resources.getIdentifier("android:id/search_src_text", null, null)
        val searchEditText = searchView.findViewById<EditText>(searchSrcTextId)

        searchEditText?.apply {
            setTextColor(Color.BLACK)
            setHintTextColor(Color.DKGRAY)
            isCursorVisible = true
            setBackgroundColor(Color.TRANSPARENT)
        }

        val searchMagIconId =
            searchView.context.resources.getIdentifier("android:id/search_mag_icon", null, null)
        val searchMagIcon = searchView.findViewById<ImageView>(searchMagIconId)
        searchMagIcon?.setColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_IN)

        val closeBtnId =
            searchView.context.resources.getIdentifier("android:id/search_close_btn", null, null)
        val closeBtn = searchView.findViewById<ImageView>(closeBtnId)
        closeBtn?.setColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_IN)

        // 🟢 2. Load Data dan Setup RecyclerView
        data = loadSampleData() // Pastikan fungsi ini tersedia
        adapter = TourismAdapter(data) { item ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("tourism", item)
            startActivity(intent)
        }

        binding.rvTourism.layoutManager = LinearLayoutManager(this)
        binding.rvTourism.adapter = adapter

        // 🟢 3. SearchView Listener
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })

        // 🟢 4. Bottom Navigation Listener (Hanya perlu diset sekali di onCreate)
        bottomNavigation.menu.findItem(R.id.nav_home)?.isChecked = true // 👈 PENTING: Home aktif saat dibuat

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Home diklik, pastikan tetap aktif
                    item.isChecked = true
                    true
                }
                R.id.nav_favorites -> {
                    // Pindah, JANGAN AKTIFKAN DI SINI
                    val intent = Intent(this, FavoriteActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }// ❌ onCreate() TUTUP DENGAN BENAR DI SINI

    private fun loadSampleData(): List<Tourism> {
        return listOf(
            Tourism(
                1,
                "Danau Toba",
                "Sumatera Utara",
                "Danau vulkanik terbesar di dunia yang terbentuk dari letusan supergunung purba Toba, dikelilingi pemandangan pegunungan dan Pulau Samosir di tengahnya.",
                R.drawable.danau_toba,
                "0812-3456-7890",       // nomor telepon
                "08.00 - 17.00",        // jam buka
                4.7,          // rating
                false,           // belum disimpan
                "Rp 0 - 20.000"
            ),
            Tourism(
                2,
                "Candi Borobudur",
                "Magelang, Jawa Tengah",
                "Candi Buddha terbesar di dunia, dengan relief yang menggambarkan ajaran kehidupan Buddha dan pemandangan sunrise yang indah dari puncak stupa.",
                R.drawable.candi_borobudur,
                "0811-2688-000",       // nomor telepon
                "07:30 - 17.00",     // jam buka
                4.8,          // rating
                false,           // belum disimpan
                "Rp 50.000"
            ),
            Tourism(
                3,
                "Raja Ampat",
                "Papua Barat",
                "Kepulauan yang terkenal dengan keindahan bawah laut dan terumbu karang terbaik di dunia, menjadi surga bagi para penyelam dan fotografer alam.",
                R.drawable.raja_ampat,
                "0852-42020 251",       // nomor telepon
                "09:00 - 17.00",        // jam buka
                4.5,          // rating
                false,           // belum disimpan
                "Rp 700.000"
            ),
            Tourism(
                4,
                "Labuan Bajo",
                "Nusa Tenggara Timur",
                "Gerbang menuju Taman Nasional Komodo, tempat habitat asli komodo hidup. Selain itu, terdapat pantai pasir pink dan spot snorkeling yang menakjubkan.",
                R.drawable.labuan_bajo,
                "(0385) 41006",       // nomor telepon
                "08.00 - 17.00",       // jam buka
                4.8,          // rating
                false,           // belum disimpan
                "Rp 5.000 - 50.000"
            ),
            Tourism(
                5,
                "Gunung Bromo",
                "Probolinggo, Jawa Timur",
                "Gunung berapi aktif yang terkenal dengan lautan pasir dan pemandangan sunrise spektakuler dari Penanjakan, menjadi ikon wisata alam Jawa Timur.",
                R.drawable.gunung_bromo,
                "08123-5466-831",       // nomor telepon
                "08.00 - 17.00",        // jam buka
                4.6,          // rating
                false,           // belum disimpan
                "Rp 54.000"
            ),
            Tourism(
                6,
                "Kawah Ijen",
                "Banyuwangi, Jawa Timur",
                "Gunung dengan fenomena api biru alami dan danau kawah berwarna toska. Penambang belerang tradisional menambah daya tarik unik kawasan ini.",
                R.drawable.kawah_ijen,
                "08123-4571-135",       // nomor telepon
                "08.00 - 17.00",        // jam buka
                4.7,          // rating
                false,           // belum disimpan
                "Rp 5.000"
            ),
            Tourism(
                7,
                "Pantai Kuta",
                "Bali",
                "Pantai paling populer di Bali dengan pasir putih lembut, ombak ideal untuk berselancar, dan pemandangan sunset yang memukau setiap sore hari.",
                R.drawable.pantai_kuta,
                "Tidak ada Nomor Telepon",  // nomor telepon
                "24 Jam",        // jam buka
                4.9,          // rating
                false,           // belum disimpan
                "Rp 2.000 - 5.000"
            ),
            Tourism(
                8,
                "Wakatobi",
                "Sulawesi Tenggara",
                "Taman Nasional Laut Wakatobi memiliki biodiversitas laut yang luar biasa, menjadikannya salah satu lokasi diving terbaik di dunia.",
                R.drawable.wakatobi,
                "0811-4057-113",       // nomor telepon
                "Senin–Kamis 07:30–16:00 dan Jumat 07:30–16:30 ",  // jam buka
                4.6,          // rating
                false,          // belum disimpan
                "Rp 5.000"
            )
        )
    }
}

