package com.ofek.superheroes.herodetailsscreen.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ofek.superheroes.R
import com.ofek.superheroes.SuperheroesApp
import com.ofek.superheroes.herodetailsscreen.network.HeroDetailsRepository
import com.ofek.superheroes.herodetailsscreen.ui.models.*
import com.squareup.picasso.Picasso
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HeroDetailsScreenViewModel @Inject constructor(
    application: Application,
    val picasso: Picasso,
    private val repository: HeroDetailsRepository,
) : AndroidViewModel(application) {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val heroLiveData = MutableLiveData<DetailsScreenSuperheroModel?>(null)
    private val extraDetailsItemsLiveData =
        MutableLiveData<List<HeroExtraDetailsModel>>(emptyList())

    fun setHero(hero: DetailsScreenSuperheroModel?) {
        heroLiveData.value = hero
        hero?.id?.let {
            fetchExtraData(it)
        }
    }

    private fun fetchExtraData(heroId: Int) {
        Observable.merge(
            repository.getConnections(heroId).toObservable().map {
                HeroExtraDetailsModel(
                    getApplication<SuperheroesApp>().resources.getString(R.string.extra_detail_connections_title),
                    buildConnections(it)
                )
            },
            repository.getWork(heroId).toObservable().map {
                HeroExtraDetailsModel(
                    getApplication<SuperheroesApp>().resources.getString(R.string.extra_detail_work_title),
                    buildWork(it)
                )
            },
            repository.getAppearance(heroId).toObservable().map {
                HeroExtraDetailsModel(
                    getApplication<SuperheroesApp>().resources.getString(R.string.extra_detail_appearance_title),
                    buildAppearance(it)
                )
            },
        ).toList()
            .doOnSubscribe {
                compositeDisposable.add(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { items ->
                extraDetailsItemsLiveData.value = items
            }
    }

    fun getSuperHeroLiveData(): LiveData<DetailsScreenSuperheroModel?> {
        return heroLiveData
    }

    fun getHeroExtraDetailsItemsLiveData(): MutableLiveData<List<HeroExtraDetailsModel>> {
        return extraDetailsItemsLiveData
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


}