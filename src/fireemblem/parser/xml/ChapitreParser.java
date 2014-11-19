package fireemblem.parser.xml;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class ChapitreParser extends DefaultHandler {

    private Attributes mCurrentAttList = null;

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        this.mCurrentAttList = atts;
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        
    }
}
