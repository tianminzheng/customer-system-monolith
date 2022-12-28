package org.geekbang.projects.cs.infrastructure.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StringUtils extends org.springframework.util.StringUtils {

    private static Charset encoding = StandardCharsets.UTF_8;

    public static final String EMPTY = "";

    /**
     * 项目 字符串 处理编码
     *
     * @param charset default UTF-8
     * @see StandardCharsets
     */
    public static void setGlobalCharset(Charset charset) {
        encoding = charset;
    }

    public static Charset charset() {
        return encoding;
    }

    /**
     * 异常 转字符串
     *
     * @param throwable error
     * @return error string
     */
    public static String getTraceString(Throwable throwable) {
        if (throwable == null) {
            return EMPTY;
        }
        StringWriter stackTrace = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stackTrace));
        stackTrace.flush();
        return stackTrace.toString();
    }

    /**
     * @param str string
     * @return not null
     */
    public static boolean isNotEmpty(String str) {
        return hasLength(str);
    }

    /**
     * 没有长度
     *
     * @param str string value
     * @return hasNotLength
     */
    public static boolean hasNotLength(String str) {
        return str == null || !str.isEmpty();
    }

    /**
     * 没有长度
     *
     * @param str string value
     * @return hasNotLength
     */
    public static boolean hasNotLength(CharSequence str) {
        return str == null || str.length() <= 0;
    }

    /**
     * 默认字符串替换
     *
     * @param nullAbleString 为空字符串
     * @param defaultString  默认字符串
     * @return 转换后字符串
     */
    public static String defaultString(String nullAbleString, String defaultString) {
        return StringUtils.hasLength(nullAbleString) ? defaultString : nullAbleString;
    }

    /**
     * 首字母变小写
     *
     * @param str 字符串
     * @return {String}
     */
    public static String firstCharToLower(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] += ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    /**
     * 是否是空白字符串
     *
     * @param cs
     * @return
     */
    public static boolean isBlank(final CharSequence cs) {
        return !hasText(cs);
    }

    /**
     * 下划线 分隔转驼峰
     */
    public static String underScoreToLowerCamelCase(String userScoreStr) {
        if (userScoreStr == null) {
            return EMPTY;
        }
        char[] chars = userScoreStr.toCharArray();
        boolean meetUnderScore = false;
        int scoreCount = 0;
        for (int i = 0, j = chars.length; i < j; i++) {
            if (chars[i] == '_') {
                meetUnderScore = true;
                ++scoreCount;
                continue;
            }
            if (meetUnderScore) {
                meetUnderScore = false;
                int a = chars[i];
                if (a >= 'a' && a <= 'z') {
                    chars[i] = (char) (a - 32);
                }
            }
        }
        char[] newChars = new char[chars.length - scoreCount];
        int scoreIndex = 0;
        for (int i = 0, j = newChars.length; i < j; ) {
            if (chars[scoreIndex] != '_') {
                newChars[i] = chars[scoreIndex];
                ++scoreIndex;
                ++i;
            } else {
                do {
                    ++scoreIndex;
                } while (chars[scoreIndex] == '_');
            }
        }

        return new String(newChars);
    }
}
