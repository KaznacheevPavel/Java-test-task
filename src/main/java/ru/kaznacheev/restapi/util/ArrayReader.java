package ru.kaznacheev.restapi.util;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import ru.kaznacheev.restapi.util.error.UserFileNotFoundException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
public class ArrayReader {

    @Cacheable(cacheNames = "file", key = "#path")
    public int[] readNumbersToArray(String path) {
        int[] array = new int[0];
        if (Files.exists(Paths.get(path))) {
            try (Stream<String> stream = Files.lines(Paths.get(path))) {
                array = stream.mapToInt(Integer::valueOf).toArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new UserFileNotFoundException();
        }
        return array;
    }
}
