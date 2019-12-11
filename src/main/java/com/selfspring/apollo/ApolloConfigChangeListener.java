//package com.selfspring.apollo;
//
///**
// * Created by ckyang on 2019/8/23.
// */
//public class ApolloConfigChangeListener implements ConfigChangeListener {
//    @Override
//    public void onChange(ConfigChangeEvent changeEvent) {
//        System.out.println("Changes for namespace " + changeEvent.getNamespace());
//        for (String key : changeEvent.changedKeys()) {
//            ConfigChange change = changeEvent.getChange(key);
//            System.out.println(String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
//        }
//    }
//}
