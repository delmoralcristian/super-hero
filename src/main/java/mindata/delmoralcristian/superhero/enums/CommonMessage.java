package mindata.delmoralcristian.superhero.enums;

public enum CommonMessage {

    SUPER_HERO_NOT_FOUND("Super hero not found - Id: '%s'");

    private String message;

    CommonMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
