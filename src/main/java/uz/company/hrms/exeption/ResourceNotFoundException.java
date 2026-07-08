package uz.company.hrms.exeption;

import org.springframework.core.io.Resource;

public class ResourceNotFoundException extends RuntimeException{


    public ResourceNotFoundException(String message){
        super(message);
    }

}
