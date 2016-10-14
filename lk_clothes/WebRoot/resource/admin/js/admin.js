
shopxx = {
	base: "/liankun",
	currencySign: "￥",
	currencyUnit: "元",
	priceScale: "2",
	priceRoundType: "roundHalfUp"
};

if(typeof(KindEditor) != "undefined") {
	KindEditor.ready(function(K) {
		editor = K.create("#editor", {
			height: "350px",
			items: ["source", "|", "undo", "redo", "|", "preview", "print", "template", "cut", "copy", "paste", "plainpaste", "wordpaste", "|","justifyleft", "justifycenter", "justifyright", "justifyfull", "insertorderedlist", "insertunorderedlist", "indent", "outdent", "subscript", "superscript", "clearhtml", "quickformat", "selectall", "|", "fullscreen", "/", "formatblock", "fontname", "fontsize", "|", "forecolor", "hilitecolor", "bold", "italic", "underline", "strikethrough", "lineheight", "removeformat", "|", "image", "flash", "media", "insertfile", "table", "hr", "emoticons", "map", "pagebreak", "link", "unlink"],
			syncType: "form",
			allowFileManager: true,
			uploadJson: shopxx.base + "/admin/file!ajaxUpload.action",
			fileManagerJson: shopxx.base + "/admin/file!ajaxBrowser.action",
			afterChange: function() {
				this.sync();
			}
		});
	});
}

// 货币格式化
function currencyFormat(price) {
	price = setScale(price, shopxx.priceScale, shopxx.priceRoundType);
	return shopxx.currencySign + price + shopxx.currencyUnit;
}

$().ready( function() {

	/* ---------- List ---------- */
	
	var $listForm = $("#listForm");// 列表表单
	if ($listForm.size() > 0) {
		var $searchButton = $("#searchButton");// 查找按钮
		var $allCheck = $("#listTable input.allCheck");// 全选复选框
		var $listTableTr = $("#listTable tr:gt(0)");
		var $idsCheck = $("#listTable input[name='ids']");// ID复选框
		var $deleteButton = $("#deleteButton");// 删除按钮
		var $nextButton=$("#nextButton");
		var $pageNumber = $("#pageNumber");// 当前页码
		var $pageSize = $("#pageSize");// 每页显示数
		var $sort = $("#listTable .sort");// 排序
		var $orderBy = $("#orderBy");// 排序字段
		var $order = $("#order");// 排序方式
		var $status=$("#status");   //工单状态,zuojk add
		var $printBtn=$("#printBtn");
		var $printButton=$("#printButton");
		
		// 全选
		$allCheck.click( function() {
			var $this = $(this);
			if ($this.attr("checked")) {
				$idsCheck.attr("checked", true);
				$deleteButton.attr("disabled", false);
				$nextButton.attr("disabled", false);
				$printBtn.attr("disabled",false);
				$printButton.attr("disabled",false);
				$listTableTr.addClass("checked");
				$listTableTr.removeClass("noprint");
			} else {
				$idsCheck.attr("checked", false);
				$deleteButton.attr("disabled", true);
				$nextButton.attr("disabled", true);
				$printBtn.attr("disabled",true);
				$printButton.attr("disabled",true);
				$listTableTr.removeClass("checked");
				$listTableTr.addClass("noprint");
			}
		});
		
		// 无复选框被选中时,删除按钮不可用
		$idsCheck.click( function() {
			var $this = $(this);
			if ($this.attr("checked")) {
				$this.parent().parent().addClass("checked");
				$deleteButton.attr("disabled", false);
				$nextButton.attr("disabled", false);
				$printBtn.attr("disabled",false);
				$printButton.attr("disabled",false);
				//
				$this.parent().parent().removeClass("noprint");
			} else {
				$this.parent().parent().removeClass("checked");
				var $idsChecked = $("#listTable input[name='ids']:checked");
				if ($idsChecked.size() > 0) {
					$deleteButton.attr("disabled", false);
					$nextButton.attr("disabled", false);
					$printBtn.attr("disabled",false);
					$printButton.attr("disabled",false);
				} else {
					$deleteButton.attr("disabled", true);
					$nextButton.attr("disabled", true);
					$printBtn.attr("disabled",true);
					$printButton.attr("disabled",true);
				}
				$this.parent().parent().addClass("noprint");
			}
		});
		
		// 批量删除
		$deleteButton.click( function() {
			var url = $(this).attr("url");
			var $idsCheckedCheck = $("#listTable input[name='ids']:checked");
			$.dialog({type: "warn", content: "您确定要删除吗?", ok: "确 定", cancel: "取 消", modal: true, okCallback: batchDelete});
			function batchDelete() {
				$.ajax({
					url: url,
					data: $idsCheckedCheck.serialize(),
					type: "POST",
					dataType: "json",
					cache: false,
					success: function(data) {
						if (data.status == "success") {
							$idsCheckedCheck.parent().parent().remove();
						}
						$deleteButton.attr("disabled", true);
						$allCheck.attr("checked", false);
						$idsCheckedCheck.attr("checked", false);
						$.message({type: data.status, content: data.message});
					}
				});
			}
		});
		$nextButton.click(function(){
			var url = $(this).attr("url");
			var $idsCheckedCheck = $("#listTable input[name='ids']:checked");
			$.dialog({type: "warn", content: "您确定所选已打印?", ok: "确 定", cancel: "取 消", modal: true, okCallback: batchDelete});
			function batchDelete() {
				$.ajax({
					url: url,
					data:$idsCheckedCheck.serialize(),
					type: "POST",
					dataType: "json",
					cache: false,
					success: function(data) {
						if (data.status == "success") {
							$idsCheckedCheck.parent().parent().remove();
						}
						$nextButton.attr("disabled", true);
						$allCheck.attr("checked", false);
						$idsCheckedCheck.attr("checked", false);
						$.message({type: data.status, content: data.message});
					}
				});
			}
			
		});
		$printButton.click(function(){
			var url = $(this).attr("url");
			var $idsCheckedCheck = $("#listTable input[name='ids']:checked");
			 window.open(url+"?"+$idsCheckedCheck.serialize());
					 
				 
			
		});
		 function uniqueArray(data){  
			   data = data || [];  
			   var a = {};  
			   for (var i=0; i<data.length; i++) {  
			       var v = data[i];  
			       if (typeof(a[v]) == 'undefined'){  
			            a[v] = 1;  
			       }  
			   };  
			   data.length=0;  
			   for (var i in a){  
			        data[data.length] = i;  
			   }  
			   return data;  
		}  
		// 查找
		$searchButton.click( function() {
			$pageNumber.val("1");
			$listForm.submit();
		});
	
		// 每页显示数
		$pageSize.change( function() {
			$pageNumber.val("1");
			$listForm.submit();
		});
	    //工单状态值,zuojk add
		$status.change(function(){
			$pageNumber.val("1");
			$listForm.submit();
		});  
		// 排序
		$sort.click( function() {
			var $currentOrderBy = $(this).attr("name");
			if ($orderBy.val() == $currentOrderBy) {
				if ($order.val() == "") {
					$order.val("asc")
				} else if ($order.val() == "desc") {
					$order.val("asc");
				} else if ($order.val() == "asc") {
					$order.val("desc");
				}
			} else {
				$orderBy.val($currentOrderBy);
				$order.val("asc");
			}
			$pageNumber.val("1");
			$listForm.submit();
		});
	
		// 排序图标效果
		if ($orderBy.val() != "") {
			$sort = $("#listTable .sort[name='" + $orderBy.val() + "']");
			if ($order.val() == "asc") {
				$sort.removeClass("desc").addClass("asc");
			} else {
				$sort.removeClass("asc").addClass("desc");
			}
		}
		
		// 页码跳转
		$.gotoPage = function(id) {
			$pageNumber.val(id);
			$listForm.submit();
		}
	}
	//菜单点击样式
	$(".menu dd").each(function(){
	        $(this).find("a").click(function(){
	            $(".menu dl dd").removeClass("lef-click-bg");
	            $(this).parent().addClass("lef-click-bg");
	        });
	 });
	//折叠样式
	  $(".menu dt").each(function(){
        $(".menu dl dd").hide();
        $(".menu dl:eq(0) dd").show();
        $(this).click(function(){
            $(".menu dl dd").hide();
            $(this).parent("dl").children("dd").show();
        });
    });

	 
});

