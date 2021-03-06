package net.java.amateras.xlsbeans.xssfconverter.impl.jxl;

import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import net.java.amateras.xlsbeans.xssfconverter.WBorder;
import net.java.amateras.xlsbeans.xssfconverter.WBorderLineStyle;
import net.java.amateras.xlsbeans.xssfconverter.WCellFormat;

/**
 * CellFormat wrapper for Java Excel API.
 *
 * @author Mitsuyoshi Hasegawa
 */
public class JxlWCellFormatImpl implements WCellFormat {

    private CellFormat format = null;

    public JxlWCellFormatImpl(CellFormat format) {
        this.format = format;
    }

    public WBorderLineStyle getBorder(WBorder border) {
        BorderLineStyle borderLineStyle = null;
        if (WBorder.ALL.equals(border)) {
            borderLineStyle = format.getBorder(Border.ALL);
        } else if (WBorder.BOTTOM.equals(border)) {
            borderLineStyle = format.getBorder(Border.BOTTOM);
        } else if (WBorder.LEFT.equals(border)) {
            borderLineStyle = format.getBorder(Border.LEFT);
        } else if (WBorder.NONE.equals(border)) {
            borderLineStyle = format.getBorder(Border.NONE);
        } else if (WBorder.RIGHT.equals(border)) {
            borderLineStyle = format.getBorder(Border.RIGHT);
        } else if (WBorder.TOP.equals(border)) {
            borderLineStyle = format.getBorder(Border.TOP);
        }
        if (borderLineStyle == null) {
            throw new IllegalArgumentException("Not support border style.");
        }
        return new WBorderLineStyle(borderLineStyle.getValue(), borderLineStyle.getDescription());
    }

}
