<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车列表</title>
<jsp:include page="myeasyui.jsp"></jsp:include>
<script type="text/javascript">
	$(function () {
		$('#dg').datagrid({    
		    url:'http://127.0.0.1:8080/findCats',    
		    columns:[[    
		        {field:'gname',title:'商品名称',width:100},    
		        {field:'price',title:'价格',width:100,align:'right'},    
		        {field:'num',title:'数量',width:100,align:'right'},    
		        {field:'totalPrice',title:'总价',width:100,align:'right'}    
		    ]]    
		});  
	})
	//添加到购物车
	function addToCat() {
		//获取用户选择的商品信息
		var rows = $('#dg').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('警告','请选择商品信息');   
			return;
		}
		var ids="";
		for (var i = 0; i < rows.length; i++) {
			var row=rows[i];
			ids+=","+row.gid;
		}
		ids=ids.substring(1);
		alert(ids);
		$.ajax({
			url:"<%=request.getContextPath()%>/addToCat",
			data:{ids:ids},
			dataType:"json",
			success:function(data){
				$.messager.alert('警告',data.msg);
				//刷新表格
				$('#dg').datagrid('reload');
			}
		})	
	}
</script>
</head>
<body>
<table id="dg"></table>  


</body>
</html>