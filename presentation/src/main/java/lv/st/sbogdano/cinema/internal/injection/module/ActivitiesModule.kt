package lv.st.sbogdano.cinema.internal.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lv.st.sbogdano.cinema.home.HomeActivity
import lv.st.sbogdano.cinema.internal.injection.module.home.HomeModule
import lv.st.sbogdano.cinema.internal.injection.module.startup.StartupModule
import lv.st.sbogdano.cinema.internal.injection.scope.HomeScope
import lv.st.sbogdano.cinema.internal.injection.scope.StartupScope
import lv.st.sbogdano.cinema.startup.StartupActivity

@Module
abstract class ActivitiesModule {

    @StartupScope
    @ContributesAndroidInjector(modules = [StartupModule::class])
    internal abstract fun contributeStartupActivity(): StartupActivity

    @HomeScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    internal abstract fun contributeHomeActivity(): HomeActivity
}