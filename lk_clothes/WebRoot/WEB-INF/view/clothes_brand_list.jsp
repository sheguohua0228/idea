<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery-1.11.1.min.js"></script>

<script type="text/javascript" src="<%=basePath%>/resource/common/layer/layer.js"></script>
<link href="<%=basePath%>/resource/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/resource/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script>
 
	$("button[id*='delete']").bind("click",function(){   //删除品牌
       
		var j=$(this).attr("id").replace(/[^0-9]/ig,"");
		var val=$("#selects"+j+" option:selected").text();
		$("#selects"+j+" option:selected").remove();
		if(j==0){
			
			var index = Stringdeleteindexof(Abrand,val);  if (index > -1) {Abrand.splice(index, 1); }  
	    }else{
	    	var index = Stringdeleteindexof(Bbrand,val);  if (index > -1) {Bbrand.splice(index, 1);}  
	    }
	});
	
	$("button[id*='insert']").bind("click",function(){   //新增品牌
		var j=$(this).attr("id").replace(/[^0-9]/ig,"");
		var val=$("#selects"+j+" option:selected").text();
		alert(val);
       $("#selects0 option").map(function(){
    	   
    	   return $(this).val();
       }).get().join(";");
		 
	})
     
</script>
<style type="text/css">
.demo{width:500px;margin:40px auto;position:relative;}
.brand_grade{font-size: 15px;margin-bottom: 20px;}
/* tag */
.default-tag a,.default-tag a span,.plus-tag a,.plus-tag a em,.plus-tag-add a{background:url(../resource/admin/images/tagbg.png) no-repeat;}
.tagbtn a{color:#333333;display:block;float:left;height:22px;line-height:22px;overflow:hidden;margin:0 10px 10px 0;padding:0 10px 0 5px;white-space:nowrap;}
/* default-tag */
.default-tag{padding:16px 0 0 0;}
.default-tag a{background-position:100% 0;}
.default-tag a:hover{background-position:100% -22px;}
.default-tag a span{padding:0 0 0 11px;background-position:0 -60px;}
.default-tag a:hover span{background-position:0 -98px;}
.default-tag a.selected{opacity:0.6;filter:alpha(opacity=60);}
.default-tag a.selected:hover{background-position:100% 0;cursor:default;}
.default-tag a.selected:hover span{background-position:0 -60px;}
/* plus-tag */
.plus-tag{padding:0 0 10px 0;}
.plus-tag a{background-position:100% -22px;}
.plus-tag a span{float:left;}
.plus-tag a em{display:block;float:left;margin:5px 0 0 8px;width:13px;height:13px;overflow:hidden;background-position:-165px -100px;cursor:pointer;}
.plus-tag a:hover em{background-position:-168px -64px;}
/* plus-tag-add */
.plus-tag-add{margin-top: 10px;}
.plus-tag-add li{height:56px;position:relative;}
.plus-tag-add li .label{position:absolute;left:-110px;top:7px;display:block;}
.plus-tag-add a{float:left;margin:12px 0 0 20px;display:inline;font-size:18px;background-position:-289px -59px;padding:0 0 0 16px;}
.plus-tag-add a.plus{background-position:-289px -96px;}

</style>
</head>
 <body>

<div class="headeline"></div>

<!--演示内容开始-->


<div class="demo" >
	<div class="brand_grade"><b>${type}级品牌：</b></div>
	<div class="plus-tag tagbtn clearfix" id="myTags" style="width: 450px;max-height: 450px;overflow-y: scroll;">
		<c:forEach items="${brands }" var="brand">
				<a value="${brand.id }" title="${brand.brandName }" href="javascript:void(0);"><span>${brand.brandName }</span><em></em></a>
		</c:forEach>
	</div>

	<div class="plus-tag-add">
		<form id="" action="" class="login">
			<ul class="Form FancyForm">
				<li>
					<span class="label"></span>
					<input id="" name="" type="text" class="formText" placeholder="输入品牌"/>
					<span class="fff"></span>
					<button type="button" class="formButton" >添加品牌</button>
				</li>
			</ul>
		</form>
	</div><!--plus-tag-add end-->
	
	 
</div>
<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.1.4.2-min.js"></script>
<script type="text/javascript">
var FancyForm=function(){
	return{
		inputs:".FancyForm input, .FancyForm textarea",
		setup:function(){
			var a=this;
			this.inputs=$(this.inputs);
			a.inputs.each(function(){
				var c=$(this);
				a.checkVal(c);
			});
			a.inputs.bind("keyup blur",function(){
				var c=$(this);
				a.checkVal(c);
			});
		},checkVal:function(a){
			a.val().length>0?a.parent("li").addClass("val"):a.parent("li").removeClass("val");
		}
	}
}();
</script>

<script type="text/javascript">
$(document).ready(function() {
	FancyForm.setup();
});
</script>

<script type="text/javascript">
var searchAjax=function(){};
var G_tocard_maxTips=3000000;

$(function(){(
	function(){
		
		var a=$(".plus-tag");
		
		$("a em",a).live("click",function(){
			var c=$(this).parents("a"),b=c.attr("title"),d=c.attr("value");
			layer.confirm('是否删除品牌"'+b+'"?',  function(index){
				 $.ajax({
					 url:"brand/delete",
					 data:{brandId:d},
					 cache:false,
					 success:function(msg){
						 if(msg.status=="success"){
							 delTips(b,d);
						 }
					 },
					 error:function(msg){
						 layer.msg(msg);
					 }
				 });
				layer.close(index);
			} );
			
		});
		
		hasTips=function(b){
			var d=$("a",a),c=false;
			d.each(function(){
				if($(this).attr("title")==b){
					c=true;
					return false;
				}
			});
			return c;
		};

		isMaxTips=function(){
			return	
			$("a",a).length>=G_tocard_maxTips;
		};

		setTips=function(c,d){
			if(hasTips(c)){
				layer.msg("品牌"+c+"已经存在");
				return false;
			}if(isMaxTips()){
				alert("最多添加"+G_tocard_maxTips+"个标签！");
				return false;
			}
			var b=d?'value="'+d+'"':"";
			a.append($("<a "+b+' title="'+c+'" href="javascript:void(0);" ><span>'+c+"</span><em></em></a>"));
			searchAjax(c,d,true);
			return true;
		};

		delTips=function(b,c){
			if(!hasTips(b)){
				return false;
			}
			$("a",a).each(function(){
				var d=$(this);
				if(d.attr("title")==b){
					d.remove();
					return false;
				}
			});
			searchAjax(b,c,false);
			return true;
		};

		getTips=function(){
			var b=[];
			$("a",a).each(function(){
				b.push($(this).attr("title"));
			});
			return b;
		};

		getTipsId=function(){
			var b=[];
			$("a",a).each(function(){
				b.push($(this).attr("value"));
			});
			return b;
		};
		
		getTipsIdAndTag=function(){
			var b=[];
			$("a",a).each(function(){
				b.push($(this).attr("value")+"##"+$(this).attr("title"));
			});
			return b;
		}
	}
	
)()});
</script>

<script type="text/javascript">
// 更新选中标签标签
$(function(){
	setSelectTips();
	$('.plus-tag').append($('.plus-tag a'));
});
var searchAjax = function(name, id, isAdd){
	setSelectTips();
};
// 搜索
(function(){
	var $b = $('.plus-tag-add button'),$i = $('.plus-tag-add input');
	$i.keyup(function(e){
		if(e.keyCode == 13){
			$b.click();
		}
	});
	$b.click(function(){
		
		var name = $i.val();
		
		if(name != ''){
			if(hasTips(name)){
				layer.msg("品牌"+name+"已经存在");
				return false;
			}
		 $.ajax({
			 url:"brand/add",
			 data:{brandName:name,grade:'${type}'},
			 success:function(msg){
				 if(msg.status=="success"){
					 setTips(name,msg.data);
						$i.val('');
						$i.select();
						
						layer.msg("品牌添加成功");
				 }
			 },
			 error:function(msg){
				 layer.msg(msg);
			 }
		 });
		}
	});
})();
// 推荐标签
(function(){
	// 更新高亮显示
	setSelectTips = function(){
		var arrName = getTips();
		if(arrName.length){
			$('#myTags').show();
		}else{
			$('#myTags').hide();
		}
		$('.default-tag a').removeClass('selected');
		$.each(arrName, function(index,name){
			$('.default-tag a').each(function(){
				var $this = $(this);
				if($this.attr('title') == name){
					$this.addClass('selected');
					return false;
				}
			})
		});
	}

})();
 
</script>
<!--演示内容结束-->
 
</body>
</html>