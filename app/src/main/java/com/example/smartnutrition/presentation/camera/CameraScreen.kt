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
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.rememberNavController
import com.example.smartnutrition.presentation.navgraph.Route
import com.example.smartnutrition.ui.theme.SmartNutritionTheme
import com.example.smartnutrition.util.rotateBitmap
import kotlinx.coroutines.delay
import java.util.concurrent.Executor

@Composable
fun CameraScreen(
    navigate: (String) -> Unit,
    viewModel: CameraViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    
    LaunchedEffect(state.shouldNavigateToDetail) {
        if (state.shouldNavigateToDetail) {
            state.classification?.className?.let { label ->
                Log.e("data ini adalah :",label)
                navigate(Route.DetailsScreen.createRoute(label))
            }
            viewModel.resetNavigation()
        }
    }

    Scaffold { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            CameraContent(
                onExitClick = { navigate(Route.HomeScreen.route) },
                onPhotoTaken = { bitmap ->
                    viewModel.classifyImage(bitmap)
                }
            )
        }
    }
}

@Composable
private fun CameraContent(
    onExitClick: () -> Unit = {},
    onPhotoTaken: (Bitmap) -> Unit
) {
    val context: Context = LocalContext.current
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val cameraController: LifecycleCameraController = remember { LifecycleCameraController(context) }
    var isLoading by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Camera Preview (full screen)
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

        // Semi-transparent overlay with a hole in the middle
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            
            // Define the transparent rectangle in the middle
            val rectWidth = 380.dp.toPx()
            val rectHeight = 476.dp.toPx()
            val cornerRadius = 24.dp.toPx()
            
            // Calculate the position of the rectangle (centered)
            val left = (width - rectWidth) / 2
            val top = (height - rectHeight) / 2
            val right = left + rectWidth
            val bottom = top + rectHeight
            
            // Create a path for the entire canvas
            val path = androidx.compose.ui.graphics.Path()
            path.addRect(androidx.compose.ui.geometry.Rect(0f, 0f, width, height))
            
            // Create a rounded rectangle for the hole
            val roundedRect = RoundRect(
                left = left,
                top = top,
                right = right,
                bottom = bottom,
                radiusX = cornerRadius,
                radiusY = cornerRadius
            )
            
            // Cut out the hole from the path
            path.addRoundRect(roundedRect)
            path.fillType = androidx.compose.ui.graphics.PathFillType.EvenOdd
            
            // Draw the semi-transparent overlay
            drawPath(
                path = path,
                color = ComposeColor.Black.copy(alpha = 0.5f)
            )
            
            // Draw a border around the hole
            drawRoundRect(
                color = ComposeColor.White.copy(alpha = 0.0f),
                topLeft = Offset(left, top),
                size = Size(rectWidth, rectHeight),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadius, cornerRadius),
                style = Stroke(width = 2.dp.toPx())
            )
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Header text
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 120.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Scan Buahmu",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = ComposeColor.White,
                    textAlign = TextAlign.Center
                )
                
                Text(
                    text = "Arahkan kamera ke buah untuk mengetahui nutrisinya",
                    fontSize = 14.sp,
                    color = ComposeColor.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp, start = 32.dp, end = 32.dp)
                )
            }
            
            // Camera button at the bottom
            FloatingActionButton(
                onClick = {
                    isLoading = true
                    capturePhoto(
                        context,
                        cameraController,
                        onCaptured = { bitmap ->
                            isLoading = false
                            onPhotoTaken(bitmap)
                        }
                    )
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 90.dp)
                    .size(72.dp),
                shape = CircleShape,
                containerColor = ComposeColor(0xFF2196F3)
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(ComposeColor.White)
                )
            }
            // Back button - ensure it's clickable and navigates back to HomeScreen
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 30.dp, end = 10.dp)
                    .size(70.dp)
                    .clip(CircleShape)
                    .clickable { onExitClick() }, // Make sure this line is not commented out
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "×",
                    color = ComposeColor.White,
                    fontSize = 35.sp
                )
            }
        }
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(3f)
                    .background(ComposeColor.Black.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = ComposeColor.White
                )
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
            
            // Ambil bagian tengah gambar
            val dimension = minOf(correctedBitmap.width, correctedBitmap.height)
            val x = (correctedBitmap.width - dimension) / 2
            val y = (correctedBitmap.height - dimension) / 2
            
            val croppedBitmap = Bitmap.createBitmap(
                correctedBitmap,
                x,
                y,
                dimension,
                dimension
            )
            
            // Resize menjadi 32x32 piksel
            val resizedBitmap = Bitmap.createScaledBitmap(croppedBitmap, 32, 32, true)
            
            onCaptured(resizedBitmap)
            image.close()
        }
        override fun onError(exception: ImageCaptureException) {
            Log.e("CameraContent", "Error capturing image", exception)
            onCaptured(Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)) // Send empty bitmap to clear loading state
        }
    })
}

@Preview
@Composable
private fun CameraPreview() {
    SmartNutritionTheme {
        CameraScreen(
            navigate = {}
        )
    }
}
