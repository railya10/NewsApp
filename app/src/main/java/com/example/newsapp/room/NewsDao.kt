package com.example.newsapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.newsapp.models.News
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAll(): List<News>


    @Insert
    fun insert(news: News)

    @Query("SELECT * FROM news ORDER BY createdAt DESC")
    fun sortAll(): List<News>

    @Delete
    fun deleteItem(news: News)

    /*  @Query("SELECT * FROM news WHERE title LIKE '%' || :search || '%'")
      fun getSearch(search: String?): List<News>

      @Query("SELECT * FROM news ORDER BY title ASC")
      fun sort(): List<News>*/


    /* @Query ("""SELECT * FROM news ORDER BY CASE WHEN :isAsc = 1 THEN title END ASC, CASE WHEN :isAsc = 2 THEN title END DESC""")
     fun getAllSortedByTime (isAsc : Int?) : Flow<List<News>>*/

}
