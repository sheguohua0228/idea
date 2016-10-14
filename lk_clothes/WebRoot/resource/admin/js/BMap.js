/**
 * 
 * 百度地图 JS
 * 
 */
// 百度地图API功能
var map = new BMap.Map("allmap");
//创建地址解析器实例
var myGeo = new BMap.Geocoder();
/**
 * 设置地图中心点
 * @param city 城市名
 */
function setCenterAndZoom(city){
	map.centerAndZoom(city);
}

// 批量地址解析
map.enableScrollWheelZoom(true);
var index = 0;
var marker;//标记
/**
 * 地图标记函数
 * @param lng 经度
 * @param lat 纬度
 * @param title 信息窗口 标题
 * @param content 信息窗口 内容
 */
function bdGEO(lng, lat ,title, content) {
	var point = new BMap.Point(lng, lat);
	addMarker(point, title ,content);
}

function bdGEOWithOverlay(lng, lat ,title, content, width){
	console.log(lng+";"+lat+";"+title+";"+content+";"+width);
	var point = new BMap.Point(lng, lat);
	addMarker(point, title ,content);
	var circle = new BMap.Circle(point,width,{fillColor: "red", strokeColor:"blue", strokeWeight:1, strokeOpacity:0.2}); //创建圆
	map.addOverlay(circle);
}

// 编写自定义函数,创建标注
function addMarker(point,title, content) {
	marker = new BMap.Marker(point);
	map.addOverlay(marker);
	var opts = {
		width : 200,     // 信息窗口宽度
		height: 100,     // 信息窗口高度
		title : title , // 信息窗口标题
		enableMessage:false
	};
	var infoWindow = new BMap.InfoWindow(content, opts);  // 创建信息窗口对象 
	marker.addEventListener("click", function(){
		map.openInfoWindow(infoWindow,point); //开启信息窗口
	});
}

/**
 * 根据经纬度来显示地图
 * @param lng 经度
 * @param lat 纬度
 */
function buildGEOByLngLat(lng, lat){
	map.centerAndZoom(new BMap.Point(lng, lat), 17);
}

/**
 *  根据经纬度进行定位并标记
 * @param lng 经度
 * @param lat 纬度
 */
function marker(lng, lat){
	var point = new BMap.Point(lng, lat);
	map.centerAndZoom(point , 17);
	marker = new BMap.Marker(point);
	map.addOverlay(marker);
}


