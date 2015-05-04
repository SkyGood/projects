<#include "/uccyou.ftl">

<@body title="修改留言">
	<body>
		<form action='<@url href="/notice/change" />' method="post">
			<table border='1'>
				<tr>
					<td>
						主题：
					</td>
					<td>
						<input type="text" name="title" value="${(notice.title)!}" />
					</td>
				</tr>
				<tr>
					<td>可见者:</td>
					<td>
						<input type="text" name="reader" value="${(notice.reader)!}" />
					</td>
				</tr>
				<tr>
					<td>
						内容：
					</td>
					<td>
						<textarea name="content" style="overflow:hidden" rows="5" cols="20" >${(notice.content)!}</textarea>
					</td>
				</tr>
				<input type="hidden" name = "id" value="${(notice.id)!}" />
				<#if tip??>
					<tr>
						<td colspan="2"><font color="red" size="20px">${tip!}</a></td>
					</tr>
				</#if>
				<tr>
					<td colspan="2"><input type="submit" value="修改" /></td>
				</tr>
			</table>
		</form>
	</body>
</@body>