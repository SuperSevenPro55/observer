package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SvgExporter {
    private final String outputDir;

    public SvgExporter() {
        this.outputDir = "output";
    }

    public void export(String fileName, String svgContent) {
        try {
            Path dirPath = Paths.get(outputDir);

            if (Files.notExists(dirPath)) {
                Files.createDirectories(dirPath);
                //System.out.println("Создана директория: " + dirPath.toAbsolutePath());
            }

            Path filePath = dirPath.resolve(fileName);

            Files.writeString(filePath,
                    svgContent,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);

            //System.out.println("Обновлен график: " + filePath.toAbsolutePath());

        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении файла " + fileName, e);
        }
    }
}