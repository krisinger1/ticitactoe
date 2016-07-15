package org.risinger;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;


public class TicTacToe implements ActionListener {
	ImageIcon boardIcon =new ImageIcon("tictactoeboard.png");
	JLabel board =new JLabel(boardIcon);
	JButton squares[][]=new JButton[3][3];
	int squaresValues[][]={{0,0,0},{0,0,0},{0,0,0}};

	JPanel panel;
	Color bg = new Color(190,225,250);
	//Color bg2 = new Color(210,235,255);
	
	String xIcon = "x.png";
	String oIcon = "o.png";
	int player = 1;
	int numPlays=0;
	
	TicTacToe() {
		JFrame frame = new JFrame("Tic Tac Toe");
		//frame.setLayout(null);
		frame.getContentPane().setBackground(bg);
		frame.setSize(400, 400);
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 0, 400, 400);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		initialize();
				
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(board);
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		JButton square = ((JButton)ae.getSource());
		if (square.getIcon()==null) {
			chooseSquare(square);
			player = (-1)*player;
			numPlays++;
			int winner = checkForWinner();
			if (numPlays>=9 && winner==0){
				JOptionPane.showMessageDialog(null, "Cat's game");
				resetBoard();
			}
			else if (winner!=0) {
				if (winner==1)JOptionPane.showMessageDialog(null, "X wins!");
				else JOptionPane.showMessageDialog(null, "O wins!");
				resetBoard();
			}
		}
		else JOptionPane.showMessageDialog(null, "Please choose an open square");
		
	}
	
	public void initialize(){
		for(int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				squares[i][j]=new JButton("");
				squares[i][j].setBounds(49+j*98,37+i*98,92, 92);
				//squares[i][j].setText(i+" "+j);
				squares[i][j].setBackground(bg);
				squares[i][j].setBorder(BorderFactory.createLineBorder(bg));
				squares[i][j].addActionListener(this);
				panel.add(squares[i][j]);
			}
		}
	}
	
	public void resetBoard(){
		numPlays=0;
		player=1;
		for(int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				squares[i][j].setIcon(null);
				squaresValues[i][j]=0;
			}
		}
	}
	
	public void chooseSquare(JButton square){
		int i,j;
		j = (square.getBounds().x-49)/98;
		i = (square.getBounds().y-37)/98;
		squaresValues[i][j]=player;
		//System.out.println("i "+i+"j "+j+" "+squaresValues[i][j]);

		if (player==1) {
			square.setIcon(new ImageIcon(xIcon));
			//squaresValues[i][j]=player;
		}
		else {
			square.setIcon(new ImageIcon(oIcon));
			//squaresValues[i][j]=player;

		}
	}
	
	public int checkForWinner(){
		//check rows
		for(int i=0;i<3;i++){
			int rowsum=0;
			int colsum=0;
			int diagsum=0;
			int otherdiagsum=0;
			for (int j=0;j<3;j++){
				rowsum+=squaresValues[i][j];
				colsum+=squaresValues[j][i];
				if (i==0)diagsum+=squaresValues[j][j];
				if (i==0)otherdiagsum+=squaresValues[j][2-j];
			}
			if (rowsum==-3||colsum==-3||diagsum==-3||otherdiagsum==-3)return -1;
			else if (rowsum==3||colsum==3||diagsum==3||otherdiagsum==3)return 1;
		}
		return 0;
	}
	
	public static void main(String args[]){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new TicTacToe();
			
			}
		});
	}

}
