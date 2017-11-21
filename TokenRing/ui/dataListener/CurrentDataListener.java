package ui.dataListener;

import lombok.AllArgsConstructor;
import ui.chatInterface.ChatInterface;

@AllArgsConstructor
public class CurrentDataListener implements DataListener {
    private ChatInterface portInterface;
    @Override
    public void update(String newData) {
        portInterface.getCurrentData().setText(newData);
    }
}
