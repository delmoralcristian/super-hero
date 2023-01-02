package mindata.delmoralcristian.superhero.enums;

public enum CommonMessage {

    SUPER_HERO_NOT_FOUND("Super hero not found - Id: '%s'"),
    USER_NOT_FOUND("Username '%s' not found");

    private String message;

    CommonMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
