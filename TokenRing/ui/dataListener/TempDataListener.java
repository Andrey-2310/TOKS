package ui.dataListener;

import lombok.AllArgsConstructor;
import ui.chatInterface.ChatInterface;

@AllArgsConstructor
public class TempDataListener implements DataListener {
    private ChatInterface portInterface;

    @Override
    public void update(String newData) {
        portInterface.getLastCameData().setText(newData);
    }
}
