package ru.androidlearning.recipessearch.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.androidlearning.recipessearch.data.repository.datasources.cloud.api.RecipesApi
import ru.androidlearning.recipessearch.data.repository.datasources.cloud.api.TokenInterceptor
import javax.inject.Named

@Module
class ApiModule {

    @Named("spoonacular_base_url")
    @Provides
    fun provideSpoonacularBaseUrl(): String = "https://api.spoonacular.com/recipes/"

    @Reusable
    @Provides
    fun provideRecipesApi(@Named("spoonacular_base_url") baseUrl: String): RecipesApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(TokenInterceptor)
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .build()
            .create(RecipesApi::class.java)

    private val gson: Gson =
        GsonBuilder()
            .setLenient()
            .create()
}
