package com.example.utsaplikasiinfopariwisata

import com.example.utsaplikasiinfopariwisata.model.Tourism
import com.example.utsaplikasiinfopariwisata.R

// Data global (bisa diakses dari semua Activity)
object DataTourism {
    val listData = listOf(
    Tourism(
        id = 1,
        name = "Danau Toba",
        location = "Sumatera Utara",
        description = "Danau Toba adalah danau vulkanik terbesar di Asia Tenggara.",
        image = R.drawable.danau_toba,
        phone = "081234567890",
        time = "08.00 - 18.00",
        rating = 4.7,
        price = "Rp 10.000",
        isFavorite = false
    ),
    Tourism(
        id = 2,
        name = "Candi Borobudur",
        location = "Magelang, Jawa Tengah",
        description = "Candi Borobudur adalah candi Buddha terbesar di dunia.",
        image = R.drawable.candi_borobudur,
        phone = "081298765432",
        time = "07.00 - 17.00",
        rating = 4.8,
        price = "Rp 50.000",
        isFavorite = false
    ),
    Tourism(
        id = 3,
        name = "Raja Ampat",
        location = "Papua Barat",
        description = "Kepulauan yang terkenal dengan keindahan bawah laut dan terumbu karang terbaik di dunia, menjadi surga bagi para penyelam dan fotografer alam.",
        image = R.drawable.raja_ampat,
        phone = "0852-42020 251",
        time = "09:00 - 17.00",
        rating = 4.5,
        price = "Rp 700.000",
        isFavorite = false
    ),
    Tourism(
        id = 4,
        name = "Labuan Bajo",
        location = "Nusa Tenggara Timur",
        description = "Gerbang menuju Taman Nasional Komodo, tempat habitat asli komodo hidup. Selain itu, terdapat pantai pasir pink dan spot snorkeling yang menakjubkan.",
        image = R.drawable.labuan_bajo,
        phone = "(0385) 41006",
        time = "08.00 - 17.00",
        rating = 4.8,
        price = "Rp 5.000 - 50.000",
        isFavorite = false
    ),
    Tourism(
        id = 5,
        name = "Gunung Bromo",
        location = "Probolinggo, Jawa Timur",
        description = "Gunung berapi aktif yang terkenal dengan lautan pasir dan pemandangan sunrise spektakuler dari Penanjakan, menjadi ikon wisata alam Jawa Timur.",
        image = R.drawable.gunung_bromo,
        phone = "08123-5466-831",
        time = "08.00 - 17.00",
        rating = 4.6,
        price = "Rp 54.000",
        isFavorite = false
    ),
    Tourism(
        id = 6,
        name = "Kawah Ijen",
        location = "Banyuwangi, Jawa Timur",
        description = "Gunung dengan fenomena api biru alami dan danau kawah berwarna toska. Penambang belerang tradisional menambah daya tarik unik kawasan ini.",
        image =   R.drawable.kawah_ijen,
        phone = "08123-4571-135",
        time = "08.00 - 17.00",
        rating = 4.7,
        price = "Rp 5.000",
        isFavorite = false
    ),
    Tourism(
        id = 7,
        name = "Pantai Kuta",
        location = "Bali",
        description = "Pantai paling populer di Bali dengan pasir putih lembut, ombak ideal untuk berselancar, dan pemandangan sunset yang memukau setiap sore hari.",
        image = R.drawable.pantai_kuta,
        phone = "Tidak ada Nomor Telepon",
        time = "24 Jam",
        rating = 4.9,
        price = "Rp 2.000 - 5.000",
        isFavorite = false
    ),
    Tourism(
        id = 8,
        name = "Wakatobi",
        location = "Sulawesi Tenggara",
        description = "Taman Nasional Laut Wakatobi memiliki biodiversitas laut yang luar biasa, menjadikannya salah satu lokasi diving terbaik di dunia.",
        image = R.drawable.wakatobi,
        phone =  "0811-4057-113",
        time = "Senin–Kamis 07:30–16:00 dan Jumat 07:30–16:30 ",
        rating = 4.6,
        price = "Rp 5.000",
        isFavorite = false
        )
    )
}
val globalTourismData: List<Tourism> = DataTourism.listData
