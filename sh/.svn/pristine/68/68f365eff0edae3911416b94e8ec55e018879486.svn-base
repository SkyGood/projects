package ccst.sh.common.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class DataInsert {
    public static void insertData(DataSource dataSource, String path) {
        String stuTabName = "student"; 
        String clasTabName = "adminclass";
        Connection conn = null;
        try {
            Integer colIndex = 0;// 记录className的位置
            Map<String, Integer> map = new HashMap<String, Integer>();// 存取班级,班级Id
            conn = dataSource.getConnection();
            InputStream is = new FileInputStream(path);
            // casilin:插入数据，先从excel中读取数据
            ExcelReader excelReader = new ExcelReader();
            String[] colName = excelReader.readExcelTitle(is);

            // 开始建立插入的sql语句,每一次插入的开头都是不变的,都是字段名
            StringBuffer sqlBegin = new StringBuffer("insert into "
                    + stuTabName + "(");
            // 获取字段名，并添加入sql语句中
            for (int i = 0; i < colName.length; i++) {
                if ("className".equalsIgnoreCase(colName[i])) {
                    colIndex = i;
                    sqlBegin.append("classId");
                } else {
                    sqlBegin.append(colName[i]);
                }
                if (i != colName.length - 1) {
                    sqlBegin.append(",");
                }
            }
            sqlBegin.append(") values(");

            is.close();

            // 导入列时,找到colName为className的列号，并将classNum进行替代
            // 导入学生时,首先对列进行遍历，找出所有的班级，用HashMap进行存储，并以<String className, Integer
            // classId>的形式进行存储
            // 按照classId,className插入表adminclass表中
            // 最后，在进行Excel表遍历数据时,直接从hashMap总通过map.get(row.getCell((short)j).trim)获取到相应的班号，并插入表student中
            // 然后，再对列遍历的时候
            // 下面读取字段内容
            POIFSFileSystem fs;
            HSSFWorkbook wb;
            HSSFSheet sheet;
            HSSFRow row;
            is = new FileInputStream(path);

            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
            sheet = wb.getSheetAt(0);

            // 得到总行数
            int rowNum = sheet.getLastRowNum();
            row = sheet.getRow(0);
            int colNum = row.getPhysicalNumberOfCells();
            // 正文内容应该从第二行开始,第一行为表头的标题
            String sql = new String(sqlBegin);
            String temp;
            Integer classId;// 班号
            Integer mapId = 1;
            for (int i = 1; i <= rowNum; i++) {
                row = sheet.getRow(i);
                int j = 0;
                while (j < colNum) {
                    if (colIndex == j) {
                        String className = excelReader.getStringCellValue(
                                row.getCell((short) j)).trim();
                        if (map.containsKey(className)) {
                            // 如果HashMap中含有，就获取HashMap的值即ID
                            classId = map.get(className);
                        } else {
                            // 如果HashMap中没有，就加入hashmap,并操作表student,并记录classId
                            classId = mapId;
                            map.put(className, mapId);
                            opStudentTable(dataSource, clasTabName, className,
                                    mapId);
                            mapId++;
                        }
                        // 最后，将id加入到属性值里面
                        temp = classId + "";
                    } else {
                        temp = excelReader.getStringCellValue(
                                row.getCell((short) j)).trim();
                        // 日期的特殊处理
                        if (colName[j].indexOf("date") != -1) {
                            temp = temp.substring(0, temp.length() - 2);
                            // excel是以1990年为基数的，而java中的date是以1970年为基数的。所以要扣除差
                            // 25569天
                            Date d = new Date(
                                    (Long.valueOf(temp) - 25569) * 24 * 3600 * 1000);
                            DateFormat formater = new SimpleDateFormat(
                                    "yyyy-MM-dd");
                            temp = "'" + formater.format(d) + "'";
                        }
                    }

                    sql = sql + temp;
                    if (j != colNum - 1) {
                        sql = sql + ",";
                    }
                    j++;
                }
                sql = sql + ")";
//                System.out.println(sql.toString());
                PreparedStatement ps = conn.prepareStatement(sql.toString());
                ps.execute();
                ps.close();
                sql = "";
                sql = sqlBegin.toString();
            }
            is.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void opStudentTable(DataSource dataSource,
            String clasTabName, String className, Integer classId) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            String sql = "INSERT INTO " + clasTabName
                    + "(id, className) VALUES(" + classId + ", " + className
                    + ")";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
