package com.sandbox.ldap;

import com.sun.jndi.ldap.LdapCtx;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Hashtable;
import javax.naming.*;
import javax.naming.directory.*;

public class LdapJNDI {

//    @Autowired
//    LdapConfiguration ldapConfiguration;

    public void JNDILookup() {
        String rootFilter = "o=cvte.com,o=isp";
//        String filter = "(&(smart-type=E1)(smart-status=1))";
        String filter = "(&(smart-type=E1)(uid=00012047))";
        String username = "uid=USER_NAME,ou=Authorization,ou=People,o=cc.com,o=isp";//xxx为申请的对接账户
        String password = "PASSW";

        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");//设置连接LDAP的实现工厂
        env.put(Context.PROVIDER_URL, "ldap://192.168.1.2:389");// 指定LDAP服务器的主机名和端口号
        env.put(Context.SECURITY_AUTHENTICATION, "simple");//给环境提供认证方法,有SIMPLE、SSL/TLS和SASL
        env.put(Context.SECURITY_PRINCIPAL, "taimei@taimei.com");//指定进入的目录识别名DN
        env.put(Context.SECURITY_CREDENTIALS, "Jiaxing@123"); //进入的目录密码
//        env.put("filter",filter);
        DirContext ctx = null;

        try {
            // 得到初始目录环境的一个引用
            ctx = new InitialDirContext(env);

            //The search base entry 'uid=00012047,ou=Internal,ou=People,o=cvte.com,o=isp' does not exist]; remaining name 'uid=00012047,ou=Internal'
//            Attributes attrs = ctx.getAttributes("uid=00012047,ou=Internal,ou=People");//获取到一个人员，

            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
            NamingEnumeration<SearchResult> namingEnumeration = ctx.search("","cn=钱杰,ou=技术中心,ou=Taimei,dc=taimei,dc=com",constraints);
            System.out.println(namingEnumeration);
//            Attributes attrs = ctx.getAttributes("CN=Taimei,CN=Users,DC=taimei,DC=com");
//            NamingEnumeration en = ctx.search("CN=Taimei","CN=Users,DC=taimei,DC=com",constraints);
//            System.out.println(en);

//            NamingEnumeration bindings = ctx.listBindings("OU=技术中心,OU=Taimei,DC=taimei,DC=com");//列举 内部人员
//            NamingEnumeration bindings = ctx.listBindings("CN=Users,DC=taimei,DC=com");//列举 内部人员
//
//            while (bindings.hasMore()) {
//                Binding bd = (Binding)bindings.next();
//                LdapCtx object = (LdapCtx) bd.getObject();
//                System.out.println(bd.getName() + ": " + object);
//            }


            /*根据结点的DN来查找它的所有属性, 然后再从属性中得到所有的值,注意一个属性可以有多个值*/
//            for (NamingEnumeration ae = attrs.getAll(); ae.hasMore(); ) {
//                //获取一个属性
//                Attribute attr = (Attribute) ae.next();
//                for (NamingEnumeration ve = attr.getAll(); ve.hasMore(); ) {
//                    System.out.println(String.format("Attribute=%s,Value=%s",attr.getID(),ve.next()) );
//                }
//            }

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

    public static void main(String[] args) {
        LdapJNDI ldapJNDI = new LdapJNDI();
        ldapJNDI.JNDILookup();

    }
}
