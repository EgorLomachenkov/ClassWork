package com.example.webapp;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;


/**
 * Template which will generate specified HTML table. Using StringBuilder.
 * @author Alex Sharlan
 * @since 03.2015
 */

public class HTMLTableBuilder {

    private int columns;
    private int rowsCounter;
    
    public static int fontHeaderSize;
    public static int fontRowSize;
    public static int fontColumnSize;
    
    private final StringBuilder table = new StringBuilder();
    public static String HTML_START = "<html>";
    public static String HTML_END = "</html>";
    public static String TABLE_START_BORDER = "<table border// =\"0\" width=\"80%\">"; 
    public static String TABLE_START = "<table>";
    public static String TABLE_END = "</table>";
    public static String HEADER_START = "<th>";
    public static String HEADER_END = "</th>";
    public static String ROW_START = "<tr>";
    public static String ROW_END = "</tr>";
    public static String COLUMN_START = "<td>";
    public static String COLUMN_END = "</td>";
    public static String FONT_HEADER_START = "<font size='" + fontHeaderSize + "' face='sans-serif''";
    public static String FONT_HEADER_END = "</font>";
    public static String FONT_COLUMN_START = "<font size='" + fontColumnSize + "' face='sans-serif''>";
    public static String FONT_COLUMN_END = "</font>";
    public static String FONT_ROW_START = "<font size='" + fontRowSize + "' face='sans-serif''";
    public static String FONT_ROW_END = "</font>";
    public static String ROW_trBgcolorWhite_START = "<tr bgcolor='white'>";
    public static String ROW_trBgcolorGray_START = "<tr bgcolor='#efefef'>";

    /**
     * @param header
     * @param border
     * @param rows
     * @param columns
     * @param fontHeaderSize
     * @param fontRowSize
     * @param fontColumnSize
     */
    public HTMLTableBuilder(String header, boolean border, int rows, int columns, int fontHeaderSize, int fontRowSize, int fontColumnSize) {
	this.columns = columns;
	HTMLTableBuilder.fontHeaderSize = fontHeaderSize;
	HTMLTableBuilder.fontRowSize = fontRowSize;
	HTMLTableBuilder.fontColumnSize = fontColumnSize;
	if (header != null) {
	    table.append("<b>");
	    table.append(header);
	    table.append("</b>");
	}
	table.append(HTML_START);
	table.append(border ? TABLE_START_BORDER : TABLE_START);
	table.append(TABLE_END);
	table.append(HTML_END);
    }

    /**
	 */
    public void addTableHeader(String... values) {
	rowsCounter++;
	if (values.length != columns) {
	    System.out.println("Error column lenth");
	} else {
	    int lastIndex = table.lastIndexOf(TABLE_END);
	    if (lastIndex > 0) {
		StringBuilder sb = new StringBuilder();
		sb.append(ROW_START);
		for (String value : values) {
		    sb.append(HEADER_START);
		    sb.append(value);
		    sb.append(HEADER_END);
		}
		sb.append(ROW_END);
		table.insert(lastIndex, sb.toString());
	    }
	}
    }

    /**
	 */
    public void addRowValues(String... values) {
	rowsCounter++;
	if (values.length != columns) {
	    System.out.println("Error column lenth");
	} else {
	    int lastIndex = table.lastIndexOf(ROW_END);
	    if (lastIndex > 0) {
		int index = lastIndex + ROW_END.length();
		StringBuilder sb = new StringBuilder();
		sb.append(rowsCounter % 2 == 0 ? ROW_trBgcolorWhite_START : ROW_trBgcolorGray_START);
		for (String value : values) {
		    sb.append(COLUMN_START);
		    sb.append(value);
		    sb.append(COLUMN_END);
		}
		sb.append(ROW_END);
		table.insert(index, sb.toString());
	    }
	}
    }
 
    /**
     * RQMT#607 - SEAT - batch job dealing with BlueGroup expiration dates on
     * existing BlueGroups
     * 
     * @param map - TreeMap - storing results for each Prolongation result
	 */


    //RQMT#626 batch job to load Dimension Level Values
    public void addRowValues2(Map<String, String> map) {
	for (Map.Entry<String, String> entry : map.entrySet()) {
	    this.addRowValues(entry.getKey(), entry.getValue());
	}
    }
    /**
     * Build HTML table
     * @return
     */
    public String build() {
	return table.toString();
    }

}