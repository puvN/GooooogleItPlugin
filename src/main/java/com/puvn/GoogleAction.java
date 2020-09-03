package com.puvn;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GoogleAction extends AnAction {

	@Override
	public void actionPerformed(@NotNull AnActionEvent event) {
		final var editor = event.getData(PlatformDataKeys.EDITOR);
		if (editor != null) {
			var selectedText = editor.getSelectionModel().getSelectedText();
			if (selectedText != null && !selectedText.isEmpty() && !selectedText.isBlank()) {
				selectedText = URLEncoder.encode(selectedText, StandardCharsets.UTF_8);
				final var url = String.format("https://www.google.com/search?q=%s", selectedText);
				BrowserUtil.browse(url);
			} else {
				Messages.showMessageDialog(
						"Text is not selected", "Empty Selection", Messages.getWarningIcon());
			}
		}
	}

	@Override
	public boolean isDumbAware() {
		return false;
	}

}
