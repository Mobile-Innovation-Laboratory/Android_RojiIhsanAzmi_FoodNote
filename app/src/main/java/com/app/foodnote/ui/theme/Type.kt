package com.app.foodnote.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.app.foodnote.R

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val robotoFamily = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_italic, FontWeight.Normal, FontStyle.Italic)
)

val parkinsansFamily = FontFamily(
    Font(R.font.parkinsans_bold, FontWeight.Bold),
    Font(R.font.parkinsans_extrabold, FontWeight.ExtraBold),
    Font(R.font.parkinsans_semibold, FontWeight.SemiBold),
    Font(R.font.parkinsans_light, FontWeight.Light),
    Font(R.font.parkinsans_medium, FontWeight.Medium),
    Font(R.font.parkinsans_regular, FontWeight.Normal)
)

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fontName = GoogleFont("Lobster Two")

val lobsterTwoFamily = FontFamily(
    Font(googleFont = fontName, fontProvider = provider)
)