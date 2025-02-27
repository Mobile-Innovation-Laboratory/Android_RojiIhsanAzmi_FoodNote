package com.app.foodnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.app.foodnote.ui.screens.CreditContent
import com.app.foodnote.ui.screens.FeedbackContent
import com.app.foodnote.ui.screens.HomeScreen
import com.app.foodnote.ui.screens.LoginScreen
import com.app.foodnote.ui.screens.MemberDetailScreen
import com.app.foodnote.ui.screens.ProfileContent
import com.app.foodnote.ui.screens.RegisterScreen
import com.app.foodnote.ui.theme.FitNoteDailyTheme
import com.app.foodnote.ui.viewmodel.RecipeViewModel
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    private val recipeViewModel: RecipeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitNoteDailyTheme {
                val navController = rememberNavController()
                val authViewModel = AuthViewModel()
                NavHost(navController = navController, startDestination = LoginScreen) {
                    composable<LoginScreen> {
                        LoginScreen(navController, authViewModel, recipeViewModel)
                    }
                    composable<HomeScreen> {
                        HomeScreen(navController, authViewModel, recipeViewModel)
                    }
                    composable<RegisterScreen> {
                        RegisterScreen(navController, authViewModel, recipeViewModel)
                    }
                    composable<ProfileContent> {
                        ProfileContent(navController, authViewModel, recipeViewModel)
                    }
                    composable<MemberDetail> {
                        val args = it.toRoute<MemberDetail>()
                        MemberDetailScreen(args)
                    }
                    composable<FeedbackContent> {
                        FeedbackContent(navController, authViewModel, recipeViewModel)
                    }
                    composable<CreditContent> {
                        CreditContent(navController, recipeViewModel, authViewModel)
                    }
                }

            }
        }
    }
}

@Serializable
object LoginScreen

@Serializable
object HomeScreen

@Serializable
object RegisterScreen

@Serializable
object ProfileContent

@Serializable
object FeedbackContent

@Serializable
object CreditContent

@Serializable
data class MemberDetail(val memberName : String, val memberRole : String, val memberImageUrl : String)