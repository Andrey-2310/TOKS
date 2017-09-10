package ui.dataListener;

import lombok.AllArgsConstructor;
import ui.PortInterface.PortInterface;

@AllArgsConstructor
public class DataListenerImpl implements DataListener {
    private PortInterface portInterface;

    @Override
    public void update(String newData) {
        portInterface.getCurrentData().setText(newData);
    }
}
