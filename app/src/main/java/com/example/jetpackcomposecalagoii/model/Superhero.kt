package com.example.jetpackcomposecalagoii.model

import androidx.annotation.DrawableRes

data class Superhero(
    var superheroName: String,
    var reaName: String,
    var publisher: String,
    @DrawableRes var photo: Int
)
