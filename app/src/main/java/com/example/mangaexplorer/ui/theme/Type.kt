package com.example.mangaexplorer.ui.theme


import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mangaexplorer.R

val Bebas = FontFamily(
    Font(R.font.bebas_neue)
)
val poppins_medium = FontFamily(
    Font(R.font.poppins_medium)
)
val poppins_regular = FontFamily(
    Font(R.font.poppins_regular)
)


// Set of Material typography styles to start with
val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = Bebas,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
    ),

    titleMedium = TextStyle(
        fontFamily = poppins_medium,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
    ),

    bodyLarge = TextStyle(
        fontFamily = poppins_regular,
        fontSize = 16.sp,
    ),

    bodyMedium = TextStyle(
        fontFamily = poppins_medium,
        fontSize = 14.sp,
        color = TextPrimary
    )

)