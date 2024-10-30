package com.example.travinappbeta.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<User>>
    private val repository : UserRepository
    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }
    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }
    fun updateAfterWeight(userId: Int, newWeight: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateAfterWeight(userId, newWeight)
        }
    }
    fun updateEdited(userId: Int, newState : Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateEdited(userId, newState)
        }
    }
    fun getUserById(userId: Int): LiveData<User> {
        return repository.getUserById(userId)
    }
}