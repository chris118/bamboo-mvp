package com.hh.bamboobase.command;

import java.util.UUID;

import rx.subjects.ReplaySubject;

/**
 * Created by chrisw on 2017/9/18.
 */

public class CommandHelper {
    private static final String TAG = "CommandHelper";
    private CommandMeditor mMeditor;

    private static class SingletonHolder{
        private static final CommandHelper instance = new CommandHelper();
    }
    private CommandHelper(){
        mMeditor = new CommandMeditor();
    }

    public static CommandHelper getInstance(){
        return SingletonHolder.instance;
    }

    public ReplaySubject<Command> sendCommand(String msg){
        String uuid = UUID.randomUUID().toString();
        Command command = new Command(0, uuid, msg);

        //登记command
        ReplaySubject<Command> subject = mMeditor.request(command);

//        boolean ret = IMClientHelper.Instance.forwardMsg(uuid, msg);
//        if(ret == false){
//            mMeditor.response(new Command(-1, uuid, ""));
//        }

        return subject;
    }

    public void commandCallback(Command command){
        mMeditor.response(command);
    }

    public void destroy(){
        mMeditor.destroy();
    }
}
