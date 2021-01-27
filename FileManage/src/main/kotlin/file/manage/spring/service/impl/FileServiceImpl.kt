package file.manage.spring.service.impl

import file.manage.spring.exception.FileException
import file.manage.spring.properties.FileProperties
import file.manage.spring.service.FileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.util.FileSystemUtils
import org.springframework.web.multipart.MultipartFile
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Stream

@Service
class FileServiceImpl @Autowired constructor(properties: FileProperties) : FileService {

    final var rootLocation: Path = Paths.get(properties.location)

    override fun init() {
        Files.createDirectory(rootLocation)
    }

    override fun store(file: MultipartFile) {
        try {
            if (file.isEmpty) {
                throw FileException("Failed to store empty file ${file.originalFilename}")
            }
            Files.copy(file.inputStream, rootLocation.resolve(file.originalFilename))
        } catch (e: IOException) {
            throw FileException("Failed to store file ${file.originalFilename} $e")
        }
    }

    override fun loadAll(): Stream<Path> {
        return Files.walk(rootLocation, 1)
            .filter { it != rootLocation }
            .map { rootLocation.relativize(it) }
    }

    override fun load(fileName: String): Path = rootLocation.resolve(fileName)

    override fun loadResource(fileName: String): Resource {
        val path = load(fileName)
        val resource = UrlResource(path.toUri())
        if (resource.exists() || resource.isReadable) {
            return resource
        }
        throw FileNotFoundException("Could not read file: $fileName")
    }

    override fun deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile())
    }
}