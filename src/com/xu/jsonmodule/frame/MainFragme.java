package com.xu.jsonmodule.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.google.gson.Gson;

import com.xu.jsonmodule.core.CreatJava;
import com.xu.jsonmodule.core.ParseJson;
import com.xu.jsonmodule.entity.BaseEntity;
import com.xu.jsonmodule.entity.DakuohaoEntity;
import com.xu.jsonmodule.entity.cache.Cache;
import com.xu.jsonmodule.main.Main;
import com.xu.jsonmodule.util.FileUtil;
import com.xu.jsonmodule.util.StringUtils;

public class MainFragme extends JFrame {

	private static final long serialVersionUID = 1L;

	// private ImageIcon i1= new
	// ImageIcon(MainFragme.class.getResource("/images/81.jpg"));
	private JTextField textf,textPa;
	private JTextArea area;
	
	public MainFragme() {
		init();
	}

	private void init() {
		setTitle("json处理工具");
		
		JLayeredPane mainPane = new JLayeredPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel l = new JLabel();
		l.setBounds(0, 0, 576, 416);
		mainPane.add(l, JLayeredPane.DEFAULT_LAYER);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		setLocation((dimension.width - getWidth()) / 2,
				(dimension.height - getHeight()) / 2);
		JButton b1 = new JButton("生成");
		b1.setBackground(new Color(0xFCD137));
		JButton b2 = new JButton("格式化");
		b1.setBackground(new Color(0xFCD137));
		JButton b4 = new JButton("退出");
		b4.setBackground(new Color(0xFCD137));
		b1.setBounds(437, 168, 79, 29);
		b2.setBounds(437, 217, 79, 29);
		b4.setBounds(437, 266, 79, 29);

		b1.addActionListener(new ActionListener() {// 生成
			public void actionPerformed(ActionEvent e) {
				Main.fileName = textf.getText();
				Main.javaPath = textPa.getText();
				try {
					if(StringUtils.isEmpty(Main.javaPath)){
						alter("路径不能为空！");
					}
					String str = area.getText().replaceAll(" ", "").trim();
					if(StringUtils.isEmpty(str)){
						return;
					}
					Main.kuohaoEntities = new ArrayList<DakuohaoEntity>();
					ParseJson json = new ParseJson();
					BaseEntity baseEntity = null;
					
					if(StringUtils.isEmpty(area.getText())){
						baseEntity = json.paseJson(FileUtil.readFile(Main.fileName));
					}else{
						baseEntity = json.paseJson(str);
					}
					new CreatJava(baseEntity);
					alter("生成成功！");
				}catch (FileNotFoundException e1) {
					alter("路径不存在!"+e1.toString());
				} 
				catch (Exception e2) {
					alter("生成失败！"+e2.toString());
				}
			}
		});
		
		b2.addActionListener(new ActionListener() {// 格式化
			public void actionPerformed(ActionEvent e) {
				try {
					String str = area.getText().replaceAll(" ", "").replaceAll("\n", "").trim();
					if(StringUtils.isEmpty(str)){
						return;
					}
					ParseJson json = new ParseJson();
					str = json.getFormatJson(str);
					area.setText(str);
				}
				catch (Exception e2) {
					alter("格式化失败！"+e2.toString());
				}
			}
		});
		
		b4.addActionListener(new ActionListener() {// 退出
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		mainPane.add(b1, JLayeredPane.PALETTE_LAYER);
		mainPane.add(b2, JLayeredPane.PALETTE_LAYER);
		mainPane.add(b4, JLayeredPane.PALETTE_LAYER);
		
		textf = new JTextField();
		textf.setBounds(10, 30,300,30);
		textf.setFont(new Font("Monospace", 1, 13));
		mainPane.add(textf,JLayeredPane.PALETTE_LAYER);
		final JFileChooser jfc = new JFileChooser();// 文件选择器 
		
		
		JButton k = new JButton("打开文件");
		k.setBackground(new Color(0xFCD137));
		k.setBounds(320, 30,140,29);
		k.addActionListener(new ActionListener() {// 打开文件
			public void actionPerformed(ActionEvent e) {
				jfc.setFileSelectionMode(0);// 设定只能选择到文件夹  
	            int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句  
	            if (state == 1) {  
	                return;  
	            } else {  
	                File f = jfc.getSelectedFile();// f为选择到的目录  
	                textf.setText(f.getAbsolutePath());  
	            }  
			}
		});
		mainPane.add(k, JLayeredPane.PALETTE_LAYER);
		
		textPa = new JTextField();
		textPa.setBounds(10, 70,300,30);
		textPa.setFont(new Font("Monospace", 1, 13));
		mainPane.add(textPa,JLayeredPane.PALETTE_LAYER);
		
		JButton kPa = new JButton("生成目录");
		kPa.setBackground(new Color(0xFCD137));
		kPa.setBounds(320, 70,140,29);
		kPa.addActionListener(new ActionListener() {// 打开目录
			public void actionPerformed(ActionEvent e) {
				jfc.setFileSelectionMode(1);// 设定只能选择到文件夹  
	            int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句  
	            if (state == 1) {  
	                return;  
	            } else {  
	                File f = jfc.getSelectedFile();// f为选择到的目录  
	                textPa.setText(f.getAbsolutePath());  
	                Main.cache.setPath(f.getAbsolutePath());
	                Gson gson = new Gson();
	                try {
						FileUtil.writeFile(Main.cachePath, gson.toJson(Main.cache));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
	            }  
			}
		});
		mainPane.add(kPa, JLayeredPane.PALETTE_LAYER);
		
		area = new JTextArea();
		JLabel jLabel = new JLabel("内容");
		jLabel.setBounds(10, 110,300,30);
		mainPane.add(jLabel, JLayeredPane.PALETTE_LAYER);
		JScrollPane jScrollPane = new JScrollPane(area);
		jScrollPane.setBounds(10, 150,300,220);
		mainPane.add(jScrollPane, JLayeredPane.PALETTE_LAYER);
		
		JLabel jLabel1 = new JLabel("作者：徐珍耀  991576395@qq.com");
		jLabel1.setBounds(320, 320, 250, 40);
		jLabel1.setFont(new Font("Monospace", 1, 13));
		mainPane.add(jLabel1, JLayeredPane.PALETTE_LAYER);
		
		setContentPane(mainPane);
		// setLayout(null);
		setSize(576, 416);
		toShow();
		
		initData();
	}
	
	private void initData() {
		Gson gson = new Gson();
        try {
			String value = FileUtil.readFile(Main.cachePath);
			if(!StringUtils.isEmpty(value)){
				Main.cache = gson.fromJson(value, Cache.class);
			}
			if(Main.cache != null){
				textPa.setText(Main.cache.getPath());  
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}

	private void alter(String msg){
		JOptionPane.showOptionDialog(this, "  "+msg, "提示",
				JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null,
				null, null);
	}

	public void toShow() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		setLocation((dimension.width - getWidth()) / 2,
				(dimension.height - getHeight()) / 2);
		setVisible(true);
	}
}
