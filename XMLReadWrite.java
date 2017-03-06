/**
 * 
 */
package com.zz.xml.startup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Administrator
 *
 */
public class XMLReadWrite {

	/**
	 * 
	 */
	private XMLReadWrite() {
		// TODO Auto-generated constructor stub
	}
	
	private static final class XMLAppSingle {
		private static final XMLReadWrite startup = new XMLReadWrite();
	}
	
	public static XMLReadWrite getInstance() {
		return XMLAppSingle.startup;
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		//getInstance().OutputXML();
		
		StringBuilder builder = new StringBuilder( System.getProperty( "user.dir" ) + File.separator + "data" + File.separator );
		//builder.append( "xml-7951f6c6-7242-4e5d-a0e6-38e0677fcc6b.xml" );
		builder.append( "xml-5e1f1ff8-7586-4030-a6b3-915ce69f9bbe.xml" );
		String path = builder.toString();
		
		getInstance().ReadXML( path );
		
//		getInstance().ReadItem( path, "http://pan.baidu.com/disk/home#path=%252FCode" );
	}
	
	public void ReadItem( String xmlPath, String uuid ) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		this.ReadItem( new File( xmlPath ), uuid );
	}
	public void ReadItem( File xmlFile, String uuid ) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document document = builder.parse( xmlFile );
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expression = xpath.compile( "/items/item[@UUID='" + uuid + "']" );
		Node node = (Node) expression.evaluate( document, XPathConstants.NODE );
		System.out.println( node.getNodeName() + " --> " + node.getTextContent() );
	}
	
	/**
	 * 
	 * @param xmlFile
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public void ReadXML( String xmlPath ) throws ParserConfigurationException, SAXException, IOException {
		this.ReadXML( new File( xmlPath ) );
	}
	
	/**
	 * 
	 * @param xmlFile
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public void ReadXML( File xmlFile ) throws ParserConfigurationException, SAXException, IOException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document document = builder.parse( xmlFile );
		
		Element items = document.getDocumentElement();
		
		NodeList list = items.getElementsByTagName( "tag" );
		for ( int i = 0, l = list.getLength(); l > i; ++i ) {
			Node item = list.item( i );
			System.out.println( item.getTextContent() + " --> " + item.getParentNode().getNodeName() );
		}
		
		for ( Node item : this.ItemNodes( items ) ) {
			System.out.println( item.getNodeName() + " --> " + item.getNodeType() + " ==> " + item.getTextContent() );
		}
	}
	
	private List<Node> ItemNodes( Element root ) {
		
		List<Node> nodes = new ArrayList<Node>( 10 );
		
		NodeList list = root.getChildNodes();
		for ( int i = 0, l = list.getLength(); l > i; ++i ) {
			Node item = list.item( i );
			if ( item.getNodeType() == Node.ELEMENT_NODE && item.getNodeName().equals( "item" ) ) {
				nodes.add( item );
			}
		}
		
		return nodes;
	}
	
	/**
	 * 
	 * @throws ParserConfigurationException
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerException
	 * @return
	 */
	public String OutputXML() throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		
		String path = System.getProperty( "user.dir" ) + File.separator + "data" + File.separator;
		File xmlFile = new File( path, "xml-" + UUID.randomUUID() + ".xml" );
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document document = builder.newDocument();
		{
			Element root = document.createElement( "items" );
			{
				for ( byte i = 0, l = 10; l > i; ++i )
					root.appendChild( this.CreateItemNode( document ) );
			}
			document.appendChild( root );
		}
		
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty( OutputKeys.INDENT, "yes" );
		transformer.setOutputProperty( OutputKeys.METHOD, "xml" );
		transformer.setOutputProperty( OutputKeys.ENCODING, "UTF-8" );
		transformer.setOutputProperty( OutputKeys.STANDALONE, "yes" );
		transformer.setOutputProperty( OutputKeys.VERSION, "1.0" );
		transformer.transform( new DOMSource( document ), new StreamResult( xmlFile ) );
		
		return xmlFile.getAbsolutePath();
	}
	
	/**
	 * 
	 * @param document
	 * @return
	 */
	private Element CreateItemNode( Document document ) {
		
		Element item = document.createElement( "item" );
		item.setAttribute( "UUID", UUID.randomUUID().toString() );
		
		item.appendChild( this.CreateTextNode( document, "date", Math.random() + "" ) );
		item.appendChild( this.CreateTextNode( document, "time", new Random().nextLong() + "" ) );
		item.appendChild( this.CreateTextNode( document, "text", System.currentTimeMillis() + "" ) );
		item.appendChild( this.CreateTextNode( document, "tag", "<tag>value</tag>" ) );
		item.appendChild( this.CreateTextNode( document, "url", "http://pan.baidu.com/disk/home#path=%252FCode" ) );
		item.appendChild( this.CreateTextNode( document, "sign", "`~1!@#$%^&*()_+=-{}[]/\\|\"" ) );
		
		return item;
	}
	
	/**
	 * 
	 * @param document
	 * @param tag
	 * @param value
	 * @return
	 */
	private Element CreateTextNode( Document document, String tag, String value ) {
		Element element = document.createElement( tag );
		element.appendChild( document.createTextNode( value ) );
		return element;
	}

}
