import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by ortalcohen on 19/07/2016.
 */
public class Codenote extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
            final Project project = e.getProject();
            if (project == null) {
                return;
            }
            Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
            if (editor == null) {
                return;
            }
            final Document document = editor.getDocument();
            if (document == null) {
                return;
            }
            VirtualFile virtualFile = FileDocumentManager.getInstance().getFile(document);
            if (virtualFile == null) {
                return;
            }
            final String contents;
            try {
                BufferedReader br = new BufferedReader(new FileReader(virtualFile.getPath()));
                String currentLine;
                StringBuilder stringBuilder = new StringBuilder();
                while ((currentLine = br.readLine()) != null) {
                    stringBuilder.append(currentLine);
                    stringBuilder.append("\n");
                }
                contents = stringBuilder.toString();
            } catch (IOException e1) {
                return;
            }

            // client

        CrunchifyRESTService crunchifyRESTService = new CrunchifyRESTService();
        crunchifyRESTService.crunchifyREST(contents);


        }
    }

