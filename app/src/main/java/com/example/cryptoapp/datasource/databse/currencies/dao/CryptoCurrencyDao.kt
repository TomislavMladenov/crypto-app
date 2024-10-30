package com.example.cryptoapp.datasource.databse.currencies.dao

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Transaction
import com.example.cryptoapp.datasource.databse.currencies.model.CryptoCurrencyEntity
import kotlinx.coroutines.flow.Flow

@Database(entities = [CryptoCurrencyEntity::class], version = 1)
abstract class CryptoDatabase : RoomDatabase() {

    abstract fun cryptoCurrencyDao(): CryptoCurrencyDao

}

@Dao
interface CryptoCurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(list: List<CryptoCurrencyEntity>)

    @Query("SELECT * FROM crypto_currency")
    fun getAllCryptoCurrencies(): Flow<List<CryptoCurrencyEntity>>

    @Query("DELETE FROM crypto_currency")
    suspend fun deleteAll()

    @Transaction
    suspend fun updateCryptoCurrencies(list: List<CryptoCurrencyEntity>) {
        deleteAll()
        insertCurrencies(list)
    }
}