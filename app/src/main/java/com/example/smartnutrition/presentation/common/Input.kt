package com.example.smartnutrition.presentation.common

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartnutrition.ui.icons.AppIcons
import com.example.smartnutrition.ui.theme.MobileTypography

@Composable
fun EmailInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Email",
    isError: Boolean = false,
    errorMessage: String = ""
) {
    val isEmailValid = value.isEmpty() || value.contains("@")
    val emailError = if (!isEmailValid) "Email harus mengandung @" else errorMessage

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { 
            Text(
                text = "Email",
                style = MobileTypography.bodySmall // Ubah dari bodySmall ke bodyMedium
            ) 
        },
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 56.dp),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.LightGray,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        isError = isError || !isEmailValid,
        supportingText = if (isError || !isEmailValid) {
            { 
                Text(
                    text = emailError,
                    style = MaterialTheme.typography.labelSmall
                ) 
            }
        } else null,
        textStyle = MobileTypography.bodySmall  // Ubah dari labelSmall ke bodyMedium
    )
}

@Composable
fun PasswordInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Password",
    isError: Boolean = false,
    errorMessage: String = ""
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val isPasswordValid = value.isEmpty() || (value.length >= 8 && value.any { it.isUpperCase() })
    val passwordError = if (!isPasswordValid) 
        "Password minimal 8 karakter dan harus mengandung huruf besar" 
    else 
        errorMessage

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = "Password",style = MobileTypography.bodySmall) },
        modifier = modifier.fillMaxWidth()
            .defaultMinSize(minHeight = 56.dp),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.LightGray,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisible)
            VisualTransformation.None
        else
            PasswordVisualTransformation(),
        isError = isError || !isPasswordValid,
        supportingText = if (isError || !isPasswordValid) {
            { Text(text = passwordError, style = MaterialTheme.typography.labelSmall) }
        } else null,
        textStyle = MobileTypography.bodySmall,
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    painter = painterResource(
                        id = if (passwordVisible) AppIcons.Visibility else AppIcons.visibilityOff
                    ),
                    contentDescription = if (passwordVisible)
                        "Sembunyikan password"
                    else
                        "Tampilkan password"
                )

            }

        }
    )
}

@Composable
fun UsernameInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String = ""
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { 
            Text(
                text = "Username",
                style = MobileTypography.bodySmall
            ) 
        },
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 56.dp),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.LightGray,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White
        ),
        isError = isError,
        supportingText = if (isError) {
            { Text(text = errorMessage, style = MaterialTheme.typography.labelSmall) }
        } else null,
        textStyle = MobileTypography.bodySmall
    )
}

@Preview(showBackground = true)
@Composable
fun EmailInputPreview() {
    EmailInput(
        value = "example@email.com",
        onValueChange = {},
        label = "Email",
        isError = false
    )
}

@Preview(showBackground = true)
@Composable
fun EmailInputErrorPreview() {
    EmailInput(
        value = "invalid-email",
        onValueChange = {},
        isError = true,
        errorMessage = "Invalid email format"
    )
}

@Preview(showBackground = true)
@Composable
fun PasswordInputPreview() {
    PasswordInput(
        value = "password123",
        onValueChange = {},
        isError = false
    )
}

@Preview(showBackground = true)
@Composable
fun PasswordInputErrorPreview() {
    PasswordInput(
        value = "123",
        onValueChange = {},
        isError = true,
        errorMessage = "Password too short"
    )
}

@Composable
fun NumberInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    placeholder: String = "",
    isError: Boolean = false,
    errorMessage: String = ""
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            // Only allow numeric input
            if (newValue.isEmpty() || newValue.all { it.isDigit() }) {
                onValueChange(newValue)
            }
        },
        placeholder = { 
            Text(
                text = placeholder,
                style = MobileTypography.bodySmall
            ) 
        },
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 56.dp),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.LightGray,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        isError = isError,
        supportingText = if (isError) {
            { Text(text = errorMessage, style = MaterialTheme.typography.labelSmall) }
        } else null,
        textStyle = MobileTypography.bodySmall
    )
}

@Preview(showBackground = true)
@Composable
fun NumberInputPreview() {
    NumberInput(
        value = "123",
        onValueChange = {},
        placeholder = "Enter number",
        isError = false
    )
}

@Preview(showBackground = true)
@Composable
fun NumberInputErrorPreview() {
    NumberInput(
        value = "",
        onValueChange = {},
        placeholder = "Enter number",
        isError = true,
        errorMessage = "Please enter a valid number"
    )
}
