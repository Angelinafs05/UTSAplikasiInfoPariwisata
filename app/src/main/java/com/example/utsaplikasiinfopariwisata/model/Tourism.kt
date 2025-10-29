package com.example.utsaplikasiinfopariwisata.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tourism(
    val id: Int,
    val name: String,
    val location: String,
    val description: String,
    val image: Int,
    val phone: String,
    val time: String,
    val rating: Double,          // ⬅️ Tambahan
    var isFavorite: Boolean = false, // ⬅️ Tambahan (default belum favorit)
    val price: String // Tambahan Harga
) : Parcelable
