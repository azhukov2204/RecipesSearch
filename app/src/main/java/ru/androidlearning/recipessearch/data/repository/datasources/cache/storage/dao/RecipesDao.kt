package ru.androidlearning.recipessearch.data.repository.datasources.cache.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ru.androidlearning.recipessearch.data.RecipeDTO

@Dao
abstract class RecipesDao {
    @Insert(onConflict = REPLACE)
    abstract fun mergeRecipes(recipes: List<RecipeDTO>)

    @Transaction
    open fun retainRecipes(recipes: List<RecipeDTO>) {
        val currentTimestamp = System.currentTimeMillis()
        recipes.forEach { it.savedTime = currentTimestamp }
        mergeRecipes(recipes)
    }

    fun retain(recipes: List<RecipeDTO>): Completable =
        Completable.fromAction {
            retainRecipes(recipes)
        }

    @Query("SELECT * FROM recipes WHERE saved_time = (SELECT max(saved_time) FROM recipes)")
    abstract fun getLastRandomRecipes(): Single<List<RecipeDTO>>

    @Query("SELECT * FROM recipes WHERE id = :recipeId")
    abstract fun getRecipeById(recipeId: Long): Maybe<RecipeDTO>
}
