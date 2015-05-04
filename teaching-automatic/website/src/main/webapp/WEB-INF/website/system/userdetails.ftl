<#include "/uccyou.ftl">
<html>
	<body>
		<form action='<@url href="/admin/user/change"/>' method="POST">
			<table>
				<tr>
					<th>学号/工号</th>
					<td><input name="identityCode" value="${(user.identityCode)!}" type="text"/></td>
				</tr>
				<tr>
					<th>姓名:</th>
					<td><input name="name" value="${(user.name)!}" type="text"/></td>
				</tr>
				<tr>
					<th>身份:</th>
					<td>
						<select name="identity">
								<option value="T" <#if user.identity=="T"> selected="selected" </#if>>教师</option>
								<option value="S" <#if user.identity=="S"> selected="selected" </#if>>学生</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>电话:</th>
					<td><input name="phone" value="${(user.phone)!}" type="text"/></td>
				</tr>
				<tr>
					<th>Email:</th>
					<td><input name="email" value="${(user.email)!}" type="text"/></td>
				</tr>
				<tr>
					<th>QQ:</th>
					<td><input name="qq" value="${(user.qq)!}" type="text"/></td>
				</tr>
				<input name="id" value="${(user.id)!}" type="hidden">
				<tr>
					<td colspan="2"><input type="submit" value="提交"/></td>
				</tr>
				<#if tip??>
					<font color="red" size="35px">${tip}</font>
				</#if>
			</table>
		</form>
	</body>
</html>