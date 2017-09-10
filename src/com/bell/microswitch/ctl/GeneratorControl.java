package com.bell.microswitch.ctl;

import org.apache.log4j.Logger;

import com.bell.microswitch.ctl.utils.PathUtil;

public class GeneratorControl {
  private static final Logger logger = Logger.getLogger(GeneratorControl.class);

  public GeneratorControl() {
  }

  public static void init(String path) throws Exception {
    PathUtil.init(path);
    logger.info("***********Path Util init**************");
    //BufferFactory.getInstance().parse();
    logger.info("***********BufferFactory parse**************");
    ChannelFactory.getInstance().parse();
    logger.info("***********ChannelFactory parse**************");
    //ClientChannelFactory.getInstance().parse();
    logger.info("***********ClientChannelFactory parse**************");
    //ClientFactory.getInstance().parse();
    logger.info("***********ClientFactory parse**************");
    ServerFactory.getInstance().parse();
    logger.info("***********ServerFactory parse**************");
  }

  public static void start() throws Exception {
    logger.info("***********Server Channel Start**************");
    ((ChannelFactory)ChannelFactory.getInstance()).start();
  }

  public static void stop() throws Exception {
    ((ChannelFactory)ChannelFactory.getInstance()).stop();
  }

  public static void destory() throws Exception {
    ServerFactory.getInstance().destroy();
    //ClientFactory.getInstance().destroy();
    //ClientChannelFactory.getInstance().destroy();
    ChannelFactory.getInstance().destroy();
    //BufferFactory.getInstance().destroy();
    PathUtil.destroy();
  }
}
