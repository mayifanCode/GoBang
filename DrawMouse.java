package com.myf.gobang1030;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowStateListener;
import java.util.HashMap;

import javax.swing.WindowConstants;

public class DrawMouse implements ActionListener, MouseMotionListener, MouseListener, Config {

	private int x, y, z;
	public int x1,y1;
	private int point_x, point_y;
	private Graphics g;
	private int turn = 0;//0：黑子  1：白子
	private ChessJpanel cp;
	private int flagStart = 0;
	private Shape[] arrayshape = new Shape[250];
	public int index = 0;
	public int winFlag=0; 
	public int flagAi=0;
    public int xMax=0;
	public int yMax=0;
    public int xMax1=0;
	public int yMax1=0;	
	public int weightMax=0;
	public int weightMax1=0;
	public int random;
	int add=1;
	private Color color = Color.BLACK;	
	int value[][] = new int[LINE][LINE];// 0:未存 1：黑 2：白 3:边界	
    HashMap <String,Integer> map =new HashMap<>();
    
	
	public void setgr(Graphics g) {
		this.g = g;
	}

	public void setcp(ChessJpanel cp) {
		this.cp = cp;
		this.cp.setarray(arrayshape);
	}

	public void actionPerformed(ActionEvent e)
	{
		if ("新游戏".equals(e.getActionCommand())) 
		{
			System.out.println("新游戏");			
			turn=0;
			cp.paint1(g);
			flagStart = 1;
			winFlag=0;
			index=0;
			for(int i=0;i<LINE;i++)
			{
				for(int j=0;j<LINE;j++)
				{
					value[i][j]=0;
				}
			}
			if(flagAi==1)
			{				
				random=1+(int)(Math.random()*2);
				if(random==1)//AI 先手,执黑子
				{			
					map.put("11110", AI_A_1);//活四连    初始化权重
					map.put("11112", AI_A_2);//死四连
					map.put("11113", AI_A_3);
					map.put("1110",AI_A_4);//活三连
					map.put("1112",AI_A_5);//死三连
					map.put("1113",AI_A_6);
					map.put("110",AI_A_7);//活二连
					map.put("112",AI_A_8);//死二连
					map.put("113",AI_A_9);
					map.put("10",AI_A_10);//活一连
					map.put("12",AI_A_11);//死一连
					map.put("13",AI_A_12);					
					map.put("2", AI_A_13);
					
					map.put("0", AI_1);
					
					map.put("3", AI_2);
					
					map.put("22220", AI_G_1);
					map.put("22221", AI_G_2);
					map.put("22223", AI_G_3);
					map.put("2220", AI_G_4);
					map.put("2221", AI_G_5);
					map.put("2223", AI_G_6);
					map.put("220", AI_G_7);
					map.put("221", AI_G_8);
					map.put("223", AI_G_9);
					map.put("20", AI_G_10);
					map.put("21", AI_G_11);
					map.put("23", AI_G_12);
					map.put("1", AI_G_13);
					

					value[LINE/2][LINE/2]=1;
					g.setColor(Color.BLACK);
			    	g.drawOval(X0+(LINE/2)*SIZE-CHESS_SIZE/2, Y0+(LINE/2)*SIZE-CHESS_SIZE/2, CHESS_SIZE,CHESS_SIZE);
			    	g.fillOval(X0+(LINE/2)*SIZE-CHESS_SIZE/2, Y0+(LINE/2)*SIZE-CHESS_SIZE/2, CHESS_SIZE, CHESS_SIZE);
			    	setShape(X0+(LINE/2)*SIZE, Y0+(LINE/2)*SIZE,g.getColor());
			    	turn=1;
				}
				else if(random==2)  //AI后手 AI执白子
				{
					map.put("11110", AI_G_1);//活四连    初始化权重
					map.put("11112", AI_G_2);//死四连
					map.put("11113", AI_G_3);
					map.put("1110",AI_G_4);//活三连
					map.put("1112",AI_G_5);//死三连
					map.put("1113",AI_G_6);
					map.put("110",AI_G_7);//活二连
					map.put("112",AI_G_8);//死二连
					map.put("113",AI_G_9);
					map.put("10",AI_G_10);//活一连
					map.put("12",AI_G_11);//死一连
					map.put("13",AI_G_12);
					map.put("2",AI_G_13);					
						
					
					map.put("0", AI_1);
					
					map.put("3", AI_2);
					
					map.put("22220", AI_A_1);
					map.put("22221", AI_A_2);
					map.put("22223", AI_A_3);
					map.put("2220", AI_A_4);
					map.put("2221", AI_A_5);
					map.put("2223", AI_A_6);
					map.put("220", AI_A_7);
					map.put("221", AI_A_8);
					map.put("223", AI_A_9);
					map.put("20", AI_A_10);
					map.put("21", AI_A_11);
					map.put("23", AI_A_12);					
					map.put("1", AI_A_13);
					
				}
			}
			
		}
		
		if("悔棋".equals(e.getActionCommand()))
			{	
			    if(index>1)
			    {			
					    value[(arrayshape[index-1].y1-Y0)/SIZE][(arrayshape[index-1].x1-X0)/SIZE]=0;			    
				        index--;
				        cp.paint(g);
					    			    
						   if(turn==0)
							   turn=1;
						   else
							   turn=0;
						   
					   if(flagAi==1)
					   {
						    value[(arrayshape[index-1].y1-Y0)/SIZE][(arrayshape[index-1].x1-X0)/SIZE]=0;
					        index--;
					        cp.paint(g);
						   if(turn==0)
							   turn=1;
						   else
							   turn=0;				   
					   }
			    }
			}
		if("人机对战".equals(e.getActionCommand()))
		{
			    if(flagAi==0)
			    {
					flagAi=1;
				    System.out.println("人机打开");
				    System.out.println("开始游戏");			    
					turn=0;
					cp.paint1(g);
					flagStart = 1;
					winFlag=0;
					index=0;
					for(int i=0;i<LINE;i++)
					{
						for(int j=0;j<LINE;j++)
						{
							value[i][j]=0;
						}
					}			    
			    }
			    else
			    {
			    flagAi=0;
			    System.out.println("人机关闭");			    
			    }
				
			if(flagAi==1)
			{
				random=1+(int)(Math.random()*2);
				if(random==1)//AI 先手,执黑子
				{			
					map.put("11110", AI_A_1);//活四连    初始化权重
					map.put("11112", AI_A_2);//死四连
					map.put("11113", AI_A_3);
					map.put("1110",AI_A_4);//活三连
					map.put("1112",AI_A_5);//死三连
					map.put("1113",AI_A_6);
					map.put("110",AI_A_7);//活二连
					map.put("112",AI_A_8);//死二连
					map.put("113",AI_A_9);
					map.put("10",AI_A_10);//活一连
					map.put("12",AI_A_11);//死一连
					map.put("13",AI_A_12);					
					map.put("2", AI_A_13);
					
					map.put("0", AI_1);
					
					map.put("3", AI_2);
					
					map.put("22220", AI_G_1);
					map.put("22221", AI_G_2);
					map.put("22223", AI_G_3);
					map.put("2220", AI_G_4);
					map.put("2221", AI_G_5);
					map.put("2223", AI_G_6);
					map.put("220", AI_G_7);
					map.put("221", AI_G_8);
					map.put("223", AI_G_9);
					map.put("20", AI_G_10);
					map.put("21", AI_G_11);
					map.put("23", AI_G_12);
					map.put("1", AI_G_13);
					value[LINE/2][LINE/2]=1;
					g.setColor(Color.BLACK);
			    	g.drawOval(X0+(LINE/2)*SIZE-CHESS_SIZE/2, Y0+(LINE/2)*SIZE-CHESS_SIZE/2, CHESS_SIZE,CHESS_SIZE);
			    	g.fillOval(X0+(LINE/2)*SIZE-CHESS_SIZE/2, Y0+(LINE/2)*SIZE-CHESS_SIZE/2, CHESS_SIZE, CHESS_SIZE);
			    	setShape(X0+(LINE/2)*SIZE, Y0+(LINE/2)*SIZE,g.getColor());
			    	turn=1;
				}
				else if(random==2)  //AI后手 AI执白子
				{
					map.put("11110", AI_G_1);//活四连    初始化权重
					map.put("11112", AI_G_2);//死四连
					map.put("11113", AI_G_3);
					map.put("1110",AI_G_4);//活三连
					map.put("1112",AI_G_5);//死三连
					map.put("1113",AI_G_6);
					map.put("110",AI_G_7);//活二连
					map.put("112",AI_G_8);//死二连
					map.put("113",AI_G_9);
					map.put("10",AI_G_10);//活一连
					map.put("12",AI_G_11);//死一连
					map.put("13",AI_G_12);
					map.put("2",AI_G_13);					
						
					
					map.put("0", AI_1);
					
					map.put("3", AI_2);
					
					map.put("22220", AI_A_1);
					map.put("22221", AI_A_2);
					map.put("22223", AI_A_3);
					map.put("2220", AI_A_4);
					map.put("2221", AI_A_5);
					map.put("2223", AI_A_6);
					map.put("220", AI_A_7);
					map.put("221", AI_A_8);
					map.put("223", AI_A_9);
					map.put("20", AI_A_10);
					map.put("21", AI_A_11);
					map.put("23", AI_A_12);					
					map.put("1", AI_A_13);
					
				}
			}
		}
//		if("".equals(e.getActionCommand()))
//		{
//			JButton jbu=new JButton();
//			color=jbu.getBackground();
//			
//		}
	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e)
    {
    	//System.out.println("点击");

	    	x=e.getX();
	    	y=e.getY();

    	if(x>X0&&x<(X0+SIZE*(LINE-1))&&y>Y0&&y<(Y0+SIZE*(LINE-1)))
    	{
	    	z=(x-X0)/SIZE;
	    	z=X0+z*SIZE;
	    	if(Math.abs(x-z)>=0.5*SIZE)
	    	{
			    	x=(((x-X0)/SIZE)+1)*SIZE+X0;
			        point_x=((x-X0)/SIZE);
	    	}
	    	else
	    	{
	    	        x=(((x-X0)/SIZE))*SIZE+X0;
	    	        point_x=((x-X0)/SIZE);	    	
	    	}
	    	z=(y-Y0)/SIZE;
	    	z=Y0+z*SIZE;    	
	    	if(Math.abs(y-z)>=0.5*SIZE)
	    	{	
	    	        y=(((y-Y0)/SIZE)+1)*SIZE+Y0;
	    	        point_y=((y-Y0)/SIZE);
	    	}
	    	else 
	    	{
	    	        y=(((y-Y0)/SIZE))*SIZE+Y0;
	    	        point_y=((y-Y0)/SIZE);
	    	}
	    	//System.out.println(point_x+" "+point_y);
	    	if(flagStart==1&&value[point_y][point_x]==0)
	    	{
		    	if(turn==0)
		    	{
		    		g.setColor(Color.BLACK);		    		
		    		value[point_y][point_x]=1;		    		
		    		checkWin();
		    		
		    	}
		    	else
		    	{
		    	    g.setColor(Color.WHITE);
		    	    value[point_y][point_x]=2;  
		    	    checkWin();
		    	}
		    	if(turn==0)
		    		turn=1;
		    	else 
		    		turn=0;
		    	
		    	g.drawOval(x-CHESS_SIZE/2, y-CHESS_SIZE/2, CHESS_SIZE,CHESS_SIZE);
		    	g.fillOval(x-CHESS_SIZE/2, y-CHESS_SIZE/2, CHESS_SIZE, CHESS_SIZE);
		    	//System.out.println(x+" "+y);
		    	setShape(x,y,g.getColor());
	    	}
    	}
    	if(flagAi==1&&flagStart==1)
    		AiChess();
    }

	public void AiChess()
    {
    	//System.out.println("点击");
            AI();
            AI1();
            //System.out.println(weightMax+"   "+weightMax1);
            if(weightMax>weightMax1)
            {
		    	x=xMax;
		    	y=yMax;
            }
            else
            {
            	x=xMax1;
    	    	y=yMax1;	
            }
	    	//System.out.println(x+"  "+y);
	  
    	if(x>X0&&x<(X0+SIZE*(LINE-1))&&y>Y0&&y<(Y0+SIZE*(LINE-1)))
    	{
	    	z=(x-X0)/SIZE;
	    	z=X0+z*SIZE;
	    	if(Math.abs(x-z)>=0.5*SIZE)
	    	{
			    	x=(((x-X0)/SIZE)+1)*SIZE+X0;
			        point_x=((x-X0)/SIZE);
	    	}
	    	else
	    	{
	    	        x=(((x-X0)/SIZE))*SIZE+X0;
	    	        point_x=((x-X0)/SIZE);	    	
	    	}
	    	z=(y-Y0)/SIZE;
	    	z=Y0+z*SIZE;    	
	    	if(Math.abs(y-z)>=0.5*SIZE)
	    	{	
	    	        y=(((y-Y0)/SIZE)+1)*SIZE+Y0;
	    	        point_y=((y-Y0)/SIZE);
	    	}
	    	else 
	    	{
	    	        y=(((y-Y0)/SIZE))*SIZE+Y0;
	    	        point_y=((y-Y0)/SIZE);
	    	}
	    	//System.out.println(point_x+" "+point_y);
	    	if(flagStart==1&&value[point_y][point_x]==0)
	    	{
		    	if(turn==0)//黑子
		    	{
		    		g.setColor(Color.BLACK);		    		
		    		value[point_y][point_x]=1;
		    		//System.out.println("point:"+point_y+"  "+point_x);
		    		checkWin();
		    	}
		    	else    //白子
		    	{
		    	    g.setColor(Color.WHITE);
		    	    value[point_y][point_x]=2;  
		    	    checkWin();
		    	}
		    	if(turn==0)
		    		turn=1;
		    	else 
		    		turn=0;
		    	g.drawOval(x-CHESS_SIZE/2, y-CHESS_SIZE/2, CHESS_SIZE,CHESS_SIZE);
		    	g.fillOval(x-CHESS_SIZE/2, y-CHESS_SIZE/2, CHESS_SIZE, CHESS_SIZE);
		    	//System.out.println(x+" "+y);
		    	setShape(x,y,g.getColor());
	    	}
    	}
    }	
	public void mousePressed(MouseEvent e) {
		// System.out.println("按下");
	}

	public void mouseReleased(MouseEvent e) {
		// System.out.println("释放");
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void setShape(int x1, int y1, Color color) {
		Shape shape = new Shape(x1, y1, color);
		arrayshape[index++] = shape;
	}
	
	public void checkWin()
	{
	    
		
       if(g.getColor().equals(Color.black))
		 {
    	      add=1;
	          for(int i=1;i<5;i++)//水平
	          {
	        	  if(point_x-i<0)
	        		  break;
	        	  if(value[point_y][point_x-i]==1)//左方向
	        		  add++;
	        	  if(value[point_y][point_x-i]!=1)
	        		  break;
	          }
	          for(int i=1;i<5;i++)//水平
	          {
	        	  if(point_x+i>LINE-1)
	        		  break;
	        	  if(value[point_y][point_x+i]==1)//右方向
	        		  add++;
	        	  if(value[point_y][point_x+i]!=1)
	        		  break;
	          }	          
	          if(add==5)
	        	  winFlag=1;
	          
	   	      add=1;
	          for(int i=1;i<5;i++)//竖直
	          {
	        	  if(point_y-i<0)
	        		  break;
	        	  if(value[point_y-i][point_x]==1)//上方向
	        		  add++;
	        	  if(value[point_y-i][point_x]!=1)
	        		  break;
	          }
	          for(int i=1;i<5;i++)//竖直
	          {
	        	  if(point_y+i>LINE-1)
	        		  break;	        	  
	        	  if(value[point_y+i][point_x]==1)//下方向
	        		  add++;
	        	  if(value[point_y+i][point_x]!=1)
	        		  break;
	          }	          
	          if(add==5)
	        	  winFlag=1;          
	          
	   	      add=1;
	          for(int i=1;i<5;i++)//斜方向
	          {
	        	  if(point_y-i<0||point_x-i<0)
	        		  break;
	        	  if(value[point_y-i][point_x-i]==1)//左上
	        		  add++;
	        	  if(value[point_y-i][point_x-i]!=1)
	        		  break;
	          }
	          for(int i=1;i<5;i++)//斜方向
	          {
	        	  if(point_y+i>LINE-1||point_x+i>LINE-1)
	        		  break;	        	  
	        	  if(value[point_y+i][point_x+i]==1)//右下
	        		  add++;
	        	  if(value[point_y+i][point_x+i]!=1)
	        		  break;
	          }	          
	          if(add==5)
	        	  winFlag=1;    	          	          
	          
	   	      add=1;
	          for(int i=1;i<5;i++)//斜方向
	          {
	        	  if(point_y+i>LINE-1||point_x-i<0)
	        		  break;	        	  
	        	  if(value[point_y+i][point_x-i]==1)//左下
	        		  add++;
	        	  if(value[point_y+i][point_x-i]!=1)
	        		  break;
	          }
	          for(int i=1;i<5;i++)//斜方向
	          {
	        	  if(point_y-i<0||point_x+i>LINE-1)
	        		  break;	        	  
	        	  if(value[point_y-i][point_x+i]==1)//右上
	        		  add++;
	        	  if(value[point_y-i][point_x+i]!=1)
	        		  break;
	          }	          
	          if(add==5)
	        	  winFlag=1;	          
	          
	          if(winFlag==1)
	          {
	        	    System.out.println("黑子胜");        	    
					System.out.println("比赛结束");
					blackWin();
				//	cp.paint1(g);
					flagStart = 0;
					winFlag=0;

	          }
	          
		 }
       if(g.getColor().equals(Color.WHITE))
		 {
    	      add=1;
	          for(int i=1;i<5;i++)//水平
	          {
	        	  if(point_x-i<0)
	        		  break;	        	  
	        	  if(value[point_y][point_x-i]==2)//左方向
	        		  add++;
	        	  if(value[point_y][point_x-i]!=2)
	        		  break;
	          }
	          for(int i=1;i<5;i++)//水平
	          {
	        	  if(point_x+i>LINE-1)
	        		  break;
	        	  if(value[point_y][point_x+i]==2)//右方向
	        		  add++;
	        	  if(value[point_y][point_x+i]!=2)
	        		  break;
	          }	          
	          if(add==5)
	        	  winFlag=2;
	          
	   	      add=1;
	          for(int i=1;i<5;i++)//竖直
	          {
	        	  if(point_y-i<0)
	        		  break;
	        	  if(value[point_y-i][point_x]==2)//上方向
	        		  add++;
	        	  if(value[point_y-i][point_x]!=2)
	        		  break;
	          }
	          for(int i=1;i<5;i++)//竖直
	          {
	        	  if(point_y+i>LINE-1)
	        		  break;
	        	  if(value[point_y+i][point_x]==2)//下方向
	        		  add++;
	        	  if(value[point_y+i][point_x]!=2)
	        		  break;
	          }	          
	          if(add==5)
	        	  winFlag=2;          
	          
	   	      add=1;
	          for(int i=1;i<5;i++)//斜方向
	          {
	        	  if(point_y-i<0||point_x-i<0)
	        		  break;
	        	  if(value[point_y-i][point_x-i]==2)//左上
	        		  add++;
	        	  if(value[point_y-i][point_x-i]!=2)
	        		  break;
	          }
	          for(int i=1;i<5;i++)//斜方向
	          {
	        	  if(point_y+i>LINE-1||point_x+i>LINE-1)
	        		  break;
	        	  if(value[point_y+i][point_x+i]==2)//右下
	        		  add++;
	        	  if(value[point_y+i][point_x+i]!=2)
	        		  break;
	          }	          
	          if(add==5)
	        	  winFlag=2;    	          	          
	          
	   	      add=1;
	          for(int i=1;i<5;i++)//斜方向
	          {
	        	  if(point_y+i>LINE-1||point_x-i<0)
	        		  break;
	        	  if(value[point_y+i][point_x-i]==2)//左下
	        		  add++;
	        	  if(value[point_y+i][point_x-i]!=2)
	        		  break;
	          }
	          for(int i=1;i<5;i++)//斜方向
	          {
	        	  if(point_y-i<0||point_x+i>LINE-1)
	        		  break;
	        	  if(value[point_y-i][point_x+i]==2)//右上
	        		  add++;
	        	  if(value[point_y-i][point_x+i]!=2)
	        		  break;
	          }	          
	          if(add==5)
	        	  winFlag=2;	          
	          
	          if(winFlag==2)
	          {
	        	System.out.println("白子胜");
				System.out.println("比赛结束");
				whiteWin();
				//cp.paint1(g);
				flagStart = 0;
				winFlag=0;
	          
	          }
		 }       
              add=1;
	          winFlag=0;		
	}
				public void blackWin()
				{
					javax.swing.JFrame jf2 = new javax.swing.JFrame();
					jf2.setSize(400, 300);
			
					jf2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					jf2.setLocationRelativeTo(null);
					java.awt.FlowLayout flow = new java.awt.FlowLayout();
					jf2.setLayout(flow);
			
					javax.swing.ImageIcon Page = new javax.swing.ImageIcon("E:\\workspace\\mayifan\\src\\com\\myf\\gobang1030\\black.png");
					javax.swing.JLabel jla = new javax.swing.JLabel(Page);
					java.awt.Dimension du = new java.awt.Dimension(400, 300);
					jla.setPreferredSize(du);
					jf2.add(jla);		
					jf2.setVisible(true);
//					new Thread(new Runnable() {		
//						public void run(){
//							while(true){
//										try {
//											Thread.sleep(5000);
//										    } 
//										catch (InterruptedException e)
//											{
//												e.printStackTrace();
//											}
//										 System.exit(0);
//							           }			
//						                 }
//                    }).start();					
				}    
	
				public void whiteWin()
				{
					javax.swing.JFrame jf1 = new javax.swing.JFrame();
					jf1.setSize(400, 300);

					jf1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					jf1.setLocationRelativeTo(null);
					java.awt.FlowLayout flow = new java.awt.FlowLayout();
					jf1.setLayout(flow);

					javax.swing.ImageIcon Page = new javax.swing.ImageIcon("E:\\workspace\\mayifan\\src\\com\\myf\\gobang1030\\white.png");
					javax.swing.JLabel jla = new javax.swing.JLabel(Page);
					java.awt.Dimension du = new java.awt.Dimension(400, 300);
					jla.setPreferredSize(du);
					jf1.add(jla);

					jf1.setVisible(true);
//					new Thread(new Runnable() {		
//						public void run(){
//							while(true){
//										try {
//											Thread.sleep(5000);
//										    } 
//										catch (InterruptedException e)
//											{
//												e.printStackTrace();
//											}
//										 System.exit(0);
//							           }			
//						                 }
//                    }).start();					
				}
	           public void AI()  //进攻形势
	           {
	        	   int chess=0;		   
	        	   int weight=0;
	        	   int weightArr[][] = new int[LINE][LINE];//保存每个位置的权重
	        	   String qiju="";	
	        	   weightMax=0;
	        	      for(int i=0;i<LINE;i++)
	        	      {
	        	    	  for(int j=0;j<LINE;j++)
	        	    	  {
	        	    		   
	        	    		  if(value[i][j]!=0)
	        	    			  continue;
	        	    		  else
	        	    		  {	   
	        	    			    for(int k=1;k<6;k++)//向上
	        	    			    {
	        	    			    	if(i-k<0)
	        	    			    	{
	        	    			    		chess=3;
	        	    			    		qiju+=chess;
	        	    			    		break;
	        	    			    	}
	        	    			    	chess=value[i-k][j];     
	        	    			    	if(chess==0)
	        	    			    		{
	        	    			    		  qiju+=chess;
	        	    			    		  break;
	        	    			    		}
	        	    			    	else if(chess==1)
	        	    			    	{
	        	    			    		qiju+=chess;	        	    			    		
	        	    			    	}
	        	    			    	else if(chess==2)
	        	    			    	{
	        	    			    		 qiju+=chess;
	        	    			    		  break;
	        	    			    	}
	        	    			    }
	        	    			    weight=map.get(qiju);
	        	    			    weightArr[i][j]+=weight;
	        	    			    qiju="";
	        	    				  
	        	    			    for(int k=1;k<6;k++)//向下
	        	    			    {
	        	    			    	if(i+k>LINE-1)
	        	    			    	{
	        	    			    		chess=3;
	        	    			    		qiju+=chess;
	        	    			    		break;
	        	    			    	}	        	    			    	
	        	    			    	chess=value[i+k][j];     
	        	    			    	if(chess==0)
	        	    			    		{
	        	    			    		  qiju+=chess;
	        	    			    		  break;
	        	    			    		}
	        	    			    	else if(chess==1)
	        	    			    	{
	        	    			    		qiju+=chess;	        	    			    		
	        	    			    	}
	        	    			    	else if(chess==2)
	        	    			    	{
	        	    			    		 qiju+=chess;
	        	    			    		  break;
	        	    			    	}
	        	    			    }
	        	    			    weight=map.get(qiju);
	        	    			    weightArr[i][j]+=weight; 
	        	    			    qiju="";
	        	    			    
	        	    			    for(int k=1;k<6;k++)//向左
	        	    			    {
	        	    			    	if(j-k<0)
	        	    			    	{
	        	    			    		chess=3;
	        	    			    		qiju+=chess;
	        	    			    		break;
	        	    			    	}	        	    			    	
	        	    			    	chess=value[i][j-k];     
	        	    			    	if(chess==0)
	        	    			    		{
	        	    			    		  qiju+=chess;
	        	    			    		  break;
	        	    			    		}
	        	    			    	else if(chess==1)
	        	    			    	{
	        	    			    		qiju+=chess;	        	    			    		
	        	    			    	}
	        	    			    	else if(chess==2)
	        	    			    	{
	        	    			    		 qiju+=chess;
	        	    			    		  break;
	        	    			    	}
	        	    			    }
	        	    			    weight=map.get(qiju);
	        	    			    weightArr[i][j]+=weight; 
	        	    			    qiju="";	        	    			    
	        	    			    
	        	    			    for(int k=1;k<6;k++)//向右
	        	    			    {
	        	    			    	if(j+k>LINE-1)
	        	    			    	{
	        	    			    		chess=3;
	        	    			    		qiju+=chess;
	        	    			    		break;
	        	    			    	}	        	    			    	
	        	    			    	chess=value[i][j+k];     
	        	    			    	if(chess==0)
	        	    			    		{
	        	    			    		  qiju+=chess;
	        	    			    		  break;
	        	    			    		}
	        	    			    	else if(chess==1)
	        	    			    	{
	        	    			    		qiju+=chess;	        	    			    		
	        	    			    	}
	        	    			    	else if(chess==2)
	        	    			    	{
	        	    			    		 qiju+=chess;
	        	    			    		  break;
	        	    			    	}
	        	    			    }
	        	    			    weight=map.get(qiju);
	        	    			    weightArr[i][j]+=weight; 
	        	    			    qiju="";	        	    			    
	        	    			    
	        	    			    for(int k=1;k<6;k++)//向左上
	        	    			    {
	        	    			    	if(i-k<0||j-k<0)
	        	    			    	{
	        	    			    		chess=3;
	        	    			    		qiju+=chess;
	        	    			    		break;
	        	    			    	}	        	    			    	
	        	    			    	chess=value[i-k][j-k];     
	        	    			    	if(chess==0)
	        	    			    		{
	        	    			    		  qiju+=chess;
	        	    			    		  break;
	        	    			    		}
	        	    			    	else if(chess==1)
	        	    			    	{
	        	    			    		qiju+=chess;	        	    			    		
	        	    			    	}
	        	    			    	else if(chess==2)
	        	    			    	{
	        	    			    		 qiju+=chess;
	        	    			    		  break;
	        	    			    	}
	        	    			    }
	        	    			    weight=map.get(qiju);
	        	    			    weightArr[i][j]+=weight; 
	        	    			    qiju="";	        	    			    

	        	    			    for(int k=1;k<6;k++)//向右下
	        	    			    {
	        	    			    	if(i+k>LINE-1||j+k>LINE-1)
	        	    			    	{
	        	    			    		chess=3;
	        	    			    		qiju+=chess;
	        	    			    		break;
	        	    			    	}	        	    			    	
	        	    			    	chess=value[i+k][j+k];     
	        	    			    	if(chess==0)
	        	    			    		{
	        	    			    		  qiju+=chess;
	        	    			    		  break;
	        	    			    		}
	        	    			    	else if(chess==1)
	        	    			    	{
	        	    			    		qiju+=chess;	        	    			    		
	        	    			    	}
	        	    			    	else if(chess==2)
	        	    			    	{
	        	    			    		 qiju+=chess;
	        	    			    		  break;
	        	    			    	}
	        	    			    }
	        	    			    weight=map.get(qiju);
	        	    			    weightArr[i][j]+=weight; 
	        	    			    qiju="";	        	    			    
	        	    			    
	        	    			    for(int k=1;k<6;k++)//向左下
	        	    			    {
	        	    			    	if(i+k>LINE-1||j-k<0)
	        	    			    	{
	        	    			    		chess=3;
	        	    			    		qiju+=chess;
	        	    			    		break;
	        	    			    	}
	        	    			    	chess=value[i+k][j-k];     
	        	    			    	if(chess==0)
	        	    			    		{
	        	    			    		  qiju+=chess;
	        	    			    		  break;
	        	    			    		}
	        	    			    	else if(chess==1)
	        	    			    	{
	        	    			    		qiju+=chess;	        	    			    		
	        	    			    	}
	        	    			    	else if(chess==2)
	        	    			    	{
	        	    			    		 qiju+=chess;
	        	    			    		  break;
	        	    			    	}
	        	    			    }
	        	    			    weight=map.get(qiju);
	        	    			    weightArr[i][j]+=weight; 
	        	    			    qiju="";	        	    			    

	        	    			    for(int k=1;k<6;k++)//向右上
	        	    			    {
	        	    			    	if(i-k<0||j+k>LINE-1)
	        	    			    	{
	        	    			    		chess=3;
	        	    			    		qiju+=chess;
	        	    			    		break;
	        	    			    	}
	        	    			    	chess=value[i-k][j+k];     
	        	    			    	if(chess==0)
	        	    			    		{
	        	    			    		  qiju+=chess;
	        	    			    		  break;
	        	    			    		}
	        	    			    	else if(chess==1)
	        	    			    	{
	        	    			    		qiju+=chess;	        	    			    		
	        	    			    	}
	        	    			    	else if(chess==2)
	        	    			    	{
	        	    			    		 qiju+=chess;
	        	    			    		  break;
	        	    			    	}
	        	    			    }
	        	    			    weight=map.get(qiju);
	        	    			    weightArr[i][j]+=weight; 
	        	    			    qiju="";
	        	    			 }	        	    		        	        	    			    
	        	    			    if(weightArr[i][j]>weightMax)
	        	    			    {
	        	    			    	weightMax=weightArr[i][j];
	        	    			    	xMax=j*SIZE+X0;
	        	    			    	yMax=i*SIZE+Y0;
	        	    			    	//System.out.println(weightMax);
	        	    			    	//System.out.println(i);
	        	    			    	//System.out.println(j);
	        	    			    }
	        	    	  }
	        	      }
	        	           
	        	   
	        	   
	        	   
	           }
	           public void AI1()
	           {
	        	   int chess1=0;		   
	        	   int weight1=0;
	        	   int weightArr1[][] = new int[LINE][LINE];//保存每个位置的权重
	        	   String qiju1="";	
	        	   weightMax1=0;
	        	      for(int i=0;i<LINE;i++)
	        	      {
	        	    	  for(int j=0;j<LINE;j++)
	        	    	  {
	        	    		   
	        	    		  if(value[i][j]!=0)
	        	    			  continue;
	        	    		  else
	        	    		  {	   
	        	    			    for(int k=1;k<6;k++)//向上
	        	    			    {
	        	    			    	if(i-k<0)
	        	    			    	{
	        	    			    		chess1=3;
	        	    			    		qiju1+=chess1;
	        	    			    		break;
	        	    			    	}
	        	    			    	chess1=value[i-k][j];     
	        	    			    	if(chess1==0)
	        	    			    		{
	        	    			    		  qiju1+=chess1;
	        	    			    		  break;
	        	    			    		}
	        	    			    	else if(chess1==1)
	        	    			    	{
	        	    			    		qiju1+=chess1;
	        	    			    		break;
	        	    			    	}
	        	    			    	else if(chess1==2)
	        	    			    	{
	        	    			    		 qiju1+=chess1;	        	    			    		 
	        	    			    	}
	        	    			    }
	        	    			    weight1=map.get(qiju1);
	        	    			    weightArr1[i][j]+=weight1;
	        	    			    qiju1="";
	        	    				  
	        	    			    for(int k=1;k<6;k++)//向下
	        	    			    {
	        	    			    	if(i+k>LINE-1)
	        	    			    	{
	        	    			    		chess1=3;
	        	    			    		qiju1+=chess1;
	        	    			    		break;
	        	    			    	}	        	    			    	
	        	    			    	chess1=value[i+k][j];     
	        	    			    	if(chess1==0)
	        	    			    		{
	        	    			    		  qiju1+=chess1;
	        	    			    		  break;
	        	    			    		}
	        	    			    	else if(chess1==1)
	        	    			    	{
	        	    			    		qiju1+=chess1;
	        	    			    		break;
	        	    			    	}
	        	    			    	else if(chess1==2)
	        	    			    	{
	        	    			    		 qiju1+=chess1;	        	    			    		 
	        	    			    	}
	        	    			    }
	        	    			    weight1=map.get(qiju1);
	        	    			    weightArr1[i][j]+=weight1; 
	        	    			    qiju1="";
	        	    			    
	        	    			    for(int k=1;k<6;k++)//向左
	        	    			    {
	        	    			    	if(j-k<0)
	        	    			    	{
	        	    			    		chess1=3;
	        	    			    		qiju1+=chess1;
	        	    			    		break;
	        	    			    	}	        	    			    	
	        	    			    	chess1=value[i][j-k];     
	        	    			    	if(chess1==0)
	        	    			    		{
	        	    			    		  qiju1+=chess1;
	        	    			    		  break;
	        	    			    		}
	        	    			    	else if(chess1==1)
	        	    			    	{
	        	    			    		qiju1+=chess1;
	        	    			    		break;
	        	    			    	}
	        	    			    	else if(chess1==2)
	        	    			    	{
	        	    			    		 qiju1+=chess1;
	        	    			    	
	        	    			    	}
	        	    			    }
	        	    			    weight1=map.get(qiju1);
	        	    			    weightArr1[i][j]+=weight1; 
	        	    			    qiju1="";	        	    			    
	        	    			    
	        	    			    for(int k=1;k<6;k++)//向右
	        	    			    {
	        	    			    	if(j+k>LINE-1)
	        	    			    	{
	        	    			    		chess1=3;
	        	    			    		qiju1+=chess1;
	        	    			    		break;
	        	    			    	}	        	    			    	
	        	    			    	chess1=value[i][j+k];     
	        	    			    	if(chess1==0)
	        	    			    		{
	        	    			    		  qiju1+=chess1;
	        	    			    		  break;
	        	    			    		}
	        	    			    	else if(chess1==1)
	        	    			    	{
	        	    			    		qiju1+=chess1;
	        	    			    		break;
	        	    			    	}
	        	    			    	else if(chess1==2)
	        	    			    	{
	        	    			    		 qiju1+=chess1;
	        	    			    		
	        	    			    	}
	        	    			    }
	        	    			    weight1=map.get(qiju1);
	        	    			    weightArr1[i][j]+=weight1; 
	        	    			    qiju1="";	        	    			    
	        	    			    
	        	    			    for(int k=1;k<6;k++)//向左上
	        	    			    {
	        	    			    	if(i-k<0||j-k<0)
	        	    			    	{
	        	    			    		chess1=3;
	        	    			    		qiju1+=chess1;
	        	    			    		break;
	        	    			    	}	        	    			    	
	        	    			    	chess1=value[i-k][j-k];     
	        	    			    	if(chess1==0)
	        	    			    		{
	        	    			    		  qiju1+=chess1;
	        	    			    		  break;
	        	    			    		}
	        	    			    	else if(chess1==1)
	        	    			    	{
	        	    			    		qiju1+=chess1;
	        	    			    		break;
	        	    			    	}
	        	    			    	else if(chess1==2)
	        	    			    	{
	        	    			    		 qiju1+=chess1;
	        	    			    		  
	        	    			    	}
	        	    			    }
	        	    			    weight1=map.get(qiju1);
	        	    			    weightArr1[i][j]+=weight1; 
	        	    			    qiju1="";	        	    			    

	        	    			    for(int k=1;k<6;k++)//向右下
	        	    			    {
	        	    			    	if(i+k>LINE-1||j+k>LINE-1)
	        	    			    	{
	        	    			    		chess1=3;
	        	    			    		qiju1+=chess1;
	        	    			    		break;
	        	    			    	}	        	    			    	
	        	    			    	chess1=value[i+k][j+k];     
	        	    			    	if(chess1==0)
	        	    			    		{
	        	    			    		  qiju1+=chess1;
	        	    			    		  break;
	        	    			    		}
	        	    			    	else if(chess1==1)
	        	    			    	{
	        	    			    		qiju1+=chess1;
	        	    			    		break;
	        	    			    	}
	        	    			    	else if(chess1==2)
	        	    			    	{
	        	    			    		 qiju1+=chess1;
	        	    			    		 
	        	    			    	}
	        	    			    }
	        	    			    weight1=map.get(qiju1);
	        	    			    weightArr1[i][j]+=weight1; 
	        	    			    qiju1="";	        	    			    
	        	    			    
	        	    			    for(int k=1;k<6;k++)//向左下
	        	    			    {
	        	    			    	if(i+k>LINE-1||j-k<0)
	        	    			    	{
	        	    			    		chess1=3;
	        	    			    		qiju1+=chess1;
	        	    			    		break;
	        	    			    	}
	        	    			    	chess1=value[i+k][j-k];     
	        	    			    	if(chess1==0)
	        	    			    		{
	        	    			    		  qiju1+=chess1;
	        	    			    		  break;
	        	    			    		}
	        	    			    	else if(chess1==1)
	        	    			    	{
	        	    			    		qiju1+=chess1;
	        	    			    		break;
	        	    			    	}
	        	    			    	else if(chess1==2)
	        	    			    	{
	        	    			    		 qiju1+=chess1;
	        	    			    		  
	        	    			    	}
	        	    			    }
	        	    			    weight1=map.get(qiju1);
	        	    			    weightArr1[i][j]+=weight1; 
	        	    			    qiju1="";	        	    			    

	        	    			    for(int k=1;k<6;k++)//向右上
	        	    			    {
	        	    			    	if(i-k<0||j+k>LINE-1)
	        	    			    	{
	        	    			    		chess1=3;
	        	    			    		qiju1+=chess1;
	        	    			    		break;
	        	    			    	}
	        	    			    	chess1=value[i-k][j+k];     
	        	    			    	if(chess1==0)
	        	    			    		{
	        	    			    		  qiju1+=chess1;
	        	    			    		  break;
	        	    			    		}
	        	    			    	else if(chess1==1)
	        	    			    	{
	        	    			    		qiju1+=chess1;
	        	    			    		break;
	        	    			    	}
	        	    			    	else if(chess1==2)
	        	    			    	{
	        	    			    		 qiju1+=chess1;
	        	    			    		 
	        	    			    	}
	        	    			    }
	        	    			    weight1=map.get(qiju1);
	        	    			    weightArr1[i][j]+=weight1; 
	        	    			    qiju1="";
	        	    			    
	        	    			    
	        	    			  }
	        	    		            if(weightArr1[i][j]>weightMax1)
	        	    			    	{
	        	    			    	weightMax1=weightArr1[i][j];
	        	    			    	xMax1=j*SIZE+X0;
	        	    			    	yMax1=i*SIZE+Y0;
	        	    			    	//System.out.println(weightMax);
	        	    			    	//System.out.println(i+"  "+j);	        	    			    	
	        	    			  	    }
	        	    	  }
	        	      }
	        	           
	        	   
	        	   
	        	   
	           }	           
	

}
