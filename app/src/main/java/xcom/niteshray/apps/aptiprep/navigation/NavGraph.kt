package xcom.niteshray.apps.aptiprep.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object QuestionList : Screen("questions/{categoryId}") {
        fun createRoute(categoryId: Int) = "questions/$categoryId"
    }
    object QuestionDetail : Screen("question/{questionId}") {
        fun createRoute(questionId: Int) = "question/$questionId"
    }
}

object NavArguments {
    const val CATEGORY_ID = "categoryId"
    const val QUESTION_ID = "questionId"
}
