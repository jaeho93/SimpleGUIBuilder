import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

class MainFrame implements ActionListener{
   
   
   private JFrame frm = new JFrame();                         // ���� ������      ����
   private Container contentPane = frm.getContentPane();	  // �����ӿ� �����̳� ����
   private JPanel aPanel  = new JPanel();         			  // Ư�� �г�     ����
   private JPanel ePanel  = new JPanel();                     // ������ �г�     ����
   private JMenuBar menuBar        = new JMenuBar();          // �޴���          ����
   private JToolBar toolBar     = new JToolBar();             // ����        ����
   private JMenu fileMenu           = new JMenu("����");				// "����"  �޴�  ����
   private JMenuItem newItem     = new JMenuItem("���� �����");		// ����-���� �����         �Ŵ������� ����
   private JMenuItem openItem     = new JMenuItem("����");			// ����-����              �޴������� ����
   private JMenuItem saveItem     = new JMenuItem("����");          // ����-����                 �޴������� ����
   private JMenuItem saveAsItem  = new JMenuItem("�ٸ� �̸����� ����");   // ����-�ٸ� �̸����� ���� �޴������� ����
   private JMenuItem toJavaItem  = new JMenuItem(".java ���� ����");   // .java���� ����        �޴������� ����
   private JMenuItem exitItem      = new JMenuItem("�ݱ�");         // ����-������           �޴������� ����
   private JButton newButton     = new JButton("���� �����");         // ����-���� �����         ��ư ����
   private JButton openButton     = new JButton("����");            // ����-����              ��ư ����
   private JButton saveButton     = new JButton("����");            // ����-����                 ��ư ����
   private JButton saveAsButton  = new JButton("�ٸ� �̸����� ����");      // ����-�ٸ� �̸����� ���� ��ư ����
   private JButton toJavaButton  = new JButton(".java ���� ����");      // .java���� ����        ��ư ����
   private JButton exitButton     = new JButton("�ݱ�");            // ����-������           ��ư ����
   private JLabel stateLabel     = new JLabel("����ǥ����");         // �ϴ� ����ǥ���� ����
   private JButton applyButton = new JButton("����");
   JTextField xField = new JTextField(10);
   JTextField yField = new JTextField(10);
   JTextField wField = new JTextField(10);
   JTextField hField = new JTextField(10);
   JTextField taField = new JTextField(100);
   JTextField ttField = new JTextField(100);
   JTextField tvField = new JTextField(100);
   class MyPanel extends JPanel{
		
		Point startP=null;
		Point endP=null;
		
		Vector<Point> sv = new Vector<Point>(); // ����
		Vector<Point> se = new Vector<Point>(); // ����

		public MyPanel(){
			//�����ʸ� ���������ؾ�  �������� �����ȴ�.
			MyMouseListener ml = new MyMouseListener();
			
			this.addMouseListener(ml); // ������
			this.addMouseMotionListener(ml);
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g); // �θ� ����Ʈȣ��
			
			if(sv.size() != 0){
				for(int i=0;i<se.size();i++){ //����ũ�⸸ŭ
					Point sp = sv.get(i); // ���Ͱ���������
					Point ep = se.get(i);	
					g.drawRect(sp.x, sp.y, ep.x, ep.y);//�׸���
				}
			}
			if(startP != null)
				g.drawRect(startP.x, startP.y, endP.x, endP.y);				
		}
		
		class MyMouseListener extends MouseAdapter implements MouseMotionListener{
			public void mousePressed(MouseEvent e){
				startP = e.getPoint();
				sv.add(e.getPoint()); // Ŭ���Ѻκ��� ����������
			}
			public void mouseReleased(MouseEvent e){
				se.add(e.getPoint()); // �巡�� �Ѻκ��� ����������
				endP = e.getPoint();
				repaint(); // �ٽñ׷���
			}
			
			public void mouseDragged(MouseEvent e){
				endP = e.getPoint();
				stateLabel.setText("("+startP.x+", "+startP.y+", "+(endP.x+startP.x)+", "+(endP.y+startP.y)+")"+" w : "+endP.x+" h : "+endP.y);
				repaint();
				editAt(startP, endP);
			}
			
			public void mouseMoved(MouseEvent e){
				
			}
		}
	}


   public MainFrame(){
      
      //�Ӽ� ����
      
      xField.setBounds(185,190,50,20);
      xField.setHorizontalAlignment(JTextField.RIGHT);
      
      yField.setBounds(235,190,50,20);
      yField.setHorizontalAlignment(JTextField.RIGHT);
      
      wField.setBounds(185,220,50,20);
      wField.setHorizontalAlignment(JTextField.RIGHT);
      
      hField.setBounds(235,220,50,20);
      hField.setHorizontalAlignment(JTextField.RIGHT);
      
      taField.setBounds(185,250,100,20);
      taField.setHorizontalAlignment(JTextField.RIGHT);
      
      ttField.setBounds(185,280,100,20);
      ttField.setHorizontalAlignment(JTextField.RIGHT);
      
      tvField.setBounds(185,310,100,20);
      tvField.setHorizontalAlignment(JTextField.RIGHT);
      JLabel xyLabel = new JLabel("���� x, y ��ǥ", javax.swing.SwingConstants.CENTER);
      JLabel whLabel = new JLabel("�ʺ�, ����", javax.swing.SwingConstants.CENTER);
      JLabel taLabel = new JLabel("������Ʈ�� �ؽ�Ʈ �Ӽ���", javax.swing.SwingConstants.CENTER);
      JLabel ttLabel = new JLabel("������Ʈ Ÿ��", javax.swing.SwingConstants.CENTER);
      JLabel tvLabel = new JLabel("������Ʈ ������", javax.swing.SwingConstants.CENTER);
      xyLabel.setBounds(5,100,150,200);
      whLabel.setBounds(5,130,150,200);
      taLabel.setBounds(5,160,150,200);
      ttLabel.setBounds(5,190,150,200);
      tvLabel.setBounds(5,220,150,200);
      applyButton.setBounds(190, 350, 100, 50);
      aPanel.setLayout(null);
      aPanel.setBounds(0,300,300,700);
      aPanel.add(xyLabel);
      aPanel.add(whLabel);
      aPanel.add(taLabel);
      aPanel.add(ttLabel);
      aPanel.add(tvLabel);
      aPanel.add(xField);
      aPanel.add(yField);
      aPanel.add(wField);
      aPanel.add(hField);
      aPanel.add(taField);
      aPanel.add(ttField);
      aPanel.add(tvField);
      aPanel.add(applyButton);
      aPanel.setVisible(true);

      //������ ����
      ePanel.setLayout(null);;
      ePanel.setBounds(300,100,1000,800);
      ePanel.setBackground(Color.WHITE);
      ePanel.setVisible(true);
      
      MyPanel eePanel = new MyPanel();
      eePanel.setLayout(null);;
      eePanel.setBounds(300,50,1000,950);
      eePanel.setBackground(Color.WHITE);
      eePanel.setVisible(true);
      
      
      //�޴��ٿ� �޴� �߰�
      menuBar.add(fileMenu);
      
      //���� �޴��� �׸� �߰�
      fileMenu.add(newItem);
      fileMenu.add(openItem);
      fileMenu.add(saveItem);
      fileMenu.add(saveAsItem);
      fileMenu.add(toJavaItem);
      fileMenu.addSeparator(); // ���м� �߱�
      fileMenu.add(exitItem);
      
      //���ٿ� �׸� �߰�
      toolBar.add(newButton);
      toolBar.add(openButton);
      toolBar.add(saveButton);
      toolBar.add(saveAsButton);
      toolBar.add(toJavaButton);
      toolBar.addSeparator(); // ���м� �߱�
      toolBar.add(exitButton);
      toolBar.setFloatable(false); // ���� ��ܿ� ����
      
      //���ϸ޴��׸� �̺�Ʈ �ڵ鷯 ����
      newItem.addActionListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        saveAsItem.addActionListener(this);
        toJavaItem.addActionListener(this);
        exitItem.addActionListener(this);
        
        //���� �׸� �̺�Ʈ �ڵ鷯 ����
         newButton.addActionListener(this);
        openButton.addActionListener(this);
        saveButton.addActionListener(this);
        saveAsButton.addActionListener(this);
        toJavaButton.addActionListener(this);
        exitButton.addActionListener(this);
        
        applyButton.addActionListener(this);
        
        //���� �׸� ����Ű �� ���Ű �����ϱ�
        fileMenu.setMnemonic(KeyEvent.VK_F);
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
        saveAsItem.setMnemonic(KeyEvent.VK_A);
        exitItem.setMnemonic(KeyEvent.VK_X);
      
        //�����ӿ� ������Ʈ ����
        //frm.add(textArea, BorderLayout.EAST);
        frm.setJMenuBar(menuBar);
        
        //contentPane.setLayout(null);
        contentPane.add(toolBar, BorderLayout.NORTH);
        contentPane.add(eePanel);
        contentPane.add(aPanel);
        frm.add(stateLabel, BorderLayout.SOUTH);
      
        //�⺻ ������ ����
        frm.setTitle("Simple GUI Builder");
        frm.setLocation(0, 0);
        frm.setSize(1330, 1200);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);

   }
   void eApply(){
	   
   }
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(e.getSource() == newItem) //���� ����� ����
            {
                stateLabel.setText(newItem.getText() + "�� ���� �Ǿ����ϴ�");
               
                //panel.setVisible(true);
            }
            else if(e.getSource() == openItem) //���� ����
            {
                stateLabel.setText(openItem.getText() + "�� ���� �Ǿ����ϴ�");
            }
            else if(e.getSource() == saveItem) //���� ����
            {
                stateLabel.setText(saveItem.getText() + "�� ���� �Ǿ����ϴ�");
            }
            else if(e.getSource() == saveAsItem) //�ٸ� �̸����� ���� ����
            {
                stateLabel.setText(saveAsItem.getText() + "�� ���� �Ǿ����ϴ�");
            }
            else if(e.getSource() == toJavaItem) //.java ���� ���� ����
            {
                stateLabel.setText(toJavaItem.getText() + "�� ���� �Ǿ����ϴ�");
            }
            else if(e.getSource() == exitItem) //�ݱ� ��ư ����
            {
                System.exit(0);
            }
            else if(e.getSource() == newButton) //���� ����� ����
            {
                stateLabel.setText(newButton.getText() + "�� ���� �Ǿ����ϴ�");
            }
            else if(e.getSource() == openButton) //���� ����
            {
                stateLabel.setText(openButton.getText() + "�� ���� �Ǿ����ϴ�");
            }
            else if(e.getSource() == saveButton) //���� ����
            {
                stateLabel.setText(saveButton.getText() + "�� ���� �Ǿ����ϴ�");
            }
            else if(e.getSource() == saveAsButton) //�ٸ� �̸����� ���� ����
            {
                stateLabel.setText(saveAsButton.getText() + "�� ���� �Ǿ����ϴ�");
            }
            else if(e.getSource() == toJavaButton) //.java ���� ���� ����
            {
                stateLabel.setText(toJavaButton.getText() + "�� ���� �Ǿ����ϴ�");
            }
            else if(e.getSource() == exitButton) //�ݱ� ��ư ����
            {
                System.exit(0);
            }
            else if(e.getSource() == applyButton)
            {
            	stateLabel.setText(applyButton.getText() + "�� ���� �Ǿ����ϴ�");
            	ColorFrame my = new ColorFrame();
            }
            
            }
        void editAt(Point s, Point e){
        	xField.setText(Integer.toString((int)s.getX()));
        	yField.setText(Integer.toString((int)s.getY()));
        	wField.setText(Integer.toString((int)e.getX()));
        	hField.setText(Integer.toString((int)e.getY()));
        }

}

   

public class SimpleGUIBuilder{
   public static void main(String[] args) {
      new MainFrame();
   }
}
