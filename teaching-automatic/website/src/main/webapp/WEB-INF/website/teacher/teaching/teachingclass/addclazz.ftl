<#include "/uccyou.ftl">

<@body title="新增教学班">
<div class="ta-content-outer" id="content">
    <div class="ta-right">
        <h1>新增教学班&nbsp;<a href='<@url href="/teacher/clazz"/>'>返回</a></h1>
        <div class="ta-usercenter">
            <form action='<@url href="/teacher/clazz/add"/>' method="post">
                <dl>
                    <dd>班级名称&nbsp;&nbsp;<input type="text" name="className" value="" class="input-info"/></dd>
                    <dd>课程名称&nbsp;&nbsp;<input type="text" name="courseName" value="" class="input-info"/></dd>
                    <dd>上课时间&nbsp;&nbsp;<input type="text" name="teachingTime" value="" class="input-info"/></dd>
                    <dd>上课教室&nbsp;&nbsp;<input type="text" name="classRoom" value="" class="input-contact"/></dd>
                    <dd>课程性质&nbsp;
                        <select name="courseType" class="input-select">
                            <option value="T">考试</option>
                            <option value="C">考查</option>
                        </select>
                    </dd>
                    <dd>开课周数&nbsp;
                        <select name="startWeek" class="input-select">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                            <option value="13">13</option>
                            <option value="14">14</option>
                            <option value="15">15</option>
                            <option value="16">16</option>
                            <option value="17">17</option>
                            <option value="18">18</option>
                            <option value="19">19</option>
                            <option value="20">20</option>
                        </select>
                    </dd>
                    <dd>结课周数&nbsp;
                        <select name="endWeek" class="input-select">
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                            <option value="13">13</option>
                            <option value="14">14</option>
                            <option value="15">15</option>
                            <option value="16">16</option>
                            <option value="17">17</option>
                            <option value="18">18</option>
                            <option value="19">19</option>
                            <option value="20">20</option>
                        </select>
                    </dd>
                    <dd>课程学分&nbsp;&nbsp;<input type="text" name="credit" value="" class="input-number"/></dd>
                    <dd></dd>
                    <dd><input type="submit" value="提交" class="input-submit"/></dd>
                </dl>
            </form>
        </div>
    </div>
</div>
</@body>