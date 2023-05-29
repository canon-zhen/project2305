package com.caizhen.commonutils.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * ip工具类
 *
 * @author liying
 * @since 2021/11/23 9:48
 */
public class IpUtil {

  /**
   * Wap网关Via头信息中特有的描述信息
   *
   * @author By liying
   */
  private static final String[] MOBILEGATEWAYHEADERS =
      new String[] {
        // 中兴提供的wap网关的via信息，例如：Via=ZXWAP GateWayZTE
        "ZXWAP",
        // Technologies，
        // 中国移动的诺基亚wap网关，例如：Via=WTP/1.1 GDSZ-PB-GW003-WAP07.gd.chinamobile.com (NokiaWAP Gateway 4.1
        // CD1/ECD13_D/4.1.04)
        "chinamobile.com",
        // 移动梦网的网关，例如：Via=WTP/1.1 BJBJ-PS-WAP1-GW08.bj1.monternet.com. (Nokia WAP Gateway 4.1
        // CD1/ECD13_E/4.1.05)
        "monternet.com",
        // 华为提供的wap网关，例如：Via=HTTP/1.1 GDGZ-PS-GW011-WAP2 (infoX-WISG Huawei Technologies)，或Via=infoX
        // WAP Gateway V300R001 Huawei Technologies
        "infoX",
        // 国外电信运营商的wap网关，不知道是哪一家
        "XMS 724Solutions HTG",
        // 貌似是一个给移动互联网提供解决方案提高网络运行效率的，例如：Via=1.1 Bytemobile OSN WebProxy/5.1
        "Bytemobile",
      };
  /** 电脑上的IE或Firefox浏览器等的User-Agent关键词 */
  private static final String[] PCHEADERS =
      new String[] {
        "Windows 98", "Windows ME", "Windows 2000", "Windows XP", "Windows NT", "Ubuntu"
      };
  /** 手机浏览器的User-Agent里的关键词 */
  private static final String[] MOBILEUSERAGENTS =
      new String[] {
        // 诺基亚，有山寨机也写这个的，总还算是手机，Mozilla/5.0 (Nokia5800
        // XpressMusic)UC AppleWebkit(like Gecko)
        // Safari/530
        "Nokia",
        // SAMSUNG-GT-B7722/1.0+SHP/VPP/R5+Dolfin/1.5+Nextreaming+SMM-MMS/1.2.0+profile/MIDP-2.1+configuration/CLDC-1.1 三星手机
        "SAMSUNG",
        // j2me2.0，Mozilla/5.0 (SymbianOS/9.3; U; Series60/3.2 NokiaE75-1 /110.48.125
        // Profile/MIDP-2.1 Configuration/CLDC-1.1 ) AppleWebKit/413 (KHTML like Gecko)
        // Safari/413
        "MIDP-2",
        // M600/MIDP2.0/CLDC1.1/Screen-240X320
        "CLDC1.1",
        // 塞班系统的，
        "SymbianOS",
        // MTK山寨机默认ua
        "MAUI",
        // 疑似山寨机的ua，基本可以确定还是手机
        "UNTRUSTED/1.0",
        // Windows CE，Mozilla/4.0 (compatible; MSIE 6.0; Windows CE; IEMobile 7.11)
        "Windows CE",
        // iPhone是否也转wap？不管它，先区分出来再说。Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_1 like MacOS X; zh-cn)
        // AppleWebKit/532.9 (KHTML like Gecko) Mobile/8B117
        "iPhone",
        // iPad的ua，Mozilla/5.0 (iPad; U; CPU OS 3_2 like Mac OS X; zh-cn)
        // AppleWebKit/531.21.10 (KHTML like Gecko) Version/4.0.4 Mobile/7B367
        // Safari/531.21.10
        "iPad",
        // Android是否也转wap？Mozilla/5.0 (Linux; U; Android 2.1-update1; zh-cn; XT800
        // Build/TITA_M2_16.22.7) AppleWebKit/530.17 (KHTML like Gecko) Version/4.0
        // Mobile Safari/530.17
        "Android",
        // BlackBerry8310/2.7.0.106-4.5.0.182
        "BlackBerry",
        // ucweb是否只给wap页面？ Nokia5800 XpressMusic/UCWEB7.5.0.66/50/999
        "UCWEB",
        // 小写的ucweb貌似是uc的代理服务器Mozilla/6.0 (compatible; MSIE 6.0;) Opera ucweb-squid
        "ucweb",
        // 很奇怪的ua，例如：REW-Applet/0x20068888 (BREW/3.1.5.20; DeviceId: 40105; Lang: zhcn) ucweb-squid
        "BREW",
        // 很奇怪的ua，只有J2ME四个字母
        "J2ME",
        // 宇龙手机，YULONG-CoolpadN68/10.14 IPANEL/2.0 CTC/1.0
        "YULONG",
        // 还是宇龙
        "YuLong",
        // 宇龙酷派YL-COOLPADS100/08.10.S100 POLARIS/2.9 CTC/1.0
        "COOLPAD",
        // 天语手机TIANYU-KTOUCH/V209/MIDP2.0/CLDC1.1/Screen-240X320
        "TIANYU",
        // 天语，TY-F6229/701116_6215_V0230 JUPITOR/2.2 CTC/1.0
        "TY-",
        // 还是天语K-Touch_N2200_CMCC/TBG110022_1223_V0801 MTK/6223 Release/30.07.2008 Browser/WAP2.0
        "K-Touch",
        // 海尔手机，Haier-HG-M217_CMCC/3.0 Release/12.1.2007 Browser/WAP2.0
        "Haier",
        // 多普达手机
        "DOPOD",
        // 联想手机，Lenovo-P650WG/S100 LMP/LML Release/2010.02.22 Profile/MIDP2.0 Configuration/CLDC1.1
        "Lenovo",
        // 联想手机，比如：LENOVO-P780/176A
        "LENOVO",
        // 华勤手机
        "HUAQIN",
        // 爱国者居然也出过手机，AIGO-800C/2.04 TMSS-BROWSER/1.0.0 CTC/1.0
        "AIGO-",
        // 含义不明
        "CTC/1.0",
        // 含义不明
        "CTC/2.0",
        // 移动定制手机，K-Touch_N2200_CMCC/TBG110022_1223_V0801 MTK/6223 Release/30.07.2008
        // Browser/WAP2.0
        "CMCC",
        // 大显手机DAXIAN X180 UP.Browser/6.2.3.2(GUI) MMP/2.0
        "DAXIAN",
        // 摩托罗拉，MOT-MOTOROKRE6/1.0 LinuxOS/2.4.20 Release/8.4.2006 Browser/Opera8.00 Profile/MIDP2.0
        // Configuration/CLDC1.1 Software/R533_G_11.10.54R
        "MOT-",
        // 索爱手机，SonyEricssonP990i/R100 Mozilla/4.0 (compatible; MSIE 6.0; Symbian OS;405) Opera 8.65
        // [zh-CN]
        "SonyEricsson",
        // 金立手机
        "GIONEE",
        // HTC手机
        "HTC",
        // 中兴手机，ZTE-A211/P109A2V1.0.0/WAP2.0 Profile
        "ZTE",
        // 华为手机，
        "HUAWEI",
        // palm手机，Mozilla/5.0 (webOS/1.4.5; U; zh-CN) AppleWebKit/532.2 (KHTML like Gecko)
        // Version/1.0 Safari/532.2 Pre/1.0
        "webOS",
        // 3g GoBrowser.User-Agent=Nokia5230/GoBrowser/2.0.290 Safari
        "GoBrowser",
        // Windows CE手机自带浏览器，
        "IEMobile",
        // 支持wap 2.0的
        "WAP2.0"
      };

  public static String getIp(HttpServletRequest request) {
    // 获取ip地址
    String ipAddress = request.getHeader("x-forwarded-for");
    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getHeader("Proxy-Client-IP");
    }
    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getRemoteAddr();
      if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
        // 根据网卡取本机配置的IP
        InetAddress inet = null;
        try {
          inet = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
          e.printStackTrace();
        }
        assert inet != null;
        ipAddress = inet.getHostAddress();
      }
    }
    // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
    // "***.***.***.***".length() = 15
    if (ipAddress != null && ipAddress.length() > 15) {
      if (ipAddress.indexOf(",") > 0) {
        ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
      }
    }

    return ipAddress;
  }

  public static IpEntity ipAnalyze(HttpServletRequest request) {

    IpEntity ipEntity = new IpEntity();
    // 登录IP
    ipEntity.setIp(getIp(request));
    boolean b = false;
    boolean pcFlag = false;
    boolean mobileFlag = false;
    String via = request.getHeader("Via");
    String userAgent = request.getHeader("user-agent");
    for (int i = 0; via != null && !"".equals(via.trim()) && i < MOBILEGATEWAYHEADERS.length; i++) {
      if (via.contains(MOBILEGATEWAYHEADERS[i])) {
        mobileFlag = true;
        break;
      }
    }
    for (int i = 0;
        !mobileFlag
            && userAgent != null
            && !"".equals(userAgent.trim())
            && i < MOBILEUSERAGENTS.length;
        i++) {
      if (userAgent.contains(MOBILEUSERAGENTS[i])) {
        mobileFlag = true;
        break;
      }
    }
    for (int i = 0;
        userAgent != null && !"".equals(userAgent.trim()) && i < PCHEADERS.length;
        i++) {
      if (userAgent.contains(PCHEADERS[i])) {
        pcFlag = true;
        break;
      }
    }
    if (mobileFlag && !pcFlag) {
      b = true;
    }
    // 判断是什么浏览
    if (b) {
      // 请求设备
      ipEntity.setEq("APP登录");
    } else {
      ipEntity.setEq("PC登录");
    }
    String agent = request.getHeader("User-Agent").toLowerCase();
    // 浏览器版本
    ipEntity.setBrowser(getBrowserName(agent));
    // agent
    ipEntity.setAgent(agent);
    // 当前时间
    ipEntity.setPort(request.getServerPort() + "");

    return ipEntity;
  }

  /**
   * 判断是什么浏览器
   *
   * @author By liying
   */
  private static String getBrowserName(String agent) {
    if (agent.indexOf("msie 7") > 0) {
      return "ie7";
    } else if (agent.indexOf("msie 8") > 0) {
      return "ie8";
    } else if (agent.indexOf("msie 9") > 0) {
      return "ie9";
    } else if (agent.indexOf("msie 10") > 0) {
      return "ie10";
    } else if (agent.indexOf("msie") > 0) {
      return "ie";
    } else if (agent.indexOf("opera") > 0) {
      return "opera";
    } else if (agent.indexOf("firefox") > 0) {
      return "firefox";
    } else if (agent.indexOf("webkit") > 0) {
      return "webkit";
    } else if (agent.indexOf("gecko") > 0 && agent.indexOf("rv:11") > 0) {
      return "ie11";
    } else {
      return "Others";
    }
  }
}
