/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import constant.MessageType;

/**
 *
 * @author ngotu
 */
public class Message implements Serializable {
    private MessageType type;
    private Object content;

    public Message(MessageType type, Object content) {
        this.type = type;
        this.content = content;
    }

    // Getters
    public MessageType getType() {
        return type;
    }

    public Object getContent() {
        return content;
    }
}