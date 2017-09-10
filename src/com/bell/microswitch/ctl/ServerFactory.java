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
import com.bell.microswitch.ctl.parse.ServerParse;
import com.bell.microswitch.ctl.utils.PathUtil;
import com.bell.microswitch.model.server.base.IServer;

public class ServerFactory implements IEntryFactory<IServer> {
  private static final Logger logger = Logger.getLogger(ServerFactory.class);
  private Map<String, IServer> serverPools;
  private static ServerFactory sf = null;

  public ServerFactory() {
  }

  public static IEntryFactory<IServer> getInstance() throws Exception {
    if(sf == null) {
      sf = new ServerFactory();
      sf.create();
    }

    return sf;
  }

  public IServer getEntry(String id) throws Exception {
    if(this.serverPools.containsKey(id)) {
      return (IServer)this.serverPools.get(id);
    } else {
      throw new Exception( "Server Factory not found id[" + id + "]");
    }
  }

  public void create() throws Exception {
    this.serverPools = new HashMap();
  }

  public void parse() throws Exception {
    String path = PathUtil.getServerPath();
    File folderFile = new File(path);
    if(folderFile.isDirectory() && folderFile.exists()) {
      Collection files = FileUtils.listFiles(folderFile, new SuffixFileFilter("xml", IOCase.INSENSITIVE), (IOFileFilter)null);
      Iterator it = files.iterator();

      while(it.hasNext()) {
        File serverFile = (File)it.next();
        List serverList = ServerParse.parse(serverFile);

        for(int i = 0; i < serverList.size(); ++i) {
          IServer server = (IServer)serverList.get(i);
          this.addEntry(server.getProperty("id"), server);
        }
      }

    } else {
      throw new Exception( "Error Server Path[" + path + "]");
    }
  }

  public void addEntry(String id, IServer entry) throws Exception {
    if(this.serverPools.containsKey(id)) {
      if(logger.isInfoEnabled()) {
        logger.warn("Server[" + id + "] is existed, would be replaced");
      }

      this.serverPools.put(id, entry);
    } else {
      this.serverPools.put(id, entry);
    }

  }

  public void destroy() throws Exception {
    this.serverPools.clear();
    this.serverPools = null;
    sf = null;
  }
}
