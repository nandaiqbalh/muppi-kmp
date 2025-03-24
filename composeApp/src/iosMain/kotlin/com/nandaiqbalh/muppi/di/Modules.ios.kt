package com.nandaiqbalh.muppi.di

import com.nandaiqbalh.muppi.core.data.local_database.DatabaseFactory
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModules: Module
	get() = module {
		single<HttpClientEngine> { Darwin.create() }
		single { DatabaseFactory() }
	}