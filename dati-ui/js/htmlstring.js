function getSingleHtml(a, b, c, d){
	let html = '<div class="layui-form-item">\n' +
	   '  <label class="layui-form-label">A:</label>\n' +
	   '  <div class="layui-input-block">\n' +
	   '              <input type="text" disabled="disabled" value="'+a+'"  class="layui-input">\n' +
	   '  </div>\n' +
	   '          </div> \n' +
	   ' \n' +
	   '         <div class="layui-form-item">\n' +
	   '             <label class="layui-form-label">B:</label>\n' +
	   '             <div class="layui-input-block">\n' +
	   '               <input type="text" disabled="disabled"  value="'+b+'"  class="layui-input">\n' +
	   '           </div>\n' +
	   '           </div> \n' +
	   ' \n' +
	   '         <div class="layui-form-item">\n' +
	   '             <label class="layui-form-label">C:</label>\n' +
	   '             <div class="layui-input-block">\n' +
	   '               <input type="text" disabled="disabled" value="'+c+'"  class="layui-input">\n' +
	   '           </div>\n' +
	   '           </div> \n' +
	   ' \n' +
	   '         <div class="layui-form-item">\n' +
	   '             <label class="layui-form-label">D:</label>\n' +
	   '             <div class="layui-input-block">\n' +
	   '               <input type="text"  disabled="disabled" value="'+d+'" class="layui-input">\n' +
	   '           </div>\n' +
	   '           </div> \n' +
	   ' \n' +
	   '         <div class="layui-form-item">\n' +
	   '           <label class="layui-form-label">选项:</label>\n' +
	   '           <div class="layui-input-block">\n' +
	   '             <input type="radio" name="answer" value="A" title="A" checked="checked"/>\n' +
	   '             <input type="radio" name="answer" value="B" title="B"/>\n' +
	   '             <input type="radio" name="answer" value="C" title="C"/>\n' +
	   '             <input type="radio" name="answer" value="D" title="D"/>\n' +
	   '           </div>\n' +
	   '         </div>';
	   return html;
}

function getBlankHtml(answer){
	  let html = '';
	  for(let i = 1; i <= answer; i++){
		  html += '<div class="layui-form-item">\n' +
      '          <label class="layui-form-label">第'+i+'空:</label>\n' +
      '          <div class="layui-input-block">\n' +
      '              <input type="text" lay-verify="required" name="answerBlank" placeholder="请输入答案" autocomplete="off" class="layui-input">\n' +
      '            </div>\n' +
      '           </div>';
	  }
	  return html;
}

function  getJudgment (correct, error) {
	 let html =       '<div class="layui-form-item">\n' +
      '       <label class="layui-form-label">正确:</label>\n' +
      '       <div class="layui-input-block">\n' +
      '         <input type="text"  disabled="disabled" lay-verify="required" value="'+correct+'" class="layui-input">\n' +
      '     </div>\n' +
      '     </div> \n' +
      '    \n' +
      '    <div class="layui-form-item">\n' +
      '        <label class="layui-form-label">错误:</label>\n' +
      '        <div class="layui-input-block">\n' +
      '          <input type="text"  disabled="disabled" lay-verify="required" value="'+error+'"  class="layui-input">\n' +
      '      </div>\n' +
      '      </div> \n' +
      '        \n' +
      ' \n' +
      ' <div class="layui-form-item">\n' +
      '   <label class="layui-form-label">正确选项:</label>\n' +
      '   <div class="layui-input-block">\n' +
      '     <input type="radio" name="answer" value="correct" title="正确" checked="checked"/>\n' +
      '     <input type="radio" name="answer" value="error" title="错误"/>\n' +
      '   </div>\n' +
      ' </div>';
	  return html;
}

function getRoleHtml(username, id){
	let html =  '<tr>\n' +
    '                              <td>'+username+'</td>\n' +
    '                              <td><div class="layui-form">\n' +
    '<div class="layui-form-item role-item" >\n' +
    '    <div class="layui-input-inline">\n' +
    '      <select id="'+id+'add">\n' +
    '   \n' +
    '      </select>\n' +
    '    </div>\n' +
    '\n' +
    '      <div class="layui-inline">\n' +
    '          <div class="layui-input-inline">\n' +
	'            <button onclick="queryRole(\''+id+'add\', \''+id+'\')" type="button" class="layui-btn layui-btn-warm">搜索</button>\n' +
    '            <button onclick="addRole(\''+id+'\', \''+id+'add\')" type="button" class="layui-btn">添加</button>\n' +
    '          </div>\n' +
    '        </div>\n' +
    '\n' +
    '  </div>\n' +
    '  </div></td>\n' +
	'\n'+
    ' <td><div class="layui-form">\n' +
    '<div class="layui-form-item role-item">\n' +
    '    <div class="layui-input-inline">\n' +
    '      <select id="'+id+'delete">\n' +
    '   \n' +
    '      </select>\n' +
    '    </div>\n' +
    '\n' +
    '      <div class="layui-inline">\n' +
    '          <div class="layui-input-inline">\n' +
    '            <button onclick="queryRole1(\''+id+'delete\', \''+id+'\')" type="button" class="layui-btn layui-btn-warm">搜索</button>\n' +
    '            <button onclick="deleteRole(\''+id+'\', \''+id+'delete\')" type="button" class="layui-btn">删除</button>\n' +
    '          </div>\n' +
    '        </div>\n' +
    '\n' +
    '  </div>\n' +
    '  </div></td>\n' +
    '  </tr>';
	return html;
}

function per(arr){
	let html ='';
	for (let i =0; i<arr.length; i++) {
		switch (arr[i]){
			case '#1':
			     html += '<li id="addTi"  class="layui-nav-item"><a  href="javascript:;" onclick="add()" >出题</a></li>';
				break;
			case '#2':
			    html += '<li id="qita" class="layui-nav-item"><a  href="recordAll.html"  >其他人历史</a></li>';
				break;
			case '#3':
				html += '<li id="role-manager" class="layui-nav-item"><a  href="roleManager.html"  >分配角色</a></li>';
				break;
		}
	}
	return html;
}