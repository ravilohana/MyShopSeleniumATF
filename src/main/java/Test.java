import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {

    public static void main(String[] args) {

        Path currDirWork = Paths.get("").toAbsolutePath();

        String folderName = "screenshot";
        String fileName = "file.png";

        Path filePath = currDirWork.resolve(Paths.get(folderName,fileName));

        System.out.println(filePath.toString());


    }
}
