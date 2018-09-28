package lv.st.sbogdano.cinema.internal.util

import android.content.Context

private const val PREFERENCES_NAME = "cinema_prefs"
private const val MOVIE_TYPE_KEY = "movies_type_key"
private const val TV_TYPE_KEY = "tv_type_key"
private const val DEFAULT_TYPE = "popular"

class PreferencesHelper(context: Context) {

    private val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    var movieType: String
        get() {
            if (!preferences.contains(MOVIE_TYPE_KEY)) {
                preferences.edit().putString(MOVIE_TYPE_KEY, DEFAULT_TYPE).apply()
            }
            return preferences.getString(MOVIE_TYPE_KEY, DEFAULT_TYPE)
        }
        set(value) = preferences.edit().putString(MOVIE_TYPE_KEY, value).apply()

    var tvType: String
        get() {
            if (!preferences.contains(TV_TYPE_KEY)) {
                preferences.edit().putString(TV_TYPE_KEY, DEFAULT_TYPE).apply()
            }
            return preferences.getString(TV_TYPE_KEY, DEFAULT_TYPE)
        }
        set(value) = preferences.edit().putString(TV_TYPE_KEY, value).apply()
}
