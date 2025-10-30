package com.example.utsaplikasiinfopariwisata

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.utsaplikasiinfopariwisata.databinding.ActivityDetailBinding
import com.example.utsaplikasiinfopariwisata.model.Tourism

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil data dari Intent (Penting: harus diambil di awal)
        val tourismItem = intent.getParcelableExtra<Tourism>("tourism")


        // 🟢 BLOK NAVIGASI BottomNavigationView (DITEMPATKAN DI SINI)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNav)

        // Setup Listener untuk perpindahan halaman
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Aksi ini akan pindah ke Home dan menutup Detail.
                    val intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    startActivity(intent)
                    finish()
                    true // Mengembalikan true untuk menandakan event ditangani
                }
                R.id.nav_favorites -> {
                    // Aksi ini akan pindah ke Favorite dan menutup Detail.
                    val intent = Intent(this, FavoriteActivity::class.java)
                    startActivity(intent)
                    finish()
                    true // Mengembalikan true untuk menandakan event ditangani
                }
                else -> false
            }
        }
        // 🟢 AKHIR BLOK NAVIGASI


        if (tourismItem != null) {
            // --- Action Bar ---
            supportActionBar?.title = tourismItem.name

            // --- Tampilkan data utama ---
            binding.ivPhoto.setImageResource(tourismItem.image)
            binding.tvLocation.text = tourismItem.location
            binding.tvDescription.text = tourismItem.description

            // ... (Kode Data Dummy Tambahan) ...

            // ... (Kode Listener Klik telepon dan Google Maps) ...

            // --- Toast notifikasi ---
            Toast.makeText(this, "Anda melihat detail ${tourismItem.name}", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Gagal memuat detail tempat wisata.", Toast.LENGTH_SHORT).show()
            finish()
        }
    } // ❌ KURUNG KURAWAL onCreate() HANYA DITUTUP SATU KALI DI SINI.

    // --- Tombol kembali di Action Bar ---
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
// ❌ KURUNG KURAWAL Class DetailActivity HANYA DITUTUP SATU KALI DI BARIS PALING AKHIR.

