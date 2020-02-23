import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {
    public static void main(String[] a) {
        // 待处理字符串
        String wpp = "jdbc:mysql://${wpp1}:${wpp2}/${wpp3}a";
        //\u0024\u007B\u0028\u002E\u002A\u003F\u0029}
        // 匹配方式
        Pattern p = Pattern.compile("\\{(.*?)}");
        // 匹配】
        Matcher matcher = p.matcher(wpp);
        System.out.println(wpp.replaceAll("\\{(.*?)}", ""));
        // 处理匹配到的值
        while (matcher.find()) {
            System.out.println("woo: " + matcher.group());

        }
    }
}
