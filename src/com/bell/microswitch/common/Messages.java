package com.bell.microswitch.common;

import java.util.HashMap;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
  private static final String BUNDLE_NAME = "com.huateng.cupSecureEmu.common.messages";
  private static final ResourceBundle RESOURCE_BUNDLE_zh_CN;
  private static final ResourceBundle RESOURCE_BUNDLE_en_US;
  private static HashMap bundleMap;

  static {
    RESOURCE_BUNDLE_zh_CN = ResourceBundle.getBundle("com.huateng.cupSecureEmu.common.messages_" + Locale.SIMPLIFIED_CHINESE);
    RESOURCE_BUNDLE_en_US = ResourceBundle.getBundle("com.huateng.cupSecureEmu.common.messages_" + Locale.US);
    bundleMap = new HashMap(2);
    bundleMap.put(Locale.SIMPLIFIED_CHINESE.toString(), RESOURCE_BUNDLE_zh_CN);
    bundleMap.put(Locale.US.toString(), RESOURCE_BUNDLE_en_US);
  }

  private Messages() {
  }

  public static String getString(String key) {
    try {
      ResourceBundle e = (ResourceBundle)bundleMap.get(Locale.getDefault().toString());
      if(e == null) {
        e = RESOURCE_BUNDLE_en_US;
      }

      return e.getString(key);
    } catch (MissingResourceException var2) {
      return " " + key + " ";
    }
  }
}
