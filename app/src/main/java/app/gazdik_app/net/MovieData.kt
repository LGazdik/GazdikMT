package app.gazdik_app.net

import com.google.gson.annotations.SerializedName

data class MovieData(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("titleType") val titleType: String,
    @SerializedName("year") val year: Number,
    @SerializedName("runningTimeInMinutes") val runningTime: Number,
    //@SerializedName("principals") val principals: String
)