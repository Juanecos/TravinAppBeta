package com.example.travinappbeta.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query



@Dao
interface UserDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    // Nueva query para actualizar el peso después de la actividad
    @Query("UPDATE user_table SET afterw = :newWeight WHERE id = :userId")
    suspend fun updateAfterWeight(userId: Int, newWeight: Double)

    // Nueva Query: Obtener un usuario por ID
    @Query("SELECT * FROM user_table WHERE id = :userId LIMIT 1")
    fun getUserById(userId: Int): LiveData<User>

    // Nueva query para actualizar el estado a editado
    @Query("UPDATE user_table SET edited = :newState WHERE id = :userId")
    suspend fun updateEdited(userId: Int, newState: Boolean)

    //@Query("DELETE FROM usuarios")
    //suspend fun eliminarTodosLosUsuarios() {}
}