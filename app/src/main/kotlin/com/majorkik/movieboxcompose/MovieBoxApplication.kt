package com.majorkik.movieboxcompose

import android.app.Application
import com.majorkik.app.preferences.impl.appPreferencesImplModule
import com.majorkik.movieboxcompose.di.AppContainer
import com.majorkik.tmdb.data.di.tmdbImplModule
import com.majorkik.ui.authorization.authModule
import com.majorkik.ui.nav.home.uiNavHome
import com.majorkik.ui.nav.profile.uiNavProfile
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class MovieBoxApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        AppContainer

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MovieBoxApplication)
            modules(
                appModule,
                authModule,
                tmdbImplModule,
                appPreferencesImplModule,
                uiNavProfile,
                uiNavHome
            )
        }
    }
}
