package lv.st.sbogdano.cinema.internal.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lv.st.sbogdano.cinema.home.HomeActivity
import lv.st.sbogdano.cinema.internal.injection.module.home.HomeModule
import lv.st.sbogdano.cinema.internal.injection.module.movie.MovieModule
import lv.st.sbogdano.cinema.internal.injection.module.person.PersonModule
import lv.st.sbogdano.cinema.internal.injection.module.startup.StartupModule
import lv.st.sbogdano.cinema.internal.injection.module.tv.TvModule
import lv.st.sbogdano.cinema.internal.injection.scope.*
import lv.st.sbogdano.cinema.movie.detail.MovieActivity
import lv.st.sbogdano.cinema.person.PersonActivity
import lv.st.sbogdano.cinema.startup.StartupActivity
import lv.st.sbogdano.cinema.tv.detail.TvActivity

@Module
abstract class ActivitiesModule {

    @StartupScope
    @ContributesAndroidInjector(modules = [StartupModule::class])
    internal abstract fun contributeStartupActivity(): StartupActivity

    @HomeScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    internal abstract fun contributeHomeActivity(): HomeActivity

    @MovieScope
    @ContributesAndroidInjector(modules = [MovieModule::class])
    internal abstract fun contributeMovieActivity(): MovieActivity

    @TvScope
    @ContributesAndroidInjector(modules = [TvModule::class])
    internal abstract fun contributeTvActivity(): TvActivity

    @PersonScope
    @ContributesAndroidInjector(modules = [PersonModule::class])
    internal abstract fun contributePersonActivity(): PersonActivity
}