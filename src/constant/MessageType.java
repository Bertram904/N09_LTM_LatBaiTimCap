/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package constant;

/**
 *
 * @author ngotu
 */
public enum MessageType {
    //Authentication
    LOGIN("login"),
    LOGOUT("logout"),
    LOGIN_SUCCESS("login_success"),
    LOGIN_FAILURE("login_failure"),
    LOGOUT_SUCCESS("logout_success"),
    
    //Notification
    UPDATE_PLAYER_STATUS("update_player_status"),
    
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
