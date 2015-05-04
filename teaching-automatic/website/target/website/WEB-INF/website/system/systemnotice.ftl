<#include "/uccyou.ftl">

<@body title="留言板管理面板">
	<body>
		<table border="2px" align="center" color="blue">
			<tr>
				<td colspan=6">
					<a href='<@url href="/notice/add"/>' />增添留言</a>
				</td>
			</tr>
				<tr>
					<td colspan="6">
						<form action='<@url href="/notice" />' method="post" align="right">
							主题：<input name="title" type="text" /> &nbsp;&nbsp;
							发布者：<input name="adminName" type="text" />&nbsp;&nbsp;
							可见(T,S, A)：<input name="reader" type="text" />&nbsp;&nbsp;
							<input type="submit" value="搜索" />
						</form>
					</td>
				</tr>
				<tr>
					<th>主题</th>
					<th>发布者</th>
					<th>创建日期</th>
					<th>可见</th>
					<th>编辑</th>
					<th>删除</th>
				</tr>
				<#if (pageModel.records)??>
					<#list pageModel.records as record>
						<tr>
							<td>${(record.title)!}</td>
							<td>${(record.adminName)!}</td>
							<td>${record.createDate?string("yyyy-MM-dd")}</td>
							<td>
								<#if record.reader == "T" >
									老师
								<#else>
									学生
								</#if>
							</td>
							<td><a href='<@url href="/notice/change/${(record.id)!}" />'>编辑</a></td>
							<td><a href='<@url href="/notice/remove/${(record.id)!}/${record.reader}" />'>删除</a></td>
						</tr>
					</#list>
				</#if>
			<tr>
				<td colspan="6">
					<@tag.paging requestUrl="${ctx}/notice" pageNo=pageModel.pageNo pageSize=pageModel.pageSize totalRecords=pageModel.totalRecords/>
				</td>
			</tr>
		</table>
	</body>
</@body>