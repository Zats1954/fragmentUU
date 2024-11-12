package ru.zatsoft.fragments

import android.widget.CheckBox
import java.util.Date

data class Note(val id: Int, val string: String, val isChecked: Boolean, val date: Date)
