package com.smallrents.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.smallrents.R

val robotoFamily = FontFamily(
    Font(resId = R.font.roboto, weight = FontWeight.Normal),
    Font(resId = R.font.roboto_bold, weight = FontWeight.Bold),
    Font(resId = R.font.roboto_medium, weight = FontWeight.Medium),
    Font(resId = R.font.roboto_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
)

val nunitoFamily = FontFamily(
    Font(resId = R.font.nunito, weight = FontWeight.Normal),
    Font(resId = R.font.nunito_bold, weight = FontWeight.Bold),
    Font(resId = R.font.nunito_bold_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(resId = R.font.nunito_light_italic, weight = FontWeight.Light, style = FontStyle.Italic),
)