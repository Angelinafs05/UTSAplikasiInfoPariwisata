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
            binding.tvPhone.text = tourismItem.phone
            binding.tvTime.text = tourismItem.time
            binding.tvPrice.text = tourismItem.price
            // ...

        when (tourismItem.name) {
            "Danau Toba" -> binding.tvDescription.text = "${tourismItem.description}\n\nFasilitas:\n- Penginapan mulai dari homestay hingga resort tepi danau\n- Restoran dan kafe dengan menu lokal Batak\n- Area parkir luas\n- Penyewaan perahu dan sepeda motor\n- Pusat oleh-oleh khas Batak\n- Spot foto dan area camping."
            "Candi Borobudur" -> binding.tvDescription.text = "${tourismItem.description}\n\nFasilitas:\n- Area parkir luas\n- Museum Borobudur\n- Area kuliner dan toko suvenir\n- Shuttle bus dan pemandu tur\n- Toilet umum dan area istirahat."
            "Raja Ampat" -> binding.tvDescription.text = "${tourismItem.description}\n\nFasilitas:\n- Penginapan eco-resort dan homestay\n- Pusat penyewaan alat snorkeling dan diving\n- Transportasi speedboat antar pulau\n- Restoran seafood\n- Pemandu wisata laut\n- Area konservasi dan edukasi lingkungan."
            "Labuan Bajo" -> binding.tvDescription.text = "${tourismItem.description}\n\nFasilitas:\n- Hotel, villa, dan kapal liveaboard\n- Pelabuhan wisata dan penyewaan kapal\n- Restoran tepi laut\n- Pusat informasi wisata dan diving center\n- Bandara Komodo\n- Pemandu tur dan transportasi lokal."
            "Gunung Bromo" -> binding.tvDescription.text = "${tourismItem.description}\n\nFasilitas:\n- Penginapan sekitar Cemoro Lawang\n- Sewa jeep menuju kawah\n- Area parkir luas\n- Kuda sewaan di lautan pasir\n- Warung dan kafe\n- Pos pengamatan sunrise."
            "Kawah Ijen" -> binding.tvDescription.text = "${tourismItem.description}\n\nFasilitas:\n- Basecamp pendakian dan alat keselamatan\n- Warung dan tempat istirahat\n- Area parkir\n- Pemandu lokal dan porter\n- Homestay sekitar Ijen\n- Pos keamanan dan toilet umum."
            "Pantai Kuta" -> binding.tvDescription.text = "${tourismItem.description}\n\nFasilitas:\n- Hotel, resort, dan vila tepi pantai\n- Restoran, bar, dan beach club\n- Pusat perbelanjaan dan hiburan\n- Sewa papan selancar dan payung pantai\n- Toilet umum dan kamar bilas\n- Pos penjaga pantai dan area parkir."
            "Wakatobi" -> binding.tvDescription.text = "${tourismItem.description}\n\nFasilitas:\n- Resort dan homestay tropis\n- Diving center profesional\n- Transportasi laut antar pulau\n- Restoran seafood\n- Pusat konservasi bahari\n- Bandara Matahora untuk akses wisata."
            else -> binding.tvDescription.text = tourismItem.description
        }

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



