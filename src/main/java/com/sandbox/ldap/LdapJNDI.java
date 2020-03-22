package com.sandbox.ldap;

import com.sun.jndi.ldap.LdapCtx;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Hashtable;
import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class LdapJNDI {

//    @Autowired
//    LdapConfiguration ldapConfiguration;

    public void JNDILookup() {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");//设置连接LDAP的实现工厂
        env.put(Context.PROVIDER_URL, "ldap://192.168.1.2:389/OU=Taimei,DC=taimei,DC=com");// 指定LDAP服务器的主机名和端口号
        env.put(Context.SECURITY_AUTHENTICATION, "simple");//给环境提供认证方法,有SIMPLE、SSL/TLS和SASL
        //指定的LDAP账号的用户名(CN+BASEDN)和密码
//        env.put(Context.SECURITY_PRINCIPAL, "CN=钱杰,OU=技术中心,OU=Taimei,DC=taimei,DC=com");//指定进入的目录识别名DN
//        env.put(Context.SECURITY_CREDENTIALS, "Jq12345"); //进入的目录密码
        //通用用户名和密码
        env.put(Context.SECURITY_PRINCIPAL, "taimei@taimei.com");//指定进入的目录识别名DN
        env.put(Context.SECURITY_CREDENTIALS, "Jiaxing@123"); //进入的目录密码

        DirContext ctx = null;

        try {
            // 得到初始目录环境的一个引用
            ctx = new InitialDirContext(env);

            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
            NamingEnumeration<SearchResult> namingEnumeration = ctx.search("","sAMAccountName=jie.qian",constraints);
            System.out.println(namingEnumeration.next());

        } catch (javax.naming.AuthenticationException e) {
            System.out.println("认证失败");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("认证出错：");
            e.printStackTrace();
        }finally {
            if (ctx != null) {
                try {
                    ctx.close();
                } catch (NamingException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private final String URL = "ldap://192.168.1.2:389/";
    private final String BASEDN = "OU=Taimei,DC=taimei,DC=com";
    private final String FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
    private LdapContext ctx = null;
    private final Control[] connCtls = null;

    private void LDAP_connect() {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, FACTORY);
        env.put(Context.PROVIDER_URL, URL + BASEDN);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");

        String root = "taimei@taimei.com";// root
        env.put(Context.SECURITY_PRINCIPAL, root);
        env.put(Context.SECURITY_CREDENTIALS, "Jiaxing@123");
        // 此处若不指定用户名和密码,则自动转换为匿名登录
        try {
            ctx = new InitialLdapContext(env, connCtls);
        } catch (javax.naming.AuthenticationException e) {
            System.out.println("验证失败：" + e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private LdapUser getUserDN(String uid) {
        String userDN = "";
        String displayName = "";
        LDAP_connect();
        try {
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
            NamingEnumeration<SearchResult> en = ctx.search("", "sAMAccountName=" + uid, constraints);
            if (en == null || !en.hasMoreElements()) {
                System.out.println("未找到该用户");
            }

            // maybe more than one element
            while (en != null && en.hasMoreElements()) {
                Object obj = en.nextElement();
                if (obj instanceof SearchResult) {
                    SearchResult si = (SearchResult) obj;
                    if("".equals(displayName)){
                        Attributes attributes = si.getAttributes();
                        if(attributes!=null){
                            Attribute attribute = attributes.get("displayName");
                            if(attribute!=null){
                                displayName = (String) attribute.get(0);
                            }
                        }
                    }
                    userDN += si.getName();
                    userDN += "," + BASEDN;
                }
            }

        } catch (Exception e) {
            System.out.println("查找用户时产生异常。");
            e.printStackTrace();
        }

        return new LdapUser(userDN,displayName);
    }

    public boolean authenticate(String uid, String password) {
        boolean valide = false;
        LdapUser ldapUser = getUserDN(uid);
        String userDN = ldapUser.getUserDN();

        try {
            ctx.addToEnvironment(Context.SECURITY_PRINCIPAL, userDN);
            ctx.addToEnvironment(Context.SECURITY_CREDENTIALS, password);
            ctx.reconnect(connCtls);
            System.out.println(userDN + " 验证通过");
            System.out.println("姓名: " + ldapUser.getDisplayName());
            valide = true;
        } catch (AuthenticationException e) {
            System.out.println(userDN + " 验证失败");
            System.out.println(e.toString());
            valide = false;
        } catch (NamingException e) {
            System.out.println(userDN + " 验证失败");
            valide = false;
        }
        return valide;
    }

    private class LdapUser{
        private String userDN;
        private String displayName;
        public LdapUser(String userDN, String displayName){
            this.userDN = userDN;
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public String getUserDN() {
            return userDN;
        }
    }

    public static void main(String[] args) {
        LdapJNDI ldapJNDI = new LdapJNDI();
//        ldapJNDI.JNDILookup();
        String uid = "jie.qian";
        String password = "Jq@12345";
        System.out.println(ldapJNDI.authenticate(uid,password));
    }
}
