package constants;

public class MessageType {

    // ====== Authentication ======
    public static final String LOGIN = "login";
    public static final String LOGOUT = "logout";
    public static final String LOGIN_SUCCESS = "login_success";
    public static final String LOGIN_FAILURE = "login_failure";
    public static final String LOGOUT_SUCCESS = "logout_success";
    public static final String STATUS_UPDATE = "status_update";

    // ====== Game Actions ======
    public static final String GET_PLAYERS = "get_palyers";
    public static final String PLAYER_LIST = "player_list";
    public static final String START_GAME = "start_game";
    public static final String FLIP_CARD = "flip_card";
    public static final String MATCH_SUCCESS = "match_success";
    public static final String MATCH_FAIL = "match_fail";
    public static final String GAME_OVER = "game_over";
    public static final String WINNER = "winner";
    public static final String LOSER = "loser";

    // ====== System / Connection ======
    public static final String CONNECTED = "connected";
    public static final String DISCONNECTED = "disconnected";
    public static final String SERVER_FULL = "server_full";
    public static final String ERROR = "error";

    private MessageType() {
    }
}
