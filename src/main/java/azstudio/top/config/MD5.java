package azstudio.top.config;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
/**
 * Author:
 * Data:2019-04-28 11:24
 * Description:<>
 */
public final class MD5 {
    private final static String key = "woaimaozedong";
    /**
     * MD5方法
     *
     * @param text 明文
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text) throws Exception {
        //加密后的字符串
        String encodeStr=DigestUtils.md5Hex(text + key);
        return encodeStr;
    }

    /**
     * MD5验证方法
     *
     * @param text 明文
     * @param md5 密文
     * @return true/false
     * @throws Exception
     */
    public static boolean verify(String text, String md5) throws Exception {
        //根据传入的密钥进行验证
        String md5Text = md5(text);
        if(md5Text.equalsIgnoreCase(md5))
        {
            return true;
        }

        return false;
    }
}