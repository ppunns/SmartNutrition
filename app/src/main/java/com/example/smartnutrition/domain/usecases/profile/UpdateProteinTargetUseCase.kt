package com.example.smartnutrition.domain.usecases.profile

import com.example.smartnutrition.domain.repository.UserRepository
import javax.inject.Inject

class UpdateProteinTargetUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(proteinTarget: Int) {
        userRepository.updateProteinTarget(proteinTarget)
    }
}