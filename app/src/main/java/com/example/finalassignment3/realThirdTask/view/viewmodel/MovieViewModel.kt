package com.example.assignment3.view
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment3.model.Movie
import com.example.assignment3.model.MovieCollectionResponse
import com.example.assignment3.network.KinopoiskApi
import com.example.assignment3.network.RetrofitInstance
import com.example.finalassignment3.realThirdTask.realThirdTask.model.Actor
import com.example.finalassignment3.realThirdTask.realThirdTask.model.FilmX
import com.example.finalassignment3.realThirdTask.realThirdTask.view.Screens.FilmScreenState
import com.example.finalassignment3.realThirdTask.realThirdTask.model.ImageItem
import com.example.finalassignment3.realThirdTask.realThirdTask.model.ItemX
import com.example.finalassignment3.realThirdTask.realThirdTask.model.Staff
import com.example.finalassignment3.realThirdTask.realThirdTask.model.StaffDetail
import com.example.finalassignment3.realThirdTask.realThirdTask.network.ActorRepository
import com.example.finalassignment3.realThirdTask.realThirdTask.network.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel(private val api: KinopoiskApi) : ViewModel() {

    private val _moviesByCollectionMap = mutableStateMapOf<String, List<Movie>>()
    val moviesByCollectionMap: Map<String, List<Movie>> get() = _moviesByCollectionMap

    var scrollPositionIndex by mutableStateOf(0)
    var scrollPositionOffset by mutableStateOf(0)
    fun fetchMoviesByCollection(collectionType: String) {
        if (_moviesByCollectionMap.containsKey(collectionType)) return

        viewModelScope.launch {
            try {
                val response: Response<MovieCollectionResponse> = api.getMoviesByCollection(collectionType)
                if (response.isSuccessful) {
                    response.body()?.let { movieCollectionResponse ->
                        _moviesByCollectionMap[collectionType] = movieCollectionResponse.items
                    } ?: run {
                        _moviesByCollectionMap[collectionType] = emptyList()
                    }
                } else {
                    _moviesByCollectionMap[collectionType] = emptyList()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _moviesByCollectionMap[collectionType] = emptyList()
            }
        }
    }
}
class FilmViewModel : ViewModel() {
    private val _filmState = MutableStateFlow<FilmScreenState<Movie>>(FilmScreenState.Loading)
    val filmState: StateFlow<FilmScreenState<Movie>> = _filmState

    fun fetchFilm(kinopoiskId: Int) {
        _filmState.value = FilmScreenState.Loading

        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getFilmById(kinopoiskId)
                _filmState.value = FilmScreenState.Success(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
class StaffFilmViewModel : ViewModel() {
    private val _filmState = MutableStateFlow<Movie?>(null)
    val filmState: StateFlow<Movie?> = _filmState

    fun fetchFilm(kinopoiskId: Int) {

        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getFilmById(kinopoiskId)
                _filmState.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

class StaffViewModel : ViewModel() {
    private val _staffList = MutableStateFlow<List<Staff>>(emptyList())
    val staffList: Flow<List<Staff>> = _staffList.map { it.take(20) } // Limit to 20 staff

    fun fetchStaff(filmId: Int) {
        viewModelScope.launch {
            try {
                val staff = RetrofitInstance.api.getStaffById(filmId)
                _staffList.value = staff // Set the entire staff list
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}


class FilmImagesViewModel(private val api: KinopoiskApi) : ViewModel() {
    private val _images = MutableStateFlow<List<ImageItem>>(emptyList())
    val images: StateFlow<List<ImageItem>> = _images

    private val _currentPage = MutableStateFlow(1)
    val currentPage: StateFlow<Int> = _currentPage

    private val _totalPages = MutableStateFlow(1)
    val totalPages: StateFlow<Int> = _totalPages

    fun fetchImages(filmId: Int, page: Int = 1) {
        viewModelScope.launch {
            try {
                val response = api.getFilmImages(filmId, page)
                _images.value = response.items
                _currentPage.value = page
                _totalPages.value = response.totalPages
            } catch (e: Exception) {
                e.printStackTrace() // Handle error
            }
        }
    }
}

class PersonViewModel : ViewModel() {
    private val _personDetails = MutableStateFlow<StaffDetail?>(null)
    val personDetails: StateFlow<StaffDetail?> = _personDetails
    private  val _personFilms = MutableStateFlow<List<FilmX>>(emptyList())
    val personFilms : StateFlow<List<FilmX?>>  = _personFilms

    fun fetchPersonFilms(personId: Int){
        viewModelScope.launch {
            try {
                val response1 = RetrofitInstance.api.getStaffDetails(personId)
                _personFilms.value = response1.films
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun fetchPersonDetails(personId: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getStaffDetails(personId)
                _personDetails.value = response
            } catch (e: Exception) {
                e.printStackTrace() // Handle error
            }
        }
    }
}
sealed class ScreenState<out T> {
    object Loading : ScreenState<Nothing>()
    data class Success<T>(val data: T) : ScreenState<T>()
    data class Error(val message: String) : ScreenState<Nothing>()
}

class SimilarFilmsViewModel(private val api: KinopoiskApi) : ViewModel() {
    private val _similar = MutableStateFlow<List<ItemX>>(emptyList())
    val similar: StateFlow<List<ItemX>> = _similar




    fun fetchImages(filmId: Int, page: Int = 1) {
        viewModelScope.launch {
            try {
                val response = api.getSimilarFilms(filmId)
                _similar.value = response.items

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
