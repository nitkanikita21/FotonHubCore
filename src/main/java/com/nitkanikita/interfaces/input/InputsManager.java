package com.nitkanikita.interfaces.input;

import org.bukkit.entity.Player;

import java.util.*;

public class InputsManager {
    private static Map<Player, ArrayDeque<BaseRequestInput>> requests = new HashMap<>();

    public static Deque<BaseRequestInput> getRequests(Player t) {
        return requests.get(t);
    }

    public static void sendRequest(BaseRequestInput r){
        if(!requests.containsKey(r.getTarget())){
            requests.put(r.getTarget(),new ArrayDeque<>());
            requests.get(r.getTarget()).add(r);
            r.request(false);
            r.getTarget().getOpenInventory().close();
        }else{
            if(requests.get(r.getTarget()).isEmpty()){
                requests.get(r.getTarget()).add(r);
                r.request(false);
            }else {
                requests.get(r.getTarget()).add(r);
            }
        }

    }

    public static void next(Player t){
        if(requests.get(t) == null)return;
        requests.get(t).pop();
        if(!requests.get(t).isEmpty()){
            requests.get(t).peekFirst().request(false);
        }
    }
}
