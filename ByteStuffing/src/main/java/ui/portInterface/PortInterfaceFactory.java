package ui.portInterface;

import java.util.HashMap;
import java.util.Map;

public enum PortInterfaceFactory implements PortFactoryInterface {
    INTERFACE_FACTORY {
        public PortInterface getPortInterface(String name) {
            return mapPort.get(name);
        }
    };
    private static Map<String, PortInterface> mapPort = new HashMap<>();

    public static void fulfillMapPort() {
        mapPort.put("COM1", new PortInterface("COM1").concreteFixing(0));
        mapPort.put("COM2", new PortInterface("COM2").concreteFixing(300));

    }


}
