package com.example.jockeyapp.model

import android.os.Parcel
import android.os.Parcelable

data class MovieModel(
    var id: Int = 0,
    var voteAverage: Double = 0.0,
    var title: String = "",
    var overview: String = "",
    var posterPath: String = "",
    var releaseDate: String = ""
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readDouble(),
        source.readString().toString(),
        source.readString().toString(),
        source.readString().toString(),
        source.readString().toString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeDouble(voteAverage)
        writeString(title)
        writeString(overview)
        writeString(posterPath)
        writeString(releaseDate)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MovieModel> = object : Parcelable.Creator<MovieModel> {
            override fun createFromParcel(source: Parcel): MovieModel = MovieModel(source)
            override fun newArray(size: Int): Array<MovieModel?> = arrayOfNulls(size)
        }
    }
}