package lv.st.sbogdano.cinema.internal.injection.module.home

//@Module
//internal abstract class HomeModule {
//
//    @ContributesAndroidInjector
//    internal abstract fun contributeMoviesListFragment(): MovieListFragment
//
//    @Module
//    companion object {
//
//        @HomeScope
//        @Provides
//        @JvmStatic
//        internal fun provideGenreTypeGetAllUseCase(schedulers: Schedulers,
//                                                   gateway: Gateway): MoviesByTypeGetAllUseCase {
//            return MoviesByTypeGetAllUseCase(schedulers, gateway)
//        }
//
//        @HomeScope
//        @Provides
//        @JvmStatic
//        internal fun provideMovieFindByGenreUseCase(schedulers: Schedulers,
//                                                    gateway: Gateway): MovieFindByGenreUseCase {
//            return MovieFindByGenreUseCase(schedulers, gateway)
//        }
//
//        @HomeScope
//        @Provides
//        @JvmStatic
//        internal fun provideViewModelFactory(context: Context,
//                                             moviesByTypeGetAllUseCase: MoviesByTypeGetAllUseCase,
//                                             movieFindByGenreUseCase: MovieFindByGenreUseCase): ViewModelProvider.Factory {
//            return object : ViewModelProvider.Factory {
//                @Suppress("UNCHECKED_CAST")
//                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//                    return when {
//                        modelClass.isAssignableFrom(GenreTypeViewModel::class.java) ->
//                            GenreTypeViewModel(context, moviesByTypeGetAllUseCase) as T
//
//                        modelClass.isAssignableFrom(MovieListViewModel::class.java) ->
//                            MovieListViewModel(context, movieFindByGenreUseCase) as T
//
//                        else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
//                    }
//                }
//
//            }
//        }
//    }
//
//}