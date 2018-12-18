//package lv.st.sbogdano.cinema.favorite
////
////import androidx.fragment.app.Fragment
////import androidx.fragment.app.FragmentManager
////import androidx.fragment.app.FragmentStatePagerAdapter
////import lv.st.sbogdano.cinema.favorite.list.FavoriteListFragment
////
////class FavoritePagerAdapter(
////    fm: FragmentManager
////) : FragmentStatePagerAdapter(fm) {
////
////    private val favorites = arrayOf(
////            Pair("Movies", "movies"),
////            Pair("Tvs", "tvs")
////    )
////
////    override fun getItem(position: Int): Fragment = FavoriteListFragment.newInstance(favorites[position].second)
////
////    override fun getCount(): Int = favorites.size
////
////    override fun getPageTitle(position: Int): CharSequence? = favorites[position].first.toUpperCase()
////}