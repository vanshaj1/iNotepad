import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.undo.UndoManager;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
class StudentRecordHolder extends WindowAdapter implements ActionListener , KeyListener{
  JFrame f ;
  JMenuBar menubar;
  JMenu taskbar,edit,Format,color,Help,FontStyle,FontSize;
  JMenuItem exit,search,cut,copy,paste,save,saveas,selectAll,WordWrap,Arial,ComicSans,TimeNewRoman,Font8,Font12,Font16,Font20,Font24,Font28,Red,Blue,Default,Black,Grey,Undo,Redo;
  FileDialog fg;
  Label label1;
  JDialog dg;
  JTextArea textArea1;
  JScrollPane ScrollPane;
  String file = null;
  String filePath = null;
  int fsize = 11;
  String fname = " Consolas";
  boolean wrap = false;
  UndoManager um;
  
  StudentRecordHolder(){
    f = new JFrame("Notepad");
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
    menubar = new JMenuBar();
	
	taskbar = new JMenu("Taskbar");
	search = new JMenuItem("search");
	exit = new JMenuItem("exit");
	save = new JMenuItem("Save");
	saveas = new JMenuItem("SaveAs");
	taskbar.add(search);
	taskbar.add(exit);
    taskbar.add(save);
	taskbar.add(saveas);
    
    edit = new JMenu("edit");
	cut = new JMenuItem("cut     Ctrl+X");
	Undo = new JMenuItem("undo    Ctrl+z");
	Redo = new JMenuItem("redo     Ctrl+del");
	copy = new JMenuItem("copy    Ctrl+C");
	paste = new JMenuItem("paste	  Ctrl+V");
	selectAll = new JMenuItem("SelectAll   Ctrl+A");
	
	edit.add(cut);
	edit.add(copy);
	edit.add(paste);
	edit.add(Undo);
	edit.add(Redo);
	edit.add(selectAll);
	
	
	Format = new JMenu("Format");
	WordWrap = new JMenuItem("WordWrap:  off");
	WordWrap.setActionCommand("WordWrap");
	Format.add(WordWrap);
	
	FontStyle = new JMenu("FontStyle");
	Arial = new JMenuItem("Arial");
	ComicSans = new JMenuItem("ComicSans");
	TimeNewRoman = new JMenuItem("TimeNewRoman");
	FontStyle.add(Arial);
	FontStyle.add(ComicSans);
	FontStyle.add(TimeNewRoman);
	Format.add(FontStyle);
	
	FontSize = new JMenu("FontSize");
	Font8 = new JMenuItem("8");
	Font12= new JMenuItem("12");
	Font16 = new JMenuItem("16");
	Font20 = new JMenuItem("20");
	Font24= new JMenuItem("24");
	Font28 = new JMenuItem("28");
	FontSize.add(Font8);
	FontSize.add(Font12);
	FontSize.add(Font16);
	FontSize.add(Font20);
	FontSize.add(Font24);
	FontSize.add(Font28);
	Format.add(FontSize);
	
	
	
	color = new JMenu("Color");
	Red = new JMenuItem("Red");
	Blue = new JMenuItem("Blue");
	Default = new JMenuItem("Default");
	Black = new JMenuItem("Black");
	Grey = new JMenuItem("Grey");
	color.add(Red);
	color.add(Blue);
	color.add(Black);
	color.add(Grey);
	color.add(Default);
	
	Help = new JMenu("Help");
	
	menubar.add(taskbar);
	menubar.add(edit);
	menubar.add(Format);
	menubar.add(color);
	menubar.add(Help);
    menubar.setBackground(Color.white);
	label1 = new Label("", Label.CENTER);
	
	
	f.add(label1,BorderLayout.CENTER);
	
    
	 um = new UndoManager();
	textArea1 = new JTextArea();
	textArea1.getDocument().addUndoableEditListener(
	new UndoableEditListener() {
		public void undoableEditHappened(UndoableEditEvent e){
			um.addEdit(e.getEdit());
		}
	});
	textArea1.setForeground(Color.RED);
	ScrollPane = new JScrollPane(textArea1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	ScrollPane.setBorder(BorderFactory.createEmptyBorder());
	f.setJMenuBar(menubar);
	Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\hp\\Desktop\\java app icon\\iNotepadIcon.png");
	f.setIconImage(icon);
	f.add(ScrollPane);
	
	f.pack();	
    f.setSize(600,500);
    f.setVisible(true);
	f.addKeyListener(this);
    exit.addActionListener(this);
    search.addActionListener(this);
	save.addActionListener(this);
	saveas.addActionListener(this);
	cut.addActionListener(this); 
	copy.addActionListener(this);  
	paste.addActionListener(this);  
	selectAll.addActionListener(this);
	WordWrap.addActionListener(this);
	Arial.addActionListener(this);
	ComicSans.addActionListener(this);
	TimeNewRoman.addActionListener(this);
	Font8.addActionListener(this);
	Font12.addActionListener(this);
	Font16.addActionListener(this);
	Font20.addActionListener(this);
	Font24.addActionListener(this);
	Font28.addActionListener(this);
	Red.addActionListener(this); 
	Blue.addActionListener(this);
	Default.addActionListener(this);
	Black.addActionListener(this);
	Grey.addActionListener(this);
    Undo.addActionListener(this);
	Redo.addActionListener(this);
	textArea1.addKeyListener(this);
 }
  public void actionPerformed(ActionEvent ae){
	  if(ae.getActionCommand().equals("search")){
		   fg = new FileDialog(f,"open a file");
		   fg.setVisible(true);
		   file =  fg.getFile();
		   f.setTitle(file);
		   filePath = fg.getDirectory();
		   textArea1.setText("");
		   
		     
			try{
		  
		    BufferedReader bofile = new BufferedReader(new FileReader(filePath+file));
			String line = null;
			while((line = bofile.readLine()) != null){
				textArea1.append(line + "\n");
			}
			bofile.close();
			}catch(Exception e){
				System.out.println(e);
			}
		   
		   }
			
	  
	  if(ae.getActionCommand().equals("Save"))
		{  
	          if(fg.getFile() != null){
				try{
			   FileWriter fwr = new FileWriter(filePath+file);
			   fwr.write(textArea1.getText());
			   f.setTitle(file);
			   fwr.close();
			   }catch(Exception e){
				   System.out.println("error");
			   }  
			  }

		   
		}
		if(ae.getActionCommand().equals("SaveAs"))
		{
		   fg = new FileDialog(f,"Save",FileDialog.SAVE);
		   fg.setVisible(true);
	       file = fg.getFile();
		   filePath = fg.getDirectory();
		   f.setTitle(file);
		   try{
			   FileWriter fwr = new FileWriter(filePath+file);
			   fwr.write(textArea1.getText());
			   fwr.close();
		   }catch(Exception e){
			   System.out.println("error");
		   }
		   
		}
		if(ae.getActionCommand().equals("WordWrap")){
			if(wrap == false){
				wrap=true;
				textArea1.setLineWrap(true);
				textArea1.setWrapStyleWord(true);
				WordWrap.setText("WordWrap:  On");
			}else{
				wrap=false;
				textArea1.setLineWrap(false);
				textArea1.setWrapStyleWord(false);
				WordWrap.setText("WordWrap:  Off");
			}
		}
		if(ae.getActionCommand().equals("Arial")){
			fname = "Arial";
			textArea1.setFont(new Font(fname,Font.PLAIN,fsize));
		}
		if(ae.getActionCommand().equals("ComicSans")){
			fname = "Comic Sans MS";
			textArea1.setFont(new Font(fname,Font.PLAIN,fsize));
		}
		if(ae.getActionCommand().equals("TimeNewRoman")){
			fname = "Times New Roman";
			textArea1.setFont(new Font(fname,Font.PLAIN,fsize));
		}
		if(ae.getActionCommand().equals("8")){
			fsize = 8;
			textArea1.setFont(new Font(fname,Font.PLAIN,fsize));
		}
		if(ae.getActionCommand().equals("12")){
			fsize = 12;
			textArea1.setFont(new Font(fname,Font.PLAIN,fsize));
		}
		if(ae.getActionCommand().equals("16")){
			fsize = 16;
			textArea1.setFont(new Font(fname,Font.PLAIN,fsize));
		}
		if(ae.getActionCommand().equals("20")){
			fsize = 20;
			textArea1.setFont(new Font(fname,Font.PLAIN,fsize));
		}
		if(ae.getActionCommand().equals("24")){
			fsize = 24;
			textArea1.setFont(new Font(fname,Font.PLAIN,fsize));
		}
		if(ae.getActionCommand().equals("28")){
			fsize = 28;
			textArea1.setFont(new Font(fname,Font.PLAIN,fsize));
		}
		if(ae.getActionCommand().equals("Red")){
			textArea1.setBackground(Color.red);
			textArea1.setForeground(Color.white);
		}
		if(ae.getActionCommand().equals("Blue")){
			textArea1.setBackground(Color.blue);
			textArea1.setForeground(Color.white);
		}
		if(ae.getActionCommand().equals("Grey")){
			textArea1.setBackground(new Color(209, 209, 209));
			textArea1.setForeground(Color.red);
		}
		if(ae.getActionCommand().equals("Black")){
			textArea1.setBackground(Color.black);
			textArea1.setForeground(Color.white);
		}
		if(ae.getActionCommand().equals("Default")){
			textArea1.setBackground(Color.white);
			textArea1.setForeground(Color.black);
		}
		if(ae.getSource() == Undo){
			um.undo();
		}
		if(ae.getSource() == Redo){
			um.redo();
		}
		
	  if(ae.getActionCommand().equals("exit")){
		  System.exit(0);
	  }
	  if (ae.getSource() == cut)  
            textArea1.cut();  
      if (ae.getSource() == paste)  
          textArea1.paste();  
      if (ae.getSource() == copy)  
          textArea1.copy();  
      if (ae.getSource() == selectAll)  
           textArea1.selectAll();  
  }
  public void windowClosing(WindowEvent we){
	  dg.setVisible(false);
  }
  
  public void keyPressed(KeyEvent ke) {
	  if(ke.isControlDown() && ke.getKeyCode() == KeyEvent.VK_S){
		  fg = new FileDialog(f,"Save",FileDialog.SAVE);
		   fg.setVisible(true);
	       file = fg.getFile();
		   filePath = fg.getDirectory();
		   f.setTitle(file);
		   try{
			   FileWriter fwr = new FileWriter(filePath+file);
			   fwr.write(textArea1.getText());
			   fwr.close();
		   }catch(Exception e){
			   System.out.println("error");
		   }
	  }
	  if(ke.isShiftDown() && ke.getKeyCode() == KeyEvent.VK_S){
		   if(fg.getFile() != null){
				try{
			   FileWriter fwr = new FileWriter(filePath+file);
			   fwr.write(textArea1.getText());
			   f.setTitle(file);
			   fwr.close();
			   }catch(Exception e){
				   System.out.println("error");
			   }  
			  }
	  }
	  if(ke.isControlDown() && ke.getKeyCode() == KeyEvent.VK_Z){
		  um.undo();
		  
	  }
  }
  public void keyReleased(KeyEvent ke)
	{
	}

	public void keyTyped(KeyEvent ke)
	{
	
	}
 public static void main(String[] args){
   StudentRecordHolder stud = new StudentRecordHolder();
 }
}
