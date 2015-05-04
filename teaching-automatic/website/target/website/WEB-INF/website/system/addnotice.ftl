<#include "/uccyou.ftl">

<@body title="添加留言">
	<body>
		<form action='<@url href="/notice/add" />' method="post">
			<table border="1px">
				<tr>
					<td>
						主题：
					</td>
					<td>
						<input type="text" name="title" />
					</td>
				</tr>
				<tr>
					<td>可见者:</td>
					<td>
						<input type="text" name="reader" />
					</td>
				</tr>
				<tr>
					<td>
						内容：
					</td>
					<td>
						<textarea name="content" style="overflow:hidden" rows="5" cols="20" ></textarea>
					</td>
				</tr>
				<input type="hidden" name = "adminId" value="${adminId?c}" />
				<#if tip??>
					<tr>
						<td colspan="2"><font color="red" size="20px">${tip!}</a></td>
					</tr>
				</#if>
				<tr>
					<td colspan="2"><input type="submit" value="提交" /></td>
				</tr>
			</table>
		</form>
	</body>
</@body>