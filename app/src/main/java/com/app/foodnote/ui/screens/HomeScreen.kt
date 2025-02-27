package com.app.foodnote.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.foodnote.AuthViewModel
import com.app.foodnote.ui.viewmodel.RecipeViewModel

data class NavigationItem(
    val title: String,
    val iconSelected: ImageVector,
    val iconUnselected: ImageVector
)


@Composable
fun HomeScreen(navController: NavController? = null, authViewModel: AuthViewModel, recipeViewModel: RecipeViewModel) {
    val screens = listOf(
        NavigationItem(
            "Home",
            Icons.Filled.Home,
            Icons.Outlined.Home
        ),
        NavigationItem(
            "My Schedule",
            Icons.Filled.DateRange,
            Icons.Outlined.DateRange
        ),
        NavigationItem(
            "Profile",
            Icons.Filled.Person,
            Icons.Outlined.Person
        ),
    )
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                screens.forEachIndexed { index, s ->
                    NavigationBarItem(
                        icon = {
                            if (selectedItemIndex == index) Icon(
                                s.iconSelected,
                                contentDescription = null
                            ) else Icon(
                                s.iconUnselected,
                                contentDescription = null
                            )
                        },
                        label = { Text(s.title) },
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                        }
                    )
                }
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp)
            ) {
                when (selectedItemIndex) {
                    0 -> RecipeContent(recipeViewModel)
                    1 -> ScheduleContent()
                    2 -> ProfileContent(navController = navController, authViewModel, recipeViewModel)
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navController = null, authViewModel = AuthViewModel(), recipeViewModel = RecipeViewModel())
}