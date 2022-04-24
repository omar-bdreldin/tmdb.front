package com.omarbadreldin.teldamoviestask.ui.screen.moviedetails.bindings

import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.scale
import coil.load
import coil.size.Scale
import coil.transform.BlurTransformation
import coil.transform.RoundedCornersTransformation
import com.omarbadreldin.teldamoviestask.R
import com.omarbadreldin.teldamoviestask.data.api.prependBaseMediaUrl
import com.omarbadreldin.teldamoviestask.data.model.movie.details.MovieDetails
import com.omarbadreldin.teldamoviestask.databinding.FragmentMovieDetailsBinding

fun FragmentMovieDetailsBinding.bindMovieDetails(movieDetails: MovieDetails) {
    val context = root.context
    imageViewBackground.load(movieDetails.posterPath?.prependBaseMediaUrl()) {
        transformations(
            BlurTransformation(context, 15F, 5F),
        )
    }
    imageViewPoster.load(
        movieDetails.posterPath?.prependBaseMediaUrl(width = null)
    ) {
        transformations(
            RoundedCornersTransformation(
                root.resources.getDimensionPixelSize(R.dimen.radius_poster).toFloat()
            ),
        )
        scale(Scale.FILL)
    }
    textViewName.text = buildSpannedString {
        appendLine(movieDetails.title)
        if (movieDetails.originalTitle != movieDetails.title)
            scale(.6F) {
                appendLine("(${movieDetails.originalTitle})")
            }
        val extras = listOf(
            movieDetails.releaseDate.toString(),
            movieDetails.genres?.firstOrNull()?.name.toString(),
            movieDetails.runtime.toString()
                .let { "$it ${context.getString(R.string.label_minutes)}" },
        )
        scale(.5F) {
            appendLine(
                extras.joinToString(separator = "   \u25CF  ")
            )
        }
        scale(1.5F) {
            appendLine("${movieDetails.voteAverage}/10")
        }
    }
    textViewOverview.text = buildSpannedString {
        bold { appendLine(movieDetails.tagline) }
        append(movieDetails.overview)
    }
}