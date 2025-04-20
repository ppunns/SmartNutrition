package com.example.smartnutrition.presentation.camera

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import com.example.smartnutrition.util.rotateBitmap
import kotlinx.coroutines.delay
import java.util.concurrent.Executor






@Composable
fun CameraScreen (
    viewModel: CameraViewModel = hiltViewModel()
) {
    val cameraState: CameraState by viewModel.state.collectAsState()

    CameraContent(
//        onPhotoCaptured = viewModel::storePhotoInGallery,
        lastCapturedPhoto = cameraState.capturedImage
    )
}
@Composable
private fun CameraContent(
//    onPhotoCaptured: (Bitmap) -> Unit,
    lastCapturedPhoto: Bitmap? = null
) {
    val context: Context = LocalContext.current
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val cameraController: LifecycleCameraController = remember { LifecycleCameraController(context) }

    // Add states for loading and temporary preview
    var isLoading by remember { mutableStateOf(false) }
    var tempPreviewPhoto by remember { mutableStateOf<Bitmap?>(null) }

    // Effect to handle temporary preview
    LaunchedEffect(lastCapturedPhoto) {
        if (lastCapturedPhoto != null && tempPreviewPhoto != lastCapturedPhoto) {
            tempPreviewPhoto = lastCapturedPhoto
            delay(3000) // Show preview for 2 seconds
            tempPreviewPhoto = null
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Take photo") },
                onClick = {
                    isLoading = true
                    capturePhoto(
                        context,
                        cameraController,
                        onCaptured = { bitmap ->
//                            onPhotoCaptured(bitmap)
                            isLoading = false
                        }
                    )
                },
                icon = { Icon(imageVector = Icons.Default.Add, contentDescription = "Camera capture icon") },
                expanded = !isLoading
            )
        }
    ) { paddingValues: PaddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            // Wrapper Box untuk memperkecil ukuran kamera
            Box(
                modifier = Modifier
                    .width(380.dp)
                    .height(476.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(15))
            ) {
                // Camera Preview
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { context ->
                        PreviewView(context).apply {
                            layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                            setBackgroundColor(Color.BLACK)
                            implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                            scaleType = PreviewView.ScaleType.FILL_CENTER
                        }.also { previewView ->
                            previewView.controller = cameraController
                            cameraController.bindToLifecycle(lifecycleOwner)
                        }
                    }
                )

                // Temporary photo preview overlay
                if (tempPreviewPhoto != null) {
                    val capturedPhoto: ImageBitmap = remember(tempPreviewPhoto.hashCode()) {
                        tempPreviewPhoto!!.asImageBitmap()
                    }

                    Image(
                        bitmap = capturedPhoto,
                        contentDescription = "Captured photo preview",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .zIndex(2f)
                            .border(2.dp, androidx.compose.ui.graphics.Color.White, RoundedCornerShape(15))
                    )
                }

                // Loading indicator
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .zIndex(3f),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = androidx.compose.ui.graphics.Color.White
                        )
                    }
                }
            }
        }
    }
}

private fun capturePhoto(
    context: Context,
    cameraController: LifecycleCameraController,
    onCaptured: (Bitmap) -> Unit
) {
    val mainExecutor: Executor = ContextCompat.getMainExecutor(context)

    cameraController.takePicture(mainExecutor, object : ImageCapture.OnImageCapturedCallback() {
        override fun onCaptureSuccess(image: ImageProxy) {
            val correctedBitmap: Bitmap = image
                .toBitmap()
                .rotateBitmap(image.imageInfo.rotationDegrees)

            onCaptured(correctedBitmap)
            image.close()
        }

        override fun onError(exception: ImageCaptureException) {
            Log.e("CameraContent", "Error capturing image", exception)
            onCaptured(Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)) // Send empty bitmap to clear loading state
        }
    })
}