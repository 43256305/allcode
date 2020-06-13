package leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: offer
 * @description:
 * @author: xjh
 * @create: 2020-06-10 10:24
 **/
public class Convert {
    private static Convert convert = new Convert();

    public static void main(String[] args) {
        convert.convert1("leetcodeishiring", 4);
    }

    /**
     * @Description:  时间复杂度 n
     * 有一个大小为numRows的list，每个list存放一个stringbuilder，然后直接遍历字符串，第一行的加入第一个stringbuilder，第二行加入第二个
     * 直到最后一行，然后转向。最后把numRows个stringbuilde加起来就行了
     * 变量:curRow(0,1..numRows-1,numRows-2...1,0)表示加入哪一个Stringbuilder  goingDown(true,false)表示curRow该+还是-
     * @Param:
     * @return:
     * @Author: xjh
     * @Date: 2020/6/10
     */
    public String convert(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());  //构造大小为numRows的list，每个list存储一个Stirngbuilder

        int curRow = 0;   //当前字符行数
        boolean goingDown = false;  //true为向下，false为向上，向下curRow++，向上curRow--

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;  //curRow加一或者减一
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);  //拼接numRows个stringbuilder
        return ret.toString();
    }



    /**
    * @Description:根据公式  时间复杂度：n
     * 公式：首行和尾行（j+2*numRow-2）   中间行（j+2*numRows-2-i）相比前一个公式，这个公式减了一个i
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/10
    */
    public String convert1(String s, int numRows) {

        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;  //同一行两个字符间隔 如第0行：0，0+cyclelen，0+cyclelen+cyclelen  第1行：1，1+

        for (int i = 0; i < numRows; i++) {   //i代表行（先把第0行的全部加入，在加入第1行的直到numRows-1）
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)  //当不是第0行也不是最后一行并且没有越界时
                    ret.append(s.charAt(j + cycleLen - i));  //把j+cyclelen-i也加入
            }
        }
        return ret.toString();
    }
}
