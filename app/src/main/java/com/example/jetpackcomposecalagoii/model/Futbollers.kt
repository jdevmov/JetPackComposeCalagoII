package com.example.jetpackcomposecalagoii.model

import androidx.annotation.DrawableRes

data class Futbollers(
    var fubollerName: String,
    var footballTeam: String,
    var bestChampion: String,
    @DrawableRes var photo: Int
)
