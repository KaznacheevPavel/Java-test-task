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
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            array = stream.mapToInt(Integer::valueOf).toArray();
        } catch (FileNotFoundException e) {
            throw new UserFileNotFoundException();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }
}
