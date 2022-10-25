package ru.kaznacheev.restapi.util.operation;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OperationWithArray {

    @Cacheable(cacheNames = "maxValue")
    public Integer getMaxValue(int[] array) {
        if (array.length == 0) {
            return null;
        }
        return Arrays.stream(array).max().getAsInt();
    }

    @Cacheable(cacheNames = "minValue")
    public Integer getMinValue(int[] array) {
        if (array.length == 0) {
            return null;
        }
        return Arrays.stream(array).min().getAsInt();
    }

    @Cacheable(cacheNames = "medianValue")
    public Double getMedianValue(int[] array) {
        if (array.length == 0) {
            return null;
        }
        int[] tempArray = array.clone();
        Arrays.parallelSort(tempArray);
        int middleIndex = 0;
        if (tempArray.length % 2 == 1) {
            middleIndex = tempArray.length / 2;
            return (double) tempArray[middleIndex];
        } else {
            middleIndex = (tempArray.length / 2) - 1;
            return (tempArray[middleIndex] + tempArray[middleIndex + 1]) / 2.0;
        }
    }

    @Cacheable(cacheNames = "arithmeticValue")
    public Double getArithmeticValue(int[] array) {
        if (array.length == 0) {
            return null;
        }
        return Arrays.stream(array).sum() / (double) array.length;
    }

    @Cacheable(cacheNames = "sequenceValue")
    public List<List<Integer>> getSequence(int[] array, SequenceType sequenceType) {
        if (array.length == 0) {
            return null;
        }
        List<List<Integer>> listOfSequences = new ArrayList<>();
        List<Integer> listOfNumbers = new ArrayList<>();
        int maxSequenceSize = 0;
        int currentSequenceSize = 1;
        listOfNumbers.add(array[0]);
        for (int i = 1; i < array.length; i++) {
            if (compareNumbers(listOfNumbers.get(listOfNumbers.size() - 1), array[i], sequenceType)) {
                currentSequenceSize++;
                listOfNumbers.add(array[i]);
            } else {
                if ((currentSequenceSize > maxSequenceSize) && (currentSequenceSize > 1)) {
                    maxSequenceSize = currentSequenceSize;
                    listOfSequences.clear();
                    listOfSequences.add(listOfNumbers);
                } else if (currentSequenceSize == maxSequenceSize) {
                    listOfSequences.add(listOfNumbers);
                }
                listOfNumbers = new ArrayList<>();
                listOfNumbers.add(array[i]);
                currentSequenceSize = 1;
            }
        }
        if (currentSequenceSize == maxSequenceSize) {
            listOfSequences.add(listOfNumbers);
        } else if ((currentSequenceSize > maxSequenceSize) && (currentSequenceSize > 1)) {
            listOfSequences.clear();
            listOfSequences.add(listOfNumbers);
        }
        return listOfSequences.size() == 0 ? null : listOfSequences;
    }

    private boolean compareNumbers(int firstValue, int secondValue, SequenceType operation) {
        return switch (operation) {
            case INCREASE -> firstValue < secondValue;
            case DECREASE -> firstValue > secondValue;
        };
    }
}
