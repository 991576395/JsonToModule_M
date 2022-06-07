package com;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.xu.jsonmodule.main.Main;

import java.io.File;
import java.io.IOException;

/**
 */
public class CreatAction extends AnAction{
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        //初始化目录
        initPath();
        //获得选中后的文字
        Editor editor = anActionEvent.getData(PlatformDataKeys.EDITOR);
        SelectionModel model = editor.getSelectionModel();
        final String selectedText = model.getSelectedText();

        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);
        GeneratorDialog dialog = new GeneratorDialog(project,selectedText);
        dialog.show();

    }


    private void initPath() {
        //初始化缓存目录
        File file = new File(Main.cachePath);
        if(!file.getParentFile().exists()){
            file.mkdirs();
        }
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

    }
}
