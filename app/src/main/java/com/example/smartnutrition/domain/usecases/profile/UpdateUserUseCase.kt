package com.example.smartnutrition.domain.usecases.profile


import com.example.smartnutrition.domain.model.User
import com.example.smartnutrition.domain.repository.UserRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        userRepository.updateUser(user)
    }
}
