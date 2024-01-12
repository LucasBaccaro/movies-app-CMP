package core.data
sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val error: String, val t: Throwable? = null) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Errors =$error, throwable=$t]"
        }
    }
}
suspend inline fun <T : Any, R : Any> Result<T>.mapSuccess(
    crossinline mapper: suspend (Result.Success<T>) -> Result<R>
): Result<R> {
    return when (this) {
        is Result.Success<T> -> mapper(this)
        is Result.Error -> this
    }
}
fun <T : Any> T.toSuccess() = Result.Success(this)
inline fun <T : Any> Result<T>.orElse(otherwise: (Result.Error) -> T): T {
    return when (this) {
        is Result.Error -> otherwise(this)
        is Result.Success -> this.data
    }
}
