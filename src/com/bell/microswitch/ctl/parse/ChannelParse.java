package com.bell.microswitch.ctl.parse;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.bell.microswitch.model.channel.base.IChannel;
import com.bell.microswitch.model.channel.base.IChannelInterceptor;
import com.bell.microswitch.model.channel.base._ServerChannel;

public class ChannelParse {
  private static Logger logger = Logger.getLogger(ChannelParse.class);

  public ChannelParse() {
  }

  public static List<IChannel> parse(File file) throws Exception {
    SAXReader saxReader = new SAXReader();
    Document document = null;
    ArrayList channelList = new ArrayList();

    try {
      document = saxReader.read(file);
    } catch (DocumentException var20) {
      logger.error("parse channel error", var20);
      throw new Exception( "parse channel file[" + file, var20);
    }

    Element rootElement = document.getRootElement();
    List nodeList = rootElement.selectNodes("Channel");
    Iterator it = nodeList.iterator();

    while(it.hasNext()) {
      Element ele = (Element)it.next();
      String impclass = ele.attributeValue("impClass");
      if(StringUtils.isEmpty(impclass)) {
        throw new Exception(  "channel impclass is empty");
      }

      try {
        _ServerChannel channel = (_ServerChannel)Class.forName(impclass).newInstance();
        Iterator e = ele.attributeIterator();

        while(e.hasNext()) {
          Attribute atr = (Attribute)e.next();
          channel.setAttrValue(atr.getName(), atr.getValue());
        }

        Iterator atr1 = ele.selectNodes("attrs/attr").iterator();

        while(atr1.hasNext()) {
          Element formatIt = (Element)atr1.next();
          channel.setAttrValue(formatIt.attributeValue("id"), formatIt.attributeValue("value"));
        }
        Iterator fait1;
        /*
        Iterator formatIt1 = ele.selectNodes("Format").iterator();
        Iterator fait1;
        if(formatIt1.hasNext()) {
          Element interIt = (Element)formatIt1.next();
          String epIt = interIt.attributeValue("impClass");
          _Format at = (_Format)Class.forName(epIt).newInstance();
          Iterator channelInterceptor = interIt.attributeIterator();

          while(channelInterceptor.hasNext()) {
            Attribute fait = (Attribute)channelInterceptor.next();
            at.setAttrValue(fait.getName(), fait.getValue());
          }

          fait1 = interIt.selectNodes("attrs/attr").iterator();

          while(fait1.hasNext()) {
            Element fatr = (Element)fait1.next();
            at.setAttrValue(fatr.attributeValue("id"), fatr.attributeValue("value"));
          }

          channel.setFormat(at);
        }*/

        Iterator interIt1 = ele.selectNodes("ChannelInterceptors/ChannelInterceptor").iterator();
        
        while(interIt1.hasNext()) {
          Element epIt1 = (Element)interIt1.next();
          String at1 = epIt1.attributeValue("impClass");
          IChannelInterceptor channelInterceptor1 = (IChannelInterceptor)Class.forName(at1).newInstance();
          fait1 = epIt1.attributeIterator();

          while(it.hasNext()) {
            Attribute fatr1 = (Attribute)fait1.next();
            channelInterceptor1.setAttrValue(fatr1.getName(), fatr1.getValue());
          }

          Iterator fatr2 = epIt1.selectNodes("attrs/attr").iterator();

          while(fatr2.hasNext()) {
            Element fat = (Element)fatr2.next();
            channelInterceptor1.setAttrValue(fat.attributeValue("id"), fat.attributeValue("value"));
          }

          channel.addInterceptor(channelInterceptor1);
        }

        Iterator epIt2 = ele.selectNodes("EndpointMappings/mapping").iterator();

        while(epIt2.hasNext()) {
            Element at2 = (Element)epIt2.next();
            channel.addEndPoint(at2.attributeValue("key"), at2.attributeValue("uri"));
        }

        channelList.add(channel);
      } catch (Exception var21) {
        throw new Exception( "channel parse fail," + var21.getMessage(), var21);
      }
    }

    return channelList;
  }
}
