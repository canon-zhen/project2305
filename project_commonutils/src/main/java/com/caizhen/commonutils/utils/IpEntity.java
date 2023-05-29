package com.caizhen.commonutils.utils;

/**
 * ip封装实体类
 *
 * @author liying
 * @since 2021/11/23 10:02
 */
public class IpEntity {
  /** ip:请求ip */
  private String ip;
  /** port:请求端口 */
  private String port;
  /** browser：浏览器版本 */
  private String browser;
  /** eq：请求设备 */
  private String eq;
  /** agent：请求头信息 User-Agent */
  private String agent;

  public IpEntity() {}

  public IpEntity(String ip, String port, String browser, String eq, String agent) {
    this.ip = ip;
    this.port = port;
    this.browser = browser;
    this.eq = eq;
    this.agent = agent;
  }

  public String getIp() {
    return ip;
  }

  public IpEntity setIp(String ip) {
    this.ip = ip;
    return this;
  }

  public String getPort() {
    return port;
  }

  public IpEntity setPort(String port) {
    this.port = port;
    return this;
  }

  public String getBrowser() {
    return browser;
  }

  public IpEntity setBrowser(String browser) {
    this.browser = browser;
    return this;
  }

  public String getEq() {
    return eq;
  }

  public IpEntity setEq(String eq) {
    this.eq = eq;
    return this;
  }

  public String getAgent() {
    return agent;
  }

  public IpEntity setAgent(String agent) {
    this.agent = agent;
    return this;
  }

  @Override
  public String toString() {
    return "IpEntity{" +
            "ip='" + ip + '\'' +
            ", port='" + port + '\'' +
            ", browser='" + browser + '\'' +
            ", eq='" + eq + '\'' +
            ", agent='" + agent + '\'' +
            '}';
  }
}
