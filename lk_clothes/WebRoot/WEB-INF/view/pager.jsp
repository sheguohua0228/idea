<script type="text/javascript">
$().ready( function() {
	
	var $pager = $("#pager");
	
	$pager.pager({
		pagenumber: ${pager.pageNo},
		pagecount: ${pager.totalPages},
		buttonClickCallback: $.gotoPage
	});

})
</script>
<span id="pager"></span>
<input type="hidden" name="pageNo" id="pageNumber" value="${pager.pageNo}" />