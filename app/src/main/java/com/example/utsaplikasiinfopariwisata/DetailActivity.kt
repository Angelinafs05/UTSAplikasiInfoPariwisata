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

class DetailActivity : AppCompatActivity() { // Pastikan Anda mendeklarasikan class

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil data dari Intent
        val tourismItem = intent.getParcelableExtra<Tourism>("tourism")

        // Jika data null, keluar dari halaman
        if (tourismItem == null) {
            Toast.makeText(this, "Gagal memuat detail tempat wisata.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
            // Di dalam blok ini, 'item' DIJAMIN BUKAN null (tipe: Tourism)
            binding.tvName.text = tourismItem.name
            binding.ivPhoto.setImageResource(tourismItem.image)
            binding.tvLocation.text = tourismItem.location
            binding.tvDescription.text = tourismItem.description
            binding.tvPhone.text = tourismItem.phone
            binding.tvTime.text = tourismItem.time
            binding.tvPrice.text = tourismItem.price
            // ...

        // klik nomor -> dial
        binding.tvPhone.setOnClickListener {
            val phoneNumber = binding.tvPhone.text.toString().trim().replace("\\s+".toRegex(), "")
            if (phoneNumber.isNotEmpty() && phoneNumber != "Tidak ada Nomor Telepon") {
                val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialIntent)
            } else {
                Toast.makeText(this, "Nomor telepon tidak tersedia", Toast.LENGTH_SHORT).show()
            }
        }

        // buka maps
        binding.btnMaps.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:0,0?q=${Uri.encode(tourismItem.name)}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
        // Tombol kembali
        binding.btnBackCustom.setOnClickListener {
            finish() // Menutup Activity saat ini
        }
        //Notifikasi
        Toast.makeText(this, "Anda melihat detail ${tourismItem.name}", Toast.LENGTH_SHORT).show()
    }
}



