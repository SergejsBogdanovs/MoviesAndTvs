package lv.st.sbogdano.cinema.internal.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides
import lv.st.sbogdano.cinema.internal.injection.DaggerApplication
import lv.st.sbogdano.cinema.internal.schedulers.AppSchedulers
import lv.st.sbogdano.cinema.internal.util.PreferencesHelper
import lv.st.sbogdano.cinema.navigation.Navigator
import lv.st.sbogdano.domain.Schedulers
import javax.inject.Singleton

@Module
internal class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: DaggerApplication): Context = application.applicationContext

    @Provides
    @Singleton
    internal fun provideSchedulers(): Schedulers = AppSchedulers()

    @Provides
    @Singleton
    internal fun provideNavigator() = Navigator()

    @Provides
    @Singleton
    internal fun providePreferenceHelper(application: DaggerApplication) = PreferencesHelper(application.applicationContext)
}