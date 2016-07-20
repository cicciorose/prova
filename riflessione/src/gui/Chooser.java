package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * This class contains 2 method Open(..) and Save(..) for File
 * 
 * @author Francesco Rose 
 * @see JFileChooser
 * @see URL
 * @see File 
 * @since 1.0
 * 
 * */
public class Chooser {
	/**
	 * 
	 * It opens a window that allows you to view the directories and select the file to open.
	 * 
	 * @return File : the file that was opened
	 * 
	 * @param nameTypeFile Name of type of file  example(Archive,Image...) to display in the window
	 * 
	 * @param defaultPath default path where open the window or null for open in the tree root fileSystem
	 * 
	 * @param extension list of allowed extensions
	 * 
	 * @see File
	 * 
	 * */
	public static File Open(String defaultPath,String nameTypeFile, String ... extension){
		
		JFileChooser chooser = new JFileChooser(defaultPath);		  
		if(extension.length>0)
		{
			FileNameExtensionFilter filter = new FileNameExtensionFilter(nameTypeFile, extension); 
			chooser.setFileFilter(filter);
			chooser.setAcceptAllFileFilterUsed(false);
		}
		int returnVal = chooser.showOpenDialog(null);		 
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		}else{
		  //case cancel
			return null;
		  }		  
	}	
	/**
	 * It opens a window that lets you choose a path and a name of a file
	 * 
	 * @return URL url and file name to save
	 * 
	 * @param nameTypeFile Name of type of file  example(Archive,Image...) to display in the window
	 * 
	 * @param defaultPath default path where open the window or null for open in the tree root fileSystem
	 * 
	 * @param extension list of allowed extensions	 * 
	 * 
	 * @see URL
	 * 
	 * */
	public static URL Save(String defaultPath,String nameTypeFile, String ... extension) throws IOException{

		
        JFileChooser fileChooser = new JFileChooser(defaultPath);
        if(extension.length>0)
		{
        	FileNameExtensionFilter filter = new FileNameExtensionFilter(nameTypeFile, extension); 
        	fileChooser.setFileFilter(filter);
        	fileChooser.setAcceptAllFileFilterUsed(false);
		}
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
        	File file = fileChooser.getSelectedFile();
        	URL u=file.toURI().toURL();
        	return u;
        }
		return null;
	}

	
	public static void main(String[] args) throws IOException {
		 
		File f=Open( "/home/francesco/programmi/ref","Archivio jar","txt");
		//File f=Open( "/home/francesco/programmi/ref",null);no filter
		if(f==null){
			System.out.println("è null-o");
		}
		URL U=Save("/home/francesco/programmi/ref","text file","txt");
		if(U==null){
			System.out.println("è nullo");
		}else
			System.out.println(U.getPath());
	}

}
