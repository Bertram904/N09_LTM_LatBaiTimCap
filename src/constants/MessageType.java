package constants;

public enum MessageType {
    //Authentication
    LOGIN("login"),
    LOGOUT("logout"),
    LOGIN_SUCCESS("login_success"),
    LOGIN_FAILURE("login_failure"),
    LOGOUT_SUCCESS("logout_success"),
    STATUS_UPDATE("status_update"),
    //Notification
    UPDATE_PLAYER_STATUS("update_player_status"),
    GET_PLAYERS("get_palyers"),
    PLAYER_LIST("player_list"),
    START_GAME("start_game"),
    //Connection
    CONNECTED("connected"),
    DISCONNECTED("disconnected"),
    ERROR("error"),
    //GET
    ONLINE_LIST("online_list");

    /**
     *
     */
    private final String value;

    private MessageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

}
