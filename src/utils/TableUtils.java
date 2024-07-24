package utils;

import model.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;

public class TableUtils {
    public static void renderMenu(List<String>menu, String title){
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        Table table = new Table(2, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        table.addCell(title, cellStyle, 2);
        table.setColumnWidth(0,5,10);
        table.setColumnWidth(1,35,40);
        if(menu!=null){
            for(int i =0 ; i<menu.size(); i++){
                table.addCell((i+1)+" ",cellStyle);
                table.addCell(menu.get(i));
            }
        }
        System.out.println(table.render());
    }
    public static void renderProductData(List<Product> allProducts, String title){
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        Table table = new Table(7, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND_HEADER_FOOTER_AND_COLUMNS);
        table.addCell(title, cellStyle, 7);
        table.setColumnWidth(0,6,10);
        table.setColumnWidth(1,35,40);
        table.addCell("ID");
        table.addCell("Product Name ");
        table.addCell("Price ");
        table.addCell("Company ");
        table.addCell("Made In ");
        table.addCell("Made At ");
        table.addCell("Expired At ");
        if(allProducts!=null){
            for(Product product: allProducts){
                table.addCell(product.getProductId()+" ");
                table.addCell(product.getProductName());
                table.addCell(product.getPrice()+" ");
                table.addCell(product.getCompany());
                table.addCell(product.getMadeIn());
                table.addCell(product.getMadeAt()+" ");
                table.addCell(product.getExpiredAt()+" ");

            }
            table.addCell("Total Product: ",2);
            table.addCell(allProducts.size()+" Product ", 5);

        }else {
            table.addCell("There is no product to show!",cellStyle,2);

        }


        System.out.println(table.render());
    }
}
