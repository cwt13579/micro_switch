package com.bell.microswitch.test;


/**
 * @author chenwentao@trustmo.com
 *
 * 2015-1-7
 */
public class Transform {

  public static Long Ipv4ToInt(String ip) {

    String[] segements = ip.split(CommonConstant.TRANSFERRED_DOT);
    long   a = Long.parseLong(segements[0]);
    long   b = Long.parseLong(segements[1]);
    long   c = Long.parseLong(segements[2]);
    long   d = Long.parseLong(segements[3]);
    Long   x = (a<<24) + (b<<16) +(c << 8) + d;
    return x;
  }

  public static String IntToIpv4(String number) {
    String ipStr = "";
    if(number == null) {
      return null;
    }
    Long  x = Long.valueOf(number);
    long  a = (x&0xff000000)>>24;
    long  b = (x&0x00ff0000)>>16;
    long  c = (x&0x0000ff00)>>8;
    long  d =  x&0x000000ff;

    ipStr=String.valueOf(a)+CommonConstant.DOT+String.valueOf(b)+CommonConstant.DOT+String.valueOf(c)+CommonConstant.DOT+String.valueOf(d);
   return ipStr;
 }
}
