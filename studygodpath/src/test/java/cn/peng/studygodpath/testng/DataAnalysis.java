package cn.peng.studygodpath.testng;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author: Administrator
 * @CreateTime:2021-11-15 15:44
 * QDescription:
 */
public class DataAnalysis {

    @Test
    public void testAnalysisLogic() {
        String data = "104*116*116*112*115*58*47*47*97*111*100*46*99*111*115*46*116*120*46*120*109*99*100*110*46*99*111*109*47*115*116*111*114*97*103*101*115*47*49*57*52*54*45*97*117*100*105*111*102*114*101*101*104*105*103*104*113*112*115*47*68*70*47*57*70*47*67*75*119*82*73*82*119*69*104*78*113*54*65*69*104*121*107*81*67*120*67*95*73*89*46*109*52*97";
        String[] result = data.split("\\*");
        StringBuilder sb = new StringBuilder();
        for (String item : result) {
            sb.append((char)Integer.valueOf(item).intValue());
        }
        System.out.println(sb.toString());

    }


}
