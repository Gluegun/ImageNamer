import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        String src = "C:/users/sunpu/desktop/src";
        String dst = "C:/users/sunpu/desktop/FolderWithNames";
        
        createAndFillFile(src, dst);

    }

    public static void createAndFillFile(String src, String dst) throws IOException {
        File dir = new File(src);
        File[] files = dir.listFiles();
        List<File> fileList = Arrays.asList(files);
        List<String> filesNames = new ArrayList<>();

        fileList.forEach(file -> filesNames.add(file.getName()));

        Path dstFolder = Paths.get(dst);
        Path dstFile = Paths.get(dst + "/names.txt");

        if (!Files.exists(dstFolder)) {
            Files.createDirectory(dstFolder);
        }

        if (!Files.exists(dstFile)) {
            Files.createFile(dstFile);
        }

        try {
            Files.write(dstFile, filesNames, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
