package com.nitkanikita.interfaces.input.chat;

import com.nitkanikita.interfaces.input.BaseRequestInput;
import com.nitkanikita.interfaces.input.InputsManager;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import java.util.Deque;

public class ChatInputListener{

    public boolean check(AsyncChatEvent e){
        Deque<BaseRequestInput> requests = InputsManager.getRequests(e.getPlayer());
        if(requests == null)return true;
        BaseRequestInput requestInput = requests.peekFirst();
        if(requestInput != null) {
            boolean test = requestInput.getCallback().test(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.originalMessage()));
            if(!test){
                requestInput.request(true);
            }else {
                InputsManager.next(e.getPlayer());
            }

            return false;
        }
        return true;
    }
}
