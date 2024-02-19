package io.isott.expertise.infra.common.exception;

public class QuestionNotFoundException extends RuntimeException {
    private final String key;
    private final String[] args;

    public QuestionNotFoundException(String key) {
        super(key);
        this.key = key;
        args = new String[0];
    }

    public QuestionNotFoundException(String key, String... args) {
        super(key);
        this.key = key;
        this.args = args;
    }
}
