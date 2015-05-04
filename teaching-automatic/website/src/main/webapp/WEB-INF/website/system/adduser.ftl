<#include "/uccyou.ftl">

<form  action='<@url href="/admin/user/add" />' method="post">
	<table border="1px" align="center">
		<tr>
			<td>学号</td>
			<td><input type="text" name="identityCode" value="${(user.identityCode)!}"/></td>
		</tr>
		<tr>
			<td>姓名</td>
			<td><input type="text" name="name" value="${(user.name)!}"/></td>
		</tr>
		<tr>
			<td>身份</td>
			<td>
				<select>
					<option value="T" <#if user.identity??><#if user.identity=="T"> selected="selected" </#if></#if>>教师</option>
					<option value="S" <#if user.identity??><#if user.identity=="S"> selected="selected" </#if></#if>>学生</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>邮箱</td>
			<td><input type="text" name="email" value="${(user.email)!}"/></td>
		</tr>
		<tr>
			<td>电话</td>
			<td><input type="text" name="phone" value="${(user.phone)!}"/></td>
		</tr>
		<tr>
			<td>企鹅</td>
			<td><input type="text" name="qq" value="${(user.qq)!}"/></td>
		</tr>
		<input type="submit" value="增加"/>
	</table>
</from>