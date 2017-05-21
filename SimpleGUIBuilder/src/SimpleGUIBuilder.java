import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

class MainFrame implements ActionListener{
   
   
   private JFrame frm = new JFrame();                         // 메인 프레임      생성
   private Container contentPane = frm.getContentPane();	  // 프레임에 컨테이너 생성
   private JPanel aPanel  = new JPanel();         			  // 특성 패널     생성
   private JPanel ePanel  = new JPanel();                     // 에디터 패널     생성
   private JMenuBar menuBar        = new JMenuBar();          // 메뉴바          생성
   private JToolBar toolBar     = new JToolBar();             // 툴바        생성
   private JMenu fileMenu           = new JMenu("파일");				// "파일"  메뉴  생성
   private JMenuItem newItem     = new JMenuItem("새로 만들기");		// 파일-새로 만들기         매뉴아이템 생성
   private JMenuItem openItem     = new JMenuItem("열기");			// 파일-열기              메뉴아이템 생성
   private JMenuItem saveItem     = new JMenuItem("저장");          // 파일-저장                 메뉴아이템 생성
   private JMenuItem saveAsItem  = new JMenuItem("다른 이름으로 저장");   // 파일-다른 이름으로 저장 메뉴아이템 생성
   private JMenuItem toJavaItem  = new JMenuItem(".java 파일 생성");   // .java파일 생성        메뉴아이템 생성
   private JMenuItem exitItem      = new JMenuItem("닫기");         // 파일-끝내기           메뉴아이템 생성
   private JButton newButton     = new JButton("새로 만들기");         // 파일-새로 만들기         버튼 생성
   private JButton openButton     = new JButton("열기");            // 파일-열기              버튼 생성
   private JButton saveButton     = new JButton("저장");            // 파일-저장                 버튼 생성
   private JButton saveAsButton  = new JButton("다른 이름으로 저장");      // 파일-다른 이름으로 저장 버튼 생성
   private JButton toJavaButton  = new JButton(".java 파일 생성");      // .java파일 생성        버튼 생성
   private JButton exitButton     = new JButton("닫기");            // 파일-끝내기           버튼 생성
   private JLabel stateLabel     = new JLabel("상태표시줄");         // 하단 상태표시줄 생성
   private JButton applyButton = new JButton("적용");
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
		
		Vector<Point> sv = new Vector<Point>(); // 시작
		Vector<Point> se = new Vector<Point>(); // 끝점

		public MyPanel(){
			//리스너를 공통으로해야  변수들이 공유된다.
			MyMouseListener ml = new MyMouseListener();
			
			this.addMouseListener(ml); // 리스너
			this.addMouseMotionListener(ml);
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g); // 부모 페인트호출
			
			if(sv.size() != 0){
				for(int i=0;i<se.size();i++){ //벡터크기만큼
					Point sp = sv.get(i); // 벡터값을꺼내다
					Point ep = se.get(i);	
					g.drawRect(sp.x, sp.y, ep.x, ep.y);//그리다
				}
			}
			if(startP != null)
				g.drawRect(startP.x, startP.y, endP.x, endP.y);				
		}
		
		class MyMouseListener extends MouseAdapter implements MouseMotionListener{
			public void mousePressed(MouseEvent e){
				startP = e.getPoint();
				sv.add(e.getPoint()); // 클릭한부분을 시작점으로
			}
			public void mouseReleased(MouseEvent e){
				se.add(e.getPoint()); // 드래그 한부분을 종료점으로
				endP = e.getPoint();
				repaint(); // 다시그려라
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
      
      //속성 페인
      
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
      JLabel xyLabel = new JLabel("시작 x, y 좌표", javax.swing.SwingConstants.CENTER);
      JLabel whLabel = new JLabel("너비, 높이", javax.swing.SwingConstants.CENTER);
      JLabel taLabel = new JLabel("컴포넌트의 텍스트 속성값", javax.swing.SwingConstants.CENTER);
      JLabel ttLabel = new JLabel("컴포넌트 타입", javax.swing.SwingConstants.CENTER);
      JLabel tvLabel = new JLabel("컴포넌트 변수명", javax.swing.SwingConstants.CENTER);
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

      //에디터 페인
      ePanel.setLayout(null);;
      ePanel.setBounds(300,100,1000,800);
      ePanel.setBackground(Color.WHITE);
      ePanel.setVisible(true);
      
      MyPanel eePanel = new MyPanel();
      eePanel.setLayout(null);;
      eePanel.setBounds(300,50,1000,950);
      eePanel.setBackground(Color.WHITE);
      eePanel.setVisible(true);
      
      
      //메뉴바에 메뉴 추가
      menuBar.add(fileMenu);
      
      //파일 메뉴에 항목 추가
      fileMenu.add(newItem);
      fileMenu.add(openItem);
      fileMenu.add(saveItem);
      fileMenu.add(saveAsItem);
      fileMenu.add(toJavaItem);
      fileMenu.addSeparator(); // 구분섯 긋기
      fileMenu.add(exitItem);
      
      //툴바에 항목 추가
      toolBar.add(newButton);
      toolBar.add(openButton);
      toolBar.add(saveButton);
      toolBar.add(saveAsButton);
      toolBar.add(toJavaButton);
      toolBar.addSeparator(); // 구분섯 긋기
      toolBar.add(exitButton);
      toolBar.setFloatable(false); // 툴바 상단에 고정
      
      //파일메뉴항목 이벤트 핸들러 장착
      newItem.addActionListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        saveAsItem.addActionListener(this);
        toJavaItem.addActionListener(this);
        exitItem.addActionListener(this);
        
        //툴바 항목 이벤트 핸들러 장착
         newButton.addActionListener(this);
        openButton.addActionListener(this);
        saveButton.addActionListener(this);
        saveAsButton.addActionListener(this);
        toJavaButton.addActionListener(this);
        exitButton.addActionListener(this);
        
        applyButton.addActionListener(this);
        
        //파일 항목에 가속키 및 기억키 설정하기
        fileMenu.setMnemonic(KeyEvent.VK_F);
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
        saveAsItem.setMnemonic(KeyEvent.VK_A);
        exitItem.setMnemonic(KeyEvent.VK_X);
      
        //프레임에 컴포넌트 장착
        //frm.add(textArea, BorderLayout.EAST);
        frm.setJMenuBar(menuBar);
        
        //contentPane.setLayout(null);
        contentPane.add(toolBar, BorderLayout.NORTH);
        contentPane.add(eePanel);
        contentPane.add(aPanel);
        frm.add(stateLabel, BorderLayout.SOUTH);
      
        //기본 프레임 셋팅
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
            if(e.getSource() == newItem) //새로 만들기 선택
            {
                stateLabel.setText(newItem.getText() + "가 선택 되었습니다");
               
                //panel.setVisible(true);
            }
            else if(e.getSource() == openItem) //열기 선택
            {
                stateLabel.setText(openItem.getText() + "가 선택 되었습니다");
            }
            else if(e.getSource() == saveItem) //저장 선택
            {
                stateLabel.setText(saveItem.getText() + "이 선택 되었습니다");
            }
            else if(e.getSource() == saveAsItem) //다른 이름으로 저장 선택
            {
                stateLabel.setText(saveAsItem.getText() + "이 선택 되었습니다");
            }
            else if(e.getSource() == toJavaItem) //.java 파일 생성 선택
            {
                stateLabel.setText(toJavaItem.getText() + "이 선택 되었습니다");
            }
            else if(e.getSource() == exitItem) //닫기 버튼 선택
            {
                System.exit(0);
            }
            else if(e.getSource() == newButton) //새로 만들기 선택
            {
                stateLabel.setText(newButton.getText() + "가 선택 되었습니다");
            }
            else if(e.getSource() == openButton) //열기 선택
            {
                stateLabel.setText(openButton.getText() + "가 선택 되었습니다");
            }
            else if(e.getSource() == saveButton) //저장 선택
            {
                stateLabel.setText(saveButton.getText() + "이 선택 되었습니다");
            }
            else if(e.getSource() == saveAsButton) //다른 이름으로 저장 선택
            {
                stateLabel.setText(saveAsButton.getText() + "이 선택 되었습니다");
            }
            else if(e.getSource() == toJavaButton) //.java 파일 생성 선택
            {
                stateLabel.setText(toJavaButton.getText() + "이 선택 되었습니다");
            }
            else if(e.getSource() == exitButton) //닫기 버튼 선택
            {
                System.exit(0);
            }
            else if(e.getSource() == applyButton)
            {
            	stateLabel.setText(applyButton.getText() + "이 선택 되었습니다");
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
