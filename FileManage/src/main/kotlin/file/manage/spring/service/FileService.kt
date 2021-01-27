package file.manage.spring.service

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path
import java.util.stream.Stream


interface FileService {
    fun init()
    fun store(file: MultipartFile)
    fun loadAll(): Stream<Path>
    fun load(fileName: String): Path
    fun loadResource(fileName: String): Resource
    fun deleteAll();
}
