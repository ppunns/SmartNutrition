package com.example.smartnutrition.domain.usecases.profile

import android.net.Uri
import com.example.smartnutrition.domain.repository.UserRepository
import javax.inject.Inject

class UpdateFotoProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke(photoUri: Uri):String{
        return userRepository.updateProfilePhoto(photoUri)
    }
}
