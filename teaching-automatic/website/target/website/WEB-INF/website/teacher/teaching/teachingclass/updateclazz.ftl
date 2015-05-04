<#include "/uccyou.ftl">

<@body title="编辑教学班信息">
<div class="ta-content-outer" id="content">
    <div class="ta-right">
        <h1>编辑教学班&nbsp;<a href='<@url href="/teacher/clazz"/>'>返回</a></h1>
        <div class="ta-usercenter">
            <form action='<@url href="/teacher/clazz/change"/>' method="post">
                <input type="hidden" name="classId" value="${clazz.classId!}"/>
                <dl>
                    <dd>班级名称&nbsp;&nbsp;<input type="text" name="className" value="${clazz.className!}" class="input-info"/></dd>
                    <dd>课程名称&nbsp;&nbsp;<input type="text" name="courseName" value="${clazz.courseName!}" class="input-info"/></dd>
                    <dd>上课时间&nbsp;&nbsp;<input type="text" name="teachingTime" value="${clazz.teachingTime!}" class="input-info"/></dd>
                    <dd>上课教室&nbsp;&nbsp;<input type="text" name="classRoom" value="${clazz.classRoom!}" class="input-contact"/></dd>
                    <dd>课程性质&nbsp;
                        <select name="courseType" class="input-select">
                            <option value="T" <@selected p="${clazz.courseType}" t="T"/> >考试</option>
                            <option value="C" <@selected p="${clazz.courseType}" t="C"/> >考查</option>
                        </select>
                    </dd>
                    <dd>开课周数&nbsp;
                        <select name="startWeek" class="input-select">
                            <option value="1" <@selected p="${clazz.startWeek}" t="1" /> >1</option>
                            <option value="2" <@selected p="${clazz.startWeek}" t="2" /> >2</option>
                            <option value="3" <@selected p="${clazz.startWeek}" t="3" /> >3</option>
                            <option value="4" <@selected p="${clazz.startWeek}" t="4" /> >4</option>
                            <option value="5" <@selected p="${clazz.startWeek}" t="5" /> >5</option>
                            <option value="6" <@selected p="${clazz.startWeek}" t="6" /> >6</option>
                            <option value="7" <@selected p="${clazz.startWeek}" t="7" /> >7</option>
                            <option value="8" <@selected p="${clazz.startWeek}" t="8" /> >8</option>
                            <option value="9" <@selected p="${clazz.startWeek}" t="9" /> >9</option>
                            <option value="10" <@selected p="${clazz.startWeek}" t="10" /> >10</option>
                            <option value="11" <@selected p="${clazz.startWeek}" t="11" /> >11</option>
                            <option value="12" <@selected p="${clazz.startWeek}" t="12" /> >12</option>
                            <option value="13" <@selected p="${clazz.startWeek}" t="13" /> >13</option>
                            <option value="14" <@selected p="${clazz.startWeek}" t="14" /> >14</option>
                            <option value="15" <@selected p="${clazz.startWeek}" t="15" /> >15</option>
                            <option value="16" <@selected p="${clazz.startWeek}" t="16" /> >16</option>
                            <option value="17" <@selected p="${clazz.startWeek}" t="17" /> >17</option>
                            <option value="18" <@selected p="${clazz.startWeek}" t="18" /> >18</option>
                            <option value="19" <@selected p="${clazz.startWeek}" t="19" /> >19</option>
                            <option value="20" <@selected p="${clazz.startWeek}" t="20" /> >20</option>
                        </select>
                    </dd>
                    <dd>结课周数&nbsp;
                        <select name="endWeek" class="input-select">
                            <option value="2" <@selected p="${clazz.endWeek}" t="2" /> >2</option>
                            <option value="3" <@selected p="${clazz.endWeek}" t="3" /> >3</option>
                            <option value="4" <@selected p="${clazz.endWeek}" t="4" /> >4</option>
                            <option value="5" <@selected p="${clazz.endWeek}" t="5" /> >5</option>
                            <option value="6" <@selected p="${clazz.endWeek}" t="6" /> >6</option>
                            <option value="7" <@selected p="${clazz.endWeek}" t="7" /> >7</option>
                            <option value="8" <@selected p="${clazz.endWeek}" t="8" /> >8</option>
                            <option value="9" <@selected p="${clazz.endWeek}" t="9" /> >9</option>
                            <option value="10" <@selected p="${clazz.endWeek}" t="10" /> >10</option>
                            <option value="11" <@selected p="${clazz.endWeek}" t="11" /> >11</option>
                            <option value="12" <@selected p="${clazz.endWeek}" t="12" /> >12</option>
                            <option value="13" <@selected p="${clazz.endWeek}" t="13" /> >13</option>
                            <option value="14" <@selected p="${clazz.endWeek}" t="14" /> >14</option>
                            <option value="15" <@selected p="${clazz.endWeek}" t="15" /> >15</option>
                            <option value="16" <@selected p="${clazz.endWeek}" t="16" /> >16</option>
                            <option value="17" <@selected p="${clazz.endWeek}" t="17" /> >17</option>
                            <option value="18" <@selected p="${clazz.endWeek}" t="18" /> >18</option>
                            <option value="19" <@selected p="${clazz.endWeek}" t="19" /> >19</option>
                            <option value="20" <@selected p="${clazz.endWeek}" t="20" /> >20</option>
                        </select>
                    </dd>
                    <dd>课程学分&nbsp;&nbsp;<input type="text" name="credit" value="${clazz.credit!}" class="input-number"/></dd>
                    <dd style="height:100px;">班级公告&nbsp;
                        <textarea name="notice" style="height:90px;width:300px;border:1px solid #999;">${clazz.notice!}</textarea>
                    <dd>
                    <dd><input type="submit" value="修改" class="input-submit"/></dd>
                </dl>
            </form>
        </div>
    </div>
</div>
</@body>