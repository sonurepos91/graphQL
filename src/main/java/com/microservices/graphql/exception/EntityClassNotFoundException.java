package com.microservices.graphql.exception;

public class EntityClassNotFoundException extends BaseException{

    public EntityClassNotFoundException (String field, String code, String message){
        super(field,code,message);
    }
}
