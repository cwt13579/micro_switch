package com.bell.microswitch.ctl.utils;

import java.io.File;

public class PathUtil {
  //private static final String SWITCH_PATH = "switch";
  private static final String SERVICES_PATH = "Services";
  private static final String BUFFER_PATH = "Buffer";
  private static final String CHANNEL_PATH = "Channel";
  private static final String CHANNELCLIENT_PATH = "ChannelClient";
  private static final String CLIENT_PATH = "Client";
  private static String rootPath = null;

  public PathUtil() {
  }

  public static void init(String path) {
    rootPath = path;
  }

  public static String getBufferPath() {
    return rootPath + File.separator + BUFFER_PATH;
  }

  public static String getServerPath() {
    return rootPath + File.separator + SERVICES_PATH;
  }

  public static String getChannelPath() {
    return rootPath + File.separator + CHANNEL_PATH;
  }

  public static String getChannelClientPath() {
    return rootPath + File.separator + CHANNELCLIENT_PATH;
  }

  public static String getClientPath() {
    return rootPath + File.separator + CLIENT_PATH;
  }

  public static String getBuffer(String type) {
    String relative_path = type.replaceAll("\\.", "\\" + File.separator);
    relative_path = BUFFER_PATH + File.separator + relative_path + ".xml";
    String path = rootPath + File.separator + relative_path;
    return path;
  }

  public static String getChannel(String type) {
    String relative_path = type.replaceAll("\\.", "\\" + File.separator);
    relative_path = CHANNEL_PATH + File.separator + relative_path + ".xml";
    String path = rootPath + File.separator + relative_path;
    return path;
  }

  public static String getChannelClient(String type) {
    String relative_path = type.replaceAll("\\.", "\\" + File.separator);
    relative_path = CHANNELCLIENT_PATH + File.separator + relative_path + ".xml";
    String path = rootPath + File.separator + relative_path;
    return path;
  }

  public static String getClient(String type) {
    String relative_path = type.replaceAll("\\.", "\\" + File.separator);
    relative_path = CLIENT_PATH + File.separator + relative_path + ".xml";
    String path = rootPath + File.separator + relative_path;
    return path;
  }

  public static String getServices(String type) {
    String relative_path = type.replaceAll("\\.", "\\" + File.separator);
    relative_path = SERVICES_PATH + File.separator + relative_path + ".xml";
    String path = rootPath + File.separator + relative_path;
    return path;
  }

  public static void destroy() {
    rootPath = null;
  }
}
