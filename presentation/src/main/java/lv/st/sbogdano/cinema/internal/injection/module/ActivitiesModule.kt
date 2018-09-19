package lv.st.sbogdano.cinema.internal.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lv.st.sbogdano.cinema.internal.injection.module.startup.StartupModule
import lv.st.sbogdano.cinema.internal.injection.scope.StartupScope
import lv.st.sbogdano.cinema.startup.StartupActivity

@Module
abstract class ActivitiesModule {

    @StartupScope
    @ContributesAndroidInjector(modules = [StartupModule::class])
    internal abstract fun contributeStartupActivity(): StartupActivity

}