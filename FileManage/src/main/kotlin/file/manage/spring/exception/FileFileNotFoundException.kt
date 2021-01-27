package file.manage.spring.exception

class FileNotFoundException : FileException {
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
}