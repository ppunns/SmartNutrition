package com.example.smartnutrition.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.smartnutrition.R


data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
)

val pages = listOf(
    Page(
        title = "Selamat Datang",
        description = "Aplikasi SmartNutrition untuk mencatat dan melacak apa yang kamu konsumsi setiap hari—cukup dengan scan!",
        image = R.drawable.artwork_3
    ),
    Page(
        title = "Scan & Catat",
        description = "Cukup arahkan kamera ke Buah. Kami akan mengenali dan menyimpan datanya untukmu.",
        image = R.drawable.artwork_3
    ),
    Page(
        title = "Pantau Konsumsimu",
        description = "Lihat apa saja yang sudah kamu konsumsi hari ini, minggu ini, dan atur target sesuai kebutuhanmu.",
        image = R.drawable.artwork_3
    )
)