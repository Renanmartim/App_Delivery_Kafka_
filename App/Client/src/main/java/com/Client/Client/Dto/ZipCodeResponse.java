package com.Client.Client.Dto;

public class ZipCodeResponse {
    private Result result;

    public Result getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "ZipCodeResponse{" +
                "result=" + result +
                '}';
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result {
        private Boolean error;

        public Boolean getError() {
            return error;
        }

        public void setError(Boolean error) {
            this.error = error;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "error=" + error +
                    '}';
        }
    }
}
