<#include "/uccyou.ftl">

<@body title="添加管理员">
	<body>
		<form action='<@url href="/admin/add" />' method="post">
			<table border='1'>
				<tr>
					<td>
						管理员名:
					</td>
					<td>
						<input type="text" name="adminname" />
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="添加" /></td>
				</tr>
			</table>
		</form>
	</body>
</@body>