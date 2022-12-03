package ru.kaznacheev.restapi.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kaznacheev.restapi.dto.InformationDTO;
import ru.kaznacheev.restapi.util.ArrayReader;
import ru.kaznacheev.restapi.util.operation.OperationType;
import ru.kaznacheev.restapi.util.operation.OperationWithArray;
import ru.kaznacheev.restapi.util.operation.SequenceType;
import ru.kaznacheev.restapi.util.error.FilePathNotFoundException;
import ru.kaznacheev.restapi.util.error.OperationNotFoundException;

import java.util.HashMap;
import java.util.Map;

@Service
public class Handler {
    private InformationDTO information;
    private final ArrayReader ARRAY_READER;
    private final OperationWithArray OPERATION_WITH_ARRAY;

    @Autowired
    public Handler(ArrayReader arrayReader, OperationWithArray operationWithArray) {
        this.ARRAY_READER = arrayReader;
        this.OPERATION_WITH_ARRAY = operationWithArray;
    }

    public void setInformation(InformationDTO information) {
        this.information = information;
    }

    public Map<String, String> handle() {
        checkFields();
        int[] array = ARRAY_READER.readNumbersToArray(information.getFilePath());
        return doOperation(array, information.getOperation());
    }

    public void checkFields() {
        if (Strings.isBlank(information.getFilePath())) {
            throw new FilePathNotFoundException();
        }
        if (information.getOperation() == null) {
            throw new OperationNotFoundException();
        }
    }

    private Map<String, String> doOperation(int[] array, OperationType operation) {
        Map<String, String> result = new HashMap<>();
        switch (operation) {
            case GET_MAX_VALUE -> result.put("max_value", String.valueOf(OPERATION_WITH_ARRAY.getMaxValue(array)));
            case GET_MIN_VALUE -> result.put("min_value", String.valueOf(OPERATION_WITH_ARRAY.getMinValue(array)));
            case GET_MEDIAN_VALUE -> result.put("median_value", String.valueOf(OPERATION_WITH_ARRAY.getMedianValue(array)));
            case GET_ARITHMETIC_VALUE -> result.put("arithmetic_value", String.valueOf(OPERATION_WITH_ARRAY.getArithmeticValue(array)));
            case GET_INCREASE_SEQUENCE -> result.put("increase_sequence", String.valueOf(OPERATION_WITH_ARRAY.getSequence(array, SequenceType.INCREASE)));
            case GET_DECREASE_SEQUENCE -> result.put("decrease_sequence", String.valueOf(OPERATION_WITH_ARRAY.getSequence(array, SequenceType.DECREASE)));
        }
        return result;
    }

}
