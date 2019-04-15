package fr.diginamic.WorkBook.service;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import fr.diginamic.WorkBook.annotations.ExcelField;
import fr.diginamic.WorkBook.annotations.ExcelObject;
import fr.diginamic.WorkBook.annotations.MappedExcelObject;
import fr.diginamic.WorkBook.annotations.ParseType;
import fr.diginamic.WorkBook.exception.ExcelParsingException;
import fr.diginamic.WorkBook.helper.HSSFHelper;



@Service
public class SheetParser {

	private final Logger log = LoggerFactory.getLogger(SheetParser.class);
	private static final String DATE_FORMAT = "dd/MM/yyyy";

	
	public SheetParser() {
	}

	
	// requete, nom fichier, entite avec annotation, et mes valeurs de cette entites;
	public <T> void createXLS(OutputStream fileOut, String sheetName, Class<T> clazz, List<T> values)
			throws IOException, IllegalAccessException {
		try (XSSFWorkbook workbook = new XSSFWorkbook()) {
			XSSFSheet sheet = workbook.createSheet(sheetName);
			
			//titre
			Row row2 = sheet.createRow((short)1 );
		    CellStyle style = workbook.createCellStyle();
		    Font font = workbook.createFont();
		    font.setFontHeightInPoints((short)14);
		    font.setFontName("Courier New");
		    font.setItalic(true);
		    
		    font.setColor(HSSFColor.RED.index);
		    style.setFont(font);
		    style.setAlignment(CellStyle.ALIGN_CENTER);
			Cell cell2 =	row2.createCell(2);
			cell2.setCellValue("Liste des missions :");
			cell2.setCellStyle(style);
			
			
			

			CreationHelper createHelper = workbook.getCreationHelper();
			Row row = sheet.createRow((short) 2);
			Map<Integer, String> excelPositionMap = getExcelColumnNamePositionMap(clazz);

			// creation des colonnes
			this.createColumnDefaultStyle(clazz, workbook, sheet, createHelper);

			// inscription des nom de colonnes
		

			for (Map.Entry<Integer, String> entrySet : excelPositionMap.entrySet()) {
				   CellStyle style2 = workbook.createCellStyle();
				   Font font2 = workbook.createFont();
				   style2.setFont(font2);
				   style2.setAlignment(CellStyle.ALIGN_CENTER);
				   style2.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
				   style2.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
				   style2.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
				   style2.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
				Cell cell =	row.createCell(entrySet.getKey() - 1);
				cell.setCellValue(createHelper.createRichTextString(entrySet.getValue()));
				cell.setCellStyle(style2);
			}

			// insertion des valeurs
			if (values != null) {
				fillWithValue(clazz, values, workbook, sheet, createHelper);
			}

			XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);

			
		
			workbook.write(fileOut);

		}
	}

	public <T> List<T> parseEntity(InputStream inputStream, String sheetName, Class<T> clazz,
			Consumer<ExcelParsingException> errorHandler) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		List<T> entityList = this.createEntity(sheet, clazz, errorHandler);
		workbook.close();
		inputStream.close();
		return entityList;
	}

	public <T> List<T> createEntity(Sheet sheet, Class<T> clazz, Consumer<ExcelParsingException> errorHandler) {
		List<T> list = new ArrayList<>();
		ExcelObject excelObject = getExcelObject(clazz, errorHandler);
		if (excelObject.start() <= 0 || excelObject.end() < 0) {
			return list;
		}
		int end = getEnd(sheet, clazz, excelObject);

		for (int currentLocation = excelObject.start(); currentLocation <= end; currentLocation++) {
			T object = getNewInstance(sheet, clazz, excelObject.parseType(), currentLocation, excelObject.zeroIfNull(),
					errorHandler);
			List<Field> mappedExcelFields = getMappedExcelObjects(clazz);
			for (Field mappedField : mappedExcelFields) {
				Class<?> fieldType = mappedField.getType();
				Class<?> clazz1 = fieldType.equals(List.class) ? getFieldType(mappedField) : fieldType;
				List<?> fieldValue = createEntity(sheet, clazz1, errorHandler);
				if (fieldType.equals(List.class)) {
					setFieldValue(mappedField, object, fieldValue);
				} else if (!fieldValue.isEmpty()) {
					setFieldValue(mappedField, object, fieldValue.get(0));
				}
			}
			list.add(object);
		}
		return list;
	}

	private <T> int getEnd(Sheet sheet, Class<T> clazz, ExcelObject excelObject) {
		int end = excelObject.end();
		if (end > 0) {
			return end;
		}
		return getRowOrColumnEnd(sheet, clazz);
	}

	public <T> int getRowOrColumnEnd(Sheet sheet, Class<T> clazz) {
		ExcelObject excelObject = getExcelObject(clazz, e -> {
			throw e;
		});
		int maxCellNumber = 0;
		if (excelObject != null) {
			ParseType parseType = excelObject.parseType();
			if (parseType == ParseType.ROW) {
				return sheet.getLastRowNum() + 1;
			}

			Set<Integer> positions = getExcelFieldPositionMap(clazz).keySet();
			OptionalInt maxPosition = positions.stream().mapToInt(x -> x).max();
			OptionalInt minPosition = positions.stream().mapToInt(x -> x).min();

			for (int i = minPosition.getAsInt(); i < maxPosition.getAsInt(); i++) {
				int cellsNumber = sheet.getRow(i).getLastCellNum();
				if (maxCellNumber < cellsNumber) {
					maxCellNumber = cellsNumber;
				}
			}
		}
		return maxCellNumber;
	}

	private Class<?> getFieldType(Field field) {
		Type type = field.getGenericType();
		if (type instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) type;
			return (Class<?>) pt.getActualTypeArguments()[0];
		}

		return null;
	}

	private <T> List<Field> getMappedExcelObjects(Class<T> clazz) {
		List<Field> fieldList = new ArrayList<>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			MappedExcelObject mappedExcelObject = field.getAnnotation(MappedExcelObject.class);
			if (mappedExcelObject != null) {
				field.setAccessible(true);
				fieldList.add(field);
			}
		}
		return fieldList;
	}

	private <T> ExcelObject getExcelObject(Class<T> clazz, Consumer<ExcelParsingException> errorHandler) {
		ExcelObject excelObject = clazz.getAnnotation(ExcelObject.class);
		if (excelObject == null) {
			errorHandler.accept(new ExcelParsingException(
					"Invalid class configuration - ExcelObject annotation missing - " + clazz.getSimpleName()));
		}
		return excelObject;
	}

	private <T> T getNewInstance(Sheet sheet, Class<T> clazz, ParseType parseType, Integer currentLocation,
			boolean zeroIfNull, Consumer<ExcelParsingException> errorHandler) {
		T object = getInstance(clazz, errorHandler);
		Map<Integer, Field> excelPositionMap = getExcelFieldPositionMap(clazz);
		for (Map.Entry<Integer, Field> entrySet : excelPositionMap.entrySet()) {
			Field field = entrySet.getValue();
			Object cellValue;

			if (ParseType.ROW == parseType) {
				cellValue = HSSFHelper.getCellValue(sheet, field.getType(), currentLocation, entrySet.getKey(),
						zeroIfNull, errorHandler);
			} else {
				cellValue = HSSFHelper.getCellValue(sheet, field.getType(), entrySet.getKey(), currentLocation,
						zeroIfNull, errorHandler);
			}
			setFieldValue(field, object, cellValue);
		}

		return object;
	}

	private <T> T getInstance(Class<T> clazz, Consumer<ExcelParsingException> errorHandler) {
		T object;
		try {
			Constructor<T> constructor = clazz.getDeclaredConstructor();
			constructor.setAccessible(true);
			object = constructor.newInstance();
		} catch (Exception e) {
			errorHandler.accept(new ExcelParsingException(
					"Exception occurred while instantiating the class " + clazz.getName(), e));
			return null;
		}
		return object;
	}

	private <T> void setFieldValue(Field field, T object, Object cellValue) {
		try {
			field.set(object, cellValue);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ExcelParsingException("Exception occurred while setting field value ", e);
		}
	}

	private <T> Map<Integer, Field> getExcelFieldPositionMap(Class<T> clazz) {
		Map<Integer, Field> fieldMap = new HashMap<>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			ExcelField excelField = field.getAnnotation(ExcelField.class);
			if (excelField != null) {
				field.setAccessible(true);
				fieldMap.put(excelField.position(), field);
			}
		}
		return fieldMap;
	}

	private <T> Map<Integer, String> getExcelColumnNamePositionMap(Class<T> clazz) {
		Map<Integer, String> fieldMap = new HashMap<>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			ExcelField excelField = field.getAnnotation(ExcelField.class);
			if (excelField != null) {
				fieldMap.put(excelField.position(), excelField.name());
			}
		}
		return fieldMap;
	}

	private <T> void fillWithValue(Class<T> clazz, List<T> values, XSSFWorkbook workbook, XSSFSheet sheet,
			CreationHelper createHelper) throws IllegalAccessException {
		Row row;
		int lineNumber = 3;
		for (T value : values) {
			Map<Integer, Field> fields = getExcelFieldPositionMap(clazz);
			row = sheet.createRow(lineNumber);
			lineNumber++;
			for (Map.Entry<Integer, Field> entrySet : fields.entrySet()) {
				Field field = entrySet.getValue();
				if (field.get(value) != null) {
					if (field.getType().equals(String.class)) {
						row.createCell(entrySet.getKey() - 1).setCellValue(field.get(value).toString());
					}

					// create and fill Date cell type
					if (field.getType().equals(Date.class)) {
						Date d = (Date) field.get(value);
						Cell cell = row.createCell(entrySet.getKey() - 1);
						cell.setCellValue(d);
						CellStyle cellStyle = workbook.createCellStyle();
						cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
						short dateFormat = createHelper.createDataFormat().getFormat(DATE_FORMAT);
						cellStyle.setDataFormat(dateFormat);
						cell.setCellStyle(cellStyle);
					}

					// create and fill Date cell type
					if (field.getType().equals(LocalDate.class)) {
						LocalDate ld = (LocalDate) field.get(value);
						Cell cell = row.createCell(entrySet.getKey() - 1);
						cell.setCellValue(Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant()));
						CellStyle cellStyle = workbook.createCellStyle();
						cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
						short dateFormat = createHelper.createDataFormat().getFormat(DATE_FORMAT);
						cellStyle.setDataFormat(dateFormat);
						cell.setCellStyle(cellStyle);
					}

					// create and fill Date cell type
					if (field.getType().equals(LocalDateTime.class)) {
						LocalDateTime ldt = (LocalDateTime) field.get(value);
						Cell cell = row.createCell(entrySet.getKey() - 1);
						cell.setCellValue(Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant()));
						CellStyle cellStyle = workbook.createCellStyle();
						cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
						short dateFormat = createHelper.createDataFormat().getFormat(DATE_FORMAT);
						cellStyle.setDataFormat(dateFormat);
						cell.setCellStyle(cellStyle);
					}

					// create and fill Numeric cell type
					if (field.getType().equals(Integer.class)) {
						row.createCell(entrySet.getKey() - 1)
								.setCellValue(Integer.valueOf(field.get(value).toString()));
					}

					if (field.getType().equals(Double.class)) {
						row.createCell(entrySet.getKey() - 1).setCellValue(Double.valueOf(field.get(value).toString()));
					}

					if (field.getType().equals(Long.class)) {
						row.createCell(entrySet.getKey() - 1).setCellValue(Long.valueOf(field.get(value).toString()));
					}
				}
			}
		}
	}

	private <T> void createColumnDefaultStyle(Class<T> clazz, XSSFWorkbook workbook, XSSFSheet sheet,
			CreationHelper createHelper) {
		Map<Integer, Field> fields = getExcelFieldPositionMap(clazz);
		for (Map.Entry<Integer, Field> entrySet : fields.entrySet()) {
			Field field = entrySet.getValue();
			CellStyle cellStyle = workbook.createCellStyle();
			short dateFormat;
			// create and fill Date cell type
			if (field.getType().equals(Date.class)) {
				dateFormat = createHelper.createDataFormat().getFormat(DATE_FORMAT);
				cellStyle.setDataFormat(dateFormat);
				sheet.setDefaultColumnStyle(entrySet.getKey() - 1, cellStyle);
			}

			// create and fill Date cell type
			if (field.getType().equals(LocalDate.class)) {
				dateFormat = createHelper.createDataFormat().getFormat(DATE_FORMAT);
				cellStyle.setDataFormat(dateFormat);
				sheet.setDefaultColumnStyle(entrySet.getKey() - 1, cellStyle);
			}

			// create and fill Date cell type
			if (field.getType().equals(LocalDateTime.class)) {
				dateFormat = createHelper.createDataFormat().getFormat(DATE_FORMAT);
				cellStyle.setDataFormat(dateFormat);
				sheet.setDefaultColumnStyle(entrySet.getKey() - 1, cellStyle);
			}

			if (field.getType().equals(Double.class)) {
				dateFormat = createHelper.createDataFormat().getFormat("#,##0.00");
				cellStyle.setDataFormat(dateFormat);
				sheet.setDefaultColumnStyle(entrySet.getKey() - 1, cellStyle);
			}

			if (field.getType().equals(String.class)) {
				dateFormat = createHelper.createDataFormat().getFormat("@");
				cellStyle.setDataFormat(dateFormat);
				sheet.setDefaultColumnStyle(entrySet.getKey() - 1, cellStyle);
			}

		}
	}
}
