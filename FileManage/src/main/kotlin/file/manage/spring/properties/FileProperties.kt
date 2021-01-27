package file.manage.spring.properties

import org.springframework.stereotype.Repository

@Repository
class FileProperties(var location: String = "upload-dir")