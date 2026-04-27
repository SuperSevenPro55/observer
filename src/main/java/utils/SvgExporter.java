package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class SvgExporter {
    public void export(String fileName, String svgContent) {
        try {
            Files.writeString(Path.of(fileName),
                    svgContent,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении файла " + fileName, e);
        }
    }
}