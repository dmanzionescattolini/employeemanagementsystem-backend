package com.blit.employeemanagementsystem.exceptions;

public class EmployeeInformationMissingException extends Exception {
    public EmployeeInformationMissingException(){
        super("Required employee field missing");
    }
}
