package com.alex.code.challenge.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OperationRequest {
//    private OperationType operation;
    private String operation;
    private String input1;
    private String input2;
}
