package com.myf.gobang1030;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DrawFrame {

	   
	
	
		public static void main(String[] args) {
			DrawFrame frame = new DrawFrame();
			frame.showUI();
		}

		public void showUI() {
			// �����������
			javax.swing.JFrame jf = new javax.swing.JFrame();
			
			ImageIcon img = new ImageIcon("E:\\workspace\\mayifan\\src\\com\\myf\\gobang1030\\timg.jpg");
			JLabel jla = new JLabel(img);
			jf.getLayeredPane().add(jla, new Integer(Integer.MIN_VALUE));
			jla.setBounds(0, 0, 950, 640);
			JPanel jp1 = (JPanel) jf.getContentPane();
			jp1.setOpaque(false); 
			
			jf.setSize(950, 640);
			//jf.getContentPane().setBackground(Color.ORANGE);//���ñ���ɫ
			jf.setTitle("������");
			jf.setDefaultCloseOperation(3);
			// ���þ�����ʾ
			jf.setLocationRelativeTo(null);
			
			DrawMouse mouse = new DrawMouse();
			
			//JPanel jp= new JPanel();
			//jp.setBackground(Color.white);
			//jp.setBounds(x, y, width, height);
			//jp.setOpaque(false); 
			
			
			JButton jb1=new JButton("����Ϸ");
			jf.add(jb1);
			jb1.setBounds(675, 185, 100,40);
			jb1.addActionListener(mouse);
			
			JButton jb2=new JButton("����");
			jf.add(jb2);
			jb2.setBounds(675, 265, 100,40);
			jb2.addActionListener(mouse);			
			
			JButton jb3=new JButton("�˻���ս");
			jf.add(jb3);
			jb3.setBounds(675, 345, 100,40);	
			jb3.addActionListener(mouse);
			
			
			//jp.add(jb1);	
			//jb.addActionListener(mouse);
			

//			Color[] color={Color.orange,Color.YELLOW};
//			for(int i=0;i<color.length;i++)
//			{
//				JButton jb=new JButton();
//				jb.setBackground(color[i]);
//				jb.setPreferredSize(new Dimension(30,30));
//				jp.add(jb);	
//				jb.addActionListener(mouse);
//			}			
			
			//���̶���
			ChessJpanel cp = new ChessJpanel();
			//cp.setBackground(Color.ORANGE);
			jf.add(cp,BorderLayout.CENTER);			
			cp.setOpaque(false); 
			
			
 
			
			cp.addMouseListener(mouse);
			cp.addMouseMotionListener(mouse);
		    
			jf.setVisible(true);
			Graphics g=cp.getGraphics();
			mouse.setgr(g);
			mouse.setcp(cp);
			cp.setMouse(mouse);
			
			
					
		}

		
}
