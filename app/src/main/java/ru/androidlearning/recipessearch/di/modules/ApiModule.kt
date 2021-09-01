package ru.androidlearning.recipessearch.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.androidlearning.recipessearch.BuildConfig
import ru.androidlearning.recipessearch.data.repository.datasources.cloud.api.RecipesApi
import java.io.IOException
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Named("spoonacular_base_url")
    @Provides
    fun provideSpoonacularBaseUrl(): String = "https://api.spoonacular.com/recipes/"

    @Named("spoonacular_api_key")
    @Provides
    fun provideSpoonacularApiKey(): String = BuildConfig.SPOONACULAR_API_KEY

    @Named("random_recipes_number")
    @Provides
    fun provideRandomRecipesNumber(): Int = 10

    @Reusable
    @Provides
    fun provideRecipesApi(@Named("spoonacular_base_url") baseUrl: String): RecipesApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(createOkHttpClient(PictureOfTheDayInterceptor()))
            .build()
            .create(RecipesApi::class.java)

    private val gson: Gson =
        GsonBuilder()
            .setLenient()
            .create()

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

    inner class PictureOfTheDayInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            return chain.proceed(chain.request())
        }
    }
}
