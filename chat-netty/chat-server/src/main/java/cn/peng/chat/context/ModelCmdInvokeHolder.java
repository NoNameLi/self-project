package cn.peng.chat.context;

import java.util.concurrent.ConcurrentHashMap;

public class ModelCmdInvokeHolder {

    private static ConcurrentHashMap<Short, ConcurrentHashMap<Short, ModelCmdInvoke>> map = new ConcurrentHashMap<>();

    public static void put(ModelCmdInvoke invoke) {
        ConcurrentHashMap<Short, ModelCmdInvoke> cmdMap = map.computeIfAbsent(invoke.getModel(), key -> new ConcurrentHashMap<>());
        if (cmdMap.contains(invoke.getCmd())) {
            System.out.println("[model,cmd] exist, ignore");
        } else {
            cmdMap.put(invoke.getCmd(), invoke);
        }
    }

    public static ModelCmdInvoke get(short model, short cmd) {
        ConcurrentHashMap<Short, ModelCmdInvoke> cmdMap = map.get(model);
        if (null == cmdMap) {
            return null;
        }
        return cmdMap.get(cmdMap);
    }


}
