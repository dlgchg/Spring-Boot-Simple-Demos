package file.manage.spring.exception

import java.lang.RuntimeException

open class FileException : RuntimeException {
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
}
