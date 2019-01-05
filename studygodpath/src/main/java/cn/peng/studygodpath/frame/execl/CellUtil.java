package cn.peng.studygodpath.frame.execl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class CellUtil {

    private static DecimalFormat number = new DecimalFormat("0");

    private static DecimalFormat numberFormat = new DecimalFormat("0.0");// 格式化 number为整

    private static DecimalFormat numberFormatPer = new DecimalFormat("##.00%");// 格式化分比格式，后面不足2位的用0补齐

    // private static final DecimalFormat df_per_ = new
    // DecimalFormat("0.00%");//格式化分比格式，后面不足2位的用0补齐,比如0.00,%0.01%

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串

    private static DecimalFormat numberScience = new DecimalFormat("0.00E000"); // 格式化科学计数器

    private static Pattern points_ptrn = Pattern.compile("0.0+_*[^/s]+");


    public static String getCellValue(FormulaEvaluator evaluator, Cell cell) {
        String value = "";
        if (null != cell) {
            switch (cell.getCellTypeEnum()) {
                case _NONE:
                    break;
                case STRING:
                    value = cell.getStringCellValue();
                    break;
                case BOOLEAN:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
                case FORMULA:
                    if (null != evaluator) {
                        CellValue cellValue = evaluator.evaluate(cell);
                        value = String.valueOf(cellValue.getNumberValue());
                    }
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) { // 日期
                        value = dateFormat.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
                    } else if ("@".equals(cell.getCellStyle().getDataFormatString())
                            || "General".equals(cell.getCellStyle().getDataFormatString())
                            || "0_ ".equals(cell.getCellStyle().getDataFormatString())) {
                        // 文本 or 常规 or 整型数值
                        value = String.valueOf(number.format(cell.getNumericCellValue()));
                    } else if (points_ptrn.matcher(cell.getCellStyle().getDataFormatString()).matches()) { // 正则匹配小数类型
                        value = String.valueOf(cell.getNumericCellValue()); // 直接显示
                    } else if ("0.00E+00".equals(cell.getCellStyle().getDataFormatString())) {// 科学计数
                        value = numberScience.format(cell.getNumericCellValue());
                    } else if ("0.00%".equals(cell.getCellStyle().getDataFormatString())) {// 百分比
                        value = numberFormatPer.format(cell.getNumericCellValue());
                    } else if ("# ?/?".equals(cell.getCellStyle().getDataFormatString())) {// 分数
                        value = String.valueOf(cell.getNumericCellValue()); // 待完善
                    } else { // 货币
                        value = DecimalFormat.getCurrencyInstance().format(cell.getNumericCellValue());
                    }
                    break;
                default:
                    value = cell.toString();
            }
        }
        return value;
    }

}
