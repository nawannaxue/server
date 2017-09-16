package com.nwnx.components.validation;

public interface BeanValidator {

     <T> void validate(T object, Class<?>... groups);
}

