package file.manage.spring.path

import org.junit.jupiter.api.Test
import java.io.IOException
import java.nio.file.*
import java.nio.file.attribute.BasicFileAttributes

class PathTests {

    @Test
    fun path() {
        // 使用Paths工厂创建Path
        val path: Path = Paths.get("/Users/liwei/IdeaProjects/Spring-Boot-Simple-Demos/FileManage/upload-dir/05-mybatis.txt")
        println(path.toAbsolutePath())
        println(Files.exists(path))

        val pPath = Paths.get("/Users/liwei/IdeaProjects/Spring-Boot-Simple-Demos/FileManage/upload-dir")
        Files.walkFileTree(pPath, object : FileVisitor<Path>{
            override fun preVisitDirectory(dir: Path?, attrs: BasicFileAttributes?): FileVisitResult {
                println("pre visit dir: $dir")
                return FileVisitResult.CONTINUE
            }

            override fun visitFile(file: Path?, attrs: BasicFileAttributes?): FileVisitResult {
                println("pre visit file: $file")
                return FileVisitResult.CONTINUE
            }

            override fun visitFileFailed(file: Path?, exc: IOException?): FileVisitResult {
                println("pre visit file: $file")
                return FileVisitResult.CONTINUE
            }

            override fun postVisitDirectory(dir: Path?, exc: IOException?): FileVisitResult {
                println("pre visit dir: $dir")
                return FileVisitResult.CONTINUE
            }

        })

        // 创建当前目录的相对路径
        val currentPath = Paths.get(".")
        // normalize() 移除 . 和 ..
        println(currentPath.toAbsolutePath().normalize())
        // 创建上级目录的相对路径
        val parentPath = Paths.get("..")
        println(parentPath.toAbsolutePath().normalize())
        val cPath = Paths.get("/Users/liwei/IdeaProjects/Spring-Boot-Simple-Demos/FileManage/upload-dir/create/06-mybatis.txt")
        if (!Files.exists(cPath)) {
            Files.createDirectory(cPath)
        }
        Files.copy(path, cPath, StandardCopyOption.REPLACE_EXISTING)
        val mPath = Paths.get("/Users/liwei/IdeaProjects/Spring-Boot-Simple-Demos/FileManage/upload-dir/move/07-mybatis.txt")
        Files.move(cPath, mPath, StandardCopyOption.REPLACE_EXISTING)
//        if (Files.exists(mPath)) {
//            Files.delete(cPath)
//        }
    }
}