package com.example.utsaplikasiinfopariwisata

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.utsaplikasiinfopariwisata.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView // ⬅️ Pastikan ini ada

// Ganti nama class sesuai nama package Anda (biarkan saja jika sudah benar)
class FavoriteActivity : AppCompatActivity() {

    // Asumsi: Jika Anda menggunakan ViewBinding, tambahkan deklarasi binding di sini.
    // private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite) // Atau binding.root jika pakai ViewBinding

        // 🟢 BLOK NAVIGASI BottomNavigationView YANG BARU
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNav)

        // 1. SET STATUS AKTIF: Favorite harus AKTIF (Efek Hitam)
        // DALAM FavoriteActivity.kt -> Listener R.id.nav_home
        bottomNavigation.setOnItemSelectedListener { item -> // <--- Dimulai di sini
            when (item.itemId) {
                // BARIS YANG ANDA KIRIM HARUS ADA DI SINI ⬇️
                R.id.nav_home -> {
                    // 1. Non-aktifkan Favorite sebelum pindah
                    bottomNavigation.menu.findItem(R.id.nav_favorites)?.isChecked = false

                    // 2. Pindah ke MainActivity
                    val intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)

                    // 3. Panggil fungsi animasi, yang mengembalikan Unit
                    overridePendingTransition(0, 0)

                    // 4. Tutup Activity
                    finish()

                    // 5. BARIS INI (atau di sekitarnya) HARUS MENJADI SATU-SATUNYA RETURN VALUE
                    true // 🟢 PASTIKAN INI ADALAH EKSPRESI TERAKHIR SEBELUM KURUNG KURAWAL PENUTUP
                }

                R.id.nav_favorites -> {
                    true// ... kode item Favorite ...
                }

                else -> false
            }
        }
        // 🟢 AKHIR BLOK NAVIGASI
    }
}


