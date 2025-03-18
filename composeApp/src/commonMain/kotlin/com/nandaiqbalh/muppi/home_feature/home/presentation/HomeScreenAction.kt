package com.nandaiqbalh.muppi.home_feature.home.presentation

import com.nandaiqbalh.muppi.core.domain.model.SeeAllSource

sealed interface HomeScreenAction {

	data class  OnClickItem(val id: Int) : HomeScreenAction

	data class OnClickSeeAll(val source: SeeAllSource): HomeScreenAction

}