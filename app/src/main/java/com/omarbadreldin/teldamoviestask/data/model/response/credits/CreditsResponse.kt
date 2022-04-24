package com.omarbadreldin.teldamoviestask.data.model.response.credits


import com.omarbadreldin.teldamoviestask.data.model.credits.Cast
import com.omarbadreldin.teldamoviestask.data.model.credits.Crew
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponse(
    @SerialName("cast")
    val cast: List<Cast>? = null,
    @SerialName("crew")
    val crew: List<Crew>? = null,
    @SerialName("id")
    val id: Int? = null
)