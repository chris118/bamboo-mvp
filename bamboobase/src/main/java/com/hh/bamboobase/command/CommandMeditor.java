package com.hh.bamboobase.command;

import java.util.concurrent.ConcurrentHashMap;

import rx.subjects.ReplaySubject;

/**
 * Created by chrisw on 2017/9/18.
 */

public class CommandMeditor {
    private ConcurrentHashMap<String, ReplaySubject<Command>> subjects = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Command> commands = new ConcurrentHashMap<>();

    public ReplaySubject<Command> request(Command command) {
        ReplaySubject<Command> subject = ReplaySubject.create();
        subjects.put(command.getUuid(), subject);
        commands.put(command.getUuid(), command);
        return subject;
    }

    public void response(Command command){
        Command cmd = commands.get(command.getUuid());
        ReplaySubject<Command> subject = subjects.get(command.getUuid());

        if (cmd != null && subject != null) {
            if(command.getCode() != 0){
                subject.onError(new Throwable(String.valueOf(command.getCode())));
            }else {
                subject.onNext(command);
            }

            commands.remove(cmd.getUuid());
            subjects.remove(cmd.getUuid());
        }
    }

    public void destroy(){
        subjects.clear();
        commands.clear();
        subjects = null;
        commands = null;
    }

}
