package ru.zatsoft.fragments

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Note(val id: Int, var string: String, val isChecked: Boolean, val date: LocalDateTime): Parcelable
