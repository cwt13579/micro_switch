
package com.nio.reactor.client; 

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

/** 

 * @author 作者姓名  E-mail: email地址 

 * @version 创建时间：2017年5月24日 下午4:39:49 

 * 类说明 

 */

public class TIOStreamTransport implements Closeable{
	  private static final Logger LOGGER = Logger.getLogger(TIOStreamTransport.class.getName());
	  protected InputStream inputStream_ = null;
	  protected OutputStream outputStream_ = null;
	  protected TIOStreamTransport() {}
	  public TIOStreamTransport(InputStream is) {
		    inputStream_ = is;
	  }
	  public TIOStreamTransport(OutputStream os) {
		    outputStream_ = os;
	  }
	  public TIOStreamTransport(InputStream is, OutputStream os) {
		    inputStream_ = is;
		    outputStream_ = os;
	  }
	  public boolean isOpen() {
		    return true;
	  }
	  public void open() throws Exception {
		  
	  }
	  public void close() {
		    if (inputStream_ != null) {
		      try {
		        inputStream_.close();
		      } catch (IOException iox) {
		        LOGGER.warn("Error closing input stream.", iox);
		      }
		      inputStream_ = null;
		    }
		    if (outputStream_ != null) {
		      try {
		        outputStream_.close();
		      } catch (IOException iox) {
		        LOGGER.warn("Error closing output stream.", iox);
		      }
		      outputStream_ = null;
		    }
	  }
	  
	  public int read(byte[] buf, int off, int len) throws Exception {
		    if (inputStream_ == null) {
		      throw new Exception( "Cannot read from null inputStream");
		    }
		    int bytesRead;
		    try {
		      bytesRead = inputStream_.read(buf, off, len);
		    } catch (IOException iox) {
		      throw new Exception(  iox);
		    }
		    if (bytesRead < 0) {
		      throw new Exception("END_OF_FILE");
		    }
		    return bytesRead;
	  }
	  
	  public void write(byte[] buf, int off, int len) throws Exception {
		    if (outputStream_ == null) {
		      throw new Exception("Cannot write to null outputStream");
		    }
		    try {
		      outputStream_.write(buf, off, len);
		    } catch (IOException iox) {
		      throw new Exception(iox);
		    }
	  }
	  
	  public void flush() throws Exception {
		    if (outputStream_ == null) {
		      throw new Exception( "Cannot flush null outputStream");
		    }
		    try {
		      outputStream_.flush();
		    } catch (IOException iox) {
		      throw new Exception(iox);
		    }
		  }
}
 