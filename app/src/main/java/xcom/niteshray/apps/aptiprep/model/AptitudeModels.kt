package xcom.niteshray.apps.aptiprep.model

data class Category(
    val id: Int,
    val title: String,
    val description: String,
    val iconVector: String
)

data class Question(
    val id: Int,
    val categoryId: Int,
    val title: String,
    val description: String,
    val answer: String,
    val explanation: String,
    var isBookmarked: Boolean = false
)

object DummyData {
    val categories = listOf(
        Category(
            1,
            "Logical Reasoning",
            "Enhance your logical thinking and problem-solving skills",
            "psychology"
        ),
        Category(
            2,
            "Quantitative Reasoning",
            "Master mathematical and numerical reasoning",
            "calculate"
        ),
        Category(
            3,
            "Verbal Reasoning",
            "Improve your verbal and language comprehension",
            "text_fields"
        ),
        Category(
            4,
            "Puzzle Challenges",
            "Solve engaging puzzles and brain teasers",
            "extension"
        )
    )

    val questions = List(50) { index ->
        Question(
            id = index + 1,
            categoryId = (index % 4) + 1,
            title = "Question ${index + 1}",
            description = when ((index % 4) + 1) {
                1 -> "If all A are B, and all B are C, what can we conclude about A and C?"
                2 -> "If a train travels 300 km in 4 hours, what is its average speed?"
                3 -> "Choose the word that best completes the analogy: Book is to Reader as Movie is to ___"
                else -> "Solve the sequence: 2, 6, 12, 20, ?"
            },
            answer = when ((index % 4) + 1) {
                1 -> "All A are C"
                2 -> "75 kilometers per hour"
                3 -> "Viewer"
                else -> "30"
            },
            explanation = when ((index % 4) + 1) {
                1 -> "This is a syllogism. Since all A are B, and all B are C, it follows that all A must be C."
                2 -> "Using the formula Speed = Distance/Time: 300/4 = 75 km/h"
                3 -> "A book is consumed by a reader, similarly a movie is consumed by a viewer."
                else -> "The sequence adds 4, then 6, then 8, then 10. So next is 20 + 10 = 30"
            }
        )
    }
}
