package co.touchlab.kampstarter

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking

actual fun <T> runTest(block: suspend () -> T) {
    runBlocking { block() }
}

class SqlDelightTestJvm : SqlDelightTest()

@ExperimentalCoroutinesApi
class BreedModelTestJvm : BreedModelTest()
