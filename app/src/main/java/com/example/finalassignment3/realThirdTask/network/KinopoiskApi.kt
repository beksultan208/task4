package com.example.assignment3.network
import androidx.compose.runtime.Composable
import com.example.assignment3.model.Movie
import com.example.assignment3.model.MovieCollectionResponse
import com.example.finalassignment3.realThirdTask.realThirdTask.model.Actor
import com.example.finalassignment3.realThirdTask.realThirdTask.model.FilmDetailsResponse
import com.example.finalassignment3.realThirdTask.realThirdTask.model.ImageResponse
import com.example.finalassignment3.realThirdTask.realThirdTask.model.Item
import com.example.finalassignment3.realThirdTask.realThirdTask.model.Similar
import com.example.finalassignment3.realThirdTask.realThirdTask.model.Staff
import com.example.finalassignment3.realThirdTask.realThirdTask.model.StaffDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Headers
import retrofit2.http.Query


interface KinopoiskApi {

    @Headers("X-API-KEY: af69c0f1-d84c-4747-9370-df0371023984")
    @GET("v1/staff/{id}")
    suspend fun getActorDetails(@Path("id") Id: Int): Actor

    @Headers("X-API-KEY: af69c0f1-d84c-4747-9370-df0371023984")
    @GET("v2.2/films/collections")
    suspend fun getMoviesByCollection(
        @Query("type") type: String,
    ): Response<MovieCollectionResponse>



    @Headers(
        "X-API-KEY: af69c0f1-d84c-4747-9370-df0371023984",
        "Content-Type: application/json"
    )
    @GET("v2.2/films/{kinopoiskId}")
    suspend fun getFilmById(@Path("kinopoiskId") kinopoiskId: Int): Movie


    @Headers(
        "X-API-KEY: af69c0f1-d84c-4747-9370-df0371023984",
        "Content-Type: application/json"
    )
    @GET("v1/staff")
        suspend fun getStaffById (
    @Query("filmId") filmId: Int
    ): List<Staff>


    @Headers(
        "X-API-KEY: af69c0f1-d84c-4747-9370-df0371023984",
        "Content-Type: application/json"
    )
    @GET("v2.2/films/{filmId}/images")
    suspend fun getFilmImages(
        @Path("filmId") filmId: Int,
        @Query("page") page: Int = 1
    ): ImageResponse



    @Headers(
        "X-API-KEY: af69c0f1-d84c-4747-9370-df0371023984",
        "Content-Type: application/json"
    )
    @GET("v2.2/films/{filmId}/similars")
    suspend fun getSimilarFilms(
        @Path("filmId") filmId: Int,
    ): Similar


    @Headers(
        "X-API-KEY: af69c0f1-d84c-4747-9370-df0371023984",
        "Content-Type: application/json"
    )
    @GET("v1/staff/{id}")
    suspend fun getStaffDetails (
        @Path("id") id: Int
    ): StaffDetail


}


interface FilmDetailsApi {
    @Headers("X-API-KEY: af69c0f1-d84c-4747-9370-df0371023984")
    @GET("v2.2/films/{id}")
    suspend fun getFilmDetails(@Path("id") filmId: Int): FilmDetailsResponse
}



