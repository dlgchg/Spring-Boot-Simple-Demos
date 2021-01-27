package file.manage.spring.controller

import file.manage.spring.exception.FileNotFoundException
import file.manage.spring.service.FileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import kotlin.streams.toList

@RestController
@RequestMapping("/")
class FileController {

    @Autowired
    lateinit var fileService: FileService

    @GetMapping
    fun downFile(): Any {
        return fileService.loadAll().map {
            MvcUriComponentsBuilder.fromMethodName(FileController::class.java,
                "serveFile",
                it.fileName.toString()).build().toUri().toString()
        }.toList()
    }

    @GetMapping("/files/{filename:.+}")
    fun serveFile(@PathVariable("filename") filename: String): ResponseEntity<Resource> {
        val file = fileService.loadResource(filename)
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${file.filename}\"")
            .body(file)
    }

    @PostMapping
    fun uploadFile(@RequestParam("file") file: MultipartFile) {
        fileService.store(file)
    }

    @ExceptionHandler(FileNotFoundException::class)
    fun handleStorageFileNotFound(exc: FileNotFoundException?): ResponseEntity<*>? {
        return ResponseEntity.notFound().build<Any>()
    }



}