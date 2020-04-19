document.write("<script type='text/javascript' src='/js/htmlstring.js'></script>"); 
 function mix(ans){
			    for (let i = ans; i>=1; i--) {
			   		 if ($("#answer"+i).length > 0) {
			   			return i;
			   			}
			   		 }
			   }
		   
 function buttonClick(id){
    $(id).remove();
	  answer = mix(answer);	
	  }

function GetQueryValue(queryName) {
      var query = decodeURI(window.location.search.substring(1));
      var vars = query.split("&");
      for (var i = 0; i < vars.length; i++) {
	        var pair = vars[i].split("=");
        if (pair[0] == queryName) { return pair[1]; }
    }
     return null;
 }

//查询分类		  
function ajax(){
	$.ajax({
		url: "http://127.0.0.1:10001/api/topic/groups/"+$("#class_id").val(),
		type: "GET",
		xhrFields: {
		       withCredentials:true  //支持附带详细信息(带cookie)
		   },
		success:(res) => {
			if (!res.success) {
				layer.open({
				  title: '提示信息',
				  content: res.message
				});
			} else {
				let quan = '';
				 $.each(res.results, (i, n)=>{
					 let group_id = n.id;
					 let htmlGroup = ' <tr>\n' +
	                           '      <td id="'+n.id+'">'+n.group_name+'</td>\n' +
	                           '      <td><button onclick="update(this);" type="button" class="layui-btn layui-btn-xs">修改</button>\n' +
							   '      <button  type="button" onclick="submit(this)" class="layui-btn layui-btn-xs layui-btn-disabled">保存</button>\n' +
							   '      <button  type="button" onclick="deleteGroup(this)" class="layui-btn layui-btn-xs">删除</button></td>\n' +
							   '    </tr>';
					quan += htmlGroup;
				 })
				 $("#group-tbody").html(quan);
			}
		}
	})
}
//删除分类		  
deleteGroup = function(id) {
	let par = $(id).parent(); //拿到父节点
	let top = par.prev("td");//分类名称节点
	let con = confirm("确定删除吗");
	if (con) {
		$.ajax({
		url: "http://127.0.0.1:10001/api/topic/groups/"+top.prop("id"),
		type: "DELETE",
		xhrFields: {
		       withCredentials:true  //支持附带详细信息(带cookie)
		   },
		success:(res) => {
			if (!res.success) {
				layer.open({
				  title: '提示信息',
				  content: res.message
				});
			} else {
				ajax();
			}
		}
	})
	}
}

//分类管理动态控制
update = function(id){
      let par = $(id).parent(); //拿到父节点
	  let top = par.prev("td");//分类名称节点
	  let bc = $(id).next("button"); //保存按钮节点
	  top.prop("contentEditable",true);
	  bc.removeClass("layui-btn-disabled");
}

//改变分类名称
submit = function(id){
	let par = $(id).parent(); //拿到父节点
	let top = par.prev("td");//分类名称节点
	
	let dataJson = {
		"group_name": top.text()
	}
	$.ajax({
		url: "http://127.0.0.1:10001/api/topic/groups/"+top.prop("id"),
		type: "PUT",
		xhrFields: {
		       withCredentials:true  //支持附带详细信息(带cookie)
		   },
		headers: {
			"content-type": "application/json;charset=utf-8"
		},
		data: JSON.stringify(dataJson),
		success:(res) => {
			if (!res.success) {
				layer.open({
				  title: '提示信息',
				  content: res.message
				});
			} else {
				top.prop("contentEditable", false);
	            $(id).addClass("layui-btn-disabled");
				ajax();
			}
		}
	})
}

//查询分类
function queryGroup(id, class_id){
	//如果长度等于空证明用户还没登录,不要去查询分类.
	if (class_id.length != 0) {
	    $.ajax({
	    	url: "http://127.0.0.1:10001/api/topic/groups/"+class_id,
	    	type: "GET",
			xhrFields: {
			       withCredentials:true  //支持附带详细信息(带cookie)
			   },
	    	success:(res) => {
	    		if (!res.success) {
	    			layer.open({
	    			  title: '提示信息',
	    			  content: res.message
	    			});
	    		} else {
	    			let quan = '<option value="0" selected="selected">未选择</option>';
	    			 $.each(res.results, (i, n)=>{
							 quan += '<option value="'+n.id+'">'+n.group_name+'</option>'; 
	    			 });
	    			 $("#"+id).html(quan);
	    			 //重新渲染
	    		     layui.use('form', function(){  //此段代码必不可少
	    		     	var form = layui.form;
	    		     	form.render();
	    		      });
	    		    
	    		}
	    	}
	    });	
	}
}
//查询所有用户
function queryAllUser(class_id){
	//如果长度等于空证明用户还没登录,不要去查询分类.
	if (class_id.length != 0) {
	    $.ajax({
	    	url: "http://127.0.0.1:10001/api/manage/users/"+class_id,
	    	type: "GET",
			xhrFields: {
			       withCredentials:true  //支持附带详细信息(带cookie)
			   },
	    	success:(res) => {
	    		if (!res.success) {
	    			layer.open({
	    			  title: '提示信息',
	    			  content: res.message
	    			});
	    		} else {
	    			let quan = '<option value="0" selected="selected">未选择</option>';
	    			 $.each(res.results, (i, n)=>{
							 quan += '<option value="'+n.id+'">'+n.username+'</option>'; 
	    			 });
	    			 $("#select-user").html(quan);
	    			 //重新渲染
	    		     layui.use('form', function(){  //此段代码必不可少
	    		     	var form = layui.form;
	    		     	form.render();
	    		      });
	    		    
	    		}
	    	}
	    });	
	}
}

	
 function initUserRecord (){
		$.ajax({
			url: "http://127.0.0.1:10001/api/manage/users",
			type: "GET",
			xhrFields: {
			       withCredentials:true  //支持附带详细信息(带cookie)
			   },
			success:(res) => {
				if ((!res.success) && (res.code == 501)) {
					layer.msg(res.message);
				    setTimeout("redirect()", 2000);
					 } else if (!res.success) {
						Layer.msg('初始化失败');
					 } else {
						 $(".layui-nav-bar").remove();
						 let htmlstring = per(res.per_id);
						 $("#dingwei").append(htmlstring);
						 $("#class_id").val(res.user.class_id);
						 ele();
						 $("#username").text(res.user.username);
						 $("#user_id").val(res.user.id);
						 $("#answerTopic").prop("href", "answer.html?user_id="+res.user.id+"&class_id="+res.user.class_id);
						 queryGroup('single', res.user.class_id);
					 }
				}
			});
		}
	

 function initUserAdd (){
	$.ajax({
		url: "http://127.0.0.1:10001/api/manage/users",
		type: "GET",
		xhrFields: {
		       withCredentials:true  //支持附带详细信息(带cookie)
		   },
		success:(res) => {
			if ((!res.success) && (res.code == 501)) {
				layer.msg(res.message);
			    setTimeout("redirect()", 2000);
				 } else if (!res.success) {
					layer.msg('初始化失败');
				 } else {
					 $(".layui-nav-bar").remove();
					let htmlstring = per(res.per_id);
					$("#dingwei").append(htmlstring);
					 ele();
					 $("#addTi").addClass("layui-this");
					 $("#class_id").val(res.user.class_id);
					 $("#username").text(res.user.username);
					 $("#user_id").val(res.user.id);
					 $("#answerTopic").prop("href", "answer.html?user_id="+res.user.id+"&class_id="+res.user.class_id);
					 queryGroup('single', res.user.class_id);
				 }
			}
		});
	}	
	
 function initUserIndex (){
	$.ajax({
		url: "http://127.0.0.1:10001/api/manage/users",
		type: "GET",
		xhrFields: {
		       withCredentials:true  //支持附带详细信息(带cookie)
		   },
		success:(res) => {
			if ((!res.success) && (res.code == 501)) {
				layer.msg(res.message);
			    setTimeout("redirect()", 1500);
				 } else if (!res.success) {
					layer.msg('初始化失败');
				 } else {
					 $(".layui-nav-bar").remove();
					 let htmlstring = per(res.per_id);
					 $("#dingwei").append(htmlstring);
					 ele();
					 $("#username").text(res.user.username);
					 $("#answerTopic").prop("href", "answer.html?user_id="+res.user.id+"&class_id="+res.user.class_id);
				 }
			}
		});
	}	
	
initUserRecordAll = function(){
	$.ajax({
		url: "http://127.0.0.1:10001/api/manage/users",
		type: "GET",
		xhrFields: {
		       withCredentials:true  //支持附带详细信息(带cookie)
		   },
		success:(res) => {
			if ((!res.success) && (res.code == 501)) {
				layer.msg(res.message);
			    setTimeout("redirect()", 2000);
				 } else if (!res.success) {
					layer.msg('初始化失败');
				 } else {
					 $(".layui-nav-bar").remove();
                     let htmlstring = per(res.per_id);
                     $("#dingwei").append(htmlstring);
					 ele();
					 $("#qita").addClass("layui-this");
					 $("#username").text(res.user.username);
					 $("#answerTopic").prop("href", "answer.html?user_id="+res.user.id+"&class_id="+res.user.class_id);
					 queryGroup('single', res.user.class_id);
					 queryAllUser(res.user.class_id);
				 }
			}
		});
	}	

 function initUserRole (){
	$.ajax({
		url: "http://127.0.0.1:10001/api/manage/users",
		type: "GET",
		xhrFields: {
		       withCredentials:true  //支持附带详细信息(带cookie)
		   },
		success:(res) => {
			if ((!res.success) && (res.code == "501")) {
				layer.msg(res.message);
			    setTimeout("redirect()", 2000);
				 } else if (!res.success) {
					layer.msg('初始化失败..请重试..');
				 } else {
					 $(".layui-nav-bar").remove();
					 let htmlstring = per(res.per_id);
					 $("#dingwei").append(htmlstring);
					 ele();
					 $("#role-manager").addClass("layui-this");
					 $("#username").text(res.user.username);
					 $("#answerTopic").prop("href", "answer.html?user_id="+res.user.id+"&class_id="+res.user.class_id);
					 queryUsers(res.user.class_id, res.user.id);
				 }
			}
		});
	}
	
function redirect(){
		window.location.href="../login.html";
}

//添加单选题
function addSingle(requestdata){
	if (requestdata.group_topic_id != '0') {
		 $.ajax({
		 	url: "http://127.0.0.1:10001/api/topic/single",
		 	type: "POST",
		 	xhrFields: {
		 	       withCredentials:true  //支持附带详细信息(带cookie)
		 	   },
			headers:{
				"content-type":"application/json;charset=uft-8"
			},  
			data: JSON.stringify(requestdata),
		 	success:(res) => {
		 			layer.msg(res.message);
		 			}
		 	});
			} else {
				layer.open({
				  title: '提示信息',
				  content: '你还未选择分类'
				});
			}
}
//填空题
function addBlank(requestdata){
    if (requestdata.group_topic_id != '0') {
	$.ajax({
		url: "http://127.0.0.1:10001/api/topic/blank",
		type: "POST",
		xhrFields: {
		       withCredentials:true  //支持附带详细信息(带cookie)
		   },
				headers:{
					"content-type":"application/json;charset=uft-8"
				},  
				data: JSON.stringify(requestdata),
		  success:(res) => {
				layer.msg(res.message);
				}
		});
		} else {
			layer.open({
			  title: '提示信息',
			  content: '你还未选择分类'
			});
		}
}

//填空题
function addJudgment(requestdata){
	if (requestdata.group_topic_id != '0') {
		$.ajax({
		url: "http://127.0.0.1:10001/api/topic/judgment",
		type: "POST",
		xhrFields: {
		       withCredentials:true  //支持附带详细信息(带cookie)
		   },
		headers:{
				"content-type":"application/json;charset=uft-8"
		},  
		data: JSON.stringify(requestdata),
		success:(res) => {
				layer.msg(res.message);
				}
		});
	} else {
		layer.open({
		  title: '提示信息',
		  content: '你还未选择分类'
		});
	}
	
}

function addRec(requestdata){
	let path = '';
	switch(parseInt(requestdata.title_type)){
		case 1:
		    path = 'singleRecord';
		  break;
		case 2:
		  path = 'blankRecord'; 
		  let requestdata1 = {
			user_id: requestdata.user_id,
			topics_id: requestdata.topics_id,  
		  	group_topic_id: requestdata.group_topic_id,
		  	title: requestdata.title,
		    title_type: requestdata.title_type,
		  	answerBlank: []
		  }
		  //对数据进行处理将多个答案遍历，添加到json中。
		  let answerblank =  $("input[name='answerBlank']");
		  $.each(answerblank, (i, n)=>{
		  	 let answerThis = $(n);
		  	 requestdata1.answerBlank[i] = answerThis.val();
		  });
		  requestdata = requestdata1;
		  break;
		case 3:
		  path = 'singleRecord';
		  break;
	}
	$.ajax({
		url: "http://127.0.0.1:10001/api/topic/"+path,
		type: "POST",
		xhrFields: {
		       withCredentials:true  //支持附带详细信息(带cookie)
		   },
		headers:{
			"content-type":"application/json;charset=uft-8"
		},  
		data: JSON.stringify(requestdata),
		success:(res) => {
				if (res.success) {
					let group_id = $("#group-select").val();
					let user_id = GetQueryValue("user_id");
					randomQuery(group_id, user_id);
				} else {
					layer.open({
					  title: '提示信息',
					  content: res.message
					});
				}
				}
		});
}

function randomQuery(group_id, user_id){
	$.ajax({
		url: "http://127.0.0.1:10001/api/topic/topics/"+group_id+"/"+user_id,
		type: "GET",
		xhrFields: {
		       withCredentials:true  //支持附带详细信息(带cookie)
		}, 
		success:(res) => {
		   if (res.success){
			 $("textarea[name='title']").html(res.result.title);
			 $("input[name='topics_id']").val(res.result.id);
			  $("input[name='group_topic_id']").val(res.result.group_topic_id);
		   switch (res.result.title_type){
		   	case 1:
			     $("input[name='title_type']").val(1);
			    let singleJson = JSON.parse(res.result.option_ans);
				let htmlSingle = getSingleHtml(singleJson.A, singleJson.B, singleJson.C, singleJson.D);
				$("#content").html(htmlSingle);
		   		break;
			case 2:
			    $("input[name='title_type']").val(2);
			    let answerSize = parseInt(res.result.answer);
				let htmlblank = getBlankHtml(answerSize);
				$("#content").html(htmlblank);
				break;
			case 3:
			    $("input[name='title_type']").val(3);
			    let judgmentJson = JSON.parse(res.result.option_ans);
				let htmlJudgment = getJudgment(judgmentJson.correct, judgmentJson.error);
				$("#content").html(htmlJudgment);
				break;
				}
			} else if (!res.success&&res.code == 501) {
			   layer.open({
			     title: '提示信息',
			     content: res.message
			   });
			   $("textarea[name='title']").html('');
			  $("#content").html('');	 
			} else {
				layer.open({
				  title: '提示信息',
				  content: res.message
				});
			}
		//重新渲染
		layui.use('form', function(){  //此段代码必不可少
			var form = layui.form;
			form.render();
		 });	
			}
		});
}

function record(){
	window.location.href="../record.html";
}

function add(){
	window.location.href="../add.html";
}

function queryUsers(class_id, user_id){
	//如果长度等于空证明用户还没登录,不要去查询分类.
	if (class_id.length != 0 && user_id.length != 0) {
	    $.ajax({
	    	url: "http://127.0.0.1:10001/api/manage/users/"+class_id+"/"+user_id,
	    	type: "GET",
			xhrFields: {
			       withCredentials:true  //支持附带详细信息(带cookie)
			   },
	    	success:(res) => {
	    		if (!res.success) {
	    			layer.open({
	    			  title: '提示信息',
	    			  content: res.message
	    			});
	    		} else {
	    			 $.each(res.results, (i,n) =>{
                        $("#tbody").append(getRoleHtml(n.username, n.id)); 
					 });
					   layui.use('form', function(){  //此段代码必不可少
					   	var form = layui.form;
					   	form.render();
					    });	
	    		}
	    	}
	    });	
	} else {
				layui.use('layer', function(){
					var layer = layui.layer;
					layer.msg("初始化异常...请刷新重试..");
				 });
			}
}


function queryRole(id, user_id){
	if (user_id.length > 0) {
	$.ajax({
		url: "http://127.0.0.1:10001/api/manage/roles/"+user_id,
		type: "GET",
		xhrFields: {
		       withCredentials:true  //支持附带详细信息(带cookie)
		   },
		xhrFields: {
		       withCredentials:true  //支持附带详细信息(带cookie)
		   },
		success:(res) => {
			   if(!res.success && res.code == 503) {
					 $("#"+id).html('');
					 layui.use('form', function(){  //此段代码必不可少
					 	var form = layui.form;
					 	form.render();
					  });
					 layer.msg(res.message);
				 } else if (!res.success) {
				  layer.msg(res.message);
				  $("#"+id).html('');
				  layui.use('form', function(){  //此段代码必不可少
				  	var form = layui.form;
				  	form.render();
				   });
				 } else {
					 let html = '';
					 $.each(res.results, (i,n)=>{
					   html += '<option value="'+n.id+'">'+n.role_name+'('+n.prompt+')</option>';
					 })
					 $("#"+id).html(html);
					 layui.use('form', function(){  //此段代码必不可少
					 	var form = layui.form;
					 	form.render();
					  });
				 }
			}
		});
		} else {
			layer.open({
			  title: '提示信息',
			  content: '发生错误,请刷新重试..'
			 });
		}
}

function addRole(user_id, role_id){
	let role_id1 = $("#"+role_id).val();
	if (user_id.length > 0 && role_id1 != null) {
		let requestdata = {
		    "user_id": user_id,
			"role_id": role_id1
	        }
	$.ajax({
		url: "http://127.0.0.1:10001/api/manage/roles/",
		type: "POST",
		data: requestdata,
		xhrFields: {
		       withCredentials:true  //支持附带详细信息(带cookie)
		   },
		success:(res) => {
				 if (!res.success) {
				   layer.open({
				   title: '提示信息',
				   content: res.message
				 });
				 } else {
					 layer.msg("添加成功");
					 //刷新
					 queryRole(role_id, user_id);
				 }
			}
		});
		} else {
			layer.open({
			  title: '提示信息',
			  content: '未选择角色..'
			 });
		}
}

function queryRole1(id, user_id){
	if (user_id.length > 0) {
	$.ajax({
		url: "http://127.0.0.1:10001/api/manage/roles1/"+user_id,
		type: "GET",
		xhrFields: {
		       withCredentials:true  //支持附带详细信息(带cookie)
		   },
		success:(res) => {
			   if(!res.success && res.code == 504) {
				    layer.msg(res.message);
					$("#"+id).html('');
					layui.use('form', function(){  //此段代码必不可少
						var form = layui.form;
						form.render();
					 });
				 } else if (!res.success){
					  layer.msg(res.message);
					  $("#"+id).html('');
					  layui.use('form', function(){  //此段代码必不可少
					  	var form = layui.form;
					  	form.render();
					   });
				   } else {
					   let html = '';
					   $.each(res.results, (i,n)=>{
					   		 html += '<option value="'+n.id+'">'+n.role_name+'('+n.prompt+')</option>';
					   })
					   $("#"+id).html(html);
					   layui.use('form', function(){  //此段代码必不可少
					   	var form = layui.form;
					   	form.render();
					    });
				   }
			}
		});
		} else {
			layer.open({
			  title: '提示信息',
			  content: '发生错误,请刷新重试..'
			 });
		}
}

function deleteRole(user_id, role_id){
	let role_id1 = $("#"+role_id).val();
	if (user_id.length > 0 && role_id1 != null) {
	$.ajax({
		url: "http://127.0.0.1:10001/api/manage/roles/"+user_id+"/"+role_id1,
		type: "DELETE",
		xhrFields: {
		       withCredentials:true  //支持附带详细信息(带cookie)
		   },
		success:(res) => {
				 if (!res.success) {
				   layer.open({
				   title: '提示信息',
				   content: res.message
				 });
				 } else {
					 layer.msg("删除成功..");
					 //刷新
					 queryRole1(role_id, user_id);
				 }
			}
		});
		} else {
			layer.open({
			  title: '提示信息',
			  content: '未选择删除的角色..'
			 });
		}
}

function ele (){
	 layui.use('element', function () {
	                var element = layui.element;
	                element.init();
	            })
}

