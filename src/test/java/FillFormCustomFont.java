//import org.apache.pdfbox.cos.COSDictionary;
//import org.apache.pdfbox.cos.COSName;
//import org.apache.pdfbox.cos.COSString;
//import org.apache.pdfbox.exceptions.COSVisitorException;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
//import org.apache.pdfbox.pdmodel.PDResources;
//import org.apache.pdfbox.pdmodel.font.PDFont;
//import org.apache.pdfbox.pdmodel.font.PDType0Font;
//import org.apache.pdfbox.pdmodel.font.PDType1Font;
//import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
//import org.apache.pdfbox.pdmodel.interactive.form.PDField;
//import org.apache.pdfbox.pdmodel.interactive.form.PDTextbox;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * <a href="http://stackoverflow.com/questions/30181250/java-pdfbox-setting-custom-font-for-a-few-fields-in-pdf-form">
// * Java PDFBox setting custom font for a few fields in PDF Form
// * </a>
// * <p>
// * {@link #testSetFieldPMB_acroform()} shows how to bold with a poor-man's-bold technique.
// * {@link #testSetFieldFont_acroform()} shows how to use a custom font
// * </p>
// *
// * @author mkl
// */
//public class FillFormCustomFont {
//    final static File RESULT_FOLDER = new File("E:\\Utils\\");
//
//    @BeforeClass
//    public static void setUpBeforeClass() throws Exception {
//        RESULT_FOLDER.mkdirs();
//    }
//
//    public static void setField(PDDocument _pdfDocument, String name, String value) throws IOException {
//        PDDocumentCatalog docCatalog = _pdfDocument.getDocumentCatalog();
//        PDAcroForm acroForm = docCatalog.getAcroForm();
//        PDField field = acroForm.getField(name);
//
//        COSDictionary dict = ((PDField) field).getDictionary();
//        COSString defaultAppearance = (COSString) dict
//                .getDictionaryObject(COSName.DA);
//        if (defaultAppearance != null) {
//            dict.setString(COSName.DA, "/Helv 10 Tf 0 g");
//            if (name.equalsIgnoreCase("Field1")) {
//                dict.setString(COSName.DA, "/Helv 12 Tf 0 g");
//            }
//        }
//        if (field instanceof PDTextbox) {
//            field = new PDTextbox(acroForm, dict);
//            ((PDField) field).setValue(value);
//        }
//    }
//
//    public static void setFieldBold(PDDocument _pdfDocument, String name, String value) throws IOException {
//        PDDocumentCatalog docCatalog = _pdfDocument.getDocumentCatalog();
//        PDAcroForm acroForm = docCatalog.getAcroForm();
//        PDField field = acroForm.getField(name);
//
//        COSDictionary dict = ((PDField) field).getDictionary();
//        COSString defaultAppearance = (COSString) dict
//                .getDictionaryObject(COSName.DA);
//        if (defaultAppearance != null) {
//            dict.setString(COSName.DA, "/Helv 10 Tf 2 Tr .5 w 0 g");
//            if (name.equalsIgnoreCase("Field1")) {
//                dict.setString(COSName.DA, "/Helv 12 Tf 0 g");
//            }
//        }
//        if (field instanceof PDTextbox) {
//            field = new PDTextbox(acroForm, dict);
//            ((PDField) field).setValue(value);
//        }
//    }
//
//    public static void setField(PDDocument _pdfDocument, String name, String value, String fontName) throws IOException {
//        PDDocumentCatalog docCatalog = _pdfDocument.getDocumentCatalog();
//        PDAcroForm acroForm = docCatalog.getAcroForm();
//        PDField field = acroForm.getField(name);
//
//        COSDictionary dict = ((PDField) field).getDictionary();
//        COSString defaultAppearance = (COSString) dict
//                .getDictionaryObject(COSName.DA);
//        if (defaultAppearance != null) {
//            dict.setString(COSName.DA, "/" + fontName + " 10 Tf 0 g");
////			if (name.equalsIgnoreCase("Field1")) {
////				dict.setString(COSName.DA, "/" + fontName + " 12 Tf 0 g");
////			}
//        }
//        if (field instanceof PDTextbox) {
//
//            field = new PDTextbox(acroForm, dict);
//            ((PDField) field).setValue(value);
//        }
//    }
//
//    @Test
//    public void testSetField_acroform() throws IOException, COSVisitorException {
//        try (InputStream originalStream = getClass().getResourceAsStream("acroform.pdf")) {
//            PDDocument doc = PDDocument.load(originalStream);
//
//            setField(doc, "FirstName", "My first name");
//            setField(doc, "LastName", "My last name");
//
//            doc.save(new File(RESULT_FOLDER, "acroform-setField.pdf"));
//            doc.close();
//        }
//    }
//
//    @Test
//    public void testSetFieldPMB_acroform() throws IOException, COSVisitorException {
//        try (InputStream originalStream = getClass().getResourceAsStream("acroform.pdf")) {
//            PDDocument doc = PDDocument.load(originalStream);
//
//            setFieldBold(doc, "FirstName", "My first name");
//            setFieldBold(doc, "LastName", "My last name");
//
//            doc.save(new File(RESULT_FOLDER, "acroform-setFieldPMB.pdf"));
//            doc.close();
//        }
//    }
//
//    @Test
//    public void testSetFieldFont_acroform() throws IOException, COSVisitorException {
//        try (InputStream originalStream = getClass().getResourceAsStream("BMBNHS_PRO_KH giao.pdf")) {
//            PDDocument doc = PDDocument.load(originalStream);
//            String fontName = prepareFont(doc);
//
//            setField(doc, "cus_full_name", "Đào Đức Mạnh", fontName);
////            setField(doc, "LastName", "My last name", fontName);
//
//            doc.save(new File(RESULT_FOLDER, "acroform-setFieldFont.pdf"));
//            doc.close();
//        }
//    }
//
//    @Test
//    public void testSetFieldCustomBold_acroform() throws IOException, COSVisitorException {
//        try (InputStream originalStream = getClass().getResourceAsStream("BMBNHS_PRO_KH giao.pdf")) {
//            PDDocument doc = PDDocument.load(originalStream);
//            String fontName = prepareFont(doc);
//
//            String str = new String("Đào Đức Mạnh");
//            byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
//            setField(doc, "cus_full_name", "Đào Đức Mạnh", fontName);
////            setFieldBold(doc, "LastName", "My last name");
//
//            doc.save(new File(RESULT_FOLDER, "acroform-setFieldCustomBold.pdf"));
//            doc.close();
//        }
//    }
//
//    @Test
//    public void testSetFieldCustomStandard_acroform() throws IOException, COSVisitorException {
//        try (InputStream originalStream = getClass().getResourceAsStream("BMBNHS_PRO_KH giao.pdf")) {
//            PDDocument doc = PDDocument.load(originalStream);
//            List<String> fontNames = prepareFont(doc, Arrays.asList(PDType1Font.TIMES_ROMAN));
//
//            setField(doc, "cus_full_name", "Đào Đức Mạnh", fontNames.get(0));
//
//            doc.save(new File(RESULT_FOLDER, "acroform-setFieldCustomStandard.pdf"));
//            doc.close();
//        }
//    }
//
//    public String prepareFont(PDDocument _pdfDocument) throws IOException {
//        PDDocumentCatalog docCatalog = _pdfDocument.getDocumentCatalog();
//        PDAcroForm acroForm = docCatalog.getAcroForm();
//
//        PDResources res = acroForm.getDefaultResources();
//        if (res == null)
//            res = new PDResources();
//
//        COSName cosName = res.add(loadTrueTypeFont(_pdfDocument, "timesbd.ttf"));
//        String fontName = cosName.getName();
//        acroForm.setDefaultResources(res);
//
//        return fontName;
//    }
//
//    public PDFont loadTrueTypeFont(PDDocument _pdfDocument, String resourceName) throws IOException {
//        try (InputStream fontStream = getClass().getResourceAsStream(resourceName);) {
//            return PDType0Font.load(_pdfDocument, fontStream, false);
//        }
//    }
//
//    public List<String> prepareFont(PDDocument _pdfDocument, List<PDFont> fonts) throws IOException {
//        PDDocumentCatalog docCatalog = _pdfDocument.getDocumentCatalog();
//        PDAcroForm acroForm = docCatalog.getAcroForm();
//
//        PDResources res = acroForm.getDefaultResources();
//        if (res == null)
//            res = new PDResources();
//
//        List<String> fontNames = new ArrayList<String>();
//        for (PDFont font : fonts) {
//            fontNames.add(res.add(font).getName());
//        }
//
//        acroForm.setDefaultResources(res);
//
//        return fontNames;
//    }
//
//}
