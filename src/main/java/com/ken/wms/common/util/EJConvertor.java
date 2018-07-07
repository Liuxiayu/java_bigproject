package com.ken.wms.common.util;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;


public class EJConvertor {

    /**
     * 榛樿閰嶇疆鏂囦欢鍚�
     */
    private static final String DEFAULT_CONFIG_FILE_NAME = "EJConvertorConfig.xml";

    /**
     * Entity 鑺傜偣鍚嶇О
     */
    private static final String ENTITY_ELEMENT = "entity";

    /**
     * Property 鑺傜偣鍚嶇О
     */
    private static final String PROPERTY_ELEMENT = "property";


    /**
     * Field 鑺傜偣淇℃伅
     */
    private static final String FIELD_ELEMENT = "field";

    /**
     * Value 鑺傜偣淇℃伅
     */
    private static final String VALUE_ELEMENT = "value";

    /**
     * class 灞炴��
     */
    private static final String CLASS_ATTRIBUTE = "class";

    /**
     * sheetName 灞炴��
     */
    private static final String SHEET_NAME_ATTRIBUTE = "sheetName";

    /**
     * boldHeading 灞炴��
     */
    private static final String BOLD_HEADING_ATTRIBUTE = "boldHeading";

    /**
     * JavaBean鐨勬槧灏勪俊鎭�
     */
    private Map<String, MappingInfo> excelJavaBeanMap;

    public EJConvertor() {
        init(DEFAULT_CONFIG_FILE_NAME);
    }

    public EJConvertor(String filePath) {
        init(filePath);
    }

    /**
     * 鍒濆鍖栨槧灏勪俊鎭�
     *
     * @param fileLocation 閰嶇疆鏂囦欢璺緞
     */
    private void init(String fileLocation) {
        try {
            // 璇诲彇閰嶇疆鏂囦欢
            File configFile = new ClassPathResource(fileLocation).getFile();
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = documentBuilder.parse(configFile);

            // 瑙ｆ瀽閰嶇疆鏂囦欢
            this.excelJavaBeanMap = parseMappingInfo(doc);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 瑙ｆ瀽 Document root 涓嬮厤缃殑鎵�鏈� excel-javaBean 鏄犲皠淇℃伅
     *
     * @param root Document 鏍硅妭鐐�
     * @return 杩斿洖 excel-javaBean 鏄犲皠淇℃伅
     */
    private Map<String, MappingInfo> parseMappingInfo(Document root) {
        // 鑾峰彇 root 涓嬬殑鎵�鏈� entity 鑺傜偣
        NodeList entities = root.getElementsByTagName(ENTITY_ELEMENT);

        // 鍒涘缓鎵胯浇 MappingInfo 淇℃伅鐨凪ap
        Map<String, MappingInfo> mappingInfoMap = new HashMap<>(entities.getLength());

        // 瑙ｆ瀽 entity 鑺傜偣
        for (int index = 0; index < entities.getLength(); index++) {
            // 鍒涘缓 mappingInfo
            MappingInfo mappingInfo = new MappingInfo();

            // 瑙ｆ瀽鑺傜偣淇℃伅
            Node entityNode = entities.item(index);
            if (entityNode.getNodeType() == Node.ELEMENT_NODE) {
                Element entityElement = (Element) entityNode;
                parseEntityElement(entityElement, mappingInfo);
            }

            // 淇濆瓨鑺傜偣淇℃伅
            mappingInfoMap.put(mappingInfo.getClassName(), mappingInfo);
        }
        return mappingInfoMap;
    }

    /**
     * 瑙ｆ瀽 entity 鑺傜偣淇℃伅
     *
     * @param entityElement entity 鑺傜偣
     * @param mappingInfo   鏈� entity 鑺傜偣鍖呭惈鐨勬槧灏勪俊鎭�
     */
    private void parseEntityElement(Element entityElement, MappingInfo mappingInfo) {
        // 瑙ｆ瀽 entity 鐨� class 灞炴��
        String className = entityElement.getAttribute(CLASS_ATTRIBUTE);
        mappingInfo.setClassName(className);

        // 瑙ｆ瀽 entity 鐨� sheetName 灞炴��
        if (entityElement.hasAttribute(SHEET_NAME_ATTRIBUTE))
            mappingInfo.setSheetName(entityElement.getAttribute(SHEET_NAME_ATTRIBUTE));

        // 瑙ｆ瀽 entity 鐨� boldHeading 灞炴��
        if (entityElement.hasAttribute(BOLD_HEADING_ATTRIBUTE)) {
            String isBoldHeading = entityElement.getAttribute(BOLD_HEADING_ATTRIBUTE);
            mappingInfo.setBoldHeading(isBoldHeading.equals("true"));
        }

        // 璇诲彇骞惰В鏋� property 鑺傜偣
        NodeList properties = entityElement.getElementsByTagName(PROPERTY_ELEMENT);
        for (int index = 0; index < properties.getLength(); index++) {
            Node propertyNode = properties.item(index);
            if (propertyNode.getNodeType() == Node.ELEMENT_NODE) {
                Element propertyElement = (Element) propertyNode;
                parsePropertyElement(propertyElement, mappingInfo);
            }
        }
    }

    /**
     * 瑙ｆ瀽 property 鑺傜偣淇℃伅
     *
     * @param propertyElement property 鑺傜偣
     * @param mappingInfo     鎵胯浇鏄犲皠淇℃伅
     */
    private void parsePropertyElement(Element propertyElement, MappingInfo mappingInfo) {
        NodeList infoNodes = propertyElement.getChildNodes();
        String field = null;
        String value = null;

        for (int infoNode_index = 0; infoNode_index < infoNodes.getLength(); infoNode_index++) {
            Node infoNode = infoNodes.item(infoNode_index);
            if (infoNode.getNodeName().equals(FIELD_ELEMENT))
                field = infoNode.getTextContent();
            if (infoNode.getNodeName().equals(VALUE_ELEMENT))
                value = infoNode.getTextContent();
        }

        // 娣诲姞鍒版槧灏勪俊鎭腑
        if (field != null && value != null) {
            mappingInfo.addFieldValueMapping(field, value);
            mappingInfo.addValueFieldMapping(value, field);
        }
    }

    /**
     * 璁�鍙� Excel 鏂囦欢涓殑鍐呭 Excel 鏂囦欢涓殑姣忎竴琛屼唬琛ㄤ簡涓�涓璞″疄渚嬶紝鑰岃涓悇鍒楃殑灞炴�у�煎搴斾负瀵硅薄涓殑鍚勪釜灞炴�у��
     * 璇诲彇鏃讹紝闇�瑕佹寚瀹氳鍙栫洰鏍囧璞＄殑绫诲瀷浠ヨ幏寰楃浉鍏崇殑鏄犲皠淇℃伅锛屽苟涓旇姹傝瀵硅薄宸插湪閰嶇疆鏂囦欢涓敞鍐�
     *
     * @param javaBeanClass 鐩爣瀵硅薄鐨勭被鍨�
     * @param file          鏁版嵁鏉ユ簮鐨� Excel 鏂囦欢
     * @return 鍖呭惈鑻ュ共涓洰鏍囧璞″疄渚嬬殑 List
     */
    public <T> List<T> excelReader(Class<T> javaBeanClass, File file) {
        // 鍙傛暟妫�鏌�
        if (file == null || javaBeanClass == null)
            return null;

        // 鍒濆鍖栧瓨鏀捐鍙栫粨鏋滅殑 List
        List<T> javaBeans = new ArrayList<>();

        // 鑾峰彇绫诲悕鍜屾槧灏勪俊鎭�
        String className = javaBeanClass.getName();
        MappingInfo mappingInfo = excelJavaBeanMap.get(className);
        if (mappingInfo == null)
            return null;

        // 璇诲彇 Excel 鏂囦欢
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(file))) {
            Sheet dataSheet = workbook.getSheetAt(0);
            Row row;
            Cell cell;

            Iterator<Row> rowIterator = dataSheet.iterator();
            Iterator<Cell> cellIterator;

            // 璇诲彇绗竴琛岃〃澶翠俊鎭�
            if (!rowIterator.hasNext())
                return null;
            String fieldName;
            Field fieldInstance;
            Class<?> fieldClass;
            List<String> fieldNameList = new ArrayList<>();// 鐩爣瀵硅薄鐨� field 鍚嶇О鍒楄〃
            List<Class<?>> fieldClassList = new ArrayList<>();// 鐩爣瀵硅薄 field 绫诲瀷鍒楄〃
            row = rowIterator.next();
            cellIterator = row.iterator();
            while (cellIterator.hasNext()) {
                cell = cellIterator.next();

                // 鑾峰彇 value 瀵瑰簲鐨� field 鐨勫悕绉颁互鍙婄被鍨�
                fieldName = mappingInfo.getValueFieldMapping(cell.getStringCellValue());
                fieldClass = (fieldName != null && (fieldInstance = javaBeanClass.getDeclaredField(fieldName)) != null) ?
                        fieldInstance.getType() : null;

                // 淇濆瓨 value 瀵瑰簲鐨� field 鐨勫悕绉板拰绫诲瀷
                fieldClassList.add(cell.getColumnIndex(), fieldClass);
                fieldNameList.add(cell.getColumnIndex(), fieldName);
            }

            // 璇诲彇琛ㄦ牸鍐呭
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                cellIterator = row.iterator();
                T javaBean = javaBeanClass.newInstance();

                // 璇诲彇鍗曞厓鏍�
                while (cellIterator.hasNext()) {
                    cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();

                    // 鑾峰彇鍗曞厓鏍肩殑鍊硷紝骞惰缃璞′腑瀵瑰簲鐨勫睘鎬�
                    Object fieldValue = getCellValue(fieldClassList.get(columnIndex), cell);
                    if (fieldValue == null) continue;
                    setField(javaBean, fieldNameList.get(columnIndex), fieldValue);
                }
                // 鏀惧叆缁撴灉
                javaBeans.add(javaBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return javaBeans;
    }

    /**
     * 灏� List 涓殑鍏冪礌瀵硅薄鍐欏叆鍒� Excel 涓紝鍏朵腑姣忎竴涓璞＄殑涓�琛岋紝姣忎竴鍒楃殑鍐呭涓哄璞＄殑灞炴��
     *
     * @param classType 鐩爣瀵硅薄鐨勭被鍨�
     * @param javaBeans 鏁版嵁鏉ユ簮鐨� List
     * @return 杩斿洖excel鏂囦欢
     */
    public File excelWriter(Class<?> classType, List<?> javaBeans) {
        // 鍙傛暟妫�鏌�
        if (classType == null || javaBeans == null)
            return null;

        // 鑾峰彇绫诲悕鍜屾槧灏勪俊鎭�
        String className = classType.getName();
        MappingInfo mappingInfo = excelJavaBeanMap.get(className);
        if (mappingInfo == null)
            return null;

        // 鑾峰彇璇� javaBean 娉ㄥ唽闇�瑕佸啓鍒� excel 鐨� field
        Set<String> fields = mappingInfo.getFieldValueMapping().keySet();// 娉ㄥ唽鐨� field 鍒楄〃
        List<String> valuesList = new ArrayList<>();// field 瀵瑰簲鐨� excel 琛ㄥご value 鍒楄〃
        fields.forEach(field -> valuesList.add(mappingInfo.getFieldValueMapping(field)));

        // 鍒涘缓瀵瑰簲鐨� excel 鏂囦欢
        File excel = null;
        try {
            // 鍒涘缓涓存椂鏂囦欢
            excel = File.createTempFile("excel", ".xlsx");
            // 鍒涘缓 workBook 瀵硅薄
            Workbook workbook = new XSSFWorkbook();
            // 鍒涘缓 sheet 瀵硅薄
            Sheet sheet = workbook.createSheet(mappingInfo.getSheetName());

            int rowIndex = 0;
            int cellIndex;
            Row row;
            Cell cell;

            // 鍐欏叆绗竴琛岃〃澶�
            cellIndex = 0;
            row = sheet.createRow(rowIndex++);
            XSSFFont font = (XSSFFont) workbook.createFont();
            font.setBold(mappingInfo.isBoldHeading());
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFont(font);
            for (String value : valuesList) {
                cell = row.createCell(cellIndex);
                cell.setCellValue(value);
                cellIndex++;

                // 璁剧疆鏍峰紡
                cell.setCellStyle(cellStyle);
            }

            // 鍐欏叆鍐呭鏁版嵁
            for (Object javaBean : javaBeans) {
                row = sheet.createRow(rowIndex++);
                cellIndex = 0;
                for (String fieldName : fields) {
                    Object value = getField(javaBean, getGetterMethodName(fieldName));
                    cell = row.createCell(cellIndex++);
                    setCellValue1(value, workbook, cell);
                }
            }

            // 璋冩暣 cell 澶у皬
            for (int i = 0; i < valuesList.size(); i++) {
                sheet.autoSizeColumn(i);
            }

            // 灏� workBook 鍐欏叆鍒� tempFile 涓�
            FileOutputStream outputStream = new FileOutputStream(excel);
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return excel;
    }

    /**
     * 鑾峰彇 Excel 鍗曞厓鏍间腑鐨勫��
     *
     * @param fieldClass JavaBean 灞炴�у瓧娈电殑绫诲瀷
     * @param cell       鍗曞厓鏍�
     * @param <T>        娉涘瀷绫诲瀷
     * @return 杩斿洖 JavaBean 灞炴�х被鍨嬪搴旂殑鍊�
     */
    @SuppressWarnings("unchecked")
    private <T> T getCellValue(Class<T> fieldClass, Cell cell) {

        // field 瀵瑰��
        T fieldValue = null;

        if (fieldClass == int.class || fieldClass == Integer.class) {
            // convert to Integer
            cell.setCellType(Cell.CELL_TYPE_STRING);
            String cellValue = cell.getStringCellValue();
            Integer integer = NumberUtils.isNumber(cellValue) ? Double.valueOf(cellValue).intValue() : 0;
            fieldValue = (T) integer;
        } else if (fieldClass == long.class || fieldClass == Long.class) {
            // convert to Long
            cell.setCellType(Cell.CELL_TYPE_STRING);
            String cellValue = cell.getStringCellValue();
            Long l = NumberUtils.isNumber(cellValue) ? Double.valueOf(cellValue).longValue() : 0;
            fieldValue = (T) l;
        } else if (fieldClass == float.class || fieldClass == Float.class) {
            // convert to Float
            cell.setCellType(Cell.CELL_TYPE_STRING);
            String cellValue = cell.getStringCellValue();
            Float f = NumberUtils.isNumber(cellValue) ? Float.valueOf(cellValue) : 0;
            fieldValue = (T) f;
        } else if (fieldClass == double.class || fieldClass == Double.class) {
            // convert to Double
            cell.setCellType(Cell.CELL_TYPE_STRING);
            String cellValue = cell.getStringCellValue();
            Double d = NumberUtils.isNumber(cellValue) ? Double.valueOf(cellValue) : 0;
            fieldValue = (T) d;
        } else if (fieldClass == short.class || fieldClass == Short.class) {
            // convert to Short
            cell.setCellType(Cell.CELL_TYPE_STRING);
            String cellValue = cell.getStringCellValue();
            Short s = NumberUtils.isNumber(cellValue) ? Double.valueOf(cellValue).shortValue() : 0;
            fieldValue = (T) s;
        } else if (fieldClass == boolean.class || fieldClass == Boolean.class) {
            // get Boolean
            cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
            Boolean b = cell.getBooleanCellValue();
            fieldValue = (T) b;
        } else if (fieldClass == char.class || fieldClass == Character.class) {
            // convert to Character
            cell.setCellType(Cell.CELL_TYPE_STRING);
            String cellValue = cell.getStringCellValue();
            Character c = cellValue.charAt(0);
            fieldValue = (T) c;
        } else if (fieldClass == byte.class || fieldClass == Byte.class) {
            // convert to Byte
            cell.setCellType(Cell.CELL_TYPE_STRING);
            String cellValue = cell.getStringCellValue();
            Byte b = NumberUtils.isNumber(cellValue) ? Double.valueOf(cellValue).byteValue() : 0;
            fieldValue = (T) b;
        } else if (fieldClass == String.class) {
            // convert to String
            cell.setCellType(Cell.CELL_TYPE_STRING);
            String cellValue = cell.getStringCellValue();
            fieldValue = (T) cellValue;
        } else if (fieldClass == Date.class) {
            // convert to java.util.Date
            fieldValue = HSSFDateUtil.isCellDateFormatted(cell) ? (T) cell.getDateCellValue() : null;
        } else if (fieldClass == java.sql.Date.class) {
            // convert to java.sql.Date
            fieldValue = null;
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                java.sql.Date date = new java.sql.Date(cell.getDateCellValue().getTime());
                fieldValue = (T) date;
            }
        }
        return fieldValue;
    }

    /**
     * 璁剧疆鍗曞厓鏍肩殑鍊�
     *
     * @param cellValue 鍗曞厓鏍肩殑鍊�
     * @param workbook  workbook
     * @param cell      鍗曞厓鏍�
     */
    private void setCellValue1(Object cellValue, Workbook workbook, Cell cell) {
        // 鍙傛暟妫�鏌�
        if (cell == null || cellValue == null || workbook == null)
            return;

        Class<?> cellValueClass = cellValue.getClass();
        if (cellValueClass == boolean.class || cellValueClass == Boolean.class) {
            cell.setCellValue((Boolean) cellValue);
        } else if (cellValueClass == char.class || cellValueClass == Character.class) {
            cell.setCellValue(String.valueOf(cellValue));
        } else if (cellValueClass == byte.class || cellValueClass == Byte.class) {
            cell.setCellValue((Byte) cellValue);
        } else if (cellValueClass == short.class || cellValueClass == Short.class) {
            cell.setCellValue((Short) cellValue);
        } else if (cellValueClass == int.class || cellValueClass == Integer.class) {
            cell.setCellValue((Integer) cellValue);
        } else if (cellValueClass == long.class || cellValueClass == Long.class) {
            cell.setCellValue((Long) cellValue);
        } else if (cellValueClass == float.class || cellValueClass == Float.class) {
            cell.setCellValue(String.valueOf(cellValue));
//            cell.setCellValue((Float) cellValue);
        } else if (cellValueClass == double.class || cellValueClass == Double.class) {
            cell.setCellValue((Double) cellValue);
        } else if (cellValueClass == String.class) {
            cell.setCellValue((String) cellValue);
        } else if (cellValueClass == Date.class) {
            Date v = (Date) cellValue;
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper creationHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy/mm/dd"));
            cell.setCellValue(v);
            cell.setCellStyle(cellStyle);
        } else if (cellValueClass == java.sql.Date.class) {
            java.sql.Date v = (java.sql.Date) cellValue;
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper creationHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy/mm/dd"));
            cell.setCellValue(v);
            cell.setCellStyle(cellStyle);
        }
    }

    /**
     * 璁剧疆 JavaBean 鎸囧畾 field 鐨勫��
     *
     * @param targetObject 鎸囧畾鐨� JavaBean 瀵硅薄
     * @param fieldName    灞炴�у瓧娈电殑鍚嶇О
     * @param fieldValue   灞炴�у瓧娈电殑鍊�
     * @throws Exception Exception
     */
    private void setField(Object targetObject, String fieldName, Object fieldValue) throws Exception {
        // 鑾峰彇瀵瑰簲鐨� setter 鏂规硶
        Class<?> targetObjectClass = targetObject.getClass();
        Class<?> fieldClass = targetObjectClass.getDeclaredField(fieldName).getType();
        Method setterMethod = targetObjectClass.getMethod(getSetterMethodName(fieldName), fieldClass);

        // 璋冪敤 setter 鏂规硶锛岃缃� field 鐨勫��
        setterMethod.invoke(targetObject, fieldValue);
    }

    /**
     * 鑾峰彇鐩爣瀵硅薄涓煇涓睘鎬х殑鍊硷紝閫氳繃璋冪敤鐩爣瀵硅薄灞炴�у搴旂殑 getter 鏂规硶锛屽洜鑰岃姹傜洰鏍囧璞″繀椤昏缃� getter 瀵硅薄锛屽惁鍒欒祴鍊间笉鎴愬姛
     *
     * @param targetObject 鐩爣瀵硅薄
     * @param methodName   getter 鏂规硶鍚�
     * @return 杩斿洖璇ュ睘鎬х殑鍊�
     * @throws Exception Exception
     */
    private Object getField(Object targetObject, String methodName) throws Exception {
        // 鑾峰緱 getter 鏂规硶瀹炰緥
        Class<?> targetObjectType = targetObject.getClass();
        Method getterMethod = targetObjectType.getMethod(methodName);

        // 璋冪敤鏂规硶
        return getterMethod.invoke(targetObject);
    }

    /**
     * setter 鏂规硶鍚嶇紦瀛�
     */
    private Map<String, String> setterMethodNameCache = new HashMap<>(64);

    /**
     * getter 鏂规硶鍚嶇紦瀛�
     */
    private Map<String, String> getterMethodNameCache = new HashMap<>(64);

    /**
     * 鏋勯�� setter 鏂规硶鐨勬柟娉曞悕
     *
     * @param fieldName 瀛楁鍚�
     * @return field瀵瑰簲鐨凷etter鏂规硶鍚�
     */
    private String getSetterMethodName(String fieldName) {
        // 灏濊瘯浠庣紦瀛樹腑鍙栧嚭, 鑻ユ病鏈夊垯鐢熸垚鍐嶆斁鍏�
        return setterMethodNameCache.computeIfAbsent(fieldName, n -> "set" + n.replace(n.substring(0, 1), n.substring(0, 1).toUpperCase()));
    }

    /**
     * 鏋勯�� getter 鏂规硶鐨勬柟娉曞悕
     *
     * @param fieldName 瀛楁鍚�
     * @return field瀵瑰簲鐨凣etter鏂规硶鍚�
     */
    private String getGetterMethodName(String fieldName) {
        return getterMethodNameCache.computeIfAbsent(fieldName, n -> "get" + n.replace(n.substring(0, 1), n.substring(0, 1).toUpperCase()));
    }

    /**
     * Excel-JavaBean鏄犲皠淇℃伅
     */
    private class MappingInfo {
        /**
         * 鏄犲皠鐨凧avaBean鐨勫叏闄愬畾绫诲悕
         */
        private String className;

        /**
         * excel 琛ㄤ腑 sheet 鐨勫悕绉�
         */
        private String sheetName = "sheet1";

        /**
         * 琛ㄦ牸鏍囬鍔犵矖
         */
        private boolean boldHeading = false;

        /**
         * Field - Value 鏄犲皠
         */
        private Map<String, String> fieldValueMapping = new LinkedHashMap<>();

        /**
         * Value - Field 鏄犲皠
         */
        private Map<String, String> valueFieldMapping = new LinkedHashMap<>();

        /**
         * 璁剧疆鏄犲皠淇℃伅鐨凧avaBean鐨勫叏绉扮被鍚�
         *
         * @param className JavaBean鍏ㄧО绫诲悕
         */
        void setClassName(String className) {
            this.className = className;
        }

        /**
         * 杩斿洖 mappingInfo 瀵瑰簲鐨� className
         *
         * @return className
         */
        String getClassName() {
            return className;
        }

        /**
         * 璁剧疆琛ㄦ牸鐨� sheet 鍚嶇О
         *
         * @param sheetName sheet 鍚嶇О
         */
        void setSheetName(String sheetName) {
            this.sheetName = sheetName;
        }

        /**
         * 鑾峰彇琛ㄦ牸鐨� sheet 鍚嶇О
         *
         * @return 杩斿洖琛ㄦ牸鐨� sheet 鍚嶇О
         */
        String getSheetName() {
            return sheetName;
        }

        /**
         * 璁剧疆琛ㄥご鏍囬鏄惁鍔犵矖
         *
         * @param boldHeading 鏄惁鍔犵矖
         */
        void setBoldHeading(boolean boldHeading) {
            this.boldHeading = boldHeading;
        }

        /**
         * 鑾峰彇琛ㄥご鏍囬鏄惁鍔犵矖
         *
         * @return 杩斿洖琛ㄥご鏍囬鏄惁鍔犵矖
         */
        boolean isBoldHeading() {
            return boldHeading;
        }

        /**
         * 娣诲姞 Field - Value 鏄犲皠
         *
         * @param field Field鍩�
         * @param value Value鍩�
         */
        void addFieldValueMapping(String field, String value) {
            fieldValueMapping.put(field, value);
        }

        /**
         * 杩斿洖鎸囧畾Field鏄犲皠鐨刅alue
         *
         * @param field Field鍩�
         * @return 杩斿洖鏄犲皠鐨刅alue
         */
        String getFieldValueMapping(String field) {
            return fieldValueMapping.get(field);
        }

        /**
         * 娣诲姞 Value - Field 鏄犲皠
         *
         * @param value Value鍩�
         * @param field Field鍩�
         */
        void addValueFieldMapping(String value, String field) {
            valueFieldMapping.put(value, field);
        }

        /**
         * 杩斿洖鎸囧畾鐨刅alue 鏄犲皠鐨� Field
         *
         * @param value Value鍩�
         * @return 杩斿洖鏄犲皠鐨凢ield
         */
        String getValueFieldMapping(String value) {
            return valueFieldMapping.get(value);
        }

        /**
         * 鑾峰緱 Field - Value 鏄犲皠
         *
         * @return 杩斿洖Field - Value 鏄犲皠
         */
        Map<String, String> getFieldValueMapping() {
            return fieldValueMapping;
        }
    }
}
