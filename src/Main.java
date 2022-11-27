import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String JPG_EXTENSION = ".jpg";
    public static void main(String[] args) throws IOException {

        String src = "D:\\Изображения\\Антонявр год\\2022-11-20-СТ2-1820\\понравились";
        String dst = src;

        createAndFillFile(src, dst);

    }

    public static void createAndFillFile(String src, String dst) throws IOException {
        File dir = new File(src);
        File[] files = dir.listFiles();
        List<String> filesNames = new ArrayList<>();

        for (File file : files) {

            if (file.getName().endsWith(JPG_EXTENSION)) {
                String fileName = getFileNameWithoutExtension(file.getName());
                BufferedImage image = ImageIO.read(file);
                int height = image.getHeight();
                int width = image.getWidth();
                if (height >= width) {
                    fileName += " - в";
                } else {
                    fileName += " - г";
                }
                filesNames.add(fileName + JPG_EXTENSION);
            }
        }

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

    private static String getFileNameWithoutExtension(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf('.') + 1);
    }
}
