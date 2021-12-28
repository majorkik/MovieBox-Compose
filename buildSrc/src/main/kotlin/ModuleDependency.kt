object ModuleDependency {

    const val app = ":app"
    const val navigation = ":navigation"

    object UI {
        private const val directory = ":ui"

        const val main = "$directory:main"
        const val home = "$directory:home"
        const val movieDetails = "$directory:movie_details"
    }

    object Feature {
        private const val directory = ":feature"

        object Tmdb {
            const val api = "$directory:tmdb:api"
            const val impl = "$directory:tmdb:impl"
        }

        object AppPrefenrences {
            const val api = "$directory:app-preferences:api"
            const val impl = "$directory:app-preferences:impl"
        }
    }

    object Core {
        private const val directory = ":core"

        const val ui = "$directory:ui"
        const val common = "$directory:common"
    }
}
