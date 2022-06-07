package com;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.util.ui.UIUtil;
import com.xu.jsonmodule.core.CreatJava;
import com.xu.jsonmodule.core.ParseJson;
import com.xu.jsonmodule.entity.BaseEntity;
import com.xu.jsonmodule.entity.DakuohaoEntity;
import com.xu.jsonmodule.entity.savejava.ClassMsg;
import com.xu.jsonmodule.main.Main;
import com.xu.jsonmodule.util.FileUtil;
import com.xu.jsonmodule.util.LoadUtils;
import com.xu.jsonmodule.util.StringUtils;
import com.xu.jsonmodule.util.Tools;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 */
public class GeneratorDialog extends DialogWrapper {

    private String[] myOptions = new String[] { "确定", "取消" };
    private int myDefaultOptionIndex = 0;
    private int myFocusedOptionIndex = 0;
    private JTextComponent packageField;
    private  Project project;
    private String content;
//    private JCheckBox fragmentCb, pagingCb;

    public GeneratorDialog(@Nullable Project project,String content) {
        super(project);
        this.project = project;
        this.content = content;
        init();
        setTitle("生成");
    }

    @NotNull
    protected Action[] createActions() {
        Action[] actions = new Action[this.myOptions.length];

        for(int i = 0; i < this.myOptions.length; ++i) {
            final int position = i;
            String option = this.myOptions[i];
            actions[i] = new AbstractAction(UIUtil.replaceMnemonicAmpersand(option)) {
                public void actionPerformed(ActionEvent e) {
                    if (position == 0) {
                        String packageName = packageField.getText().trim();
                        if (packageName.length() <= 0){
                            Messages.showMessageDialog("包名不能为空!", "Information", Messages.getInformationIcon());
                            return;
                        }
                        Main.javaPath = Tools.getFileNameByProjectAndPackage(project,packageName);
                        String str = content.replaceAll(" ", "").trim();
                        if(StringUtils.isEmpty(str)){
                            return;
                        }
                        Main.kuohaoEntities = new ArrayList<DakuohaoEntity>();
                        Main.classMsg = new ArrayList<ClassMsg>();
                        ParseJson json = new ParseJson();
                        BaseEntity baseEntity = null;
                        baseEntity = json.paseJson(str);
                        Main.packgePath = packageName;
                        try {
                            new CreatJava(baseEntity);
                            Messages.showMessageDialog("生成成功!", "Information", Messages.getInformationIcon());
                        } catch (Exception e1) {
                            e1.printStackTrace();
                            FileUtil.writeFile(LoadUtils.LOG_PATH+"Log.txt",e1);
                            Messages.showMessageDialog("生成失败!"+e1.toString(), "Information", Messages.getInformationIcon());
                        }
                    }
                    close(position, true);
                }
            };
            if(i == this.myDefaultOptionIndex) {
                actions[i].putValue("DefaultAction", Boolean.TRUE);
            }

            if(i == this.myFocusedOptionIndex) {
                actions[i].putValue("FocusedAction", Boolean.TRUE);
            }

            UIUtil.assignMnemonic(option, actions[i]);
        }
        return actions;
    }

    @Nullable @Override protected JComponent createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 5));
        JPanel content = new JPanel(new GridLayout(0, 1));
//        JPanel boxes = new JPanel(new BorderLayout());
//
//        JTextPane messageComponent1 = createMessageComponent("请输入类名:");
//        content.add(messageComponent1);
//
//        classNameField = new JTextField(30);
//        content.add(this.classNameField);

//        JTextPane messageComponent2 = createMessageComponent("请输入待生成的Activity/Fragment需在的包名: 如main");
//        content.add(messageComponent2);
//
//        subPackageField = new JTextField(30);
//        content.add(this.subPackageField);
//        subPackageField.setText(PropertiesComponent.getInstance().getValue("sub", ""));

        JTextPane messageComponent3 = createMessageComponent("请输入包名:");
        content.add(messageComponent3);

        packageField = new JTextField(30);
        content.add(this.packageField);
//        packageField.setText(Constant.getProjectPackage());

//        fragmentCb = new JCheckBox("fragment");
//        fragmentCb.setSelected(false);
//        pagingCb = new JCheckBox("分页");
//        pagingCb.setSelected(false);

//        boxes.add(fragmentCb, "North");
//        boxes.add(pagingCb, "West");

        panel.add(content, "North");
//        panel.add(boxes);

        return panel;
    }

    private JTextPane createMessageComponent(String message) {
        JTextPane messageComponent = new JTextPane();
        return Messages.configureMessagePaneUi(messageComponent, message);
    }
}
