package com.example.smartnutrition.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.smartnutrition.data.remote.dto.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TokenManager @Inject constructor(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_preferences")
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
        private val USER_ID = intPreferencesKey("userId")
        private val USERNAME_KEY = stringPreferencesKey("username")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val PROFILE_PIC = stringPreferencesKey("profile_picture")
        private val PROTEIN_TAR = intPreferencesKey("protein_Target")
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun saveUserData(user: User) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID] = user.id
            preferences[USERNAME_KEY] = user.name
            preferences[EMAIL_KEY] = user.email
            preferences[PROFILE_PIC] = user.profile_picture
            preferences[PROTEIN_TAR] = user.proteinTarget
        }
    }

    val getToken: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[TOKEN_KEY]
    }

    val getUserData: Flow<User> = context.dataStore.data.map { preferences ->
        User(
            id = preferences[USER_ID] ?: 0,
            name = preferences[USERNAME_KEY] ?: "",
            email = preferences[EMAIL_KEY] ?: "",
            profile_picture = preferences[PROFILE_PIC] ?: "",
            proteinTarget = preferences[PROTEIN_TAR] ?: 0
        )

    }
    suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}