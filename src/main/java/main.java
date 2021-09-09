import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.apache.pdfbox.pdmodel.interactive.form.PDVariableText;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) throws IOException {
        //Loading an existing document
        File file = new File("E:\\Utils\\BMBNHS_PRO_KH giao.pdf");
        PDDocument document = PDDocument.load(file);

        PDFont formFont = PDType0Font.load(document, new FileInputStream("E:\\Utils\\src\\main\\resources\\timesbd.ttf"), false); // check that the font has what you need; ARIALUNI.TTF is good but huge

        PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();

        PDResources res = acroForm.getDefaultResources(); // could be null, if so, then create it with the setter
        String fontName = res.add(formFont).getName();
        String defaultAppearanceString = "/" + fontName + " 0 Tf 0 g"; // adjust to replace existing font name

        PDTextField textField = (PDTextField) acroForm.getField("cus_full_name");

        textField.setDefaultAppearance(defaultAppearanceString);

        textField.setValue("ĐÀO MẠNH");

        document.save("E:\\Utils\\456.pdf");

        //Closing the document
        document.close();
    }



}

