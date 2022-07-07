package com.AppGaeBom.sickal.exception;

public class Constants {
    public enum ExceptionClass {

        MEMBER("Member"), Login("login");//에러클래스 custom타입으로 열거

        private String exceptionClass;

        ExceptionClass(String exceptionClass) {
            this.exceptionClass = exceptionClass;
        }

        public String getExceptionClass() {
            return exceptionClass;
        }

        @Override
        public String toString() {
            return getExceptionClass() + " Exception. ";
        }

    }
}
