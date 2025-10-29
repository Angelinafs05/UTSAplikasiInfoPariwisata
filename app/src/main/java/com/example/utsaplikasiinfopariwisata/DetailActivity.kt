package com.example.utsaplikasiinfopariwisata

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.utsaplikasiinfopariwisata.databinding.ActivityDetailBinding
import com.example.utsaplikasiinfopariwisata.model.Tourism

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil data dari Intent
        val tourismItem = intent.getParcelableExtra<Tourism>("tourism")

        if (tourismItem != null) {
            // --- Action Bar ---
            supportActionBar?.title = tourismItem.name
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            // --- Tampilkan data utama ---
            binding.ivPhoto.setImageResource(tourismItem.image)
            binding.tvLocation.text = tourismItem.location
            binding.tvDescription.text = tourismItem.description

            // --- Tambahan: Data Dummy Tambahan (nomor, jam buka, dan harga tiket) ---
            binding.tvPhone.text = tourismItem.phone
            binding.tvTime.text = tourismItem.time
            binding.tvPrice.text = "${tourismItem.price}"

            // === 🟢 FITUR: Klik nomor telepon untuk langsung buka dialer ===
            binding.tvPhone.setOnClickListener {
                val phoneNumber = binding.tvPhone.text.toString().replace("\\s+".toRegex(), "")
                if (phoneNumber.isNotEmpty() && phoneNumber != "Tidak ada Nomor Telepon") {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:$phoneNumber")
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Nomor telepon tidak tersedia", Toast.LENGTH_SHORT).show()
                }
            }

            // --- Tombol menuju Google Maps ---
            binding.btnMaps.setOnClickListener {
                val gmmIntentUri = Uri.parse("geo:0,0?q=${tourismItem.name}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }

            // --- Toast notifikasi ---
            Toast.makeText(this, "Anda melihat detail ${tourismItem.name}", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Gagal memuat detail tempat wisata.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    // --- Tombol kembali di Action Bar ---
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
