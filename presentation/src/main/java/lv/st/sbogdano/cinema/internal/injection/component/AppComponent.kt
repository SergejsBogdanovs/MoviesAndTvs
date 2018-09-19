package lv.st.sbogdano.cinema.internal.injection.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import lv.st.sbogdano.cinema.internal.injection.DaggerApplication
import lv.st.sbogdano.cinema.internal.injection.module.ActivitiesModule
import lv.st.sbogdano.cinema.internal.injection.module.AppModule
import lv.st.sbogdano.cinema.internal.injection.module.DataModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivitiesModule::class,
    AppModule::class,
    DataModule::class])
internal interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DaggerApplication>()
}