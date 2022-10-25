package ru.kaznacheev.restapi.dto;

import ru.kaznacheev.restapi.util.operation.OperationType;

public class InformationDTO {

    private String filePath;
    private OperationType operation;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }
}
