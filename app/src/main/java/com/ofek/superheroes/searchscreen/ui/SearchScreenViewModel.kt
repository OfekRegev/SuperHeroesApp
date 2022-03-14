package com.ofek.superheroes.searchscreen.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ofek.superheroes.R
import com.ofek.superheroes.SuperheroesApp
import com.ofek.superheroes.searchscreen.network.SearchScreenRepository
import com.ofek.superheroes.searchscreen.ui.models.SearchScreenSuperHeroModel
import com.ofek.superheroes.searchscreen.ui.models.mapSuperHeroDtoToUiHeroModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    val picasso: Picasso,
    private val repository: SearchScreenRepository,
    application: Application,
) : AndroidViewModel(application) {


    companion object {
        const val debounce_frequency = 500L
        const val minimum_letters_for_query = 3
    }

    private val queryReducer: PublishSubject<String> = PublishSubject.create()
    private val compositeDisposable = CompositeDisposable()
    private val heroesListLiveData = MutableLiveData<List<SearchScreenSuperHeroModel>>(emptyList())
    private val isHeroesListLoadingLiveData = MutableLiveData(false)
    private val suggestedHeroesListLiveData = MutableLiveData<List<SearchScreenSuperHeroModel>>(emptyList())
    private val isSuggestedHeroListLoadingLiveData = MutableLiveData(false)


    init {
        // a debounce reducer to prevent spamming queries, and limit to 3 letters at least per query
        queryReducer.filter { it.length >= minimum_letters_for_query }
            .debounce(debounce_frequency, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                compositeDisposable.add(it)
            }
            .subscribe {
                fetchSuperheroes(it)
            }
        fetchSuggestedSuperheroes()
    }

    private fun fetchSuggestedSuperheroes() {
        val heroesIds =
            getApplication<SuperheroesApp>().resources.getIntArray(R.array.suggested_heroes)
        Observable.merge(
            heroesIds.map {
                repository.getSuperheroById(it).toObservable()
            }
        ).map {
            mapSuperHeroDtoToUiHeroModel(it)
        }.toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                compositeDisposable.add(it)
            }.subscribe { items ->
                suggestedHeroesListLiveData.value = items
            }
    }

    private fun fetchSuperheroes(query: String) {
        repository.searchSuperheroes(query)
            .map { heroDtosList ->
                heroDtosList.map {
                    mapSuperHeroDtoToUiHeroModel(it)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                compositeDisposable.add(it)
                isHeroesListLoadingLiveData.postValue(true)
            }
            .doOnTerminate {
                isHeroesListLoadingLiveData.postValue(false)
            }
            .subscribe { items ->
                heroesListLiveData.value = items
            }
    }


    // called when the search text is changed
    fun onQueryChanged(query: String) {
        queryReducer.onNext(query)
    }

    fun getSearchItems(): LiveData<List<SearchScreenSuperHeroModel>> {
        return heroesListLiveData
    }

    fun getSuggestedItems(): LiveData<List<SearchScreenSuperHeroModel>> {
        return suggestedHeroesListLiveData
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}