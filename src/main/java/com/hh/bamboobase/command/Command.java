package com.hh.bamboobase.command;

/**
 * Created by chrisw on 2017/9/18.
 */

public class Command {
    public Command(int code, String uuid, String msg) {
        this.code = code;
        this.uuid = uuid;
        this.msg = msg;
    }

    @Override
    public String toString(){
        return "\n" + "code = " + String.valueOf(code) + "\n" +
                "uuid =" + uuid + "\n" +
                "msg =" + msg + "\n";
    }

    public Command(){}

    private int code;
    private String uuid;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
