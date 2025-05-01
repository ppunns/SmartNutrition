package com.example.smartnutrition.presentation.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutritionInputBottomSheet(
    title: String = "Protein",
    subtitle: String = "Pantau dan Capai Target Anda Setiap Hari",
    label: String = "Karbohidrat",
    initialValue: String = "",
    onSave: (String) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    val bottomSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var inputValue by remember { mutableStateOf(initialValue) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = bottomSheetState,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        dragHandle = { BottomSheetDefaults.DragHandle(color = Color.LightGray) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = title,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp),
                textAlign = TextAlign.Center
            )

            // Subtitle
            Text(
                text = subtitle,
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 24.dp),
                textAlign = TextAlign.Center
            )

            // Input Label
            Text(
                text = label,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 8.dp)
            )

            // Input Field
            OutlinedTextField(
                value = inputValue,
                onValueChange = { inputValue = it },
                modifier = Modifier
                    .height(56.dp)  // Adjusted height to match button
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                shape = RoundedCornerShape(100.dp),  // Rounded corners like button
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White,
                    focusedBorderColor = Color(0xFF106DF1),
                    unfocusedBorderColor = Color.LightGray
                ),
                singleLine = true,
                keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                    keyboardType = androidx.compose.ui.text.input.KeyboardType.Number
                )
            )

            // Replace the standard Button with PrimaryButton
            PrimaryButton(
                text = "Simpan",
                onClick = {
                    onSave(inputValue)
                    scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                        if (!bottomSheetState.isVisible) {
                            onDismiss()
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// How to use the bottom sheet
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutritionTrackingScreen() {
    var showBottomSheet by remember { mutableStateOf(false) }
    var karbohidratValue by remember { mutableStateOf("854") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Karbohidrat: $karbohidratValue",
                fontSize = 20.sp
            )

            Button(
                onClick = { showBottomSheet = true },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Edit Karbohidrat")
            }
        }

        if (showBottomSheet) {
            NutritionInputBottomSheet(
                title = "Protein",
                subtitle = "Pantau dan Capai Target Anda Setiap Hari",
                label = "Karbohidrat", 
                initialValue = karbohidratValue,
                onSave = { newValue ->
                    karbohidratValue = newValue
                },
                onDismiss = { showBottomSheet = false }
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun NutritionTrackingScreenPreview() {
    MaterialTheme {
        NutritionTrackingScreen()
    }
}