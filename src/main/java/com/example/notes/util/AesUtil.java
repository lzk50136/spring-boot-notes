package com.example.notes.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.util.Base64;

/**
 * AES加密解密工具类
 *
 * @author Lzk
 */
public class AesUtil {
    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";
    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS7Padding";
    /**
     * 生成secretKey
     */
    private static SecretKeySpec secretKey = new SecretKeySpec(Md5Util.md5("your key").toLowerCase().getBytes(), ALGORITHM);

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private AesUtil() {

    }

    /**
     * AES加密
     *
     * @param data 待加密数据
     * @return 加密后数据
     * @throws Exception Exception
     */
    public static String encryptData(String data) throws Exception {
        // 创建密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);
        // 初始化
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
    }

    /**
     * AES解密
     *
     * @param data 待解密数据
     * @return 待密后数据
     * @throws Exception Exception
     */
    public static String decryptData(String data) throws Exception {
        // 创建密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);
        // 初始化
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
    }

    /*public static void main(String[] args) throws Exception {
        //String A 为测试字符串，是微信返回过来的退款通知字符串
        String A = "CEK317dNPCK8frPC34EzmTwfxPnSFbQyPP0/MjlgeQJTu96jZNvJDgq8lc/NyYReYqZCWSqrrG3gKEijeUhhzJBAsR8/zc/MUQlDXDjrxWrkT6YaqKJK6QCNfSfsP61b6Z+JwyxWk2DB9Gw7wfHW8b0r45kPBt9fafcljXRX4LME7VcXURak/tHFBcHzSOs0SWwR8SM+CJ4ZYODddK71mlnSb/usMOGbakkknmAO0qu2bTOOTtI8ftlffpbxU+wwrcs1iQmgq+HWI0TYqejqH4smuHhi4r+i7a1JCV/z5Scy3wqmPI4D7FUd8R47lpYZpEafCQAR4ZE4iGMdwQcItGgIoZke5mgXln7uhsppFcydULYVz6sCSV8Py94axIH/oxABqcC2N8iRgKlPXxKsj2rWK3YyP0va6XWOftqBaCffi5NSfA20nYL0UxHwx1y1Wpn5zILM3h03I3/zhc+b1/FAaZkhLzT9ljNxMDWMPGM7m2aXweuk47gU3tX2yPPoGDu86gGnVBj35/8TDSafK3iIi8DmeysKEFePmZa1vd/a4+uvVAP5h0Kd67jJBkhbiV7hujCGCXn3p+c9HILwVXC118ozk2okXMTXGBh8CGuO27TMPEiMnC9QXpuxaPntpxdbHIGzvy7G5UNb83aOfcJdsva6YGIbJjXkO1MwLtITMqHouL+egG9GRCEud9BNhMmqK4Ec3quVfKRsks8cuS5F3l0y/7+qXZszmK8zB0Yt5z89EssgyAzaTSP7SYiT+SH9n2U224pjUQiTzWFbizH5ZdROSm/v3Cw5knpHjnp3ZCeF/uzl6oI6VcfFDEt1qABArTgBWHXAwOrIx9JD78hL9yj8NOzfE4dkViBN+ReOF4n3CzNLKdlvA9hP0plJpp0Mh7u4H7Z1vSQtQnmoqr/8NPbJwqJphATzykVenwrVq5FoVDFLCPbYC/uEKyx4qAbmlWyeJ9JFrpUjGThQTLZm1DVxnI+TJeWN9xK1L1RmSvcGpm7SL9v/5X6R6I7XULAaJLoFAuywWskQr/6FUwiCEMmD69bpT0167loKxF1YzVFyXAxPudsWyODtrM1VEz7C6PeAaMNKdvpAUfelLw==";
        //String A = "oSWxqqMF5lk2EF+gdrdt5wPrOru854za5XjHq5cUXs74zF9+jlxGOo7DHQIuntolVF3kQAruoMoNK5lLRsCulgG2hAT+6sNen8f/f3drMxfsTFOj3aBTKkIHs2p3AVJA4fXpGRCpejq3JJplSQnnSwFljzcxvqe7rU3y/H0KpFyBuYUSEf+msbkHEnHnIHQi4p9JDlLPWoKHramM7R65Qd13GdUU41scNybWCkwl+q/cY2Nv6KUt490JXTbTEgZNE6ArJKGg9woRMUdJEimTnv2OSY16yjo8dlIiozEoHcoQsvSFuMA5DHfHmtk5gbn8y6FVLHbt8XmmOIkfl/CVCXGQ+fGJmazxmqpTLBnAxXogFX2c2h8ZFqrWHW0wWZNSqpRX8HnMBw4V5hUMCiN9ASP3AzkpbtxdkDaeJYagVFgpB7oXxNUlQMy7pCqWCqbhoeLlZtzACx3qNqf57cQLn06T8wrYddf3f78oIYceVWMBses6wcJW2uTUdci4hYOQn5G+iVGLRzMuI8xwQSeBtdrWBor842tEsg4/wgFRxiEgjN+Jl+pCbwULjzt870OwC/UKD9mM3bhyay1jxeKNfkqgks0TH9eZXT1mR6IBfIUipgk9nTrGLFQwt4AAAf7/KoW7A3d1eYGY1vo/QkinixiZsxOJhzw95X6wiiARPa8oe0180lCuhLtIrNRlxyVMbbwA8GQVuCCE6w+/yKIF+el+Gcf7Gm2ljQzV7PEwiomW/DsBqUb5mwGfI52NLRa70kJ8vgaXeMN1xhwWYLzg02muvGGwS2P4kgGO0Sg0L5ycpN7Vp421+HnAPdcW6y/pQi03BKAR6fZT5JQYAIoNN4K8K6ZbgfZiuG32q0q4bwVWrg4jBlyPmj8JwHtbikbAgoJ9sUwWYi7P+Btk1ZHCPLW90p+1mIL8eVpneOaon3mSW0R4JDiIJK8oYLD/1n4NTKRTg9c6OMdSHnK8BUnodw==";
        String B = AesUtil.decryptData(A);
        System.out.println(B);
    }*/

}