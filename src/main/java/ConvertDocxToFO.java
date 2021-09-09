//import org.docx4j.Docx4J;
//import org.docx4j.convert.out.FOSettings;
//import org.docx4j.convert.out.pdf.PdfConversion;
//import org.docx4j.convert.out.pdf.viaXSLFO.PdfSettings;
//import org.docx4j.fonts.IdentityPlusMapper;
//import org.docx4j.fonts.Mapper;
//import org.docx4j.model.fields.FieldUpdater;
//import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
//
//import java.io.*;
//
//public class ConvertDocxToFO {
//    public static void main(String[] args) throws Exception {
//
//        String docInputStream = "E:\\Utils\\src\\main\\resources\\BMBNHS_PNOTE_KH giao.xml";
//        InputStream docxInputStream = new FileInputStream(docInputStream);
//
//        WordprocessingMLPackage tmpPkg = null;
//
//        tmpPkg = WordprocessingMLPackage.load(docxInputStream);
//
////        MainDocumentPart documentPart = tmpPkg.getMainDocumentPart();
////
////        HashMap<String, String> mappings = new HashMap<>();
////        mappings.put("contratante", "Omar Mota");
////        mappings.put("naturalidade", "Goiás-GO");
////        mappings.put("nacionalidade", "Brasileiro");
//
////        documentPart.variableReplace(mappings);
//        // Refresh the values of DOCPROPERTY fields
//        FieldUpdater updater = new FieldUpdater(tmpPkg);
//        updater.update(true);
//
//        // Set up font mapper (optional)
//        Mapper fontMapper = new IdentityPlusMapper();
//        tmpPkg.setFontMapper(fontMapper);
//
//        // FO exporter setup (required)
//        // .. the FOSettings object
//        final FOSettings foSettings = Docx4J.createFOSettings();
//        foSettings.setWmlPackage(tmpPkg);
//
//        // Document format:
//        // The default implementation of the FORenderer that uses Apache Fop will output
//        // a PDF document if nothing is passed via
//        foSettings.setApacheFopMime(FOSettings.MIME_PDF);
//        // apacheFopMime can be any of the output formats defined in org.apache.fop.apps.MimeConstants eg org.apache.fop.apps.MimeConstants.MIME_FOP_IF or
//        // FOSettings.INTERNAL_FO_MIME if you want the fo document as the result.
//        //foSettings.setApacheFopMime(FOSettings.INTERNAL_FO_MIME);
//
//        // Specify whether PDF export uses XSLT or not to create the FO
//        // (XSLT takes longer, but is more complete).
//
////      // Save it
////      if (true) {
////          SaveToZipFile saver = new SaveToZipFile(tmpPkg);
////          saver.save(outputFile);
////      } else {
////          System.out.println(XmlUtils.marshaltoString(documentPart.getJaxbElement(), true,
////                  true));
////      }
//
//        PdfSettings pdfSettings = new PdfSettings();
//        OutputStream out = new FileOutputStream(new File("E:\\Utils\\456.pdf"));
//        PdfConversion converter = new org.docx4j.convert.out.pdf.viaXSLFO.Conversion(tmpPkg);
//        converter.output(out, pdfSettings);
//    }
//}
