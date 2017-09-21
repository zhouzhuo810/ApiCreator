package me.zhouzhuo810.apicreator.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.vfs.VirtualFile;
import me.zhouzhuo810.apicreator.ui.JsonDialog;

/**
 * Created by admin on 2017/5/19.
 */
public class MainAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here

        VirtualFile data = e.getData(PlatformDataKeys.VIRTUAL_FILE);
        String path = data.getPath();
        String packagename = path.substring(path.indexOf("main/java") + 10, path.length()).replaceAll("/", ".");

        System.out.println(path);
        System.out.println(packagename);

        JsonDialog jsonDialog = new JsonDialog(path, packagename, e);
        jsonDialog.setSize(620, 440);
        jsonDialog.setAlwaysOnTop(true);
        jsonDialog.setVisible(true);


    }


}
