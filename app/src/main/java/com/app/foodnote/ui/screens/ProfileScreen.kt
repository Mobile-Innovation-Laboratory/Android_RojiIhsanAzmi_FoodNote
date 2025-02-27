package com.app.foodnote.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.foodnote.AuthState
import com.app.foodnote.AuthViewModel
import com.app.foodnote.CreditContent
import com.app.foodnote.FeedbackContent
import com.app.foodnote.LoginScreen
import com.app.foodnote.ui.viewmodel.RecipeViewModel

@Composable
fun ProfileContent(navController: NavController? = null, authViewModel: AuthViewModel, recipeViewModel: RecipeViewModel) {
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when(authState.value) {
            is AuthState.Unauthenticated -> navController?.navigate(LoginScreen)
            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(50.dp))

        TextButton(
            onClick = {
                navController?.navigate(CreditContent)
            }
        ) {
            Text("Credits")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                authViewModel.signout()
                navController?.navigate(LoginScreen)
            }
        ) {
            Icon(Icons.Default.ExitToApp, contentDescription = "Sign Out")
            Spacer(modifier = Modifier.size(8.dp))
            Text("Sign Out")
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingActionButton(
                onClick = {
                    navController?.navigate(FeedbackContent)
                },
                modifier = Modifier.padding(16.dp),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.ThumbUp, contentDescription = "Feedback")
            }
        }
    }
}