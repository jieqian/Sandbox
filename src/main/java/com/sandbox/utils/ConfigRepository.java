package com.sandbox.utils;


import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;

public class ConfigRepository {
	private static Config config;
	
	static{
		System.setProperty("env","dev");
    	System.setProperty("app.id", "ppc-cloud-parent");
		
		 config = ConfigService.getAppConfig();
        /**
         * 增加监听器：
         *   在配置中心服务端的配置有变动的时候，如果ojbect.map这个key对应value(json字符串)发生变化了，
         *   则拿最新的value(json字符串)转换成一个新的map对象，来替换原来旧的map对象。
         */
        config.addChangeListener(new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent changeEvent) {
                for (String key : changeEvent.changedKeys()) {
                	ConfigChange change = changeEvent.getChange(key);
                    System.out.println(String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
                }
            }
        });
	}

    public static Config INSTANCE(){
        return config;
    }

}
