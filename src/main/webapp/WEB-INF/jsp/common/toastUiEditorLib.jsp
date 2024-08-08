<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<link rel="stylesheet" href="https://uicdn.toast.com/tui-color-picker/latest/tui-color-picker.min.css" />
<link rel="stylesheet" href="https://uicdn.toast.com/editor-plugin-color-syntax/latest/toastui-editor-plugin-color-syntax.min.css" />
<script src="https://uicdn.toast.com/tui-color-picker/latest/tui-color-picker.min.js"></script>
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<script src="https://uicdn.toast.com/editor-plugin-color-syntax/latest/toastui-editor-plugin-color-syntax.min.js"></script>

<script>
	const { Editor } = toastui;
	const { colorSyntax } = Editor.plugin;
	
	$(function(){
	  $('.toast-ui-editor').each(function(index, item) {
	    const items = $(item);
	    const initialValueEl = items.find(' > script');
	    const initialValue = initialValueEl.length == 0 ? '' : initialValueEl.html().trim();
	
	    const editor = new Editor({
	      el: item,
	      height: '600px',
	      initialValue: initialValue,
	      initialEditType: 'markdown',
	      previewStyle: 'tab',
	      plugins: [colorSyntax]
	    });
		
	    items.data('data-toast-editor', editor);
	  });
	});
	
	function submitForm(form){
		form.title.value = form.title.value.trim();
		form.body.value = form.body.value.trim();
		
		if (form.title.value.length == 0) {
			alert('제목을 입력해주세요');
			form.title.focus();
			return;
		}
		
		const editorData = $(form).find('.toast-ui-editor').data('data-toast-editor');
		const markdown = editorData.getMarkdown().trim();
		
		if(markdown.length == 0){
			alert('내용을 입력해주세요');
			editorData.focus();
			return;
		}
		
		form.body.value = markdown;
		
		form.submit();
	}
</script>