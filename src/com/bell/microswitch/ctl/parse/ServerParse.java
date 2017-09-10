package com.bell.microswitch.ctl.parse;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.bell.microswitch.model.server.base.IServer;

public class ServerParse {
  private static Logger logger = Logger.getLogger(ServerParse.class);
  private static Map<String, String> serverType = new HashMap();

  static {
    serverType.put("common", "com.bell.microswitch.model.server.CommonServer");
  }

  public ServerParse() {
  }

  public static List<IServer> parse(File file) throws Exception {
    SAXReader saxReader = new SAXReader();
    Document document = null;
    ArrayList serverList = new ArrayList();

    try {
      document = saxReader.read(file);
    } catch (DocumentException var13) {
      logger.error("parse server error", var13);
      throw new Exception( "parse server file[" + file, var13);
    }

    Element rootElement = document.getRootElement();
    List serviceNodeList = rootElement.selectNodes("Service");
    Iterator it = serviceNodeList.iterator();

    while(it.hasNext()) {
      Element ele = (Element)it.next();
      String type = ele.attributeValue("type");
      String impclass = ele.attributeValue("impClass");
      if(StringUtils.isEmpty(type)) {
        throw new Exception( "service type is empty");
      }

      try {
        IServer server = (IServer)Class.forName((String)serverType.get(type)).newInstance();
        server.parse(ele);
        serverList.add(server);
      } catch (Exception var12) {
        throw new Exception( "service parse fail," + var12.getMessage(), var12);
      }
    }

    return serverList;
  }
}
