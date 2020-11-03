package nl.vanzanden.flippingtooltest.di

import android.content.Context
import dagger.Module
import dagger.Provides
import nl.vanzanden.flippingtooltest.data.repository.impl.Repository
import nl.vanzanden.flippingtooltest.data.repository.preferences.PreferenceHelper
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    fun providesAppContext() = context

    @Provides
    @Singleton
    fun provideAuth() = Repository()

    @Provides
    @Singleton
    fun providePreferences(): PreferenceHelper {
        return PreferenceHelper()
    }

}