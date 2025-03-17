package xcom.niteshray.apps.aptiprep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import xcom.niteshray.apps.aptiprep.navigation.NavArguments
import xcom.niteshray.apps.aptiprep.navigation.Screen
import xcom.niteshray.apps.aptiprep.ui.screens.*
import xcom.niteshray.apps.aptiprep.ui.theme.AptiPrepTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }
            
            AptiPrepTheme(darkTheme = isDarkTheme) {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route
                ) {
                    composable(Screen.Home.route) {
                        HomeScreen(
                            onCategoryClick = { categoryId ->
                                navController.navigate(Screen.QuestionList.createRoute(categoryId))
                            },
                            onThemeToggle = { isDarkTheme = !isDarkTheme },
                            isDarkTheme = isDarkTheme
                        )
                    }

                    composable(
                        route = Screen.QuestionList.route,
                        arguments = listOf(
                            navArgument(NavArguments.CATEGORY_ID) {
                                type = NavType.IntType
                            }
                        )
                    ) { backStackEntry ->
                        val categoryId = backStackEntry.arguments?.getInt(NavArguments.CATEGORY_ID) ?: 1
                        QuestionListScreen(
                            categoryId = categoryId,
                            onQuestionClick = { questionId ->
                                navController.navigate(Screen.QuestionDetail.createRoute(questionId))
                            },
                            onBackClick = { navController.popBackStack() }
                        )
                    }

                    composable(
                        route = Screen.QuestionDetail.route,
                        arguments = listOf(
                            navArgument(NavArguments.QUESTION_ID) {
                                type = NavType.IntType
                            }
                        )
                    ) { backStackEntry ->
                        val questionId = backStackEntry.arguments?.getInt(NavArguments.QUESTION_ID) ?: 1
                        QuestionDetailScreen(
                            questionId = questionId,
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}