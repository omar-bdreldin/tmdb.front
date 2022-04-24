package com.omarbadreldin.teldamoviestask.data.model.credits


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Credits(
    @SerialName("cast")
    val cast: List<Cast>? = null,
    @SerialName("crew")
    val crew: List<Crew>? = null,
    @SerialName("id")
    val id: Int? = null
) : Parcelable