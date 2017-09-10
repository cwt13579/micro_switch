package com.bell.microswitch.ctl;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.log4j.Logger;

import com.bell.microswitch.ctl.base.IEntryFactory;
import com.bell.microswitch.ctl.parse.ChannelParse;
import com.bell.microswitch.ctl.utils.PathUtil;
import com.bell.microswitch.model.channel.base.IChannel;
import com.bell.microswitch.model.channel.base._ServerChannel;

public class ChannelFactory implements IEntryFactory<IChannel> {
  private static final Logger logger = Logger.getLogger(ChannelFactory.class);
  private Map<String, IChannel> channelPools;
  private static ChannelFactory cf = null;

  public ChannelFactory() {
  }

  public static IEntryFactory<IChannel> getInstance() throws Exception {
    if(cf == null) {
      cf = new ChannelFactory();
      cf.create();
    }

    return cf;
  }

  public IChannel getEntry(String id) throws Exception {
    if(this.channelPools.containsKey(id)) {
      return (IChannel)this.channelPools.get(id);
    } else {
      throw new Exception("Server Channel Factory not found id[" + id + "]");
    }
  }

  public void start() throws Exception {
    Iterator it = this.channelPools.values().iterator();

    while(it.hasNext()) {
      IChannel channel = (IChannel)it.next();
      if(channel instanceof _ServerChannel) {
        ((_ServerChannel)channel).start();
      }
    }

  }

  public void stop() throws Exception {
    Iterator it = this.channelPools.values().iterator();

    while(it.hasNext()) {
      IChannel channel = (IChannel)it.next();
      if(channel instanceof _ServerChannel) {
        ((_ServerChannel)channel).stop();
      }
    }

  }

  public void create() throws Exception {
    this.channelPools = new HashMap();
  }

  public void parse() throws Exception {
    String path = PathUtil.getChannelPath();
    File folderFile = new File(path);
    if(folderFile.isDirectory() && folderFile.exists()) {
      Collection files = FileUtils.listFiles(folderFile, new SuffixFileFilter("xml", IOCase.INSENSITIVE), (IOFileFilter)null);
      Iterator it = files.iterator();

      while(it.hasNext()) {
        File channelFile = (File)it.next();
        List channelList = ChannelParse.parse(channelFile);

        for(int i = 0; i < channelList.size(); ++i) {
          IChannel channel = (IChannel)channelList.get(i);
          channel.init();
          this.addEntry(channel.getProperty("id"), channel);
        }
      }

    } else {
      throw new Exception("Error Channel Path[" + path + "]");
    }
  }

  public void addEntry(String id, IChannel entry) throws Exception {
    if(this.channelPools.containsKey(id)) {
      if(logger.isInfoEnabled()) {
        logger.warn("Channel[" + id + "] is existed, would be replaced");
      }

      this.channelPools.put(id, entry);
    } else {
      this.channelPools.put(id, entry);
    }

  }

  public void destroy() throws Exception {
    this.channelPools.clear();
    this.channelPools = null;
    cf = null;
  }
}
