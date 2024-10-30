package com.example.travinappbeta.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun  addUser(user: User){
        userDao.addUser(user)

    }
    suspend fun updateAfterWeight(userId: Int, newWeight: Double) {
        userDao.updateAfterWeight(userId, newWeight)
    }
    suspend fun updateEdited(userId: Int, newState: Boolean) {
        userDao.updateEdited(userId, newState)
    }
    // Nueva función: Obtener un usuario por ID
    fun getUserById(userId: Int): LiveData<User> {
        return userDao.getUserById(userId)
    }
}