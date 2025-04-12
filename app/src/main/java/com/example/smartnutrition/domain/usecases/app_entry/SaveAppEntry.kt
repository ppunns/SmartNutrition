package com.example.smartnutrition.domain.usecases.app_entry

import com.example.smartnutrition.domain.manger.LocalUserManger

class SaveAppEntry(
    private val localUserManger: LocalUserManger
) {
    suspend operator fun invoke(){
        localUserManger.saveAppEntry()
    }
}