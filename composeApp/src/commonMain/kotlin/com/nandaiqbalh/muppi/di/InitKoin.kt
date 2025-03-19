package com.nandaiqbalh.muppi.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
	startKoin {
		config?.invoke((this))
		modules(
			platformModules,
			sharedModules,
		)
	}
}