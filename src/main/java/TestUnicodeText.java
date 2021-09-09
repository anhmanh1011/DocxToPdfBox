///*
// * Licensed to the Apache Software Foundation (ASF) under one or more
// * contributor license agreements.  See the NOTICE file distributed with
// * this work for additional information regarding copyright ownership.
// * The ASF licenses this file to You under the Apache License, Version 2.0
// * (the "License"); you may not use this file except in compliance with
// * the License.  You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//import org.apache.pdfbox.cos.COSStream;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.font.PDType1Font;
//
//import java.io.*;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * Tests that we can create PDFs containing unicode text,
// * then extract the text back out again without getting
// * the encoding/accents corrupted
// */
//public class TestUnicodeText {
//    private static final String TEXT_ASCII =
//            "This is some regular text. It should all be expressable in ASCII";
//    /**
//     * En français où les choses sont accentués. En español, así
//     */
//    private static final String TEXT_8BIT =
//            "En fran\u00e7ais o\u00f9 les choses sont accentu\u00e9s. En espa\u00f1ol, as\u00ed";
//    /**
//     * をクリックしてく
//     */
//    private static final String TEXT_HIGHBITS =
//            "\u3092\u30af\u30ea\u30c3\u30af\u3057\u3066\u304f";
//    private static final Pattern p = Pattern.compile("\\s+$", Pattern.DOTALL);
//
//    private static Map<Reader, String> buildReaders() {
//        Map<Reader, String> readers = new HashMap<Reader, String>();
//
//        // From the real strings
//        readers.put(new StringReader(TEXT_ASCII), TEXT_ASCII);
//        readers.put(new StringReader(TEXT_8BIT), TEXT_8BIT);
//        readers.put(new StringReader(TEXT_HIGHBITS), TEXT_HIGHBITS);
//
//        // Via input streams from the bytes
//        readers.put(buildReader(TEXT_ASCII, "ASCII"), TEXT_ASCII);
//        for (String encoding : new String[]{"ISO-8859-1", "ISO-8859-15"}) {
//            readers.put(buildReader(TEXT_ASCII, encoding), TEXT_ASCII);
//            readers.put(buildReader(TEXT_8BIT, encoding), TEXT_8BIT);
//        }
//        for (String encoding : new String[]{"UTF-8", "UnicodeBig", "UnicodeLittle"}) {
//            readers.put(buildReader(TEXT_ASCII, encoding), TEXT_ASCII);
//            readers.put(buildReader(TEXT_8BIT, encoding), TEXT_8BIT);
//            readers.put(buildReader(TEXT_HIGHBITS, encoding), TEXT_HIGHBITS);
//        }
//
//        // All done
//        return readers;
//    }
//
//    private static Reader buildReader(String text, String encoding) {
//        try {
//            byte[] bytes = text.getBytes(encoding);
//            return new InputStreamReader(new ByteArrayInputStream(bytes), encoding);
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException("Broken JVM - core encoding " + encoding + " not found!");
//        }
//    }
//
//    private static String cleanText(String text) {
//        Matcher m = p.matcher(text);
//        if (m.find()) {
//            return m.replaceFirst("");
//        }
//        return text;
//    }
//
//    /**
//     * This test ensures that we can get the text back from the
//     * Readers, i.e. nothing got lost building them.
//     */
//    public void testReaders() throws Exception {
//        Map<Reader, String> readers = buildReaders();
//        for (Reader reader : readers.keySet()) {
//            String exp = readers.get(reader);
//
//            char[] c = new char[exp.length() * 2];
//            int len = reader.read(c);
//            assertEquals(exp.length(), len);
//
//            String found = new String(c, 0, len);
//            assertEquals(exp, found);
//        }
//    }
//
//    public void testRoundTripUnicodeText() throws Exception {
//        PDType1Font font = PDType1Font.getStandardFont("Times-Roman");
//        assertNotNull(font.getFontDescriptor());
//
//        TextToPDF generator = new TextToPDF();
//        generator.setFont(font);
//        generator.setFontSize(20);
//
//        Map<Reader, String> readers = buildReaders();
//        for (Reader reader : readers.keySet()) {
//            String exp = readers.get(reader);
//
//            // Build the PDF from the reader
//            PDDocument gen = generator.createPDFFromText(reader);
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            gen.save(out);
//            gen.close();
//
//            // Open it up again
//            ByteArrayInputStream inp = new ByteArrayInputStream(out.toByteArray());
//            PDDocument read = PDDocument.load(inp);
//
//
//            // Check via the dictionary
//            List<PDPage> pages = read.getDocumentCatalog().getAllPages();
//            assertEquals(1, pages.size());
//            PDPage page = pages.get(0);
//            COSStream contents = page.getContents().getStream();
//            // TODO
//
//            // Check via the text stripper
//            PDFTextStripper textStripper = new PDFTextStripper();
//            String found = textStripper.getText(read);
//            found = cleanText(found);
//
//            // Remove trailing whitespace and check
//            assertEquals(exp, found);
//        }
//    }
//}
