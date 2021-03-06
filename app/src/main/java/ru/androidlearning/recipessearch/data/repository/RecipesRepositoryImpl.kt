package ru.androidlearning.recipessearch.data.repository

import io.reactivex.rxjava3.core.Single
import ru.androidlearning.recipessearch.data.RecipeDTO
import ru.androidlearning.recipessearch.data.RecipesDTO
import ru.androidlearning.recipessearch.data.SearchResultsDTO
import ru.androidlearning.recipessearch.data.repository.datasources.cache.RecipesDataSourceCache
import ru.androidlearning.recipessearch.data.repository.datasources.cloud.RecipesDataSourceCloud
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val recipesDataSourceCloud: RecipesDataSourceCloud,
    private val recipesDataSourceCache: RecipesDataSourceCache
) : RecipesRepository {

    /**
     * Логика следующая: берем рандомные рецепты преимущественно из "облачного" источника. При этом
     * полученные данные отправляем в БД.
     * Если при попытке взять данные из облака возникнет ошибка - тогда идеет попытка взять список рецептов
     * из БД (самый свежий надор рецептов)
     */
    override fun getRandomRecipes(): Single<RecipesDTO> =
        recipesDataSourceCloud
            .getRandomRecipes()
            .flatMap { recipes ->
                recipesDataSourceCache
                    .retain(recipes)
                    .andThen(Single.just(recipes))
            }
            .onErrorResumeWith(
                recipesDataSourceCache
                    .getRandomRecipes()
            )

    /**
     * Логика следующая: преимущественно берем данные из БД.
     * Если по данному ID в БД данных нет - переключаемся на "облачный" источник, берем из облака. При этом
     * полученные данные отправляем в БД.
     */
    override fun getRecipeById(recipeId: Long): Single<RecipeDTO> =
        recipesDataSourceCache
            .getRecipeById(recipeId)
            .switchIfEmpty(recipesDataSourceCloud
                .getRecipeById(recipeId)
                .flatMapSingle { recipeDTO ->
                    recipesDataSourceCache
                        .retain(recipeDTO)
                        .andThen(Single.just(recipeDTO))
                }).toSingle()

    /**
     * Логика следующая: выполняем поиск преимущественно с помощью "облачного" источника.
     * Если при попытке взять данные из облака возникнет ошибка - тогда идеет попытка выполнить поиск
     * в БД
     */
    override fun searchRecipesByName(name: String): Single<SearchResultsDTO> =
        recipesDataSourceCloud
            .searchRecipesByName(name)
            .onErrorResumeWith(
                recipesDataSourceCache
                    .searchRecipesByName(name)
            )
}
